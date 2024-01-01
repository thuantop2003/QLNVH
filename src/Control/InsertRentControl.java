package Control;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;

import DAO.ActivityDAO;
import DAO.DeviceActivityDAO;
import DAO.DeviceDAO;
import DAO.DeviceRentDAO;
import DAO.RentDAO;
import DAO.RenterDAO;
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
import model.Activity;
import model.Device;
import model.DeviceActivity;
import model.DeviceRent;
import model.Rent;
import model.Renter;
import model.Room;

public class InsertRentControl implements Initializable {
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
	private TextField textrentid;
	@FXML
	private TextField textrentname;
	@FXML
	private TextField textsdt;
	@FXML
	private TextField textaddress;
	@FXML
	private TextField textrentnote;
	@FXML
	private TableView<DeviceRent> table;
	
	@FXML
	private TableColumn<DeviceRent,String> devicename;
	@FXML
	private TableColumn<DeviceRent,String> deviceid;
	
	@FXML
	private TableColumn<DeviceRent,String> amount;
	
	private ObservableList<DeviceRent> accountlist=FXCollections.observableArrayList();


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
			devicename.setCellValueFactory(new PropertyValueFactory<DeviceRent, String>("name"));
			deviceid.setCellValueFactory(new PropertyValueFactory<DeviceRent, String>("deviceid"));
			amount.setCellValueFactory(new PropertyValueFactory<DeviceRent, String>("amount"));
			table.setItems(accountlist);
		
	}
	public void submit(ActionEvent event) throws IOException{
		Room a=RoomDAO.getInstance().selectByName(textrname.getText());
		Rent newrent=new Rent(textaname.getText(),a.getRoomId(),Timestamp.valueOf(textstart.getText()),Timestamp.valueOf(textfinish.getText()),textrentid.getText(),textnote.getText());
		RentDAO.getInstance().insert(newrent);
		int rentid=RentDAO.getInstance().SearchID(newrent);
		for(int i=0;i<accountlist.size();i++) {
			accountlist.get(i).setRentid(rentid);
			DeviceRentDAO.getInstance().insert(accountlist.get(i));
		}
		Renter renter=new Renter(textrentid.getText(),textrentname.getText(),textsdt.getText(),textaddress.getText(),textrentnote.getText());
		RenterDAO.getInstance().insert(renter);
	}
	public void insertDeviceActivity(ActionEvent event) throws IOException{
		Device dd=DeviceDAO.getInstance().selectByName(textdname.getText());
		DeviceRent d= new DeviceRent(dd.getDeviceId(),Integer.parseInt(textamount.getText()),textdname.getText());
		accountlist.add(d);
	}
	
}

