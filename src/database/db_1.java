package database;

import java.sql.*;



public class db_1 {

	public static void main(String [] args) throws SQLException {

		ResultSet rückgabe = connect();
		while(rückgabe.next()) {
			System.out.println(rückgabe.getString(1) + " | " + rückgabe.getString(2));
		}
		rückgabe.close();
	}
	
	public static ResultSet connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://lm-bln-flatchat1.clciw2tvdadg.eu-central-1.rds.amazonaws.com?" +"user=root"+"&password=flatchat123");
			System.out.println(con.isClosed());



			Statement testStmt = con.createStatement();
			testStmt.executeUpdate("Insert Into lm_bln_flatchat1.users Values (2, 'Duschek', 'Felicia')");
			return testStmt.executeQuery("Select * From lm_bln_flatchat1.users;" );
		}
		catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
