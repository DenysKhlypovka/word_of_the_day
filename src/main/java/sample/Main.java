package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main extends Application {

  public static void main(String[] args) {
    launch();
  }

  @Override
  public void start(Stage stage) {

    Map<TagEntity, String> wordOfTheDayData = getData();
    LayoutUI layoutUI = new LayoutUI();
    layoutUI.drawStage(stage, wordOfTheDayData, this);
    stage.getIcons().add(new javafx.scene.image.Image(new ResourceManager().getAppIcon()));
    setCheckAndUpdateTimeline(stage);

    new Tray().setupTray();
  }

  private void setCheckAndUpdateTimeline(Stage stage) {
    Timeline updateAlwaysOnTopTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> checkAndUpdateAlwaysOnTop(stage)));
    updateAlwaysOnTopTimeline.setCycleCount(Timeline.INDEFINITE);
    updateAlwaysOnTopTimeline.play();
  }

  public void checkAndUpdateAlwaysOnTop(Stage stage) {
    try {
      stage.setAlwaysOnTop(new PropertiesManager().getAlwaysOnTopProperty());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private Map<TagEntity, String> getData() {
    List<TagEntity> selectors = Arrays.asList(TagEntity.values());
    Map<TagEntity, String> data = new Extractor().extract(selectors);
    data.put(TagEntity.Word, data.get(TagEntity.Word).toUpperCase());
    return data;
  }
}