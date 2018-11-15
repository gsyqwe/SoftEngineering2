package financelerui;

import java.io.IOException;

import mainframeui.TField;

import java.rmi.RemoteException;

import java.util.ArrayList;

import java.util.Date;

import javafx.beans.property.SimpleStringProperty;

import javafx.collections.FXCollections;

import javafx.collections.ObservableList;

import javafx.event.ActionEvent;

import javafx.event.EventHandler;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;

import javafx.scene.Scene;

import javafx.scene.control.ContextMenu;

import javafx.scene.control.MenuItem;

import javafx.scene.control.TableColumn;

import javafx.scene.control.TableView;

import javafx.scene.control.TextField;

import javafx.scene.input.KeyCode;

import javafx.scene.input.KeyEvent;

import javafx.scene.layout.AnchorPane;

import javafx.scene.text.Font;

import javafx.stage.Stage;

import mainframeui.TField;

import rmi.BankAccountHelper;

import rmi.OperationLogHelper;

import VO.BankAccountVO;

import VO.LogVO;

import VO.ReceiptVO;

import enums.ResultMessage;

public class getAccountui {// �鿴�˻�

	static String j;

	static String n;

	static Stage pStage;

	private static String id;

	private static ArrayList<BankAccountVO> b1 = new ArrayList<>();// �����˻���ArrayList

	private static TField tf1;// ����

	private static TableView<BankAccountVO> t1;// ��ʾ���б�

	private static TableColumn<BankAccountVO, String> t2;// �˻����

	private static TableColumn<BankAccountVO, String> t3; // �˻�����

	private static TableColumn<BankAccountVO, String> t4;// ���п���

	private static TableColumn<BankAccountVO, String> t5;// ���


	@SuppressWarnings("unchecked")
	public void display(String Job, String name, String ID) {

		// TODO Auto-generated method stub

		Stage primaryStage = new Stage();

		id = ID;

		// �õ�һ�������˻�

		getAccountui get = new getAccountui();

		try {

			b1 = get.getBankAccountList();

		} catch (RemoteException e2) {

			// TODO Auto-generated catch block

			e2.printStackTrace();

		}

		Parent root = null;

		try {

			root = FXMLLoader.load(getClass().getResource("getAccountui.fxml"));

		} catch (IOException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

		AnchorPane a = new AnchorPane();

		TextField text1 = new TextField();

		text1.setStyle("-fx-background-color:null");

		text1.setText("��ӭ��:" + name);

		text1.setLayoutX(913.0);

		text1.setLayoutY(40.0);

		text1.setMinHeight(36.0);

		text1.setMaxHeight(36.0);

		text1.setFont(Font.font("Verdana", 18));

		text1.prefWidth(185.0);

		text1.setEditable(false);

		TextField text2 = new TextField();

		text2.setStyle("-fx-background-color:null");

		text2.setText("��ǰְ��:" + Job);

		text2.setLayoutX(913.0);

		text2.setLayoutY(68.0);

		text2.setMinHeight(30.0);

		text2.setMaxHeight(30.0);

		text2.setFont(Font.font("Verdana", 18));

		text2.prefWidth(185.0);

		text2.setEditable(false);

		t1 = new TableView();

		t1.setLayoutX(232.0);

		t1.setLayoutY(250.0);

		t1.setMinHeight(460.0);

		t1.setMaxHeight(460.0);

		t1.minWidth(654.0);

		t1.maxWidth(654.0);

		t2 = new TableColumn("�˻����");

		t2.setPrefWidth(177.0);

		t3 = new TableColumn("�˻�����");

		t3.setPrefWidth(187.0);

		t4 = new TableColumn("���п���");

		t4.setPrefWidth(138.0);

		t5 = new TableColumn("���");

		t5.setPrefWidth(151.0);

		// ��ʾ

		ObservableList<BankAccountVO> data = FXCollections
				.observableArrayList();

		for (int i = 0; i < b1.size(); i++) {

			data.add(b1.get(i));

		}

		t2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData

		.getValue().getId()));// ���

