package salerui;

import java.io.IOException;
import java.rmi.RemoteException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import mainframeui.AlertBox;
import mainframeui.TField;
import rmi.BusinessProcessHelper;
import rmi.CheckReceiptHelper;
import rmi.CommodityHelper;
import rmi.MemberHelper;
import rmi.SalesHelper;
import VO.CommodityVO;
import VO.LineItemVO;
import VO.MemberVO;
import VO.SalesReceiptVO;
import VO.VoucherVO;
import enums.MemberType;
import enums.ResultMessage;
import enums.SalesReceiptType;

/**
 *
 * @author JiYuzhe 不需要向新建销售单一样一行一行添加 销售单在红冲并复制和总经理的编辑界面中不会修改该单据的促销策略
 *         即如果是总经理编辑该单据，促销策略不变 如果是财务人员红冲并复制单据，则不考虑促销策略
 *
 */
public class SaleBackuiX {// 红冲并复制和总经理的编辑界面
	private static String j;

	private static String n;

	private static Stage pStage;

	private static String id;

	private static ArrayList<LineItemVO> mylist = new ArrayList<>();// 维护这个LineItem

	private static ComboBox comboBox1;// 选择客户

	private static ComboBox comboBox2;// 选择仓库

	private static ComboBox com[] = new ComboBox[6];// 选择商品

	private static TField t1[] = new TField[6];// 商品编号，选择完生成

	private static TField t2[] = new TField[6];// 商品型号

	private static TField t3[] = new TField[6];// 数量

	private static TField t4[] = new TField[6];// 单价

	private static TField t5[] = new TField[6];// 备注

	private static TField tf2;

	private static TField tf3;// 原总价

	private static TField tf4;// 原折让

	private static ComboBox cm1;// 选择已用代金券

	private static TField tf5;// 最终金额

	private static TField Timefield;// 时间

	private static boolean isAdd[] = new boolean[6];

	private static int k = 0;

	private static ArrayList<CommodityVO> mylist2 = new ArrayList<>();// 得到所有商品的ArrayList

	private static CommodityVO commodity;// 选择后的商品

	private static MemberVO member;
	private static ArrayList<MemberVO> memberlist = new ArrayList<>();
	private static VoucherVO voucher;

	private static SalesReceiptVO sales = new SalesReceiptVO();// 因为不知道rmi的调用对Controller初始化的影响，

	// 所以决定在界面层维护一个单据VO，在调用提交的时候再将完整的vo传入逻辑层

