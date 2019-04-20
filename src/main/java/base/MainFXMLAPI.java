package base;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The Main class for the JavaFX application. This class spawns the JavaFX application and creates
 * the FacadeAPI and IdleMonitor, as well as starting up the home screen of the application.
 * @author Ryan LaMarche
 */
public class MainFXMLAPI extends Application {
    /**
     * Starting routine for base.MainFXMLAPI view.
     * @author Ryan LaMarche
     * @param s the Stage to start on.
     */
    @Override
    public void start(Stage s) {
        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource(EnumScreenTypeAPI.APIMain.getPath())));
            s.setScene(scene);
            PrescriptionRequestAPI.screenController = new FacadeAPI(s.getScene());
            s.setTitle("BWH Navigation Kiosk");
            s.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The main method / entry point for launching the base.MainFXMLAPI instance.
     * @author Ryan LaMarche
     * @param args arguments provided to main method.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
