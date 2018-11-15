package financelerui;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

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
import mainframeui.AlertBox;
import rmi.InitializeAccountHelper;
import VO.BankAccountVO;
import VO.InitializeAccountVO;
import enums.ResultMessage;

public class makeAccountThird {
	private static String j;
	private static String n;
	private static Stage pStage;
	private static String id;
	private static TableView<BankAccountVO> t;//��ʾ�б�
	private static TableColumn<BankAccountVO,String> t1;//�˻�����
	private static TableColumn<BankAccountVO,String> t2;//�˻����
	private static TableColumn<BankAccountVO,String> t3;//�˻����
	private static ArrayList<BankAccountVO> bankaccountlist=new ArrayList<>();
	
	public void display(String Job, String name,String ID,InitializeAccountVO intaialize){
		Stage primaryStage = new Stage();
		Parent root = null;
		bankaccountlist=intaialize.getBankAccountPOList();
		id=ID;
		AnchorPane a = new AnchorPane();
		try {
			root = FXMLLoader.load(getClass().getResource(
					"makeAccountThird.fxml"));
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

		TableView t = new TableView();
		t.setLayoutX(108.0);
		t.setLayoutY(177.0);
		t.setMinHeight(243.0);
		t.setMaxHeight(243.0);
		t.setPrefWidth(600.0);
		t1 = new TableColumn("�˻�����");
		t1.setPrefWidth(213.0);
		t2 = new TableColumn("�˻����");
		t2.setPrefWidth(183.0);
		t3 = new TableColumn("�˻����");
		t3.setPrefWidth(203.0);
		t.getColumns().addAll(t1, t2, t3);
		
		ObservableList<BankAccountVO> data = FXCollections.observableArrayList();
		for (int i = 0; i < bankaccountlist.size(); i++) {
			data.add(bankaccountlist.get(i));
		}

		// �㲻������������ʾ��������˻�����Ϊ������Ҫ����ʾ���Ǹ���Ҫ�����

		t1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData
				.getValue().getName()));// ����

		t2.setCellValueFactory(cellData -> new SimpleStringProperty(""
				+ cellData.getValue().getId()));// ���
		t3.setCellValueFactory(cellData -> new SimpleStringProperty(Double.toString(cellData
				.getValue().getAmount())));// ���
		
		t.setStyle("-fx-background-color:null");
		t.setItems(data);

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
	private void HandleFinish(ActionEvent event) throws RemoteException {// ����ڳ����˵Ĳ���
		InitializeAccountVO vo=new InitializeAccountVO();//��Ҫ��һ�����洫������vo
		InitializeAccountHelper helper=InitializeAccountHelper.getInstance();
		ResultMessage result=helper.getService().setUp(vo);
		if(result==ResultMessage.SUCCESS){
			//�ɹ�
			AlertBox a=new AlertBox();
			a.display("�ɹ�","�ɹ�");
			pStage.close();
		}else{
			//ʧ��
			AlertBox a=new AlertBox();
			a.display("ʧ��","ʧ��");
		}
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
