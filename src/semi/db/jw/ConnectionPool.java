package semi.db.jw;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionPool {
	
	static DataSource ds = null;
	static {
		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/myoracle");			
		} catch(NamingException ne) {
			System.out.println(ne.getMessage());
		}
	}
	
	public static Connection getConn() throws SQLException{
		Connection con = ds.getConnection();
		return con;
	}
	
	public static void close(Connection con,Statement stmt,ResultSet rs) {
		try {
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			if(con!=null) con.close();
		}catch(SQLException s) {
			System.out.println(s.getMessage());
		}
	}
	public static void close(ResultSet rs,PreparedStatement pstmt,
			Connection con) {
		try {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(con!=null) con.close();
		}catch(SQLException s) {
			System.out.println(s.getMessage());
		}
	}
	public static void close(Connection con){
		try {
			if(con!=null) con.close();
		}catch(SQLException s) {
			System.out.println(s.getMessage());
		}
	}
	public static void close(Statement stmt) {
		try {
			if(stmt!=null) stmt.close();
		}catch(SQLException s) {
			System.out.println(s.getMessage());
		}
	}
	public static void close( ResultSet rs) {
		try {
			if(rs!=null) rs.close();
		}catch(SQLException s) {
			System.out.println(s.getMessage());
		}
	}
}
