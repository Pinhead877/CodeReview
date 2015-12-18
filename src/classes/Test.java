package classes;

import codereview.data.*;

public class Test {

	public static void main(String[] args) {
		Team team = new Team("A-Team");
		
		Player player = new Player("Alex", team, "alex@google.com");
		player.setId(1);
		
		Segment seg1 = new Segment("THE CODE THE CODE", player, "COmmmmment");
		Segment seg2 = new Segment("THE CODE THE CODE", player);

		try {
//			for (int i = 0; i < 10; i++) {
//				team.setId(new DataHandler().saveTeamAndGetID(team));
//				player.setTeam(team);
//				player.setId(new DataHandler().savePlayerAndGetID(player, "123456789"));
//			}
//			new DataHandler().saveSegment(seg1);
//			new DataHandler().saveSegment(seg2);
//			Segment [] data = new DataHandler().getSegmentsByPlayer(player);
//			if(data!=null){
//				System.out.println("OK!");
//				for(Segment seg: data)
//					System.out.println("ID: "+seg.getSegId()+"\nCODE: "+seg.getCode()+"\nCOMMENT: "+seg.getComment());
//			}else
//				System.out.println("ERROR");
//			new DataHandler().saveSegmentAndGetID(player, "123123123", "fdsfsd");
			System.out.println(new DataHandler().loadPlayer("Alex@Alex.com", "123456789").getName());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
