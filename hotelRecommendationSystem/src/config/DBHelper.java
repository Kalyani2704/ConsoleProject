package config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class DBHelper {
	protected DBConfig db= DBConfig.getDBInstance();
	protected Connection conn= DBConfig.getConnection();
	protected PreparedStatement pstmt= DBConfig.getStatement();
	protected ResultSet rs= DBConfig.getResultSet();
	protected String login= DBConfig.login();
}
