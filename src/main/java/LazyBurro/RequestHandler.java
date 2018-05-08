package LazyBurro;

import LazyBurro.Helper.JSON;
import LazyBurro.Helper.XML;
import org.json.JSONObject;
import rocks.painless.Request;

public class RequestHandler {
    private Request request;
    private String response;
    private long responseTime;
    private int responseCode;
    private String contentType;
    private JSONObject responseHeaders;

    private static String USER_AGENT = "LazyBurro/0.0.1";

    public RequestHandler(String requestData) {
       request = new Request(requestData);
    }

    public String getResponse() {
        return this.response;
    }

    public Long getResponseTime() {
        return this.responseTime;
    }

    public String getHeaders() {
        return JSON.prettyPrint(responseHeaders);
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
        response = request.process();
        responseHeaders = request.responseHeaders();
        contentType = responseHeaders.getString("Content-Type");
        responseTime = request.responseTime();
    }
}
