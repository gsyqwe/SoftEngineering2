package mainframeui;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class devisePassword {
	static String j;
	static String n;
	static Stage pStage;

	public void display(String Job, String name, String ID) {
		Stage primaryStage = new Stage();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass()
					.getResource("devisePassword.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AnchorPane a = new AnchorPane();
		a.prefHeight(500);
		a.prefWidth(800);
		Text t = new Text("欢迎您:" + name);
		t.setLayoutX(639.0);
		t.setLayoutY(101.0);
		t.setFont(Font.font("Verdana", 18));
		t.setStyle("-fx-background-color:null");

		Text t1 = new Text("当前职务:" + Job);
		t1.setLayoutX(639.0);
		t1.setLayoutY(122.0);
		t1.setFont(Font.font("Verdana", 18));
		t1.setStyle("-fx-background-color:null");

		a.getChildren().addAll(root, t, t1);

		Scene s = new Scene(a, 800, 500);
		primaryStage.setTitle("管理员");
		primaryStage.setScene(s);
		pStage = primaryStage;
		primaryStage.show();
	}

	@FXML
	public void HandleOut(ActionEvent event) {
		pStage.close();
	}

	@FXML
	public void HandleESC(ActionEvent event) {
		pStage.close();
	}

	@FXML
	public void HandleDevisePassword(ActionEvent event) {
		pStage.close();
	}

	@FXML
	public void HandleCancle(ActionEvent event) {
		pStage.close();
	}

}