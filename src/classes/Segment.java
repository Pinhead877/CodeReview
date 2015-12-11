package classes;

public class Segment {
	
	private String code, comment;
	private Player writer;
	private Review review;
	
	public Segment(String code, Player writer){
		this(code, writer, null);
	}
	
	public Segment(String code, Player writer, String comment){
		this.comment = comment;
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
	
	public String getComment(){
		return this.comment;
	}
}
