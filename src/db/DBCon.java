package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBCon {
    private static String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static String USER = "osfu";
    private static String PASSWORD = "12345678";
    private static String DRIVER = "oracle.jdbc.OracleDriver";
    private static Connection con = null;
    
    public static Connection getCon() {
    	try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return con;
		}


public static void close() {
	if(con!=null) {
		try {
			if(!con.isClosed()) {
				
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	con=null;
}
public static void main(String[] args) {
	getCon();
	close();
}
}

