package api;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class APIPrescription {
    int drugID;
    int patientID;
    int pharmerID;


    public APIPrescription(int drugID, int patientID, int pharmerID) {
        this.drugID = drugID;
        this.patientID = patientID;
        this.pharmerID = pharmerID;
    }

    public static boolean addPrescription(String drugID, String patientID){
        boolean executed = false;
        String sqlCmd = "insert into PRESCRITPIONS (drugid, patientsid) values (?,?)";
        try {
            PreparedStatement ps = APIMain.connection.prepareStatement(sqlCmd);
            ps.setString(1, drugID);
            ps.setString(2, patientID);
            return ps.execute(); //returns a boolean
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return executed;
    }

}
