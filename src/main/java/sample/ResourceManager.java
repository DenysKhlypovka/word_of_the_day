package sample;

import java.io.InputStream;
import java.net.URL;

public class ResourceManager {
  private static final String APP_ICON_PATH = "/icon.png";
  private static final String PROPERTIES_PATH = "/app.properties";

  private InputStream getResourceAsStream(String path) {
    return getClass().getResourceAsStream(path);
  }

  private URL getResourceAsURL(String path) {
    return getClass().getResource(path);
  }

  public URL getPropertiesURL() {
    return getResourceAsURL(PROPERTIES_PATH);
  }

  public InputStream getAppIcon() {
    return getResourceAsStream(APP_ICON_PATH);
  }

  public InputStream getProperties() {
    return getResourceAsStream(PROPERTIES_PATH);
  }
}
