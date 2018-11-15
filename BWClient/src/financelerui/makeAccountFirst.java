package financelerui;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import VO.CommodityVO;
import VO.InitializeAccountVO;
import VO.ReceiptVO;
import VO.RecordVO;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import rmi.InitializeAccountHelper;

public class makeAccountFirst {// �ڳ�����
	private static String j;
	private static String n;
	private static Stage pStage;
	private static String id;
	private static TableView<CommodityVO> t;// ��ʾ���б�
	private static TableColumn<CommodityVO,String> t1;// ��Ʒ����
	private static TableColumn<CommodityVO,String> t2;// ��Ʒ���
	private static TableColumn<CommodityVO,String> t3;// �������
	private static TableColumn<CommodityVO,String> t4;// �ͺ�
	private static TableColumn<CommodityVO,String> t5;// ��Ʒ���
	private static TableColumn<CommodityVO,String> t6;// ����
	private static TableColumn<CommodityVO,String> t7;// �ۼ�
	private static TableColumn<CommodityVO,String> t8;//�������
	private static TableColumn<CommodityVO,String> t9;//����ۼ�
	ArrayList<CommodityVO> commoditylist=new ArrayList<>();
	private static InitializeAccountVO inital;

	public void display(String Job, String name,String ID,InitializeAccountVO intaialize) {
		// TODO Auto-generated method stub
		Stage primaryStage = new Stage();
		commoditylist=intaialize.getCommodityVOList();
		id=ID;
		inital=intaialize;
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource(
					"makeAccountFirst.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AnchorPane a = new AnchorPane();
		// TableView
		t = new TableView();
		t.setLayoutX(57.0);
		t.setLayoutY(170.0);
		t.setMinHeight(215.0);
		t.setMaxHeight(215.0);
		t.setPrefWidth(690.0);
		t1 = new TableColumn("��Ʒ����");
		t1.setPrefWidth(75.0);
		t2 = new TableColumn("��Ʒ���");
		t2.setPrefWidth(75.0);
		t3 = new TableColumn("�������");
		t3.setPrefWidth(75.0);
		t4 = new TableColumn("�ͺ�");
		t4.setPrefWidth(75.0);
		t5 = new TableColumn("��Ʒ���");
		t5.setPrefWidth(75.0);
		t6 = new TableColumn("����");
		t6.setPrefWidth(75.0);
		t7 = new TableColumn("�ۼ�");
		t7.setPrefWidth(75.0);
		t8 = new TableColumn("�������");
		t8.setPrefWidth(75.0);
		t9 = new TableColumn("����ۼ�");
		t9.setPrefWidth(89.0);
		t.getColumns().addAll(t1, t2, t3, t4, t5, t6, t7, t8, t9);
		
		ObservableList<CommodityVO> data = FXCollections.observableArrayList();
		for (int i = 0; i < commoditylist.size(); i++) {
			data.add(commoditylist.get(i));
		}

		// �㲻������������ʾ��������˻�����Ϊ������Ҫ����ʾ���Ǹ���Ҫ�����

		t1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData
				.getValue().getName()));// ��Ʒ����

		t2.setCellValueFactory(cellData -> new SimpleStringProperty(""
				+ cellData.getValue().getId()));// ��Ʒ���
		t3.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData
				.getValue().getQuantity()));// �������
		t4.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData
				.getValue().getVersion()));// �ͺ�
		t5.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData
				.getValue().getCategory()));//��Ʒ����
		t6.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData
				.getValue().getBid()));// ����
		t7.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData
				.getValue().getRetailPrice()));// �ۼ�
		t8.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData
				.getValue().getRecentBid()));// �������
		t9.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData
				.getValue().getRecentRetailPrice()));// ����ۼ�
		
		t.setItems(data);
		
		t.setStyle("-fx-background-color:null");

		// 2����Ϣ
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

		a.getChildren().addAll(root, text1, text2, t);
		Scene s = new Scene(a, 800, 500);
		primaryStage.setTitle("������Ա");
		primaryStage.setScene(s);
		j = Job;
		n = name;
		pStage = primaryStage;
		primaryStage.show();
	}

	@FXML
	private void HandleHelp(ActionEvent event) {
		HelpF h = new HelpF();
		h.display(j, n,id);
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
	private void HandleNext(ActionEvent event) {
		//�û����Ա༭��һҳ��ʾ�����ݣ���Ҫ�õ��û����޸ģ�second��thirdͬ��
		makeAccountSecond m = new makeAccountSecond();
		m.display(j, n,id,inital);
		pStage.close();
	}

	@FXML
	private void HandleCancle(ActionEvent event) {
		pStage.close();
	}

}
