package inventoryui;

import java.io.IOException;
import java.util.ArrayList;

import VO.InventoryReceiptVO;
import VO.ReceiptVO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GetReceipt {
	static String j;
	static String n;
	static Stage pStage;
	private static String id;
	private ArrayList<InventoryReceiptVO> mylist=new ArrayList<>();// 得到所有的草稿的单据
	private RadioButton r1;// 报警
	private RadioButton r2;// 报损
	private RadioButton r3;// 赠送
	private RadioButton r4;// 报溢
	private ToggleGroup group;
	private static TableView<InventoryReceiptVO> table;
	private static TableColumn<InventoryReceiptVO,String> t1;//单据类型
	private static TableColumn<InventoryReceiptVO,String> t2;//制定时间
	private static TableColumn<InventoryReceiptVO,String> t3;//单据内容
	

	@SuppressWarnings("unchecked")
	public void display(String Job, String name,String ID) {
		// TODO Auto-generated method stub
		Stage primaryStage = new Stage();
		id=ID;
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

		table=new TableView();
		table.setStyle("-fx-background-color:null");
		table.setPrefWidth(600);
		table.setMinHeight(220);
		table.setMaxHeight(220);
		table.setLayoutX(97);
		table.setLayoutY(190);
		t1=new TableColumn("单据类型");
		t1.setPrefWidth(150);
		t2=new TableColumn("制定时间");
		t2.setPrefWidth(150);
		t3=new TableColumn("单据内容");
		t3.setPrefWidth(300);
		table.getColumns().addAll(t1,t2,t3);
		// 增加右键按钮
		ContextMenu cme = new ContextMenu();
		// 增加右键菜单的一些项
		MenuItem m1 = new MenuItem("创建报警单");
		cme.getItems().add(m1);
		MenuItem m2=new MenuItem("创建报溢单");
		cme.getItems().add(m2);
		MenuItem m3=new MenuItem("创建报损单");
		cme.getItems().add(m3);
		MenuItem m4=new MenuItem("创建赠送单");
		cme.getItems().add(m4);
		table.setContextMenu(cme);
		// 增加这个事件
		m1.setOnAction(new EventHandler<ActionEvent>() {// 报警单
			public void handle(ActionEvent e){
				//得到被选择的index,得到编号
				int index;
				index=table.getSelectionModel().getSelectedIndex();
				//写你的删除事件
			}
		});
		m2.setOnAction(new EventHandler<ActionEvent>() {// 报溢单
			public void handle(ActionEvent e){
				//得到被选择的index,得到编号
				int index;
				index=table.getSelectionModel().getSelectedIndex();
				//写你的编辑事件
			}
		});
		m3.setOnAction(new EventHandler<ActionEvent>() {// 报损单
			public void handle(ActionEvent e){
				//得到被选择的index,得到编号
				int index;
				index=table.getSelectionModel().getSelectedIndex();
				//写你的编辑事件
			}
		});
		m4.setOnAction(new EventHandler<ActionEvent>() {// 赠送单
			public void handle(ActionEvent e){
				//得到被选择的index,得到编号
				int index;
				index=table.getSelectionModel().getSelectedIndex();
				//写你的编辑事件
			}
		});

		// 设置单选框来进行筛选
		r1 = new RadioButton("报警单");
		r2 = new RadioButton("报损单");
		r3 = new RadioButton("赠送单");
		r4 = new RadioButton("报溢单");
		r1.setLayoutX(469);
		r1.setLayoutY(140);
		r2.setLayoutX(566);
		r2.setLayoutY(140);
		r3.setLayoutX(469);
		r3.setLayoutY(170);
		r4.setLayoutX(566);
		r4.setLayoutY(170);
		group = new ToggleGroup();
		r1.setToggleGroup(group);
		r2.setToggleGroup(group);
		r3.setToggleGroup(group);
		r4.setToggleGroup(group);
		group.selectedToggleProperty().addListener(
				new ChangeListener<Toggle>() {
					public void changed(ObservableValue<? extends Toggle> ov,
							Toggle toggle, Toggle new_toggle) {
						if (r1.isSelected()) {//报警
							//只显示报警单
							ArrayList<InventoryReceiptVO> ilist=new ArrayList<>();
						} else if (r2.isSelected()) {//报损
							//只显示报损单
						}else if(r3.isSelected()){//赠送
							//只显示赠送单
						}else if(r4.isSelected()){//报溢
							//只显示报溢单
						}
					}
				});

		AnchorPane a = new AnchorPane();
		a.getChildren().addAll(root, text1, text2, table, r1, r2, r3, r4);
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
		f.display(j, n,id);
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
