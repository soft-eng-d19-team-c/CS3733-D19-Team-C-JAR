package controller;

import base.Main;
import javafx.animation.Animation;
import javafx.animation.FillTransition;
import javafx.animation.StrokeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import model.DataTable;
import model.Edge;
import model.Node;

import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.ResourceBundle;

public class Map extends Controller implements Initializable {
    @FXML
    private AnchorPane bigPane;
    @FXML
    private ImageView mapImg;
    @FXML
    private Pane imInPane;
    @FXML
    private ToggleButton dancePartyBtn;

    private DataTable dt;

    private String floorPlan;

    private LinkedList<Node> nodeList;

    private HashMap<String, Edge> edgeList;

    @Override
    public void init(URL location, ResourceBundle resources) {
        initialize(location, resources);
    }

    public void backBtnClick(ActionEvent e) {
        Main.screenController.setScreen(EnumScreenType.NODETABLE);
    }

    public void danceParty(ActionEvent event) {
        imInPane.getChildren().remove(1, imInPane.getChildren().size());
        if (dancePartyBtn.isSelected()) {
            Random rand = new Random();
            double mapX = mapImg.getLayoutX();
            double mapY = mapImg.getLayoutY();
            ColorAdjust blackout = new ColorAdjust();
            blackout.setBrightness(-0.4);
            mapImg.setEffect(blackout);
            Color c = new Color(0,0,0,1.0);
            bigPane.setBackground(new Background(new BackgroundFill(c, null, null)));
            dancePartyBtn.setTextFill(new Color(1, 1, 1, 1.0));
            for (Edge e : edgeList.values()){
                Node startNode = dt.getDataById(e.getStartNode());
                Node endNode = dt.getDataById(e.getEndNode());
                if (startNode != null && endNode != null && startNode.getFloor().equals(endNode.getFloor())){
                    Line line = new Line();
                    line.setStartX(mapX + (startNode.getX() / 4));
                    line.setStartY(mapY + (startNode.getY()) / 4);
                    line.setEndX(mapX + (endNode.getX()) / 4);
                    line.setEndY(mapY + (endNode.getY()) / 4);
                    double r = rand.nextDouble();
                    double g = rand.nextDouble();
                    double b = rand.nextDouble();
                    Color color = new Color(r,g,b,1);
                    line.setStroke(color);
                    imInPane.getChildren().add(line);
                    r = rand.nextDouble();
                    g = rand.nextDouble();
                    b = rand.nextDouble();
                    Color color2 = new Color(r, g, b, 1.0);
                    StrokeTransition ft = new StrokeTransition(Duration.millis(400), line, color, color2);
                    ft.setCycleCount(Animation.INDEFINITE);
                    ft.setAutoReverse(true);
                    ft.play();
                }
            }
            for (Node n : nodeList) {
                Circle circle = new Circle();
                circle.setCenterX(mapX + n.getX() / 4.0);
                circle.setCenterY(mapY + n.getY() / 4.0);
                circle.setRadius(1.0 + (rand.nextDouble() * 5.0));
                double r = rand.nextDouble();
                double g = rand.nextDouble();
                double b = rand.nextDouble();
                Color color = new Color(r, g, b, 1.0);
                imInPane.getChildren().add(circle);
                r = rand.nextDouble();
                g = rand.nextDouble();
                b = rand.nextDouble();
                Color color2 = new Color(r, g, b, 1.0);
                FillTransition ft = new FillTransition(Duration.millis(250), circle, color, color2);
                ft.setCycleCount(Animation.INDEFINITE);
                ft.setAutoReverse(true);
                ft.play();
            }
        } else {
            drawNodes();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mapImg.setImage(new Image(String.valueOf(getClass().getResource("/img/"+ Main.screenController.getData("floor")+"_NoIcons.png"))));
        Platform.runLater(() -> {
            dt = new DataTable();
            nodeList = dt.getProjectCNodesByFloor((String) Main.screenController.getData("floor"));
            edgeList = dt.getProjectCEdges(nodeList);
            dancePartyBtn.setSelected(false);
            drawNodes();
        });
    }

    private void drawNodes() {
        Color c = new Color(1,1,1,1.0);
        bigPane.setBackground(new Background(new BackgroundFill(c, null, null)));
        dancePartyBtn.setTextFill(new Color(0, 0, 0, 1.0));

        ColorAdjust reset = new ColorAdjust();
        reset.setBrightness(0);
        mapImg.setEffect(reset);
        imInPane.getChildren().remove(1, imInPane.getChildren().size());
        double mapX = mapImg.getLayoutX();
        double mapY = mapImg.getLayoutY();
        for (Node n : nodeList){
            Circle circle = new Circle();
            circle.setCenterX(mapX + n.getX()/4.0);
            circle.setCenterY(mapY + n.getY()/4.0);
            circle.setRadius(3.0);
            imInPane.getChildren().add(circle);
        }

        for (Edge e : edgeList.values()){
            Node startNode = dt.getDataById(e.getStartNode());
            Node endNode = dt.getDataById(e.getEndNode());

            if (startNode != null && endNode != null && startNode.getFloor().equals(endNode.getFloor())){

                Line line = new Line();

                line.setStartX(mapX + (startNode.getX() / 4));
                line.setStartY(mapY + (startNode.getY()) / 4);
                line.setEndX(mapX + (endNode.getX()) / 4);
                line.setEndY(mapY + (endNode.getY()) / 4);

                line.setStroke(new Color(0,0,0,1));

                imInPane.getChildren().add(line);
            }
        }
    }

}