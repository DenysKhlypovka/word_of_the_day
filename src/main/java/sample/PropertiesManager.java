package sample;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertiesManager {

  public boolean getAlwaysOnTopProperty() throws IOException {
    return Boolean.parseBoolean(getProperty(Property.isAlwaysOnTop));
  }

  private String getProperty(Property property) throws IOException {
    Properties appProps = new Properties();
    appProps.load(new ResourceManager().getProperties());
    return appProps.getProperty(property.name());
  }

  public void updateProperty(Property property, Object value) throws Exception {
    Properties appProps = new Properties();
    appProps.load(new ResourceManager().getProperties());
    appProps.setProperty(property.name(), value.toString());
    appProps.store(new FileWriter(Paths.get(new ResourceManager().getPropertiesURL().toURI()).toFile()), null);
  }

  public enum Property {
    isAlwaysOnTop
  }
}
