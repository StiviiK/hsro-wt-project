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
            System.out.println("Auth:"+auth);
            // String token=auth.get("Bearer").asText();
            // System.out.println("Bearer:"+auth);

            String tokenString = auth.substring(auth.indexOf("[") + 1, auth.indexOf("]"));
            // System.out.println("SUBSTRING: "+tokenString);
            //String payloadString = tokenString.substring(tokenString.indexOf(".") + 1, tokenString.indexOf("."));

            String[] splitToken = tokenString.split("\\.");

// splitToken[0] is the header, splitToken[1] is the payload and
// splitToken[2] is the signature
            byte[] decodedBytes = Base64.getDecoder().decode(splitToken[1]);

            try {
                String payload = new String(decodedBytes, "UTF-8");
                System.out.println("Payload is : "+payload);
                JsonNode userNode= Json.parse(payload);
                System.out.println("Payload in json:"+userNode);
                Long id = userNode.get("user_id").asLong();
                System.out.println("ID: "+ Json.stringify(userNode));
                //System.out.println("Payloadstribg:"+payloadString);
                //tring secret=config.getString("play.http.secret.key");
                //byte[] decodedBytes = Base64.getDecoder().decode(payloadString);
                //String decodedString = new String(decodedBytes);
                System.out.println("usernode:"+userNode+"userID"+id);
                return id;
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
