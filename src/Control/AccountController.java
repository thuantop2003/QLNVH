package Control;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import DAO.AccountDAO;
import DAO.LocalPersonDAO;
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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Account;
import model.ManagerAccount;
import model.Person;

public class AccountController implements Initializable {
	@FXML
	private TableView<Account> table;
	
	@FXML
	private TableColumn<Account,String> userId;
	
	@FXML
	private TableColumn<Account,String> accountName;
	
	@FXML
	private TableColumn<Account,String> password;
	
	@FXML
	private TableColumn<Account,String> note;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	
	private ObservableList<Account> accountlist=FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

        ArrayList<Account> a = AccountDAO.getInstance().selectAll();
        accountlist.addAll(a);
		userId.setCellValueFactory(new PropertyValueFactory<Account, String>("userId"));
		password.setCellValueFactory(new PropertyValueFactory<Account, String>("password"));
		accountName.setCellValueFactory(new PropertyValueFactory<Account, String>("accountName"));
		note.setCellValueFactory(new PropertyValueFactory<Account, String>("note"));
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
	
	public void makeNewAccount(ActionEvent event) throws IOException{
		root = FXMLLoader.load(getClass().getResource("/view/MakeNewAccount.fxml"));
		stage=new Stage();
		scene= new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	public void deleteAccount(ActionEvent event){
		Account Selected = table.getSelectionModel().getSelectedItem();
		if(Selected != null) {
		AccountDAO.getInstance().delete(Selected);
		accountlist.remove(Selected);
		}
	}
	public void ChangePassword(ActionEvent event) throws IOException{
		root = FXMLLoader.load(getClass().getResource("/view/ChangePassword.fxml"));
		stage=new Stage();
		scene= new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	

}
