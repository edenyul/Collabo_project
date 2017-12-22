package test;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCTest {

	public static void main(String[] args) {
		
		Connection con=null;
		PreparedStatement prmt=null;
		ResultSet re=null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		
			String url="jdbc:mysql://localhost:3306/javadb?useSSL=true";
			con=DriverManager.getConnection(url,"root","12345");
			
			String sql="select * from member";
			prmt=con.prepareStatement(sql);
			
			re=prmt.executeQuery();
			while(re.next()) {
				System.out.println(re.getInt(1));
				System.out.println(re.getString(2));
				System.out.println(re.getString(3));
				System.out.println(re.getString(4));
				System.out.println(re.getString(5));
				System.out.println(re.getString(6));
				System.out.println(re.getString(7));
				System.out.println(re.getString(8));
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}

}
