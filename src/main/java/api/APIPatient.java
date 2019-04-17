package api;

import base.APIMain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static java.lang.Integer.parseInt;

public class APIPatient {
    public boolean addPatient(String name) {
        boolean executed = false;

        String sqlCmd = "insert into PATIENTS (NAME) values (?)";
        try {
            PreparedStatement ps = APIMain.connection.prepareStatement(sqlCmd);
            ps.setString(1, name);
            executed = ps.execute(); //returns a boolean
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return executed;
    }

    public static boolean exists(String name){
        String sqlStmt = "SELECT * FROM PATIENTS WHERE NAME = '" + name + "'";
        try {
            Statement stmt = APIMain.connection.createStatement();
            ResultSet rs = stmt.executeQuery(sqlStmt);
            if (rs.next()) {
               return name.equals(rs.getString("Name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
