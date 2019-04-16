package api;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class APIPrescription {
    int drugID;
    int patientID;


    public APIPrescription(int drugID, int patientID) {
        this.drugID = drugID;
        this.patientID = patientID;
    }

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

}
