package codereview.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;



public class DataHandler {
	
	private Connection connect;
	
	private static final String [] parameters = {"localhost","3306","admin","monkey36more"};
	
	private static final String DB_NAME = "codereviewdb";
	
	private static final int IP = 0;
	private static final int PORT = 1;
	private static final int USER = 2;
	private static final int PASS = 3;
	
	public DataHandler() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		connect();
		connect.close();
	}

	private void connect() throws Exception {
		connect = DriverManager.getConnection("jdbc:mysql://"+parameters[IP]+":"+parameters[PORT]+"?user="+parameters[USER]+"&password="+parameters[PASS]);
		connect.createStatement().executeQuery("USE "+DB_NAME);
	}
	
}
