package sample;

import javafx.application.Application;
import javafx.stage.Stage;

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
  }

  private Map<TagEntity, String> getData() {
    List<TagEntity> selectors = Arrays.asList(TagEntity.values());
    Map<TagEntity, String> data = new Extractor().extract(selectors);
    data.put(TagEntity.Word, data.get(TagEntity.Word).toUpperCase());
    return data;
  }
}