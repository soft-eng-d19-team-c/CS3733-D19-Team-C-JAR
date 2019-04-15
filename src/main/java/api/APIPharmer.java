package api;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class APIPharmer {
    public static int getID(String name){
        String sqlStmt = "SELECT * FROM PHARMERS WHERE NAME = '" + name + "'";
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
