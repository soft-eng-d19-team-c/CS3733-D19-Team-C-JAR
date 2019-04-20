package base;

import model.APIPrescription;
import model.ServiceException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.sql.*;

public class PrescriptionRequestAPI extends Application {
    private int windowWidth;
    private int windowLegth;
    private int xcoord;
    private int ycoord;
    private String cssPath;
    public static FacadeAPI screenController;
    private Stage primaryStage;
    public static Connection connection;

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

        this.windowLegth = windowLength;
        this.windowWidth = windowWidth;
        this.xcoord = xcoord;
        this.ycoord = ycoord;

        if (cssPath == null){
            cssPath = "/views/default.css";
        }
        connectToDatabase();
        buildDatabase();



        primaryStage = new Stage();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(EnumScreenTypeAPI.APIMain.getPath()));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, windowWidth, windowLength);
            scene.getStylesheets().add(getClass().getResource(cssPath).toExternalForm());
            primaryStage.setScene(scene);
            screenController = new FacadeAPI(primaryStage.getScene());
            primaryStage.setX(xcoord);
            primaryStage.setY(ycoord);
            primaryStage.setTitle("Prescription Services");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    private void connectToDatabase(){
        System.out.println("Attempting to connect to the embedded database...");
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (ClassNotFoundException e) {
            System.err.println("Java DB Driver not found");
            e.printStackTrace();
            return;
        }
        System.out.println("Driver registered");
        try {
            this.connection = DriverManager.getConnection("jdbc:derby:APIDatabase;create=true");
        } catch (SQLException e) {
            System.err.println("Connection failed");
            e.printStackTrace();
            return;
        }
        System.out.println("Successfully connected to database");
    }

    @SuppressWarnings("Dupilcates")
    private void buildDatabase(){
        String PATIENTS1 = "create table PATIENTS (ID varchar(255) not null, NAME varchar(255), AGE int, SEX VARCHAR(255))";
        String PATIENTS2 = "create unique index PATIENTS_NAME_uindex on PATIENTS (ID)";
        String PATIENTS3 = "alter table PATIENTS add constraint PATIENTS_ID_pk primary key(ID)";

        String PRESCRITPIONS1 = "create table PRESCRITPIONS (ID int generated always as identity, DRUGID varchar(255), DRUGDESCRIPTION varchar(1000), PATIENTSID varchar(255) constraint PATIENTS_ID_fk references PATIENTS (ID), RESOLVED boolean)";
        String PRESCRITPIONS2 = "create unique index PRESCRITPIONS_ID_uindex on PRESCRITPIONS (ID)";
        String PRESCRITPIONS3 = "alter table PRESCRITPIONS add constraint PRESCRITPIONS_id_pk primary key(ID)";
        try {
            Statement tableStmt = connection.createStatement();

            tableStmt.executeUpdate(PATIENTS1);
            tableStmt.executeUpdate(PATIENTS2);
            tableStmt.executeUpdate(PATIENTS3);

            tableStmt.executeUpdate(PRESCRITPIONS1);
            tableStmt.executeUpdate(PRESCRITPIONS2);
            tableStmt.executeUpdate(PRESCRITPIONS3);

        } catch (SQLException e) {
            if (e.getSQLState().equals("X0Y32")) {
                // table exists
                System.out.println("Database schema already exists.");
            } else {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args){
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        PrescriptionRequestAPI prescriptionRequestAPI = new PrescriptionRequestAPI();
        prescriptionRequestAPI.run(0,0,1000,1000,null,null,null);
    }

    public Connection getConnection(){
        return connection;
    }


}



