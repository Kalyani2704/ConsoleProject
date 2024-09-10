package model;

public class HotelMasterModel {
	private int hotel_id;
	private String hotel_name;
	private String address;
	private int area_id;
	private String area_name;
	private int city_id;
	private String city_name;
	private int amenity_id;
	private String amenity;
	private int room_id;
	private String room_type;
	private int price;
	public HotelMasterModel() {
		
	}
	public HotelMasterModel(int hotel_id, String hotel_name, String address, int area_id) {
		this.hotel_id = hotel_id;
		this.hotel_name = hotel_name;
		this.address = address;
		this.area_id = area_id;
	}
	public void CityMasterModel() {
		
	}
	public void AreaMasterModel() {
		
	}
	public int getHotel_id() {
		return hotel_id;
	}
	public void setHotel_id(int hotel_id) {
		this.hotel_id = hotel_id;
	}
	public String getHotel_name() {
		return hotel_name;
	}
	public void setHotel_name(String hotel_name) {
		this.hotel_name = hotel_name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getArea_id() {
		return area_id;
	}
	public void setArea_id(int area_id) {
		this.area_id = area_id;
	}
	public String getArea_name() {
		return area_name;
	}
	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}
	public int getCity_id() {
		return city_id;
	}
	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	public int getAmenity_id() {
		return amenity_id;
	}
	public void setAmenity_id(int amenity_id) {
		this.amenity_id = amenity_id;
	}
	public String getAmenity() {
		return amenity;
	}
	public void setAmenity(String amenity) {
		this.amenity = amenity;
	}
	public int getRoom_id() {
		return room_id;
	}
	public void setRoom_id(int room_id) {
		this.room_id = room_id;
	}
	public String getRoom_type() {
		return room_type;
	}
	public void setRoom_type(String room_type) {
		this.room_type = room_type;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}
