package repository;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import config.DBHelper;
import config.PathHelper;
import model.GuestMasterModel;
import model.HotelMasterModel;

public class HotelMasterRepository extends DBHelper{
	public String login() {
		return login;
	}
	
	/**************************************************CITY***********************************************************/
	public int getCityId(String name) {
		try {
			pstmt= conn.prepareStatement("select city_id from citymaster where city_name=?");
			pstmt.setString(1, name);
			rs=pstmt.executeQuery();
			if(rs.next()) 
				return rs.getInt(1);
			else
				return -1;
		}
		catch(Exception ex) {
			System.out.println("Error: "+ex);
			return -1;
		}
	}
	
	public boolean isAddCity(HotelMasterModel model) {
		try {
			pstmt= conn.prepareStatement("insert into citymaster values('0',?)");
			pstmt.setString(1, model.getCity_name());
			return pstmt.executeUpdate()>0?true:false;
		}
		catch(Exception ex) {
			System.out.println("Error is "+ex);
			return false;
		}
	}
	
	public boolean isAddBulkCities() {
		try {
			FileReader fr= new FileReader(PathHelper.path+"city.csv");
			BufferedReader br= new BufferedReader(fr);
			String line= null;
			int value= 0;
			while((line= br.readLine())!=null) {
				String data[]= line.split(",");
				pstmt= conn.prepareStatement("insert into citymaster values('0',?)");
				pstmt.setString(1, data[1]);
				value= pstmt.executeUpdate();
			}
			return value>0?true:false;
		}
		catch(Exception ex) {
			System.out.println("Exception "+ex);
			return false;
		}
	}
	
	public List<HotelMasterModel> showCities(){
		List<HotelMasterModel> list= new ArrayList<HotelMasterModel>();
		try {
			pstmt= conn.prepareStatement("select *from citymaster order by city_id");
			rs= pstmt.executeQuery();
			while(rs.next()) {
				HotelMasterModel model= new HotelMasterModel();
				model.setCity_id(rs.getInt(1));
				model.setCity_name(rs.getString(2));
				list.add(model);
			}
			return list.size()>0?list:null;
		}
		catch(Exception ex) {
			System.out.println("Error "+ex);
			return null;
		}
	}
	
	/***************************************************AREA***********************************************************/
	public int getAreaId(String aname) {
		try {
			pstmt= conn.prepareStatement("select area_id from areamaster where area_name=?");
			pstmt.setString(1, aname);
			rs=pstmt.executeQuery();
			if(rs.next()) 
				return rs.getInt(1);
			else
				return -1;
		}
		catch(Exception ex) {
			System.out.println("Error: "+ex);
			return -1;
		}
	}
	
	public boolean isAddArea(HotelMasterModel model) {
		try {
			pstmt= conn.prepareStatement("insert into areamaster values('0',?,?)");
			pstmt.setString(1, model.getArea_name());
			pstmt.setInt(2, model.getCity_id());
			return pstmt.executeUpdate()>0?true:false;
		}
		catch(Exception ex) {
			System.out.println("Error is "+ex);
			return false;
		}
	}
	
	public boolean isAddBulkAreas() {
		try {
			FileReader fr= new FileReader(PathHelper.path+"area.csv");
			BufferedReader br= new BufferedReader(fr);
			String line= null;
			int value= 0;
			while((line= br.readLine())!=null) {
				String data[]= line.split(",");
				pstmt= conn.prepareStatement("insert into areamaster values('0',?,?)");
				pstmt.setString(1, data[1]);
				pstmt.setInt(2, Integer.valueOf(data[2]));
				value= pstmt.executeUpdate();
			}
			return value>0?true:false;
		}
		catch(Exception ex) {
			System.out.println("Exception "+ex);
			return false;
		}
	}
	
	public List<HotelMasterModel> showAreas(){
		List<HotelMasterModel> list= new ArrayList<HotelMasterModel>();
		try {
			pstmt= conn.prepareStatement("select a.area_id, a.area_name, c.city_name from areamaster a left join citymaster c on a.city_id= c.city_id");
			rs= pstmt.executeQuery();
			while(rs.next()) {
				HotelMasterModel model= new HotelMasterModel();
				model.setArea_id(rs.getInt(1));
				model.setArea_name(rs.getString(2));
				model.setCity_name(rs.getString(3));
				list.add(model);
			}
			return list.size()>0?list:null;
		}
		catch(Exception ex) {
			System.out.println("Error "+ex);
			return null;
		}
	}
	
