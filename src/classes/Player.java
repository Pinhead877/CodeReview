package classes;

import java.util.ArrayList;

public class Player {
	private int id;
	private String name;
	private Team team;
	private int points;
	private String imagePath;
	private String mail;
	private boolean reviewer;
	
	private ArrayList<Segment> segmentsPublished;
	private ArrayList<Review> reviewsWrote;
	
	public Player(String name){
		this.name = name;
	}
	
	public Player(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Player(int id, String name, int points){
		this(name,null,points,null,null);
		this.id = id;
	}
	
	public Player(String name, Team team, int points, String image, String mail){
		this.name = name;
		this.team = team;
		this.points = points;
		this.imagePath = image;
		this.mail = mail;
	}
	
	public Player(int id, String name, Team team, int points, String image, String mail, boolean reviewer){
		this.id = id;
		this.name = name;
		this.team = team;
		this.points = points;
		this.imagePath = image;
		this.mail = mail;
		this.reviewer = reviewer;
	}

	public Player(String name, Team team, String mail) {
		this.name = name;
		this.team = team;
		this.mail = mail;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	public int getPoints() {
		return points;
	}
	public void addPoints(int points) {
		this.points += points;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public ArrayList<Segment> getSegmentsPublished() {
		return segmentsPublished;
	}
	public void setSegmentsPublished(ArrayList<Segment> segmentsPublished) {
		this.segmentsPublished = segmentsPublished;
	}
	public ArrayList<Review> getReviewsWrote() {
		return reviewsWrote;
	}
	public void setReviewsWrote(ArrayList<Review> reviewsWrote) {
		this.reviewsWrote = reviewsWrote;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public boolean isReviewer() {
		return reviewer;
	}

	public void setReviewer(boolean reviewer) {
		this.reviewer = reviewer;
	}
	
	
}
