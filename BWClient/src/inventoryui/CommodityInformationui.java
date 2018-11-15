package inventoryui;

import java.io.IOException;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import VO.CommodityVO;
import VO.RecordVO;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import rmi.InventoryHelper;

public class CommodityInformationui {// 库存查看
	private static String j;
	private static String n;
	private static Stage pStage;
	private static String id;
	private static TField c1;// 选择开始时间
	private static TField c2;// 选择结束时间
	private static TableView<RecordVO> t;// 列表显示
	private static TableColumn<RecordVO,String> t1;// 商品名称
	private static TableColumn<RecordVO,String> t2;// 商品编号
	private static TableColumn<RecordVO,String> t3;// 出入库情况
	private static TableColumn<RecordVO,String> t4;// 数量
	private static TableColumn<RecordVO,String> t5;// 价格
	private static TableColumn<RecordVO,String> t6;// 时间
	private static ArrayList<RecordVO> recordlist=new ArrayList<>();

	@SuppressWarnings("unchecked")
	public void display(String Job, String name,String ID) {
		// TODO Auto-generated method stub
		Stage primaryStage = new Stage();
		id=ID;
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource(
					"CommodityInformationui.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 显示登录人的信息
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

		// 选择开始时间
		c1 = new TField();
		c1.setText("选择开始时间 ");
		c1.setLayoutX(159.0);
		c1.setLayoutY(176.0);
		c1.setPrefWidth(152.0);
		c1.setMinHeight(32.0);
		c1.setMaxHeight(32.0);
		// 选择结束时间
		c2 = new TField();
		c2.setText("选择结束时间 ");
		c2.setEditable(true);
		c2.setLayoutX(565.0);
		c2.setLayoutY(176.0);
		c2.setPrefWidth(152.0);
		c2.setMinHeight(32.0);
		c2.setMaxHeight(32.0);

		// 库存盘点的情况显示,利用tableview
		t = new TableView();
		t.setLayoutX(68.0);
		t.setLayoutY(212.0);
		t.setMinHeight(220.0);
		t.setMaxHeight(220.0);
		t.setPrefWidth(645.0);
		t1 = new TableColumn("商品名称");
		t1.setPrefWidth(75.0);
		t2 = new TableColumn("商品编号");
		t2.setPrefWidth(75.0);
		t3 = new TableColumn("出入库情况");
		t3.setPrefWidth(153.0);
		t4 = new TableColumn("数量");
		t4.setPrefWidth(100.0);
		t5 = new TableColumn("价格");
		t5.setPrefWidth(101.0);
		t6 = new TableColumn("时间");
		t6.setPrefWidth(140.0);
		t.getColumns().addAll(t1, t2, t3, t4, t5, t6);
		t.setStyle("-fx-background-color:null");

		c2.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER) {
					InventoryHelper helper=InventoryHelper.getInstance();
					DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");
					Date startTime;
					try {
						startTime = fmt.parse(c1.getSelection().toString());
						Date endTime = fmt.parse(c2.getSelection().toString());
						ArrayList<RecordVO> records=helper.getService().showRecord(startTime,endTime);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//显示records里面的内容

				}
			}
		});

		AnchorPane a = new AnchorPane();
		a.getChildren().addAll(root, c1, text1, text2, c2, t);

		Scene s = new Scene(a, 800, 500);
		primaryStage.setTitle("库存管理人员");
		primaryStage.setScene(s);
		j = Job;
		n = name;
		pStage = primaryStage;
		primaryStage.show();
	}

	@FXML
	private void HandleHelp(ActionEvent event) {
		HelpIn h = new HelpIn();
		h.display(j, n,id);
	}

	@FXML
	private void HandleOut(ActionEvent event) {
		pStage.close();
	}

	@FXML
	private void HandleCancle(ActionEvent event) {
		pStage.close();
	}

	@FXML
	private void HandleEsc(ActionEvent event) {
		pStage.close();
	}

	@FXML
	private void HandleSure(ActionEvent event) throws RemoteException, ParseException {
		InventoryHelper helper=InventoryHelper.getInstance();
		DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd HH::mm");
		Date startTime = fmt.parse(c1.getSelection().toString());
		Date endTime = fmt.parse(c2.getSelection().toString());
		ArrayList<RecordVO> records=helper.getService().showRecord(startTime,endTime);
		//显示records里面的内容
	}

	@FXML
	private void HandleExport(ActionEvent event) {// 导出功能

	}

}
