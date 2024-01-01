package Control;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import DAO.AccountDAO;
import DAO.ActivityDAO;
import DAO.RentDAO;
import DAO.WorkDAO;
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
import model.Account;
import model.Activity;
import model.Rent;
import model.Work;

public class RentController implements Initializable {
	@FXML
	private TableView<Rent> table;
	
	@FXML
	private TableColumn<Rent,String> rentid;
	
	@FXML
	private TableColumn<Rent,String> rentname;

	@FXML
	private TableColumn<Rent,String> renterid;

	@FXML
	private TableColumn<Rent,String> roomid;
	
	@FXML
	private TableColumn<Rent,String> note;
	
	@FXML
	private TableColumn<Rent,LocalDateTime> timestart;
	
	@FXML
	private TableColumn<Rent,LocalDateTime> timefinish;
	
	
	private ObservableList<Rent> accountlist=FXCollections.observableArrayList();
	
	private Stage stage;
	private Scene scene;
	private Parent root;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		ArrayList<Rent> a = RentDAO.getInstance().selectAll();
        accountlist.addAll(a);
        rentid.setCellValueFactory(new PropertyValueFactory<Rent, String>("rentid"));
        renterid.setCellValueFactory(new PropertyValueFactory<Rent, String>("renterid"));
        rentname.setCellValueFactory(new PropertyValueFactory<Rent, String>("rentname"));
		roomid.setCellValueFactory(new PropertyValueFactory<Rent, String>("roomid"));
		timestart.setCellValueFactory(new PropertyValueFactory<Rent, LocalDateTime>("timestart"));		
		timefinish.setCellValueFactory(new PropertyValueFactory<Rent, LocalDateTime>("timefinish"));
		note.setCellValueFactory(new PropertyValueFactory<Rent, String>("note"));
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
	public void insertAct(ActionEvent event) throws IOException{
		root = FXMLLoader.load(getClass().getResource("/view/InsertRent.fxml"));
		stage = new Stage();
		scene= new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void detailRent(ActionEvent event) throws IOException{
		Rent Selected = table.getSelectionModel().getSelectedItem();
		FXMLLoader f = new FXMLLoader(getClass().getResource("/view/DetailRent.fxml"));
		root = f.load();
		DetailRentControl dac=f.getController();
		dac.getInfor(Selected.getRentid());
		stage = new Stage();
		scene= new Scene(root);
		stage.setScene(scene);
		stage.show();
	
}
}
