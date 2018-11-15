package financelerui;

import java.io.IOException;

import mainframeui.TField;

import java.util.ArrayList;

import VO.ReceiptVO;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GetReceipt {
	private static String j;
	private static String n;
	private static Stage pStage;
	private static String id;
	private static ArrayList<ReceiptVO> mylist=new ArrayList<>();// �õ�֮ǰ�ĵ���
	private static RadioButton r1;// ��ѡ��ť��ɸѡ���
	private static RadioButton r2;// ɸѡ�տ
	private static RadioButton r3;// ɸѡ�ֽ���õ�
	private static ToggleGroup group;// ����r1,r2,r3
	private static TField tf1;// ��������
	private static TableView<ReceiptVO> table;// �õ����еĵ��ݵ���ʾ
	private static TableColumn<ReceiptVO, String> t1;// ��������
	private static TableColumn<ReceiptVO, String> t2;// �ƶ�ʱ��
	private static TableColumn<ReceiptVO, String> t3;// ��������

	@SuppressWarnings("unchecked")
	public void display(String Job, String name, String ID) {
		// TODO Auto-generated method stub
		Stage primaryStage = new Stage();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("GetReceipt.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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

		// �б���ʾ
		table = new TableView();
		table.setStyle("-fx-background-color:null");
		table.setPrefWidth(600);
		table.setMinHeight(220);
		table.setMaxHeight(220);
		table.setLayoutX(97);
		table.setLayoutY(190);
		t1 = new TableColumn("��������");
		t1.setPrefWidth(150);
		t2 = new TableColumn("�ƶ�ʱ��");
		t2.setPrefWidth(150);
		t3 = new TableColumn("��������");
		t3.setPrefWidth(300);

		// ������ʾ
		// ��Ҫ��ObserValue�����
		ObservableList<ReceiptVO> data = FXCollections.observableArrayList();
		for (int i = 0; i < mylist.size(); i++) {
			data.add(mylist.get(i));
		}

		// �㲻������������ʾ��������˻�����Ϊ������Ҫ����ʾ���Ǹ���Ҫ�����

		t1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData
				.getValue().getType().toString()));// ��������

		t2.setCellValueFactory(cellData -> new SimpleStringProperty(""
				+ cellData.getValue().getDate()));// �ƶ�ʱ��
		t3.setCellValueFactory(cellData -> new SimpleStringProperty(cellData
				.getValue().getId()));// ����

		table.setItems(data);

		table.getColumns().addAll(t1, t2, t3);
		// �����Ҽ���ť
		ContextMenu cme = new ContextMenu();
		// �����Ҽ��˵���һЩ��
		MenuItem m1 = new MenuItem("�ƶ��ֽ���õ�");
		cme.getItems().add(m1);
		MenuItem m2 = new MenuItem("�ƶ����");
		cme.getItems().add(m2);
		MenuItem m3 = new MenuItem("�ƶ��տ");
		cme.getItems().add(m3);
		table.setContextMenu(cme);
		// ��������¼�
		m1.setOnAction(new EventHandler<ActionEvent>() {// �ƶ��ֽ���õ�
			public void handle(ActionEvent e) {
				// �õ���ѡ���index,�õ����
				int index;
				index = table.getSelectionModel().getSelectedIndex();
				// �����ƶ��ֽ���õ��¼�
			}
		});
		m2.setOnAction(new EventHandler<ActionEvent>() {// �ƶ��տ
			public void handle(ActionEvent e) {
				// �õ���ѡ���index,�õ����
				int index;
				index = table.getSelectionModel().getSelectedIndex();
				// �����ƶ��տ
			}
		});
		m3.setOnAction(new EventHandler<ActionEvent>() {// �ƶ����
			public void handle(ActionEvent e) {
				// �õ���ѡ���index,�õ����
				int index;
				index = table.getSelectionModel().getSelectedIndex();
				// �����ƶ����
			}
		});

		// ���õ�ѡ��������ɸѡ
		r1 = new RadioButton("���");
		r2 = new RadioButton("�տ");
		r3 = new RadioButton("�ֽ���õ�");
		r1.setLayoutX(469);
		r1.setLayoutY(140);
		r2.setLayoutX(566);
		r2.setLayoutY(140);
		r3.setLayoutX(469);
		r3.setLayoutY(170);
		group = new ToggleGroup();
		r1.setToggleGroup(group);
		r2.setToggleGroup(group);
		r3.setToggleGroup(group);

		AnchorPane a = new AnchorPane();
		a.getChildren().addAll(root, text1, text2, table, r1, r2, r3);
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
		HelpF f = new HelpF();
		f.display(j, n, id);
	}

	@FXML
	private void HandleCancle(ActionEvent event) {
		pStage.close();
	}

	@FXML
	private void HandleSure(ActionEvent event) {
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
	private void HandleTI(ActionEvent event) {

	}
}
