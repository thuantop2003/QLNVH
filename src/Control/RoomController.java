package Control;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


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
import model.Room;

public class RoomController implements Initializable {
	@FXML
	private TableView<Room> table;
	
	@FXML
	private TableColumn<Room,String> roomid;
	
	@FXML
	private TableColumn<Room,String> name;
	
	@FXML
	private TableColumn<Room,String> capacity;
	
	@FXML
	private TableColumn<Room,String> price;
	@FXML
	private TableColumn<Room,String> status;
	@FXML
	private TableColumn<Room,String> note;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	
	private ObservableList<Room> accountlist=FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ArrayList<Room> a = RoomDAO.getInstance().selectAll();
        accountlist.addAll(a);
        
        roomid.setCellValueFactory(new PropertyValueFactory<Room, String>("roomId"));
        name.setCellValueFactory(new PropertyValueFactory<Room, String>("name"));
        status.setCellValueFactory(new PropertyValueFactory<Room, String>("status"));
        note.setCellValueFactory(new PropertyValueFactory<Room, String>("note"));
        price.setCellValueFactory(new PropertyValueFactory<Room, String>("price"));
        capacity.setCellValueFactory(new PropertyValueFactory<Room, String>("capacity"));
        table.setItems(accountlist);
        table.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                // Lấy dữ liệu của hàng được chọn
                Room selectedRoom = table.getSelectionModel().getSelectedItem();
                if (selectedRoom != null) {
                    System.out.println(selectedRoom.toString());
                }
            }
        });
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
