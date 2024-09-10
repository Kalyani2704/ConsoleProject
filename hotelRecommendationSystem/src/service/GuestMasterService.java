package service;
import java.util.List;
import model.GuestMasterModel;
import model.HotelMasterModel;
import repository.GuestMasterRepository;

public class GuestMasterService {
	GuestMasterRepository guestRepo= new GuestMasterRepository();
	public boolean isAddGuest(GuestMasterModel model) {
		boolean b= guestRepo.isAddGuest(model);
		return b;
	}
	public List<GuestMasterModel> showGuests() {
		return guestRepo.showGuests();
	}
	public boolean giveFeedback(String contact, String hotel_name, GuestMasterModel m) {
		boolean b= guestRepo.giveFeedback(contact, hotel_name, m);
		return b;
	}
	public List<HotelMasterModel> getHotels(String city_name) {
		return guestRepo.getHotels(city_name);
	}
	public float calcProbability(String name) {
		return guestRepo.calcProbability(name);
	}
	public List<GuestMasterModel> viewReviews() {
		return guestRepo.viewReviews();
	}
	public int getId(String contact) {
		return guestRepo.getId(contact);
	}
}
