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

public class checkreportui {// 审核报表
	private static String j;
	private static String n;
	private static Stage pStage;
	private static String id;
	private static TableView<Person> t;// 列表显示
	private static TableColumn<Person, String> t1;// 单据类型
	private static TableColumn<Person, String> t2;// 单据编号
	private static TableColumn<Person, String> t3;// 提交人编号
	private static TableColumn<Person, String> t4;// 单据内容
	private static TableColumn<Person, CheckBox> t5;
	private TField tf1;// 筛选
	private static CheckBox ch1;// 类型
	private static CheckBox ch2;// 业务员
	private static ArrayList<ReceiptVO> mylist = new ArrayList();// 得到所有的单据
	private static ArrayList<Person> list = new ArrayList();// 显示的ArrayList
	private static ArrayList<ReceiptVO> mylist1 = new ArrayList();// 得到筛选后的单据

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

		// 你先给我mylist
		checkreportui chec = new checkreportui();
		try {
			mylist = chec.getAllReceipt();
		} catch (RemoteException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		// 进行转换
		for (int i = 0; i < mylist.size(); i++) {
			Person p = new Person(mylist.get(i));
			list.add(p);
		}

		AnchorPane a = new AnchorPane();
		TextField text1 = new TextField();
		text1.setStyle("-fx-background-color:null");
		text1.setText("欢迎您:" + name);
		text1.setLayoutX(613.0);
		text1.setLayoutY(40.0);
		text1.setMinHeight(36.0);
		text1.setMaxHeight(36.0);
		text1.setFont(Font.font("Verdana", 18));
		text1.prefWidth(185.0);
		text1.setEditable(false);

		TextField text2 = new TextField();
		text2.setStyle("-fx-background-color:null");
		text2.setText("当前职务:" + Job);
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
		t1 = new TableColumn("单据类型");
		t1.setPrefWidth(124.0);
		t2 = new TableColumn("单据编号");
		t2.setPrefWidth(112.0);
		t3 = new TableColumn("提交人的编号");
		t3.setPrefWidth(123.0);
		t4 = new TableColumn("单据内容");
		t4.setPrefWidth(305.0);
		t5 = new TableColumn();
		t.getColumns().addAll(t1, t2, t3, t4, t5);
		t.setStyle("-fx-background-color:null");

		// 需要使用checkBox来使用增加一个复选框来进行选择，进行批量
		// 需要用ObserValue来添加
		ObservableList<Person> data = FXCollections.observableArrayList();
		for (int i = 0; i < mylist.size(); i++) {
			data.add(list.get(i));
		}
		t1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData
				.getValue().getReceipt().getType().toString()));// 单据类型
		t2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData
				.getValue().getReceipt().getId()));// 单据编号
		t3.setCellValueFactory(cellData -> new SimpleStringProperty(cellData
				.getValue().getReceipt().getOperatorID()));// 提交人的编号
		t4.setCellValueFactory(cellData -> new SimpleStringProperty(cellData
				.getValue().getReceipt().getLineItem().get(0).getComment()));// 单据内容
		t5.setCellValueFactory(cellData -> cellData.getValue().cb.getCheckBox());

		t.setItems(data);

		// 增加右键按钮
		ContextMenu cme = new ContextMenu();
		// 增加右键菜单的一些项
		MenuItem m1 = new MenuItem("通过");
		MenuItem m2 = new MenuItem("不通过");
		MenuItem m3 = new MenuItem("修改");
		cme.getItems().addAll(m1, m2, m3);
		t.setContextMenu(cme);
		// 增加这个事件
		m1.setOnAction(new EventHandler<ActionEvent>() {// 通过
			@Override
			public void handle(ActionEvent e) {
				// 得到被选择的index,得到编号
				int index;
				index = t.getSelectionModel().getSelectedIndex();
				ReceiptVO u = mylist.get(index);// 得到对应Index的UserVO
				// 写你的通过事件
				CheckReceiptHelper helper = CheckReceiptHelper.getInstance();
				ArrayList<ReceiptVO> rlist = new ArrayList<>();
				rlist.add(u);
				try {
					ArrayList<String> result = helper.getService().passByList(
							rlist, id);
					if (result.size() == 0) {
						AlertBox a = new AlertBox();
						a.display("审核成功", "审核成功");
						checkreportui chec = new checkreportui();
						try {
							mylist = chec.getAllReceipt();
						} catch (RemoteException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}

						// 进行转换
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
						a.display("审核失败", "审核失败");
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}// 这个result里面放的是通过审核失败的单据信息
			}
		});
		// 不通过的事件
		m2.setOnAction(new EventHandler<ActionEvent>() {// 不通过
			@Override
			public void handle(ActionEvent e) {
				int index;
				index = t.getSelectionModel().getSelectedIndex();
				ReceiptVO u = mylist.get(index);// 得到对应Index的UserVO
				// 写你的通过事件
				CheckReceiptHelper helper = CheckReceiptHelper.getInstance();
				ArrayList<ReceiptVO> rlist = new ArrayList<>();
				rlist.add(u);
				try {
					ArrayList<String> result = helper.getService()
							.failPassByList(rlist, id);
					if (result.size() == 0) {
						AlertBox a = new AlertBox();
						a.display("审核成功", "审核成功");
						checkreportui chec = new checkreportui();
						try {
							mylist = chec.getAllReceipt();
						} catch (RemoteException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}

						// 进行转换
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
						a.display("审核失败", "审核失败");
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}// 这个result里面放的是通过审核失败的单据信息
			}
		});

		// 编辑
		m3.setOnAction(new EventHandler<ActionEvent>() {// 编辑
			@Override
			public void handle(ActionEvent e) {
				// 得到被选择的index,得到编号
				int index;
				index = t.getSelectionModel().getSelectedIndex();
				ReceiptVO r1 = mylist.get(index);// 得到对应Index的UserVO
				// 写你的编辑事件，跳到一个编辑界面去,根据它的Type跳到你对应的新建的那个界面去
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

		ch1 = new CheckBox("类型");// 单据类型的复选框
		ch1.setLayoutX(485);
		ch1.setLayoutY(163);
		ch2 = new CheckBox("业务员");// 业务员的复选框
		ch2.setLayoutX(560);
		ch2.setLayoutY(163);

		// 筛选
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
					// 筛选事件
					if (ch1.isSelected() && ch2.isSelected()) {
						String s = tf1.getText();
						String str[] = s.split(" ");
						// str[0]是单据类型
						// str[1]是业务员
						for (int i = 0; i < mylist.size(); i++) {
							if (mylist.get(i).getType().toString() == str[0]
									&& mylist.get(i).getOperatorID() == str[1]) {
								mylist1.add(mylist.get(i));
							}
						}
					} else if (ch1.isSelected() && (!ch2.isSelected())) {
						String s = tf1.getText();// s是单据类型
						for (int i = 0; i < mylist.size(); i++) {
							if (mylist.get(i).getType().toString() == s) {
								mylist1.add(mylist.get(i));
							}
						}
					} else if (!ch1.isSelected() && ch2.isSelected()) {
						String s = tf1.getText();// s是业务员
						for (int i = 0; i < mylist.size(); i++) {
							if (mylist.get(i).getOperatorID() == s) {
								mylist1.add(mylist.get(i));
							}
						}
					}
					// 然后来显示mylist1的内容
					list.clear();// 先把list清空，然后在添加
					// 先转换为Person
					for (int i = 0; i < mylist1.size(); i++) {
						Person p = new Person(mylist1.get(i));
						list.add(p);
					}
					// 需要使用checkBox来使用增加一个复选框来进行选择，进行批量
					// 需要用ObserValue来添加
					ObservableList<Person> data = FXCollections
							.observableArrayList();
					for (int i = 0; i < mylist1.size(); i++) {
						data.add(list.get(i));
					}
					t1.setCellValueFactory(cellData -> new SimpleStringProperty(
							cellData.getValue().getReceipt().getType()
									.toString()));// 单据类型
					t2.setCellValueFactory(cellData -> new SimpleStringProperty(
							cellData.getValue().getReceipt().getId()));// 单据编号
					t3.setCellValueFactory(cellData -> new SimpleStringProperty(
							cellData.getValue().getReceipt().getOperatorID()));// 提交人的编号
					t4.setCellValueFactory(cellData -> new SimpleStringProperty(
							cellData.getValue().getReceipt().getLineItem()
									.get(0).getComment()));// 单据内容
					t5.setCellValueFactory(cellData -> cellData.getValue().cb
							.getCheckBox());

					t.setItems(data);
				}
			}
		});

		a.getChildren().addAll(root, t, text1, text2, tf1, ch1, ch2);

		Scene s = new Scene(a, 800, 500);
		primaryStage.setTitle("总经理");
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
	private void HandleApprove(ActionEvent event) {// 通过的方法
		ArrayList<ReceiptVO> rlist = new ArrayList<>();// 获得的单据
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
					.passByList(rlist, id);// 这个result里面放的是通过审核失败的单据信息

			if (result.size() != 0) {// 有单据审核失败，弹窗提示
				AlertBox a = new AlertBox();
				a.display("单据审核失败", "单据审核失败");
			} else {
				AlertBox a = new AlertBox();
				a.display("单据审核成功", "单据审核成功");
				checkreportui chec = new checkreportui();
				// 进行转换
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
	private void HandleDisapprove(ActionEvent event) {// 不通过的方法
		ArrayList<ReceiptVO> rlist = new ArrayList<>();// 获得的单据
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).cb.isSelected()) {
				rlist.add(mylist.get(i));
				mylist.remove(i);
			}
		}

		CheckReceiptHelper helper = CheckReceiptHelper.getInstance();

		try {

			ArrayList<String> result = helper.getService().failPassByList(
					rlist, id);// 这个result里面放的是通过审核失败的单据信息

			if (result.size() != 0) {// 有单据审核失败，弹窗提示
				AlertBox a = new AlertBox();
				a.display("有单据审核失败", "有单据审核失败");
			} else {
				AlertBox a = new AlertBox();
				a.display("单据审核成功", "单据审核成功");

				list.clear();
				// 进行转换
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
