package inventoryui;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import VO.CommodityVO;
import VO.EmailVO;
import javafx.application.Application;
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
import rmi.CommodityHelper;

public class checkCommodityui {// ����̵�
	private static String j;
	private static String n;
	private static Stage pStage;
	private static String id;
	private static TField tf1;// ��ʼʱ��
	private static TField tf2;// ����ʱ��
	private ArrayList<CommodityVO> mylist = new ArrayList<>();
	private static TableView<CommodityVO> t;// �б���ʾ
	private static TableColumn<CommodityVO, String> t1;// ��Ʒ����
	private static TableColumn<CommodityVO, String> t2;// ��Ʒ���
	private static TableColumn<CommodityVO, String> t3;// �������
	private static TableColumn<CommodityVO, String> t4;// ������
	private static TableColumn<CommodityVO, String> t6;// �ͺ�

	@SuppressWarnings("unchecked")
	public void display(String Job, String name, String ID) {
		// TODO Auto-generated method stub
		Stage primaryStage = new Stage();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource(
					"checkCommodityui.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ��ʾ��¼�˵���Ϣ
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

		tf1 = new TField();// ��ʼʱ��
		tf1.setLayoutX(135);
		tf1.setLayoutY(176);
		tf1.setPrefWidth(160);
		tf2 = new TField();// ����ʱ��
		tf2.setLayoutX(555);
		tf2.setLayoutY(176);
		tf2.setPrefWidth(160);
		// ����һ�������¼�
		// ����ʱ��
		tf1.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() == 2) {
					// ���ʱ��
					Date day = new Date();
					SimpleDateFormat df = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm");
					tf1.setText(df.format(day));
				}
			}
		});
		// ����ʱ��
		tf2.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() == 2) {
					// ���ʱ��
					Date day = new Date();
					SimpleDateFormat df = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm");
					tf2.setText(df.format(day));
				}
			}
		});
		// ������ʱ�������������¼�
		tf2.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER) {
					// ����̵�û������û��ʱ�䷶Χ����һ����ǰ������
				}
			}
		});

		// ����̵�������ʾ,����tableview
		t = new TableView();
		t.setLayoutX(145.0);
		t.setLayoutY(216.0);
		t.setMinHeight(200.0);
		t.setMaxHeight(200.0);
		t.setPrefWidth(526.0);
		t1 = new TableColumn("��Ʒ����");
		t1.setPrefWidth(125.0);
		t2 = new TableColumn("��Ʒ���");
		t2.setPrefWidth(101.0);
		t3 = new TableColumn("�������");
		t3.setPrefWidth(102.0);
		t4 = new TableColumn("������");
		t4.setPrefWidth(122.0);
		t6 = new TableColumn("�ͺ�");
		t6.setPrefWidth(75.0);
		t.getColumns().addAll(t1, t2, t3, t4, t6);
		ObservableList<CommodityVO> data = FXCollections.observableArrayList();
		for (int i = 0; i < mylist.size(); i++) {
			data.add(mylist.get(i));
		}
		t1.setCellValueFactory(cellData -> new SimpleStringProperty(""
				+ cellData.getValue().getId()));// �˻����
		t2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData
				.getValue().getName()));// ������
		t3.setCellValueFactory(cellData -> new SimpleStringProperty(Double
				.toString(cellData.getValue().getQuantity())));// �Ķ�״��
		t4.setCellValueFactory(cellData -> new SimpleStringProperty(Double
				.toString(cellData.getValue().getBid())));// ����
		t6.setCellValueFactory(cellData -> new SimpleStringProperty(cellData
				.getValue().getVersion()));// ����
		t.setStyle("-fx-background-color:null");

		AnchorPane a = new AnchorPane();
		a.getChildren().addAll(root, text1, text2, t, tf1, tf2);

		Scene s = new Scene(a, 800, 500);
		primaryStage.setTitle("��������Ա");
		primaryStage.setScene(s);
		j = Job;
		n = name;
		pStage = primaryStage;
		primaryStage.show();
	}

	@FXML
	private void HandleHelp(ActionEvent event) {
		HelpIn h = new HelpIn();
		h.display(j, n, id);
	}

	@FXML
	private void HandleOut(ActionEvent event) {
		pStage.close();
	}

	@FXML
	private void HandleCancle(ActionEvent event) {
		pStage.close();
	}

	@FXML
	private void HandleEsc(ActionEvent event) {
		pStage.close();
	}

	@FXML
	private void HandleSure(ActionEvent event) throws RemoteException {
		CommodityHelper helper = CommodityHelper.getInstance();
		ArrayList<CommodityVO> commodities = helper.getService()
				.showCommodityList();
		mylist = commodities;
		// ��ʾ���commodities��Ķ��������ȫ����ʾ���ڽ����ϼӣ�������治������get������vo������Ҫ��ʾ�Ķ�����
		ObservableList<CommodityVO> data = FXCollections.observableArrayList();
		for (int i = 0; i < mylist.size(); i++) {
			data.add(mylist.get(i));
		}
		t.setItems(data);
	}

	@FXML
	private void HandleExport(ActionEvent event) {// ��������
		FileChooser filechooser = new FileChooser();
		filechooser.setTitle("����");
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
				"EXCEL files (*.excel)", "*.excel");
		filechooser.getExtensionFilters().add(extFilter);
		File file = filechooser.showSaveDialog(pStage);
		if (file != null) {
			// ��Ҫд�������д��file�ļ���
		}
	}

}
