package main.java.unit_converter.util;

import java.awt.*;
import java.net.URI;

public class WebHelper {
    public static void openWebpage(String url) {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(new URI(url));
            } catch (Exception ignored) {}
        }
    }
}
