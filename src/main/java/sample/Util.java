package sample;

import javafx.geometry.Bounds;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Util {
  public static double getStringWidth(String string, Font font) {
    return getStringBounds(string, font).getWidth();
  }

  public static double getStringHeight(String string, Font font) {
    return getStringBounds(string, font).getHeight();
  }

  private static Bounds getStringBounds(String string, Font font) {
    Text sample = new Text(string);
    sample.setFont(font);
    return sample.getBoundsInLocal();
  }
}
