package manganerui;

import inventoryui.AlarmX;
import inventoryui.OverflowX;
import inventoryui.lossX;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import mainframeui.AlertBox;
import enums.FinancialReceiptType;
import enums.InventoryReceiptType;
import enums.ReceiptType;
import enums.SalesReceiptType;
import financelerui.MoneyuiX;
import financelerui.costX;
import financelerui.getMoneyuiX;
import rmi.CheckReceiptHelper;
import rmi.FinancialReceiptHelper;
import rmi.InventoryReceiptHelper;
import rmi.SalesHelper;
import salerui.InBackX;
import salerui.InX;
import salerui.SaleBackuiX;
import salerui.SaleuiX;
import VO.FinancialReceiptVO;
import VO.InventoryReceiptVO;
import VO.ReceiptVO;
import VO.SalesReceiptVO;
import VO.checkbox;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
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

public class checkreportui {// ��˱���
	private static String j;
	private static String n;
	private static Stage pStage;
	private static String id;
	private static TableView<Person> t;// �б���ʾ
	private static TableColumn<Person, String> t1;// ��������
	private static TableColumn<Person, String> t2;// ���ݱ��
	private static TableColumn<Person, String> t3;// �ύ�˱��
	private static TableColumn<Person, String> t4;// ��������
	private static TableColumn<Person, CheckBox> t5;
	private TField tf1;// ɸѡ
	private static CheckBox ch1;// ����
	private static CheckBox ch2;// ҵ��Ա
	private static ArrayList<ReceiptVO> mylist = new ArrayList();// �õ����еĵ���
	private static ArrayList<Person> list = new ArrayList();// ��ʾ��ArrayList
	private static ArrayList<ReceiptVO> mylist1 = new ArrayList();// �õ�ɸѡ��ĵ���

