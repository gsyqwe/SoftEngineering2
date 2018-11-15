package financelerui;

import java.io.IOException;

import mainframeui.TField;

import java.rmi.RemoteException;

import java.util.ArrayList;

import java.util.Date;

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

import javafx.scene.input.KeyCode;

import javafx.scene.input.KeyEvent;

import javafx.scene.layout.AnchorPane;

import javafx.scene.text.Font;

import javafx.stage.Stage;

import mainframeui.TField;

import rmi.BankAccountHelper;

import rmi.OperationLogHelper;

import VO.BankAccountVO;

import VO.LogVO;

import VO.ReceiptVO;

import enums.ResultMessage;

public class getAccountui {// 查看账户

	static String j;

	static String n;

	static Stage pStage;

	private static String id;

	private static ArrayList<BankAccountVO> b1 = new ArrayList<>();// 所有账户的ArrayList

	private static TField tf1;// 搜索

	private static TableView<BankAccountVO> t1;// 显示的列表

	private static TableColumn<BankAccountVO, String> t2;// 账户编号

	private static TableColumn<BankAccountVO, String> t3; // 账户名称

	private static TableColumn<BankAccountVO, String> t4;// 银行卡号

	private static TableColumn<BankAccountVO, String> t5;// 金额


	@SuppressWarnings("unchecked")
	public void display(String Job, String name, String ID) {

		// TODO Auto-generated method stub

		Stage primaryStage = new Stage();

		id = ID;

		// 得到一下银行账户

		getAccountui get = new getAccountui();

		try {

			b1 = get.getBankAccountList();

		} catch (RemoteException e2) {

			// TODO Auto-generated catch block

			e2.printStackTrace();

		}

		Parent root = null;

		try {

			root = FXMLLoader.load(getClass().getResource("getAccountui.fxml"));

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

		TextField text2 = new TextField();

		text2.setStyle("-fx-background-color:null");

		text2.setText("当前职务:" + Job);

		text2.setLayoutX(913.0);

		text2.setLayoutY(68.0);

		text2.setMinHeight(30.0);

		text2.setMaxHeight(30.0);

		text2.setFont(Font.font("Verdana", 18));

		text2.prefWidth(185.0);

		text2.setEditable(false);

		t1 = new TableView();

		t1.setLayoutX(232.0);

		t1.setLayoutY(250.0);

		t1.setMinHeight(460.0);

		t1.setMaxHeight(460.0);

		t1.minWidth(654.0);

		t1.maxWidth(654.0);

		t2 = new TableColumn("账户编号");

		t2.setPrefWidth(177.0);

		t3 = new TableColumn("账户名称");

		t3.setPrefWidth(187.0);

		t4 = new TableColumn("银行卡号");

		t4.setPrefWidth(138.0);

		t5 = new TableColumn("金额");

		t5.setPrefWidth(151.0);

		// 显示

		ObservableList<BankAccountVO> data = FXCollections
				.observableArrayList();

		for (int i = 0; i < b1.size(); i++) {

			data.add(b1.get(i));

		}

		t2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData

		.getValue().getId()));// 编号

		t3.setCellValueFactory(cellData -> new SimpleStringProperty(""
				+ cellData

				.getValue().getName()));// 名称

		t4.setCellValueFactory(cellData -> new SimpleStringProperty(cellData

		.getValue().getCardNumber()));// 卡号

		t5.setCellValueFactory(cellData -> new SimpleStringProperty(Double
				.toString(cellData.getValue().getAmount())));// 金额

		t1.setItems(data);

		t1.getColumns().addAll(t2, t3, t4, t5);

		t1.setStyle("-fx-background-color:null");

		// 增加右键按钮

		ContextMenu cme = new ContextMenu();

		// 增加右键菜单的一些项

		MenuItem m1 = new MenuItem("删除");

		cme.getItems().add(m1);

		MenuItem m2 = new MenuItem("修改");

		cme.getItems().add(m2);

		t1.setContextMenu(cme);

		// 增加这个事件

		m1.setOnAction(new EventHandler<ActionEvent>() {// 删除

			public void handle(ActionEvent e) {

				// 得到被选择的index,得到编号
				int index;
				index = t1.getSelectionModel().getSelectedIndex();
				System.out.println(index);
				BankAccountVO u = b1.get(index);// 得到对应Index的UserVO
				deleteAccount delete=new deleteAccount();//需要输入密码
				delete.display(id,u,t1,b1);

			}

		});

		m2.setOnAction(new EventHandler<ActionEvent>() {// 修改

			public void handle(ActionEvent e) {

				// 得到被选择的index,得到编号

				int index;

				index = t1.getSelectionModel().getSelectedIndex();

				BankAccountVO vo = b1.get(index);
				// 跳到修改的界面,把vo传过去
				deviseAccountui d=new deviseAccountui();
				d.display(j,n,id,vo,t1,b1);

			}

		});

		// 搜索

		tf1 = new TField();

		tf1.setLayoutX(622);

		tf1.setLayoutY(167);

		tf1.setMinHeight(30);

		tf1.setMaxHeight(30);

		tf1.setPrefWidth(155);

		tf1.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {

				if (event.getCode() == KeyCode.ENTER) {

					String key = tf1.getText();

					BankAccountHelper helper = BankAccountHelper.getInstance();

					ArrayList<BankAccountVO> vos = new ArrayList<BankAccountVO>();

					try {

						BankAccountVO vo1 = helper.getService().findByID(key);

						BankAccountVO vo2 = helper.getService()

						.findByCardNumber(key);

						BankAccountVO vo3 = helper.getService().findByName(key);

						vos.add(vo1);

						if (vo2 != vo1) {

							vos.add(vo2);

						}

						if (vo3 != vo1 && vo3 != vo2) {

							vos.add(vo3);

						}

					} catch (RemoteException e) {

						// TODO Auto-generated catch block

						e.printStackTrace();

					}

					// 显示vos里面的内容

				}

			}

		});

		a.getChildren().addAll(root, t1, text1, text2, tf1);

		Scene s = new Scene(a, 1080, 800);

		primaryStage.setTitle("财务人员");

		primaryStage.setScene(s);

		j = Job;

		n = name;

		pStage = primaryStage;

		primaryStage.show();

	}


	public ArrayList<BankAccountVO> getBankAccountList() throws RemoteException {

		BankAccountHelper helper = BankAccountHelper.getInstance();

		return helper.getService().getBankAccountList();

	}

	@FXML
	private void HandleHelp(ActionEvent event) {

		HelpF f = new HelpF();

		f.display(j, n, id);

	}

	@FXML
	private void HandleSure(ActionEvent event) {

		pStage.close();

	}

	@FXML
	private void HandleCancle(ActionEvent event) {

		pStage.close();

	}

	@FXML
	private void HandleExport(ActionEvent event) {

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

}