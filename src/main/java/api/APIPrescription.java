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

    public static boolean addPrescription(int drugID, int patientID, int pharmerID){
        boolean executed = false;

        String sqlCmd = "insert into PRESCRITPIONS (drugid, patientsid, pharmerid) values (?,?,?)";
        try {
            PreparedStatement ps = APIMain.connection.prepareStatement(sqlCmd);
            ps.setInt(1, drugID);
            ps.setInt(2, patientID);
            ps.setInt(3, pharmerID);
            executed = ps.execute(); //returns a boolean
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return executed;
    }

    public static boolean addPrescription(String drugIDstr, String patientIDstr, String pharmerIDstr){
        boolean executed = false;

        int drugID = APIDrug.getID(drugIDstr);
        int patientID = APIDrug.getID(patientIDstr);
        int pharmerID = APIDrug.getID(patientIDstr);

        if (drugID == -1 || patientID == -1 || patientID == -1){
            return false;
        }


        String sqlCmd = "insert into PRESCRITPIONS (drugid, patientsid, pharmerid) values (?,?,?)";
        try {
            PreparedStatement ps = APIMain.connection.prepareStatement(sqlCmd);
            ps.setInt(1, drugID);
            ps.setInt(2, patientID);
            ps.setInt(3, pharmerID);
            executed = ps.execute(); //returns a boolean
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return executed;
    }





}
