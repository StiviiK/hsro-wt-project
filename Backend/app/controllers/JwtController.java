package controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.typesafe.config.Config;
import io.ebean.Ebean;
import io.ebean.EbeanServer;
import jwt.JwtControllerHelper;
import jwt.VerifiedJwt;
import jwt.filter.Attrs;
import models.User;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.io.UnsupportedEncodingException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;

public class JwtController extends Controller {

    @Inject
    private JwtControllerHelper jwtControllerHelper;

    @Inject
    private Config config;

    /*public Result generateSignedToken() throws UnsupportedEncodingException {
        return ok("signed token: " + getSignedToken(5l));
    }*/

    public Result login() throws UnsupportedEncodingException {
        JsonNode body = request().body().asJson();

        if (body == null) {
            Logger.error("json body is null");
            return forbidden();
        }

        if (body.hasNonNull("username") && body.hasNonNull("password") /* && body.get("username").asText().equals("abc")*/) {

            User currentUser=Ebean.find(User.class)
                    .where().eq("username", body.get("username").asText())
                    .findOne();
            System.out.println("this is what i found:"+currentUser.getUsername()+currentUser.getPassword());
            System.out.println("this is what i have:"+body.get("username").asText()+body.get("password").asText());

            if(currentUser!=null){
                if(body.get("password").asText().equals(currentUser.getPassword())){
                    //pw correct! logging in!
                    //System.out.println(currentUser.getUsername()+currentUser.getPassword());
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

        return forbidden();
    }

    private String getSignedToken(User user) throws UnsupportedEncodingException {
        String secret = config.getString("play.http.secret.key");
        System.out.println(secret);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withClaim("username",user.getUsername())
                .withClaim("email",user.getEmail())
                .withClaim("user_id", user.getId())
                .withExpiresAt(Date.from(ZonedDateTime.now(ZoneId.systemDefault()).plusMinutes(10).toInstant()))
                .sign(algorithm);
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