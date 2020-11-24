package app;

import java.util.Map;
import static java.util.Map.entry;

public interface iSources {

    public static final Map<String, String> sources = Map.ofEntries(
            entry("nasa", "https://apod.nasa.gov/apod/astropix.html"),
            entry("natgeo", "https://www.nationalgeographic.com/photography/photo-of-the-day/")
    );

}
