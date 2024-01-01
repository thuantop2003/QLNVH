package Control;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.Label;
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

public class DetailRentControl implements Initializable {
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
	private TableView<DeviceRent> table;
	
	@FXML
	private TableColumn<DeviceRent,String> devicename;
	@FXML
	private TableColumn<DeviceRent,String> deviceid;
	
	@FXML
	private TableColumn<DeviceRent,String> amount;
	@FXML
	private Label idlabel;
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
	
	private ObservableList<DeviceRent> accountlist=FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
			devicename.setCellValueFactory(new PropertyValueFactory<DeviceRent, String>("name"));
			deviceid.setCellValueFactory(new PropertyValueFactory<DeviceRent, String>("deviceid"));
			amount.setCellValueFactory(new PropertyValueFactory<DeviceRent, String>("amount"));
			table.setItems(accountlist);
	}

	public void getInfor(int rentid) {
		Rent act=RentDAO.getInstance().selectByID(rentid);
		textaname.setText(act.getRentname());
		Room r=RoomDAO.getInstance().selectByID(act.getRoomid());
		textrname.setText(r.getName());
		textstart.setText(act.getTimestart().toString());
		textfinish.setText(act.getTimefinish().toString());
		textnote.setText(act.getNote());
		Renter renter =RenterDAO.getInstance().selectByID(act.getRenterid());
		textrentname.setText(renter.getName());
		textrentid.setText(renter.getId());
		textsdt.setText(renter.getSdt());
		textaddress.setText(renter.getAddress());
		textrentnote.setText(renter.getNote());
		ArrayList<DeviceRent> a=DeviceRentDAO.getInstance().selectByRentId(rentid);
		for(int i=0;i<a.size();i++) {
			System.out.println(a.get(i).toString());
		}
		idlabel.setText(String.valueOf(rentid));
		accountlist.addAll(a);
	}
	public void insertDeviceRent(ActionEvent event) throws IOException{
		Device dd=DeviceDAO.getInstance().selectByName(textdname.getText());
		DeviceRent d= new DeviceRent(dd.getDeviceId(),Integer.parseInt(textamount.getText()),textdname.getText());
		accountlist.add(d);
	}

}
