package config;
import java.sql.*;
import java.util.Properties;

public class DBConfig {
	private static Connection conn;
	private static PreparedStatement pstmt;
	private static ResultSet rs;
	private static String un;
	private static String pass;
	private static DBConfig db= null;
	private DBConfig() {
		try {
			Properties p= new Properties();
			p.load(PathHelper.fin);
			String className= p.getProperty("driver.classname");
			String username= p.getProperty("username");
			String password= p.getProperty("password");
			String url= p.getProperty("url");
			un= p.getProperty("admin.username");
			pass= p.getProperty("admin.password");
			Class.forName(className);
			conn= DriverManager.getConnection(url, username, password);
			if(conn!= null) 
				System.out.println("DB connected");
			else
				System.out.println("DB not connected");
		}
		catch(Exception ex) {
			System.out.println("Error is "+ex);
		}
	}
	public static DBConfig getDBInstance() {
		if(db==null) {
			db= new DBConfig();
		}
		return db;
	}
	public static Connection getConnection() {
		return conn;
	}
	public static PreparedStatement getStatement() {
		return pstmt;
	}
	public static ResultSet getResultSet() {
		return rs;
	}
	public static String login() {
		return un+pass;
	}
}
