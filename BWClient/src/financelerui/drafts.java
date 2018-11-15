package financelerui;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import mainframeui.AlertBox;
import rmi.FinancialReceiptHelper;
import rmi.SalesHelper;
import enums.FinancialReceiptType;
import enums.ResultMessage;
import VO.FinancialReceiptVO;
import VO.ReceiptVO;
import VO.SalesReceiptVO;
import VO.UserVO;
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
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class drafts {
	static String j;
	static String n;
	static Stage pStage;
	private static String id;
	private static TableView<FinancialReceiptVO> table;// ��ʾ���ݲݸ�
	private static TableColumn<FinancialReceiptVO, String> t1;// ��������
	private static TableColumn<FinancialReceiptVO, String> t2;// �ƶ�ʱ��
	private static TableColumn<FinancialReceiptVO, String> t3;// ��������
	private static ArrayList<FinancialReceiptVO> mylist = new ArrayList<>();// �õ����еĲݸ�ĵ���

	@SuppressWarnings("unchecked")
	public void display(String Job, String name, String ID) {
		// TODO Auto-generated method stub
		Stage primaryStage = new Stage();
		Parent root = null;
		id = ID;
		try {
			root = FXMLLoader.load(getClass().getResource("drafts.fxml"));
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

		table = new TableView();
		table.setStyle("-fx-background-color:null");
		table.setPrefWidth(600);
		table.setMinHeight(200);
		table.setMaxHeight(200);
		table.setLayoutX(97);
		table.setLayoutY(190);
		t1 = new TableColumn("��������");
		t1.setPrefWidth(150);
		t2 = new TableColumn("�ƶ�ʱ��");
		t2.setPrefWidth(150);
		t3 = new TableColumn("���ݱ��");
		t3.setPrefWidth(300);
		table.getColumns().addAll(t1, t2, t3);
		// ��ʾ
		// ��Ҫ��ObserValue�����
		ObservableList<FinancialReceiptVO> data = FXCollections
				.observableArrayList();
		for (int i = 0; i < mylist.size(); i++) {
			data.add(mylist.get(i));
		}

		// �㲻������������ʾ��������˻�����Ϊ������Ҫ����ʾ���Ǹ���Ҫ�����

		t1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData
				.getValue().getType().toString()));// ��������

		t2.setCellValueFactory(cellData -> new SimpleStringProperty(""
				+ cellData.getValue().getDate()));// ����ʱ��
		t3.setCellValueFactory(cellData -> new SimpleStringProperty(cellData
				.getValue().getId()));// ���ݱ��

		table.setStyle("-fx-background-color:null");
		table.setItems(data);

		// �����Ҽ���ť
		ContextMenu cme = new ContextMenu();
		// �����Ҽ��˵���һЩ��
		MenuItem m1 = new MenuItem("ɾ��");
		cme.getItems().add(m1);
		MenuItem m2 = new MenuItem("�༭");
		cme.getItems().add(m2);
		table.setContextMenu(cme);
		// ��������¼�
		m1.setOnAction(new EventHandler<ActionEvent>() {// ɾ��
			public void handle(ActionEvent e) {
				// �õ���ѡ���index,�õ����
				int index;
				index = table.getSelectionModel().getSelectedIndex();
				// д���ɾ���¼�
				// Ȼ����ҲҪ�ڱ���ݸ嵥�ĵط�ɾ����
				FinancialReceiptVO r1 = mylist.get(index);
				mylist.remove(index);
				FinancialReceiptHelper helper = FinancialReceiptHelper
						.getInstance();
				try {
					ResultMessage message = helper.getService().deleteDraft(
							r1.getId());// �ݸ嵥Ҳ��id
					if (message != ResultMessage.SUCCESS) {
						String warning = "Receipt delete fault.";
						AlertBox a = new AlertBox();
						a.display(warning, warning);
						return;
					} else {
						AlertBox a = new AlertBox();
						a.display("ɾ���ɹ�", "ɾ���ɹ�");
						ObservableList<FinancialReceiptVO> data = FXCollections
								.observableArrayList();
						for (int i = 0; i < mylist.size(); i++) {
							data.add(mylist.get(i));
						}
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		m2.setOnAction(new EventHandler<ActionEvent>() {// �༭
			public void handle(ActionEvent e) {
				// �õ���ѡ���index,�õ����
				int index;
				index = table.getSelectionModel().getSelectedIndex();
				// д��ı༭�¼�
				FinancialReceiptVO r1 = mylist.get(index);
				if (r1.getFinancialReceiptType() == FinancialReceiptType.BILL) {// ���
					Moneyui m = new Moneyui();
					m.display(j, n, id, r1);
				} else if (r1.getFinancialReceiptType() == FinancialReceiptType.DEBIT_NOTE) {// �տ
					getMoneyui g = new getMoneyui();
					g.display(j, n, id, r1);
				} else if (r1.getFinancialReceiptType() == FinancialReceiptType.CASH_CLAIM) {// �ֽ���õ�
					cost c = new cost();
					c.display(j, n, id, r1);
				}
				mylist.remove(index);
				FinancialReceiptHelper helper = FinancialReceiptHelper
						.getInstance();
				try {
					ResultMessage message = helper.getService().deleteDraft(
							r1.getId());// �ݸ嵥Ҳ��id
					if (message != ResultMessage.SUCCESS) {
						String warning = "Receipt delete fault.";
						return;
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		AnchorPane a = new AnchorPane();
		a.getChildren().addAll(root, text1, text2, table);
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
	private ArrayList<SalesReceiptVO> getAllList() {

		SalesHelper helper = SalesHelper.getInstance();

		try {
			
			return helper.getService().getDraftList(id);

		} catch (RemoteException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

		return new ArrayList<>();

	}
}
