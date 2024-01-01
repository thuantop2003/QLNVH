package application;
	
import java.sql.Timestamp;
import java.util.ArrayList;

import DAO.ActivityDAO;
import DAO.DeviceActivityDAO;
import DAO.DeviceDAO;
import DAO.RentDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.Activity;
import model.Device;
import model.DeviceActivity;
import model.Rent;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class test{
	public static void main(String[] args) {
		Rent act=RentDAO.getInstance().selectByID(1);
		System.out.println(act.toString());
	}
	
}
