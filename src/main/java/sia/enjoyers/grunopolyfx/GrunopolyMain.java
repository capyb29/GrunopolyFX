package sia.enjoyers.grunopolyfx;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.geometry.Pos;

public class GrunopolyMain extends Application {

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = createBoard();
        Scene scene = new Scene(grid, 800, 800);

        primaryStage.setTitle("Monopoly Board");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createBoard() {
        GridPane grid = new GridPane();
        int size = 7; // Grid size, including corners
        int paneSize = 70; // Preferred size for each pane

        // Style for the panes
        String style = "-fx-background-color: purple; -fx-border-color: black;";

        // Top row
        for (int i = 1; i < size - 1; i++) {
            Pane propertyTop = new Pane();
            propertyTop.setPrefSize(paneSize, paneSize);
            propertyTop.setStyle(style);
            grid.add(propertyTop, i, 0);
        }
        // Bottom row
        for (int i = 1; i < size - 1; i++) {
            Pane propertyBottom = new Pane();
            propertyBottom.setPrefSize(paneSize, paneSize);
            propertyBottom.setStyle(style);
            grid.add(propertyBottom, i, size - 1);
        }
        // Left column
        for (int i = 1; i < size - 1; i++) {
            Pane propertyLeft = new Pane();
            propertyLeft.setPrefSize(paneSize, paneSize);
            propertyLeft.setStyle(style);
            grid.add(propertyLeft, 0, i);
        }
        // Right column
        for (int i = 1; i < size - 1; i++) {
            Pane propertyRight = new Pane();
            //propertyRight.setPrefSize(paneSize, paneSize);
            propertyRight.setStyle(style);


            if (i == size - 2) {
                propertyRight.setPrefSize(5, 5);
            }

            grid.add(propertyRight, size - 1, i);
        }

        // Adjust grid alignment and gaps as needed
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        return grid;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
