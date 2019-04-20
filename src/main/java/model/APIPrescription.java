package model;

import base.PrescriptionRequestAPI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class APIPrescription {

    private int ID;
    private String title;
    private String description;

    public APIPrescription(int ID, String title, String description) {
        this.ID = ID;
        this.title = title;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString(){
        return title;
    }





    public static boolean addPrescription(String drugTitle, String instructions, String patientID){
        boolean executed = false;
        String sqlCmd = "insert into PRESCRITPIONS (DRUGID, DRUGDESCRIPTION, PATIENTSID, RESOLVED) values (?,?,?,?)";
        try {
            PreparedStatement ps = PrescriptionRequestAPI.connection.prepareStatement(sqlCmd);
            ps.setString(1, drugTitle);
            ps.setString(2, instructions);
            ps.setString(3, patientID);
            ps.setBoolean(4, false);
            return ps.execute(); //returns a boolean
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return executed;
    }

    public static ObservableList<APIPrescription> getCurrentPrescriptions(String patientID){
        ObservableList<APIPrescription> drugs = FXCollections.observableArrayList();

        String sqlStmt;
        sqlStmt = "SELECT * FROM PRESCRITPIONS WHERE PATIENTSID = '" + patientID + "'";
        try {
            Statement stmt = PrescriptionRequestAPI.connection.createStatement();
            ResultSet rs = stmt.executeQuery(sqlStmt);
            while (rs.next()) {
                int ID = rs.getInt("ID");
                String title =  rs.getString("drugid");
                String description =  rs.getString("drugdescription");
                if (!rs.getBoolean("resolved")) {
                    drugs.add(new APIPrescription(ID, title, description));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return drugs;
    }

    public void resolve(){
        String sqlStmt = "UPDATE PRESCRITPIONS SET RESOLVED = TRUE WHERE ID = ?";
        try {
            PreparedStatement ps = PrescriptionRequestAPI.connection.prepareStatement(sqlStmt);
            ps.setInt(1, this.ID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
