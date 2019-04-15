package api;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class APIPatient {
    public boolean addPatient(String name){
        boolean executed = false;

        String sqlCmd = "insert into PATIENTS (NAME) values (?)";
        try {
            PreparedStatement ps = APIMain.connection.prepareStatement(sqlCmd);
            ps.setString(1, name);
            executed = ps.execute(); //returns a boolean
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

        return executed;
    }
}
