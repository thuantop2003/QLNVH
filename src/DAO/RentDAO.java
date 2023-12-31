package DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.JDBCUtil;
import model.Rent;



public class RentDAO implements DAOInterface<Rent>  {
	public static RentDAO getInstance() {
		return new RentDAO();
	}

	@Override
	public int insert(Rent t) {
		
		Connection connection = JDBCUtil.getConnection();
		String sql= "INSERT INTO rent (activityid,deviceid,roomid,roomamount,deviceamount,renterid)"
				+ "VALUES(?,?,?,?,?,?)";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			
			pst.setInt(1,t.getActivityid());
			pst.setInt(2, t.getDeviceid());
			pst.setInt(3, t.getRoomid());
			pst.setInt(4, t.getRoomAmount());
			pst.setInt(5,t.getDeviceAmount());
			pst.setString(6, t.getRenterid());
			int ketqua =pst.executeUpdate();
			System.out.println(ketqua);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JDBCUtil.CloseConnection(connection);
		return 0;
	}

	@Override
	public int update(Rent t) {
		Connection connection = JDBCUtil.getConnection();
		String sql= "UPDATE rent"
				+" SET activityid= ?, deviceid=?, roomid=?, roomamount=?, deviceamount=?, renterid=?"
				+" WHERE rentid=?";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			
			pst.setInt(1,t.getActivityid());
			pst.setInt(2, t.getDeviceid());
			pst.setInt(3, t.getRoomid());
			pst.setInt(4, t.getRoomAmount());
			pst.setInt(5,t.getDeviceAmount());
			pst.setString(6, t.getRenterid());
			pst.setInt(7, t.getRentid());
			int ketqua =pst.executeUpdate();
			System.out.println(ketqua);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JDBCUtil.CloseConnection(connection);
		return 0;
	}

	@Override
	public int delete(Rent t) {
		Connection connection = JDBCUtil.getConnection();
		
		String sql= "DELETE from rent "
				+" WHERE rentid=?";
		System.out.println(sql);
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1,t.getRentid());
			int ketqua =pst.executeUpdate();
			System.out.println(ketqua);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		JDBCUtil.CloseConnection(connection);
		return 0;
	}

	@Override
	public ArrayList<Rent> selectAll() {
		ArrayList<Rent> ketQua = new ArrayList<Rent>();
		Connection connection = JDBCUtil.getConnection();
		String sql= "SELECT rentid, renter.name as rentername, room.name as roomname, devicename, roomamount, deviceamount,activityname FROM rent, renter, room, device,activity "
				+ "WHERE rent.renterid= renter.renterid and rent.deviceid=device.deviceid and rent.roomid=room.roomid and rent.activityid=activity.activityid";
		System.out.println(sql);
		try {
			ResultSet rs = connection.createStatement().executeQuery(sql);
			while (rs.next()) {
				int rentid = rs.getInt("rentid");
				String rentername = rs.getString("rentername");
				String devicename = rs.getString("devicename");
				int deviceamount = rs.getInt("deviceamount");
				String roomname = rs.getString("roomname");
				int roomamount= rs.getInt("roomamount");
				String activityname = rs.getString("activityname");
				Rent a = new Rent(rentid, roomname, roomamount, devicename, deviceamount, activityname, rentername);
				ketQua.add(a);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JDBCUtil.CloseConnection(connection);
		// TODO Auto-generated method stub
		return ketQua;
	}

	@Override
	public Rent selectByID(String t) {
		// TODO Auto-generated method stub
		return null;
	}
	public Rent selectByID(int t) {
		Rent ketQua = new Rent();
		Connection connection = JDBCUtil.getConnection();
		String sql= "SELECT rentid, renter.name as rentername, room.name as roomname, devicename, roomamount, deviceamount,activity.name as activityname FROM rent, renter, room, device,activity "
				+ "WHERE rent.renterid= renter.renterid and rent.deviceid=device.deviceid and rent.roomid=room.roomid and rent.activityid=activity.activityid and rentid=?";
		System.out.println(sql);
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1,t);
			ResultSet rs =pst.executeQuery();
			while(rs.next()) {
				int rentid = rs.getInt("rentid");
				String rentername = rs.getString("rentername");
				String devicename = rs.getString("devicename");
				int deviceamount = rs.getInt("deviceamount");
				String roomname = rs.getString("roomname");
				int roomamount= rs.getInt("roomamount");
				String activityname = rs.getString("activityname");
				Rent a = new Rent(rentid, roomname, roomamount, devicename, deviceamount, activityname, rentername);
				ketQua = a;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JDBCUtil.CloseConnection(connection);
		// TODO Auto-generated method stub
		return ketQua;

	}

	@Override
	public Rent selectByName(String t) {
		Rent ketQua = new Rent();
		Connection connection = JDBCUtil.getConnection();
		String sql= "SELECT rentid, renter.name as rentername, room.name as roomname, devicename, roomamount, deviceamount,activityname FROM rent, renter, room, device,activity "
				+ "WHERE rent.renterid= renter.renterid and rent.deviceid=device.deviceid and rent.roomid=room.roomid and rent.activityid=activity.activityid and rentername=?";
		System.out.println(sql);
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1,t);
			ResultSet rs =pst.executeQuery();
			while (rs.next()) {
				int rentid = rs.getInt("rentid");
				String rentername = rs.getString("rentername");
				String devicename = rs.getString("devicename");
				int deviceamount = rs.getInt("deviceamount");
				String roomname = rs.getString("roomname");
				int roomamount= rs.getInt("roomamount");
				String activityname = rs.getString("activityname");
				Rent a = new Rent(rentid, roomname, roomamount, devicename, deviceamount, activityname, rentername);
				ketQua = a;
			} 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JDBCUtil.CloseConnection(connection);
		// TODO Auto-generated method stub
		return ketQua;
	}
	public int Rentcost(Rent t) {
		int cost = 0;
		Connection connection = JDBCUtil.getConnection();
		String sql= "SELECT roomamount, deviceamount, room.price as roomprice, device.price as deviceprice, TIMESTAMPDIFF(HOUR ,timestart, timefinish) as renthour FROM rent, room, device,activity "
				+ "WHERE   rent.deviceid=device.deviceid and rent.roomid=room.roomid and rent.activityid=activity.activityid and rentid=?";
		System.out.println(sql);
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1,t.getRentid());
			ResultSet rs =pst.executeQuery();
			while (rs.next()) {
				int deviceprice = rs.getInt("deviceprice");
				int deviceamount = rs.getInt("deviceamount");
				int roomprice = rs.getInt("roomprice");
				int roomamount= rs.getInt("roomamount");
				int renthour = rs.getInt("renthour");
				cost = (deviceprice*deviceamount + roomprice*roomamount)*renthour;
				
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JDBCUtil.CloseConnection(connection);
		return cost;
	}
}
