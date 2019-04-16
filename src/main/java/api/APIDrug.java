package api;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class APIDrug {

    public static int getID(String name){
        String sqlStmt = "SELECT * FROM DRUGS WHERE TITLE = '" + name + "'";
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
