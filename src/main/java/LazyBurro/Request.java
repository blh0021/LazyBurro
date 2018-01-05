package LazyBurro;

import LazyBurro.Helper.JSON;
import LazyBurro.Helper.XML;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class Request {
    private String method;
    private String url;
    private String response;
    private int responseCode;
    private String contentType;
    private Map<String, List<String>> headers;
    private Map<String, String> requestHeaders;

    private static String USER_AGENT = "LazyBurro/0.0.1";

    public Request() {}

    public Request(String method, String url, String requestHeaders) {
        this.method = method;
        this.url = url;
        this.requestHeaders = JSON.parseSimpleJson(requestHeaders);
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getResponse() {
        return this.response;
    }

    public String getContentType() {
        return this.contentType;
    }

    public String getHeaders() {
        return this.headers.toString();
    }

    public String formatResponse() {
        System.out.println(this.contentType);
        try {
            if (this.contentType.contains("text/html")) {
                return XML.prettyPrint(this.response, 4);
            } else if (this.contentType.contains("application/json")) {
                return JSON.prettyPrint(this.response);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return this.response;
    }

    public void makeRequestCall() throws Exception {
        URL obj = new URL(this.url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod(this.method);

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);
        for (Map.Entry<String, String> rh : this.requestHeaders.entrySet()) {
            con.setRequestProperty(rh.getKey(), rh.getValue());
        }

        this.responseCode = con.getResponseCode();
        //System.out.println("\nSending " + this.method + " request to URL : " + this.url);
        //System.out.println("Response Code : " + this.responseCode);
        this.headers = con.getHeaderFields();
        this.contentType = this.headers.get("Content-Type").get(0).toString();

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        this.response = response.toString();
    }
}
