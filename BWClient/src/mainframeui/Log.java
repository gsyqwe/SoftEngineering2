package mainframeui;



import java.rmi.RemoteException;

import java.util.ArrayList;



import VO.UserVO;

import enums.JobType;

import enums.ResultMessage;

import javafx.application.Application;

import javafx.event.ActionEvent;

import javafx.event.EventHandler;

import javafx.geometry.Insets;

import javafx.geometry.Pos;

import javafx.scene.Group;

import javafx.scene.Scene;

import javafx.scene.control.Button;

import javafx.scene.control.Label;

import javafx.scene.control.PasswordField;

import javafx.scene.control.TextField;

import javafx.scene.image.Image;

import javafx.scene.image.ImageView;

import javafx.scene.layout.GridPane;

import javafx.scene.layout.HBox;

import javafx.stage.Stage;

import rmi.UserHelper;



public class Log extends Application {

	public static void main(String[] args) {

		launch(args);

	}



	private static ArrayList<String> userlist = new ArrayList<String>();

	private static ArrayList<String> passwordlist = new ArrayList<String>();

	// ����2��ArrayList������������û���

	private PasswordField pb = new PasswordField();

	private TextField textfield = new TextField();

	@Override

	public void start(final Stage primaryStage) throws Exception {

		// TODO Auto-generated method stub

		// Ӧ����һ�����ļ��д洢����Ϣȫ������2��Arraylist��

		Label label = new Label("����");

		Label label2 = new Label("���");

		Button button = new Button("��¼");

		Button button1 = new Button("ȡ��");

		// ����VBox���в���

		HBox hb = new HBox();

		hb.setSpacing(10);

		hb.setAlignment(Pos.CENTER_LEFT);

		HBox hb1 = new HBox();

		hb1.setSpacing(10);

		hb1.setAlignment(Pos.CENTER_LEFT);

		hb.getChildren().addAll(label, pb);

		hb1.getChildren().addAll(label2, textfield);

		// ��Log��button����¼�������Log����

		button.setOnAction(new EventHandler<ActionEvent>() {// ��¼

			public void handle(ActionEvent e){

				// ���������LogService
//				String ID = "MAN-004";
//				String password = "000000"
				String ID=textfield.getText();
//
				String password=pb.getText();

				UserHelper helper=UserHelper.getInstance();

				ResultMessage result=null;

				try {

					result = helper.getService().Log(ID,password);

				if(result==ResultMessage.LOGIN_FAIL_USER_NOT_FOUND){

					AlertBox a=new AlertBox();
					a.display("�û�������","�û�������");
					//������ʾ���û������ڡ�

				}

				else if(result==ResultMessage.LOGIN_FAIL_WRONG_PASSWORD){
					AlertBox a=new AlertBox();
					a.display("�������","�������");
					
				}

				else{

					UserVO user=helper.getService().findByID(ID);
					switch(user.getJob()){

					case FINANCIAL:

						Financeler f=new Financeler();

						primaryStage.close();
						
						f.display(user.getJob().toString(), user.getName(),user.getId());

						break;

					case INVENTORY:

						Inventory i=new Inventory();
						
						primaryStage.close();

						i.display(user.getJob().toString(), user.getName(),user.getId());

						break;

					case SALESMAN :

						Saler s=new Saler();
						
						primaryStage.close();

						s.display(user.getJob().toString(), user.getName(),user.getId());

						break;

					case MANAGER :

						Manganer m=new Manganer();
						
						primaryStage.close();

						m.display(user.getJob().toString(), user.getName(),user.getId());

						break;

					case ADMINOR:

						Adminor a=new Adminor();
						
						primaryStage.close();

						a.display(user.getJob().toString(), user.getName(),user.getId());

						break;

					}

				}} catch (RemoteException e1) {

					// TODO Auto-generated catch block

					e1.printStackTrace();

				}

			}

		});

		button1.setOnAction(new EventHandler<ActionEvent>() {// ȡ��

			public void handle(ActionEvent e) {

				primaryStage.close();

			}

		});

		GridPane grid = new GridPane(); // ���񲼾�

		grid.setHgap(10); // ����10��hgap�ڵ�

		grid.setVgap(10); // ����10��vgap�ڵ�

		grid.setPadding(new Insets(0, 0, 0, 10));// ���ýڵ�֮��ľ���

		grid.add(hb1, 5, 5);

		grid.add(hb, 5, 7);

		HBox hb2 = new HBox();

		hb2.setSpacing(150);

		hb2.getChildren().addAll(button, button1);

		grid.add(hb2, 5, 10);

		Group group = new Group();

		group.getChildren().addAll(

				new ImageView(new Image("/backgroud.png", true)), grid);

		Scene s = new Scene(group, 400, 250);

		primaryStage.setScene(s);

		primaryStage.setTitle("��¼");

		primaryStage.show();

	}

}