package markdowneditor.model.utils;

;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public abstract class StyleLoader {
    public static Map<String, String> getAllStyles() throws IOException {
        var map = new HashMap<String, String>();
        URL pathUrl = StyleLoader.class.getResource("/stylesToLoad");
        assert (pathUrl != null) : "pathUrl in StyleLoader is null";
        var folder = new File(pathUrl.getPath());
        var files = folder.listFiles();
        assert (files != null) : "files in StyleLoader is null";
        for (File file : files) {
            var text = new String(Files.readAllBytes(file.toPath()));
            map.put(file.getName().replaceAll(".css$", ""), text);
        }
        return map;
    }
}
