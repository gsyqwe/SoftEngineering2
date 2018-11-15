package financelerui;

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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import mainframeui.TField;
import rmi.FindReceiptHelper;
import rmi.SalesDetailHelper;
import VO.ReceiptVO;
import VO.SalesDetailVO;
import export.implement.Export;
import export.interpreter.ExportInterpreter;

public class salelistui {
	private static String j;
	private static String n;
	private static Stage pStage;
	private static String id;
	private static TField tf1;// ��ʼʱ��
	private static TField tf2;// ����ʱ��
	private static TField tf3;// ɸѡ
	private static TableView<SalesDetailVO> t1;// �б���ʾ
	private static TableColumn<SalesDetailVO, String> t2;// ʱ��
	private static TableColumn<SalesDetailVO, String> t3;// ����Ա���
	private static TableColumn<SalesDetailVO, String> t4;// �ͻ�
	private static TableColumn<SalesDetailVO, String> t5;// �ֿ�
	private static TableColumn<SalesDetailVO, String> t6;// ��Ʒ��
	private static TableColumn<SalesDetailVO, String> t7;// �ͺ�
	private static TableColumn<SalesDetailVO, String> t8;// ����
	private static TableColumn<SalesDetailVO, String> t9;// ����
	private static TableColumn<SalesDetailVO, String> t10;// ���
	private static CheckBox ch1;// ��Ʒ��
	private static CheckBox ch2;// �ͻ�
	private static CheckBox ch3;// ҵ��Ա
	private static CheckBox ch4;// �ֿ�
	private static ArrayList<SalesDetailVO> salesdetaillist = new ArrayList<>();// �õ�������ϸ��

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void display(String Job, String name, String ID) {
		// TODO Auto-generated method stub
		Stage primaryStage = new Stage();
		id = ID;
		salesdetaillist.clear();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("salelistui.fxml"));
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
		t1.setLayoutX(220.0);
		t1.setLayoutY(265);
		t1.setMinHeight(450.0);
		t1.setMaxHeight(450.0);
		t1.setMinWidth(715.0);
		t1.setMaxWidth(715.0);
		t2 = new TableColumn("ʱ��");
		t2.setPrefWidth(100.0);
		t5 = new TableColumn("�ֿ�");
		t5.setPrefWidth(91);
		t6 = new TableColumn("��Ʒ��");
		t6.setPrefWidth(120.0);
		t7 = new TableColumn("�ͺ�");
		t7.setPrefWidth(100.0);
		t8 = new TableColumn("����");
		t8.setPrefWidth(100.0);
		t9 = new TableColumn("����");
		t9.setPrefWidth(100.0);
		t10 = new TableColumn("���");
		t10.setPrefWidth(100.0);
		t1.getColumns().addAll(t2, t5, t6, t7, t8, t9, t10);

		ObservableList<SalesDetailVO> data = FXCollections
				.observableArrayList();
		for (int i = 0; i < salesdetaillist.size(); i++) {
			data.add(salesdetaillist.get(i));
		}

		// �㲻������������ʾ��������˻�����Ϊ������Ҫ����ʾ���Ǹ���Ҫ�����

		t2.setCellValueFactory(cellData -> new SimpleStringProperty(""
				+ cellData.getValue().getDate()));// ʱ��
		t5.setCellValueFactory(cellData -> new SimpleStringProperty("1�Ųֿ�"));// �ֿ�
		t6.setCellValueFactory(cellData -> new SimpleStringProperty(""
				+ cellData.getValue().getCommodityName()));// ��Ʒ��
		t7.setCellValueFactory(cellData -> new SimpleStringProperty(""
				+ cellData.getValue().getVersion()));// �ͺ�
		t8.setCellValueFactory(cellData -> new SimpleStringProperty(""
				+ cellData.getValue().getQuantity()));// ����
		t9.setCellValueFactory(cellData -> new SimpleStringProperty(""
				+ cellData.getValue().getPriceForEach()));// ����
		t10.setCellValueFactory(cellData -> new SimpleStringProperty(""
				+ cellData.getValue().getTotalPrice()));// ʱ��

