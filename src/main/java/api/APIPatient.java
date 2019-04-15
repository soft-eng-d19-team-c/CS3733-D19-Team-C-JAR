package api;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
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

    public static int getID(String name){
        String sqlStmt = "SELECT * FROM PATIENTS WHERE NAME = '" + name + "'";
        try {
            Statement stmt = APIMain.connection.createStatement();
            ResultSet rs = stmt.executeQuery(sqlStmt);
            if (rs.next()) {
               return rs.getInt("ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
