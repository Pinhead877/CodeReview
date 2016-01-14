package classes;

public class Review {
	private int revId;
	private Segment segment;
	private String review;
	private int score;
	private Player writer;
	private boolean read;
	
	public Review(Segment segment, String review, int score, Player writer) {
		this.segment = segment;
		this.review = review;
		this.score = score;
		this.writer = writer;
	}

	public Review(int revId, Segment segment, String review, int score, boolean read) {
		this.revId = revId;
		this.segment = segment;
		this.review = review;
		this.score = score;
		this.read = read;
	}

	public Review(int revId, Segment segment, String review, int score, Player writer, boolean read) {
		this.revId = revId;
		this.segment = segment;
		this.review = review;
		this.score = score;
		this.writer = writer;
		this.read = read;
	}

	public int getRevId() {
		return revId;
	}

	public void setRevId(int revId) {
		this.revId = revId;
	}

	public Segment getSeg() {
		return segment;
	}

	public void setSeg(Segment segment) {
		this.segment = segment;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Player getWriter() {
		return writer;
	}

	public void setWriter(Player writer) {
		this.writer = writer;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}
	
}