		t1.setItems(data);
		t1.setStyle("-fx-background-color:null");

		// ��ʼʱ��
		tf1 = new TField();
		tf1.setMinHeight(25);
		tf1.setMaxHeight(25);
		tf1.setLayoutX(116);
		tf1.setLayoutY(200);
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
		tf2.setLayoutY(235);
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

		ch1 = new CheckBox("��Ʒ��");// ��Ʒ���ĸ�ѡ��
		ch1.setLayoutX(770);
		ch1.setLayoutY(234);
		ch2 = new CheckBox("�ͻ�");// �ͻ��ĸ�ѡ��
		ch2.setLayoutX(845);
		ch2.setLayoutY(234);
		ch3 = new CheckBox("ҵ��Ա");// ҵ��Ա�ĸ�ѡ��
		ch3.setLayoutX(912);
		ch3.setLayoutY(234);
		ch4 = new CheckBox("�ֿ�");// �ֿ�ĸ�ѡ��
		ch4.setLayoutX(960);
		ch4.setLayoutY(234);

		tf2.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER) {
					// ���������ʱ��������ʾ

				}
			}
		});

		// ɸѡ
		tf3 = new TField();
		tf3.setMinHeight(25);
		tf3.setMaxHeight(25);
		tf3.setPrefWidth(222);
		tf3.setLayoutX(810);
		tf3.setLayoutY(210);
		tf3.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER) {
					// ���������ʱ��������ʾ

					String keywords = tf3.getText();
					String[] temp = keywords.split(" ");
					String commodityName = "";
					ArrayList<String> words = new ArrayList<>(Arrays
							.asList(temp));

					/*
					 * 
					 * 
					 * if (ch2.isSelected()) { Finder finder = new
					 * FindByMember(info); finders.add(finder); }
					 * 
					 * if (ch3.isSelected()) { Finder finder = new
					 * FindByUser(info); finders.add(finder); }
					 * 
					 * if (ch4.isSelected()) { Finder finder = new
					 * FindByRepository(info); finders.add(finder); }
					 */
					if (ch1.isSelected()) {
						commodityName = words.get(0);
						words.remove(0);
					}

					FindReceiptHelper findHelper = FindReceiptHelper
							.getInstance();
					ArrayList<ReceiptVO> theResult = new ArrayList<>();
					int index = 0;
					if (ch2.isSelected()) {
						ArrayList<ReceiptVO> tempResult;
						try {
							tempResult = findHelper.getService().findByMember(
									words.get(index));
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
							tempResult = findHelper.getService().findByUser(
									words.get(index));
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

					String beginTime = tf1.getText();
					String endTime = tf2.getText();

					boolean containsDate = true;
					if (beginTime == null || beginTime.equals("")) {
						containsDate = false;
					}

					if (containsDate) {
						ArrayList<ReceiptVO> tempResult = new ArrayList<>();
						try {
							tempResult = findHelper.getService().findByDate(
									beginTime + "->" + endTime);
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

					SalesDetailHelper helper = SalesDetailHelper.getInstance();
					if (commodityName.equals("")) {
						try {
							salesdetaillist = helper.getService()
									.getEffectiveLineItemWithoutSpecificName(
											theResult);
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						try {
							salesdetaillist = helper.getService()
									.getEffectiveLineItemWithCommodityName(
											commodityName, theResult);
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					ObservableList<SalesDetailVO> data = FXCollections
							.observableArrayList();
					for (int i = 0; i < salesdetaillist.size(); i++) {
						data.add(salesdetaillist.get(i));
					}
					t1.setItems(data);
				}
			}
		});

		RadioButton r1 = new RadioButton("ʱ��");
		RadioButton r2 = new RadioButton("����");
		RadioButton r3 = new RadioButton("�ܼ�");
		RadioButton r4 = new RadioButton("���");
		RadioButton r5 = new RadioButton("��Ʒ��");
		ToggleGroup group = new ToggleGroup();
		r1.setToggleGroup(group);
		r2.setToggleGroup(group);
		r3.setToggleGroup(group);
		r4.setToggleGroup(group);
		r5.setToggleGroup(group);

		group.selectedToggleProperty().addListener(
				new ChangeListener<Toggle>() {
					public void changed(ObservableValue<? extends Toggle> ov,
							Toggle toggle, Toggle new_toggle) {
						if (r1.isSelected()) {
							SalesDetailHelper helper = SalesDetailHelper
									.getInstance();
							try {
								salesdetaillist = helper.getService()
										.sortByDate(salesdetaillist);
							} catch (RemoteException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							ObservableList<SalesDetailVO> data = FXCollections
									.observableArrayList();
							for (int i = 0; i < salesdetaillist.size(); i++) {
								data.add(salesdetaillist.get(i));
							}
							t1.setItems(data);
						} else if (r2.isSelected()) {
							SalesDetailHelper helper = SalesDetailHelper
									.getInstance();
							try {
								salesdetaillist = helper.getService()
										.sortByPriceEach(salesdetaillist);
							} catch (RemoteException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							ObservableList<SalesDetailVO> data = FXCollections
									.observableArrayList();
							for (int i = 0; i < salesdetaillist.size(); i++) {
								data.add(salesdetaillist.get(i));
							}
							t1.setItems(data);
						} else if (r3.isSelected()) {
							SalesDetailHelper helper = SalesDetailHelper
									.getInstance();
							try {
								salesdetaillist = helper.getService()
										.sortByAmount(salesdetaillist);
							} catch (RemoteException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							ObservableList<SalesDetailVO> data = FXCollections
									.observableArrayList();
							for (int i = 0; i < salesdetaillist.size(); i++) {
								data.add(salesdetaillist.get(i));
							}
							t1.setItems(data);
						} else if (r4.isSelected()) {
							SalesDetailHelper helper = SalesDetailHelper
									.getInstance();
							try {
								salesdetaillist = helper.getService()
										.sortByQuantity(salesdetaillist);
							} catch (RemoteException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							ObservableList<SalesDetailVO> data = FXCollections
									.observableArrayList();
							for (int i = 0; i < salesdetaillist.size(); i++) {
								data.add(salesdetaillist.get(i));
							}
							t1.setItems(data);
						} else if (r5.isSelected()) {
							SalesDetailHelper helper = SalesDetailHelper
									.getInstance();
							try {
								salesdetaillist = helper.getService()
										.sortByCommodityName(salesdetaillist);
							} catch (RemoteException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							ObservableList<SalesDetailVO> data = FXCollections
									.observableArrayList();
							for (int i = 0; i < salesdetaillist.size(); i++) {
								data.add(salesdetaillist.get(i));
							}
							t1.setItems(data);
						}
					}
				});
		r1.setLayoutX(0);
		r1.setLayoutY(645);
		r2.setLayoutX(75);
		r2.setLayoutY(645);
		r3.setLayoutX(150);
		r3.setLayoutY(645);
		r4.setLayoutX(0);
		r4.setLayoutY(700);
		r5.setLayoutX(75);
		r5.setLayoutY(700);

		a.getChildren().addAll(root, t1, text1, text2, tf1, tf2, tf3, ch1, ch2,
				ch3, ch4, r1, r2, r3, r4, r5);
		Scene s = new Scene(a, 1080, 800);
		primaryStage.setTitle("������Ա");
		primaryStage.setScene(s);
		j = Job;
		n = name;
		pStage = primaryStage;
		primaryStage.show();
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
				"EXCEL files (*.xlsx)", "*.xlsx");
		filechooser.getExtensionFilters().add(extFilter);
		File file = filechooser.showSaveDialog(pStage);
		ExportInterpreter<SalesDetailVO> interpreter = new ExportInterpreter<>(
				salesdetaillist.toArray(new SalesDetailVO[1]));
		Export export = new Export(interpreter.getExportContent(),
				file.getPath());
		export.export();
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
