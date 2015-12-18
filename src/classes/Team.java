package classes;

import java.util.ArrayList;

public class Team {
	private int id;
	private String name;
	private String imagePath;
	private int points;
	private ArrayList<Player> teamMembers;
	
	public Team(String name){
		this.name = name;
		teamMembers = new ArrayList<Player>();
	}
	
	
	
	public Team(int id, String name, String imagePath, int points) {
		this.id = id;
		this.name = name;
		this.imagePath = imagePath;
		this.points = points;
	}



	public void addNewTeamMember(Player player){
		player.setTeam(this);
		teamMembers.add(player);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public ArrayList<Player> getTeamMembers() {
		return teamMembers;
	}

	public void setTeamMembers(ArrayList<Player> teamMembers) {
		this.teamMembers = teamMembers;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
