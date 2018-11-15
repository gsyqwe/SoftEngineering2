package financelerui;

import java.io.IOException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import mainframeui.AlertBox;
import mainframeui.TField;
import rmi.BankAccountHelper;
import rmi.BusinessProcessHelper;
import rmi.CheckReceiptHelper;
import rmi.CommodityHelper;
import rmi.UserHelper;
import VO.BankAccountVO;
import VO.CommodityVO;
import VO.FinancialReceiptVO;
import VO.LineItemVO;
import VO.UserVO;
import enums.FinancialReceiptType;
import enums.ReceiptState;
import enums.ReceiptType;
import enums.ResultMessage;

public class costX {// 用来红冲并复制和总经理的编辑单据来用

	private static String j;

	private static String n;

	private static Stage pStage;

	private static TField tx1[] = new TField[6];// 申请原因

	private static TField tx2[] = new TField[6];// 申请金额

	private static ComboBox tx3[] = new ComboBox[6];// 选择银行账户

	private static TField tx4[] = new TField[6];// 输入备注

	private static TField tf1;// 申请人编号

	private static TField tf2;// 申请人职务

	private static TField tf3;// 时间

	private static String id;

	private static ArrayList<BankAccountVO> bankaccountlist;

	@SuppressWarnings("unchecked")
	public void display(String Job, String name, String ID,

	FinancialReceiptVO f1) {// 得到一个这个vo

		// TODO Auto-generated method stub

		Stage primaryStage = new Stage();

		id = ID;

		Parent root = null;

		try {

			root = FXMLLoader.load(getClass().getResource("costX.fxml"));

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

		// ComboBox

		@SuppressWarnings("rawtypes")
		ComboBox comboBox1 = new ComboBox();

		comboBox1.setValue("单据类型");

		comboBox1.setLayoutX(55.0);

		comboBox1.setLayoutY(90.0);

		comboBox1.minWidth(150.0);

		comboBox1.maxWidth(150.0);

		comboBox1.getItems().addAll("现金费用单", "收款单", "付款单");

		comboBox1.getSelectionModel().selectedItemProperty()

		.addListener(new ChangeListener() {

			@Override
			public void changed(ObservableValue arg0, Object arg1,

			Object arg2) {

				// TODO Auto-generated method stub

				if (arg2.toString() == "现金费用单") {

					cost b = new cost();

					b.display(Job, name, id);

				} else if (arg2.toString() == "收款单") {

					getMoneyui b = new getMoneyui();

					b.display(Job, name, id);

				} else if (arg2.toString() == "付款单") {

					Moneyui b = new Moneyui();

					b.display(Job, name, id);

				}

			}

		});

		comboBox1

				.setStyle("-fx-border-color:Grey;-fx-border-radius:4;-fx-background-color:null");

		ScrollPane s1 = new ScrollPane();

		s1.setPrefWidth(527);

		s1.setMinHeight(176);

		s1.setMaxHeight(176);

		s1.setLayoutX(267);

		s1.setLayoutY(200);

		AnchorPane a1 = new AnchorPane();

		a1.setMinHeight(245);

		a1.setMaxHeight(245);

		a1.setPrefWidth(507);

		s1.setContent(a1);

		ImageView i1 = new ImageView(new Image("/backgroud.png", true));

		i1.setFitWidth(507);

		i1.setFitHeight(245);

		a1.getChildren().add(i1);

		// 申请原因

		TField t1 = new TField();

		t1.setText("申请原因");

		t1.setPrefWidth(108);

		t1.setLayoutX(0);

		t1.setLayoutY(0);

		t1.setEditable(false);

		// 输入申请原因

		for (int i = 0; i < 6; i++) {

			tx1[i] = new TField();

			tx1[i].setPrefWidth(108);

			tx1[i].setLayoutX(0);

			tx1[i].setLayoutY(35 * (1 + i));

			a1.getChildren().add(tx1[i]);

		}

		// 申请金额

		TField t2 = new TField();

		t2.setText("申请金额");

		t2.setPrefWidth(108);

		t2.setLayoutX(108);

		t2.setLayoutY(0);

		t2.setEditable(false);

		// 输入申请金额

		for (int i = 0; i < 6; i++) {

			tx2[i] = new TField();

			tx2[i].setPrefWidth(108);

			tx2[i].setLayoutX(108);

			tx2[i].setLayoutY(35 * (1 + i));

			a1.getChildren().add(tx2[i]);

		}

		// 银行账户

		TField t3 = new TField();

		t3.setText("银行账户");

		t3.setPrefWidth(136);

		t3.setLayoutX(216);

		t3.setLayoutY(0);

		t3.setEditable(false);

		// 输入银行账户

		for (int i = 0; i < 6; i++) {

			tx3[i] = new ComboBox();

			// 添加bankAccountVO的名称进去进行选择

			for (int j = 0; j < bankaccountlist.size(); j++) {

				tx3[i].getItems().add(bankaccountlist.get(i).getName());

				tx3[i].getSelectionModel().selectedItemProperty()

				.addListener(new ChangeListener() {

					@Override
					public void changed(ObservableValue arg0, Object arg1,

					Object arg2) {

						// TODO Auto-generated method stub

						String name = arg2.toString();

					}

				});

			}

			tx3[i].setMinHeight(35);

			tx3[i].setEditable(false);

			tx3[i].setMaxHeight(35);

			tx3[i].setPrefWidth(136);

			tx3[i].setLayoutX(216);

			tx3[i].setStyle("-fx-border-color:Grey;-fx-border-radius:4;-fx-background-color:null");

			tx3[i].setLayoutY(35 * (1 + i));

			a1.getChildren().add(tx3[i]);

		}

		// 备注

		TField t4 = new TField();

		t4.setText("备注");

		t4.setEditable(false);

		t4.setPrefWidth(155);

		t4.setLayoutX(352);

		t4.setLayoutY(0);

		for (int i = 0; i < 6; i++) {

			tx4[i] = new TField();

			tx4[i].setPrefWidth(155);

			tx4[i].setLayoutX(352);

			tx4[i].setLayoutY(35 * (i + 1));

			a1.getChildren().add(tx4[i]);

		}

		a1.getChildren().addAll(t1, t2, t3, t4);

		// 申请人的编号

		tf1 = new TField();

		tf1.setMinHeight(30);

		tf1.setMaxHeight(30);

		tf1.setPrefWidth(117);

		tf1.setLayoutX(132);

		tf1.setLayoutY(167);

		tf1.setEditable(false);

		// 申请人职务

		tf2 = new TField();

		tf2.setMinHeight(30);

		tf2.setMaxHeight(30);

		tf2.setPrefWidth(117);

		tf2.setLayoutX(132);

		tf2.setLayoutY(210);

		tf2.setEditable(false);

		// 时间

		tf3 = new TField();

		tf3.setMinHeight(30);

		tf3.setMaxHeight(30);

		tf3.setPrefWidth(117);

		tf3.setLayoutX(132);

		tf3.setLayoutY(250);

		tf3.setEditable(false);

		// 初始化显示的操作

		costX c = new costX();

		int size = f1.getLineItem().size();

		tf1.setText(f1.getMemberID());// 申请人编号

		UserVO u = new UserVO();

		try {

			u = c.getUserByID(f1.getMemberID());

		} catch (RemoteException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

		tf2.setText(u.getJob().toString());// 申请人职务

		tf3.setText("" + f1.getDate());// 申请时间

		ArrayList<LineItemVO> lineItem = f1.getLineItem();

		ArrayList<String> bankAccounts = new ArrayList<String>();

		BankAccountHelper bankHelper = BankAccountHelper.getInstance();

		for (LineItemVO temp : lineItem) {

			try {

				bankAccounts.add(bankHelper.getService().findByID(temp.getId())
						.getName());

			} catch (RemoteException e) {

				// TODO Auto-generated catch block

				e.printStackTrace();

			}

		}

		// 对多显示

		for (int i = 0; i < size; i++) {

			tx1[i].setText(f1.getLineItem().get(i).getComment());// 原因

			tx2[i].setText(Double.toString(f1.getLineItem().get(i).getPrice()));// 金额

			tx3[i].setPromptText(bankAccounts.get(i));// 银行账户的名称

		}

		AnchorPane a = new AnchorPane();

		a.getChildren()

		.addAll(root, text1, text2, comboBox1, s1, tf1, tf2, tf3);

		Scene s = new Scene(a, 800, 500);

		primaryStage.setTitle("财务人员");

		primaryStage.setScene(s);

		j = Job;

		n = name;

		pStage = primaryStage;

		primaryStage.show();

	}

	private UserVO getUserByID(String ID) throws RemoteException {

		UserHelper helper = UserHelper.getInstance();

		return helper.getService().findByID(ID);

	}

	private CommodityVO getComByID(String ID) throws RemoteException {

		CommodityHelper helper = CommodityHelper.getInstance();

		return helper.getService().findByID(ID);

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

	@FXML
	private void HandleTI(ActionEvent event) throws RemoteException,
			ParseException {

		boolean isRedCopy = false;

		// 我要知道用户的选择

		FinancialReceiptVO vo = new FinancialReceiptVO();

		ArrayList<LineItemVO> lineItems = new ArrayList<LineItemVO>();

		// 把用户选择的账户加入到bankAccounts里,按照申请原因和申请金额的顺序（每一个申请都有一个申请账户）

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		vo.setDate(sdf.parse(tf3.getText()));

		vo.setFinancialReceiptType(FinancialReceiptType.CASH_CLAIM);

		vo.setType(ReceiptType.FINANCIAL_RECEIPT);

		vo.setState(ReceiptState.SUBMITTED);

		vo.setOperatorID(id);

		String mistake = "";

		for (int i = 0; i < tx2.length; i++) {

			LineItemVO temp = new LineItemVO();

			temp.setComment(tx1[i].getText());

			temp.setId(tx3[i].getPromptText());

			if (tx2[i].getText() == null || tx2[i].getText().equals("")) {

				String warning = "The price cannot be empty.";

				return;

			}

			temp.setPrice(Double.parseDouble(tx2[i].getText()));

			if (Double.parseDouble(tx2[i].getText()) <= 0) {

				mistake = mistake + "第" + i + "行申请金额非法\n";

			} else {

				lineItems.add(temp);

			}

		}

		if (!mistake.equals("")) {

			// 显示mistake

		}

		else {

			if (isRedCopy) {// 为红冲并复制服务

				BusinessProcessHelper helper = BusinessProcessHelper
						.getInstance();

				try {

					ResultMessage message = helper.getService()
							.completeModifyFinancial(vo, id);

					if (message == ResultMessage.RED_COPY_MODIFY_TWICE) {

						String warning = "You cannot red copy and modify a receipt twice.";

						// 显示“不能重复红冲并修改同一张单据两次”
						AlertBox a=new AlertBox();
						a.display(warning,warning);

						return;

					}

				} catch (RemoteException e) {

					// TODO Auto-generated catch block

					e.printStackTrace();

				}

			}

			else {// 为总经理修改销售单服务

				CheckReceiptHelper helper = CheckReceiptHelper.getInstance();

				try {

					ResultMessage message = helper.getService()
							.completeModifyFinancial(vo, id);

					if (message == ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND) {

						String warning = "Unexcepted bug occurs.";

						// 显示“操作失败”

						return;

					}

				} catch (RemoteException e) {

					// TODO Auto-generated catch block

					e.printStackTrace();

				}

			}

		}

	}

}