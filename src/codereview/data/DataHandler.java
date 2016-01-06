package codereview.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;

import com.mysql.jdbc.Statement;

import classes.Player;
import classes.Review;
import classes.Segment;
import classes.Team;

public class DataHandler {

	private Connection connect;

	private static final String[] parameters = { "localhost", "3306", "admin", "monkey36more" };

	private static final String DB_NAME = "codereviewdb";

	private static final int IP = 0;
	private static final int PORT = 1;
	private static final int USER = 2;
	private static final int PASS = 3;

	public DataHandler() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		connect();
		connect.close();
	}

	private void connect() throws Exception {
		connect = DriverManager.getConnection("jdbc:mysql://" + parameters[IP] + ":" + parameters[PORT] + "?user="
				+ parameters[USER] + "&password=" + parameters[PASS]);
		connect.createStatement().executeQuery("USE " + DB_NAME);
	}

	public int saveSegmentAndGetID(Player player, String code, String comment) throws Exception {
		connect();
		String query = "INSERT INTO segments(player_id, code_text, comment_text)" + "VALUES(" + player.getId() + ", \'"
				+ code + "\', ";
		query += (comment == null) ? "null" : "\'" + comment + "\'";
		query += ");";
		java.sql.Statement statement = connect.createStatement();
		statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
		ResultSet result = statement.getGeneratedKeys();
		result.first();
		int returnedID = result.getInt(1);
		connect.close();
		return returnedID;
	}

	public void sendSegment(Player player, String code, String comment) throws Exception {
		System.out.println("ID of the sender: " + player.getId());
		int reviewer = getReviewerForSegment(player.getId());
		int segmentId = saveSegmentAndGetID(player, code, comment);
		connect();
		String query = "INSERT INTO segments_for_review" + "(player_id, segment_id) VALUES(" + reviewer + ", "
				+ segmentId + ")";
		connect.createStatement().executeUpdate(query);
		connect.close();
	}

	public int savePlayerAndGetID(Player player, String password) throws Exception {
		connect();
		String query = "INSERT INTO players(p_name, team_id, mail, u_password)" + " VALUES('" + player.getName() + "', "
				+ player.getTeam().getId() + ", '" + player.getMail() + "', '" + password + "');";
		java.sql.Statement statement = connect.createStatement();
		statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
		ResultSet result = statement.getGeneratedKeys();
		result.first();
		int returnedID = result.getInt(1);
		connect.close();
		return returnedID;
	}

	public int saveTeamAndGetID(Team team) throws Exception {
		connect();
		String query = "INSERT INTO teams(t_name) VALUES('" + team.getName() + "')";
		java.sql.Statement statement = connect.createStatement();
		statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
		ResultSet result = statement.getGeneratedKeys();
		result.first();
		int returnedID = result.getInt(1);
		connect.close();
		return returnedID;
	}

	public Segment[] getSegmentsForReviewByPlayer(Player player) throws Exception {
		Segment[] segments;
		int index = 0;
		connect();
		String query = "SELECT * FROM segments_for_review WHERE player_id=" + player.getId() + ";";
		String size = "SELECT COUNT(*) FROM segments_for_review WHERE player_id=" + player.getId() + ";";
		ResultSet result = connect.createStatement().executeQuery(query);
		ResultSet conutResult = connect.createStatement().executeQuery(size);
		if (!conutResult.first()) {
			return null;
		}
		String tempIDs = "";
		while (result.next()) {
			if (index++ > 0) {
				tempIDs += ", ";
			}
			tempIDs += result.getInt(1);
		}
		if (tempIDs == "") {
			return segments = new Segment[0];
		}
		query = "SELECT * FROM segments WHERE s_id in (" + tempIDs + ");";
		result = connect.createStatement().executeQuery(query);
		segments = new Segment[index];
		index = 0;
		while (result.next()) {
			segments[index++] = new Segment(result.getInt(1), result.getString(3), result.getString(4));
		}
		connect.close();
		return segments;
	}

	public int getReviewerForSegment(int id) throws Exception {
		connect();
		String query = "SELECT p_id FROM players WHERE is_reviewer = true AND p_id != " + id + ";";
		ResultSet result = connect.createStatement().executeQuery(query);
		ArrayList<Integer> reviewers = new ArrayList<Integer>();
		while (result.next()) {
			reviewers.add(result.getInt("p_id"));
		}
		connect.close();
		if(reviewers.size()==0){
			throw new Exception("No Reviwers Found!");
		}
		return reviewers.get(new Random().nextInt(reviewers.size()));
	}

	public Player loadPlayer(String mail, String password) throws Exception {
		connect();
		String query = "SELECT * FROM players where mail like '" + mail + "' AND u_password like '" + password + "';";
		ResultSet result = connect.createStatement().executeQuery(query);
		if (!result.first()) {
			return null;
		}
		query = "SELECT * FROM teams WHERE t_id = " + result.getInt("team_id") + ";";
		ResultSet teamResult = connect.createStatement().executeQuery(query);
		if (!teamResult.first()) {
			return null;
		}
		Team team = new Team(teamResult.getInt("t_id"), teamResult.getString("t_name"), "",
				teamResult.getInt("t_points"));
		return new Player(result.getInt("p_id"), result.getString("p_name"), team, result.getInt("p_points"), "",
				result.getString("mail"), result.getBoolean("is_reviewer"));
	}

	public void saveReview(int segId, int score, String review, int reviewerId) throws Exception{
		connect();
		String query = "INSERT INTO reviews(segment_id, score, review_text, player_id)"+
		"VALUES("+segId+", "+score+", '"+review+"', "+reviewerId+");";
		java.sql.Statement statement = connect.createStatement();
		statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
		ResultSet result = statement.getGeneratedKeys();
		result.first();
		int returnedID = result.getInt(1);
		query = "DELETE FROM `segments_for_review` WHERE `segment_id`="+segId+";";
		connect.createStatement().executeUpdate(query);
		query = "UPDATE segments SET review_id = "+returnedID+" WHERE s_id = "+segId+";";
		connect.createStatement().executeUpdate(query);
		connect.close();
	}

	public String getNumberOfSegmentsByPlayer(int id) throws Exception {
		connect();
		String query = "SELECT count(*) FROM segments WHERE player_id ="+id;
		ResultSet result = connect.createStatement().executeQuery(query);
		result.first();
		int count = result.getInt(1);
		return count+"";
	}
	
	public String getNumberOfReviewsByPlayer(int id) throws Exception {
		connect();
		String query = "SELECT count(*) FROM reviews WHERE player_id ="+id;
		ResultSet result = connect.createStatement().executeQuery(query);
		result.first();
		int count = result.getInt(1);
		return count+"";
	}

	public void updatePlayerPoints(Player player) throws Exception {
		connect();
		String query = "UPDATE players SET p_points = "+player.getPoints()+" WHERE p_id = "+player.getId()+";";
		connect.createStatement().executeUpdate(query);
		connect.close();
	}
	
	public String getNumberOfReviewsWaitingByPlayer(int id) throws Exception {
		connect();
		String query = "SELECT count(*) FROM segments_for_review WHERE player_id ="+id;
		ResultSet result = connect.createStatement().executeQuery(query);
		result.first();
		int count = result.getInt(1);
		return count+"";
	}
	
	public Review[] getReviewsBySegmentsWriter(Player player) throws Exception{
		connect();
		String query = "SELECT r.r_id, s.s_id, r.score, r.review_text, r.is_read FROM segments s, reviews r"+
		"WHERE s.s_id = r.segment_id AND s.player_id = "+player.getId();
		ResultSet result = connect.createStatement().executeQuery(query);
		ArrayList<Review> reviews = new ArrayList<Review>();
		while(result.next()){
			reviews.add(new Review(result.getInt(1), getSegmentById(result.getInt(2), player), result.getString(4), result.getInt(3), result.getBoolean(5)));
		}
		connect.close();
		return (Review[]) reviews.toArray();
	}
	
	public Segment getSegmentById(int id, Player player) throws Exception{
		connect();
		String query = "SELECT * FROM segments WHERE s_id = "+id+";";
		ResultSet result = connect.createStatement().executeQuery(query);
		if(!result.first()){
			connect.close();
			return null;
		}
		Segment seg = new Segment(result.getInt(1), result.getString(3), result.getString(4), player);
		connect.close();
		return seg;
	}
	
	public Segment[] getSegmentsByPlayer(Player player) throws Exception{
		connect();
		String query = "SELECT count(*) FROM segments WHERE player_id="+player.getId()+";";
		ResultSet result = connect.createStatement().executeQuery(query);
		if(!result.first()){
			return null;
		}
		Segment [] list = new Segment[result.getInt(1)];
		query = "SELECT * FROM segments WHERE player_id = "+player.getId()+";";
		result = connect.createStatement().executeQuery(query);
		int index = 0;
		while(result.next()){
			list[index++] = new Segment(result.getInt(1),result.getString(3),result.getString(4),player);
		}
		return list;
	}
}
