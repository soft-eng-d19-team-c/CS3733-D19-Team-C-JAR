package api;

/* Doctors can request a prescription by providing a PATIENTSs id and description of the prescription like we already have
 There should be a database with all of the available PATIENTSs with there IDs, current room, name, and age.

 When a nurse is about to apply the drug to the PATIENTS. They will have to check confirm the PATIENTSs ID into the application.
 Then they will be given the PATIENTSs name and will be given instructions on how to apply the drug.
 */
// in the gradle add "mainClassName = "base.Main" for the main class or what ever it needs to be

import base.Database;
import base.EnumScreenType;
import base.Facade;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.sql.*;


public class APIMain {
    private int windowWidth;
    private int windowLegth;
    private int xcoord;
    private int ycoord;
    private String cssPath;
    public static Facade screenController;
    Stage primaryStage;
    public static Connection connection;
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
//        URL path = ClassLoader.getSystemResource(cssPath);
//        try {
//        String currDir = System.getProperty("user.dir");
//        System.out.println(currDir);
//            if (cssPath != null && !((new File(currDir + cssPath)).exists()))
//                throw new ServiceException("Invalid path");
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }

        this.windowLegth = windowLength;
        this.windowWidth = windowWidth;
        this.xcoord = xcoord;
        this.ycoord = ycoord;

        if (cssPath == null){
            cssPath = "/views/default.css";
        }
        connectToDatabase();
        buildDatabase();

        System.out.println(APIPrescription.addPrescription("Vicodin", "Rivers Cuomo"));

        primaryStage = new Stage();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(EnumScreenType.APIMain.getPath()));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, windowWidth, windowLength);
            scene.getStylesheets().add(getClass().getResource(cssPath).toExternalForm());
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
        // drugs
        String drugs1 = "create table DRUGS (title varchar(255) not NULL, description varchar(1000))";
        String drugs2 = "create unique index DRUGS_TITLE_uindex on DRUGS (TITLE)";
        String drugs3 = "alter table DRUGS add constraint drugs_title_pk primary key(TITLE)";


        String PATIENTS1 = "create table PATIENTS (NAME varchar(255) not null )";
        String PATIENTS2 = "create unique index PATIENTS_NAME_uindex on PATIENTS (NAME)";
        String PATIENTS3 = "alter table PATIENTS add constraint PATIENTS_NAME_pk primary key(NAME)";