		t3.setCellValueFactory(cellData -> new SimpleStringProperty(""
				+ cellData

				.getValue().getName()));// ����

		t4.setCellValueFactory(cellData -> new SimpleStringProperty(cellData

		.getValue().getCardNumber()));// ����

		t5.setCellValueFactory(cellData -> new SimpleStringProperty(Double
				.toString(cellData.getValue().getAmount())));// ���

		t1.setItems(data);

		t1.getColumns().addAll(t2, t3, t4, t5);

		t1.setStyle("-fx-background-color:null");

		// �����Ҽ���ť

		ContextMenu cme = new ContextMenu();

		// �����Ҽ��˵���һЩ��

		MenuItem m1 = new MenuItem("ɾ��");

		cme.getItems().add(m1);

		MenuItem m2 = new MenuItem("�޸�");

		cme.getItems().add(m2);

		t1.setContextMenu(cme);

		// ��������¼�

		m1.setOnAction(new EventHandler<ActionEvent>() {// ɾ��

			public void handle(ActionEvent e) {

				// �õ���ѡ���index,�õ����
				int index;
				index = t1.getSelectionModel().getSelectedIndex();
				System.out.println(index);
				BankAccountVO u = b1.get(index);// �õ���ӦIndex��UserVO
				deleteAccount delete=new deleteAccount();//��Ҫ��������
				delete.display(id,u,t1,b1);

			}

		});

		m2.setOnAction(new EventHandler<ActionEvent>() {// �޸�

			public void handle(ActionEvent e) {

				// �õ���ѡ���index,�õ����

				int index;

				index = t1.getSelectionModel().getSelectedIndex();

				BankAccountVO vo = b1.get(index);
				// �����޸ĵĽ���,��vo����ȥ
				deviseAccountui d=new deviseAccountui();
				d.display(j,n,id,vo,t1,b1);

			}

		});

		// ����

		tf1 = new TField();

		tf1.setLayoutX(622);

		tf1.setLayoutY(167);

		tf1.setMinHeight(30);

		tf1.setMaxHeight(30);

		tf1.setPrefWidth(155);

		tf1.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {

				if (event.getCode() == KeyCode.ENTER) {

					String key = tf1.getText();

					BankAccountHelper helper = BankAccountHelper.getInstance();

					ArrayList<BankAccountVO> vos = new ArrayList<BankAccountVO>();

					try {

						BankAccountVO vo1 = helper.getService().findByID(key);

						BankAccountVO vo2 = helper.getService()

						.findByCardNumber(key);

						BankAccountVO vo3 = helper.getService().findByName(key);

						vos.add(vo1);

						if (vo2 != vo1) {

							vos.add(vo2);

						}

						if (vo3 != vo1 && vo3 != vo2) {

							vos.add(vo3);

						}

					} catch (RemoteException e) {

						// TODO Auto-generated catch block

						e.printStackTrace();

					}

					// ��ʾvos���������

				}

			}

		});

		a.getChildren().addAll(root, t1, text1, text2, tf1);

		Scene s = new Scene(a, 1080, 800);

		primaryStage.setTitle("������Ա");

		primaryStage.setScene(s);

		j = Job;

		n = name;

		pStage = primaryStage;

		primaryStage.show();

	}


	public ArrayList<BankAccountVO> getBankAccountList() throws RemoteException {

		BankAccountHelper helper = BankAccountHelper.getInstance();

		return helper.getService().getBankAccountList();

	}

	@FXML
	private void HandleHelp(ActionEvent event) {

		HelpF f = new HelpF();

		f.display(j, n, id);

	}

	@FXML
	private void HandleSure(ActionEvent event) {

		pStage.close();

	}

	@FXML
	private void HandleCancle(ActionEvent event) {

		pStage.close();

	}

	@FXML
	private void HandleExport(ActionEvent event) {

		pStage.close();

	}

	@FXML
	private void HandleOut(ActionEvent event) {

		pStage.close();

	}

	@FXML
	private void HandleEsc(ActionEvent event) {

		pStage.close();

	}

}