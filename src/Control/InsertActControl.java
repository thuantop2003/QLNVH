package Control;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.ResourceBundle;

import DAO.AccountDAO;
import DAO.ActivityDAO;
import DAO.DeviceActivityDAO;
import DAO.DeviceDAO;
import DAO.RoomDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Account;
import model.Activity;
import model.Device;
import model.DeviceActivity;
import model.Room;

public class InsertActControl implements Initializable {

	@FXML
	private TextField textaname;
	@FXML
	private TextField textrname;
	@FXML
	private TextField textstart;
	@FXML
	private TextField textfinish;
	@FXML
	private TextField textnote;
	@FXML
	private TextField textdname;
	@FXML
	private TextField textamount;
	@FXML
	private TableView<DeviceActivity> table;
	
	@FXML
	private TableColumn<DeviceActivity,String> devicename;
	@FXML
	private TableColumn<DeviceActivity,String> deviceid;
	
	@FXML
	private TableColumn<DeviceActivity,String> amount;
	
	private ObservableList<DeviceActivity> accountlist=FXCollections.observableArrayList();


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
			devicename.setCellValueFactory(new PropertyValueFactory<DeviceActivity, String>("name"));
			deviceid.setCellValueFactory(new PropertyValueFactory<DeviceActivity, String>("deviceid"));
			amount.setCellValueFactory(new PropertyValueFactory<DeviceActivity, String>("amount"));
			table.setItems(accountlist);
		
	}
	public void submit(ActionEvent event) throws IOException{
		Room a=RoomDAO.getInstance().selectByName(textrname.getText());
		Activity newact=new Activity(textaname.getText(),a.getRoomId(),Timestamp.valueOf(textstart.getText()),Timestamp.valueOf(textfinish.getText()),textnote.getText());
		ActivityDAO.getInstance().insert(newact);
		int activityid=ActivityDAO.getInstance().SearchID(newact);
		for(int i=0;i<accountlist.size();i++) {
			accountlist.get(i).setActivityid(activityid);
			DeviceActivityDAO.getInstance().insert(accountlist.get(i));
		}
	}
	public void insertDeviceActivity(ActionEvent event) throws IOException{
		Device dd=DeviceDAO.getInstance().selectByName(textdname.getText());
		DeviceActivity d= new DeviceActivity(dd.getDeviceId(),Integer.parseInt(textamount.getText()),textdname.getText());
		accountlist.add(d);
	}
	
}
