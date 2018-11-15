package financelerui;

import inventoryui.AlarmX;
import inventoryui.OverflowX;
import inventoryui.lossX;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import mainframeui.AlertBox;
import mainframeui.TField;
import receiptFinder.Finder;
import receiptFinder.ReceiptDataServiceInfo;
import rmi.BusinessProcessHelper;
import rmi.FinancialReceiptHelper;
import rmi.FindReceiptHelper;
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
import enums.FinancialReceiptType;
import enums.InventoryReceiptType;
import enums.ReceiptType;
import enums.ResultMessage;
import enums.SalesReceiptType;

public class businessprocessui {

	private static String j;
	private static String n;
	private static Stage pStage;
	private static String id;
	private static TField tf1;// ��ʼʱ��
	private static TField tf2;// ����ʱ��
	private static TField tf3;// ɸѡ
	private static TableView<ReceiptVO> t1;// ���
	private static TableColumn<ReceiptVO, String> t2;// ��������
	private static TableColumn<ReceiptVO, String> t3;// ����ʱ��
	private static TableColumn<ReceiptVO, String> t4;// ����Ա���
	private static TableColumn<ReceiptVO, String> t5;// ��������
	private static CheckBox ch1;// ��������
	private static CheckBox ch2;// �ͻ�
	private static CheckBox ch3;// ҵ��Ա
	private static CheckBox ch4;// �ֿ�
	private static ReceiptVO r1;// ��ѡ�еĵ���
	private static ArrayList<ReceiptVO> receiptlist = new ArrayList<>();

	@SuppressWarnings("unchecked")
	public void display(String Job, String name, String ID) {
		// TODO Auto-generated method stub
		Stage primaryStage = new Stage();
		id = ID;
		businessprocessui b = new businessprocessui();
		AnchorPane a = new AnchorPane();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource(
					"businessprocessui.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		TextField text1 = new TextField();
		text1.setStyle("-fx-background-color:null");
		text1.setText("��ӭ��:" + name);
		text1.setLayoutX(900.0);
		text1.setLayoutY(40.0);
		text1.setMinHeight(36.0);
		text1.setMaxHeight(36.0);
		text1.setFont(Font.font("Verdana", 18));
		text1.prefWidth(185.0);
		text1.setEditable(false);

		TextField text2 = new TextField();
		text2.setStyle("-fx-background-color:null");
		text2.setText("��ǰְ��:" + Job);
		text2.setLayoutX(900.0);
		text2.setLayoutY(68.0);
		text2.setMinHeight(30.0);
		text2.setMaxHeight(30.0);
		text2.setFont(Font.font("Verdana", 18));
		text2.prefWidth(185.0);
		text2.setEditable(false);

		t1 = new TableView();
		t1.setLayoutX(150.0);
		t1.setLayoutY(290.0);
		t1.setMinHeight(426.0);
		t1.setMaxHeight(426.0);
		t1.setPrefWidth(725.0);

		t2 = new TableColumn("��������");
		t2.setPrefWidth(95.0);
		t3 = new TableColumn("����ʱ��");
		t3.setPrefWidth(92.0);
		t4 = new TableColumn("���ݱ��");
		t4.setPrefWidth(85.0);
		t5 = new TableColumn("����Ա���");
		t5.setPrefWidth(452.0);

		ObservableList<ReceiptVO> data = FXCollections.observableArrayList();
		for (int i = 0; i < receiptlist.size(); i++) {
			data.add(receiptlist.get(i));
		}
		t2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData
				.getValue().getType().toString()));// ��������
		t3.setCellValueFactory(cellData -> new SimpleStringProperty(""
				+ cellData.getValue().getDate()));// ʱ��
		t4.setCellValueFactory(cellData -> new SimpleStringProperty(cellData
				.getValue().getId()));// ���ݱ��
		t5.setCellValueFactory(cellData -> new SimpleStringProperty(cellData
				.getValue().getOperatorID()));// ����Ա���

