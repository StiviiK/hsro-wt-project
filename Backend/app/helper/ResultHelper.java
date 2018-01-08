package helper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;

public class ResultHelper {
    public static JsonNode success(String message,JsonNode object){
        ObjectNode retNode= Json.newObject();
        retNode.put("Message",message);
        if(object!=null){
        retNode.set("Object",object);
        }
        return retNode;
    }
}
