package DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import database.JDBCUtil;
import model.Activity;
import model.Rent;


public class RentDAO implements DAOInterface<Rent>  {
	public static RentDAO getInstance() {
		return new RentDAO();
	}


	public int insert(Rent t) {
		Connection connection = JDBCUtil.getConnection();
		int ketqua = 0;
		String sql= "INSERT INTO rent (rentid, rentname,roomid, timestart, timefinish, renterid, note)"
				+ "VALUES(?,?,?,?,?,?,?)";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			
			pst.setInt(1, t.getRentid());
			pst.setString(2, t.getRentname());
			pst.setInt(3, t.getRoomid());
			pst.setTimestamp(4, t.getTimestart());
			pst.setTimestamp(5, t.getTimefinish());
			pst.setString(6, t.getRenterid());
			pst.setString(7, t.getNote());
			ketqua =pst.executeUpdate();
			System.out.println(ketqua);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JDBCUtil.CloseConnection(connection);
		// TODO Auto-generated method stub
		return ketqua;
	}


	@Override
	public int update(Rent t) {
		Connection connection = JDBCUtil.getConnection();
		int ketqua=0;
		String sql= "UPDATE rent "
				+" SET rentname=?, roomid=?, timestart=?, timefinish=?, note = ?"
				+" WHERE rentid=?";
		PreparedStatement pst;
		try {
			pst = connection.prepareStatement(sql);			
			pst.setString(1, t.getRentname());
			pst.setInt(2, t.getRoomid());
			pst.setTimestamp(3, t.getTimestart());
			pst.setTimestamp(4, t.getTimefinish());
			pst.setString(6, t.getNote());
			pst.setString(5, t.getRenterid());
			pst.setInt(5, t.getRentid());
			ketqua =pst.executeUpdate();
			System.out.println(ketqua);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JDBCUtil.CloseConnection(connection);
		// TODO Auto-generated method stub
		return ketqua;
	}


	@Override
	public int delete(Rent t) {
		Connection connection = JDBCUtil.getConnection();
		int ketqua=0;
		String sql= "DELETE from rent "
				+" WHERE rentid=?";
		System.out.println(sql);
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1,t.getRentid());
			ketqua =pst.executeUpdate();
			System.out.println(ketqua);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JDBCUtil.CloseConnection(connection);
		// TODO Auto-generated method stub
		return ketqua;
	}


