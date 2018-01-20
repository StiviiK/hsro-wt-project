package helper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;

public class ResultHelper {
    public static JsonNode completed(Boolean done,String message,JsonNode object){
        ObjectNode retNode= Json.newObject();
        retNode.put("status",done);
        retNode.put("message",message);
        if(object!=null){
        retNode.set("data",object);
        }
        return retNode;
    }
}
