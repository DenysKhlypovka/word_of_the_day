package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main extends Application {
  private final double DEFAULT_SCENE_HEIGHT = 150;
  private final double SCENE_WIDTH = 340;

  private final double STAGE_LOCATION_X = Screen.getPrimary().getBounds().getWidth() - SCENE_WIDTH;
  private final double STAGE_LOCATION_Y = 0;

  private final double LAYOUT_SPACING = 15;
  private final double LAYOUT_PADDING = 10;

  private final String STAGE_TITLE = "Word of the Day";

  public static void main(String[] args) {
    launch();
  }

  @Override
  public void start(Stage stage) {

    Map<TagEntity, String> wordOfTheDayData = getData();
    Scene scene = new Scene(getHolder(wordOfTheDayData), SCENE_WIDTH, calculateSceneHeight(wordOfTheDayData));

    stage.initStyle(StageStyle.UNDECORATED);
    stage.setX(STAGE_LOCATION_X);
    stage.setY(STAGE_LOCATION_Y);
    stage.setTitle(STAGE_TITLE);
    stage.setScene(scene);
    stage.show();
  }

  private VBox getHolder(Map<TagEntity, String> map) {

    Label word = new Label(map.get(TagEntity.Word).toUpperCase());
    word.setFont(CustomFont.BASKERVILLE);

    Label description = new Label(map.get(TagEntity.Description));
    description.setFont(CustomFont.SERIF);
    description.setWrapText(true);

    Label moreInfoLabel = new Label("More info: ");

    String moreInfoLink = map.get(TagEntity.More_Info);
    Hyperlink hyperlink = new Hyperlink(moreInfoLink);
    hyperlink.setText(moreInfoLink);
    hyperlink.setOnAction(t -> getHostServices().showDocument(hyperlink.getText()));

    HBox hBox = new HBox(moreInfoLabel, hyperlink);
    VBox layout = new VBox(word, description, hBox);
    layout.setSpacing(LAYOUT_SPACING);
    layout.setPadding(new Insets(LAYOUT_PADDING));
    layout.setStyle("-fx-background-color: c2e0e3;");

    return layout;
  }

  private double calculateSceneHeight(Map<TagEntity, String> wordOfTheDayData) {
    double oneRowHeight = Util.getStringHeight(wordOfTheDayData.get(TagEntity.Description), CustomFont.SERIF);
    int numberOfRows = (int)(Util.getStringWidth(wordOfTheDayData.get(TagEntity.Description), CustomFont.SERIF) / (SCENE_WIDTH - LAYOUT_PADDING * 2));
    return DEFAULT_SCENE_HEIGHT + ((numberOfRows - 1) * oneRowHeight);
  }

  private Map<TagEntity, String> getData() {
    List<TagEntity> selectors = Arrays.asList(TagEntity.values());
    return new Extractor().extract(selectors);
  }
}