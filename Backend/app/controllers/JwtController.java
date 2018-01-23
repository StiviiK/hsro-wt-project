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

public class JwtController extends Controller {

    @Inject
    private JwtControllerHelper jwtControllerHelper;

    @Inject
    private Config config;

    /*public Result generateSignedToken() throws UnsupportedEncodingException {
        return ok("signed token: " + getSignedToken(5l));
    }*/

    public Result login()  {
        JsonNode body = request().body().asJson();


        if (body == null) {
            Logger.error("json body is null");
            return forbidden(ResultHelper.completed(false,"Json body was empty",body));
        }


        if(body.hasNonNull("googleToken")){
            NetHttpTransport trans=new NetHttpTransport();
            JacksonFactory factory = new JacksonFactory();
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(trans, factory)
                    .setAudience(Collections.singletonList("46761239813-oumj8o0oh51ipa90d6gf88jkp2d946n3.apps.googleusercontent.com"))
                    // Or, if multiple clients access the backend:
                    //  .setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
                    .build();
            System.out.println("token:"+body.get("googleToken").asText());
            /*/ (Receive idTokenString by HTTPS POST)
            try{
                System.out.println("Im trying to verify GoogleToken");
                GoogleIdToken idToken = verifier.verify(body.get("googleToken").asText());
                System.out.println("still trying");
                if (idToken != null) {
                    Payload payload = idToken.getPayload();

                    // Print user identifier
                    String userId = payload.getSubject();
                    System.out.println("User ID: " + userId);

                    // Get profile information from payload
                    String email = payload.getEmail();
                    boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
                    String name = (String) payload.get("name");
                    String pictureUrl = (String) payload.get("picture");
                    String locale = (String) payload.get("locale");
                    String familyName = (String) payload.get("family_name");
                    String givenName = (String) payload.get("given_name");

                    // Use or store profile information
                    // ...

                } else {
                    System.out.println("Invalid ID token.");
                }
                System.out.println(idToken);
            }
            catch(Exception exception){
                return badRequest(ResultHelper.completed(false,"invalid googleToken",null));
            }
            / *///Google token is authenticated, search for the user now
            JsonNode user = body.get("user");
            User current =Json.fromJson(user,User.class);
            List<User> userFoundList= Ebean.find(User.class).where().eq("email",current.getEmail()).findList();
            String currentToken;
            //Generate JWT


            //Create userview in JSon


            if(userFoundList.size()!=0){
                //LOG IN
                current=userFoundList.get(0);
                try {
                    currentToken = getSignedToken(current);
                }
                catch(UnsupportedEncodingException except){
                    return badRequest("UnsupportedEncoding Exception");
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
                    return badRequest("UnsupportedEncoding Exception");
                }
                JsonNode dataObject=userCreator(currentToken,current);
                return(ok(ResultHelper.completed(true,"Created user!",dataObject)));
            }
        }
        /*
        if (body.hasNonNull("username") && body.hasNonNull("password") /* && body.get("username").asText().equals("abc")) {

            User currentUser=Ebean.find(User.class)
                    .where().eq("username", body.get("username").asText())
                    .findOne();
            System.out.println("this is what i found:"+currentUser.getName()+currentUser.getPassword());
            System.out.println("this is what i have:"+body.get("username").asText()+body.get("password").asText());

            if(currentUser!=null){
                if(body.get("password").asText().equals(currentUser.getPassword())){
                    //pw correct! logging in!
                    //System.out.println(currentUser.getName()+currentUser.getPassword());
                    ObjectNode result = Json.newObject();
                    String currentToken=getSignedToken(currentUser);
                    currentUser.setCurrentToken(currentToken);
                    result.put("access_token", currentToken);
                    return ok(result);
                }
                else{
                    return unauthorized("password is incorrect");
                }


            }
            else{
                    return badRequest("no user found with name: "+body.get("username"));
            }


        } else {
            Logger.error("json body not as expected: {}", body.toString());
        }
        */
        return forbidden();
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