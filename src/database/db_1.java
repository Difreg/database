package database;

import java.sql.*;


public class db_1 {
    private static Connection con;
    private static ResultSet result;

    public static void main(String[] args) throws SQLException {
        con = null;
        result = null;
        connect();
        getResult(2, "Select * from FT.Users");
        printResults(result);

    }

    public static Connection connect() {
        //	Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://lm-bln-flatchat1.clciw2tvdadg.eu-central-1.rds.amazonaws.com?" + "user=root" + "&password=flatchat123");
            System.out.println("Verbindung hergestellt");
            return con;

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static void getResult(int type, String stmt) {
        Statement testStmt = null;
        try {
            testStmt = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (type == 1) {
            try {
                System.out.println(testStmt.executeUpdate(stmt));
                result = null;
                System.out.println("Update ausgeführt");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (type == 2) {
            try {
                result = testStmt.executeQuery(stmt);
                System.out.println("Abfrage ausgeführt");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Kein Typ ausgewählt.");
            result = null;
        }
    }



    public static void printResults(ResultSet rs) throws SQLException {
        if (rs != null) {
            int zaehler = 0;
            while (rs.next()) {
                System.out.println(rs.getString(1) + " | " + rs.getString(2) + " | " + rs.getString(3));
                zaehler++;
            }
            System.out.println("Zeilen: " + zaehler);
        }

    }

}
