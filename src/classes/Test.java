package classes;

import codereview.data.*;

public class Test {

	public static void main(String[] args) {
		Player player = new Player(45,"This is the name");
		Segment seg1 = new Segment("THE CODE THE CODE", player, "COmmmmment");
		Segment seg2 = new Segment("THE CODE THE CODE", player);

		try {
			new DataHandler().saveSegment(seg1);
			new DataHandler().saveSegment(seg2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("OK!");
	}

}