		t1.getColumns().addAll(t2, t3, t4, t5);
		t1.setItems(data);

		// ComboBox
		@SuppressWarnings("rawtypes")
		ComboBox comboBox1 = new ComboBox();
		comboBox1.setValue("��������");
		comboBox1.setLayoutX(55.0);
		comboBox1.setLayoutY(170.0);
		comboBox1.minWidth(150.0);
		comboBox1.maxWidth(150.0);
		comboBox1.getItems().addAll("��Ӫ״����", "��Ӫ���̱�", "������ϸ��");
		comboBox1.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener() {
					@Override
					public void changed(ObservableValue arg0, Object arg1,
							Object arg2) {
						// TODO Auto-generated method stub
						if (arg2.toString() == "��Ӫ״����") {// �޸�������Ӧ��Ҫ���µ�¼
							businessconditionui b = new businessconditionui();
							b.display(Job, name, id);
						} else if (arg2.toString() == "��Ӫ���̱�") {
							businessprocessui b = new businessprocessui();
							b.display(Job, name, id);
						} else if (arg2.toString() == "������ϸ��") {
							salelistui s = new salelistui();
							s.display(Job, name, id);
						}
					}
				});

		// ��ʼʱ��
		tf1 = new TField();
		tf1.setMinHeight(25);
		tf1.setMaxHeight(25);
		tf1.setLayoutX(116);
		tf1.setLayoutY(210);
		tf1.setPrefWidth(193);
		// ��ʾʱ��
		tf1.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() == 2) {
					Date day = new Date();
					SimpleDateFormat df = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm");
					tf1.setText(df.format(day));
				}
			}
		});
		// ����ʱ��
		tf2 = new TField();
		tf2.setMinHeight(25);
		tf2.setMaxHeight(25);
		tf2.setLayoutX(116);
		tf2.setLayoutY(250);
		tf2.setPrefWidth(193);
		// ��ʾʱ��
		tf2.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() == 2) {
					Date day = new Date();
					SimpleDateFormat df = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm");
					tf2.setText(df.format(day));
				}
			}
		});
		// ɸѡ
		tf3 = new TField();
		tf3.setMinHeight(25);
		tf3.setMaxHeight(25);
		tf3.setLayoutX(800);
		tf3.setLayoutY(210);
		tf3.setPrefWidth(227);

		ch1 = new CheckBox("��������");// �������͵ĸ�ѡ��
		ch1.setLayoutX(734);
		ch1.setLayoutY(252);
		ch2 = new CheckBox("�ͻ�");// �ͻ��ĸ�ѡ��
		ch2.setLayoutX(824);
		ch2.setLayoutY(252);
		ch3 = new CheckBox("ҵ��Ա");// ҵ��Ա�ĸ�ѡ��
		ch3.setLayoutX(886);
		ch3.setLayoutY(252);
		ch4 = new CheckBox("�ֿ�");// �ֿ�ĸ�ѡ��
		ch4.setLayoutX(969);
		ch4.setLayoutY(252);

		tf2.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER) {
					// ���������ʱ��������ʾ
					int count = 0;
					String str = tf3.getText();
					String s[] = str.split(" ");
					if (ch1.isSelected()) {
						for (int i = 0; i < receiptlist.size(); i++) {
							if (receiptlist.get(i).getType().toString()
									.equals(s[count])) {

							}
						}
					}
					if (ch2.isSelected()) {
						for (int i = 0; i < receiptlist.size(); i++) {
							if (receiptlist.get(i).getOperatorID().equals(s[1])) {

							}
						}

					}
					if (ch3.isSelected()) {

					}
					if (ch4.isSelected()) {

					}
				}
			}
		});

		tf3.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {

				if (event.getCode() == KeyCode.ENTER) {

					// ���������ʱ��������ʾ

					if (event.getCode() == KeyCode.ENTER) {

						// ɸѡ

						String keywords = tf3.getText();

						String beginTime = tf1.getText();

						String endTime = tf2.getText();

						String[] temp = keywords.split(" ");

						ArrayList<String> words = new ArrayList<>(Arrays

						.asList(temp));
						System.out.println(words.get(0));

						boolean containsDate = true;

						if (beginTime == null || beginTime.equals("")) {

							containsDate = false;

						}

						FindReceiptHelper findHelper = FindReceiptHelper
								.getInstance();
						ArrayList<ReceiptVO> theResult = new ArrayList<>();
						int index = 0;
						if (ch2.isSelected()) {
							ArrayList<ReceiptVO> tempResult = new ArrayList<>();
							try {
								tempResult = findHelper.getService()
										.findByMember(words.get(0));
								if (theResult.size() != 0) {

									theResult = fliter_effective(theResult,
											tempResult);

								} else {
									theResult = tempResult;
								}
								index = index + 1;
							} catch (RemoteException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

						if (ch3.isSelected()) {
							ArrayList<ReceiptVO> tempResult;
							try {
								tempResult = findHelper.getService()
										.findByUser(words.get(index));
								if (theResult.size() != 0) {
									theResult = fliter_effective(theResult,
											tempResult);
								} else {
									theResult = tempResult;
								}
								index = index + 1;
							} catch (RemoteException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

						if (ch4.isSelected()) {
							ArrayList<ReceiptVO> tempResult;
							try {
								tempResult = findHelper.getService()
										.findByCategory(words.get(index));
								if (theResult.size() != 0) {
									theResult = fliter_effective(theResult,
											tempResult);
								} else {
									theResult = tempResult;
								}
								index = index + 1;
							} catch (RemoteException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

						if (containsDate) {
							ArrayList<ReceiptVO> tempResult;
							try {
								tempResult = findHelper.getService()
										.findByDate(beginTime + "->" + endTime);
								if (theResult.size() != 0) {
									theResult = fliter_effective(theResult,
											tempResult);
								} else {
									theResult = tempResult;
								}
								index = index + 1;
							} catch (RemoteException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

						receiptlist = theResult;
						ObservableList<ReceiptVO> data = FXCollections
								.observableArrayList();
						for (int i = 0; i < receiptlist.size(); i++) {
							data.add(receiptlist.get(i));
						}
						t1.setItems(data);
					}
				}
			}
		});

		a.getChildren().addAll(root, text1, text2, t1, comboBox1, tf1, tf2,
				tf3, ch1, ch2, ch3, ch4);
		Scene s = new Scene(a, 1080, 800);
		primaryStage.setTitle("������Ա");
		primaryStage.setScene(s);
		j = Job;
		n = name;
		pStage = primaryStage;
		primaryStage.show();
	}

	@FXML
	private void HandleRed(ActionEvent event) {
		BusinessProcessHelper helper = BusinessProcessHelper.getInstance();
		ResultMessage message = null;
		AlertBox a = new AlertBox();
		a.display("���ɹ�", "���ɹ�");
		if (r1.getType() == ReceiptType.SALE_RECEIPT) {
			try {
				message = helper.getService().redCopySales((SalesReceiptVO) r1,
						id);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (r1.getType() == ReceiptType.FINANCIAL_RECEIPT) {
			try {
				message = helper.getService().redCopyFinancial(
						(FinancialReceiptVO) r1, id);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (r1.getType() == ReceiptType.INVENTORY_RECEIPT) {
			try {
				message = helper.getService().redCopyInventory(
						(InventoryReceiptVO) r1, id);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (message == ResultMessage.RED_COPY_TWICE) {
			String warning = "RedCopy a receipt more than once";// ��ͼ��һ�����ݺ�����δ���,������ʾ
			AlertBox alert = new AlertBox();
			a.display(warning, warning);
		} else if (message == ResultMessage.COMMODITY_STOCKIN_FAIL) {
			String warning = "Commodity stocking fail the commodity is negative.";// �����ʧ�ܣ�������ʾ
			AlertBox alert = new AlertBox();
			a.display(warning, warning);
		} else if (message == ResultMessage.BANK_ACCOUNT_UPDATE_FAIL_NEGATIVE_AMOUNT) {
			String warning = "Bank account is negative.";// �۳�Ǯ��ʧ�ܣ�������ʾ
			AlertBox alert = new AlertBox();
			a.display(warning, warning);
		}

	}

	@FXML
	private void HandleRedAndCopy(ActionEvent event) {
		int index = t1.getSelectionModel().getSelectedIndex();
		r1 = receiptlist.get(index);
		SalesReceiptVO sales = new SalesReceiptVO();
		if (r1.getType() == ReceiptType.SALE_RECEIPT) {
			SalesHelper helper = SalesHelper.getInstance();
			try {
				sales = helper.getService().getReceiptByID(r1.getId());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (sales.getType().equals(SalesReceiptType.SALES)) {
				SaleuiX s = new SaleuiX();
				s.display(j, n, id, sales);
			} else if (sales.getType().equals(SalesReceiptType.SALES_RETURN)) {
				SaleBackuiX s = new SaleBackuiX();
				s.display(j, n, id, sales);
			} else if (sales.getType().equals(SalesReceiptType.PURCHASE)) {
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
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (f1.getType().equals(FinancialReceiptType.BILL)) {
				MoneyuiX m = new MoneyuiX();
				m.display(j, n, id, f1);
			} else if (f1.getType().equals(FinancialReceiptType.DEBIT_NOTE)) {
				getMoneyuiX m = new getMoneyuiX();
				m.display(j, n, id, f1);
			} else if (f1.getType().equals(FinancialReceiptType.CASH_CLAIM)) {
				costX c = new costX();
				c.display(j, n, id, f1);
			}
		} else if (r1.getType() == ReceiptType.INVENTORY_RECEIPT) {
			InventoryReceiptHelper helper = InventoryReceiptHelper
					.getInstance();
			InventoryReceiptVO i1 = new InventoryReceiptVO();
			try {
				i1 = helper.getService().findByID(r1.getId());
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			if (i1.getType().equals(InventoryReceiptType.OVERFLOW)) {
				OverflowX o = new OverflowX();
				o.display(j, n, id, i1);
			} else if (i1.getType().equals(InventoryReceiptType.BREAKAGE)) {
				lossX l = new lossX();
				l.display(j, n, id, i1);
			} else if (i1.getType().equals(InventoryReceiptType.ALARM)) {
				AlarmX a = new AlarmX();
				a.display(j, n, id, i1);
			}
		}

	}

	@FXML
	private void HandleHelp(ActionEvent event) {
		HelpF f = new HelpF();
		f.display(j, n, id);
	}

	@FXML
	private void Handlesure(ActionEvent event) {
		pStage.close();
	}

	@FXML
	private void HandleCancle(ActionEvent event) {
		pStage.close();
	}

	@FXML
	private void HandleExport(ActionEvent event) {
		FileChooser filechooser = new FileChooser();
		filechooser.setTitle("����");
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
				"EXCEL files (*.excel)", "*.xlsx");
		filechooser.getExtensionFilters().add(extFilter);
		File file = filechooser.showSaveDialog(pStage);
	}

	@FXML
	private void HandleOut(ActionEvent event) {
		pStage.close();
	}

	@FXML
	private void HandleEsc(ActionEvent event) {
		pStage.close();
	}

	public ArrayList<ReceiptVO> fliter_effective(
			ArrayList<ReceiptVO> effective_list, ArrayList<ReceiptVO> find_list) {
		Iterator<ReceiptVO> it = find_list.iterator();// ����Ҫɾ��Ԫ�ص�ʱ��Ҫ�õ���forѭ�����õ�������ȫ

		while (it.hasNext()) {
			ReceiptVO find_item = it.next();
			boolean is_effective = false;
			for (ReceiptVO effective_item : effective_list) {
				if (find_item.getId().equals(effective_item.getId())) {
					is_effective = true;
					break;
				}
			}

			if (!is_effective) {
				it.remove();
			}
		}

		return find_list;
	}

}
