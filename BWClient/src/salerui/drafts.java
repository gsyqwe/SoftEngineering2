package salerui;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import mainframeui.AlertBox;
import VO.FinancialReceiptVO;
import VO.SalesReceiptVO;
import enums.ResultMessage;
import enums.SalesReceiptType;
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
import rmi.SalesHelper;

public class drafts {

	private static String j;

	private static String n;

	private static Stage pStage;

	private static String id;

	private ArrayList<SalesReceiptVO> mylist = new ArrayList<>();// 得到所有的草稿的单据，得到草稿的单据应该是SalesReceiptVO

	private static TableView<SalesReceiptVO> table;

	private static TableColumn<SalesReceiptVO, String> t1;// 单据类型

	private static TableColumn<SalesReceiptVO, String> t2;// 制定时间

	private static TableColumn<SalesReceiptVO, String> t3;// 单据内容

	@SuppressWarnings("unchecked")
	public void display(String Job, String name, String ID) {

		// TODO Auto-generated method stub

		Stage primaryStage = new Stage();

		id = ID;

		Parent root = null;

		drafts d = new drafts();
		mylist = d.getAllList();// 得到全部的草稿单据
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

		table.setMinHeight(200);

		table.setMaxHeight(200);

		table.setLayoutX(97);

		table.setLayoutY(210);

		t1 = new TableColumn("单据类型");

		t1.setPrefWidth(150);

		t2 = new TableColumn("制定时间");

		t2.setPrefWidth(150);

		t3 = new TableColumn("单据编号");

		t3.setPrefWidth(300);

		// 需要用ObserValue来添加
		ObservableList<SalesReceiptVO> data = FXCollections
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

		MenuItem m2 = new MenuItem("编辑");// 点击编辑草稿后在bl层上无方法调用，就是调到编辑界面，然后显示原来的草稿信息

		cme.getItems().add(m2);

		table.setContextMenu(cme);

		// 增加这个事件

		m1.setOnAction(new EventHandler<ActionEvent>() {// 删除

			@Override
			public void handle(ActionEvent e) {

				// 得到被选择的index,得到编号

				int index;

				index = table.getSelectionModel().getSelectedIndex();

				SalesReceiptVO tem = mylist.get(index);

				SalesHelper helper = SalesHelper.getInstance();

				try {

					ResultMessage message = helper.getService().deleteReceipt(
							tem.getId());// 草稿单也有id

					if (message != ResultMessage.SUCCESS) {

						String warning = "Receipt delete fault.";
						AlertBox a = new AlertBox();
						a.display(warning, warning);

						return;

					} else {
						AlertBox a = new AlertBox();
						a.display("删除成功", "删除成功");
						mylist.remove(index);
						// 写你的删除事件
						ObservableList<SalesReceiptVO> data = FXCollections
								.observableArrayList();
						for (int i = 0; i < mylist.size(); i++) {
							data.add(mylist.get(i));
						}
						table.setItems(data);

					}

				} catch (RemoteException e1) {

					// TODO Auto-generated catch block

					e1.printStackTrace();

				}

			}

		});

		m2.setOnAction(new EventHandler<ActionEvent>() {// 编辑

			@Override
			public void handle(ActionEvent e) {

				// 得到被选择的index,得到编号

				int index;

				index = table.getSelectionModel().getSelectedIndex();

				// 写你的编辑事件

				SalesReceiptVO sareceipt = mylist.get(index);

				if (sareceipt.getSalesType().equals(SalesReceiptType.PURCHASE)) {

					In i = new In();

					i.display(j, n, id, sareceipt);

				} else if (sareceipt.getSalesType().equals(
						SalesReceiptType.PURCHASE_RETURN)) {
					InBack i = new InBack();
					i.display(j, n, id, sareceipt);
				} else if (sareceipt.getSalesType().equals(
						SalesReceiptType.SALES)) {
					Saleui s = new Saleui();
					s.display(j, n, id, sareceipt);
				} else if (sareceipt.getSalesType().equals(
						SalesReceiptType.SALES_RETURN)) {
					SaleBackui s = new SaleBackui();
					s.display(j, n, id, sareceipt);
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

		HelpSa f = new HelpSa();

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