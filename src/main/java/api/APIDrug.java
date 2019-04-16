package api;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class APIDrug {

    private String title;
    private String description;

    public APIDrug(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

//    public static ObservableList<APIDrug> getAllDrugs(){
//        ObservableList<APIDrug> drugs = FXCollections.observableArrayList();
//
//        String sqlStmt = "SELECT * FROM DRUGS";
//        try {
//            Statement stmt = APIMain.connection.createStatement();
//            ResultSet rs = stmt.executeQuery(sqlStmt);
//            while (rs.next()) {
//                String title =  rs.getString("Title");
//                String description =  rs.getString("Description");
//                drugs.add(new APIDrug(title, description));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return drugs;
//    }

    //    public static int getID(String name){
//        String sqlStmt = "SELECT * FROM DRUGS WHERE TITLE = '" + name + "'";
//        try {
//            Statement stmt = APIMain.connection.createStatement();
//            ResultSet rs = stmt.executeQuery(sqlStmt);
//            if (rs.next()) {
//                return rs.getInt("ID");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return -1;
//    }
}
