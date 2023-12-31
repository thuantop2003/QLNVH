package model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import DAO.ActivityDAO;


public class Activity  {
	private int activityId;
    private String activityName;
    private String timeStart;
    private String timeFinish;
    private String note;
    private LocalDateTime timestart;
    private LocalDateTime timefinish;
    private Timestamp TStimestart;
    private Timestamp TStimefinish;
    
	

	
	
	public Activity(int activityId, String activityName, String note, Timestamp tStimestart, Timestamp tStimefinish) {
		super();
		this.activityId = activityId;
		this.activityName = activityName;
		this.note = note;
		TStimestart = tStimestart;
		TStimefinish = tStimefinish;
	}
	public Activity(int activityId, String activityName, String note, LocalDateTime timestart,
			LocalDateTime timefinish) {
		super();
		this.activityId = activityId;
		this.activityName = activityName;
		this.note = note;
		this.timestart = timestart;
		this.timefinish = timefinish;
	}
	public Activity(int activityId, String activityName, String timeStart, String timeFinish, String note) {
		super();
		this.activityId = activityId;
		this.activityName = activityName;
		this.timeStart = timeStart;
		this.timeFinish = timeFinish;
		this.note = note;
	}
	
	public Activity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getActivityId() {
		return activityId;
	}
	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getTimeStart() {
		return timeStart;
	}
	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}
	public String getTimeFinish() {
		return timeFinish;
	}
	public void setTimeFinish(String timeFinish) {
		this.timeFinish = timeFinish;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public LocalDateTime getTimestart() {
		return timestart;
	}
	public void setTimestart(LocalDateTime timestart) {
		this.timestart = timestart;
	}
	public LocalDateTime getTimefinish() {
		return timefinish;
	}
	public void setTimefinish(LocalDateTime timefinish) {
		this.timefinish = timefinish;
	}
	public Timestamp getTStimestart() {
		return TStimestart;
	}
	public void setTStimestart(Timestamp tStimestart) {
		TStimestart = tStimestart;
	}
	public Timestamp getTStimefinish() {
		return TStimefinish;
	}
	public void setTStimefinish(Timestamp tStimefinish) {
		TStimefinish = tStimefinish;
	}
	//ham check activity
	public boolean checkActivity() {
		if (this.getActivityName() != null && !this.getActivityName().isEmpty()) {
            return true;  // Hoạt động hợp lệ
        } else {
            return false; // Hoạt động không hợp lệ
        }
	}
	//ham all pay
	public int allPay() {
		Activity pay = ActivityDAO.getInstance().selectByID(this);
		if(pay == null) {
			return 0;
		}else {
			return 1;
		}
	}
	//ham searchSoonAct
	public Activity[] searchSoonAct() {
        List<Activity> soonActivities = new ArrayList<>();
        ArrayList<Activity> act = new ArrayList<>();
        act = ActivityDAO.getInstance().selectAll();

        long currentTimeMillis = System.currentTimeMillis();

        
        for (Activity activity : act) {
            if (convertTimeStringToMillis(activity.getTimeStart()) > currentTimeMillis) {
                soonActivities.add(activity);
            }
        }

 
        return soonActivities.toArray(new Activity[0]);
    }

   @Override
	public String toString() {
		return "Activity [activityId=" + activityId + ", activityName=" + activityName + ", note=" + note
				+ ", timestart=" + timestart + ", timefinish=" + timefinish + "]";
	}
	//ham chuyen thoi gian thanh millis
    private long convertTimeStringToMillis(String timeString) {
        
        return 0;
    }
	//ham searchLateAct
    public Activity[] searchLateAct() {
        List<Activity> soonActivities = new ArrayList<>();
        ArrayList<Activity> act = new ArrayList<>();
        act = ActivityDAO.getInstance().selectAll();

        long currentTimeMillis = System.currentTimeMillis();

        
        for (Activity activity : act) {
            if (convertTimeStringToMillis(activity.getTimeFinish()) > currentTimeMillis) {
                soonActivities.add(activity);
            }
        }

 
        return soonActivities.toArray(new Activity[0]);
    }
    //ham searchActByTime
    public Activity[] searchActByTime() {
        List<Activity> soonActivities = new ArrayList<>();
        ArrayList<Activity> act = new ArrayList<>();
        act = ActivityDAO.getInstance().selectAll();

        long currentTimeMillis = System.currentTimeMillis();

        
        for (Activity activity : act) {
            if ((convertTimeStringToMillis(activity.getTimeStart()) < currentTimeMillis) && (convertTimeStringToMillis(activity.getTimeFinish()) > currentTimeMillis)) {
                soonActivities.add(activity);
            }
        }

 
        return soonActivities.toArray(new Activity[0]);
    }
    public int insert() {
    	int ketQua= ActivityDAO.getInstance().insert(this);
    	return ketQua;
    }
    public int delete() {
    	int ketQua= ActivityDAO.getInstance().delete(this);
    	return ketQua;
    }
    public int update() {
    	int ketQua= ActivityDAO.getInstance().update(this);
    	return ketQua;
    }
    public ArrayList<LocalDateTime> Checkfreetime(LocalDateTime fromtime,LocalDateTime totime){
    	ArrayList<LocalDateTime> ketQua = ActivityDAO.getInstance().CheckFreeActivity(fromtime, totime);
    	return ketQua;
    }
    public ArrayList<Timestamp> Checkfreeact(Timestamp fromtime,Timestamp totime){
    	ArrayList<Timestamp> ketQua = ActivityDAO.getInstance().CheckFreeActivity(fromtime, totime);
    	return ketQua;
    } 
}
