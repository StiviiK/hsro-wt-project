package controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import com.typesafe.config.Config;
import helper.ResultHelper;
import io.ebean.Ebean;
import jwt.JwtControllerHelper;
import jwt.VerifiedJwt;
import jwt.filter.Attrs;
import models.User;
import play.Logger;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;
import play.mvc.Controller;
import play.mvc.Result;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;


import javax.inject.Inject;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.completedFuture;

public class JwtController extends Controller {
    private HttpExecutionContext hec;
    private final WSClient ws;
    @Inject
    private JwtControllerHelper jwtControllerHelper;

    @Inject
    private Config config;

    @Inject
    public JwtController(HttpExecutionContext hec,WSClient ws) {
        this.hec = hec;
        this.ws=ws;
    }

    /*public Result generateSignedToken() throws UnsupportedEncodingException {
        return ok("signed token: " + getSignedToken(5l));
    }*/
    private CompletionStage<WSResponse> checkGoogleToken(String token) {
        return ws.url("https://www.googleapis.com/oauth2/v1/tokeninfo?access_token="+token).get();

    }
    public CompletionStage<Result> login()  {
        return CompletableFuture.supplyAsync(() -> {
        JsonNode body = request().body().asJson();

        if (body == null) {
            Logger.error("json body is null");
            completedFuture(forbidden(ResultHelper.completed(false,"Json body was empty",body)));
        }


        if(body.hasNonNull("googleToken")){
            boolean verifiedByGoogle=false;
            NetHttpTransport trans=new NetHttpTransport();
            JacksonFactory factory = new JacksonFactory();
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(trans, factory)
                    .setAudience(Collections.singletonList("46761239813-oumj8o0oh51ipa90d6gf88jkp2d946n3.apps.googleusercontent.com"))
                    // Or, if multiple clients access the backend:
                    //  .setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
                    .build();
            System.out.println("token:"+body.get("googleToken").asText());
            JsonNode node;
            CompletionStage<WSResponse> res=checkGoogleToken(body.get("googleToken").asText());
            WSResponse response;
            try {
                response = res.toCompletableFuture().get();
                System.out.println("response from google:"+response.getBody());
                System.out.println("responseStatus:"+response.getStatus());
                if(response.getStatus()==200){
                    verifiedByGoogle=true;
                }
            }
            catch(Exception e){
                System.out.println("Attempt to call google for validation failed");
                return badRequest(ResultHelper.completed(false,"invalid googleToken or no response",null));
            }

            if(verifiedByGoogle){
            //Google token is authenticated, search for the user now
            JsonNode user = body.get("user");
            User current =Json.fromJson(user,User.class);
            List<User> userFoundList= Ebean.find(User.class).where().eq("email",current.getEmail()).findList();
            String currentToken;
            //Generate JWT & Login/Create
            if(userFoundList.size()!=0){
                //LOG IN
                current=userFoundList.get(0);
                try {
                    currentToken = getSignedToken(current);
                }
                catch(UnsupportedEncodingException except){
                   completedFuture(badRequest("UnsupportedEncoding Exception"));
                   currentToken=null;
                }

                JsonNode dataObject=userCreator(currentToken,current);
                return ok(ResultHelper.completed(true,"Logged in user!",dataObject));

            }
            else{
                //Create account

                current.save();
                try {
                    currentToken = getSignedToken(current);
                }
                catch(UnsupportedEncodingException except){
                   completedFuture(badRequest("UnsupportedEncoding Exception"));
                   currentToken=null;
                }
                JsonNode dataObject=userCreator(currentToken,current);
                return ok(ResultHelper.completed(true,"Created user!",dataObject));
            }
        }
        else{
                return badRequest(ResultHelper.completed(false,"invalid google token",null));
            }}
        return forbidden(ResultHelper.completed(false,"no googleToken found in body",null));
        }, hec.current());
    }
    private JsonNode userCreator(String currentToken,User user){
        ObjectNode dataObject=Json.newObject();
        dataObject.put("token",currentToken);
        ObjectNode userObject=Json.newObject();
        userObject.put("id",user.getId());
        dataObject.set("user",userObject);

        return dataObject;
    }
    private String getSignedToken(User user) throws UnsupportedEncodingException {
        String secret = config.getString("play.http.secret.key");
        System.out.println(secret);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withClaim("username",user.getName())
                .withClaim("email",user.getEmail())
                .withClaim("user_id", user.getId())
                .withExpiresAt(Date.from(ZonedDateTime.now(ZoneId.systemDefault()).plusMinutes(300).toInstant()))
                .sign(algorithm);
    }
    public Result stillValid(){
        return ok(ResultHelper.completed(true,"token still valid",null));
    }
    public Result requiresJwt() {
        return jwtControllerHelper.verify(request(), res -> {
            if (res.left.isPresent()) {
                return forbidden(res.left.get().toString());
            }

            VerifiedJwt verifiedJwt = res.right.get();
            Logger.debug("{}", verifiedJwt);

            ObjectNode result = Json.newObject();
            result.put("access", "granted");
            result.put("secret_data", "birds fly");
            return ok(result);
        });
    }

    public Result requiresJwtViaFilter() {
        Optional<VerifiedJwt> oVerifiedJwt = request().attrs().getOptional(Attrs.VERIFIED_JWT);
        return oVerifiedJwt.map(jwt -> {
            Logger.debug(jwt.toString());
            return ok("access granted via filter");
        }).orElse(forbidden("eh, no verified jwt found"));
    }
}