package manganerui;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelpMan {

	static String j;
	static String n;
	static Stage pStage;
	private static String id;

	public void display(String Job, String name,String ID) {
		Stage primaryStage = new Stage();
		id=ID;
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("HelpMan.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene s = new Scene(root, 800, 500);
		primaryStage.setTitle("库存管理人员");
		j = Job;
		n = name;
		pStage = primaryStage;
		primaryStage.setScene(s);
		primaryStage.show();
	}
	@FXML
	private void HandleHelp(ActionEvent event){
		
	}
	@FXML
	private void HandleOut(ActionEvent event){
		pStage.close();
	}
	@FXML
	private void HandleEsc(ActionEvent event){
		
	}
	@FXML
	private void HandleSure(ActionEvent event){
		pStage.close();
	}
	@FXML
	private void HandleCancle(ActionEvent event){
		pStage.close();
	}
}