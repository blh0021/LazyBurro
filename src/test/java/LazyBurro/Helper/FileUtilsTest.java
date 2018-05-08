package LazyBurro.Helper;

import LazyBurro.Config.ConfigFile;

import org.junit.Test;

import static org.junit.Assert.*;

public class FileUtilsTest {

    @Test
    public void processJson() {
        String test = "{\"header\": {\"Content-Type\": \"application/json\"},\"baseUrl\": \"http:\\/\\/www.harringtonweb.com\",\"path\": \"\",\"method\": \"GET\"}";
        FileUtils fu = new FileUtils();
        ConfigFile cfg = new ConfigFile();
        //cfg = fu.processJson(test);
        //assertEquals(cfg.baseUrl, "http://www.harringtonweb.com");
        //assertEquals(cfg.method, "GET");
    }
}