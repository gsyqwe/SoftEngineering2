package adminorui;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mainframeui.AlertBox;
import mainframeui.TField;
import rmi.OperationLogHelper;
import rmi.UserHelper;
import VO.LogVO;
import VO.UserVO;
import enums.JobType;
import enums.ResultMessage;

public class addui {
	private static String j;
	private static String n;
	private static Stage pStage;
	private static TField tx[] = new TField[9];// ������TField
	private static TField tx1[] = new TField[9];// ���
	private static TField tx2[] = new TField[9];// ����
	private static ComboBox tx3[] = new ComboBox[9];// ְ��
	private static String id;
	private static String adminid;
	private static String financialid;
	private static String inventoryid;
	private static String salerid;
	private static String manganerid;
	private static int count = 0;

	@SuppressWarnings("unchecked")
	public void display(String Job, String name, String ID) {
		j = Job;
		n = name;
		id = ID;
		// �õ�����UserID
		addui add = new addui();
		try {
			adminid = add.maxID(JobType.ADMINOR);
			adminid = "ADM" + "-" + adminid.substring(2, adminid.length());
			financialid = add.maxID(JobType.FINANCIAL);
			financialid = "FIN" + "-"
					+ financialid.substring(2, financialid.length());
			inventoryid = add.maxID(JobType.INVENTORY);
			inventoryid = "INV" + "-"
					+ inventoryid.substring(2, inventoryid.length());
			salerid = add.maxID(JobType.SALESMAN);
			salerid = "SAL" + "-" + salerid.substring(2, salerid.length());
			manganerid = add.maxID(JobType.MANAGER);
			manganerid = "MAN" + "-"
					+ manganerid.substring(2, manganerid.length());
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Stage primaryStage = new Stage();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("addui.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AnchorPane a = new AnchorPane();
		a.prefHeight(500);
		a.prefWidth(800);
		Text t = new Text("��ӭ��:" + name);
		t.setLayoutX(900.0);
		t.setLayoutY(70.0);
		t.setFont(Font.font("Verdana", 18));
		t.setStyle("-fx-background-color:null");

		ScrollPane s1 = new ScrollPane();
		s1.setPrefWidth(800);
		s1.setMinHeight(400);
		s1.setMaxHeight(400);
		s1.setLayoutX(150);
		s1.setLayoutY(280);
		AnchorPane a1 = new AnchorPane();
		a1.setPrefWidth(778);
		a1.setMinHeight(380);
		a1.setMaxHeight(380);
		s1.setContent(a1);

		ImageView i1 = new ImageView(new Image("/backgroud.png", true));
		i1.setFitWidth(800);
		i1.setFitHeight(400);
		a1.getChildren().add(i1);
		// ���ø�д��TField
		// Ա������
		TField tf = new TField();
		tf.setText("Ա������");
		tf.setEditable(false);
		tf.setPrefWidth(200);
		tf.setLayoutX(0);
		tf.setLayoutY(0);
		// ��������
		for (int i = 0; i < 9; i++) {
			tx[i] = new TField();
			tx[i].setPrefWidth(200);
			tx[i].setLayoutX(0);
			tx[i].setLayoutY(35 * (1 + i));
			a1.getChildren().add(tx[i]);
		}
		// Ա�����
		TField th = new TField();
		th.setText("Ա�����");
		th.setEditable(false);
		th.setPrefWidth(217);
		th.setLayoutX(580);
		th.setLayoutY(0); // ������
		for (int i = 0; i < 9; i++) {
			tx1[i] = new TField();
			tx1[i].setPrefWidth(217);
			tx1[i].setLayoutX(580);
			tx1[i].setLayoutY(35 * (1 + i));
			tx1[i].setEditable(false);
			a1.getChildren().add(tx1[i]);
		}
		// Ա������
		TField tc = new TField();
		tc.setText("Ա������");
		tc.setEditable(false);
		tc.setPrefWidth(200);
		tc.setLayoutX(380);
		tc.setLayoutY(0);
		// �������룬��γ�ʼ������Ӧ��Ҫ�ܿ���
		for (int i = 0; i < 9; i++) {
			tx2[i] = new TField();
			tx2[i].setPrefWidth(200);
			tx2[i].setLayoutX(380);
			tx2[i].setLayoutY(35 * (1 + i));
			a1.getChildren().add(tx2[i]);
		}
		// ְ��
		TField td = new TField();
		td.setText("��ǰְ��");
		td.setEditable(false);
		td.setPrefWidth(180);
		td.setLayoutX(200);
		td.setLayoutY(0);
		// ����ְ��
		for (int i = 0; i < 9; i++) {
			tx3[i] = new ComboBox();
			tx3[i].setMinHeight(35);
			tx3[i].setMaxHeight(35);
			tx3[i].setPrefWidth(180);
			tx3[i].setLayoutX(200);
			tx3[i].setLayoutY(35 * (1 + i));
			count = i;
			tx3[i].getItems().addAll("����Ա", "��������Ա", "������Ա", "������Ա", "�ܾ���");
			tx3[i].setStyle("-fx-border-color:Grey;-fx-border-radius:4;-fx-background-color:transparent");
			a1.getChildren().add(tx3[i]);
		}
		// ��comboBox�����¼�
		tx3[0].getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener() {
					@Override
					public void changed(ObservableValue arg0, Object arg1,
							Object arg2) {
						// TODO Auto-generated method stub
						if (arg2.toString() == "����Ա") {// �����û�
							tx1[0].setText(adminid);
							String index = adminid.substring(4,
									adminid.length());
							int m = Integer.parseInt(index);
							m = m + 1;
							String n = "" + m;
							adminid = "ADM-";
							for (int i = n.length(); i < 3; i++) {
								adminid = adminid + "0";
							}
							adminid = adminid + n;
						} else if (arg2.toString() == "��������Ա") {
							tx1[0].setText(inventoryid);
							String index = inventoryid.substring(4,
									inventoryid.length());
							int m = Integer.parseInt(index);
							m = m + 1;
							String n = "" + m;
							inventoryid = "INV-";
							for (int i = n.length(); i < 3; i++) {
								inventoryid = inventoryid + "0";
							}
							inventoryid = inventoryid + n;
						} else if (arg2.toString() == "������Ա") {
							tx1[0].setText(salerid);
							String index = salerid.substring(4,
									salerid.length());
							int m = Integer.parseInt(index);
							m = m + 1;
							String n = "" + m;
							salerid = "SAL-";
							for (int i = n.length(); i < 3; i++) {
								salerid = salerid + "0";
							}
							salerid = salerid + n;
						} else if (arg2.toString() == "������Ա") {
							tx1[0].setText(financialid);
							String index = financialid.substring(4,
									financialid.length());
							int m = Integer.parseInt(index);
							m = m + 1;
							String n = "" + m;
							financialid = "FIN-";
							for (int i = n.length(); i < 3; i++) {
								financialid = financialid + "0";
							}
							financialid = financialid + n;
						} else {
							tx1[0].setText(manganerid);
							String index = manganerid.substring(4,
									manganerid.length());
							int m = Integer.parseInt(index);
							m = m + 1;
							String n = "" + m;
							manganerid = "MAN-";
							for (int i = n.length(); i < 3; i++) {
								manganerid = manganerid + "0";
							}
							manganerid = manganerid + n;
						}
					}
				});

		tx3[1].getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener() {
					@Override
					public void changed(ObservableValue arg0, Object arg1,
							Object arg2) {
						// TODO Auto-generated method stub
						if (arg2.toString() == "����Ա") {// �����û�
							tx1[1].setText(adminid);
							String index = adminid.substring(4,
									adminid.length());
							System.out.println(index);
							int m = Integer.parseInt(index);
							m = m + 1;
							String n = "" + m;
							adminid = "ADM-";
							for (int i = n.length(); i < 3; i++) {
								adminid = adminid + "0";
							}
							adminid = adminid + n;
						} else if (arg2.toString() == "��������Ա") {
							tx1[1].setText(inventoryid);
							String index = inventoryid.substring(4,
									inventoryid.length());
							int m = Integer.parseInt(index);
							m = m + 1;
							String n = "" + m;
							inventoryid = "INV-";
							for (int i = n.length(); i < 3; i++) {
								inventoryid = inventoryid + "0";
							}
							inventoryid = inventoryid + n;
						} else if (arg2.toString() == "������Ա") {
							tx1[1].setText(salerid);
							String index = salerid.substring(4,
									salerid.length());
							int m = Integer.parseInt(index);
							m = m + 1;
							String n = "" + m;
							salerid = "SAL-";
							for (int i = n.length(); i < 3; i++) {
								salerid = salerid + "0";
							}
							salerid = salerid + n;
						} else if (arg2.toString() == "������Ա") {
							tx1[1].setText(financialid);
							String index = financialid.substring(4,
									financialid.length());
							int m = Integer.parseInt(index);
							m = m + 1;
							String n = "" + m;
							financialid = "FIN-";
							for (int i = n.length(); i < 3; i++) {
								financialid = financialid + "0";
							}
							financialid = financialid + n;
						} else {
							tx1[1].setText(manganerid);
							String index = manganerid.substring(4,
									manganerid.length());
							int m = Integer.parseInt(index);
							m = m + 1;
							String n = "" + m;
							manganerid = "MAN-";
							for (int i = n.length(); i < 3; i++) {
								manganerid = manganerid + "0";
							}
							manganerid = manganerid + n;
						}
					}
				});

		tx3[2].getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener() {
					@Override
					public void changed(ObservableValue arg0, Object arg1,
							Object arg2) {
						// TODO Auto-generated method stub
						if (arg2.toString() == "����Ա") {// �����û�
							tx1[2].setText(adminid);
							String index = adminid.substring(4,
									adminid.length());
							int m = Integer.parseInt(index);
							m = m + 1;
							String n = "" + m;
							adminid = "ADM-";
							for (int i = n.length(); i < 3; i++) {
								adminid = adminid + "0";
							}
							adminid = adminid + n;
						} else if (arg2.toString() == "��������Ա") {
							tx1[2].setText(inventoryid);
							String index = inventoryid.substring(4,
									inventoryid.length());
							int m = Integer.parseInt(index);
							m = m + 1;
							String n = "" + m;
							inventoryid = "INV-";
							for (int i = n.length(); i < 3; i++) {
								inventoryid = inventoryid + "0";
							}
							inventoryid = inventoryid + n;
						} else if (arg2.toString() == "������Ա") {
							tx1[2].setText(salerid);
							String index = salerid.substring(4,
									salerid.length());
							int m = Integer.parseInt(index);
							m = m + 1;
							String n = "" + m;
							salerid = "SAL-";
							for (int i = n.length(); i < 3; i++) {
								salerid = salerid + "0";
							}
							salerid = salerid + n;
						} else if (arg2.toString() == "������Ա") {
							tx1[2].setText(financialid);
							String index = financialid.substring(4,
									financialid.length());
							int m = Integer.parseInt(index);
							m = m + 1;
							String n = "" + m;
							financialid = "FIN-";
							for (int i = n.length(); i < 3; i++) {
								financialid = financialid + "0";
							}
							financialid = financialid + n;
						} else {
							tx1[2].setText(manganerid);
							String index = manganerid.substring(4,
									manganerid.length());
							int m = Integer.parseInt(index);
							m = m + 1;
							String n = "" + m;
							manganerid = "MAN-";
							for (int i = n.length(); i < 3; i++) {
								manganerid = manganerid + "0";
							}
							manganerid = manganerid + n;
						}
					}
				});

