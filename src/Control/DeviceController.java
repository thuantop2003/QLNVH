package Control;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import DAO.DeviceDAO;
import DAO.RoomDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Device;
import model.Room;

public class DeviceController implements Initializable {
	@FXML
	private TableView<Device> table;
	
	@FXML
	private TableColumn<Device,String> deviceid;
	
	@FXML
	private TableColumn<Device,String> devicename;
	
	@FXML
	private TableColumn<Device,String> amount;
	
	@FXML
	private TableColumn<Device,String> price;
	
	@FXML
	private TableColumn<Device,String> status;
	
	@FXML
	private TableColumn<Device,String> note;
	
	@FXML
	private TableColumn<Device,String> roomid;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	
	private ObservableList<Device> accountlist=FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ArrayList<Device> a = DeviceDAO.getInstance().selectAll();
        accountlist.addAll(a);
        deviceid.setCellValueFactory(new PropertyValueFactory<Device, String>("deviceId"));
        roomid.setCellValueFactory(new PropertyValueFactory<Device, String>("roomId"));
        devicename.setCellValueFactory(new PropertyValueFactory<Device, String>("name"));
        status.setCellValueFactory(new PropertyValueFactory<Device, String>("status"));
        note.setCellValueFactory(new PropertyValueFactory<Device, String>("note"));
        price.setCellValueFactory(new PropertyValueFactory<Device, String>("price"));
        amount.setCellValueFactory(new PropertyValueFactory<Device, String>("amount"));
        table.setItems(accountlist);
	}
	
	public void switchToAccountManagement(ActionEvent event) throws IOException{
		root = FXMLLoader.load(getClass().getResource("/view/Account.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene= new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void switchToWorkManagement(ActionEvent event) throws IOException{
		root = FXMLLoader.load(getClass().getResource("/view/Work.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene= new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void switchToLocalPersonManagement(ActionEvent event) throws IOException{
		root = FXMLLoader.load(getClass().getResource("/view/LocalPerson.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene= new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void switchToRoomManagement(ActionEvent event) throws IOException{
		root = FXMLLoader.load(getClass().getResource("/view/Room.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene= new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void switchToDeviceManagement(ActionEvent event) throws IOException{
		root = FXMLLoader.load(getClass().getResource("/view/Device.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene= new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void switchToActivityManagement(ActionEvent event) throws IOException{
		root = FXMLLoader.load(getClass().getResource("/view/Activity.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene= new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void switchToRentManagement(ActionEvent event) throws IOException{
		root = FXMLLoader.load(getClass().getResource("/view/Rent.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene= new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}
