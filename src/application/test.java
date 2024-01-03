package application;
	
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import DAO.ActivityDAO;
import DAO.DeviceActivityDAO;
import DAO.DeviceDAO;
import DAO.ManagerAccountDAO;
import DAO.RentDAO;
import DAO.RoomDAO;
import DAO.WorkDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.Activity;
import model.Device;
import model.DeviceActivity;
import model.ManagerAccount;
import model.Rent;
import model.Work;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class test{
	public static void main(String[] args) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime times = LocalDateTime.parse("2000-01-01 00:00:00", formatter);
        LocalDateTime timef = LocalDateTime.parse("3000-01-01 00:00:00", formatter);
        ArrayList<Work> a= WorkDAO.getInstance().selectByTime(times, timef);
        for(int i=0;i<a.size();i++) {
        	System.out.println(a.get(i).toString());
        }
		
	}
}
	
