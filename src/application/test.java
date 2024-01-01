package application;
	
import java.util.ArrayList;

import DAO.DeviceDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.Device;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class test{
	public static void main(String[] args) {
		ArrayList<Device> a = DeviceDAO.getInstance().selectAll();
		for (int i =0;i< a.size();i++ ) {
			System.out.println(a.toString());
			
		}
	}
	
}
