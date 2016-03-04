package classes;

public final class Cons {
	
	public static final String PATH_TO_SERVER = "localhost";
	
	public static final int MAINMENU = 1;
	public static final int CREATOR = 2;
	
	public static final int NUMBER_OF_IMAGES = 14; 
	
	public static final int REVIEW_VIEW = 1;
	public static final int SEGMENT_VIEW = 2;
	
	public static final int MAIN_MENU_SCREEN = 11;
	public static final int CREATOR_MODE_SCREEN = 12;
	public static final int NEW_SEGMENT_SCREEN = 13;
	public static final int SEGMENTS_WROTE_SCREEN = 14;
	public static final int REVIEWS_WROTE_SCREEN = 15;
	public static final int REVIEWS_WAITING_SCREEN = 16;
	public static final int PROFILE_SCREEN = 17;
	public static final int TEAM_PROFILE_SCREEN = 18;
	public static final int NEW_REVIEW_SCREEN = 19;
	public static final int PREVIEW_SEGMENT_SCREEN = 20;
	public static final int PREVIEW_REVIEW_SCREEN = 21;
	public static final int LOGIN_SCREEN = 22;

	public static final int NUM_PLAYERS_IN_HALL_OF_FAME = 11;
	
	public static float calcWordsInSentence(String review) {
		String [] words = review.split(" ");
		int sum = 0;
		for(String word: words)
			sum+=word.length();
		return (float)sum/words.length;
	}
}
