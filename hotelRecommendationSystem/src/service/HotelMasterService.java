package service;
import java.util.List;
import model.HotelMasterModel;
import repository.HotelMasterRepository;

public class HotelMasterService {
	HotelMasterRepository hotelRepo= new HotelMasterRepository();
	public String login() {
		return hotelRepo.login();
	}
	public int getCityId(String name) {
		return hotelRepo.getCityId(name);
	}
	public boolean isAddCity(HotelMasterModel model) {
		return hotelRepo.isAddCity(model);
	}
	public List<HotelMasterModel> showCities() {
		return hotelRepo.showCities();
	}
	public int getAreaId(String aname) {
		return hotelRepo.getAreaId(aname);
	}
	public boolean isAddArea(HotelMasterModel model) {
		return hotelRepo.isAddArea(model);
	}
	public List<HotelMasterModel> showAreas() {
		return hotelRepo.showAreas();
	}
	public int getHotelId(String name) {
		return hotelRepo.getHotelId(name);
	}
	public boolean isAddHotel(HotelMasterModel model) {
		return hotelRepo.isAddHotel(model);
	}
	public boolean updateHotel(String new_name, int id) {
		return hotelRepo.updateHotel(new_name, id);
	}
	public boolean updateAddress(String address, int id) {
		return hotelRepo.updateAddress(address, id);
	}
	public boolean updatePrice(int price, int id) {
		return hotelRepo.updatePrice(price,id);
	}
	public boolean deleteHotel(int id){
		return hotelRepo.deleteHotel(id);
	}
	public List<HotelMasterModel> showHotels() {
		return hotelRepo.showHotels();
	}
	public boolean isAddBulkCities() {
		return hotelRepo.isAddBulkCities();
	}
	public boolean isAddBulkAreas() {
		return hotelRepo.isAddBulkAreas();
	}
	public boolean isAddBulkHotels() {
		return hotelRepo.isAddBulkHotels();
	}

	
//	public boolean isAddAmenity(HotelMasterModel model) throws IOException, SQLException{
//		boolean b= hotelRepo.isAddAmenity(model);
//		return b;
//	}
}
