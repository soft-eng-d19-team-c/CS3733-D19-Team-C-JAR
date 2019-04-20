package model;

import base.PrescriptionRequestAPI;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static java.lang.Integer.parseInt;

public class APIPatient {
    public static boolean addPatient(String ID, String name, int age, String sex) {
        boolean executed = false;

        String sqlCmd = "insert into PATIENTS (ID, NAME, AGE, SEX) values (?,?,?,?)";
        try {
            PreparedStatement ps = PrescriptionRequestAPI.connection.prepareStatement(sqlCmd);
            ps.setString(1, ID);
            ps.setString(2, name);
            ps.setInt(3, age);
            ps.setString(4, sex);
            executed = ps.execute(); //returns a boolean
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return executed;
    }

    public static boolean exists(String ID){
        String sqlStmt = "SELECT * FROM PATIENTS WHERE ID = '" + ID + "'";
        try {
            Statement stmt = PrescriptionRequestAPI.connection.createStatement();
            ResultSet rs = stmt.executeQuery(sqlStmt);
            if (rs.next()) {
               return ID.equals(rs.getString("ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}
