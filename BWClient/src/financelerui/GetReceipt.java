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
	private static ArrayList<ReceiptVO> mylist=new ArrayList<>();// 得到之前的单据
	private static RadioButton r1;// 单选按钮，筛选付款单
	private static RadioButton r2;// 筛选收款单
	private static RadioButton r3;// 筛选现金费用单
	private static ToggleGroup group;// 包括r1,r2,r3
	private static TField tf1;// 单据类型
	private static TableView<ReceiptVO> table;// 得到所有的单据的显示
	private static TableColumn<ReceiptVO, String> t1;// 单据类型
	private static TableColumn<ReceiptVO, String> t2;// 制定时间
	private static TableColumn<ReceiptVO, String> t3;// 单据内容

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

		// 列表显示
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

		// 进行显示
		// 需要用ObserValue来添加
		ObservableList<ReceiptVO> data = FXCollections.observableArrayList();
		for (int i = 0; i < mylist.size(); i++) {
			data.add(mylist.get(i));
		}

		// 你不能这样给我显示这个银行账户，因为它必须要与显示的那个类要相关联

		t1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData
				.getValue().getType().toString()));// 单据类型

		t2.setCellValueFactory(cellData -> new SimpleStringProperty(""
				+ cellData.getValue().getDate()));// 制定时间
		t3.setCellValueFactory(cellData -> new SimpleStringProperty(cellData
				.getValue().getId()));// 内容

		table.setItems(data);

		table.getColumns().addAll(t1, t2, t3);
		// 增加右键按钮
		ContextMenu cme = new ContextMenu();
		// 增加右键菜单的一些项
		MenuItem m1 = new MenuItem("制定现金费用单");
		cme.getItems().add(m1);
		MenuItem m2 = new MenuItem("制定付款单");
		cme.getItems().add(m2);
		MenuItem m3 = new MenuItem("制定收款单");
		cme.getItems().add(m3);
		table.setContextMenu(cme);
		// 增加这个事件
		m1.setOnAction(new EventHandler<ActionEvent>() {// 制定现金费用单
			public void handle(ActionEvent e) {
				// 得到被选择的index,得到编号
				int index;
				index = table.getSelectionModel().getSelectedIndex();
				// 调用制定现金费用单事件
			}
		});
		m2.setOnAction(new EventHandler<ActionEvent>() {// 制定收款单
			public void handle(ActionEvent e) {
				// 得到被选择的index,得到编号
				int index;
				index = table.getSelectionModel().getSelectedIndex();
				// 调用制定收款单
			}
		});
		m3.setOnAction(new EventHandler<ActionEvent>() {// 制定付款单
			public void handle(ActionEvent e) {
				// 得到被选择的index,得到编号
				int index;
				index = table.getSelectionModel().getSelectedIndex();
				// 调用制定付款单
			}
		});

		// 设置单选框来进行筛选
		r1 = new RadioButton("付款单");
		r2 = new RadioButton("收款单");
		r3 = new RadioButton("现金费用单");
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
		primaryStage.setTitle("财务人员");
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
