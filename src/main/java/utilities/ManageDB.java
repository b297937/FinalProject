package utilities;

import java.sql.DriverManager;

public class ManageDB extends CommonOps {

    public static void openConnection(String dbUrl, String user, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(dbUrl, user, password);
            stmt = con.createStatement();
        } catch (Exception e) {
            System.out.println("Error Occurred while Connection to DB, See Details: " + e);
        }
    }

    public static void closeConnection() {
        try {
            con.close();
        } catch (Exception e) {
            System.out.println("Error Occurred while Closing DB, See Details: " + e);
        }
    }
}
