package LazyBurro.Helper;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class JSON {
    public static String prettyPrint(String json) {
        if (json.charAt(0) == '[') {
            JSONArray jsa = new JSONArray(json);
            return prettyPrint(jsa);
        } else {
            JSONObject jsObject = new JSONObject(json);
            return prettyPrint(jsObject);
        }
    }

    public static String prettyPrint(JSONObject json) {
        return json.toString(4);
    }

    public static String prettyPrint(JSONArray json) {
        return json.toString(4);
    }

    private static String jsonElementString(String key, String val) {
        return "\"" + key + "\": \"" + val + "\"";
    }

    public static String mapToString(Map<String, List<String>> mapList) {
        String tmp = "{";
        int mlength = mapList.size();
        int ct = 1;
        for (Map.Entry<String, List<String>> element : mapList.entrySet()) {
            String result = StringEscapeUtils.escapeJava(element.getValue().get(0));
            tmp += jsonElementString(element.getKey(), result);
            if (ct < mlength) {
                tmp += ",";
            }
            ct++;
        }
        tmp += "}";
        return tmp;
    }

}
