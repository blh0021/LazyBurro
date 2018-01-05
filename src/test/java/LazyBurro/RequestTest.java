package LazyBurro;

import org.junit.Test;

import static org.junit.Assert.*;

public class RequestTest {

    @Test
    public void makeRequestCall() throws Exception {
        Request tmp = new Request("GET", "http://example.com", "{}");
        tmp.makeRequestCall();
        assertTrue(tmp.getResponse().contains("Example Domain"));
    }

    @Test
    public void makeRequestCallBlankHeaders() throws Exception {
        Request tmp = new Request("GET", "http://example.com", "");
        tmp.makeRequestCall();
        assertTrue(tmp.getResponse().contains("Example Domain"));
    }

}