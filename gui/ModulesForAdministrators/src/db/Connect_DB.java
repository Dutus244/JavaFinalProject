package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.Vector;

public class Connect_DB {
	private static Connect_DB instance = null;
	private Connection conn = null;
	
	// Database URL
	static final String DB_URL = "jdbc:mysql://javachatapplication.mysql.database.azure.com:3306/java_finalproject_chatapplication";
	// Database credentials
	static final String USER = "java";
	static final String PASS = "20Ktpm2022";
	
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
	
	public Vector<Vector<Object>> getAllUser(String filter, String order) throws SQLException {
		Statement stmt = conn.createStatement();
		String sql = "select * from users order by " + filter + " " + order;

		ResultSet rs = stmt.executeQuery(sql);
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		while (rs.next()) { 
			Vector<Object> row = new Vector<Object>();
			row.add(rs.getString("UserName"));
			row.add(rs.getString("FullName"));
			row.add(rs.getString("Address"));
			row.add(rs.getDate("DOB"));
			row.add(rs.getString("Sex"));
			row.add(rs.getString("Email"));
			row.add(rs.getTimestamp("CreateTime"));
			row.add(rs.getBoolean("LockAccount"));
			
			data.add(row);
		}

		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return data;
	} 

	public Vector<Vector<Object>> searchUser(String keyword, String criteria, String filter, String order) throws SQLException {
		Statement stmt = conn.createStatement();
		String sql = "select * from users "
				+ "where " + criteria + " like '%" + keyword + "%' "
				+ "order by " + filter + " " + order;

		ResultSet rs = stmt.executeQuery(sql);
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		while (rs.next()) { 
			Vector<Object> row = new Vector<Object>();
			row.add(rs.getString("UserName"));
			row.add(rs.getString("FullName"));
			row.add(rs.getString("Address"));
			row.add(rs.getDate("DOB"));
			row.add(rs.getString("Sex"));
			row.add(rs.getString("Email"));
			row.add(rs.getTimestamp("CreateTime"));
			row.add(rs.getBoolean("LockAccount"));
			
			data.add(row);
		}

		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return data;
	}
	
