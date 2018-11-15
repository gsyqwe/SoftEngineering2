package financelerui;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import mainframeui.AlertBox;
import adminorui.PField;
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
import rmi.BankAccountHelper;
import rmi.OperationLogHelper;
import rmi.UserHelper;
import enums.JobType;
import enums.ResultMessage;
import VO.BankAccountVO;
import VO.LogVO;
import VO.UserVO;

public class deleteAccount {

	private static String id;
	private static String password;
	private static ResultMessage result;

	public void display(String Id, BankAccountVO bankaccount, TableView t,ArrayList<BankAccountVO>banklist) {
		PField t1 = new PField();
		Button b = new Button("ȷ��");
		Stage primaryStage = new Stage();
		Label l = new Label("����������");
		l.setPrefWidth(100);
		l.setMinHeight(35);
		l.setMaxHeight(35);
		t1.setPrefWidth(150);
		t1.setMinHeight(30);
		t1.setMaxHeight(30);
		l.setLayoutX(50);
		l.setLayoutY(100);
		t1.setLayoutX(170);
		t1.setLayoutY(100);
		password = bankaccount.getPassword();

		b.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {

					result = delete(bankaccount.getId(), password);
					System.out.println(result);

					if (result == ResultMessage.BANK_ACCOUNT_DELETE_FAIL_AMOUNT) {

						// ��ʾ���˻���Ϊ0������ɾ����
						AlertBox a = new AlertBox();
						a.display("�˻���Ϊ0������ɾ��", "�˻���Ϊ0������ɾ��");

					} else if (result == ResultMessage.BANK_ACCOUNT_DELETE_FAIL_PASSWORD_WRONG) {

						// ��ʾ������������������롱
						AlertBox a = new AlertBox();
						a.display("���������������", "���������������");

					} else if (result == ResultMessage.SUCCESS) {
						// ��ʾ��ɾ���˻��ɹ���
						AlertBox alert = new AlertBox();
						alert.display("ɾ���˻��ɹ�", "ɾ���ɹ�");

						OperationLogHelper logHelper = OperationLogHelper

						.getInstance();

						LogVO vo = new LogVO();

						vo.setUserID(id);

						vo.setTime(new Date());

						String content = "ɾ���˻�:" + bankaccount.getId();

						ResultMessage logResult = logHelper.getService()

						.add(vo);
						for(int i=0;i<banklist.size();i++){
							if(banklist.get(i).getId().equals(bankaccount.getId())){
								banklist.remove(i);
							}
						}
						
						ObservableList<BankAccountVO> data = FXCollections
								.observableArrayList();

						for (int i = 0; i < banklist.size(); i++) {

							data.add(banklist.get(i));

						}
						t.setItems(data);
						
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

		// ȡ����ť
		Button b1 = new Button(("ȡ��"));
		b1.setLayoutX(225);
		b1.setLayoutY(200);
		b1.setOnAction(new EventHandler<ActionEvent>() {// ȡ��
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
		primaryStage.setTitle("����Ա");
		primaryStage.setScene(s);
		primaryStage.show();

	}

	private ResultMessage delete(String id, String password)

	throws RemoteException {

		BankAccountHelper helper = BankAccountHelper.getInstance();

		return helper.getService().deleteBankAccount(id, password);

	}

}
