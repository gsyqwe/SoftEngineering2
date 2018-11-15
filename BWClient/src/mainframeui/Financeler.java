package mainframeui;

import java.io.IOException;
import java.rmi.RemoteException;

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
import rmi.InitializeAccountHelper;
import VO.InitializeAccountVO;
import financelerui.EmailF;
import financelerui.GetReceipt;
import financelerui.HelpF;
import financelerui.Moneyui;
import financelerui.Record;
import financelerui.addAccountui;
import financelerui.businessconditionui;
import financelerui.businessprocessui;
import financelerui.cost;
import financelerui.drafts;
import financelerui.getAccountui;
import financelerui.getMoneyui;
import financelerui.makeAccountFirst;
import financelerui.operationlog;
import financelerui.salelistui;

public class Financeler {
	static String j;
	static String n;
	static Stage pStage;
	private static String id;

	@SuppressWarnings("unchecked")
	public void display(String Job, String name, String ID) {
		// TODO Auto-generated method stub
		Stage primaryStage = new Stage();
		id = ID;
		AnchorPane a = new AnchorPane();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("Financeler.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// �ĸ�������
		// �˻�����
		ComboBox<String> c1 = new ComboBox();
		c1.setValue("�˻����� ");
		c1.getItems().addAll("�����˻�", "�����˻�");
		c1.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener() {
					@Override
					public void changed(ObservableValue arg0, Object arg1,
							Object arg2) {
						// TODO Auto-generated method stub
						if (arg2.toString() == "�����˻�") {// �޸�������Ӧ��Ҫ���µ�¼
							addAccountui de = new addAccountui();
							try {
								de.display(Job, name, id);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else if (arg2.toString() == "�����˻�") {
							getAccountui get = new getAccountui();
							try {
								get.display(Job, name, id);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				});
		c1.setLayoutX(57.0);
		c1.setLayoutY(95.0);
		c1.setMinHeight(30.0);
		c1.setMaxHeight(30.0);
		c1.setPrefWidth(130.0);

		// �ƶ�����
		ComboBox<String> c2 = new ComboBox();
		c2.setValue("�ƶ����� ");
		c2.getItems().addAll("�ƶ����", "�ƶ��տ", "�ƶ��ֽ���õ�", "�ݸ���", "�鿴����");
		c2.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener() {
					@Override
					public void changed(ObservableValue arg0, Object arg1,
							Object arg2) {
						// TODO Auto-generated method stub
						if (arg2.toString() == "�ƶ����") {// �޸�������Ӧ��Ҫ���µ�¼
							Moneyui de = new Moneyui();
							try {
								de.display(Job, name, id);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else if (arg2.toString() == "�ƶ��տ") {
							getMoneyui de = new getMoneyui();
							try {
								de.display(Job, name, id);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else if (arg2.toString() == "�ƶ��ֽ���õ�") {
							cost de = new cost();
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
							GetReceipt G = new GetReceipt();
							G.display(Job, name, id);
						}
					}
				});
		c2.setLayoutX(186.0);
		c2.setLayoutY(95.0);
		c2.setMinHeight(30.0);
		c2.setMaxHeight(30.0);
		c2.setPrefWidth(130.0);

		// �鿴����
		ComboBox<String> c3 = new ComboBox();
		c3.setValue("�鿴���� ");
		c3.getItems().addAll("�鿴��Ӫ���̱�", "�鿴��Ӫ�����", "�鿴������ϸ��");
		c3.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener() {
					@Override
					public void changed(ObservableValue arg0, Object arg1,
							Object arg2) {
						// TODO Auto-generated method stub
						if (arg2.toString() == "�鿴��Ӫ���̱�") {// �޸�������Ӧ��Ҫ���µ�¼
							businessprocessui de = new businessprocessui();
							try {
								de.display(Job, name, id);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else if (arg2.toString() == "�鿴��Ӫ�����") {
							businessconditionui de = new businessconditionui();
							try {
								de.display(Job, name, id);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else if (arg2.toString() == "�鿴������ϸ��") {
							salelistui de = new salelistui();
							try {
								de.display(Job, name, id);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				});
		c3.setLayoutX(315.0);
		c3.setLayoutY(95.0);
		c3.setMinHeight(30.0);
		c3.setMaxHeight(30.0);
		c3.setPrefWidth(130.0);

		// ϵͳ������־
		ComboBox<String> c4 = new ComboBox();
		c4.setValue("�鿴��־ ");
		c4.getItems().addAll("�鿴ϵͳ������־");
		c4.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener() {
					@Override
					public void changed(ObservableValue arg0, Object arg1,
							Object arg2) {
						// TODO Auto-generated method stub
						if (arg2.toString() == "�鿴ϵͳ������־") {// �޸�������Ӧ��Ҫ���µ�¼
							operationlog de = new operationlog();
							try {
								de.display(Job, name, id);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				});
		c4.setLayoutX(445.0);
		c4.setLayoutY(95.0);
		c4.setMinHeight(30.0);
		c4.setMaxHeight(30.0);
		c4.setPrefWidth(130.0);
		// �ڳ�����
		ComboBox<String> c5 = new ComboBox();
		c5.setValue("�ڳ����˹���");
		c5.getItems().addAll("�ڳ�����", "�ڳ����˼�¼");
		c5.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener() {
					@Override
					public void changed(ObservableValue arg0, Object arg1,
							Object arg2) {
						// TODO Auto-generated method stub
						if (arg2.toString() == "�ڳ�����") {
							Financeler f = new Financeler();
							InitializeAccountVO in = new InitializeAccountVO();
							try {
								in = f.getRemain();
							} catch (RemoteException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							makeAccountFirst m = new makeAccountFirst();
							m.display(j, n, id, in);
						} else if (arg2.toString() == "�ڳ����˼�¼") {// �ڳ����˼�¼
							Record r = new Record();
							r.display(Job, name, id);
						}
					}
				});
		c5.setLayoutX(575.0);
		c5.setLayoutY(95.0);
		c5.setMinHeight(30.0);
		c5.setMaxHeight(30.0);
		c5.setPrefWidth(150.0);

		Text te = new Text("�����˻�");
		te.setLayoutX(75);
		te.setLayoutY(330);
		te.setFont(Font.font(20));

		Text te1 = new Text("�ƶ�����");
		te1.setLayoutX(225);
		te1.setLayoutY(330);
		te1.setFont(Font.font(20));

		Text te2 = new Text("�鿴����");
		te2.setLayoutX(365);
		te2.setLayoutY(330);
		te2.setFont(Font.font(20));

		Text te3 = new Text("�ڳ�����");
		te3.setLayoutX(505);
		te3.setLayoutY(330);
		te3.setFont(Font.font(20));

		Text te4 = new Text("�鿴������־");
		te4.setLayoutX(635);
		te4.setLayoutY(330);
		te4.setFont(Font.font(20));

		TextField text1 = new TextField();
		text1.setStyle("-fx-background-color:null");
		text1.setText("��ӭ��:" + name);
		text1.setLayoutX(613.0);
		text1.setLayoutY(40.0);
		text1.setMinHeight(36.0);
		text1.setMaxHeight(36.0);
		text1.setFont(Font.font("Verdana", 16));
		text1.prefWidth(185.0);
		text1.setEditable(false);

		TextField text2 = new TextField();
		text2.setStyle("-fx-background-color:null");
		text2.setText("��ǰְ��:" + Job);
		text2.setLayoutX(613.0);
		text2.setLayoutY(68.0);
		text2.setMinHeight(30.0);
		text2.setMaxHeight(30.0);
		text2.setFont(Font.font("Verdana", 16));
		text2.prefWidth(185.0);
		text2.setEditable(false);

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

		a.getChildren().addAll(root, c1, c2, c3, c4, c5, text1, text2, t1, t2,
				t3, t4, te, te1, te2, te3, te4);

		Scene s = new Scene(a, 800, 500);
		primaryStage.setTitle("������Ա");
		primaryStage.setScene(s);
		j = Job;
		n = name;
		pStage = primaryStage;
		primaryStage.show();
	}

	private InitializeAccountVO getRemain() throws RemoteException {
		InitializeAccountHelper helper = InitializeAccountHelper.getInstance();
		return helper.getService().getRemainingData();
	}

	@FXML
	private void HandleMakeAccount(ActionEvent event) {
		Financeler f = new Financeler();
		InitializeAccountVO in = new InitializeAccountVO();
		try {
			in = f.getRemain();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		makeAccountFirst m = new makeAccountFirst();
		m.display(j, n, id, in);
	}

	@FXML
	private void HandleEmail(ActionEvent event) {
		EmailF e = new EmailF();
		e.display(j, n, id);
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
		HelpF h = new HelpF();
		h.display(j, n, id);
	}

	@FXML
	private void HandleAddAccount(ActionEvent event) {
		addAccountui a = new addAccountui();
		a.display(j, n, id);
	}

	@FXML
	private void HandleMakeReceipt(ActionEvent event) {
		cost c = new cost();
		c.display(j, n, id);
	}

	@FXML
	private void HandleCheckReport(ActionEvent event) {
		businessconditionui b = new businessconditionui();
		b.display(j, n, id);
	}

	@FXML
	private void HandleOperationLog(ActionEvent event) {
		operationlog o = new operationlog();
		o.display(j, n, id);
	}

	@FXML
	private void HandleDevisePassword(ActionEvent event) {
		devisePassword d = new devisePassword();
		d.display(j, n, id);
	}
}
