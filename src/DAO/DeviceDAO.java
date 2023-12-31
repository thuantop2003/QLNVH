package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.JDBCUtil;
import model.Device;

public class DeviceDAO implements DAOInterface<Device> {
	public static DeviceDAO getInstance() {
		return new DeviceDAO();
	}

	@Override
	public int insert(Device t) {
		Connection connection = JDBCUtil.getConnection();
		
		String sql= "INSERT INTO device (devicename,amount,price,status,roomid,note)"
				+ "VALUES(?,?,?,?,?,?)";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			
			pst.setString(1, t.getName());
			pst.setInt(2, t.getAmount());
			pst.setInt(3, t.getPrice());
			pst.setString(4, t.getStatus());
			pst.setInt(5, t.getRoomId());
			pst.setString(6, t.getNote());
			int ketqua =pst.executeUpdate();
			System.out.println(ketqua);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JDBCUtil.CloseConnection(connection);
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Device t) {
		Connection connection = JDBCUtil.getConnection();
		String sql= "UPDATE device"
				+ "SET devicename= ?, amount= ?, price= ?, status= ?, roomid= ?, note= ?"
				+ "WHERE deviceid=?";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			
			pst.setString(1, t.getName());
			pst.setInt(2, t.getAmount());
			pst.setInt(3, t.getPrice());
			pst.setString(4, t.getStatus());
			pst.setInt(5, t.getRoomId());
			pst.setString(6, t.getNote());
			pst.setInt(7, t.getDeviceId());
			int ketqua =pst.executeUpdate();
			System.out.println(ketqua);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JDBCUtil.CloseConnection(connection);
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Device t) {
		Connection connection = JDBCUtil.getConnection();
		
		String sql= "DELETE from device "
				+" WHERE devicename= ?";
		System.out.println(sql);
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1,t.getName());
			int ketqua =pst.executeUpdate();
			System.out.println(ketqua);
			return 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JDBCUtil.CloseConnection(connection);	
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Device> selectAll() {
		ArrayList<Device> ketQua = new ArrayList<>();
		Connection connection = JDBCUtil.getConnection();
		String sql= "SELECT * FROM device ";
		System.out.println(sql);
		try {
			ResultSet rs = connection.createStatement().executeQuery(sql);
			while (rs.next()) {
				String devicename = rs.getString("devicename");
				int amount = rs.getInt("amount");
				int price = rs.getInt("price");
				String status= rs.getString("status");
				String note = rs.getString("note");
				int roomId= rs.getInt("roomId");
				int deviceid = rs.getInt("deviceid");
				Device a = new Device(devicename, amount, price, status, roomId, note, deviceid);
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

	public Device selectByID(int t) {
		Device aa = new Device() ;
		Connection connection = JDBCUtil.getConnection();
		String sql= "SELECT * FROM device where deviceid= ? ";
		System.out.println(sql);
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1,t);
			ResultSet rs =pst.executeQuery();
			while (rs.next()) {
				String devicename = rs.getString("devicename");
				int amount = rs.getInt("amount");
				int price = rs.getInt("price");
				String status= rs.getString("status");
				String note = rs.getString("note");
				int roomId= rs.getInt("roomId");
				int deviceid = rs.getInt("deviceid");
				Device a = new Device(devicename, amount, price, status, roomId, note, deviceid);
				aa= a;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JDBCUtil.CloseConnection(connection);
		// TODO Auto-generated method stub
		return aa;
	}

	@Override
	public Device selectByName(String t) {
		Device aa = new Device() ;
		Connection connection = JDBCUtil.getConnection();
		String sql= "SELECT * FROM device where devicename = ?  ";
		System.out.println(sql);
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1,t);
			ResultSet rs =pst.executeQuery();
			while (rs.next()) {
				String devicename = rs.getString("devicename");
				int amount = rs.getInt("amount");
				int price = rs.getInt("price");
				String status= rs.getString("status");
				String note = rs.getString("note");
				int roomId= rs.getInt("roomId");
				int deviceid = rs.getInt("deviceid");
				Device a = new Device(devicename, amount, price, status, roomId, note, deviceid);
				aa= a;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JDBCUtil.CloseConnection(connection);
		// TODO Auto-generated method stub
		return aa;
		
	}

	@Override
	public Device selectByID(String t) {
		// TODO Auto-generated method stub
		return null;
	}
	public ArrayList<Device> selectByStatus(String t) {
		ArrayList<Device> aa = new ArrayList<>() ;
		Connection connection = JDBCUtil.getConnection();
		String sql= "SELECT * FROM device where status = ? ";
		System.out.println(sql);
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1,t);
			ResultSet rs =pst.executeQuery();
			while (rs.next()) {
				String devicename = rs.getString("devicename");
				int amount = rs.getInt("amount");
				int price = rs.getInt("price");
				String status= rs.getString("status");
				String note = rs.getString("note");
				int roomId= rs.getInt("roomId");
				int deviceid = rs.getInt("deviceid");
				Device a = new Device(devicename, amount, price, status, roomId, note, deviceid);
				aa.add(a);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JDBCUtil.CloseConnection(connection);
		// TODO Auto-generated method stub
		return aa;
		
	}
}	