//        String Pharmers1 = "create table Pharmers (ID int generated always as identity, name varchar(255), title varchar(255))";
//        String Pharmers2 = "create unique index Pharmers_ID_uindex on Pharmers (ID)";
//        String Pharmers3 = "alter table Pharmers add constraint Pharmers_id_pk primary key(ID)";
//        String Pharmers4 = "create unique index Pharmers_NAME_uindex on Pharmers (NAME)";

        String PRESCRITPIONS1 = "create table PRESCRITPIONS (ID int generated always as identity, DRUGID varchar(255) constraint drugs_title_fk references drugs (title), PATIENTSID varchar(255) constraint PATIENTS_NAME_fk references PATIENTS (NAME))";
        String PRESCRITPIONS2 = "create unique index PRESCRITPIONS_ID_uindex on PRESCRITPIONS (ID)";
        String PRESCRITPIONS3 = "alter table PRESCRITPIONS add constraint PRESCRITPIONS_id_pk primary key(ID)";
        try {
            Statement tableStmt = connection.createStatement();
            // create nodes and edges first
            tableStmt.executeUpdate(drugs1);
            tableStmt.executeUpdate(drugs2);
            tableStmt.executeUpdate(drugs3);
//            tableStmt.executeUpdate(drugs4);


            tableStmt.executeUpdate(PATIENTS1);
            tableStmt.executeUpdate(PATIENTS2);
            tableStmt.executeUpdate(PATIENTS3);
//            tableStmt.executeUpdate(PATIENTS4);

//            tableStmt.executeUpdate(Pharmers1);
//            tableStmt.executeUpdate(Pharmers2);
//            tableStmt.executeUpdate(Pharmers3);
//            tableStmt.executeUpdate(Pharmers4);

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

        System.out.println("Attempting to import patients...");
        URL csvFile = getClass().getResource("/data/patients.csv");
        BufferedReader br = null;
        String line;
        String cvsSplitBy = ",";
        try {
            br = new BufferedReader(new InputStreamReader(csvFile.openStream()));
            br.readLine(); // throw away header
            while ((line = br.readLine()) != null) {
                String[] nodeData = line.split(cvsSplitBy); // split by comma
                // get fields
                String name = nodeData[0];
                // prepare the insert sql statement with room to insert variables
                PreparedStatement ps = null;
                String sqlCmd = "insert into Patients (Name) values (?)";
                try {
                    ps = APIMain.connection.prepareStatement(sqlCmd);
                    ps.setString(1, name);
                    ps.execute();
                } catch (SQLException e) {
                    if (e.getSQLState().equals("23505")) { // duplicate key, update instead of insert
                    } else {
                        e.printStackTrace();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        System.out.println("Attempting to import drugs...");
        csvFile = getClass().getResource("/data/drugs.csv");
        br = null;
        try {
            br = new BufferedReader(new InputStreamReader(csvFile.openStream()));
            br.readLine(); // throw away header
            while ((line = br.readLine()) != null) {
                String[] nodeData = line.split(cvsSplitBy); // split by comma
                // get fields
                String title = nodeData[0];
                String description = nodeData[1];
                // prepare the insert sql statement with room to insert variables
                PreparedStatement ps = null;
                String sqlCmd = "insert into drugs (TITLE, DESCRIPTION) values (?,?)";
                try {
                    ps = APIMain.connection.prepareStatement(sqlCmd);
                    ps.setString(1, title);
                    ps.setString(2, description);
                    ps.execute();
                } catch (SQLException e) {
                    if (e.getSQLState().equals("23505")) { // duplicate key, update instead of insert
                    } else {
                        e.printStackTrace();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

//        System.out.println("Attempting to import staff...");
//        csvFile = getClass().getResource("/data/pharmers.csv");
//        br = null;
//        try {
//            br = new BufferedReader(new InputStreamReader(csvFile.openStream()));
//            br.readLine(); // throw away header
//            while ((line = br.readLine()) != null) {
//                String[] nodeData = line.split(cvsSplitBy); // split by comma
//                // get fields
//                String name = nodeData[0];
//                String title = nodeData[1];
//                // prepare the insert sql statement with room to insert variables
//                PreparedStatement ps = null;
//                String sqlCmd = "insert into PHARMERS (NAME, TITLE) values (?,?)";
//                try {
//                    ps = APIMain.connection.prepareStatement(sqlCmd);
//                    ps.setString(1, name);
//                    ps.setString(2, title);
//                    ps.execute();
//                } catch (SQLException e) {
//                    if (e.getSQLState().equals("23505")) { // duplicate key, update instead of insert
//                    } else {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (br != null) {
//                try {
//                    br.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }


//        System.out.println(APIDrug.getID("Vicodin"));
//        System.out.println(APIPatient.getID("Rivers Cuomo"));
//        System.out.println(APIPharmer.getID("Buddy Holly"));
//
//
//       if(!APIPrescription.addPrescription(6, 1, 1)){
//           System.out.println("failed");
//       }
    }


    
    public boolean addDrug(String title, String description){
        boolean executed = false;

        String sqlCmd = "insert into DRUGS (TITLE, DESCRIPTION) values (?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sqlCmd);
            ps.setString(1, title);
            ps.setString(2, description);
            executed = ps.execute(); //returns a boolean
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

        return executed;
    }

    public boolean addPharmer(String name, String title){
        boolean executed = false;

        String sqlCmd = "insert into PHARMERS (NAME, TITLE) values (?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sqlCmd);
            ps.setString(1, name);
            ps.setString(2, title);
            executed = ps.execute(); //returns a boolean
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

        return executed;
    }

    public boolean addPrescription(int drugID, int patientID, int pharmerID){
        boolean executed = false;

        String sqlCmd = "insert into PRESCRITPIONS (DRUGID, PATIENTSID, PHARMERID) values (?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sqlCmd);
            ps.setInt(1, drugID);
            ps.setInt(2, patientID);
            ps.setInt(3, pharmerID);
            executed = ps.execute(); //returns a boolean
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

        return executed;
    }





}



