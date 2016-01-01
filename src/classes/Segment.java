package classes;

public class Segment {
	
	private int segId;
	private String code, comment;
	private Player writer;
	private Review review;
	
	
	
	public Segment(int segId, String code, String comment, Player writer, Review review) {
		this.segId = segId;
		this.code = code;
		this.comment = comment;
		this.writer = writer;
		this.review = review;
	}

//	public Segment(String code, Player writer){
//		this(code, writer, null);
//	}
//	
//	public Segment(String code, Player writer, String comment){
//		this.comment = comment;
//		this.code = code;
//		this.writer = writer;
//	}
	
	public Segment(int id, String code, String comment){
		this(code, comment);
		this.segId = id;
	}

	public Segment(String code, String comment) {
		this.code = code;
		this.comment = comment;
	}

	public Segment(int int1, String string, String string2, Player player) {
		// TODO Auto-generated constructor stub
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