	/*************************************************HOTEL***********************************************************/
	public int getHotelId(String name) {
		try {
			pstmt= conn.prepareStatement("select hotel_id from hotelmaster where hotel_name=?");
			pstmt.setString(1, name);
			rs=pstmt.executeQuery();
			if(rs.next()) 
				return rs.getInt(1);
			else
				return -1;
		}
		catch(Exception ex) {
			System.out.println("Error: "+ex);
			return -1;
		}
	}
	
	public boolean isAddHotel(HotelMasterModel model) {
		try {
			pstmt= conn.prepareStatement("insert into hotelmaster values('0',?, ?, ?, ?)");
			pstmt.setString(1, model.getHotel_name());
			pstmt.setString(2, model.getAddress());
			pstmt.setInt(3, model.getArea_id());
			pstmt.setInt(4, model.getPrice());
			return pstmt.executeUpdate()>0?true:false;
		}
		catch(Exception ex) {
			System.out.println("Error is "+ex);
			return false;
		}
	}
	
	public boolean isAddBulkHotels() {
		try {
			FileReader fr= new FileReader(PathHelper.path+"hotel.csv");
			BufferedReader br= new BufferedReader(fr);
			String line= null;
			int value= 0;
			while((line= br.readLine())!=null) {
				String data[]= line.split(",");
				pstmt= conn.prepareStatement("insert into hotelmaster values('0', ?, ?, ?, ?)");
				pstmt.setString(1, data[1]);
				pstmt.setString(2, data[2]);
				pstmt.setInt(3, Integer.valueOf(data[3]));
				pstmt.setInt(4, Integer.valueOf(data[4]));
				value= pstmt.executeUpdate();
			}
			return value>0?true:false;
		}
		catch(Exception ex) {
			System.out.println("Exception "+ex);
			return false;
		}
	}	
	
	public List<HotelMasterModel> showHotels(){
		List<HotelMasterModel> list= new ArrayList<HotelMasterModel>();
		try {
			pstmt= conn.prepareStatement("select h.hotel_id, h.hotel_name, h.address, a.area_name, c.city_name, h.price from hotelmaster h left join areamaster a on h.area_id= a.area_id left join citymaster c on a.city_id= c.city_id");
			rs= pstmt.executeQuery();
			while(rs.next()) {
				HotelMasterModel model= new HotelMasterModel();
				model.setHotel_id(rs.getInt(1));
				model.setHotel_name(rs.getString(2));
				model.setAddress(rs.getString(3));
				model.setArea_name(rs.getString(4));
				model.setCity_name(rs.getString(5));
				model.setPrice(rs.getInt(6));
				list.add(model);
			}
			return list.size()>0?list:null;
		}
		catch(Exception ex) {
			System.out.println("Error "+ex);
			return null;
		}
	}
	public boolean updateHotel(String new_name, int id) {
		try {
			pstmt= conn.prepareStatement("update hotelmaster set hotel_name=? where hotel_id=?");
			pstmt.setString(1, new_name);
			pstmt.setInt(2, id);
			return pstmt.executeUpdate()>0?true:false;
		}
		catch(Exception ex) {
			System.out.println("Error is: "+ex);
			return false;
		}
	}
	
	public boolean updateAddress(String address, int id) {
		try {
			pstmt= conn.prepareStatement("update hotelmaster set address=? where hotel_id=?");
			pstmt.setString(1, address);
			pstmt.setInt(2, id);
			return pstmt.executeUpdate()>0?true:false;
		}
		catch(Exception ex) {
			System.out.println("Error is: "+ex);
			return false;
		}
	}
	
	public boolean updatePrice(int price, int id) {
		try {
			pstmt= conn.prepareStatement("update hotelmaster set price=? where hotel_id=?");
			pstmt.setInt(1, price);
			pstmt.setInt(2, id);
			return pstmt.executeUpdate()>0?true:false;
		}
		catch(Exception ex) {
			System.out.println("Error is: "+ex);
			return false;
		}
	}
	
	public boolean deleteHotel(int id){
		try {
			pstmt= conn.prepareStatement("delete from hotelmaster where hotel_id=?");
			pstmt.setInt(1, id);
			return pstmt.executeUpdate()>0?true:false;
		}
		catch(Exception ex) {
			System.out.println("Error is: "+ex);
			return false;
		}
	}
		
	/*************************************************AMENITY***********************************************************/
//	public boolean isAddAmenity(HotelMasterModel model) {
//		try {
//			pstmt= conn.prepareStatement("insert into amenity values('0',?)");
//			pstmt.setString(1, model.getAmenity());
//			return pstmt.executeUpdate()>0?true:false;
//		}
//		catch(Exception ex) {
//			System.out.println("Error is "+ex);
//			return false;
//		}
//	}
}
