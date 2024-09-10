package repository;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.*;
import config.DBHelper;
import config.PathHelper;
import model.GuestMasterModel;
import model.HotelMasterModel;

public class GuestMasterRepository extends DBHelper {
	/**************************************************GUEST**********************************************************/
	public int getId(String contact) {
		try {
			pstmt= conn.prepareStatement("select guest_id from guestmaster where contact=?");
			pstmt.setString(1, contact);
			rs=pstmt.executeQuery();
			if(rs.next())
				return rs.getInt(1);
			else
				return -1;
		}
		catch(Exception ex) {
			System.out.println("Error is: "+ex);
			return -1;
		}
	}
	
	public boolean isAddGuest(GuestMasterModel model) {
		try {
			pstmt = conn.prepareStatement("insert into guestmaster values('0',?, ?, ?, ?)");
			pstmt.setString(1, model.getGuest_fname());
			pstmt.setString(2, model.getGuest_lname());
			pstmt.setString(3, model.getContact());
			pstmt.setString(4, model.getEmail());
			return pstmt.executeUpdate() > 0 ? true : false;
		} catch (Exception ex) {
			System.out.println("Error is " + ex);
			return false;
		}
	}
	
	public List<GuestMasterModel> showGuests() {
		List<GuestMasterModel> list = new ArrayList<GuestMasterModel>();
		try {
			pstmt = conn.prepareStatement("select *from guestmaster");
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				GuestMasterModel model = new GuestMasterModel();
				model.setGuest_id(rs.getInt(1));
				model.setGuest_fname(rs.getString(2));
				model.setGuest_lname(rs.getString(3));
				model.setContact(rs.getString(4));
				model.setEmail(rs.getString(5));
				list.add(model);
			}
			return list.size() > 0 ? list : null;
		} catch (Exception ex) {
			System.out.println("Error " + ex);
			return null;
		}
	}

	/**************************************************REVIEW**********************************************************/
	public boolean giveFeedback(String contact, String hotel_name, GuestMasterModel gm) {
		try {
			HotelMasterModel hm = new HotelMasterModel();
			CallableStatement cs= conn.prepareCall("{call giveFeedback(?,?,?,?,?,?)}");
			cs.setString(1, contact);
			cs.setString(2, hotel_name);
			cs.setInt(3, gm.getGuest_id());
			cs.setInt(4, hm.getHotel_id());
			cs.setString(5, gm.getReview());
			cs.setInt(6, gm.getRating());
			return cs.executeUpdate() > 0 ? true : false;
		} catch (Exception ex) {
			System.out.println("Error is " + ex);
			return false;
		}
	}

	public List<GuestMasterModel> viewReviews() {
		List<GuestMasterModel> list = new ArrayList<GuestMasterModel>();
		try {
			pstmt = conn.prepareStatement("select *from feedback");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				GuestMasterModel model = new GuestMasterModel();
				model.setFeedback_id(rs.getInt(1));
				model.setGuest_id(rs.getInt(2));
				model.setHotel_id(rs.getInt(3));
				model.setRating(rs.getInt(4));
				model.setReview(rs.getString(5));
				list.add(model);
			}
			return list.size() > 0 ? list : null;
		} catch (Exception ex) {
			System.out.println("Error " + ex);
			return null;
		}
	}
	
	/*************************************************ALGORITHM***********************************************************/
	public List<HotelMasterModel> getHotels(String city_name) {
		List<HotelMasterModel> list= new ArrayList<HotelMasterModel>();
		try {
			pstmt= conn.prepareStatement("select h.hotel_name, h.address, c.city_name, r.room_type, h.price from room r inner join hotelroomjoin hrj on r.room_id= hrj.room_id inner join hotelmaster h on hrj.hotel_id=h.hotel_id inner join areamaster a on h.area_id=a.area_id inner join citymaster c on a.city_id=c.city_id where city_name=?");
			pstmt.setString(1, city_name);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				HotelMasterModel model= new HotelMasterModel();
				model.setHotel_name(rs.getString(1));
				model.setAddress(rs.getString(2));
				model.setCity_name(rs.getString(3));
				model.setRoom_type(rs.getString(4));
				model.setPrice(rs.getInt(5));
				list.add(model);
			}
			return list.size()>0?list:null;
		}
		catch(Exception ex) {
			System.out.println("Error is "+ex);
		}
		return null;
	}

	public float calcProbability(String city_name) {
		try {
			int positive = 0;
			int negative = 0;
			int total= 27;
//			pstmt = conn.prepareStatement("select reviews from feedback where hotel_id= 10");
			HashMap<Integer,Float> map = new HashMap<Integer,Float>();
			pstmt = conn.prepareStatement("select f.hotel_id, f.reviews from feedback f inner join hotelmaster h on f.hotel_id= h.hotel_id inner join areamaster a on h.area_id= a.area_id inner join citymaster c on a.city_id= c.city_id where city_name=? order by f.hotel_id");
			pstmt.setString(1, city_name);
			rs = pstmt.executeQuery();
			while (rs.next()) {	
				String data[] = (rs.getString(2)).split(" ");
				for (int i = 0; i < data.length; i++) {
					boolean flag= false;
					FileReader frp = new FileReader(PathHelper.path + "positiveReviews.txt");
					BufferedReader br1 = new BufferedReader(frp);
					String line1 = null;
					FileReader frn = new FileReader(PathHelper.path + "negativeReviews.txt");
					BufferedReader br2 = new BufferedReader(frn);
					String line2 = null;
					while (((line2 = br2.readLine())!= null) || ((line1 = br1.readLine())!= null)) {
//						positive=0;
						if (data[i].equalsIgnoreCase(line1)) {
							++positive;
							flag= true;
							break;
	   					}
						else if (data[i].equalsIgnoreCase(line2)) {
							++negative;
							flag= true;
							break;
						}
					}
					map.put(rs.getInt(1), (float)positive/(float)total);
					if(flag) {
						break;
					}
				}
			}
//			System.out.println("+ve= "+positive);
//			System.out.println("-ve= "+negative);
//			System.out.println(total);
//			System.out.println("total= "+total);
			Set<Map.Entry<Integer, Float>> s=map.entrySet();
			for(Map.Entry<Integer, Float> m:s) {
				System.out.println(m.getKey()+"\t"+(m.getValue()));
			}
			return 0;
		} catch (Exception ex) {
			System.out.println("Exception " + ex);
			return 0;
		}
	}

}
