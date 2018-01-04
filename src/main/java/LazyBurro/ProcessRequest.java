package LazyBurro;

import LazyBurro.Helper.JSON;
import LazyBurro.Helper.XML;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class ProcessRequest {
    private static String USER_AGENT = "Mozilla/5.0";

    private static String formatResponse(String contentType, String response) {
        System.out.println(contentType);
        try {
            switch (contentType) {
                case "text/html; charset=UTF-8":
                    response = XML.prettyPrint(response, 4);
                    break;
                case "application/json; charset=utf-8":
                    response = JSON.prettyPrint(response);
                    break;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return response;
    }

    // HTTP GET request
    public static String sendGet(String method, String url) throws Exception {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod(method);

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending " + method + " request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        Map<String, List<String>> hds = con.getHeaderFields();
        System.out.println(hds.get("Content-Type"));

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return formatResponse(hds.get("Content-Type").get(0), response.toString());

    }
}
