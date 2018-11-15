package inventoryui;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import mainframeui.AlertBox;
import rmi.InventoryReceiptHelper;
import VO.InventoryReceiptVO;
import enums.InventoryReceiptType;
import enums.ResultMessage;

public class drafts {

	private static String j;

	private static String n;

	private static Stage pStage;

	private static String id;

	private ArrayList<InventoryReceiptVO> mylist = new ArrayList<>();// �õ����еĲݸ�ĵ���

	private static TableView<InventoryReceiptVO> table;

	private static TableColumn<InventoryReceiptVO, String> t1;// ��������

	private static TableColumn<InventoryReceiptVO, String> t2;// �ƶ�ʱ��

	private static TableColumn<InventoryReceiptVO, String> t3;// ��������

	@SuppressWarnings("unchecked")
	public void display(String Job, String name, String ID) {

		// TODO Auto-generated method stub

		Stage primaryStage = new Stage();

		id = ID;
		
		drafts d=new drafts();
		mylist=d.getAllList();

		Parent root = null;

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
		
		ObservableList<InventoryReceiptVO> data = FXCollections
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

		table.getColumns().addAll(t1, t2, t3);

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

				InventoryReceiptVO tem = mylist.get(index);

				mylist.remove(index);

				InventoryReceiptHelper helper = InventoryReceiptHelper
						.getInstance();

				try {

					ResultMessage message = helper.getService().deleteReceipt(
							tem.getId());// �ݸ嵥Ҳ��id

					if (message != ResultMessage.SUCCESS) {

						String warning = "Receipt delete fault.";
						
						AlertBox a=new AlertBox();
						a.display(warning,warning);

						return;

					}else{
						AlertBox a=new AlertBox();
						a.display("ɾ���ɹ�","ɾ���ɹ�");
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

				InventoryReceiptVO inventoryReceipt = mylist.get(index);
				System.out.println(inventoryReceipt.getId());

				if (inventoryReceipt.getInventoryType().equals(
						InventoryReceiptType.ALARM)) {

					// �������������棬��inventoryReceipt����ȥ
					Alarm a=new Alarm();
					a.display(j,n,id,inventoryReceipt);

				}

				else if (inventoryReceipt.getInventoryType().equals(
						InventoryReceiptType.BREAKAGE)) {
					loss l=new loss();
					l.display(j,n,id,inventoryReceipt);

					// �������𵥽��棬��inventoryReceipt����ȥ

				}

				else if (inventoryReceipt.getInventoryType().equals(
						InventoryReceiptType.GIFT)) {
					Gift g=new Gift();
					g.display(j,n,id,inventoryReceipt);

					// �������͵����棬��inventoryReceipt����ȥ

				}

				else if (inventoryReceipt.getInventoryType().equals(
						InventoryReceiptType.OVERFLOW)) {
					Overflow o=new Overflow();
					o.display(j,n,id,inventoryReceipt);

					// �������絥���棬��inventoryReceipt����ȥ

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

		HelpIn f = new HelpIn();

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

	private ArrayList<InventoryReceiptVO> getAllList() {

		InventoryReceiptHelper helper = InventoryReceiptHelper.getInstance();

		try {

			return helper.getService().getDraftList(id);

		} catch (RemoteException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

		return new ArrayList<>();

	}

}