package financelerui;

import java.io.IOException;
import java.util.ArrayList;

import VO.CommodityVO;
import VO.InitializeAccountVO;
import VO.MemberVO;
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

public class makeAccountSecond {
	private static String j;
	private static String n;
	private static Stage pStage;
	private static String id;
	private static TableView<MemberVO> t;// ��ʾ���б�
	private static TableColumn<MemberVO,String> t1;// �ͻ�����
	private static TableColumn<MemberVO,String> t2;// �ͻ����
	private static TableColumn<MemberVO,String> t3;// �绰
	private static TableColumn<MemberVO,String> t4;// �ʱ�
	private static TableColumn<MemberVO,String> t5;// ��ַ
	private static TableColumn<MemberVO,String> t6;// Ӧ��
	private static TableColumn<MemberVO,String> t7;// Ӧ��
	private static InitializeAccountVO intaial;
	private static ArrayList<MemberVO> memberlist=new ArrayList<>();

	public void display(String Job, String name,String ID,InitializeAccountVO intaialize) {
		Stage primaryStage = new Stage();
		Parent root = null;
		intaial=intaialize;
		id=ID;
		memberlist=intaial.getMemberPOList();
		AnchorPane a = new AnchorPane();
		try {
			root = FXMLLoader.load(getClass().getResource(
					"makeAccountSecond.fxml"));
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

		t = new TableView();
		t.setLayoutX(154.0);
		t.setLayoutY(172.0);
		t.setMinHeight(261.0);
		t.setMaxHeight(261.0);
		t.setPrefWidth(525.0);
		t1 = new TableColumn("�ͻ�����");
		t1.setPrefWidth(75.0);
		t2 = new TableColumn("�ͻ����");
		t2.setPrefWidth(75.0);
		t3 = new TableColumn("�绰");
		t3.setPrefWidth(75.0);
		t4 = new TableColumn("�ʱ�");
		t4.setPrefWidth(75.0);
		t5 = new TableColumn("��ַ");
		t5.setPrefWidth(75.0);
	    t6 = new TableColumn("Ӧ��");
		t6.setPrefWidth(75.0);
		t7 = new TableColumn("Ӧ��");
		t7.setPrefWidth(75.0);
		t.getColumns().addAll(t1, t2, t3, t4, t5, t6, t7);
		
		ObservableList<MemberVO> data = FXCollections.observableArrayList();
		for (int i = 0; i < memberlist.size(); i++) {
			data.add(memberlist.get(i));
		}

		// �㲻������������ʾ��������˻�����Ϊ������Ҫ����ʾ���Ǹ���Ҫ�����

		t1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData
				.getValue().getName()));// ����

		t2.setCellValueFactory(cellData -> new SimpleStringProperty(""
				+ cellData.getValue().getId()));// ���
		t3.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData
				.getValue().getPhoneNumber()));// �绰
		t4.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData
				.getValue().getPostcode()));// �ʱ�
		t5.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData
				.getValue().getAddress()));//��ַ
		t6.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData
				.getValue().getPayment()));// Ӧ��
		t7.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData
				.getValue().getReceivable()));// Ӧ��
		
		t.setItems(data);
		
		t.setStyle("-fx-background-color:null");

		a.getChildren().addAll(root, t, text1, text2);

		Scene s = new Scene(a, 800, 500);
		primaryStage.setTitle("������Ա");
		primaryStage.setScene(s);
		j = Job;
		n = name;
		pStage = primaryStage;
		primaryStage.show();
	}

	@FXML
	private void HandleNext(ActionEvent event) {
		makeAccountThird m = new makeAccountThird();
		m.display(j, n,id,intaial);
		pStage.close();
	}

	@FXML
	private void HandleCancle(ActionEvent event) {
		pStage.close();
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
}
