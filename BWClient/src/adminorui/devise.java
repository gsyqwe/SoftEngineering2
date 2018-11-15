package adminorui;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mainframeui.AlertBox;
import mainframeui.TField;
import rmi.OperationLogHelper;
import rmi.UserHelper;
import VO.LogVO;
import VO.UserVO;
import enums.JobType;
import enums.ResultMessage;

public class devise {

	public void deviseJob(String Job, String name, String ID, UserVO u,
			TableView table) {
		Stage primaryStage = new Stage();
		Label l = new Label("请输入新职务");
		l.setPrefWidth(100);
		l.setMinHeight(35);
		l.setMaxHeight(35);
		ComboBox t1 = new ComboBox();// 用来输入新的职务
		t1.setPrefWidth(150);
		t1.setMinHeight(30);
		t1.setMaxHeight(30);
		t1.getItems().addAll("管理员", "销售人员", "库存管理人员", "财务人员", "总经理");
		t1.setStyle("-fx-border-color:Grey;-fx-border-radius:4;-fx-background-color:transparent");
		l.setLayoutX(50);
		l.setLayoutY(100);
		t1.setLayoutX(170);
		t1.setLayoutY(100);
		Button b = new Button("确定");

		// 确定修改职务的事件
		b.setOnAction(new EventHandler<ActionEvent>() {// 修改职务
			public void handle(ActionEvent e) {
				String oldJob = u.getJobName();

				// 我要得到用户的输入，也就是新的职务,赋值给newJob

				String newJob = "";

				newJob = t1.getValue().toString();

				u.setJob(JobType.getEnumByValue((newJob)));

				UserHelper helper = UserHelper.getInstance();

				ResultMessage result;

				try {

					result = helper.getService().modifyUser(u);

					if (result == ResultMessage.SUCCESS) {

						OperationLogHelper operationHelper = OperationLogHelper

						.getInstance();

						LogVO log = new LogVO();

						log.setTime(new Date());

						log.setUserID(ID);

						String Content = "修改" + u.getId() + "职位:" + oldJob

						+ "到" + newJob;

						log.setContent(Content);

						ResultMessage logResult = operationHelper.getService()
								.add(log);
						
						ArrayList<UserVO> userlist=new ArrayList<>();
						ObservableList<UserVO> data = FXCollections.observableArrayList();
						for (int i = 0; i < userlist.size(); i++) {
							data.add(userlist.get(i));
						}
						table.setItems(data);
						// 显示“修改成功”
						AlertBox a=new AlertBox();
						a.display("修改成功","修改成功");
						primaryStage.close();
					}

				} catch (RemoteException e1) {

					// TODO Auto-generated catch block

					e1.printStackTrace();

				}
			}
		});

		b.setLayoutX(90);
		b.setLayoutY(200);

		// 取消按钮
		Button b1 = new Button(("取消"));
		b1.setLayoutX(225);
		b1.setLayoutY(200);
		b1.setOnAction(new EventHandler<ActionEvent>() {// 取消
			public void handle(ActionEvent e) {
				primaryStage.close();
			}
		});
		AnchorPane a = new AnchorPane();
		a.setPrefWidth(400);
		a.setMinHeight(300);
		a.setMaxHeight(300);
		a.getChildren().addAll(l, t1, b, b1);
		Group group = new Group();

		group.getChildren().addAll(

		new ImageView(new Image("/backgroud.png", true)), a);
		Scene s = new Scene(group, 400, 300);
		primaryStage.setTitle("管理员");
		primaryStage.setScene(s);
		primaryStage.show();
	}

	public void devisePassword(String Job, String name, String ID, UserVO u) {// 修改密码的
		Stage primaryStage = new Stage();
		PField t1 = new PField();// 第一个密码
		PField t2 = new PField();// 再次输入密码
		Label l1 = new Label("请输入密码:");
		Label l2 = new Label("再次确认密码:");
		// 给t1,t2以及l1,l2做位置的初始化
		t1.setMinHeight(30);
		t1.setMaxHeight(30);
		t2.setMinHeight(30);
		t2.setMaxHeight(30);

		l1.setLayoutX(50);
		l1.setLayoutY(100);
		t1.setLayoutX(170);
		t1.setLayoutY(100);
		l2.setLayoutX(50);
		l2.setLayoutY(160);
		t2.setLayoutX(170);
		t2.setLayoutY(160);

		AnchorPane a = new AnchorPane();
		a.setPrefWidth(400);
		a.setMinHeight(400);
		a.setMaxHeight(400);
		Button b = new Button("确定");
		b.setLayoutX(100);
		b.setLayoutY(255);
		// 确定修改密码的事件
		b.setOnAction(new EventHandler<ActionEvent>() {// 修改密码
			public void handle(ActionEvent e) {
				String newPassword1 = t1.getText();
				String newPassword2 = t2.getText();
				if (!newPassword1.equals(newPassword2)) {
					// 显示“两次新密码输入不一致，请重新输入”
					AlertBox a = new AlertBox();
					a.display("", "两次密码输入不一致，请重新输入");
				} else {
					u.setPassword(newPassword1);
					UserHelper helper = UserHelper.getInstance();
					try {
						ResultMessage result = helper.getService()
								.modifyUser(u);
						if (result == ResultMessage.CATEGORY_UPDATE_SUCCESS) {
							OperationLogHelper operationHelper = OperationLogHelper
									.getInstance();
							LogVO log = new LogVO();
							log.setTime(new Date());
							log.setUserID(ID);
							String Content = "修改" + u.getId() + "密码";
							log.setContent(Content);
							ResultMessage logResult = operationHelper
									.getService().add(log);
							// 显示“密码修改成功”考虑一下要不要退回到登陆界面重新登陆
						}
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					primaryStage.close();
				}
			}
		});

		// 取消按钮
		Button b1 = new Button(("取消"));
		b1.setOnAction(new EventHandler<ActionEvent>() {// 取消
			public void handle(ActionEvent e) {
				primaryStage.close();
			}
		});
		b1.setLayoutX(260);
		b1.setLayoutY(260);

		a.getChildren().addAll(b1, b, t1, t2, l1, l2);
		Group group = new Group();

		group.getChildren().addAll(

		new ImageView(new Image("/backgroud.png", true)), a);
		Scene s = new Scene(group, 400, 350);
		primaryStage.setTitle("管理员");
		primaryStage.setScene(s);
		primaryStage.show();
	}

	public ArrayList<UserVO> getUserList() throws RemoteException {
		UserHelper helper = UserHelper.getInstance();
		ArrayList<UserVO> vo = helper.getService().getAllList();
		return vo;
	}
}
