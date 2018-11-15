package inventoryui;

import java.io.IOException;
import java.rmi.RemoteException;

import mainframeui.AlertBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import rmi.CommodityHelper;
import VO.CategoryVO;
import VO.CommodityVO;
import enums.ResultMessage;

public class addCommodity {// 新建商品
	private static String j;
	private static String n;
	private static Stage pStage;
	private static String id;
	private static TField c1;// 选择商品分类
	private static TField tx1;// 输入商品名称
	private static TField tx2;// 输入型号
	private static TField tx3;// 输入进价
	private static TField tx4;// 输入最近进价
	private static TField tx5;// 输入警戒数量
	private static TField tx6;// 输入库存数量
	private static TField tx7;// 输入零售价
	private static TField tx8;// 输入最近售价
	private static TField tx9;// 输入编号
	private static TreeItem tr;

	@SuppressWarnings("unchecked")
	public void display(String Job, String name, String ID, String co,
			TreeItem t1) {
		tr = t1;
		// TODO Auto-generated method stub
		Stage primaryStage = new Stage();
		id = ID;
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("addCommodity.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		c1 = new TField();

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

		// 选择商品分类，在这进行添加
		c1 = new TField();
		c1.setLayoutX(523.0);
		c1.setLayoutY(202.0);
		c1.setEditable(false);
		c1.setText(co);
		c1.setPrefWidth(152.0);
		c1.setMinHeight(35.0);
		c1.setMaxHeight(35.0);
		AnchorPane a = new AnchorPane();

		// 商品名称
		TField tf1 = new TField();
		tf1.setText("商品名称");
		tf1.setPrefWidth(144);
		tf1.setLayoutX(148);
		tf1.setLayoutY(202);
		tf1.setEditable(false);
		// 输入商品名称
		tx1 = new TField();
		tx1.setPrefWidth(107);
		tx1.setLayoutX(292);
		tx1.setLayoutY(202);
		// 型号
		TField tf2 = new TField();
		tf2.setText("型号");
		tf2.setPrefWidth(144);
		tf2.setLayoutX(148);
		tf2.setLayoutY(237);
		tf2.setEditable(false);
		// 输入型号
		tx2 = new TField();
		tx2.setPrefWidth(107);
		tx2.setLayoutX(292);
		tx2.setLayoutY(237);
		// 进价
		TField tf3 = new TField();
		tf3.setText("进价");
		tf3.setPrefWidth(144);
		tf3.setLayoutX(148);
		tf3.setLayoutY(272);
		tf3.setEditable(false);
		// 输入进价
		tx3 = new TField();
		tx3.setPrefWidth(107);
		tx3.setLayoutX(292);
		tx3.setLayoutY(272);
		// 最近进价
		TField tf4 = new TField();
		tf4.setText("最近进价");
		tf4.setPrefWidth(144);
		tf4.setLayoutX(148);
		tf4.setLayoutY(307);
		tf4.setEditable(false);
		// 输入最近进价
		tx4 = new TField();
		tx4.setPrefWidth(107);
		tx4.setLayoutX(292);
		tx4.setLayoutY(307);
		// 警戒数量
		TField tf5 = new TField();
		tf5.setText("警戒数量");
		tf5.setPrefWidth(144);
		tf5.setLayoutX(148);
		tf5.setLayoutY(342);
		tf5.setEditable(false);
		// 输入警戒数量
		tx5 = new TField();
		tx5.setPrefWidth(107);
		tx5.setLayoutX(292);
		tx5.setLayoutY(342);
		// 所属分类
		TField tf6 = new TField();
		tf6.setText("所属分类");
		tf6.setPrefWidth(124);
		tf6.setLayoutX(399);
		tf6.setLayoutY(202);
		tf6.setEditable(false);
		// 库存数量
		TField tf7 = new TField();
		tf7.setText("库存数量");
		tf7.setPrefWidth(124);
		tf7.setLayoutX(399);
		tf7.setLayoutY(237);
		tf7.setEditable(false);
		// 输入库存数量
		tx6 = new TField();
		tx6.setPrefWidth(152);
		tx6.setLayoutX(523);
		tx6.setLayoutY(237);
		// 零售价
		TField tf8 = new TField();
		tf8.setText("零售价");
		tf8.setPrefWidth(124);
		tf8.setLayoutX(399);
		tf8.setLayoutY(272);
		tf8.setEditable(false);
		// 输入零售价
		tx7 = new TField();
		tx7.setPrefWidth(152);
		tx7.setLayoutX(523);
		tx7.setLayoutY(272);
		// 最近零售价
		TField tf9 = new TField();
		tf9.setText("最近零售价");
		tf9.setPrefWidth(124);
		tf9.setLayoutX(399);
		tf9.setLayoutY(307);
		tf9.setEditable(false);
		// 输入最近零售价
		tx8 = new TField();
		tx8.setPrefWidth(152);
		tx8.setLayoutX(523);
		tx8.setLayoutY(307);
		// 编号
		TField tf10 = new TField();
		tf10.setText("编号");
		tf10.setPrefWidth(124);
		tf10.setLayoutX(399);
		tf10.setLayoutY(342);
		tf10.setEditable(false);
		// 自动生成编号
		tx9 = new TField();
		tx9.setPrefWidth(152);
		tx9.setLayoutX(523);
		tx9.setLayoutY(342);
		tx9.setEditable(false);

		a.getChildren().addAll(root, c1, text1, text2, tf1, tf2, tf3, tf4, tf5,
				tf6, tf7, tf8, tf9, tf10, tx1, tx2, tx3, tx4, tx5, tx6, tx7,
				tx8, tx9);

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
		h.display(j, n, id);
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
	private void HandleAdd(ActionEvent event) throws RemoteException {// 增加的方法
		TreeItem tree = new TreeItem(tx1.getText());
		tr.getChildren().add(tree);
		AlertBox a = new AlertBox();
		a.display("新建商品成功", "新建商品成功");
		pStage.close();
		String name = tx1.getText();
		String category = c1.getText();
		String version = tx2.getText();
		int quantity = Integer.parseInt(tx6.getText());
		String mistake = "";
		if (quantity < 0) {
			mistake = mistake + "商品库存数量不能为负";
		}
		double bid = Double.valueOf(tx3.getText());
		if (bid < 0) {
			mistake = mistake + "商品进价不能为负";
		}
		double retailPrice = Double.valueOf(tx7.getText());
		if (retailPrice < 0) {
			mistake = mistake + "商品零售价不能为负";
		}
		double recentBid = bid;
		if (tx4.getText() != null) {
			recentBid = Double.valueOf(tx4.getText());
		}
		if (recentBid < 0) {
			mistake = mistake + "最近进价不能为负";
		}
		double recentRetailPrice = retailPrice;
		if (tx8.getText() != null) {
			recentRetailPrice = Double.valueOf(tx8.getText());
		}
		if (recentRetailPrice < 0) {
			mistake = mistake + "最近零售价不能为负";
		}
		int alertQuantity = Integer.parseInt(tx5.getText());
		if (alertQuantity < 0) {
			mistake = mistake + "警戒数量不能为负";
		}
		if (mistake != "") {
			// 显示mistake
			return;
		}
		CommodityVO com = new CommodityVO();
		com.setAlertQuantity(alertQuantity);
		com.setBid(recentBid);
		com.setCategory(category);
		com.setId(null);
		com.setName(name);
		com.setQuantity(quantity);
		com.setRecentBid(recentBid);
		com.setRecentRetailPrice(recentRetailPrice);
		com.setRetailPrice(recentRetailPrice);
		com.setVersion(version);
		CommodityHelper helper = CommodityHelper.getInstance();
		ResultMessage result = helper.getService().addCommodity(com);
		if (result == ResultMessage.COMMODITY_INSERT_FAIL_NAME_ALREADY_EXSIT) {
			// 显示“商品名称已存在，请重新输入”
		} else if (result == ResultMessage.COMMODITU_INSERT_SUCCESS) {
			// 显示“新建商品成功”

		}
	}

	@FXML
	private void HandleEsc(ActionEvent event) {
		pStage.close();
	}
}
