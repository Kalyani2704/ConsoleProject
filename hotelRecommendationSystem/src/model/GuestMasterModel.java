package model;

public class GuestMasterModel extends HotelMasterModel{
	private int guest_id;
	private String guest_fname;
	private String guest_lname;
	private String contact;
	private String email;
	private int feedback_id;
	private String review;
	private int rating;
	public GuestMasterModel() {
		
	}
	public GuestMasterModel(int guest_id, String guest_fname, String guest_lname, String contact, String email, int feedback_id, String review, int rating) {
		this.guest_id = guest_id;
		this.guest_fname = guest_fname;
		this.guest_lname = guest_lname;
		this.contact = contact;
		this.email = email;
		this.feedback_id = feedback_id;
		this.review = review;
		this.rating = rating;
	}
	public int getGuest_id() {
		return guest_id;
	}
	public void setGuest_id(int guest_id) {
		this.guest_id = guest_id;
	}
	public String getGuest_fname() {
		return guest_fname;
	}
	public void setGuest_fname(String guest_fname) {
		this.guest_fname = guest_fname;
	}
	public String getGuest_lname() {
		return guest_lname;
	}
	public void setGuest_lname(String guest_lname) {
		this.guest_lname = guest_lname;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}		
	public int getFeedback_id() {
		return feedback_id;
	}
	public void setFeedback_id(int feedback_id) {
		this.feedback_id = feedback_id;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
}
