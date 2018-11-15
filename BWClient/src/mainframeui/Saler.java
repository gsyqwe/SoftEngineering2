package mainframeui;

import java.io.IOException;

import salerui.EmailS;
import salerui.GetMember;
import salerui.GetReceipt;
import salerui.HelpSa;
import salerui.In;
import salerui.InBack;
import salerui.SaleBackui;
import salerui.Saleui;
import salerui.addMember;
import salerui.deviseMember;
import salerui.drafts;
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

public class Saler {// ����������Ա
	static String j;
	static String n;
	static Stage pStage;
	private static String id;

	@SuppressWarnings("unchecked")
	public void display(String Job, String name, String ID) {
		// TODO Auto-generated method stub
		j = Job;
		n = name;
		id = ID;
		Stage primaryStage = new Stage();
		AnchorPane a = new AnchorPane();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("Saler.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// �ƶ�����
		@SuppressWarnings("rawtypes")
		ComboBox comboBox = new ComboBox();
		comboBox.setPrefWidth(180.0);
		comboBox.setValue("����/����                     ");
		comboBox.getItems().addAll("�ƶ�������", "�ƶ����۵�", "�ƶ������˻���", "�ƶ������˻���",
				"�ݸ���", "�鿴����");
		comboBox.setLayoutX(82.0);
		comboBox.setLayoutY(95.0);
		comboBox.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener() {

					@Override
					public void changed(ObservableValue arg0, Object arg1,
							Object arg2) {
						// TODO Auto-generated method stub
						if (arg2.toString() == "�ƶ�������") {
							In add = new In();
							try {
								add.display(Job, name, id);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else if (arg2.toString() == "�ƶ����۵�") {
							Saleui de = new Saleui();
							try {
								de.display(Job, name, id);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else if (arg2.toString() == "�ƶ������˻���") {
							InBack de = new InBack();
							try {
								de.display(Job, name, id);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else if (arg2.toString() == "�ƶ������˻���") {
							SaleBackui de = new SaleBackui();
							try {
								de.display(Job, name, id);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else if (arg2.toString() == "�ݸ���") {
							drafts d = new drafts();
							d.display(Job, name, id);
						} else if (arg2.toString() == "�鿴����") {
							GetReceipt g = new GetReceipt();
							g.display(Job, name, id);
						}
					}

				});
		// �����Ա��Ϣ
		@SuppressWarnings("rawtypes")
		ComboBox comboBox1 = new ComboBox();
		comboBox1.setValue("�����Ա��Ϣ      ");
		comboBox1.getItems().addAll("���ӻ�Ա", "�����Ա");
		comboBox1.setLayoutX(296.0);
		comboBox1.setLayoutY(95.0);
		comboBox1.setMinWidth(180.0);
		comboBox1.setMaxWidth(180.0);
		comboBox1.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener() {
					@Override
					public void changed(ObservableValue arg0, Object arg1,
							Object arg2) {
						// TODO Auto-generated method stub
						if (arg2.toString() == "�����Ա") {// �޸�������Ӧ��Ҫ���µ�¼
							GetMember de = new GetMember();
							try {
								de.display(Job, name, id);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else if (arg2.toString() == "���ӻ�Ա") {
							addMember de = new addMember();
							try {
								de.display(Job, name, id);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}

				});

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

		// ������Ϣ
		// ����
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

		Text te = new Text("�½����۵�");
		te.setLayoutX(200);
		te.setLayoutY(330);
		te.setFont(Font.font(20));

		Text te1 = new Text("������Ա");
		te1.setLayoutX(485);
		te1.setLayoutY(330);
		te1.setFont(Font.font(20));

		a.getChildren().addAll(root, text1, text2, comboBox, comboBox1, t1, t2,
				t3, t4, te1, te);

		Scene s = new Scene(a, 800, 500);
		primaryStage.setTitle("����������Ա");
		primaryStage.setScene(s);
		pStage = primaryStage;
		primaryStage.show();
	}

	@FXML
	private void HandleEmail(ActionEvent event) {
		EmailS e = new EmailS();
		e.display(j, n, id);
	}

	@FXML
	private void HandleAddMember(ActionEvent event) {
		addMember a = new addMember();
		a.display(j, n, id);
	}

	@FXML
	private void HandleSale(ActionEvent event) {
		Saleui s = new Saleui();
		s.display(j, n, id);
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
		HelpSa h = new HelpSa();
		h.display(j, n, id);
	}

	@FXML
	private void HandleDevisePassword(ActionEvent event) {
		devisePassword d = new devisePassword();
		d.display(j, n, id);
	}
}
