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


public class ActivityDAO  {
	public static ActivityDAO getInstance() {
		return new ActivityDAO();
	}


	public int insert(Activity t) {
		Connection connection = JDBCUtil.getConnection();
		int ketqua=0;
		String sql= "INSERT INTO activity (activityid, name, timestart, timefinish, note)"
				+ "VALUES(?,?,?,?,?)";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			
			pst.setInt(1, t.getActivityId());
			pst.setString(2, t.getActivityName());
			pst.setString(3, t.getTimeStart());
			pst.setString(4, t.getTimeFinish());
			pst.setString(5, t.getNote());
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


	public int update(Activity t) {
		Connection connection = JDBCUtil.getConnection();
		int ketqua=0;
		String sql= "UPDATE activity "
				+" SET name=?, timestart=?, timefinish=?"
				+" WHERE activityid=?";
		PreparedStatement pst;
		try {
			pst = connection.prepareStatement(sql);
			pst.setInt(4, t.getActivityId());
			pst.setString(1, t.getActivityName());
			pst.setString(2, t.getTimeStart());
			pst.setString(3, t.getTimeFinish());
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


	public int delete(Activity t) {
		Connection connection = JDBCUtil.getConnection();
		int ketqua=0;
		String sql= "DELETE from activity "
				+" WHERE activityid=?";
		System.out.println(sql);
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1,t.getActivityId());
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


	public ArrayList<Activity> selectAll() {
		ArrayList<Activity> ketQua = new ArrayList<>();
		Connection connection = JDBCUtil.getConnection();
		
		String sql= "SELECT * FROM activity ";
		System.out.println(sql);
		try {
			ResultSet rs = connection.createStatement().executeQuery(sql);
			while (rs.next()) {
				int activityid = rs.getInt("activityid");
				String activityname = rs.getString("name");
				String timestart = rs.getString("timestart");
				String timefinish = rs.getString("timefinish");
				String note = rs.getString("note");
				Activity a = new Activity(activityid,activityname,timestart,timefinish,note);
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


	public Activity selectByID(Activity t) {
		Activity ketQua = null;
		try {
			Connection connection = JDBCUtil.getConnection();
			String sql= "SELECT * FROM activity WHERE activityid =? ";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1,t.getActivityId());
			System.out.println(sql);
			ResultSet rs =pst.executeQuery();
			while (rs.next()) {
				int activityid = rs.getInt("activityid");
				String activityname = rs.getString("name");
				String timestart = rs.getString("timestart");
				String timefinish = rs.getString("timefinish");
				String note = rs.getString("note");
				Activity a = new Activity(activityid,activityname,timestart,timefinish,note);
				ketQua = a;
			}
			
			
			JDBCUtil.CloseConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ketQua;
		
	}


	public Activity selectByName(Activity t) {
		Activity ketQua = null;
		try {
			Connection connection = JDBCUtil.getConnection();
			String sql= "SELECT * FROM activity WHERE name =? ";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1,t.getActivityName());
			System.out.println(sql);
			ResultSet rs =pst.executeQuery();
			while (rs.next()) {
				int activityid = rs.getInt("activityid");
				String activityname = rs.getString("name");
				String timestart = rs.getString("timestart");
				String timefinish = rs.getString("timefinish");
				String note = rs.getString("note");
				ketQua = new Activity(activityid,activityname,timestart,timefinish,note);
			}
			
			JDBCUtil.CloseConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ketQua;
		
	}
	public ArrayList<LocalDateTime> CheckFreeActivity( LocalDateTime fromtime, LocalDateTime totime){
		ArrayList<LocalDateTime> from = new ArrayList<>();
		ArrayList<LocalDateTime> to = new ArrayList<>();
		Connection connection = JDBCUtil.getConnection();
		LocalDateTime timebegin = fromtime;
		LocalDateTime timeend = totime;
		String sql1= "SELECT timestart FROM activity WHERE timestart between ? and ? and timefinish between ? and ? ORDER BY timestart asc ";
		
		String sql2= "SELECT timefinish FROM activity WHERE timestart between ? and ? and timefinish between ? and ? ORDER BY timefinish asc ";

		String sql3= "SELECT timefinish FROM activity WHERE ? between timestart and timefinish";
	
		String sql4= "SELECT timestart FROM activity WHERE ? between timestart and timefinish";
		
		try {
			PreparedStatement pst3 = connection.prepareStatement(sql3);
			pst3.setObject(1, fromtime);
			System.out.println(sql3);
			ResultSet rs3 =pst3.executeQuery();
			if (rs3.next()) {
			    timebegin = (LocalDateTime) rs3.getObject("timefinish");
			}
			PreparedStatement pst4 = connection.prepareStatement(sql4);
			pst4.setObject(1,totime);
			System.out.println(sql4);
			ResultSet rs4 =pst4.executeQuery();
			if( rs4.next()) { 
				timeend = (LocalDateTime) rs4.getObject("timestart");
			}
			from.add(timebegin);
			
			PreparedStatement pst1 = connection.prepareStatement(sql1);
			pst1.setObject(1,fromtime);
			pst1.setObject(2, totime);
			pst1.setObject(3,fromtime);
			pst1.setObject(4, totime);
			System.out.println(sql1);
			ResultSet rs1 =pst1.executeQuery();
			while (rs1.next()) {
				to.add((LocalDateTime) rs1.getObject("timestart"));
			}
			PreparedStatement pst2 = connection.prepareStatement(sql2);
			pst2.setObject(1,fromtime);
			pst2.setObject(2, totime);
			pst2.setObject(3,fromtime);
			pst2.setObject(4, totime);
			System.out.println(sql2);
			ResultSet rs2 =pst2.executeQuery();
			while (rs2.next()) {
				from.add((LocalDateTime) rs2.getObject("timefinish"));
			}
			to.add(timeend);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<LocalDateTime> ketQua = new ArrayList<>();
		for(int i=0 ; i< to.size(); i++ ) {
			ketQua.add(from.get(i));
			ketQua.add(to.get(i));
			System.out.println("from"+from.get(i)+ "to"+ to.get(i));
		}
		
		JDBCUtil.CloseConnection(connection);
		return ketQua;
	}
	public ArrayList<Timestamp> CheckFreeActivity( Timestamp fromtime, Timestamp totime){
		ArrayList<Timestamp> from = new ArrayList<>();
		ArrayList<Timestamp> to = new ArrayList<>();
		Connection connection = JDBCUtil.getConnection();
		Timestamp timebegin = fromtime;
		Timestamp timeend = totime;
		String sql1= "SELECT timestart FROM activity WHERE timestart between ? and ? and timefinish between ? and ? ORDER BY timestart asc ";
		
		String sql2= "SELECT timefinish FROM activity WHERE timestart between ? and ? and timefinish between ? and ? ORDER BY timefinish asc ";

		String sql3= "SELECT timefinish FROM activity WHERE ? between timestart and timefinish";
	
		String sql4= "SELECT timestart FROM activity WHERE ? between timestart and timefinish";
		
		try {
			PreparedStatement pst3 = connection.prepareStatement(sql3);
			pst3.setTimestamp(1, fromtime);
			System.out.println(sql3);
			ResultSet rs3 =pst3.executeQuery();
			if (rs3.next()) {
			    timebegin = rs3.getTimestamp("timefinish");
			}
			PreparedStatement pst4 = connection.prepareStatement(sql4);
			pst4.setTimestamp(1,totime);
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
	public String searchOffDate(Activity t) {
		String ketQua = null;
		try {
			Connection connection = JDBCUtil.getConnection();
			String sql= "SELECT timefinish FROM activity WHERE name =? ";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1,t.getActivityName());
			System.out.println(sql);
			ResultSet rs =pst.executeQuery();
			while (rs.next()) {
				String timefinish = rs.getString("timefinish");
				String a = timefinish;
				ketQua = a;
			System.out.println(a);
			}
			JDBCUtil.CloseConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ketQua;
	}
	public Activity SearchNearOffDate() {
		Activity ketQua = new Activity();
		Connection connection = JDBCUtil.getConnection();
		
		String sql= "SELECT TOP 1 FROM activity WHERE timefinish > NOW() ORDER BY timefinsh ";
		System.out.println(sql);
		try {
			ResultSet rs = connection.createStatement().executeQuery(sql);
			while (rs.next()) {
				int activityid = rs.getInt("activityid");
				String activityname = rs.getString("name");
				String timestart = rs.getString("timestart");
				String timefinish = rs.getString("timefinish");
				String note = rs.getString("note");
				Activity a = new Activity(activityid,activityname,timestart,timefinish,note);
				ketQua = a;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JDBCUtil.CloseConnection(connection);
		return ketQua;
	}
}
