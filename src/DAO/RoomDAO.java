package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.JDBCUtil;

import model.Room;

public class RoomDAO implements DAOInterface<Room> {
	public static RoomDAO getInstance() {
		return new RoomDAO();
	}
	

	@Override
	public int insert(Room t) {
		Connection connection = JDBCUtil.getConnection();
		
		String sql= "INSERT INTO room (name,capacity,price,status,note)"
				+ "VALUES(?,?,?,?,?)";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			
			pst.setString(1, t.getName());
			pst.setInt(2, t.getCapacity());
			pst.setInt(3, t.getPrice());
			pst.setString(4, t.getStatus());
			pst.setString(5, t.getNote());
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
	public int update(Room t) {
		Connection connection = JDBCUtil.getConnection();
		String sql= "UPDATE room"
				+ "SET name= ?, capacity= ?, price= ?, status= ?, note= ?"
				+ "WHERE roomid=?";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			
			pst.setString(1, t.getName());
			pst.setInt(2, t.getCapacity());
			pst.setInt(3, t.getPrice());
			pst.setString(4, t.getStatus());
			pst.setString(5, t.getNote());
			pst.setInt(6, t.getRoomId());
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
	public int delete(Room t) {
		Connection connection = JDBCUtil.getConnection();
		
		String sql= "DELETE from room "
				+" WHERE roomid= ?";
		System.out.println(sql);
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1,t.getRoomId());
			int ketqua =pst.executeUpdate();
			System.out.println(ketqua);
			return 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JDBCUtil.CloseConnection(connection);
		return 0;
	}

	@Override
	public ArrayList<Room> selectAll() {
		ArrayList<Room> ketQua = new ArrayList<>();
		Connection connection = JDBCUtil.getConnection();
		String sql= "SELECT * FROM room ";
		System.out.println(sql);
		try {
			ResultSet rs = connection.createStatement().executeQuery(sql);
			while (rs.next()) {
				String name = rs.getString("name");
				int capacity = rs.getInt("capacity");
				int price = rs.getInt("price");
				String status= rs.getString("status");
				String note = rs.getString("note");
				int roomId= rs.getInt("roomid");
				Room a = new Room(roomId,name, capacity, price, status, note);
				ketQua.add(a);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JDBCUtil.CloseConnection(connection);
		return ketQua;
	}


	
	public Room selectByID(int t) {
		Room ketQua = new Room();
		Connection connection = JDBCUtil.getConnection();
		String sql= "SELECT * FROM room where roomid =  ?";
		System.out.println(sql);
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1,t);
			ResultSet rs =pst.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				int capacity = rs.getInt("capacity");
				int price = rs.getInt("price");
				String status= rs.getString("status");
				String note = rs.getString("note");
				int roomid= rs.getInt("roomid");
		
				Room a = new Room(roomid, name, capacity, price, status, note);
				ketQua =a;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JDBCUtil.CloseConnection(connection);
		return ketQua;
	}

	@Override
	public Room selectByName(String t) {
		Room ketQua = new Room();
		Connection connection = JDBCUtil.getConnection();
		String sql= "SELECT * FROM room where name = ? ";
		System.out.println(sql);
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1,t);
			ResultSet rs =pst.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				int capacity = rs.getInt("capacity");
				int price = rs.getInt("price");
				String status= rs.getString("status");
				String note = rs.getString("note");
				int roomid= rs.getInt("roomid");
		
				Room a = new Room(roomid, name, capacity, price, status, note);
				ketQua =a;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JDBCUtil.CloseConnection(connection);
		return ketQua;
	}

	public ArrayList<Room> selectByStatus(String t) {
		ArrayList<Room> ketQua = new ArrayList<>() ;
		Connection connection = JDBCUtil.getConnection();
		String sql= "SELECT * FROM room where status = ?  ";
		System.out.println(sql);
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1,t);
			ResultSet rs =pst.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				int capacity = rs.getInt("capacity");
				int price = rs.getInt("price");
				String status= rs.getString("status");
				String note = rs.getString("note");
				int roomid= rs.getInt("roomId");
				Room a = new Room(roomid, name,capacity, price, status, note);
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
	public Room selectByID(String t) {
		// TODO Auto-generated method stub
		return null;
	}

}
