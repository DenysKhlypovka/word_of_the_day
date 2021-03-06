package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Map;

public class LayoutUI {

  private final double LAYOUT_SPACING = 15;
  private final double LAYOUT_PADDING = 10;

  private final double DEFAULT_SCENE_HEIGHT = 150;
  private final double DEFAULT_SCENE_WIDTH = 340;
  private final double STAGE_LOCATION_Y = 0;

  private final String STAGE_TITLE = "Word of the Day";
  private final String LAYOUT_STYLE = "-fx-background-color: c2e0e3;";

  public void drawStage(Stage stage, Map<TagEntity, String> wordOfTheDayData, Application app) {

    double sceneWidth = calculateSceneWidth(wordOfTheDayData.get(TagEntity.Word));
    double stageLocationX = Screen.getPrimary().getBounds().getWidth() - sceneWidth;
    Scene scene = new Scene(getHolder(wordOfTheDayData, app), sceneWidth, calculateSceneHeight(wordOfTheDayData.get(TagEntity.Description), sceneWidth));

    stage.initStyle(StageStyle.UNDECORATED);
    stage.setX(stageLocationX);
    stage.setY(STAGE_LOCATION_Y);
    stage.setTitle(STAGE_TITLE);
    stage.setScene(scene);
    stage.show();

    try {
      stage.setAlwaysOnTop(new PropertiesManager().getAlwaysOnTopProperty());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private VBox getHolder(Map<TagEntity, String> map, Application app) {

    Label wordOfTheDayLabel = new Label(map.get(TagEntity.Word));
    wordOfTheDayLabel.setFont(CustomFont.BASKERVILLE);

    Label descriptionLabel = new Label(map.get(TagEntity.Description));
    descriptionLabel.setFont(CustomFont.SERIF);
    descriptionLabel.setWrapText(true);

    Label moreInfoLabel = new Label("More info: ");

    String moreInfoLink = map.get(TagEntity.More_Info);
    Hyperlink hyperlink = new Hyperlink(moreInfoLink);
    hyperlink.setText(moreInfoLink);
    hyperlink.setOnAction(actionEvent -> app.getHostServices().showDocument(hyperlink.getText()));

    HBox moreInfoHBox = new HBox(moreInfoLabel, hyperlink);
    moreInfoHBox.setAlignment(Pos.CENTER_LEFT);

    VBox layout = new VBox(wordOfTheDayLabel, descriptionLabel, moreInfoHBox);
    layout.setSpacing(LAYOUT_SPACING);
    layout.setPadding(new Insets(LAYOUT_PADDING));
    layout.setStyle(LAYOUT_STYLE);

    return layout;
  }

  private double calculateSceneHeight(String descriptionStr, double sceneWidth) {
    double oneRowHeight = Util.getStringHeight(descriptionStr, CustomFont.SERIF);
    int numberOfRows = (int) (Util.getStringWidth(descriptionStr, CustomFont.SERIF) / (sceneWidth - LAYOUT_PADDING * 2));
    return DEFAULT_SCENE_HEIGHT + ((numberOfRows - 1) * oneRowHeight);
  }

  //TODO magic number 50
  private double calculateSceneWidth(String wordOfTheDayStr) {
    double wordOfTheDayStrWidth = Util.getStringWidth(wordOfTheDayStr, CustomFont.BASKERVILLE);
    return Math.max(wordOfTheDayStrWidth + LAYOUT_PADDING * 2 + 50, DEFAULT_SCENE_WIDTH);
  }
}
