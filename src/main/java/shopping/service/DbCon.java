package shopping.service;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbCon {
	private static Connection connection=null;
	public static Connection getConnection(){
		try {
		if(connection==null) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/shopping","root","root");
			System.out.println("connected to database");
		}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return connection;
	}
}
