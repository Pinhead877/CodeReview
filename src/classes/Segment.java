package classes;

public class Segment {
	
	private int segId;
	private String code, comment;
	private Player writer;
//	private Review review;
	
	
	
	public Segment(int segId, String code, String comment, Player writer) {
		this.segId = segId;
		this.code = code;
		this.comment = comment;
		this.writer = writer;
//		this.review = review;
	}
	
	public Segment(int id, String code, String comment){
		this(code, comment);
		this.segId = id;
	}

	public Segment(String code, String comment) {
		this.code = code;
		this.comment = comment;
	}

//	public Segment(int id, String code, String comment, Player player) {
//		this(id,code,comment);
//		this.writer = player;
//	}

	public String getCode() {
		return code;
	}

	public Player getWriter() {
		return writer;
	}

//	public Review getReview() {
//		return review;
//	}
//
//	public void setReview(Review review) {
//		this.review = review;
//	}
	
	public String getComment(){
		return this.comment;
	}

	public int getSegId() {
		return segId;
	}

	public void setSegId(int segId) {
		this.segId = segId;
	}

	@Override
	public String toString() {
		return "Segment [segId=" + segId + ", code=" + code + ", comment=" + comment + "]";
	}
	
	
}
