package utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class JSONHandler {

    private JSONObject jsonObject;
    private static JSONHandler jsonHandler;

    public static JSONHandler getJsonHandlerObject(){
        if(jsonHandler == null){
            return new JSONHandler();
        }
        return jsonHandler;
    }

    public JSONObject loadJSONFile(String fileName){
        JSONParser jsonParser = new JSONParser();
        try {
            jsonObject = (JSONObject)jsonParser.parse(new FileReader(System.getProperty("user.dir")+"/src/main/resources/JsonFiles/"+fileName+".json"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public JSONObject getUpdatedJSONObject(JSONObject jsonObject, Map<String, Object> fieldsToBeUpdated){
        JSONObject updatedJSONObject = jsonObject;
        for(Map.Entry<String, Object> fieldEntry :fieldsToBeUpdated.entrySet()){
            updatedJSONObject = getUpdatedJSONObject(updatedJSONObject, fieldEntry.getKey(), fieldEntry.getValue());
            }
        return updatedJSONObject;
        }

    public JSONObject getUpdatedJSONObject(JSONObject updatedJSONObject, String fieldName, Object fieldValue) {
        Set<String> jsonKeys = updatedJSONObject.keySet();
        Iterator<String> iterator = jsonKeys.iterator();
        String key;
        while(iterator.hasNext()){
            key = iterator.next();
            if(key.equals(fieldName)){
                if(fieldValue.equals("null")){
                    updatedJSONObject.put(key, null);
                }else {
                    updatedJSONObject.put(key, fieldValue);
                }
                break;
            }
            else if(key!=null && updatedJSONObject.get(key) instanceof JSONObject){
                    getUpdatedJSONObject((JSONObject) updatedJSONObject.get(key), fieldName, fieldValue);
            } else if(key!=null && updatedJSONObject.get(key) instanceof JSONArray){
                    for(Object object : (JSONArray)updatedJSONObject.get(key) ){
                        getUpdatedJSONObject((JSONObject) object, fieldName, fieldValue);
                    }
            }
        }
        return updatedJSONObject;
    }


}
