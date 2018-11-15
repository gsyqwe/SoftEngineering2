package inventoryui;

import java.io.IOException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rmi.EmailHelper;
import rmi.UserHelper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import mainframeui.AlertBox;
import mainframeui.TField;
import VO.EmailVO;
import VO.UserVO;
import enums.ResultMessage;
import extra.AutoCompleteTextField;
import extra.AutoCompleteTextFieldBuilder;

public class EmailIn {
	static String j;
	static String n;
	static Stage pStage;
	private static String id;
	private static TableView<EmailVO> table;// 用来显示邮件
	private static TableColumn<EmailVO, String> t1;// 时间
	private static TableColumn<EmailVO, String> t2;// 发件人
	private static TableColumn<EmailVO, String> t3;// 阅读状况
	private static TableColumn<EmailVO, String> t4;// 邮件标题
	private static ToggleGroup group = new ToggleGroup();
	private static ToggleButton to1;
	private static ToggleButton to2;
	private static ArrayList<EmailVO> emaillist = new ArrayList<>();
	private static TextArea textarea;// 内容
	private static TField tf1;// 收件人
	private static TField tf2;// 标题

	public void display(String Job, String name, String ID) {
		// TODO Auto-generated method stub
		Stage primaryStage = new Stage();
		Parent root = null;
		id = ID;
		to1 = new ToggleButton("发件");
		to2 = new ToggleButton("收件");
		to1.setToggleGroup(group);
		to2.setToggleGroup(group);
		to1.setLayoutX(143);
		to1.setLayoutY(284);
		to2.setLayoutX(143);
		to2.setLayoutY(314);
		to2.setSelected(true);
		try {
			root = FXMLLoader.load(getClass().getResource("EmailIn.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 显示登录人的信息
		TextField text1 = new TextField();
		text1.setStyle("-fx-background-color:null");
		text1.setText("欢迎你:" + name);
		text1.setLayoutX(900.0);
		text1.setLayoutY(40.0);
		text1.setMinHeight(36.0);
		text1.setMaxHeight(36.0);
		text1.setFont(Font.font("Verdana", 18));
		text1.prefWidth(185.0);
		text1.setEditable(false);

		TextField text2 = new TextField();
		text2.setStyle("-fx-background-color:null");
		text2.setText("当前职务:" + Job);
		text2.setLayoutX(900.0);
		text2.setLayoutY(68.0);
		text2.setMinHeight(30.0);
		text2.setMaxHeight(30.0);
		text2.setFont(Font.font("Verdana", 18));
		text2.prefWidth(185.0);
		text2.setEditable(false);

		AnchorPane a = new AnchorPane();
		// 收件人的情况显示
		table = new TableView();
		table.setLayoutX(50);
		table.setLayoutY(120);
		table.setStyle("-fx-background-color:null");
		table.setPrefWidth(783);
		table.setMinHeight(600);
		table.setMaxHeight(600);
		table.setLayoutX(192);
		table.setLayoutY(280);
		t1 = new TableColumn("时间");
		t1.setPrefWidth(183);
		t2 = new TableColumn("发件人");
		t2.setPrefWidth(200);
		t3 = new TableColumn("阅读状况");
		t3.setPrefWidth(200);
		t4 = new TableColumn("邮件标题");
		t4.setPrefWidth(200);
		table.getColumns().addAll(t1, t2, t3, t4);
		ObservableList<EmailVO> data = FXCollections.observableArrayList();
		for (int i = 0; i < emaillist.size(); i++) {
			data.add(emaillist.get(i));
		}
		t1.setCellValueFactory(cellData -> new SimpleStringProperty(""
				+ cellData.getValue().getDate()));// 账户编号
		t2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData
				.getValue().getSenderID()));// 发件人
		t3.setCellValueFactory(cellData -> new SimpleStringProperty("未读"));// 阅读状况
		t4.setCellValueFactory(cellData -> new SimpleStringProperty(cellData
				.getValue().getTitle()));// 标题

		table.getColumns().addAll(t1, t2, t3, t4);
		table.setItems(data);
		table.setStyle("-fx-background-color:null");
		// 发件
		ScrollPane s1 = new ScrollPane();
		s1.setLayoutX(193);
		s1.setLayoutY(285);
		s1.setPrefWidth(783);
		s1.setMinHeight(500);
		s1.setMaxHeight(500);
		s1.setVisible(false);
		AnchorPane a1 = new AnchorPane();
		a1.setLayoutX(0);
		a1.setLayoutY(0);
		a1.setPrefWidth(763);
		a1.setMinHeight(490);
		a1.setMaxHeight(490);
		ImageView i1 = new ImageView(new Image("/backgroud.png", true));
		i1.setFitWidth(763);
		i1.setFitHeight(600);
		a1.getChildren().add(i1);

		Label label = new Label("收  件  人");
		label.setLayoutX(13);
		label.setLayoutY(14);
		tf1 = new TField();
		tf1.setLayoutX(88);
		tf1.setLayoutY(14);
		tf1.setPrefWidth(600);
		tf1.setMinHeight(30);
		tf1.setMaxHeight(30);
		ArrayList<UserVO> mylist = new ArrayList<>();
		EmailIn em = new EmailIn();
		try {
			mylist = em.getUserList();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> testList = new ArrayList<String>();// 文本框下拉框中显示的数据就是testList中与文本框中想匹配的数据
		for (int i = 0; i < mylist.size(); i++) {
			testList.add(mylist.get(i).getName() + " " + mylist.get(i).getId());
		}
		AutoCompleteTextField auto = AutoCompleteTextFieldBuilder.build(tf1);
		auto.setCacheDataList(testList);
		Label l = new Label("    标      题");
		l.setLayoutX(0);
		l.setLayoutY(53);
		tf2 = new TField();
		tf2.setPrefWidth(600);
		tf2.setMinHeight(30);
		tf2.setMaxHeight(30);
		tf2.setLayoutX(88);
		tf2.setLayoutY(53);
		textarea = new TextArea();
		textarea.setWrapText(true);
		textarea.setLayoutX(0);
		textarea.setLayoutY(88);
		textarea.setPrefWidth(763);
		textarea.setMinHeight(400);
		textarea.setMaxHeight(400);
		Button butt = new Button("发送");
		butt.setLayoutX(700);
		butt.setLayoutY(14);
		butt.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				EmailVO e = new EmailVO();
				boolean truth = true;
				e.setSenderID(id);
				String text = tf1.getText();
				text = text.substring(text.length() - 7, text.length());
				e.setReceiverID(text);
				String content = textarea.getText();
				List<String> str = new ArrayList<>();
				str.add(content);
				e.setContent(str);
				String title = tf2.getText();
				e.setTitle(title);
				Date date = new Date();
				e.setDate(date);
				EmailHelper helper = EmailHelper.getInstance();
				ResultMessage result = null;
				try {
					result = helper.getService().completeAddEmail(e);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (result == ResultMessage.SUCCESS) {
					AlertBox a = new AlertBox();
					a.display("发送成功", "发送成功");
					primaryStage.close();
				}
			}

		});
		a1.getChildren().addAll(l, label, tf1, tf2, textarea, butt);
		s1.setContent(a1);

		group.selectedToggleProperty().addListener(
				new ChangeListener<Toggle>() {
					public void changed(ObservableValue<? extends Toggle> ov,
							Toggle toggle, Toggle new_toggle) {
						if (to1.isSelected()) {
							table.setVisible(false);
							s1.setVisible(true);
						} else if (to2.isSelected()) {
							table.setVisible(true);
							s1.setVisible(false);
						}
					}
				});

		a.getChildren().addAll(root, text1, text2, to1, to2, table, s1);
		Scene s = new Scene(a, 1080, 800);
		primaryStage.setTitle("财务人员");
		j = Job;
		n = name;
		pStage = primaryStage;
		primaryStage.setScene(s);
		primaryStage.show();
	}

	@FXML
	private void HandleHelp(ActionEvent event) {

	}

	@FXML
	private void HandleOut(ActionEvent event) {
		pStage.close();
	}

	@FXML
	private void HandleEsc(ActionEvent event) {
		pStage.close();
	}

	@FXML
	private void HandleSure(ActionEvent event) {
		pStage.close();
	}

	@FXML
	private void HandleCancle(ActionEvent event) {
		pStage.close();
	}

	public ArrayList<UserVO> getUserList() throws RemoteException {
		UserHelper helper = UserHelper.getInstance();
		ArrayList<UserVO> vo = helper.getService().getAllList();
		return vo;
	}
}
