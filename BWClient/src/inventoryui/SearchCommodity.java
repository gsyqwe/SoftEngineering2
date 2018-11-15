package inventoryui;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import VO.CommodityVO;
import VO.LogVO;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import rmi.CommodityHelper;

public class SearchCommodity {// ��Ʒ��ѯ
	private static String j;
	private static String n;
	private static Stage pStage;
	private static String id;
	private static TableView<CommodityVO> t;// �б���ʾ
	private static TableColumn<CommodityVO, String> t1;// ��Ʒ����
	private static TableColumn<CommodityVO, String> t2;// ��Ʒ���
	private static TableColumn<CommodityVO, String> t3;// �������
	private static TableColumn<CommodityVO, String> t4;// �ͺ�
	private static TableColumn<CommodityVO, String> t6;// �ۼ�
	private static TableColumn<CommodityVO, String> t7;// ����
	private static TableColumn<CommodityVO, String> t8;// ��������
	private static TField tf1;// ����

	@SuppressWarnings("unchecked")
	public void display(String Job, String name, String ID) {
		// TODO Auto-generated method stub
		Stage primaryStage = new Stage();
		id = ID;
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource(
					"SearchCommodity.fxml"));
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

		t = new TableView();
		t.setLayoutX(29.0);
		t.setLayoutY(208.0);
		t.setMinHeight(239.0);
		t.setMaxHeight(239.0);
		t.setPrefWidth(647.0);
		t1 = new TableColumn("��Ʒ����");
		t1.setPrefWidth(100.0);
		t2 = new TableColumn("��Ʒ���");
		t2.setPrefWidth(99.0);
		t3 = new TableColumn("�������");
		t3.setPrefWidth(104.0);
		t4 = new TableColumn("�ͺ�");
		t4.setPrefWidth(82.0);
		t6 = new TableColumn("�ۼ�");
		t6.setPrefWidth(91.0);
		t7 = new TableColumn("����");
		t7.setPrefWidth(75.0);
		t8 = new TableColumn("��������");
		t8.setPrefWidth(95.0);
		t.getColumns().addAll(t1, t2, t3, t4, t6, t7, t8);
		t1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData
				.getValue().getName()));// ��Ʒ����

		t2.setCellValueFactory(cellData -> new SimpleStringProperty(""
				+ cellData.getValue().getId()));// ��Ʒ���
		t3.setCellValueFactory(cellData -> new SimpleStringProperty(""
				+ cellData.getValue().getQuantity()));// �������
		t4.setCellValueFactory(cellData -> new SimpleStringProperty(""
				+ cellData.getValue().getVersion()));// �ͺ�
		t6.setCellValueFactory(cellData -> new SimpleStringProperty(Double
				.toString(cellData.getValue().getRetailPrice())));// �ۼ�
		t7.setCellValueFactory(cellData -> new SimpleStringProperty(""
				+ cellData.getValue().getBid()));// ����
		t8.setCellValueFactory(cellData -> new SimpleStringProperty(""
				+ cellData.getValue().getAlertQuantity()));// ��������
		t.setStyle("-fx-background-color:null");

		// ����
		tf1 = new TField();
		tf1.setLayoutX(91);
		tf1.setLayoutY(165);
		tf1.setMinHeight(30);
		tf1.setMaxHeight(30);
		tf1.setPrefWidth(150);
		// ���������¼�
		tf1.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER) {
					// �����¼�
					CommodityHelper helper = CommodityHelper.getInstance();

					try {

						ArrayList<CommodityVO> commodities = helper
								.getService().findWithKeyWord(tf1.getText());

						// ��ʾcommodities���������

					} catch (RemoteException e) {

						// TODO Auto-generated catch block

						e.printStackTrace();

					}
				}
			}
		});

		AnchorPane a = new AnchorPane();
		a.getChildren().addAll(root, text1, text2, t, tf1);

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
	private void HandleSearch(ActionEvent event) throws RemoteException {
		CommodityHelper helper = CommodityHelper.getInstance();
		ArrayList<CommodityVO> commodities = helper.getService()
				.findWithKeyWord(tf1.getText());
		// ��ʾcommodities���������
		ObservableList<CommodityVO> data = FXCollections.observableArrayList();
		for (int i = 0; i < commodities.size(); i++) {
			data.add(commodities.get(i));
		}
		t.setItems(data);

	}

	@FXML
	private void HandleEsc(ActionEvent event) {
		pStage.close();
	}
}