	@SuppressWarnings("unchecked")
	public void display(String Job, String name, String ID,
			SalesReceiptVO SaReceipt) {
		// TODO Auto-generated method stub
		sales = SaReceipt;
		sales.setSalesType(SalesReceiptType.SALES_RETURN);
		SaleBackuiX s = new SaleBackuiX();
		mylist2 = s.getAllCommodity();
		memberlist = s.getAllMember();

		Stage primaryStage = new Stage();

		id = ID;

		Parent root = null;

		try {

			root = FXMLLoader.load(getClass().getResource("SaleBackuiX.fxml"));

		} catch (IOException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

		AnchorPane a = new AnchorPane();

		TextField text1 = new TextField();

		text1.setStyle("-fx-background-color:null");

		text1.setText("欢迎您:" + name);

		text1.setLayoutX(913.0);

		text1.setLayoutY(40.0);

		text1.setMinHeight(36.0);

		text1.setMaxHeight(36.0);

		text1.setFont(Font.font("Verdana", 18));

		text1.prefWidth(185.0);

		text1.setEditable(false);

		// ComboBox1选择客户

		comboBox1 = new ComboBox();

		comboBox1.setValue("选择客户");

		comboBox1.setLayoutX(133.0);

		comboBox1.setLayoutY(313.0);

		comboBox1
				.setStyle("-fx-border-color:Grey;-fx-border-radius:4;-fx-background-color:null");

		for (int i = 0; i < memberlist.size(); i++) {
			comboBox1.getItems().addAll(memberlist.get(i).getName());
		}
		comboBox1.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener() {
					@Override
					public void changed(ObservableValue arg0, Object arg1,
							Object arg2) {
						// TODO Auto-generated method stub
						int index = comboBox1.getSelectionModel()
								.getSelectedIndex();
						member = memberlist.get(index);
					}
				});

		comboBox1.setMinHeight(30.0);

		comboBox1.setMaxHeight(30.0);

		comboBox1.setMinWidth(133.0);

		comboBox1.setMaxWidth(133.0);

		// ComboBox2选择仓库

		comboBox2 = new ComboBox();

		comboBox2.setValue("选择仓库");

		comboBox2
				.setStyle("-fx-border-color:Grey;-fx-border-radius:4;-fx-background-color:null");

		comboBox2.setLayoutX(621.0);

		comboBox2.setLayoutY(240.0);

		comboBox2.getItems().addAll("1号仓库");

		comboBox2.setMinHeight(30.0);

		comboBox2.setMaxHeight(30.0);

		comboBox2.setMinWidth(125.0);

		comboBox2.setMaxWidth(125.0);

		// 2调显示操作员信息

		TField text3 = new TField();

		text3.setEditable(false);

		text3.setText(name);

		text3.setLayoutX(907.0);

		text3.setLayoutY(231.0);

		text3.setMinHeight(30.0);

		text3.setMaxHeight(30.0);

		text3.setMinWidth(110.0);

		text3.setMaxWidth(110.0);

		TextField text2 = new TextField();

		text2.setStyle("-fx-background-color:null");

		text2.setText("当前职务:" + Job);

		text2.setLayoutX(907.0);

		text2.setLayoutY(68.0);

		text2.setMinHeight(30.0);

		text2.setMaxHeight(30.0);

		text2.setFont(Font.font("Verdana", 18));

		text2.prefWidth(185.0);

		text2.setEditable(false);

		// 创建ScollPane

		ScrollPane s1 = new ScrollPane();

		s1.setLayoutX(70);

		s1.setLayoutY(400);

		s1.setMinHeight(320);

		s1.setMaxHeight(320);

		s1.setPrefWidth(866);

		AnchorPane a1 = new AnchorPane();

		a1.setMinHeight(310);

		a1.setMaxHeight(310);

		a1.setPrefWidth(816);

		ImageView i1 = new ImageView(new Image("/backgroud.png", true));

		i1.setFitWidth(816);

		i1.setFitHeight(310);

		a1.getChildren().add(i1);

		// 商品名称

		TField tx1 = new TField();

		tx1.setText("商品名称");

		tx1.setEditable(false);

		tx1.setMinHeight(35);

		tx1.setMaxHeight(35);

		tx1.setPrefWidth(160);

		tx1.setLayoutX(0);

		tx1.setLayoutY(0);

		// 选择商品名称

		for (int i = 0; i < 6; i++) {

			com[i] = new ComboBox();

			com[i].setMinHeight(35);

			com[i].setMaxHeight(35);

			com[i].setStyle("-fx-border-color:Grey;-fx-border-radius:4;-fx-background-color:null");

			com[i].setPrefWidth(160);
			for (int j = 0; j < mylist2.size(); j++) {
				com[i].getItems().add(mylist2.get(j).getName());
			}

			com[i].setLayoutX(0);

			com[i].setLayoutY(35 * (i + 1));

			a1.getChildren().add(com[i]);

		}

		// 编号

		TField tx2 = new TField();

		tx2.setText("商品编号");

		tx2.setEditable(false);

		tx2.setPrefWidth(90);

		tx2.setLayoutX(160);

		tx2.setMinHeight(35);

		tx2.setMaxHeight(35);

		tx2.setLayoutY(0);

		// 根据选择的商品生成编号

		for (int i = 0; i < 6; i++) {

			t1[i] = new TField();

			t1[i].setPrefWidth(90);

			t1[i].setLayoutX(160);

			t1[i].setMinHeight(35);

			t1[i].setMaxHeight(35);

			t1[i].setEditable(false);

			t1[i].setLayoutY(35 * (i + 1));

			a1.getChildren().add(t1[i]);

		}
		// 设置事件将com与t1捆绑
		com[0].getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener() {
					@Override
					public void changed(ObservableValue arg0, Object arg1,
							Object arg2) {
						// TODO Auto-generated method stub
						int index = com[0].getSelectionModel()
								.getSelectedIndex();
						commodity = mylist2.get(index);
						t1[0].setText(commodity.getId());
					}
				});

