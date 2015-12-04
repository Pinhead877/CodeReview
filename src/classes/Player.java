package classes;

import java.util.ArrayList;

public class Player {
	private int id;
	private String name;
	private Team team;
	private int points;
	private String imagePath;
	
	private ArrayList<Segment> segmentsPublished;
	private ArrayList<Review> reviewsWrote;
	
	public Player(String name){
		this.name = name;
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
	
	
}