	@Override
	public ArrayList<Rent> selectAll() {
		ArrayList<Rent> ketQua = new ArrayList<>();
		Connection connection = JDBCUtil.getConnection();
		
		String sql= "SELECT * FROM rent ";
		System.out.println(sql);
		try {
			ResultSet rs = connection.createStatement().executeQuery(sql);
			while (rs.next()) {
				int rentid = rs.getInt("rentid");
				String rentname = rs.getString("rentname");
				int roomid = rs.getInt("roomid");
				Timestamp timestart = rs.getTimestamp("timestart");
				Timestamp timefinish = rs.getTimestamp("timefinish");
				String renterid = rs.getString("renterid");
				String note = rs.getString("note");
				Rent a = new Rent(rentid,rentname,roomid,timestart,timefinish,renterid,note);
				ketQua.add(a);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JDBCUtil.CloseConnection(connection);
		return ketQua;
		// TODO Auto-generated method stub
	}


	public Rent selectByID(int t) {
		Rent ketQua = null;
		try {
			Connection connection = JDBCUtil.getConnection();
			String sql= "SELECT * FROM activity WHERE rentid =? ";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1,t);
			System.out.println(sql);
			ResultSet rs =pst.executeQuery();
			while (rs.next()) {
				int rentid = rs.getInt("rentid");
				String rentname = rs.getString("rentname");
				int roomid = rs.getInt("roomid");
				Timestamp timestart = rs.getTimestamp("timestart");
				Timestamp timefinish = rs.getTimestamp("timefinish");
				String renterid = rs.getString("renterid");
				String note = rs.getString("note");
				Rent a = new Rent(rentid,rentname,roomid,timestart,timefinish,renterid,note);
				ketQua = a;
			}
			
			
			JDBCUtil.CloseConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ketQua;
	}


	@Override
	public Rent selectByName(String t) {
		Rent ketQua = null;
		try {
			Connection connection = JDBCUtil.getConnection();
			String sql= "SELECT * FROM activity WHERE rentname =? ";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1,t);
			System.out.println(sql);
			ResultSet rs =pst.executeQuery();
			while (rs.next()) {
				int rentid = rs.getInt("rentid");
				String rentname = rs.getString("rentname");
				int roomid = rs.getInt("roomid");
				Timestamp timestart = rs.getTimestamp("timestart");
				Timestamp timefinish = rs.getTimestamp("timefinish");
				String renterid = rs.getString("renterid");
				String note = rs.getString("note");
				Rent a = new Rent(rentid,rentname,roomid,timestart,timefinish,renterid,note);
				ketQua = a;
			}
			
			
			JDBCUtil.CloseConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ketQua;
	}


	@Override
	public Rent selectByID(String t) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ArrayList<Timestamp> CheckFreeActivity(int t, Timestamp fromtime, Timestamp totime){
		ArrayList<Timestamp> from = new ArrayList<>();
		ArrayList<Timestamp> to = new ArrayList<>();
		Connection connection = JDBCUtil.getConnection();
		Timestamp timebegin = fromtime;
		Timestamp timeend = totime;
		String sql1= "SELECT timestart FROM rent WHERE timestart between ? and ? and timefinish between ? and ? and roomid = ? ORDER BY timestart asc ";
		
		String sql2= "SELECT timefinish FROM rent WHERE timestart between ? and ? and timefinish between ? and ? and roomid = ? ORDER BY timefinish asc ";

		String sql3= "SELECT timefinish FROM rent WHERE ? between timestart and timefinish and roomid = ?";
	
		String sql4= "SELECT timestart FROM rent WHERE ? between timestart and timefinish and roomid = ?";
		
		try {
			PreparedStatement pst3 = connection.prepareStatement(sql3);
			pst3.setTimestamp(1, fromtime);
			pst3.setInt(2, t);
			System.out.println(sql3);
			ResultSet rs3 =pst3.executeQuery();
			if (rs3.next()) {
			    timebegin = rs3.getTimestamp("timefinish");
			}
			PreparedStatement pst4 = connection.prepareStatement(sql4);
			pst4.setTimestamp(1,totime);
			pst4.setInt(2, t);
			System.out.println(sql4);
			ResultSet rs4 =pst4.executeQuery();
			if( rs4.next()) { 
				timeend = rs4.getTimestamp("timestart");
			}
			from.add(timebegin);
			
			PreparedStatement pst1 = connection.prepareStatement(sql1);
			pst1.setTimestamp(1,fromtime);
			pst1.setTimestamp(2, totime);
			pst1.setTimestamp(3,fromtime);
			pst1.setTimestamp(4, totime);
			pst1.setInt(5, t);
			System.out.println(sql1);
			ResultSet rs1 =pst1.executeQuery();
			while (rs1.next()) {
				to.add(rs1.getTimestamp("timestart"));
			}
			PreparedStatement pst2 = connection.prepareStatement(sql2);
			pst2.setTimestamp(1, fromtime);
			pst2.setTimestamp(2, totime);
			pst2.setTimestamp(3, fromtime);
			pst2.setTimestamp(4, totime);
			pst2.setInt(5, t);
			System.out.println(sql2);
			ResultSet rs2 =pst2.executeQuery();
			while (rs2.next()) {
				from.add(rs2.getTimestamp("timefinish"));
			}
			to.add(timeend);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Timestamp> ketQua = new ArrayList<>();
		for(int i=0 ; i< to.size(); i++ ) {
			ketQua.add(from.get(i));
			ketQua.add(to.get(i));
			System.out.println("from "+from.get(i)+ "to "+ to.get(i));
		}
		
		JDBCUtil.CloseConnection(connection);
		return ketQua;
	}


}