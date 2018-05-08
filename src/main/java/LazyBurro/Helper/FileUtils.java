package LazyBurro.Helper;

import LazyBurro.Config.ConfigFile;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtils {
    public String openFile(String filename) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filename)), StandardCharsets.UTF_8);
    }

    public JSONObject openJsonFile(String filename) throws IOException{
        String jsonInString = openFile(filename);
        return new JSONObject(jsonInString);
    }

    public void saveFile(File file, String cfg) throws IOException {
        //Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
        //gson.toJson(cfg, new FileWriter(file));
        //String data = gson.toJson(cfg);
        PrintWriter writer = new PrintWriter(file.toString(), "UTF-8");
        writer.println(cfg);
        writer.close();
    }
}