		tx3[3].getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener() {
					@Override
					public void changed(ObservableValue arg0, Object arg1,
							Object arg2) {
						// TODO Auto-generated method stub
						if (arg2.toString() == "����Ա") {// �����û�
							tx1[3].setText(adminid);
							String index = adminid.substring(4,
									adminid.length());
							int m = Integer.parseInt(index);
							m = m + 1;
							String n = "" + m;
							adminid = "ADM-";
							for (int i = n.length(); i < 3; i++) {
								adminid = adminid + "0";
							}
							adminid = adminid + n;
						} else if (arg2.toString() == "��������Ա") {
							tx1[3].setText(inventoryid);
							String index = inventoryid.substring(4,
									inventoryid.length());
							int m = Integer.parseInt(index);
							m = m + 1;
							String n = "" + m;
							inventoryid = "INV-";
							for (int i = n.length(); i < 3; i++) {
								inventoryid = inventoryid + "0";
							}
							inventoryid = inventoryid + n;
						} else if (arg2.toString() == "������Ա") {
							tx1[3].setText(salerid);
							String index = salerid.substring(4,
									salerid.length());
							int m = Integer.parseInt(index);
							m = m + 1;
							String n = "" + m;
							salerid = "SAL-";
							for (int i = n.length(); i < 3; i++) {
								salerid = salerid + "0";
							}
							salerid = salerid + n;
						} else if (arg2.toString() == "������Ա") {
							tx1[3].setText(financialid);
							String index = financialid.substring(4,
									financialid.length());
							int m = Integer.parseInt(index);
							m = m + 1;
							String n = "" + m;
							financialid = "FIN-";
							for (int i = n.length(); i < 3; i++) {
								financialid = financialid + "0";
							}
							financialid = financialid + n;
						} else {
							tx1[3].setText(manganerid);
							String index = manganerid.substring(4,
									manganerid.length());
							int m = Integer.parseInt(index);
							m = m + 1;
							String n = "" + m;
							manganerid = "MAN-";
							for (int i = n.length(); i < 3; i++) {
								manganerid = manganerid + "0";
							}
							manganerid = manganerid + n;
						}
					}
				});

		tx3[4].getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener() {
					@Override
					public void changed(ObservableValue arg0, Object arg1,
							Object arg2) {
						// TODO Auto-generated method stub
						if (arg2.toString() == "����Ա") {// �����û�
							tx1[4].setText(adminid);
							String index = adminid.substring(4,
									adminid.length());
							int m = Integer.parseInt(index);
							m = m + 1;
							String n = "" + m;
							adminid = "ADM-";
							for (int i = n.length(); i < 3; i++) {
								adminid = adminid + "0";
							}
							adminid = adminid + n;
						} else if (arg2.toString() == "��������Ա") {
							tx1[4].setText(inventoryid);
							String index = inventoryid.substring(4,
									inventoryid.length());
							int m = Integer.parseInt(index);
							m = m + 1;
							String n = "" + m;
							inventoryid = "INV-";
							for (int i = n.length(); i < 3; i++) {
								inventoryid = inventoryid + "0";
							}
							inventoryid = inventoryid + n;
						} else if (arg2.toString() == "������Ա") {
							tx1[4].setText(salerid);
							String index = salerid.substring(4,
									salerid.length());
							int m = Integer.parseInt(index);
							m = m + 1;
							String n = "" + m;
							salerid = "SAL-";
							for (int i = n.length(); i < 3; i++) {
								salerid = salerid + "0";
							}
							salerid = salerid + n;
						} else if (arg2.toString() == "������Ա") {
							tx1[4].setText(financialid);
							String index = financialid.substring(4,
									financialid.length());
							int m = Integer.parseInt(index);
							m = m + 1;
							String n = "" + m;
							financialid = "FIN-";
							for (int i = n.length(); i < 3; i++) {
								financialid = financialid + "0";
							}
							financialid = financialid + "-" + n;
						} else {
							tx1[4].setText(manganerid);
							String index = manganerid.substring(4,
									manganerid.length());
							int m = Integer.parseInt(index);
							m = m + 1;
							String n = "" + m;
							manganerid = "MAN-";
							for (int i = n.length(); i < 3; i++) {
								manganerid = manganerid + "0";
							}
							manganerid = manganerid + "-" + n;
						}
					}
				});

		tx3[5].getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener() {
					@Override
					public void changed(ObservableValue arg0, Object arg1,
							Object arg2) {
						// TODO Auto-generated method stub
						if (arg2.toString() == "����Ա") {// �����û�
							tx1[5].setText(adminid);
							String index = adminid.substring(4,
									adminid.length());
							int m = Integer.parseInt(index);
							m = m + 1;
							String n = "" + m;
							adminid = "ADM-";
							for (int i = n.length(); i < 3; i++) {
								adminid = adminid + "0";
							}
							adminid = adminid + n;
						} else if (arg2.toString() == "��������Ա") {
							tx1[5].setText(inventoryid);
							String index = inventoryid.substring(4,
									inventoryid.length());
							int m = Integer.parseInt(index);
							m = m + 1;
							String n = "" + m;
							inventoryid = "INV-";
							for (int i = n.length(); i < 3; i++) {
								inventoryid = inventoryid + "0";
							}
							inventoryid = inventoryid + n;
						} else if (arg2.toString() == "������Ա") {
							tx1[5].setText(salerid);
							String index = salerid.substring(4,
									salerid.length());
							int m = Integer.parseInt(index);
							m = m + 1;
							String n = "" + m;
							salerid = "SAL-";
							for (int i = n.length(); i < 3; i++) {
								salerid = salerid + "0";
							}
							salerid = salerid + n;
						} else if (arg2.toString() == "������Ա") {
							tx1[5].setText(financialid);
							String index = financialid.substring(4,
									financialid.length());
							int m = Integer.parseInt(index);
							m = m + 1;
							String n = "" + m;
							financialid = "FIN-";
							for (int i = n.length(); i < 3; i++) {
								financialid = financialid + "0";
							}
							financialid = financialid + n;
						} else {
							tx1[5].setText(manganerid);
							String index = manganerid.substring(4,
									manganerid.length());
							int m = Integer.parseInt(index);
							m = m + 1;
							String n = "" + m;
							manganerid = "MAN-";
							for (int i = n.length(); i < 3; i++) {
								manganerid = manganerid + "0";
							}
							manganerid = manganerid + n;
						}
					}
				});

		tx3[6].getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener() {
					@Override
					public void changed(ObservableValue arg0, Object arg1,
							Object arg2) {
						// TODO Auto-generated method stub
						if (arg2.toString() == "����Ա") {// �����û�
							tx1[6].setText(adminid);
							String index = adminid.substring(4,
									adminid.length());
							int m = Integer.parseInt(index);
							m = m + 1;
							String n = "" + m;
							adminid = "ADM-";
							for (int i = n.length(); i < 3; i++) {
								adminid = adminid + "0";
							}
							adminid = adminid + n;
						} else if (arg2.toString() == "��������Ա") {
							tx1[6].setText(inventoryid);
							String index = inventoryid.substring(4,
									inventoryid.length());
							int m = Integer.parseInt(index);
							m = m + 1;
							String n = "" + m;
							inventoryid = "INV-";
							for (int i = n.length(); i < 3; i++) {
								inventoryid = inventoryid + "0";
							}
							inventoryid = inventoryid + n;
						} else if (arg2.toString() == "������Ա") {
							tx1[6].setText(salerid);
							String index = salerid.substring(4,
									salerid.length());
							int m = Integer.parseInt(index);
							m = m + 1;
							String n = "" + m;
							salerid = "SAL-";
							for (int i = n.length(); i < 3; i++) {
								salerid = salerid + "0";
							}
							salerid = salerid + n;
						} else if (arg2.toString() == "������Ա") {
							tx1[6].setText(financialid);
							String index = financialid.substring(4,
									financialid.length());
							int m = Integer.parseInt(index);
							m = m + 1;
							String n = "" + m;
							financialid = "FIN-";
							for (int i = n.length(); i < 3; i++) {
								financialid = financialid + "0";
							}
							financialid = financialid + n;
						} else {
							tx1[6].setText(manganerid);
							String index = manganerid.substring(4,
									manganerid.length());
							int m = Integer.parseInt(index);
							m = m + 1;
							String n = "" + m;
							manganerid = "MAN-";
							for (int i = n.length(); i < 3; i++) {
								manganerid = manganerid + "0";
							}
							manganerid = manganerid + n;
						}
					}
				});

		tx3[7].getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener() {
					@Override
					public void changed(ObservableValue arg0, Object arg1,
							Object arg2) {
						// TODO Auto-generated method stub
						if (arg2.toString() == "����Ա") {// �����û�
							tx1[7].setText(adminid);
							String index = adminid.substring(4,
									adminid.length());
							int m = Integer.parseInt(index);
							m = m + 1;
							String n = "" + m;
							adminid = "ADM-";
							for (int i = n.length(); i < 3; i++) {
								adminid = adminid + "0";
							}
							adminid = adminid + n;
						} else if (arg2.toString() == "��������Ա") {
							tx1[7].setText(inventoryid);
							String index = inventoryid.substring(4,
									inventoryid.length());
							int m = Integer.parseInt(index);
							m = m + 1;
							String n = "" + m;
							inventoryid = "INV-";
							for (int i = n.length(); i < 3; i++) {
								inventoryid = inventoryid + "0";
							}
							inventoryid = inventoryid + n;
						} else if (arg2.toString() == "������Ա") {
							tx1[7].setText(salerid);
							String index = salerid.substring(4,
									salerid.length());
							int m = Integer.parseInt(index);
							m = m + 1;
							String n = "" + m;
							salerid = "SAL-";
							for (int i = n.length(); i < 3; i++) {
								salerid = salerid + "0";
							}
							salerid = salerid + n;
						} else if (arg2.toString() == "������Ա") {
							tx1[7].setText(financialid);
							String index = financialid.substring(4,
									financialid.length());
							int m = Integer.parseInt(index);
							m = m + 1;
							String n = "" + m;
							financialid = "FIN-";
							for (int i = n.length(); i < 3; i++) {
								financialid = financialid + "0";
							}
							financialid = financialid + n;
						} else {
							tx1[7].setText(manganerid);
							String index = manganerid.substring(4,
									manganerid.length());
							int m = Integer.parseInt(index);
							m = m + 1;
							String n = "" + m;
							manganerid = "MAN-";
							for (int i = n.length(); i < 3; i++) {
								manganerid = manganerid + "0";
							}
							manganerid = manganerid + n;
						}
					}
				});

		tx3[8].getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener() {
					@Override
					public void changed(ObservableValue arg0, Object arg1,
							Object arg2) {
						// TODO Auto-generated method stub
						if (arg2.toString() == "����Ա") {// �����û�
							tx1[8].setText(adminid);
							String index = adminid.substring(4,
									adminid.length());
							int m = Integer.parseInt(index);
							m = m + 1;
							String n = "" + m;
							adminid = "ADM-";
							for (int i = n.length(); i < 3; i++) {
								adminid = adminid + "0";
							}
							adminid = adminid + "-" + n;
						} else if (arg2.toString() == "��������Ա") {
							tx1[8].setText(inventoryid);
							String index = inventoryid.substring(4,
									inventoryid.length());
							int m = Integer.parseInt(index);
							m = m + 1;
							String n = "" + m;
							inventoryid = "INV-";
							for (int i = n.length(); i < 3; i++) {
								inventoryid = inventoryid + "0";
							}
							inventoryid = inventoryid + n;
						} else if (arg2.toString() == "������Ա") {
							tx1[8].setText(salerid);
							String index = salerid.substring(4,
									salerid.length());
							int m = Integer.parseInt(index);
							m = m + 1;
							String n = "" + m;
							salerid = "SAL-";
							for (int i = n.length(); i < 3; i++) {
								salerid = salerid + "0";
							}
							salerid = salerid + n;
						} else if (arg2.toString() == "������Ա") {
							tx1[8].setText(financialid);
							String index = financialid.substring(4,
									financialid.length());
							int m = Integer.parseInt(index);
							m = m + 1;
							String n = "" + m;
							financialid = "FIN-";
							for (int i = n.length(); i < 3; i++) {
								financialid = financialid + "0";
							}
							financialid = financialid + n;
						} else {
							tx1[8].setText(manganerid);
							String index = manganerid.substring(4,
									manganerid.length());
							int m = Integer.parseInt(index);
							m = m + 1;
							String n = "" + m;
							manganerid = "MAN-";
							for (int i = n.length(); i < 3; i++) {
								manganerid = manganerid + "0";
							}
							manganerid = manganerid + n;
						}
					}
				});

		a1.getChildren().addAll(tf, th, tc, td);

		Text t1 = new Text("��ǰְ��:" + Job);
		t1.setLayoutX(900.0);
		t1.setLayoutY(100.0);
		t1.setFont(Font.font("Verdana", 18));
		t1.setStyle("-fx-background-color:null");

		a.getChildren().addAll(root, t, t1, s1);

		Scene s = new Scene(a, 1080, 800);
		primaryStage.setTitle("����Ա");
		primaryStage.setScene(s);
		pStage = primaryStage;
		primaryStage.show();
	}

	@FXML
	private void HandleHelp(ActionEvent event) { // ������ť
		HelpAD a = new HelpAD();
		a.display(j, n, id);
	}

	@FXML
	private void HandleESC(ActionEvent event) { // �˳�
		pStage.close();
	}

	@FXML
	private void HandleOut(ActionEvent event) {// �˳�
		pStage.close();
	}

	@FXML
	private void HandleAdd(ActionEvent event) throws RemoteException {// ����
		// �ȼ���ж��ٸ�������

		ArrayList<String> namelist = new ArrayList();// ����������ArrayList

		ArrayList<String> numberlist = new ArrayList();// �����ŵ�ArrayList

		ArrayList<String> passwordlist = new ArrayList();// ���������ArrayList

		ArrayList<String> joblist = new ArrayList();// ����ְ��list

		for (int i = 0; i < 9; i++) {

			if (tx[i].getText().equals("")) {

			} else {

				namelist.add(tx[i].getText());

			}

			if (tx1[i].getText().equals("")) {

			} else {

				numberlist.add(tx1[i].getText());

			}

			if (tx2[i].getText().equals("")) {

			} else {

				passwordlist.add(tx2[i].getText());

			}

			if (tx3[i].getValue() == null) {

			} else {

				System.out.println(tx3[i].getValue().toString());

				joblist.add(tx3[i].getValue().toString());

			}

		}

		if (namelist.size() == numberlist.size()

		&& namelist.size() == passwordlist.size()

		&& namelist.size() == joblist.size()) {

			// System.out.println("a1");

			for (int i = 0; i < namelist.size(); i++) {

				UserVO vo = new UserVO();

				vo.setId("");

				vo.setJob(JobType.getEnumByValue((joblist.get(i))));

				vo.setName(namelist.get(i));

				vo.setPassword(passwordlist.get(i));

				UserHelper helper = UserHelper.getInstance();

				ResultMessage result = helper.getService().addUser(vo);

				if (result == ResultMessage.SUCCESS) {

					OperationLogHelper operationHelper = OperationLogHelper
							.getInstance();

					LogVO log = new LogVO();

					log.setUserID(id);

					log.setTime(new Date());

					String content = "�½��û���" + vo.getName();

					log.setContent(content);

					ResultMessage logResult = operationHelper.getService().add(
							log);

					System.out.println(logResult);
					AlertBox a = new AlertBox();

					// ��ʾ�������û��ɹ���

					a.display("�����ɹ�", "�����ɹ�");
					pStage.close();
				} else {

					AlertBox a = new AlertBox();

					a.display("ʧ��", "ʧ��");

				}

			}

		} else {

			// ���з�����Ϣ

			AlertBox a = new AlertBox();

			a.display("��д��Ϣ��ȫ", "��д��Ϣ��ȫ");

		}

	}

	@FXML
	private void HandleCancle(ActionEvent event) {
		pStage.close();
	}

	private String maxID(JobType job) throws RemoteException {

		UserHelper helper = UserHelper.getInstance();

		return helper.getService().getMaxID(job);

	}

}
