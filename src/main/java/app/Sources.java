package app;

import java.util.Map;
import static java.util.Map.entry;

public interface Sources {

    public static final Map<String, String> SOURCESMAP = Map.ofEntries(
            entry("nasa", "https://apod.nasa.gov/apod/astropix.html"),
            entry("natgeo", "https://www.nationalgeographic.com/photography/photo-of-the-day/")
    );

}
