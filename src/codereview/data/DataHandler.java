package codereview.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;

import com.mysql.jdbc.Statement;

import classes.Cons;
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
		ArrayList<Integer> revIds = getReviewerForSegment(player.getId());
		int segmentId = saveSegmentAndGetID(player, code, comment);
		connect();
		for(int i=0; i<revIds.size();i++){			
			String query = "INSERT INTO segments_for_review" + "(player_id, segment_id) VALUES(" + revIds.get(i) + ", "
					+ segmentId + ")";
			connect.createStatement().executeUpdate(query);
		}
		connect.close();
	}

//	public int savePlayerAndGetID(Player player, String password) throws Exception {
//		connect();
//		String query = "INSERT INTO players(p_name, team_id, mail, u_password)" + " VALUES('" + player.getName() + "', "
//				+ player.getTeam().getId() + ", '" + player.getMail() + "', '" + password + "');";
//		java.sql.Statement statement = connect.createStatement();
//		statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
//		ResultSet result = statement.getGeneratedKeys();
//		result.first();
//		int returnedID = result.getInt(1);
//		connect.close();
//		return returnedID;
//	}

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
		String query = "SELECT segment_id FROM segments_for_review WHERE player_id=" + player.getId() + ";";
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

	public ArrayList<Integer> getReviewerForSegment(int id) throws Exception {
		connect();
		String query = "SELECT p_id FROM players WHERE is_reviewer = true AND p_id != " + id + ";";
		ResultSet result = connect.createStatement().executeQuery(query);
		ArrayList<Integer> reviewers = new ArrayList<Integer>();
		while (result.next()) {
			reviewers.add(result.getInt("p_id"));
		}
		connect.close();
		if (reviewers.size() == 0) {
			throw new Exception("No Reviwers Found!");
		}
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for (int i = 0; i < Cons.NUM_OF_REVIEWS_TO_SET_FOR_SEGMENT; i++) {
			if(reviewers.size()<1) break;
			temp.add(reviewers.remove(new Random().nextInt(reviewers.size())));
		}
		return temp;
	}

	public Player loadPlayer(String mail, String password) throws Exception {
		connect();
		String query = "SELECT * FROM players where mail like '" + mail + "' AND u_password like '" + password + "';";
		ResultSet result = connect.createStatement().executeQuery(query);
		if (!result.first()) {
			connect.close();
			return null;
		}
//		query = "SELECT * FROM teams WHERE t_id = " + result.getInt("team_id") + ";";
//		ResultSet teamResult = connect.createStatement().executeQuery(query);
//		if (!teamResult.first()) {
//			connect.close();
//			return null;
//		}
//		Team team = new Team(teamResult.getInt("t_id"), teamResult.getString("t_name"), "", teamResult.getInt("t_points"));
		Player p = new Player(result.getInt(1), result.getString(2), result.getInt(3), result.getString("image"), result.getString("mail"), result.getBoolean("is_reviewer"));
		connect.close();
		return p;
	}

	public void saveReview(int segId, int score, String review, int reviewerId) throws Exception {
		connect();
		float wordsInReview = Cons.calcWordsInSentence(review);
		String query = "INSERT INTO reviews(segment_id, score, review_text, player_id, words_in_review)" + "VALUES("
				+ segId + ", " + score + ", '" + review + "', " + reviewerId + ", " + wordsInReview + ");";
		java.sql.Statement statement = connect.createStatement();
		statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
		ResultSet result = statement.getGeneratedKeys();
		result.first();
		//		int returnedID = result.getInt(1);
		query = "DELETE FROM `segments_for_review` WHERE `segment_id`=" + segId + " AND player_id = "+reviewerId+";";
		connect.createStatement().executeUpdate(query);
		//		query = "UPDATE segments SET review_id = " + returnedID + " WHERE s_id = " + segId + ";";
		//		connect.createStatement().executeUpdate(query);
		connect.close();
	}

	public int getNumberOfSegmentsByPlayer(int id) throws Exception {
		connect();
		String query = "SELECT count(*) FROM segments WHERE player_id =" + id;
		ResultSet result = connect.createStatement().executeQuery(query);
		result.first();
		int count = result.getInt(1);
		connect.close();
		return count;
	}

	public int getNumberOfReviewsByPlayer(int id) throws Exception {
		connect();
		String query = "SELECT count(*) FROM reviews WHERE player_id =" + id;
		ResultSet result = connect.createStatement().executeQuery(query);
		result.first();
		int count = result.getInt(1);
		connect.close();
		return count;
	}

	public void updatePlayerPoints(Player player) throws Exception {
		connect();
		String query = "UPDATE players SET p_points = " + player.getPoints() + " WHERE p_id = " + player.getId() + ";";
		connect.createStatement().executeUpdate(query);
		connect.close();
	}

	public int getNumberOfReviewsWaitingByPlayer(int id) throws Exception {
		connect();
		String query = "SELECT count(*) FROM segments_for_review WHERE player_id =" + id;
		ResultSet result = connect.createStatement().executeQuery(query);
		result.first();
		int count = result.getInt(1);
		connect.close();
		return count;
	}

	public Review[] getReviewsBySegmentsWriter(Player player) throws Exception {
		connect();
		String query = "SELECT r.r_id, s.s_id, r.score, r.review_text, r.is_read FROM segments s, reviews r"
				+ "WHERE s.s_id = r.segment_id AND s.player_id = " + player.getId();
		ResultSet result = connect.createStatement().executeQuery(query);
		ArrayList<Review> reviews = new ArrayList<Review>();
		while (result.next()) {
			reviews.add(new Review(result.getInt(1), getSegmentById(result.getInt(2), player), result.getString(4),
					result.getInt(3), result.getBoolean(5)));
		}
		connect.close();
		return (Review[]) reviews.toArray();
	}

	public Segment getSegmentById(int id, Player player) throws Exception {
		connect();
		String query = "SELECT * FROM segments WHERE s_id = " + id + ";";
		ResultSet result = connect.createStatement().executeQuery(query);
		if (!result.first()) {
			connect.close();
			return null;
		}
		Segment seg = new Segment(result.getInt(1), result.getString(3), result.getString(4), player);
		connect.close();
		return seg;
	}

	public Segment getSegmentById(int id) throws Exception {
		connect();
		String query = "SELECT * FROM segments WHERE s_id = " + id + ";";
		ResultSet result = connect.createStatement().executeQuery(query);
		if (!result.first()) {
			connect.close();
			return null;
		}
		Segment seg = new Segment(result.getInt(1), result.getString(3), result.getString(4));
		connect.close();
		return seg;
	}

	public Segment[] getSegmentsByPlayer(Player player) throws Exception {
		connect();
		String query = "SELECT count(*) FROM segments WHERE player_id=" + player.getId() + ";";
		ResultSet result = connect.createStatement().executeQuery(query);
		if (!result.first()) {
			return null;
		}
		Segment[] list = new Segment[result.getInt(1)];
		query = "SELECT * FROM segments WHERE player_id = " + player.getId() + ";";
		result = connect.createStatement().executeQuery(query);
		int index = 0;
		while (result.next()) {
			//			Review rev = null;
			//			if (result.getInt(5) == 0) {
			//				rev = new Review(-1, null, "Waiting for Review...", 0, false);
			//			} else {
			//				rev = getReviewById(result.getInt(5));
			//			}
			list[index++] = new Segment(result.getInt(1), result.getString(3), result.getString(4), player);
		}
		connect.close();
		return list;
	}

	private Review getReviewById(int segId) throws Exception {
		connect();
		String query = "SELECT * FROM reviews WHERE segment_id = " + segId + ";";
		ResultSet result = connect.createStatement().executeQuery(query);
		if (!result.first()) {
			return null;
		}
		Review rev = new Review(result.getInt(1), null, result.getString(4), result.getInt(3), result.getBoolean(6));
		connect.close();
		return rev;
	}

	public Review[] getReviewsByPlayer(Player player) throws Exception {
		connect();
		String query = "SELECT COUNT(*) FROM reviews WHERE player_id=" + player.getId() + ";";
		ResultSet result = connect.createStatement().executeQuery(query);
		if (!result.first()) {
			return null;
		}
		Review[] list = new Review[result.getInt(1)];
		query = "SELECT * FROM reviews WHERE player_id=" + player.getId() + ";";
		result = connect.createStatement().executeQuery(query);
		int index = 0;
		while (result.next()) {
			Segment seg = getSegmentById(result.getInt(2));
			list[index++] = new Review(result.getInt(1), seg, result.getString(4), result.getInt(3), player,
					result.getBoolean(6));
		}
		connect.close();
		return list;
	}

	public Review getReviewBySegment(Segment seg) throws Exception {
		Review rev = getReviewById(seg.getSegId());
		rev.setSeg(seg);
		return rev;
	}

	public ArrayList<Player> getAllPlayers() throws Exception {
		connect();
		ArrayList<Player> temp = new ArrayList<Player>();
		String query = "SELECT * FROM players;";
		ResultSet result = connect.createStatement().executeQuery(query);
		while (result.next()) {
			temp.add(new Player(result.getInt(1), result.getString(2), result.getInt(3)));
		}
		connect.close();
		return temp;
	}

	public float getAverageScoreRecievedByPlayer(Player p) throws Exception {
		connect();
		String query = "SELECT AVG(score) FROM segments as s, reviews as r WHERE s.player_id=" + p.getId()
		+ " AND s.review_id IS NOT NULL AND s.s_id = r.segment_id;";
		ResultSet result = connect.createStatement().executeQuery(query);
		if (result.first())
			return result.getFloat(1);
		connect.close();
		throw new Exception("getAverageScoreRecievedByPlayer: No Scores!");
	}

	public float getAverageScoreGivenByPlayer(Player p) throws Exception {
		connect();
		String query = "SELECT AVG(score) FROM reviews WHERE player_id=" + p.getId() + ";";
		ResultSet result = connect.createStatement().executeQuery(query);
		float resultNum;
		if (result.first())
			resultNum = result.getFloat(1);
		else {
			connect.close();
			throw new Exception("getAverageScoreGivenByPlayer: No Scores!");
		}
		connect.close();
		return resultNum;
	}

	public int getNumberOfReviewsRecievedByPlayer(Player p) throws Exception {
		connect();
		String query = "SELECT COUNT(*) FROM segments WHERE player_id=" + p.getId() + " AND review_id IS NOT NULL;";
		ResultSet result = connect.createStatement().executeQuery(query);
		int resultNum;
		if (result.first())
			resultNum = result.getInt(1);
		else {
			connect.close();
			throw new Exception("getNumberOfReviewsRecievedByPlayer: No Reviews Found!");
		}
		connect.close();
		return resultNum;
	}

	public float getAverageNumverOfWordsInReview(Player p) throws Exception {
		connect();
		String query = "SELECT AVG(words_in_review) FROM reviews WHERE player_id=" + p.getId() + ";";
		ResultSet result = connect.createStatement().executeQuery(query);
		float resultNum;
		if (result.first())
			resultNum = result.getFloat(1);
		else {
			connect.close();
			throw new Exception("getAverageNumverOfWordsInReview: Error getting data!");
		}
		connect.close();
		return resultNum;
	}

	public void updateLogin(Player p) throws Exception {
		connect();
		String query = "UPDATE players SET times_login = times_login + 1 WHERE p_id=" + p.getId();
		connect.createStatement().executeUpdate(query);
		connect.close();
	}

	public int getNumberOfTimesLogin(Player p) throws Exception {
		connect();
		String query = "SELECT times_login FROM players WHERE p_id=" + p.getId();
		ResultSet result = connect.createStatement().executeQuery(query);
		int numOfLogin;
		if (result.first()) {
			numOfLogin = result.getInt(1);
		} else {
			connect.close();
			throw new Exception("getNumberOfTimesLogin: No data in tables!");
		}
		connect.close();
		return numOfLogin;
	}

	public ArrayList<Review> getReviewsBySegmentID(Segment seg) throws Exception{
		connect();
		String query = "SELECT r_id, score, review_text, is_read FROM reviews WHERE segment_id="+seg.getSegId()+";";
		ResultSet result = connect.createStatement().executeQuery(query);
		ArrayList<Review> reviews = new ArrayList<Review>();
		while(result.next()){
			reviews.add(new Review(result.getInt(1),seg, result.getString(3), result.getInt(2), result.getBoolean(5)));
		}
		connect.close();
		return reviews;
	}

	public boolean checkIfSegmentHasReviews(Segment seg) throws Exception {
		connect();
		String query = "SELECT * FROM reviews WHERE segment_id="+seg.getSegId()+";";
		ResultSet result = connect.createStatement().executeQuery(query);
		if(result.first()){
			connect.close();
			return true;
		}
		connect.close();
		return false;
	}

	public int getAvgScoreOfSeg(Segment seg) throws Exception {
		connect();
		String query = "SELECT AVG(score) FROM reviews WHERE segment_id="+seg.getSegId()+";";
		ResultSet result = connect.createStatement().executeQuery(query);
		if(result.first()) return (int)result.getFloat(1);
		throw new Exception("getAvgScoreOfSeg: No Score");
	}
	
	public void updateAvatar(Player player) throws Exception{
		connect();
		String query = "UPDATE players SET image = \""+player.getImagePath().toLowerCase()+"\" WHERE p_id="+player.getId()+";";
		connect.createStatement().executeUpdate(query);
		connect.close();
	}
}
