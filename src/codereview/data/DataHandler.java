package codereview.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.Statement;

import classes.*;



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
	
	public void saveSegment(Segment seg) throws Exception{
		connect();
		String query = "INSERT INTO segments(player_id, code_text, comment_text)"+
		"VALUES("+seg.getWriter().getId()+", \'"+seg.getCode()+"\', ";
		query+=(seg.getComment()==null)?"null":"\'"+seg.getComment()+"\'";
		connect.createStatement().executeUpdate(query+=");");
		connect.close();
	}
	public void saveSegment(Player player, String code, String comment) throws Exception{
		connect();
		String query = "INSERT INTO segments(player_id, code_text, comment_text)"+
		"VALUES("+player.getId()+", \'"+code+"\', ";
		query+=(comment.equals(""))?"null":"\'"+comment+"\'";
		connect.createStatement().executeUpdate(query+=");");
		connect.close();
	}
	
	public int savePlayerAndGetID(Player player, String password) throws Exception{
		connect();
		String query = "INSERT INTO players(p_name, team_id, mail, u_password)"+
						" VALUES('"+player.getName()+"', "+player.getTeam().getId()+", '"+player.getMail()+"', '"+password+"');";	
		java.sql.Statement statement = connect.createStatement();
		statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
		ResultSet result = statement.getGeneratedKeys();
		result.first();
		int returnedID = result.getInt(1);
		connect.close();
		return returnedID;
	}
	
	public int saveTeamAndGetID(Team team) throws Exception{
		connect();
		String query = "INSERT INTO teams(t_name) VALUES('"+team.getName()+"')";
		java.sql.Statement statement = connect.createStatement();
		statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
		ResultSet result = statement.getGeneratedKeys();
		result.first();
		int returnedID = result.getInt(1);
		connect.close();
		return returnedID;
	}
	
	public Segment[] getSegmentsByPlayer(Player player) throws Exception{
		Segment[] temp;
		int index = 0;
		connect();
		String query = "SELECT * FROM segments WHERE player_id="+player.getId()+";";
		String size = "SELECT COUNT(*) FROM segments WHERE player_id="+player.getId()+";";
		ResultSet result = connect.createStatement().executeQuery(query);
		ResultSet conutResult = connect.createStatement().executeQuery(size);
		if(!conutResult.first()){
			return null;
		}
		int cnt = conutResult.getInt(1);
		temp = new Segment[cnt];
		while(result.next()){
			temp[index++] = new Segment(result.getInt(1),result.getString(3),result.getString(4));
		}
		return temp;
	}
	
}
