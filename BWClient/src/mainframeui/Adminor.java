package mainframeui;

import java.io.IOException;

import adminorui.EmailAd;
import adminorui.HelpAD;
import adminorui.addui;
import adminorui.getui;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Adminor {
	static String j;
	static String n;
	private static Stage pStage;
	private static String id;

	@SuppressWarnings("unchecked")
	public void display(String Job, String name,String ID) {// 管理员,第一个为职业,第二个为name
		Stage primaryStage = new Stage();
		id=ID;//ID
		AnchorPane a = new AnchorPane();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("Adminor.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		TextField text1 = new TextField();
		text1.setStyle("-fx-background-color:null");
		text1.setText("欢迎您:" + name);
		text1.setLayoutX(613.0);
		text1.setLayoutY(40.0);
		text1.setMinHeight(36.0);
		text1.setMaxHeight(36.0);
		text1.setFont(Font.font("Verdana", 18));
		text1.prefWidth(185.0);
		text1.setEditable(false);

		TextField text2 = new TextField();
		text2.setStyle("-fx-background-color:null");
		text2.setText("当前职务:" + Job);
		text2.setLayoutX(613.0);
		text2.setLayoutY(68.0);
		text2.setMinHeight(30.0);
		text2.setMaxHeight(30.0);
		text2.setFont(Font.font("Verdana", 18));
		text2.prefWidth(185.0);
		text2.setEditable(false);

		// 四条消息
		// 左上
		TextField t4 = new TextField();
		t4.setLayoutX(56.0);
		t4.setLayoutY(394.0);
		t4.setMinHeight(30.0);
		t4.setMaxHeight(30.0);
		t4.setPrefWidth(362.0);
		t4.setStyle("-fx-background-color: null");
		// 右上
		TextField t1 = new TextField();
		t1.setLayoutX(468.0);
		t1.setLayoutY(394.0);
		t1.setMinHeight(30.0);
		t1.setMaxHeight(30.0);
		t1.setPrefWidth(319.0);
		t1.setStyle("-fx-background-color: null");
		// 左下
		TextField t2 = new TextField();
		t2.setLayoutX(54.0);
		t2.setLayoutY(456.0);
		t2.setMinHeight(30.0);
		t2.setMaxHeight(30.0);
		t2.setPrefWidth(362.0);
		t2.setStyle("-fx-background-color: null");
		// 右下
		TextField t3 = new TextField();
		t3.setLayoutX(468.0);
		t3.setLayoutY(456.0);
		t3.setMinHeight(30.0);
		t3.setMaxHeight(30.0);
		t3.setPrefWidth(327.0);
		t3.setStyle("-fx-background-color: null");
		t1.setEditable(false);
		t2.setEditable(false);
		t3.setEditable(false);
		t4.setEditable(false);

		ComboBox<String> c2 = new ComboBox();
		c2.setValue("管理用户 ");
		c2.getItems().addAll("增加用户",  "管理用户");
		c2.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener() {
					@Override
					public void changed(ObservableValue arg0, Object arg1,
							Object arg2) {
						// TODO Auto-generated method stub
						if (arg2.toString() == "增加用户") {// 增加用户
							addui a = new addui();
							a.display(j, n,id);
						} else if (arg2.toString() == "管理用户") {
							getui d = new getui();
							d.display(j, n,ID);
						}
					}
				});
		c2.setLayoutX(45.0);
		c2.setLayoutY(90.0);
		c2.setPrefWidth(150.0);
		Text te=new Text("增加用户");
		te.setLayoutX(190);
		te.setLayoutY(340);
		te.setFont(Font.font(20));
		Text te1=new Text("管理用户");
		te1.setLayoutX(490);
		te1.setLayoutY(340);
		te1.setFont(Font.font(20));

		a.getChildren().addAll(root, text1, text2, c2, t1, t2, t3, t4,te,te1);

		Scene s = new Scene(a, 800, 500);
		primaryStage.setTitle("管理员");
		primaryStage.setScene(s);
		j = Job;
		n = name;
		pStage = primaryStage;
		primaryStage.show();
	}

	@FXML
	private void HandleEmail(ActionEvent event) {
		EmailAd e = new EmailAd();
		e.display(j, n,id);
	}

	@FXML
	private void HandleEsc(ActionEvent event) {
		Log l = new Log();
		try {
			l.start(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		pStage.close();
	}

	@FXML
	private void HandleHelp(ActionEvent event) {
		HelpAD h = new HelpAD();
		h.display(j, n,id);
	}

	@FXML
	private void HandleAddUser(ActionEvent event) {
		addui a = new addui();
		a.display(j, n,id);
	}
	@FXML
	private void HandleDeviseUser(ActionEvent event) {
		getui d = new getui();
		d.display(j, n,id);
	}

	@FXML
	private void HandleDevisePassword(ActionEvent event) {
		devisePassword d = new devisePassword();
		d.display(j, n,id);
	}
}