		com[1].getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener() {
					@Override
					public void changed(ObservableValue arg0, Object arg1,
							Object arg2) {
						// TODO Auto-generated method stub
						int index = com[1].getSelectionModel()
								.getSelectedIndex();
						commodity = mylist2.get(index);
						t1[1].setText(commodity.getId());
					}
				});

		com[2].getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener() {
					@Override
					public void changed(ObservableValue arg0, Object arg1,
							Object arg2) {
						// TODO Auto-generated method stub
						int index = com[2].getSelectionModel()
								.getSelectedIndex();
						commodity = mylist2.get(index);
						t1[2].setText(commodity.getId());
					}
				});

		com[3].getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener() {
					@Override
					public void changed(ObservableValue arg0, Object arg1,
							Object arg2) {
						// TODO Auto-generated method stub
						int index = com[3].getSelectionModel()
								.getSelectedIndex();
						commodity = mylist2.get(index);
						t1[3].setText(commodity.getId());
					}
				});

		com[4].getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener() {
					@Override
					public void changed(ObservableValue arg0, Object arg1,
							Object arg2) {
						// TODO Auto-generated method stub
						int index = com[4].getSelectionModel()
								.getSelectedIndex();
						commodity = mylist2.get(index);
						t1[4].setText(commodity.getId());
					}
				});

		com[5].getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener() {
					@Override
					public void changed(ObservableValue arg0, Object arg1,
							Object arg2) {
						// TODO Auto-generated method stub
						int index = com[5].getSelectionModel()
								.getSelectedIndex();
						commodity = mylist2.get(index);
						t1[5].setText(commodity.getId());
					}
				});

		// 型号

		TField tx3 = new TField();

		tx3.setText("型号");

		tx3.setEditable(false);

		tx3.setMinHeight(35);

		tx3.setMaxHeight(35);

		tx3.setPrefWidth(110);

		tx3.setLayoutX(230);

		tx3.setLayoutY(0);

		// 输入型号

		for (int i = 0; i < 6; i++) {

			t2[i] = new TField();

			t2[i].setMinHeight(35);

			t2[i].setMaxHeight(35);

			t2[i].setPrefWidth(110);

			t2[i].setLayoutX(230);

			t2[i].setLayoutY(35 * (i + 1));

			a1.getChildren().add(t2[i]);

		}

		// 数量

		TField tx4 = new TField();

		tx4.setText("数量");

		tx4.setEditable(false);

		tx4.setMinHeight(35);

		tx4.setMaxHeight(35);

		tx4.setPrefWidth(110);

		tx4.setLayoutX(320);

		tx4.setLayoutY(0);

		// 输入数量

		for (int i = 0; i < 6; i++) {

			t3[i] = new TField();

			t3[i].setMinHeight(35);

			t3[i].setMaxHeight(35);

			t3[i].setPrefWidth(110);

			t3[i].setLayoutX(320);

			t3[i].setLayoutY(35 * (i + 1));

			a1.getChildren().add(t3[i]);

		}

		// 单价

		TField tx5 = new TField();

		tx5.setText("单价");

		tx5.setEditable(false);

		tx5.setMinHeight(35);

		tx5.setMaxHeight(35);

		tx5.setPrefWidth(110);

		tx5.setLayoutX(410);

		tx5.setLayoutY(0);

		// 输入单价

		for (int i = 0; i < 6; i++) {

			t4[i] = new TField();

			t4[i].setMinHeight(35);

			t4[i].setMaxHeight(35);

			t4[i].setPrefWidth(110);

			t4[i].setLayoutX(410);

			t4[i].setLayoutY(35 * (i + 1));

			a1.getChildren().add(t4[i]);

		}

		// 备注

		TField tx6 = new TField();

		tx6.setText("备注");

		tx6.setLayoutX(500);

		tx6.setLayoutY(0);

		tx6.setPrefWidth(216);

		tx6.setMinHeight(35);

		tx6.setMaxHeight(35);

		tx6.setEditable(false);

		// 输入备注

		for (int i = 0; i < 6; i++) {

			t5[i] = new TField();

			t5[i].setMinHeight(35);

			t5[i].setMaxHeight(35);

			t5[i].setPrefWidth(216);

			t5[i].setLayoutX(500);

			t5[i].setLayoutY(35 * (i + 1));

			a1.getChildren().add(t5[i]);

		}

		// 增加的按钮

		Button b[] = new Button[6];

		for (int i = 0; i < 6; i++) {

			b[i] = new Button();

			b[i].setPrefWidth(30);

			b[i].setMinHeight(30);

			b[i].setMaxHeight(30);

			b[i].setLayoutX(736);

			isAdd[i] = true;

			b[i].setLayoutY(35 * (1 + i));
			b[i].setStyle("-fx-background-color:null");
			b[i].setStyle("-fx-background-image:url('@../add.jpg')");
			a1.getChildren().add(b[i]);
		}

		b[0].setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				// TODO Auto-generated method stub

				if (isAdd[0]) {

					String name = com[0].getPromptText();

					String id = t1[0].getText();

					String xinghao = t2[0].getText();

					String quantity = t3[0].getText();

					String danjia = t4[0].getText();

					String commence = t5[0].getText();

					if (quantity == null || quantity.equals("")) {

						String warning = "the quantity cannot be empty.";

						AlertBox a = new AlertBox();
						a.display(warning, warning);
						return;

					}

					if (danjia == null || danjia.equals("")) {

						String warning = "the price cannnot be empty.";

						return;

					}

					LineItemVO l1 = new LineItemVO(id, Integer
							.parseInt(quantity), Double.parseDouble(danjia),

					commence);
					isAdd[0] = false;
					mylist.add(l1);
					b[0].setStyle("-fx-background-image:url('@../delete.jpg')");

				} else {// 删除
					isAdd[0] = true;
					mylist.remove(0);
					b[0].setStyle("-fx-background-image:url('@../add.jpg')");
					// 进行依次上移
					for (int i = 1; i < 6; i++) {
						if (t1[i].getText().equals("")) {
							// 不动
						} else {
							t1[i - 1].setText(t1[i].getText());
						}
						if (t2[i].getText().equals("")) {

						} else {
							t2[i - 1].setText(t2[i].getText());
						}
						if (t3[i].getText().equals("")) {

						} else {
							t3[i - 1].setText(t3[i].getText());
						}
						if (t4[i].getText().equals("")) {

						} else {
							t4[i - 1].setText(t4[i].getText());
						}
						if (t5[i].getText().equals("")) {

						} else {
							t5[i - 1].setText(t5[i - 1].getText());
						}
						if (isAdd[i]) {

						} else {
							isAdd[i - 1] = isAdd[i];
						}
					}

				}

			}

		});

		b[1].setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				// TODO Auto-generated method stub

				if (isAdd[1]) {

					String name = com[1].getPromptText();

					String id = t1[1].getText();

					String xinghao = t2[1].getText();

					String quantity = t3[1].getText();

					String danjia = t4[1].getText();

					String commence = t5[1].getText();

					if (quantity == null || quantity.equals("")) {

						String warning = "the quantity cannot be empty.";

						AlertBox a = new AlertBox();
						a.display(warning, warning);
						return;

					}

					if (danjia == null || danjia.equals("")) {

						String warning = "the price cannnot be empty.";

						return;

					}

					LineItemVO l1 = new LineItemVO(id, Integer
							.parseInt(quantity), Double.parseDouble(danjia),

					commence);
					isAdd[1] = false;
					mylist.add(l1);
					b[1].setStyle("-fx-background-image:url('@../delete.jpg')");

				} else {// 删除
					isAdd[0] = true;
					mylist.remove(1);
					b[1].setStyle("-fx-background-image:url('@../add.jpg')");
					// 进行依次上移
					for (int i = 2; i < 6; i++) {
						if (t1[i].getText().equals("")) {
							// 不动
						} else {
							t1[i - 1].setText(t1[i].getText());
						}
						if (t2[i].getText().equals("")) {

						} else {
							t2[i - 1].setText(t2[i].getText());
						}
						if (t3[i].getText().equals("")) {

						} else {
							t3[i - 1].setText(t3[i].getText());
						}
						if (t4[i].getText().equals("")) {

						} else {
							t4[i - 1].setText(t4[i].getText());
						}
						if (t5[i].getText().equals("")) {

						} else {
							t5[i - 1].setText(t5[i - 1].getText());
						}
						if (isAdd[i]) {

						} else {
							isAdd[i - 1] = isAdd[i];
						}
					}

				}
			}

		});

		b[2].setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				// TODO Auto-generated method stub

				if (isAdd[2]) {

					String name = com[2].getPromptText();

					String id = t1[2].getText();

					String xinghao = t2[2].getText();

					String quantity = t3[2].getText();

					String danjia = t4[2].getText();

					String commence = t5[2].getText();

					if (quantity == null || quantity.equals("")) {

						String warning = "the quantity cannot be empty.";

						AlertBox a = new AlertBox();
						a.display(warning, warning);
						return;

					}

					if (danjia == null || danjia.equals("")) {

						String warning = "the price cannnot be empty.";
						AlertBox a = new AlertBox();
						a.display(warning, warning);

						return;

					}

					LineItemVO l1 = new LineItemVO(id, Integer
							.parseInt(quantity), Double.parseDouble(danjia),

					commence);
					isAdd[2] = false;
					mylist.add(l1);
					b[2].setStyle("-fx-background-image:url('@../delete.jpg')");

				} else {// 删除
					isAdd[2] = true;
					mylist.remove(2);
					b[2].setStyle("-fx-background-image:url('@../add.jpg')");
					// 进行依次上移
					for (int i = 3; i < 6; i++) {
						if (t1[i].getText().equals("")) {
							// 不动
						} else {
							t1[i - 1].setText(t1[i].getText());
						}
						if (t2[i].getText().equals("")) {

						} else {
							t2[i - 1].setText(t2[i].getText());
						}
						if (t3[i].getText().equals("")) {

						} else {
							t3[i - 1].setText(t3[i].getText());
						}
						if (t4[i].getText().equals("")) {

						} else {
							t4[i - 1].setText(t4[i].getText());
						}
						if (t5[i].getText().equals("")) {

						} else {
							t5[i - 1].setText(t5[i - 1].getText());
						}
						if (isAdd[i]) {

						} else {
							isAdd[i - 1] = isAdd[i];
						}
					}

				}

			}

		});

		b[3].setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				// TODO Auto-generated method stub

				if (isAdd[3]) {

					String name = com[3].getPromptText();

					String id = t1[3].getText();

					String xinghao = t2[3].getText();

					String quantity = t3[3].getText();

					String danjia = t4[3].getText();

					String commence = t5[3].getText();

					if (quantity == null || quantity.equals("")) {

						String warning = "the quantity cannot be empty.";

						AlertBox a = new AlertBox();
						a.display(warning, warning);
						return;

					}

					if (danjia == null || danjia.equals("")) {

						String warning = "the price cannnot be empty.";

						AlertBox a = new AlertBox();
						a.display(warning, warning);

						return;

					}

					LineItemVO l1 = new LineItemVO(id, Integer
							.parseInt(quantity), Double.parseDouble(danjia),

					commence);
					isAdd[3] = false;
					mylist.add(l1);
					b[3].setStyle("-fx-background-image:url('@../delete.jpg')");

				} else {// 删除
					isAdd[0] = true;
					mylist.remove(3);
					b[3].setStyle("-fx-background-image:url('@../add.jpg')");
					// 进行依次上移
					for (int i = 4; i < 6; i++) {
						if (t1[i].getText().equals("")) {
							// 不动
						} else {
							t1[i - 1].setText(t1[i].getText());
						}
						if (t2[i].getText().equals("")) {

						} else {
							t2[i - 1].setText(t2[i].getText());
						}
						if (t3[i].getText().equals("")) {

						} else {
							t3[i - 1].setText(t3[i].getText());
						}
						if (t4[i].getText().equals("")) {

						} else {
							t4[i - 1].setText(t4[i].getText());
						}
						if (t5[i].getText().equals("")) {

						} else {
							t5[i - 1].setText(t5[i - 1].getText());
						}
						if (isAdd[i]) {

						} else {
							isAdd[i - 1] = isAdd[i];
						}
					}

				}

			}

		});

		b[4].setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				// TODO Auto-generated method stub

				if (isAdd[4]) {

					String name = com[4].getPromptText();

					String id = t1[4].getText();

					String xinghao = t2[4].getText();

					String quantity = t3[4].getText();

					String danjia = t4[4].getText();

					String commence = t5[4].getText();

					if (quantity == null || quantity.equals("")) {

						String warning = "the quantity cannot be empty.";

						AlertBox a = new AlertBox();
						a.display(warning, warning);
						return;

					}

					if (danjia == null || danjia.equals("")) {

						String warning = "the price cannnot be empty.";

						return;

					}

					LineItemVO l1 = new LineItemVO(id, Integer
							.parseInt(quantity), Double.parseDouble(danjia),

					commence);
					isAdd[4] = false;
					mylist.add(l1);
					b[4].setStyle("-fx-background-image:url('@../delete.jpg')");

				} else {// 删除
					isAdd[4] = true;
					mylist.remove(4);
					b[4].setStyle("-fx-background-image:url('@../add.jpg')");
					// 进行依次上移
					for (int i = 1; i < 6; i++) {
						if (t1[i].getText().equals("")) {
							// 不动
						} else {
							t1[i - 1].setText(t1[i].getText());
						}
						if (t2[i].getText().equals("")) {

						} else {
							t2[i - 1].setText(t2[i].getText());
						}
						if (t3[i].getText().equals("")) {

						} else {
							t3[i - 1].setText(t3[i].getText());
						}
						if (t4[i].getText().equals("")) {

						} else {
							t4[i - 1].setText(t4[i].getText());
						}
						if (t5[i].getText().equals("")) {

						} else {
							t5[i - 1].setText(t5[i - 1].getText());
						}
						if (isAdd[i]) {

						} else {
							isAdd[i - 1] = isAdd[i];
						}
					}

				}

			}

		});

		b[5].setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				// TODO Auto-generated method stub

				if (isAdd[5]) {

					String name = com[5].getPromptText();

					String id = t1[5].getText();

					String xinghao = t2[5].getText();

					String quantity = t3[5].getText();

					String danjia = t4[5].getText();

					String commence = t5[5].getText();

					if (quantity == null || quantity.equals("")) {

						String warning = "the quantity cannot be empty.";

						AlertBox a = new AlertBox();
						a.display(warning, warning);
						return;

					}

					if (danjia == null || danjia.equals("")) {

						String warning = "the price cannnot be empty.";

						return;

					}

					LineItemVO l1 = new LineItemVO(id, Integer
							.parseInt(quantity), Double.parseDouble(danjia),

					commence);
					isAdd[5] = false;
					mylist.add(l1);
					b[5].setStyle("-fx-background-image:url('@../delete.jpg')");

				} else {// 删除
					isAdd[5] = true;
					mylist.remove(5);
					b[5].setStyle("-fx-background-image:url('@../add.jpg')");
					// 进行依次上移

				}

			}

		});

		a1.getChildren().addAll(tx1, tx2, tx3, tx4, tx5, tx6);

		s1.setContent(a1);
		// 操作员

		tf2 = new TField();

		tf2.setMinHeight(30);

		tf2.setMaxHeight(30);

		tf2.setPrefWidth(133);

		tf2.setLayoutX(355);

		tf2.setLayoutY(250);

		// 原总金额,依靠填完的计算

		tf3 = new TField();

		tf3.setEditable(false);

		tf3.setLayoutX(45);

		tf3.setLayoutY(707);

		tf3.setPrefWidth(80);

		// 原折让

		tf4 = new TField();

		tf4.setLayoutX(270);

		tf4.setLayoutY(707);

		tf4.setPrefWidth(80);

		// 已用代金券

		cm1 = new ComboBox();

		cm1.setStyle("-fx-border-color:Grey;-fx-border-radius:4;-fx-background-color:null");

		cm1.setMinHeight(30);

		cm1.setMaxHeight(30);

		for (int i = 0; i < member.getVouchers().size(); i++) {
			cm1.getItems().add(member.getVouchers().get(i).getFaceValue());
		}
		cm1.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener() {
					@Override
					public void changed(ObservableValue arg0, Object arg1,
							Object arg2) {
						// TODO Auto-generated method stub
						int index = cm1.getSelectionModel().getSelectedIndex();
						voucher = member.getVouchers().get(index);
						String str = tf3.getText();
						double m = Double.parseDouble(str);
						String str1 = cm1.getValue().toString();
						double m1 = Double.parseDouble(str1);
						double m2 = m - m1;
						String str2 = tf4.getText();
						double m3 = Double.parseDouble(str2);
						m2 = m2 - m3;
						tf5.setText(Double.toString(m2));
					}
				});

		cm1.setLayoutX(533);

		cm1.setLayoutY(707);

		cm1.setPrefWidth(80);

		// 最终退货金额

		tf5 = new TField();

		tf5.setLayoutX(800);

		tf5.setLayoutY(707);

		tf5.setPrefWidth(85);

		tf5.setEditable(false);

		// 时间

		Timefield = new TField();

		Timefield.setMinHeight(30);

		Timefield.setMaxHeight(30);

		Timefield.setPrefWidth(117);

		Timefield.setLayoutX(52);

		Timefield.setLayoutY(260);

		Timefield.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				if (event.getClickCount() == 2) {

					// 获得时间

					Date day = new Date();

					SimpleDateFormat df = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm");

					Timefield.setText(df.format(day));

				}

			}

		});

		tf3.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				if (event.getClickCount() == 2) {

					// 获得总价

					double sum = 0;

					for (LineItemVO item : mylist) {

						sum = sum + item.getPrice() * item.getQuantity();

					}

					tf3.setText(Double.toString(sum));

				}

			}

		});

		a.getChildren().addAll(root, text1, text2, text3, comboBox1, comboBox2,
				s1, tf2, tf3, tf4, tf5, cm1,

				Timefield);

		Scene scene = new Scene(a, 1080, 800);

		primaryStage.setTitle("进货销售人员");

		primaryStage.setScene(scene);

		j = Job;

		n = name;

		pStage = primaryStage;

		SaleBackuiX sal = new SaleBackuiX();
		sales = SaReceipt;
		mylist = sales.getLineItem();
		int size = sales.getLineItem().size();

		for (int i = 0; i < size; i++) {
			String commodityname = "";
			CommodityVO como = new CommodityVO();
			try {
				como = sal.getComByID(mylist.get(i).getId());
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			commodityname = como.getName();
			com[i].setValue(commodityname);
			t1[i].setText(SaReceipt.getLineItem().get(i).getId());
			t2[i].setText(como.getVersion());
			t3[i].setText("" + mylist.get(i).getQuantity());
			t4[i].setText(Double.toString(mylist.get(i).getPrice()));
			t5[i].setText(mylist.get(i).getComment());
		}
		MemberVO membero = new MemberVO();
		try {
			membero = sal.getmemberByID(sales.getMemberID());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		member = membero;
		comboBox1.setValue(membero.getName());

		comboBox2.setValue("一号仓库");

		tf4.setText(Double.toString(SaReceipt.getDiscount()));// 折让

		tf3.setText(Double.toString(SaReceipt.getSumBeforeDiscount()));// 原总价

		tf5.setText(Double.toString(SaReceipt.getSumAfterDiscount()));// 折让后总价

		voucher = SaReceipt.getVoucher();// 代金券

		cm1.setValue(Double.toString(voucher.getFaceValue()));

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
	private void HandleOut(ActionEvent event) {

		pStage.close();

	}

	@FXML
	private void HandleEsc(ActionEvent event) {

		pStage.close();

	}

	@FXML
	private void HandleTI(ActionEvent event) {
		// 现在假设供应商关联了一个memberVO
		String date = Timefield.getText();
		if (date == null || date.equals("")) {
			String warning = "The date cannot be empty.";
			return;
		}

		Date makeDate = strToDate(date);
		if (makeDate == null) {
			String warning = "The date format is not right.";
			return;
		}

		sales.setDate(makeDate);
		sales.setMemberID(member.getId());
		sales.setRepositoryName(comboBox2.getPromptText());
		sales.setOperatorID(id);// 我猜测当前制作人员的id应该是id
		sales.setSalesType(SalesReceiptType.SALES_RETURN);// 设定单据的类型：销售退货

		if (tf4.getText() == null || tf4.getText().equals("")) {
			sales.setDiscount(0);
		} else {
			sales.setDiscount(Double.parseDouble(tf4.getText()));
		}

		for (int i = 0; i < 6; i++) {
			if (t1[i].getText() == null || t1[i].getText().equals("")) {
				break;
			} else {
				if (t3[i].getText() == null || t3[i].getText().equals("")) {
					String warning = "The quantity cannot be empty.";
					AlertBox a = new AlertBox();
					a.display(warning, warning);
					return;
				}
				int quantity = Integer.parseInt(t3[i].getText());
				String price = t4[i].getText();
				if (price == null || price.equals("")) {
					String warning = "Price cannot be empty.";
					AlertBox a = new AlertBox();
					a.display(warning, warning);
					return;
				}
				double thePrice = Double.parseDouble(price);

				LineItemVO item = new LineItemVO(t1[i].getText(), quantity,
						thePrice, t5[i].getText());
				mylist.add(item);
			}
		}

		sales.setLineItem(mylist);
		sales.setVoucher(new VoucherVO());// 将voucher设为空
		sales.setVoucherReceived(new ArrayList<>());// 将voucherList设为空
		sales.setReceivedID("");// 将触发的赠送单的id设为空

		/**
		 * 以上先将界面上所有的属性重新打包，然后根据这个界面是在什么地方使用，为谁服务调用不同的方法
		 */

		boolean isRedCopy = false;
		if (isRedCopy) {// 为红冲并复制服务
			BusinessProcessHelper helper = BusinessProcessHelper.getInstance();
			try {
				sales.setSalesType(SalesReceiptType.SALES_RETURN);
				ResultMessage message = helper.getService()
						.completeModifySales(sales, id);
				if (message == ResultMessage.RED_COPY_MODIFY_TWICE) {
					String warning = "You cannot red copy and modify a receipt twice.";
					return;
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {// 为总经理修改销售单服务
			CheckReceiptHelper helper = CheckReceiptHelper.getInstance();
			try {
				sales.setSalesType(SalesReceiptType.SALES_RETURN);
				ResultMessage message = helper.getService()
						.completeModifySales(sales, id);
				if (message == ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND) {
					String warning = "Unexcepted bug occurs.";
					return;
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private ArrayList<MemberVO> getAllMember() {
		SalesHelper helper = SalesHelper.getInstance();
		try {
			ArrayList<MemberVO> memberList = helper.getService().getNowMembers(
					MemberType.RETAILER);// 这里面已经把欠钱过多的member剔除在名单外了
			return memberList;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ArrayList<>();// 如果网络通信发生问题，返回一个新的ArrayList
	}

	private ArrayList<CommodityVO> getAllCommodity() {
		SalesHelper helper = SalesHelper.getInstance();
		try {
			ArrayList<CommodityVO> commodityList = helper.getService()
					.getCommodities();
			return commodityList;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ArrayList<>();
	}

	private CommodityVO getComByID(String ID) throws RemoteException {

		CommodityHelper helper = CommodityHelper.getInstance();

		return helper.getService().findByID(ID);

	}

	private MemberVO getmemberByID(String ID) throws RemoteException {
		MemberHelper helper = MemberHelper.getInstance();
		return helper.getService().findByID(ID);
	}

	private static Date strToDate(String time) {// 将字符串转化为Date,如果格式不对，返回null
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = date.parse(time, pos);
		return strtodate;
	}

}