	public void display(String Job, String name, String ID) {
		// TODO Auto-generated method stub
		Stage primaryStage = new Stage();
		id = ID;
		mylist.clear();
		list.clear();
		mylist1.clear();
		Parent root = null;
		try {
			root = FXMLLoader
					.load(getClass().getResource("checkreportui.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ���ȸ���mylist
		checkreportui chec = new checkreportui();
		try {
			mylist = chec.getAllReceipt();
		} catch (RemoteException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		// ����ת��
		for (int i = 0; i < mylist.size(); i++) {
			Person p = new Person(mylist.get(i));
			list.add(p);
		}

		AnchorPane a = new AnchorPane();
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

		t = new TableView();
		t.setLayoutX(37.0);
		t.setLayoutY(193.0);
		t.setMinHeight(240.0);
		t.setMaxHeight(240.0);
		t.setMinWidth(725.0);
		t.setMaxWidth(725.0);
		t1 = new TableColumn("��������");
		t1.setPrefWidth(124.0);
		t2 = new TableColumn("���ݱ��");
		t2.setPrefWidth(112.0);
		t3 = new TableColumn("�ύ�˵ı��");
		t3.setPrefWidth(123.0);
		t4 = new TableColumn("��������");
		t4.setPrefWidth(305.0);
		t5 = new TableColumn();
		t.getColumns().addAll(t1, t2, t3, t4, t5);
		t.setStyle("-fx-background-color:null");

		// ��Ҫʹ��checkBox��ʹ������һ����ѡ��������ѡ�񣬽�������
		// ��Ҫ��ObserValue�����
		ObservableList<Person> data = FXCollections.observableArrayList();
		for (int i = 0; i < mylist.size(); i++) {
			data.add(list.get(i));
		}
		t1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData
				.getValue().getReceipt().getType().toString()));// ��������
		t2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData
				.getValue().getReceipt().getId()));// ���ݱ��
		t3.setCellValueFactory(cellData -> new SimpleStringProperty(cellData
				.getValue().getReceipt().getOperatorID()));// �ύ�˵ı��
		t4.setCellValueFactory(cellData -> new SimpleStringProperty(cellData
				.getValue().getReceipt().getLineItem().get(0).getComment()));// ��������
		t5.setCellValueFactory(cellData -> cellData.getValue().cb.getCheckBox());

		t.setItems(data);

		// �����Ҽ���ť
		ContextMenu cme = new ContextMenu();
		// �����Ҽ��˵���һЩ��
		MenuItem m1 = new MenuItem("ͨ��");
		MenuItem m2 = new MenuItem("��ͨ��");
		MenuItem m3 = new MenuItem("�޸�");
		cme.getItems().addAll(m1, m2, m3);
		t.setContextMenu(cme);
		// ��������¼�
		m1.setOnAction(new EventHandler<ActionEvent>() {// ͨ��
			@Override
			public void handle(ActionEvent e) {
				// �õ���ѡ���index,�õ����
				int index;
				index = t.getSelectionModel().getSelectedIndex();
				ReceiptVO u = mylist.get(index);// �õ���ӦIndex��UserVO
				// д���ͨ���¼�
				CheckReceiptHelper helper = CheckReceiptHelper.getInstance();
				ArrayList<ReceiptVO> rlist = new ArrayList<>();
				rlist.add(u);
				try {
					ArrayList<String> result = helper.getService().passByList(
							rlist, id);
					if (result.size() == 0) {
						AlertBox a = new AlertBox();
						a.display("��˳ɹ�", "��˳ɹ�");
						checkreportui chec = new checkreportui();
						try {
							mylist = chec.getAllReceipt();
						} catch (RemoteException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}

						// ����ת��
						for (int i = 0; i < mylist.size(); i++) {
							Person p = new Person(mylist.get(i));
							list.add(p);
						}
						ObservableList<Person> data = FXCollections
								.observableArrayList();
						for (int i = 0; i < mylist.size(); i++) {
							data.add(list.get(i));
						}
						t.setItems(data);
					} else {
						AlertBox a = new AlertBox();
						a.display("���ʧ��", "���ʧ��");
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}// ���result����ŵ���ͨ�����ʧ�ܵĵ�����Ϣ
			}
		});
		// ��ͨ�����¼�
		m2.setOnAction(new EventHandler<ActionEvent>() {// ��ͨ��
			@Override
			public void handle(ActionEvent e) {
				int index;
				index = t.getSelectionModel().getSelectedIndex();
				ReceiptVO u = mylist.get(index);// �õ���ӦIndex��UserVO
				// д���ͨ���¼�
				CheckReceiptHelper helper = CheckReceiptHelper.getInstance();
				ArrayList<ReceiptVO> rlist = new ArrayList<>();
				rlist.add(u);
				try {
					ArrayList<String> result = helper.getService()
							.failPassByList(rlist, id);
					if (result.size() == 0) {
						AlertBox a = new AlertBox();
						a.display("��˳ɹ�", "��˳ɹ�");
						checkreportui chec = new checkreportui();
						try {
							mylist = chec.getAllReceipt();
						} catch (RemoteException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}

						// ����ת��
						for (int i = 0; i < mylist.size(); i++) {
							Person p = new Person(mylist.get(i));
							list.add(p);
						}
						ObservableList<Person> data = FXCollections
								.observableArrayList();
						for (int i = 0; i < mylist.size(); i++) {
							data.add(list.get(i));
						}
						t.setItems(data);
					} else {
						AlertBox a = new AlertBox();
						a.display("���ʧ��", "���ʧ��");
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}// ���result����ŵ���ͨ�����ʧ�ܵĵ�����Ϣ
			}
		});

		// �༭
		m3.setOnAction(new EventHandler<ActionEvent>() {// �༭
			@Override
			public void handle(ActionEvent e) {
				// �õ���ѡ���index,�õ����
				int index;
				index = t.getSelectionModel().getSelectedIndex();
				ReceiptVO r1 = mylist.get(index);// �õ���ӦIndex��UserVO
				// д��ı༭�¼�������һ���༭����ȥ,��������Type�������Ӧ���½����Ǹ�����ȥ
				if (r1.getType() == ReceiptType.SALE_RECEIPT) {
					SalesReceiptVO sales = new SalesReceiptVO();
					SalesHelper helper = SalesHelper.getInstance();
					try {
						sales = helper.getService().getReceiptByID(
								r1.getId());
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (sales.getSalesType().equals(SalesReceiptType.SALES)) {
						SaleuiX s = new SaleuiX();
						s.display(j, n, id, sales);
					} else if (sales.getType().equals(
							SalesReceiptType.SALES_RETURN)) {
						SaleBackuiX s = new SaleBackuiX();
						s.display(j, n, id, sales);
					} else if (sales.getType()
							.equals(SalesReceiptType.PURCHASE)) {
						InX i = new InX();
						i.display(j, n, id, sales);
					} else {
						InBackX i = new InBackX();
						i.display(j, n, id, sales);
					}

				} else if (r1.getType() == ReceiptType.FINANCIAL_RECEIPT) {
					FinancialReceiptHelper helper = FinancialReceiptHelper
							.getInstance();
					FinancialReceiptVO f1 = new FinancialReceiptVO();
					try {
						f1 = helper.getService().findByID(r1.getId());
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (f1.getType().equals(FinancialReceiptType.BILL)) {
						MoneyuiX m = new MoneyuiX();
						m.display(j, n, id, f1);
					} else if (f1.getType().equals(
							FinancialReceiptType.DEBIT_NOTE)) {
						getMoneyuiX m = new getMoneyuiX();
						m.display(j, n, id, f1);
					} else if (f1.getType().equals(
							FinancialReceiptType.CASH_CLAIM)) {
						costX c = new costX();
						c.display(j, n, id, f1);
					}
				} else if (r1.getType() == ReceiptType.INVENTORY_RECEIPT) {
					InventoryReceiptHelper helper = InventoryReceiptHelper
							.getInstance();
					InventoryReceiptVO i1 = new InventoryReceiptVO();
					try {
						i1 = helper.getService().findByID(r1.getId());
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
					if (i1.getType().equals(InventoryReceiptType.OVERFLOW)) {
						OverflowX o = new OverflowX();
						o.display(j, n, id, i1);
					} else if (i1.getType().equals(
							InventoryReceiptType.BREAKAGE)) {
						lossX l = new lossX();
						l.display(j, n, id, i1);
					} else if (i1.getType().equals(InventoryReceiptType.ALARM)) {
						AlarmX a = new AlarmX();
						a.display(j, n, id, i1);
					}
				}

			}
		});

		ch1 = new CheckBox("����");// �������͵ĸ�ѡ��
		ch1.setLayoutX(485);
		ch1.setLayoutY(163);
		ch2 = new CheckBox("ҵ��Ա");// ҵ��Ա�ĸ�ѡ��
		ch2.setLayoutX(560);
		ch2.setLayoutY(163);

		// ɸѡ
		tf1 = new TField();
		tf1.setMinHeight(25);
		tf1.setMaxHeight(25);
		tf1.setLayoutX(534);
		tf1.setLayoutY(135);
		tf1.setPrefWidth(243);
		tf1.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER) {
					// ɸѡ�¼�
					if (ch1.isSelected() && ch2.isSelected()) {
						String s = tf1.getText();
						String str[] = s.split(" ");
						// str[0]�ǵ�������
						// str[1]��ҵ��Ա
						for (int i = 0; i < mylist.size(); i++) {
							if (mylist.get(i).getType().toString() == str[0]
									&& mylist.get(i).getOperatorID() == str[1]) {
								mylist1.add(mylist.get(i));
							}
						}
					} else if (ch1.isSelected() && (!ch2.isSelected())) {
						String s = tf1.getText();// s�ǵ�������
						for (int i = 0; i < mylist.size(); i++) {
							if (mylist.get(i).getType().toString() == s) {
								mylist1.add(mylist.get(i));
							}
						}
					} else if (!ch1.isSelected() && ch2.isSelected()) {
						String s = tf1.getText();// s��ҵ��Ա
						for (int i = 0; i < mylist.size(); i++) {
							if (mylist.get(i).getOperatorID() == s) {
								mylist1.add(mylist.get(i));
							}
						}
					}
					// Ȼ������ʾmylist1������
					list.clear();// �Ȱ�list��գ�Ȼ�������
					// ��ת��ΪPerson
					for (int i = 0; i < mylist1.size(); i++) {
						Person p = new Person(mylist1.get(i));
						list.add(p);
					}
					// ��Ҫʹ��checkBox��ʹ������һ����ѡ��������ѡ�񣬽�������
					// ��Ҫ��ObserValue�����
					ObservableList<Person> data = FXCollections
							.observableArrayList();
					for (int i = 0; i < mylist1.size(); i++) {
						data.add(list.get(i));
					}
					t1.setCellValueFactory(cellData -> new SimpleStringProperty(
							cellData.getValue().getReceipt().getType()
									.toString()));// ��������
					t2.setCellValueFactory(cellData -> new SimpleStringProperty(
							cellData.getValue().getReceipt().getId()));// ���ݱ��
					t3.setCellValueFactory(cellData -> new SimpleStringProperty(
							cellData.getValue().getReceipt().getOperatorID()));// �ύ�˵ı��
					t4.setCellValueFactory(cellData -> new SimpleStringProperty(
							cellData.getValue().getReceipt().getLineItem()
									.get(0).getComment()));// ��������
					t5.setCellValueFactory(cellData -> cellData.getValue().cb
							.getCheckBox());

					t.setItems(data);
				}
			}
		});

		a.getChildren().addAll(root, t, text1, text2, tf1, ch1, ch2);

		Scene s = new Scene(a, 800, 500);
		primaryStage.setTitle("�ܾ���");
		primaryStage.setScene(s);
		j = Job;
		n = name;
		pStage = primaryStage;
		primaryStage.show();
	}

	@FXML
	private void HandleHelp(ActionEvent event) {
		HelpMan f = new HelpMan();
		f.display(j, n, id);
	}

	@FXML
	private void HandleCancle(ActionEvent event) {
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

	@FXML
	private void HandleApprove(ActionEvent event) {// ͨ���ķ���
		ArrayList<ReceiptVO> rlist = new ArrayList<>();// ��õĵ���
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).cb.isSelected()) {
				rlist.add(mylist.get(i));
			}
		}
		for (int i = 0; i < rlist.size(); i++) {
			for (int j = 0; j < mylist.size(); j++) {
				if (mylist.get(j).getId().equals(rlist.get(i).getId())) {
					mylist.remove(j);
				}
			}
		}

