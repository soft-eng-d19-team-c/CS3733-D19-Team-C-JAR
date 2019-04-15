package base;

import api.APIMain;
import javafx.application.Application;
import javafx.stage.Stage;
import model.User;

/**
 * The Main class of the application that stores the User, Facade, and Database objects.
 * This class also spawns the FXML application.
 * @author Ryan LaMarche
 */
public class Main extends Application {
    public static Facade screenController;
    public static Database database;
    public static User user;
    public static ApplicationInformation info;
    public static void main(String[] args){
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        APIMain APIMain = new APIMain();
        APIMain.run(0,0,900,900,null,null,null);
    }
}
