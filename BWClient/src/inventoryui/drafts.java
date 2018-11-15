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

	private ArrayList<InventoryReceiptVO> mylist = new ArrayList<>();// 得到所有的草稿的单据

	private static TableView<InventoryReceiptVO> table;

	private static TableColumn<InventoryReceiptVO, String> t1;// 单据类型

	private static TableColumn<InventoryReceiptVO, String> t2;// 制定时间

	private static TableColumn<InventoryReceiptVO, String> t3;// 单据内容

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

		text1.setText("欢迎您:" + name);

		text1.setLayoutX(613.0);

		text1.setLayoutY(40.0);

		text1.setMinHeight(36.0);

		text1.setMaxHeight(36.0);

		text1.setFont(Font.font("Verdana", 18));

		text1.prefWidth(185.0);

		text1.setEditable(false);

		TextField text2 = new TextField();

		text2.setStyle("-fx-background-color:null");

		text2.setText("当前职务:" + Job);

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

		t1 = new TableColumn("单据类型");

		t1.setPrefWidth(150);

		t2 = new TableColumn("制定时间");

		t2.setPrefWidth(150);

		t3 = new TableColumn("单据内容");

		t3.setPrefWidth(300);
		
		ObservableList<InventoryReceiptVO> data = FXCollections
				.observableArrayList();
		for (int i = 0; i < mylist.size(); i++) {
			data.add(mylist.get(i));
		}

		// 你不能这样给我显示这个银行账户，因为它必须要与显示的那个类要相关联

		t1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData
				.getValue().getType().toString()));// 单据类型

		t2.setCellValueFactory(cellData -> new SimpleStringProperty(""
				+ cellData.getValue().getDate()));// 单据时间
		t3.setCellValueFactory(cellData -> new SimpleStringProperty(cellData
				.getValue().getId()));// 单据编号

		table.setStyle("-fx-background-color:null");
		table.setItems(data);

		table.getColumns().addAll(t1, t2, t3);

		// 增加右键按钮

		ContextMenu cme = new ContextMenu();

		// 增加右键菜单的一些项

		MenuItem m1 = new MenuItem("删除");

		cme.getItems().add(m1);

		MenuItem m2 = new MenuItem("编辑");

		cme.getItems().add(m2);

		table.setContextMenu(cme);

		// 增加这个事件

		m1.setOnAction(new EventHandler<ActionEvent>() {// 删除

			public void handle(ActionEvent e) {

				// 得到被选择的index,得到编号

				int index;

				index = table.getSelectionModel().getSelectedIndex();

				InventoryReceiptVO tem = mylist.get(index);

				mylist.remove(index);

				InventoryReceiptHelper helper = InventoryReceiptHelper
						.getInstance();

				try {

					ResultMessage message = helper.getService().deleteReceipt(
							tem.getId());// 草稿单也有id

					if (message != ResultMessage.SUCCESS) {

						String warning = "Receipt delete fault.";
						
						AlertBox a=new AlertBox();
						a.display(warning,warning);

						return;

					}else{
						AlertBox a=new AlertBox();
						a.display("删除成功","删除成功");
					}

				} catch (RemoteException e1) {

					// TODO Auto-generated catch block

					e1.printStackTrace();

				}

			}

		});

		m2.setOnAction(new EventHandler<ActionEvent>() {// 编辑

			public void handle(ActionEvent e) {

				// 得到被选择的index,得到编号

				int index;

				index = table.getSelectionModel().getSelectedIndex();

				InventoryReceiptVO inventoryReceipt = mylist.get(index);
				System.out.println(inventoryReceipt.getId());

				if (inventoryReceipt.getInventoryType().equals(
						InventoryReceiptType.ALARM)) {

					// 跳到报警单界面，把inventoryReceipt传过去
					Alarm a=new Alarm();
					a.display(j,n,id,inventoryReceipt);

				}

				else if (inventoryReceipt.getInventoryType().equals(
						InventoryReceiptType.BREAKAGE)) {
					loss l=new loss();
					l.display(j,n,id,inventoryReceipt);

					// 跳到报损单界面，把inventoryReceipt传过去

				}

				else if (inventoryReceipt.getInventoryType().equals(
						InventoryReceiptType.GIFT)) {
					Gift g=new Gift();
					g.display(j,n,id,inventoryReceipt);

					// 跳到赠送单界面，把inventoryReceipt传过去

				}

				else if (inventoryReceipt.getInventoryType().equals(
						InventoryReceiptType.OVERFLOW)) {
					Overflow o=new Overflow();
					o.display(j,n,id,inventoryReceipt);

					// 跳到报溢单界面，把inventoryReceipt传过去

				}

			}

		});

		AnchorPane a = new AnchorPane();

		a.getChildren().addAll(root, text1, text2, table);

		Scene s = new Scene(a, 800, 500);

		primaryStage.setTitle("财务人员");

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