		CheckReceiptHelper helper = CheckReceiptHelper.getInstance();

		try {

			ArrayList<String> result = helper.getService()
					.passByList(rlist, id);// ���result����ŵ���ͨ�����ʧ�ܵĵ�����Ϣ

			if (result.size() != 0) {// �е������ʧ�ܣ�������ʾ
				AlertBox a = new AlertBox();
				a.display("�������ʧ��", "�������ʧ��");
			} else {
				AlertBox a = new AlertBox();
				a.display("������˳ɹ�", "������˳ɹ�");
				checkreportui chec = new checkreportui();
				// ����ת��
				list.clear();
				for (int i = 0; i < mylist.size(); i++) {
					Person p = new Person(mylist.get(i));
					list.add(p);
				}
				ObservableList<Person> data = FXCollections
						.observableArrayList();
				for (int i = 0; i < mylist.size(); i++) {
					data.add(list.get(i));
				}
				t.setItems(data);

			}

		} catch (RemoteException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

	}

	private ArrayList<ReceiptVO> getAllReceipt() throws RemoteException {
		CheckReceiptHelper helper = CheckReceiptHelper.getInstance();
		return helper.getService().showTheUncheckedReceiptList();
	}

	@FXML
	private void HandleDisapprove(ActionEvent event) {// ��ͨ���ķ���
		ArrayList<ReceiptVO> rlist = new ArrayList<>();// ��õĵ���
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).cb.isSelected()) {
				rlist.add(mylist.get(i));
				mylist.remove(i);
			}
		}

		CheckReceiptHelper helper = CheckReceiptHelper.getInstance();

		try {

			ArrayList<String> result = helper.getService().failPassByList(
					rlist, id);// ���result����ŵ���ͨ�����ʧ�ܵĵ�����Ϣ

			if (result.size() != 0) {// �е������ʧ�ܣ�������ʾ
				AlertBox a = new AlertBox();
				a.display("�е������ʧ��", "�е������ʧ��");
			} else {
				AlertBox a = new AlertBox();
				a.display("������˳ɹ�", "������˳ɹ�");

				list.clear();
				// ����ת��
				for (int i = 0; i < mylist.size(); i++) {
					Person p = new Person(mylist.get(i));
					list.add(p);
				}
				ObservableList<Person> data = FXCollections
						.observableArrayList();
				for (int i = 0; i < mylist.size(); i++) {
					data.add(list.get(i));
				}
				t.setItems(data);
			}

		} catch (RemoteException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

	}
}

class Person {
	ReceiptVO r;
	public checkbox cb = new checkbox();

	Person(ReceiptVO r) {
		this.r = r;
	}

	public ReceiptVO getReceipt() {
		return r;
	}
}
