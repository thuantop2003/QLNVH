package model;

import java.time.LocalDateTime;

import DAO.WorkDAO;

public class Work {
	String userid;
	String AccountName;
	LocalDateTime timeloggin;
	LocalDateTime timeloggout;
	
	public String getAccountName() {
		return AccountName;
	}
	public void setAccountName(String accountName) {
		AccountName = accountName;
	}
	public Work(String userid, String accountName, LocalDateTime timeloggin, LocalDateTime timeloggout) {
		super();
		this.userid = userid;
		AccountName = accountName;
		this.timeloggin = timeloggin;
		this.timeloggout = timeloggout;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public LocalDateTime getTimeloggin() {
		return timeloggin;
	}
	public void setTimeloggin(LocalDateTime timeloggin) {
		this.timeloggin = timeloggin;
	}
	public LocalDateTime getTimeloggout() {
		return timeloggout;
	}
	public void setTimeloggout(LocalDateTime timeloggout) {
		this.timeloggout = timeloggout;
	}
	@Override
	public String toString() {
		return "Work [userid=" + userid + ", AccountName=" + AccountName + ", timeloggin=" + timeloggin
				+ ", timeloggout=" + timeloggout + "]";
	}
	public int insert() {
		int ketqua = WorkDAO.getInstance().insert(this);
		return ketqua;
	}

}
