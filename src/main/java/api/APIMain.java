package api;

/* Doctors can request a prescription by providing a patients id and description of the prescription like we already have
 There should be a database with all of the available patients with there IDs, current room, name, and age.

 When a nurse is about to apply the drug to the patient. They will have to check confirm the patients ID into the application.
 Then they will be given the patients name and will be given instructions on how to apply the drug.
 */
// in the gradle add "mainClassName = "base.Main" for the main class or what ever it needs to be

import base.Database;
import base.EnumScreenType;
import base.Facade;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;


public class APIMain {
    private int windowWidth;
    private int windowLegth;
    private int xcoord;
    private int ycoord;
    private String cssPath;
    public static Facade screenController;
    Stage primaryStage;
    public static Database database;

    @SuppressWarnings("Duplicates")

    public void run(int xcoord, int ycoord, int windowWidth, int windowLength, String cssPath, String destNodeID, String originNodeID) throws ServiceException {
        if (xcoord < 0)
            throw new ServiceException("xcoord must be greater than 0");
        if (ycoord < 0)
            throw new ServiceException("ycoord must be greater than 0");
        if (windowWidth < 0)
            throw new ServiceException("windowWidth must be greater than 0");
        if (windowLength < 0)
            throw new ServiceException("windowLength must be greater than 0");
        if (windowWidth < xcoord)
            throw new ServiceException("windowWidth must be greater than xcoord");
        if (windowLength < ycoord)
            throw new ServiceException("windowLength must be greater than ycoord");
        if (cssPath != null && !(new File(cssPath).isFile()))
            throw new ServiceException("Invalid path");

        this.windowLegth = windowLength;
        this.windowWidth = windowWidth;
        this.xcoord = xcoord;
        this.ycoord = ycoord;

        if (cssPath != null){
            this.cssPath = cssPath;
        }

        primaryStage = new Stage();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(EnumScreenType.APIMain.getPath()));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, windowWidth, windowLength);
            primaryStage.setScene(scene);
            screenController = new Facade(primaryStage.getScene());
            primaryStage.setX(xcoord);
            primaryStage.setY(ycoord);
            primaryStage.setTitle("Prescription Services");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}


