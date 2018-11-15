package mainframeui;

import inventoryui.Alarm;
import inventoryui.CommodityInformationui;
import inventoryui.CommodityKindInformationui;
import inventoryui.EmailIn;
import inventoryui.GetReceipt;
import inventoryui.Gift;
import inventoryui.HelpIn;
import inventoryui.Overflow;
import inventoryui.SearchCommodity;
import inventoryui.checkCommodityui;
import inventoryui.drafts;
import inventoryui.loss;

import java.io.IOException;

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

public class Inventory {
	static String j;
	static String n;
	static Stage pStage;
	private static String id;

	@SuppressWarnings("unchecked")
	public void display(String Job, String name, String ID) {
		Stage primaryStage = new Stage();
		id = ID;
		AnchorPane a = new AnchorPane();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("Inventory.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ����������ʼ���Ϣ
		// ���ϵ���Ϣ
		TextField t4 = new TextField();
		t4.setLayoutX(56.0);
		t4.setLayoutY(394.0);
		t4.setMinHeight(30.0);
		t4.setMaxHeight(30.0);
		t4.setPrefWidth(362.0);
		t4.setStyle("-fx-background-color: null");
		// ����
		TextField t1 = new TextField();
		t1.setLayoutX(468.0);
		t1.setLayoutY(394.0);
		t1.setMinHeight(30.0);
		t1.setMaxHeight(30.0);
		t1.setPrefWidth(319.0);
		t1.setStyle("-fx-background-color: null");
		// ����
		TextField t2 = new TextField();
		t2.setLayoutX(54.0);
		t2.setLayoutY(456.0);
		t2.setMinHeight(30.0);
		t2.setMaxHeight(30.0);
		t2.setPrefWidth(362.0);
		t2.setStyle("-fx-background-color: null");
		// ����
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

		// 4��comboBox
		// ������
		ComboBox<String> c1 = new ComboBox();
		c1.setValue("������ ");
		c1.getItems().addAll("����̵�", "���鿴");
		c1.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener() {
					@Override
					public void changed(ObservableValue arg0, Object arg1,
							Object arg2) {
						// TODO Auto-generated method stub
						if (arg2.toString() == "����̵�") {// ����̵�
							checkCommodityui de = new checkCommodityui();
							try {
								de.display(Job, name, id);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else if (arg2.toString() == "���鿴") {// ���鿴
							CommodityInformationui c = new CommodityInformationui();
							c.display(Job, name, id);
						}
					}
				});
		c1.setLayoutX(57.0);
		c1.setLayoutY(95.0);
		c1.setMinHeight(30.0);
		c1.setMaxHeight(30.0);
		c1.setPrefWidth(130.0);

		// �½�����
		ComboBox<String> c2 = new ComboBox();
		c2.setValue("�½����� ");
		c2.getItems().addAll("���絥", "����", "������", "���͵�", "�ݸ���", "�鿴����");
		c2.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener() {
					@Override
					public void changed(ObservableValue arg0, Object arg1,
							Object arg2) {
						// TODO Auto-generated method stub
						if (arg2.toString() == "���絥") {// ���絥
							Overflow de = new Overflow();
							try {
								de.display(Job, name, id);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else if (arg2.toString() == "����") {// ����
							loss l = new loss();
							l.display(Job, name, id);
						} else if (arg2.toString() == "������") {// ������
							Alarm a = new Alarm();
							a.display(Job, name, id);
						} else if (arg2.toString() == "���͵�") {// ���͵�
							Gift g = new Gift();
							g.display(Job, name, id);
						} else if (arg2.toString() == "�ݸ���") {// �ݸ���
							drafts d = new drafts();
							d.display(Job, name, id);
						} else if (arg2.toString() == "�鿴����") {// �鿴����
							GetReceipt g = new GetReceipt();
							g.display(Job, name, id);
						}
					}
				});
		c2.setLayoutX(186.0);
		c2.setLayoutY(95.0);
		c2.setMinHeight(30.0);
		c2.setMaxHeight(30.0);
		c2.setPrefWidth(130.0);

		// ��Ʒ����
		ComboBox<String> c3 = new ComboBox();
		c3.setValue("��Ʒ����");
		c3.getItems().addAll("��ѯ��Ʒ");
		c3.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener() {
					@Override
					public void changed(ObservableValue arg0, Object arg1,
							Object arg2) {
						// TODO Auto-generated method stub
						if (arg2.toString() == "��ѯ��Ʒ") {// ��ѯ��Ʒ
							SearchCommodity s = new SearchCommodity();
							s.display(Job, name, id);
						}
					}
				});
		c3.setLayoutX(315.0);
		c3.setLayoutY(95.0);
		c3.setMinHeight(30.0);
		c3.setMaxHeight(30.0);
		c3.setPrefWidth(130.0);

		// ��Ʒ�������
		ComboBox<String> c4 = new ComboBox();
		c4.setValue("��Ʒ�������");
		c4.getItems().addAll("�������");
		c4.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener() {
					@Override
					public void changed(ObservableValue arg0, Object arg1,
							Object arg2) {
						// TODO Auto-generated method stub
						if (arg2.toString() == "�������") {// �鿴��Ʒ����
							CommodityKindInformationui c = new CommodityKindInformationui();
							c.display(Job, name, id);
						}
					}
				});
		c4.setLayoutX(445.0);
		c4.setLayoutY(95.0);
		c4.setMinHeight(30.0);
		c4.setMaxHeight(30.0);
		c4.setPrefWidth(150.0);

		TextField text1 = new TextField();
		text1.setStyle("-fx-background-color:null");
		text1.setText("��ӭ��:" + name);
		text1.setLayoutX(613.0);
		text1.setLayoutY(40.0);
		text1.setMinHeight(36.0);
		text1.setMaxHeight(36.0);
		text1.setFont(Font.font("Verdana", 18));
		text1.prefWidth(185.0);
		text1.setEditable(false);

		TextField text2 = new TextField();
		text2.setStyle("-fx-background-color:null");
		text2.setText("��ǰְ��:" + Job);
		text2.setLayoutX(613.0);
		text2.setLayoutY(68.0);
		text2.setMinHeight(30.0);
		text2.setMaxHeight(30.0);
		text2.setFont(Font.font("Verdana", 18));
		text2.prefWidth(185.0);
		text2.setEditable(false);

		Text te = new Text("�ƶ�����");
		te.setLayoutX(140);
		te.setLayoutY(330);
		te.setFont(Font.font(20));

		Text te1 = new Text("����̵�");
		te1.setLayoutX(350);
		te1.setLayoutY(330);
		te1.setFont(Font.font(20));

		Text te2 = new Text("��Ʒ�������");
		te2.setLayoutX(535);
		te2.setLayoutY(330);
		te2.setFont(Font.font(20));

		a.getChildren().addAll(root, text1, text2, c1, c2, c3, c4, t1, t2, t3,
				t4, te, te1, te2);
		Scene s = new Scene(a, 800, 500);
		primaryStage.setTitle("��������Ա");
		j = Job;
		n = name;
		pStage = primaryStage;
		primaryStage.setScene(s);
		primaryStage.show();
	}

	@FXML
	private void HandleEsc(ActionEvent event) {
		pStage.close();
		Log l = new Log();
		try {
			l.start(new Stage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	private void HandleHelp(ActionEvent event) {
		HelpIn h = new HelpIn();
		h.display(j, n, id);
	}

	@FXML
	private void HandleEmail(ActionEvent event) {
		EmailIn e = new EmailIn();
		e.display(j, n, id);
	}

	@FXML
	private void HandleLoss(ActionEvent event) {
		loss l = new loss();
		l.display(j, n, id);
	}

	@FXML
	private void HandleCheck(ActionEvent event) {
		checkCommodityui c = new checkCommodityui();
		c.display(j, n, id);
	}

	@FXML
	private void HandleMDevise(ActionEvent event) {
		CommodityKindInformationui com = new CommodityKindInformationui();
		com.display(j, n, id);
	}

	@FXML
	private void HandleDevisePassword(ActionEvent event) {
		devisePassword d = new devisePassword();
		d.display(j, n, id);
	}
}
