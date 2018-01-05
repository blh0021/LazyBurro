package LazyBurro.Helper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class JSON {
    public static String prettyPrint(String json) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(json);
        return gson.toJson(je);
    }

    public static Map<String, String> parseSimpleJson(String json) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(json);
        Set<Map.Entry<String, JsonElement>> obj = je.getAsJsonObject().entrySet();
        Map<String, String> flatObj = new HashMap<String, String>();
        for (Map.Entry<String, JsonElement> element : je.getAsJsonObject().entrySet()) {
            flatObj.put(element.getKey(), element.getValue().getAsString());
        }
        return flatObj;
    }
}
