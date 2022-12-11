package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect_DB {
	private static Connect_DB instance = null;
	private Connection conn = null;
	
	// Database URL
	static final String DB_URL = "jdbc:mysql://localhost/java_finalproject_chatapplication";
	// Database credentials
	static final String USER = "root";
	static final String PASS = "123456";
	
	private Connect_DB() {
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			if(conn != null) 
				conn.close();
		} catch (SQLException se) {
			// TODO: handle exception
			se.printStackTrace();
		}
	}
	
	public static Connect_DB getInstance() {
        if(instance == null){
            instance = new Connect_DB();
        }
        return instance;
	}
}
