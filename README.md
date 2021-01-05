<p align="center">
        <img src="https://github.com/e-reznik/GnomeBackgroundChanger/workflows/Java%20CI%20with%20Maven/badge.svg" />
        <img src="https://img.shields.io/github/commit-activity/y/e-reznik/GnomeBackgroundChanger" />
        <img src="https://img.shields.io/github/last-commit/e-reznik/GnomeBackgroundChanger" />
</p>



# Description
Extracts the picture of the day from several sources and sets it as your Gnome background.

A simple example Gui can be found here: [GnomeBackgroundChanger-Gui](https://github.com/e-reznik/GnomeBackgroundChanger-Gui)

In its first alpha release you can download a Jar file and test it directly on your Gnome system: [GnomeBackgroundChanger-GUI v0.1-alpha](https://github.com/e-reznik/GnomeBackgroundChanger-Gui/releases/tag/v0.1-alpha)

## Sources

Currently supported sources are
- **nasa**: https://apod.nasa.gov/apod/astropix.html
- **natgeo**: https://www.nationalgeographic.com/photography/photo-of-the-day/

## Example

```java
App backgroundChanger = new App();
backgroundChanger.changeBackground("nasa");
```
