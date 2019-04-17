package api;

import base.APIMain;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class APIPrescription {
    public static boolean addPrescription(String drugTitle, String instructions, String patientID){
        boolean executed = false;
        String sqlCmd = "insert into PRESCRITPIONS (DRUGID, DRUGDESCRIPTION, PATIENTSID ) values (?,?,?)";
        try {
            PreparedStatement ps = APIMain.connection.prepareStatement(sqlCmd);
            ps.setString(1, drugTitle);
            ps.setString(2, instructions);
            ps.setString(3, patientID);
            return ps.execute(); //returns a boolean
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return executed;
    }

    public static ObservableList<APIDrug> getAllDrugs(String name){
        ObservableList<APIDrug> drugs = FXCollections.observableArrayList();

        String sqlStmt;
        sqlStmt = "SELECT * FROM PRESCRITPIONS WHERE PATIENTSID = '" + name + "'";
        try {
            Statement stmt = APIMain.connection.createStatement();
            ResultSet rs = stmt.executeQuery(sqlStmt);
            while (rs.next()) {
                String title =  rs.getString("drugid");
                String description =  rs.getString("drugdescription");
                drugs.add(new APIDrug(title, description));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return drugs;
    }


}
