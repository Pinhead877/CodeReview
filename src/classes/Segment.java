package classes;

public class Segment {
	
	private String code;
	private Player writer;
	private Review review;
	
	public Segment(String code, Player writer){
		this.code = code;
		this.writer = writer;
	}

	public String getCode() {
		return code;
	}

	public Player getWriter() {
		return writer;
	}

	public Review getReview() {
		return review;
	}

	public void setReview(Review review) {
		this.review = review;
	}
}
