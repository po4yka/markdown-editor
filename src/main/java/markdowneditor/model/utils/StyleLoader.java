package markdowneditor.model.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public abstract class StyleLoader {
    public static Map<String, String> getAllStyles() throws IOException {
        var map = new HashMap<String, String>();

        URL darkStylePath = StyleLoader.class.getResource("/stylesToLoad/Dark.css");
        assert darkStylePath != null;
        Scanner scannerDark = new Scanner(darkStylePath.openStream());
        StringBuilder textDark = new StringBuilder();
        while (scannerDark.hasNext()) {
            textDark.append(scannerDark.next());
        }
        map.put("Dark", textDark.toString());

        URL defaultStylePath = StyleLoader.class.getResource("/stylesToLoad/Default.css");
        assert defaultStylePath != null;
        Scanner scannerDefault = new Scanner(defaultStylePath.openStream());
        StringBuilder textDefault = new StringBuilder();
        while (scannerDefault.hasNext()) {
            textDefault.append(scannerDefault.next());
        }
        map.put("Default", textDefault.toString());

        return map;
    }
}
