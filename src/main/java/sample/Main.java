package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Main extends Application {
  private final double SCENE_WIDTH = 340;
  private final double STAGE_LOCATION_X = Screen.getPrimary().getBounds().getWidth() - SCENE_WIDTH;
  private final double STAGE_LOCATION_Y = 0;

  private final String STAGE_TITLE = "Word of the Day";

  @Override
  public void start(Stage stage) {

    Scene scene = new Scene(getHolder(getData()), SCENE_WIDTH, 120);

    stage.initStyle(StageStyle.UNDECORATED);
    stage.setX(STAGE_LOCATION_X);
    stage.setY(STAGE_LOCATION_Y);
    stage.setTitle(STAGE_TITLE);
    stage.setScene(scene);
    stage.show();
  }

  private VBox getHolder(Map<TagEntity, String> map) {

    Label word = new Label(map.get(TagEntity.Word).toUpperCase());
    word.setFont(CustomFont.ARIAL);

    Label description = new Label(map.get(TagEntity.Description));
    description.setFont(CustomFont.SERIF);

    Label moreInfoLabel = new Label("More info: ");

    String moreInfoLink = map.get(TagEntity.More_Info);
    Hyperlink hyperlink = new Hyperlink(moreInfoLink);
    hyperlink.setText(moreInfoLink);
    hyperlink.setOnAction(t -> getHostServices().showDocument(hyperlink.getText()));

    HBox hBox = new HBox(moreInfoLabel, hyperlink);
    VBox holder = new VBox(word, description, hBox);
    holder.setSpacing(15);
    holder.setPadding(new Insets(0, 10, 10, 10));
    holder.setAlignment(Pos.BASELINE_LEFT);
    holder.setStyle("-fx-background-color: c2e0e3;");

    return holder;
  }

  public static void main(String[] args) {
    launch();
  }

  private Map<TagEntity, String> getData() {
    List<TagEntity> selectors = Arrays.asList(TagEntity.values());
    return new Extractor().extract(selectors);
  }
}