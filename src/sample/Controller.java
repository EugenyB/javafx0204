package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;

public class Controller {

    public final static int N = 10;
    @FXML private TextField xField;
    @FXML private TextField yField;

    @FXML private Pane pane;
    @FXML private Canvas canvas;

    @FXML
    public void initialize() {
        canvas.widthProperty().bind(pane.widthProperty());
        canvas.heightProperty().bind(pane.heightProperty());

        canvas.heightProperty().addListener((observable, oldValue, newValue) -> draw());
        canvas.widthProperty().addListener((observable, oldValue, newValue) -> draw());
    }

    public void draw() {
        GraphicsContext g2 = canvas.getGraphicsContext2D();
        g2.setFill(Color.WHITESMOKE);

        double height = canvas.getHeight();
        double width = canvas.getWidth();
        g2.fillRect(0,0, width, height);

        double minSize = Double.min(width,height)-10;
        double cellSize = minSize / N;
//        g2.rotate(45);
        g2.translate(15,5);
        for (int i = 0; i <= N; i++) {
            g2.strokeLine(0, i*cellSize, minSize, i*cellSize);
            g2.strokeLine(i*cellSize, 0, i*cellSize, minSize);
        }

//        g2.rotate(-45);
//        g2.setFill(Color.YELLOWGREEN);
//        g2.fillRect(10,10, 100, 100);
//        g2.setStroke(Color.rgb(50, 30, 125));
//        g2.strokeRect(10,10, 100, 100);
//        g2.strokeRect(0,0, canvas.getWidth(), canvas.getHeight());
        if (xField.getText()!=null && yField.getText()!=null) {
            try {
                int x = Integer.parseInt(xField.getText());
                int y = Integer.parseInt(yField.getText());
                g2.setFill(Color.RED);
                g2.fillRect(cellSize*x+2, cellSize*y+2, cellSize-4, cellSize-4);
            } catch (NumberFormatException ignored) {}
        }
        g2.translate(-15,-5);
    }
}
