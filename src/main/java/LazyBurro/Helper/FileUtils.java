package LazyBurro.Helper;

import LazyBurro.Config.ConfigFile;
import com.google.gson.Gson;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtils {
    public String openFile(String filename) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filename)), StandardCharsets.UTF_8);
    }

    public ConfigFile processJson(String data) {
        Gson gson = new Gson();
        ConfigFile cfg = gson.fromJson(data, ConfigFile.class);
        return cfg;
    }

    public ConfigFile openJsonFile(String filename) throws IOException{
        String jsonInString = openFile(filename);
        return processJson(jsonInString);
    }
}
