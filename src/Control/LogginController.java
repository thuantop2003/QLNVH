package Control;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Account;

public class LogginController {
	@FXML
	TextField accountname;
	@FXML
	TextField password;
	
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void login(ActionEvent event) throws IOException{
		String username = accountname.getText();
		String ps= password.getText();
		Account a=new Account(username,ps);
		if(a.checkAccount()) {
			root = FXMLLoader.load(getClass().getResource("/view/Menu.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene= new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		else {
			showAlert(AlertType.ERROR,"Lỗi", "Tài khoản hoặc mật khẩu không chính xác");
		}
		
	}
	private static void showAlert(AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null); // HeaderText set null để không hiển thị tiêu đề phụ
        alert.setContentText(content);
        alert.showAndWait();
    }

}
