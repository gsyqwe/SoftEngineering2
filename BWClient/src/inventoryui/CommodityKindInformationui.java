package inventoryui;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import mainframeui.AlertBox;
import rmi.CategoryHelper;
import rmi.CommodityHelper;
import VO.CategoryVO;
import VO.CommodityVO;
import enums.CategoryType;
import enums.ResultMessage;

public class CommodityKindInformationui {
	private static String j;
	private static String n;
	private static Stage pStage;
	private static String id;
	// 利用一个ArrayList来保存商品分类信息，指示父节点之类
	private ArrayList<String> list = new ArrayList();// 保存当前的商品分类
	private static TreeView<String> t;// 显示商品分类的名称
	private static ArrayList<String> namelist = new ArrayList<>();
	private static ArrayList<String> idlist = new ArrayList<>();

	public void display(String Job, String name, String ID) {// 商品分类信息
		// TODO Auto-generated method stub
		Stage primaryStage = new Stage();
		list.clear();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource(
					"CommodityKindInformationui.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 显示登录人的信息
		TextField text1 = new TextField();
		text1.setStyle("-fx-background-color:null");
		text1.setText("欢迎您:" + name);
		text1.setLayoutX(900.0);
		text1.setLayoutY(40.0);
		text1.setMinHeight(36.0);
		text1.setMaxHeight(36.0);
		text1.setFont(Font.font("Verdana", 18));
		text1.prefWidth(185.0);
		text1.setEditable(false);

		TextField text2 = new TextField();
		text2.setStyle("-fx-background-color:null");
		text2.setText("当前职务:" + Job);
		text2.setLayoutX(900.0);
		text2.setLayoutY(68.0);
		text2.setMinHeight(30.0);
		text2.setMaxHeight(30.0);
		text2.setFont(Font.font("Verdana", 18));
		text2.prefWidth(185.0);
		text2.setEditable(false);

		// 利用TreeView来显示
		TreeItem<String> RootNode = new TreeItem<>("商品分类名称");
		t = new TreeView(RootNode);
		// 我加一份数据进

		t.setPrefWidth(256);
		t.setMinHeight(411);
		t.setMaxHeight(411);
		t.setLayoutX(102);
		t.setLayoutY(278);
		// 利用一个ScrollPane来显示一些商品分类的信息
		ScrollPane s1 = new ScrollPane();
		s1.setPrefWidth(611);
		s1.setMinHeight(411);
		s1.setMaxHeight(411);
		s1.setLayoutX(358);
		s1.setLayoutY(278);
		AnchorPane a1 = new AnchorPane();
		a1.setPrefWidth(591);
		a1.setMinHeight(411);
		a1.setMaxHeight(411);
		ImageView i1 = new ImageView(new Image("/backgroud.png", true));
		i1.setFitWidth(591);
		i1.setFitHeight(411);
		a1.getChildren().add(i1);
		s1.setContent(a1);

		// 商品分类的信息
		Label l = new Label(" 名       称");
		l.setLayoutX(14);
		l.setLayoutY(30);
		TField namefield = new TField();// 商品分类名称
		namefield.setMinHeight(30);
		namefield.setMaxHeight(30);
		namefield.setPrefWidth(152);
		namefield.setLayoutX(108);
		namefield.setLayoutY(25);
		Label l1 = new Label("编       号");
		l1.setLayoutX(304);
		l1.setLayoutY(30);
		TField idfield = new TField();// 编号
		idfield.setMinHeight(30);
		idfield.setMaxHeight(30);
		idfield.setPrefWidth(152);
		idfield.setLayoutX(377);
		idfield.setLayoutY(25);
		Button b = new Button("确认修改");
		b.setLayoutX(473);
		b.setLayoutY(357);
		a1.getChildren().addAll(l, l1, idfield, namefield, b);
		// 添加商品的信息
		Label l2 = new Label("库存数量");
		l2.setLayoutX(20);
		l2.setLayoutY(89);
		TField numberfield = new TField();
		numberfield.setPrefWidth(152);
		numberfield.setMinHeight(30);
		numberfield.setMaxHeight(30);
		numberfield.setLayoutX(108);
		numberfield.setLayoutY(84);
		Label l3 = new Label("警戒数量");
		l3.setLayoutX(304);
		l3.setLayoutY(89);
		TField alarmfield = new TField();
		alarmfield.setPrefWidth(152);
		alarmfield.setMinHeight(30);
		alarmfield.setMaxHeight(30);
		alarmfield.setLayoutX(377);
		alarmfield.setLayoutY(84);
		Label l4 = new Label("型       号");
		l4.setLayoutX(20);
		l4.setLayoutY(147);
		TField versionfield = new TField();
		versionfield.setMinHeight(30);
		versionfield.setMaxHeight(30);
		versionfield.setPrefWidth(152);
		versionfield.setLayoutX(108);
		versionfield.setLayoutY(142);
		Label l5 = new Label("进       价");
		l5.setLayoutX(304);
		l5.setLayoutY(147);
		TField moneyfield = new TField();
		moneyfield.setMinHeight(30);
		moneyfield.setMaxHeight(30);
		moneyfield.setPrefWidth(152);
		moneyfield.setLayoutX(377);
		moneyfield.setLayoutY(142);
		Label l6 = new Label("售       价");
		l6.setLayoutX(20);
		l6.setLayoutY(204);
		TField pricefield = new TField();
		pricefield.setMinHeight(30);
		pricefield.setMaxHeight(30);
		pricefield.setPrefWidth(152);
		pricefield.setLayoutX(108);
		pricefield.setLayoutY(199);
		Label l7 = new Label("最近进价");
		l7.setLayoutX(304);
		l7.setLayoutY(204);
		TField Recentpricefield = new TField();
		Recentpricefield.setMinHeight(30);
		Recentpricefield.setMaxHeight(30);
		Recentpricefield.setPrefWidth(152);
		Recentpricefield.setLayoutX(377);
		Recentpricefield.setLayoutY(199);
		Label l8 = new Label("最近售价");
		l8.setLayoutX(20);
		l8.setLayoutY(266);
		TField RecentSalefield = new TField();
		RecentSalefield.setMinHeight(30);
		RecentSalefield.setMaxHeight(30);
		RecentSalefield.setPrefWidth(152);
		RecentSalefield.setLayoutX(108);
		RecentSalefield.setLayoutY(261);
		a1.getChildren().addAll(l2, l3, l4, l5, l6, l7, l8, RecentSalefield,
				Recentpricefield, pricefield, moneyfield, versionfield,
				alarmfield, numberfield);
		l2.setVisible(false);
		l3.setVisible(false);
		l4.setVisible(false);
		l5.setVisible(false);
		l6.setVisible(false);
		l7.setVisible(false);
		l8.setVisible(false);
		RecentSalefield.setVisible(false);
		Recentpricefield.setVisible(false);
		pricefield.setVisible(false);
		moneyfield.setVisible(false);
		versionfield.setVisible(false);
		alarmfield.setVisible(false);
		numberfield.setVisible(false);
		// 然后给t设置click方法，
		t.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// 将所有的信息全部显示在右边的ScrollPane里面
				// 我要先判断index是商品分类还是商品
				String index = t.getSelectionModel().getSelectedItem()
						.getValue().toString();

				String id1;
				boolean truth = false;// false是商品，true是商品分类
				if (index.equals("商品分类名称")) {
					index = "商品分类名称";
					if (index.equals("大灯泡")) {
						truth = true;
					}

				} else {
					for (int i = 0; i < namelist.size(); i++) {
						if (namelist.get(i).equals(index)) {
							id1 = idlist.get(i);
							String nm = id1.substring(0, 3);
							if (nm.equals("CAT")) {
								truth = true;
							} else {
								truth = false;
							}
						}
					}
				}

				if (index.equals("商品分类名称") || truth == true) {// 商品分类

					// 这边是商品分类的
					// 显示商品分类信息
					l2.setVisible(false);
					l3.setVisible(false);
					l4.setVisible(false);
					l5.setVisible(false);
					l6.setVisible(false);
					l7.setVisible(false);
					l8.setVisible(false);
					RecentSalefield.setVisible(false);
					Recentpricefield.setVisible(false);
					pricefield.setVisible(false);
					moneyfield.setVisible(false);
					versionfield.setVisible(false);
					alarmfield.setVisible(false);
					numberfield.setVisible(false);
					CategoryVO vo = new CategoryVO();

					try {
						// CommodityKindInformationui com = new
						// CommodityKindInformationui();
						vo = findByNameCategory(index);
						ArrayList<String> sub = vo.getSubCateOrCom();

					} catch (RemoteException e) {

						// TODO Auto-generated catch block

						e.printStackTrace();

					}
					namefield.setText(vo.getName());
					idfield.setText(vo.getId());
					// 双击显示其子分类的信息
					if (event.getClickCount() == 2) {
						// TODO Auto-generated method stub
						// 得到Index

						if (vo.getType() == CategoryType.LEAF) {

							ArrayList<String> com = vo.getSubCateOrCom();// 商品名称/ID

							// 叶节点，下面没有分类只有商品

							// string的格式为商品名字/ID
							for (int i = 0; i < com.size(); i++) {
								String s[] = com.get(i).split("/");
								namelist.add(s[0]);
								idlist.add(s[1]);
								TreeItem<String> Node = t.getSelectionModel()
										.getSelectedItem();
								TreeItem<String> Node1 = new TreeItem(s[0]);
								Node.getChildren().add(Node1);
							}

						}

						else {

							ArrayList<String> subCate = vo.getSubCateOrCom();

							// 分支节点，下面有子分类
							// string的格式为分类名字/ID
							for (int i = 0; i < subCate.size(); i++) {
								String s[] = subCate.get(i).split("/");
								namelist.add(s[0]);
								idlist.add(s[1]);
								TreeItem<String> Node = t.getSelectionModel()
										.getSelectedItem();
								TreeItem<String> Node1 = new TreeItem(s[0]);
								Node.getChildren().add(Node1);
							}
						}

					}
				} else {
					// 这里对商品处理
					// 就在右边显示商品的信息
					l2.setVisible(true);
					l3.setVisible(true);
					l4.setVisible(true);
					l5.setVisible(true);
					l6.setVisible(true);
					l7.setVisible(true);
					l8.setVisible(true);
					RecentSalefield.setVisible(true);
					Recentpricefield.setVisible(true);
					pricefield.setVisible(true);
					moneyfield.setVisible(true);
					versionfield.setVisible(true);
					alarmfield.setVisible(true);
					numberfield.setVisible(true);
					CommodityVO com = new CommodityVO();
					CommodityKindInformationui c = new CommodityKindInformationui();
					try {
						com = c.findByNameCommodity(index);
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					namefield.setText(com.getName());
					idfield.setText(com.getId());
					RecentSalefield.setText(Double.toString(com.getRecentBid()));
					Recentpricefield.setText(Double.toString(com
							.getRecentRetailPrice()));
					pricefield.setText(Double.toString(com.getRetailPrice()));
					moneyfield.setText(Double.toString(com.getBid()));
					versionfield.setText(com.getVersion());
					alarmfield.setText("" + com.getAlertQuantity());
					numberfield.setText("" + com.getQuantity());

				}
			}
		});
		ContextMenu cme = new ContextMenu();
		// 增加右键菜单的一些项
		MenuItem m = new MenuItem("增加分类");
		cme.getItems().add(m);
		MenuItem m1 = new MenuItem("删除分类");
		cme.getItems().add(m1);
		MenuItem m3 = new MenuItem("增加商品");
		cme.getItems().add(m3);
		MenuItem m4 = new MenuItem("删除商品");
		cme.getItems().add(m4);
		t.setContextMenu(cme);
		// 给t增加键盘的删除事件
		// 进行增加
		// 现在增加分类只要你输入分类的名称，然后确定它是叶分类还是非叶分类，然后就将这个分类增加到那个你所选的那个分类的子分类中
		m.setOnAction(new EventHandler<ActionEvent>() {// 增加分类
			public void handle(ActionEvent e) {
				// 得到Category
				namelist.add("大灯泡");
				idlist.add("CAT-00008");
				CategoryVO category = new CategoryVO();
				// 得到被选择的商品分类的String
				String index;
				index = t.getSelectionModel().getSelectedItem().getValue()
						.toString();
				TreeItem t1 = t.getSelectionModel().getSelectedItem();
				// 写你的增加分类事
				CommodityKindInformationui co = new CommodityKindInformationui();
				addCommodityKind a = new addCommodityKind();
				try {
					category = co.findByNameCategory(index);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				String indd = "";
				for (int i = 0; i < namelist.size(); i++) {
					if (index.equals(namelist.get(i))) {
						indd = idlist.get(i);
					}
				}
				a.display(id, t1, category.getName() + "/" + category.getId());

			}
		});
		// 增加这个事件
		m1.setOnAction(new EventHandler<ActionEvent>() {// 删除分类
			public void handle(ActionEvent e) {
				// 得到被选择的商品分类的String
				String index;
				index = t.getSelectionModel().getSelectedItem().getValue()
						.toString();
				// 写你的删除事件
				CategoryVO category = new CategoryVO();
				CommodityKindInformationui com = new CommodityKindInformationui();
				try {
					category = com.findByNameCategory(index);
				} catch (RemoteException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				CategoryHelper helper = CategoryHelper.getInstance();

				try {

					ResultMessage result = helper.getService().deleteCategory(
							ID);

					if (result == ResultMessage.CATEGORY_DELETE_FAIL) {

						// 显示“删除失败”
						AlertBox a = new AlertBox();
						a.display("删除失败", "删除失败");

					} else if (result == ResultMessage.CATEGORY_DELETE_SUCCESS) {

						// 显示“删除成功”
						AlertBox a = new AlertBox();
						a.display("删除成功", "删除成功");

					}

				} catch (RemoteException e1) {

					// TODO Auto-generated catch block

					e1.printStackTrace();

				}
			}
		});
		// 利用商品分类进行增加商品
		m3.setOnAction(new EventHandler<ActionEvent>() {// 增加商品
			public void handle(ActionEvent e) {
				String index;
				index = t.getSelectionModel().getSelectedItem().getValue()
						.toString();
				TreeItem t1 = t.getSelectionModel().getSelectedItem();
				CategoryVO category = new CategoryVO();
				// 跳到新建分类的界面，把nameAndId传过去
				CommodityKindInformationui c = new CommodityKindInformationui();
				try {
					category = c.findByNameCategory(index);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				addCommodity add = new addCommodity();
				add.display(j, n, id, index, t1);
			}
		});
		m4.setOnAction(new EventHandler<ActionEvent>() {// 删除商品
			public void handle(ActionEvent e) {
				String index;
				index = t.getSelectionModel().getSelectedItem().getValue();
				CommodityVO commodity = new CommodityVO();
				// 直接删除一个商品
				CommodityKindInformationui com = new CommodityKindInformationui();
				try {
					commodity = com.findByNameCommodity(index);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		AnchorPane a = new AnchorPane();
		a.getChildren().addAll(root, t, text1, text2, s1);

		Scene s = new Scene(a, 1080, 800);
		primaryStage.setTitle("库存管理人员");
		j = Job;
		n = name;
		pStage = primaryStage;
		primaryStage.setScene(s);
		primaryStage.show();
	}

	private ResultMessage modifyCategory(CategoryVO vo) throws RemoteException {
		CategoryHelper helper = CategoryHelper.getInstance();
		return helper.getService().modifyCategory(vo);
	}

	// 需要修改后的商品分类vo，如果返回值为ResultMessage.SUCCESS就成功了，其他就是错了

	private ResultMessage modifyCommodity(CommodityVO vo)
			throws RemoteException {
		CommodityHelper helper = CommodityHelper.getInstance();
		return helper.getService().modifyCommodity(vo);
	}

	private CategoryVO findByNameCategory(String name) throws RemoteException {

		CategoryHelper helper = CategoryHelper.getInstance();

		return helper.getService().findByName(name);

	}

	private CommodityVO findByNameCommodity(String name) throws RemoteException {

		CommodityHelper helper = CommodityHelper.getInstance();

		return helper.getService().findByName(name);

	}

	@FXML
	private void HandleHelp(ActionEvent event) {

	}

	@FXML
	private void HandleOut(ActionEvent event) {

		pStage.close();

	}

	/*
	 * 
	 * @FXML//商品分类查看 private void HandleSure(ActionEvent event){
	 * 
	 * 
	 * 
	 * }
	 */

	@FXML
	private void HandleReturn(ActionEvent event) {// 利用size来返回

	}

	@FXML
	private void HandleCancle(ActionEvent event) {

		pStage.close();

	}

}