	public void addUser(String username, String password, String fullname, String addr, 
			String dob, String sex, String email, String dateCreated) throws SQLException {
		Statement stmt = conn.createStatement();
		String sql = MessageFormat.format("insert into users values "
				+ "(uuid(), \"{0}\", \"{1}\", \"{2}\", \"{3}\", \"{4}\", \"{5}\", \"{6}\", \"{7}\", false, \"\")", 
				username, fullname, addr, dob, sex, email, password, dateCreated);

		stmt.executeUpdate(sql);
		
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateUser(String username, Vector<String> val) throws SQLException {
		Statement stmt = conn.createStatement();
		String sql = MessageFormat.format("update users "
				+ "set fullname = \"{0}\", address = \"{1}\", dob = \"{2}\", sex = \"{3}\", email = \"{4}\" "
				+ "where username = \"{5}\"", 
				val.get(0), val.get(1), val.get(2), val.get(3), val.get(4), username);

		stmt.executeUpdate(sql);
		
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteUser(String username) throws SQLException {
		Statement stmt = conn.createStatement();
		String sql = "delete from users where UserName=\"" + username + "\"";
		
		stmt.executeUpdate(sql);
		
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateBanStatus(String username, String status) throws SQLException {
		Statement stmt = conn.createStatement();
		String sql = "update users set LockAccount=" + status + " where UserName=\"" + username + "\"";
		
		stmt.executeUpdate(sql);
		
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Vector<Vector<Object>> getUserFriendList(String user, String filter, String order) throws SQLException {
		Statement stmt = conn.createStatement();
		
		String sqlID = "select UserID from users where users.UserName = '" + user + "'";
		ResultSet rsid = stmt.executeQuery(sqlID); rsid.next();
		String id = rsid.getString("UserID");
				
		String sql = "select * from users "
				+ "join friendlist on users.UserID = friendlist.FriendID "
				+ "where users.UserID = '" + id + "' "
				+ "order by " + filter + " " + order;
		
		ResultSet rs = stmt.executeQuery(sql);
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		while (rs.next()) { 
			Vector<Object> row = new Vector<Object>();
			row.add(rs.getString("UserName"));
			row.add(rs.getString("FullName"));
			row.add(rs.getString("Address"));
			row.add(rs.getDate("DOB"));
			row.add(rs.getString("Sex"));
			row.add(rs.getString("Email"));
			row.add(rs.getTimestamp("CreateTime"));
			
			data.add(row);
		}
		
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	
	public Vector<Vector<Object>> searchUserInUserFriendList(String user, String criteria, String keyword, String filter, String order) throws SQLException{
		Statement stmt = conn.createStatement();

		String sqlID = "select UserID from users where users.UserName = '" + user + "'";
		ResultSet rsid = stmt.executeQuery(sqlID); rsid.next();
		String id = rsid.getString("UserID");
		
		String sql = "select * from users "
				+ "join friendlist on users.UserID = friendlist.FriendID "
				+ "where users.UserID = '" + id + "' "
				+ "and " + criteria + " like '%" + keyword + "%' "
				+ "order by " + filter + " " + order;
		
		ResultSet rs = stmt.executeQuery(sql);
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		while (rs.next()) { 
			Vector<Object> row = new Vector<Object>();
			row.add(rs.getString("UserName"));
			row.add(rs.getString("FullName"));
			row.add(rs.getString("Address"));
			row.add(rs.getDate("DOB"));
			row.add(rs.getString("Sex"));
			row.add(rs.getString("Email"));
			row.add(rs.getTimestamp("CreateTime"));
			
			data.add(row);
		}
		
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	
	public Vector<Vector<Object>> getLoginHistory(String user, String filter, String order) throws SQLException {
		Statement stmt = conn.createStatement();
		
		String sqlID = "select UserID from users where users.UserName = '" + user + "'";
		ResultSet rsid = stmt.executeQuery(sqlID); rsid.next();
		String id = rsid.getString("UserID");
		
		String sql = "select loginhistory.LoginTime from loginhistory "
				+ "where loginhistory.UserID = '" + id + "' "
				+ "order by " + filter + " " + order;
		
		ResultSet rs = stmt.executeQuery(sql);
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		while (rs.next()) { 
			Vector<Object> row = new Vector<Object>();
			row.add(rs.getDate("LoginTime"));
			
			data.add(row);
		}
		
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	
	public Vector<Vector<Object>> getLogInList(String filter, String order) throws SQLException {
		Statement stmt = conn.createStatement();
		String sql = "select * from users "
				+ "join loginhistory on users.UserID = loginhistory.UserID "
				+ "order by " + filter + " " + order;
		
		ResultSet rs = stmt.executeQuery(sql);
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		while (rs.next()) { 
			Vector<Object> row = new Vector<Object>();
			row.add(rs.getDate("LoginTime"));
			row.add(rs.getString("UserName"));
			row.add(rs.getString("FullName"));
			
			data.add(row);
		}
		
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	
	public Vector<Vector<Object>> searchUserInLogInList(String criteria, String keyword, String filter, String order) throws SQLException {
		Statement stmt = conn.createStatement();
		System.out.println(criteria + " " + keyword);
		String sql = "select * from users "
				+ "join loginhistory on users.UserID = loginhistory.UserID "
				+ "and " + criteria + " like '%" + keyword + "%' "
				+ "order by " + filter + " " + order;
		
		ResultSet rs = stmt.executeQuery(sql);
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		while (rs.next()) { 
			Vector<Object> row = new Vector<Object>();
			row.add(rs.getDate("LoginTime"));
			row.add(rs.getString("UserName"));
			row.add(rs.getString("FullName"));
			
			data.add(row);
		}
		
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	
	public Vector<Vector<Object>> getAllGroupChat(String filter, String order) throws SQLException {
		Statement stmt = conn.createStatement();
		String sql = "select * from inbox "
				+ "where inbox.TypeInbox = 'group' "
				+ "order by " + filter + " " + order;
		
		ResultSet rs = stmt.executeQuery(sql);
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		while (rs.next()) { 
			Vector<Object> row = new Vector<Object>();
			row.add(rs.getString("UserName"));
			
			data.add(row);
		}
		
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	public Vector<Vector<Object>> getGroupChatAdmin(String id, String filter, String order) throws SQLException {
		Statement stmt = conn.createStatement();
		String sql = "select * from users "
				+ "join admingroup on users.UserID = admingroup.UserID "
				+ "join inbox on admingroup.InboxID = inbox.InboxID "
				+ "and inbox.TypeInbox = 'group' "
				+ "and inbox.InboxID ='" + id + "' "
				+ "order by " + filter + " " + order;
		
		ResultSet rs = stmt.executeQuery(sql);
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		while (rs.next()) { 
			Vector<Object> row = new Vector<Object>();
			row.add(rs.getString("UserName"));
			row.add(rs.getString("FullName"));
			
			data.add(row);
		}
		
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}	
	
	public Vector<Vector<Object>> getGroupChatMember(String id, String filter, String order) throws SQLException {
		Statement stmt = conn.createStatement();
		String sql = "select * from users "
				+ "join inboxparticipants on users.UserID = inboxparticipants.UserID "
				+ "join inbox on inboxparticipants.InboxID = inbox.InboxID "
				+ "and inbox.TypeInbox = 'group' "
				+ "and + " + id + " = inbox.InboxID"
				+ "order by " + filter + " " + order;
		
		ResultSet rs = stmt.executeQuery(sql);
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		while (rs.next()) { 
			Vector<Object> row = new Vector<Object>();
			row.add(rs.getString("UserName"));
			row.add(rs.getString("FullName"));
			
			data.add(row);
		}
		
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
}
