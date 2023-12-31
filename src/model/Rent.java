package model;


import java.util.ArrayList;

import DAO.DeviceDAO;
import DAO.RentDAO;

public class Rent {
	private int rentid;
    private int activityid;
    private int deviceid;
    private int roomid;
    private int roomAmount;
    private int deviceAmount;
    private String renterid;
    private String rentername;
    private String activityname;
    private String roomname;
    private String devicename;
    
    
	public String getRentername() {
		return rentername;
	}


	public void setRentername(String rentername) {
		this.rentername = rentername;
	}


	public String getActivityname() {
		return activityname;
	}


	public void setActivityname(String activityname) {
		this.activityname = activityname;
	}


	public String getRoomname() {
		return roomname;
	}


	public void setRoomname(String roomname) {
		this.roomname = roomname;
	}


	public String getDevicename() {
		return devicename;
	}


	public void setDevicename(String devicename) {
		this.devicename = devicename;
	}


	public Rent(int rentid, String roomname, int roomAmount,String devicename, int deviceAmount,String activityname, String rentername) {
		super();
		this.rentid = rentid;
		this.roomAmount = roomAmount;
		this.deviceAmount = deviceAmount;
		this.rentername = rentername;
		this.activityname = activityname;
		this.roomname = roomname;
		this.devicename = devicename;
	}


	public Rent() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Rent(int activityid, int deviceid, int roomid, int roomAmount, int deviceAmount, String renterid) {
		super();
		this.activityid = activityid;
		this.deviceid = deviceid;
		this.roomid = roomid;
		this.roomAmount = roomAmount;
		this.deviceAmount = deviceAmount;
		this.renterid = renterid;
	}



	public Rent(int rentid, int activityid, int deviceid, int roomid, int roomAmount, int deviceAmount) {
		super();
		this.rentid = rentid;
		this.activityid = activityid;
		this.deviceid = deviceid;
		this.roomid = roomid;
		this.roomAmount = roomAmount;
		this.deviceAmount = deviceAmount;
	}


	public Rent(int rentid, int activityid, int deviceid, int roomid, int roomAmount, int deviceAmount,
			String renterid) {
		super();
		this.rentid = rentid;
		this.activityid = activityid;
		this.deviceid = deviceid;
		this.roomid = roomid;
		this.roomAmount = roomAmount;
		this.deviceAmount = deviceAmount;
		this.renterid = renterid;
	}



	public int getRentid() {
		return rentid;
	}



	public void setRentid(int rentid) {
		this.rentid = rentid;
	}



	public int getActivityid() {
		return activityid;
	}



	public void setActivityid(int activityid) {
		this.activityid = activityid;
	}



	public int getDeviceid() {
		return deviceid;
	}



	public void setDeviceid(int deviceid) {
		this.deviceid = deviceid;
	}



	public int getRoomid() {
		return roomid;
	}



	public void setRoomid(int roomid) {
		this.roomid = roomid;
	}


	
	public int getRoomAmount() {
		return roomAmount;
	}



	public void setRoomAmount(int roomAmount) {
		this.roomAmount = roomAmount;
	}



	public int getDeviceAmount() {
		return deviceAmount;
	}


	public void setDeviceAmount(int deviceAmount) {
		this.deviceAmount = deviceAmount;
	}

	public String getRenterid() {
		return renterid;
	}



	public void setRenterid(String renterid) {
		this.renterid = renterid;
	}


	@Override
	public String toString() {
		return "Rent [rentid=" + rentid + ", roomAmount=" + roomAmount + ", deviceAmount=" + deviceAmount
				+ ", rentername=" + rentername + ", activityname=" + activityname + ", roomname=" + roomname
				+ ", devicename=" + devicename + "]";
	}

	public int insert() {
    	int ketQua= RentDAO.getInstance().insert(this);
    	return ketQua;
    }
	public int update() {
    	int ketQua= RentDAO.getInstance().update(this);
    	return ketQua;
    }
	public int delete() {
    	int ketQua= RentDAO.getInstance().delete(this);
    	return ketQua;
    }
	public ArrayList<Rent> searchAllDevice() {
		ArrayList<Rent> a = RentDAO.getInstance().selectAll();
		return a;
	}
	public Rent searchRentByName(String t) {
		Rent a = RentDAO.getInstance().selectByName(t);
		return a;
	}
	public Rent searchRentByID(int t) {
		Rent a = RentDAO.getInstance().selectByID(t);
		return a ;
	}
	public int cost() {
    	int ketQua = RentDAO.getInstance().Rentcost(this);
    	System.out.println(ketQua);
    	return ketQua;
    }



	//ham extraPaymen
	

}