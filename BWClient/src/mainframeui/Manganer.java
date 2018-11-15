package mainframeui;

import java.io.IOException;

import manganerui.EmailMan;
import manganerui.HelpMan;
import manganerui.PromotionByLevelui;
import manganerui.PromotionByCombinationui;
import manganerui.PromotionBySum;
import manganerui.businessconditionui;
import manganerui.businessprocessui;
import manganerui.operationlogui;
import manganerui.checkreportui;
import manganerui.salelistui;
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

public class Manganer {
	static String j;
	static String n;
	static Stage pStage;
	private static String id;

	@SuppressWarnings("unchecked")
	public void display(String Job, String name, String ID) {// �ܾ���
		Stage primaryStage = new Stage();
		id = ID;
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("Manganer.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ��˵���
		ComboBox<String> comboBox = new ComboBox<String>();
		comboBox.setValue("��˵���    ");
		comboBox.getItems().addAll(// ��ͨ����δͨ�����Լ�δ��˵�
				"��˵���");
		comboBox.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener() {
					@Override
					public void changed(ObservableValue arg0, Object arg1,
							Object arg2) {
						// TODO Auto-generated method stub
						if (arg2.toString() == "��˵���") {
							checkreportui c = new checkreportui();
							try {
								c.display(Job, name, id);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							comboBox.setValue("��˵���");
						}
					}
				});
		comboBox.setLayoutX(86.0);
		comboBox.setLayoutY(95.0);
		comboBox.setPrefWidth(150.0);

		// �鿴����
		ComboBox<String> comboBox1 = new ComboBox<String>();
		comboBox1.setValue("�鿴����              ");
		comboBox1.getItems().addAll("�鿴��Ӫ���̱�", "�鿴��Ӫ�����", "�鿴������ϸ��");
		comboBox1.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener() {
					@Override
					public void changed(ObservableValue arg0, Object arg1,
							Object arg2) {
						// TODO Auto-generated method stub
						if (arg2.toString() == "�鿴��Ӫ���̱�") {
							businessprocessui b = new businessprocessui();
							try {
								b.display(Job, name, id);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else if (arg2.toString() == "�鿴��Ӫ�����") {
							businessconditionui b = new businessconditionui();
							try {
								b.display(Job, name, id);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else if (arg2.toString() == "�鿴������ϸ��") {
							salelistui b = new salelistui();
							try {
								b.display(Job, name, id);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				});
		comboBox1.setLayoutX(236.0);
		comboBox1.setLayoutY(95.0);
		comboBox1.setPrefWidth(150.0);

		// �ƶ���������
		ComboBox<String> comboBox2 = new ComboBox<String>();
		comboBox2.setValue("�ƶ���������");
		comboBox2.getItems().addAll("�ƶ���ͬ�û��ȼ���������", "�ƶ������Ʒ����������",
				"�ƶ��Ż��ܼ۴�������", "�鿴��������");
		comboBox2.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener() {
					@Override
					public void changed(ObservableValue arg0, Object arg1,
							Object arg2) {
						// TODO Auto-generated method stub
						if (arg2.toString() == "�ƶ���ͬ�û��ȼ���������") {
							PromotionByLevelui p = new PromotionByLevelui();
							try {
								p.display(Job, name, id);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else if (arg2.toString() == "�ƶ������Ʒ����������") {
							PromotionByCombinationui p = new PromotionByCombinationui();
							try {
								p.display(Job, name, id);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else if (arg2.toString() == "�ƶ��Ż��ܼ۴�������") {
							PromotionBySum p = new PromotionBySum();
							try {
								p.display(Job, name, id);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				});
		comboBox2.setLayoutX(386.0);
		comboBox2.setLayoutY(95.0);
		comboBox2.setPrefWidth(150.0);

		// �鿴ϵͳ������־
		ComboBox<String> comboBox3 = new ComboBox<String>();
		comboBox3.setValue("�鿴ϵͳ������־");
		comboBox3.getItems().addAll("�鿴ȫ��ϵͳ��־");
		comboBox3.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener() {
					@Override
					public void changed(ObservableValue arg0, Object arg1,
							Object arg2) {
						// TODO Auto-generated method stub
						if (arg2.toString() == "�鿴ȫ��ϵͳ��־") {
							operationlogui c = new operationlogui();
							try {
								c.display(Job, name, id);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				});
		comboBox3.setLayoutX(536.0);
		comboBox3.setLayoutY(95.0);
		comboBox3.setPrefWidth(182.0);

		AnchorPane a = new AnchorPane();
		// ���
		TextField t2 = new TextField();
		t2.setLayoutX(54.0);
		t2.setEditable(false);
		t2.setLayoutY(456.0);
		t2.setMinHeight(30.0);
		t2.setMaxHeight(30.0);
		t2.setPrefWidth(362.0);
		t2.setStyle("-fx-background-color: null");
		// �ұ�
		TextField t3 = new TextField();
		t3.setStyle("-fx-background-color:null");
		t3.setEditable(false);
		t3.setLayoutX(468.0);
		t3.setLayoutY(456.0);
		t3.setMinHeight(30.0);
		t3.setMaxHeight(30.0);
		t3.setPrefWidth(327.0);

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

		Text te = new Text("�鿴����");
		te.setLayoutX(90);
		te.setLayoutY(330);
		te.setFont(Font.font(20));

		Text te1 = new Text("��˱���");
		te1.setLayoutX(257);
		te1.setLayoutY(330);
		te1.setFont(Font.font(20));

		Text te2 = new Text("�ƶ���������");
		te2.setLayoutX(412);
		te2.setLayoutY(330);
		te2.setFont(Font.font(20));

		Text te3 = new Text("�鿴������־");
		te3.setLayoutX(595);
		te3.setLayoutY(330);
		te3.setFont(Font.font(20));

		a.getChildren().addAll(root, text1, text2, t2, t3, comboBox, comboBox1,
				comboBox2, comboBox3, te, te1, te2, te3);

		Scene s3 = new Scene(a, 800, 500);
		primaryStage.setTitle("�ܾ���");
		primaryStage.setScene(s3);
		j = Job;
		n = name;
		pStage = primaryStage;
		primaryStage.show();
	}

	@FXML
	private void HandleMail(ActionEvent event) {
		EmailMan e = new EmailMan();
		e.display(j, n, id);
	}

	@FXML
	private void HandleOut(ActionEvent event) {
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
	private void HandleCheck(ActionEvent event) {// �鿴����
		businessconditionui b = new businessconditionui();
		b.display(j, n, id);
	}

	@FXML
	private void HandleReport(ActionEvent event) {// ��˱���
		checkreportui c = new checkreportui();
		c.display(j, n, id);
	}

	@FXML
	private void HandlePromotion(ActionEvent event) {// �ƶ���������
		PromotionByLevelui p = new PromotionByLevelui();
		p.display(j, n, id);
	}

	@FXML
	private void HandleLog(ActionEvent event) {// �鿴ϵͳ������־
		operationlogui o = new operationlogui();
		o.display(j, n, id);
	}

	@FXML
	private void HandleHelp(ActionEvent event) {
		HelpMan h = new HelpMan();
		h.display(j, n, id);
	}

	@FXML
	private void HandleDevisePassword(ActionEvent event) {
		devisePassword d = new devisePassword();
		d.display(j, n, id);
	}
}