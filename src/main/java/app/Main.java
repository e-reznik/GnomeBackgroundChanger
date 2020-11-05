package app;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Main {

    public static void main(String[] args) {
        Main m = new Main();

        if (args.length > 0) {
            try {
                String url = m.findUrl(args[0]);
                String urlImg = m.parse(url);
                Path path = m.download(urlImg);
                m.execScript(path);
            } catch (IOException | URISyntaxException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            throw new IllegalArgumentException("Source required. Try nasa.");
        }
    }

    /**
     * Map with different image sources.
     *
     * @param source
     * @return
     */
    private String findUrl(String source) {
        Map<String, String> map = new HashMap<>();
        map.put("nasa", "https://apod.nasa.gov/apod/astropix.html");

        String url = map.get(source);
        if (url != null) {
            return url;
        } else {
            throw new NullPointerException("The source " + source + " doesn't exist. Please try another one.");
        }
    }

    /**
     * Finds the image on that page. There is only one present. The image is
     * inside of an a-tag, so we need its parent. Take the value of its
     * href-attribute. Than is the URL of the picture.
     *
     * @param url
     * @return
     * @throws IOException
     */
    private String parse(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Element elementImg = doc.getElementsByTag("img").first();
        String urlImg = elementImg.parent().absUrl("href");

        System.out.println("Url of the image: " + url);
        return urlImg;
    }

    /**
     * Downloads and saves an image locally.
     *
     * @param url
     * @return
     * @throws IOException
     */
    private Path download(String url) throws IOException {
        try (InputStream in = new URL(url).openStream()) {
            Path path = Paths.get("image.jpg");
            Files.copy(in, path, StandardCopyOption.REPLACE_EXISTING);

            Path imgAbs = path.toAbsolutePath();

            System.out.println("Local path of the image: " + imgAbs);
            return imgAbs;
        }
    }

    /**
     * Build and executes the script.
     *
     * @param path
     * @throws IOException
     * @throws URISyntaxException
     */
    private void execScript(Path path) throws IOException, URISyntaxException {
        String content = "gsettings set org.gnome.desktop.background picture-uri " + path;
        Runtime.getRuntime().exec(content);
    }
}
