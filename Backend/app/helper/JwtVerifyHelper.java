package helper;

import com.fasterxml.jackson.databind.JsonNode;
import play.Logger;
import play.libs.Json;
import play.mvc.Http;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class JwtVerifyHelper {
    public static Long getUserFromToken(Http.Headers header) {
        if(header.get("Authorization").isPresent()){
            String auth =header.get("Authorization").toString();
            //Data in Header authorization
            System.out.println("Auth:"+auth);
            //Trim the authorization data to the pure token
            String tokenString = auth.substring(auth.indexOf("[") + 1, auth.indexOf("]"));
            //Split the JWT in Head/Payload/Signing
            String[] splitToken = tokenString.split("\\.");

            // splitToken[0] is the header, splitToken[1] is the payload and
            // splitToken[2] is the signature

            //decode the payload
            byte[] decodedBytes = Base64.getDecoder().decode(splitToken[1]);

            try {
                //turn bytes to String again
                String payload = new String(decodedBytes, "UTF-8");
                System.out.println("Payload is : "+payload);
                //parse string to json
                JsonNode userNode= Json.parse(payload);
                System.out.println("Payload in json:"+userNode);
                //get user_id from json
                if(userNode.hasNonNull("user_id")) {
                    Long id = userNode.get("user_id").asLong();

                    System.out.println("usernode:" + userNode + "userID" + id);
                    //return user id from payload for verification
                    return id;
                }
                return null;
            }
            catch (Exception e){
                Logger.info("couldnt decode payload "+e.toString());
                return null;
            }

        }
        Logger.info("No header authorization here!");
        return null;
    }
}
