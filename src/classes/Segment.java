package classes;

public class Segment {
	
	private int segId;
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
	
	public Segment(int id, String code, String comment){
		this(code, comment);
		this.segId = id;
	}

	public Segment(String code, String comment) {
		this.code = code;
		this.comment = comment;
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

	public int getSegId() {
		return segId;
	}

	public void setSegId(int segId) {
		this.segId = segId;
	}
	
	
}
