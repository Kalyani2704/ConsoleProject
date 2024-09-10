package client;

import java.util.*;
import model.GuestMasterModel;
import model.HotelMasterModel;
import service.GuestMasterService;
import service.HotelMasterService;

public class ClientApp {
	public static void main(String[] args) {
		GuestMasterService gms = new GuestMasterService();
		HotelMasterService hms = new HotelMasterService();
		Scanner xyz = new Scanner(System.in);
		do {
			System.out.println("A. Admin");
			System.out.println("B. User");
			System.out.println("\nEnter choice: ");
			char choice = xyz.next().charAt(0);
			String name = null;
			boolean b = false;
			switch (choice) {
			case 'A':
				System.out.println("Enter username & password: ");
				String un = xyz.next();
				String pass = xyz.next();
				un = un.concat(pass);
				if (un.equals(hms.login())) {
					System.out.println("Welcome admin!");
					do {
						System.out.println("\n1. Manage city & area");
						System.out.println("2. Manage hotel");
						System.out.println("3. Manage customers");
//						System.out.println("4. Manage amenity");
						System.out.println("\nEnter choice: ");
						int choice2 = xyz.nextInt();
						HotelMasterModel model = new HotelMasterModel();
						switch (choice2) {
						case 1:
							System.out.println("a. add city/area");
							System.out.println("b. view city");
							System.out.println("c. view area");
							System.out.println("Enter choice:");
							switch (xyz.next().charAt(0)) {
							case 'a':
								System.out.println("Enter city name: ");
								xyz.nextLine();
								name = xyz.nextLine();
								int id = hms.getCityId(name);
								if (id != -1) {
									System.out.println("City already present");
									System.out.println("\nEnter area name: ");
									String aname = xyz.nextLine();
									int aid = hms.getAreaId(aname);
									if (aid != -1) {
										System.out.println("Area already present");
									} else {
										model.setArea_name(aname);
										model.setCity_id(id);
										b = hms.isAddArea(model);
//										b= hms.isAddBulkAreas();
										if (b) {
											System.out.println("Area added");
										} else {
											System.out.println("Unable to add data");
										}
									}
								} else {
									model.setCity_name(name);
									b = hms.isAddCity(model);
//									b = hms.isAddBulkCities();
									id = hms.getCityId(name);
									if (b) {
										System.out.println("City added");
										System.out.println("\nEnter area name: ");
										String aname = xyz.nextLine();
										int aid = hms.getAreaId(aname);
										if (aid != -1) {
											System.out.println("Area already present");
										} else {
											model.setArea_name(aname);
											model.setCity_id(id);
											b = hms.isAddArea(model);
//											b= hms.isAddBulkAreas();
											if (b) {
												System.out.println("Area added");
											} else {
												System.out.println("Unable to add data");
											}
										}
									} else {
										System.out.println("Unable to add data");
									}
								}
//								System.out.println("Enter city name: ");
//								xyz.nextLine();
//								name = xyz.nextLine();
//								int id = hms.getCityId(name);
//								if (id != -1) {
//									System.out.println("City already present");
//								} else {
//									model.setCity_name(name);
//									b = hms.isAddCity(model);
////									b = hms.isAddBulkCities();
//									if (b) {
//										System.out.println("City added");
//									} else {
//										System.out.println("Unable to add data");
//									}
//								}
								break;

							case 'b':
								List<HotelMasterModel> list = hms.showCities();
								if (list != null) {
									list.forEach((m) -> System.out.println(m.getCity_id() + "\t" + m.getCity_name()));
								} else {
									System.out.println("No data");
								}
								break;

							case 'c':
								list = hms.showAreas();
								if (list != null) {
									list.forEach((m) -> System.out.println(
											m.getArea_id() + "\t" + m.getArea_name() + "\t\t" + m.getCity_name()));
								} else {
									System.out.println("No data");
								}
								break;

							default:
								System.out.println("wrong choice");
							}
							break;

						case 2:
							System.out.println("a. add hotel");
							System.out.println("b. view hotels");
							System.out.println("c. update hotel");
							System.out.println("d. delete hotel");
							System.out.println("Enter choice:");
							switch (xyz.next().charAt(0)) {
							case 'a':
								System.out.println("Hotel name: ");
								xyz.nextLine();
								name = xyz.nextLine();
								int hid = hms.getHotelId(name);
								if (hid != -1) {
									System.out.println("Hotel already registered");
								} else {
									model.setHotel_name(name);
									System.out.println("Address: ");
									model.setAddress(xyz.nextLine());
									System.out.println("Area: ");
									String area = xyz.nextLine();
									model.setArea_id(hms.getAreaId(area));
									System.out.println("Price: ");
									model.setPrice(xyz.nextInt());
									b = hms.isAddHotel(model);
//									b = hms.isAddBulkHotels();
									if (b) {
										System.out.println("Hotel added");
									} else {
										System.out.println("Unable to add data");
									}
								}
								break;

							case 'b':
								List<HotelMasterModel> list;
								list = hms.showHotels();
								if (list != null) {
									list.forEach((m) -> System.out.println(m.getHotel_id() + "\t" + m.getHotel_name()
											+ "\t\t" + m.getAddress() + "\t\t" + m.getArea_name() + "\t\t"
											+ m.getCity_name() + "\t\t" + m.getPrice()));
								} else {
									System.out.println("No data");
								}
								break;

							case 'c':
								System.out.println("\nEnter hotel name: ");
								xyz.nextLine();
								int id = hms.getHotelId(xyz.nextLine());
								System.out.println("1. update hotel");
								System.out.println("2. update address");
								System.out.println("3. update price");
								System.out.println("Enter choice");
								switch (xyz.nextInt()) {
								case 1:
									System.out.println("Enter new hotel name: ");
									xyz.nextLine();
									if (hms.updateHotel(xyz.nextLine(), id))
										System.out.println("Record updated");
									else
										System.out.println("Some problem occured");
									break;

								case 2:
									System.out.println("Enter new address: ");
									xyz.nextLine();
									if (hms.updateAddress(xyz.nextLine(), id))
										System.out.println("Record updated");
									else
										System.out.println("Some problem occured");
									break;

								case 3:
									System.out.println("Enter new price: ");
									if (hms.updatePrice(xyz.nextInt(), id))
										System.out.println("Record updated");
									else
										System.out.println("Some problem occured");
									break;

								default:
									System.out.println("Wrong choice");
								}
								break;

							case 'd':
								System.out.println("Enter hotel name to be deleted: ");
								xyz.nextLine();
								if (hms.deleteHotel(hms.getHotelId(xyz.nextLine())))
									System.out.println("Record deleted");
								else
									System.out.println("Some problem occured");
								break;

							default:
								System.out.println("wrong choice");
							}
							break;

						case 3:
							System.out.println("a. view guests");
							System.out.println("b. view reviews");
							System.out.println("Enter choice:");
							switch (xyz.next().charAt(0)) {
							case 'a':
								List<GuestMasterModel> list = gms.showGuests();
								if (list != null) {
									list.forEach((m) -> System.out.println(m.getGuest_id() + "\t" + m.getGuest_fname()
											+ "\t" + m.getGuest_lname() + "\t" + m.getContact() + "\t" + m.getEmail()));
								} else {
									System.out.println("No data");
								}
								break;

							case 'b':
								list = gms.viewReviews();
								if (list != null) {
									list.forEach((m) -> System.out.println(m.getFeedback_id() + "\t" + m.getGuest_id()
											+ "\t" + m.getHotel_id() + "\t" + m.getRating() + "\t" + m.getReview()));
								} else {
									System.out.println("No data");
								}
								break;
							}
							break;

//						case 4:
//							System.out.println("a. add amenity");
//							System.out.println("b. view amenities");
//							System.out.println("c. update amenity");
//							System.out.println("d. delete amenity");
//							System.out.println("Enter choice:");
//							switch (xyz.next().charAt(0)) {
//							case 'a':
//								System.out.println("Amenity: ");
//								xyz.nextLine();
//								name = xyz.nextLine();
//								model.setAmenity(name);
//								try {
//									b = hms.isAddAmenity(model);
//									if (b) {
//										System.out.println("Amenity added");
//									} else {
//										System.out.println("Unable to add data");
//									}
//								} catch (Exception e) {
//									e.printStackTrace();
//								}
//								break;
//
//							}
//							break;							

						default:
							System.out.println("Wrong choice");
						}
					} while (true);
				} else {
					System.out.println("Invalid login credentials");
				}
				break;

			case 'B':
				if (choice == 'B') {
					do {
						System.out.println("\n1. Add new guest");
						System.out.println("2. View & book hotel");
						System.out.println("3. Give review");
						System.out.println("\nEnter choice: ");
						switch (xyz.nextInt()) {
						case 1:
							System.out.println("First name: ");
							String guest_fname = xyz.next();
							System.out.println("Last name: ");
							String guest_lname = xyz.next();
							System.out.println("Contact: ");
							String contact = xyz.next();
							int id = (gms.getId(contact));
							if (id != -1) {
								System.out.println("Contact already registered");
								System.out.println("Welcome " + guest_fname + "!");
							} else {
								GuestMasterModel model = new GuestMasterModel();
								model.setGuest_fname(guest_fname);
								model.setGuest_lname(guest_lname);
								model.setContact(contact);
								System.out.println("Email: ");
								String email = xyz.next();
								model.setEmail(email);
								b = gms.isAddGuest(model);
								if (b)
									System.out.println("Welcome " + guest_fname + "!");
								else
									System.out.println("Some problem occured, please try again after sometime");
							}
							break;

						case 2:
							System.out.println("Enter city: ");
							xyz.nextLine();
							String city_name = xyz.nextLine();
							List<HotelMasterModel> list = gms.getHotels(city_name);
							if (list != null) {
								list.forEach((m) -> System.out.println(m.getHotel_name() + "\t" + m.getAddress() + "\t"
										+ m.getCity_name() + "\t" + m.getRoom_type() + "\t" + m.getPrice()));
							} else {
								System.out.println("No data");
							}
							break;

						case 3:
							GuestMasterModel model = new GuestMasterModel();
							System.out.println("Contact");
							contact = xyz.next();
							id = (gms.getId(contact));
							if (id != -1) {
								System.out.println("Enter hotel name");
								xyz.nextLine();
								String hotel_name = xyz.nextLine();
								System.out.println("Enter rating between 0-10");
								int rating = xyz.nextInt();
								model.setRating(rating);
								System.out.println("Enter review");
								xyz.nextLine();
								String review = xyz.nextLine();
								model.setReview(review);
								b = gms.giveFeedback(contact, hotel_name, model);
								if (b) {
									System.out.println("Thank You!");
								} else {
									System.out.println("Some problem occured, please try again after sometime");
								}
							} else {
								System.out.println("Contact not registered");
								System.out.println("Would you like to sign-up (yes or no):");
								String msg = xyz.next();
								if (msg.equals("yes")) {
									System.out.println("First name: ");
									guest_fname = xyz.next();
									model.setGuest_fname(guest_fname);
									System.out.println("Last name: ");
									guest_lname = xyz.next();
									model.setGuest_lname(guest_lname);
									model.setContact(contact);
									System.out.println("Email: ");
									String email = xyz.next();
									model.setEmail(email);
									b = gms.isAddGuest(model);
									if (b) {
										System.out.println("Welcome " + guest_fname + "!");
										System.out.println("Enter hotel name");
										xyz.nextLine();
										String hotel_name = xyz.nextLine();
										System.out.println("Enter rating between 0-10");
										int rating = xyz.nextInt();
										model.setRating(rating);
										System.out.println("Enter review");
										xyz.nextLine();
										String review = xyz.nextLine();
										model.setReview(review);
										b = gms.giveFeedback(contact, hotel_name, model);
										if (b)
											System.out.println("Thank You!");
										else
											System.out.println("Some problem occured, please try again after sometime");
									} else
										System.out.println("Some problem occured, please try again after sometime");
								}
							}
							break;

						case 4:
							System.out.println("Enter city: ");
							xyz.nextLine();
							gms.calcProbability(xyz.nextLine());
							break;

						default:
							System.out.println("Wrong choice");
						}
					} while (true);
				}
				break;

			default:
				System.out.println("Wrong choice");
			}
		} while (true);
	}
}
