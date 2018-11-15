package financelerui;

import java.io.IOException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import mainframeui.AlertBox;
import mainframeui.TField;
import rmi.BankAccountHelper;
import rmi.FinancialReceiptHelper;
import rmi.MemberHelper;
import rmi.OperationLogHelper;
import rmi.UserHelper;
import VO.BankAccountVO;
import VO.FinancialReceiptVO;
import VO.LineItemVO;
import VO.LogVO;
import VO.MemberVO;
import VO.UserVO;
import enums.FinancialReceiptType;
import enums.ReceiptState;
import enums.ReceiptType;
import enums.ResultMessage;

public class Moneyui {// 制定付款单

	static String j;

	static String n;

	static Stage pStage;

	private static String id;

	private static TableView<AccountName> t1;// 显示列表

	private static TableColumn<AccountName, String> t2;// 银行账户

	private static TableColumn<AccountName, String> t3;// 转账金额

	private static TableColumn<AccountName, String> t4;// 备注

	private static TField tf1;// 转账金额

	private static TField tf2;// 备注

	private static ComboBox comboBox2;// 选择客户

	private static ComboBox comboBox3;// 选择账户

	private static TField tf3;// 时间

	private static ArrayList<BankAccountVO> mylist = new ArrayList<>();

	private static ArrayList<MemberVO> memberlist = new ArrayList<>();

	private static BankAccountVO bankaccount;// bankaccount

	private static MemberVO member;// 客户

	private static ArrayList<LineItemVO> lineItems = new ArrayList<>();

	private static ArrayList<AccountName> accountnamelist = new ArrayList<>();// AccountName的list

	public void display(String Job, String name, String ID,

	FinancialReceiptVO f1) {// 通过草稿单来这个界面

		Moneyui m = new Moneyui();

		// 得到一下

		m.display(Job, name, ID);

		// 先得到memberID,然后进行查找

		// 我得到你的单据里面的lineItem

		lineItems = f1.getLineItem();

		for (int i = 0; i < f1.getLineItem().size(); i++) {

			AccountName acc = new AccountName(f1.getLineItem().get(i));

			accountnamelist.add(acc);

		}

		ObservableList<AccountName> data = FXCollections.observableArrayList();

		for (int i = 0; i < accountnamelist.size(); i++) {

			data.add(accountnamelist.get(i));

		}

		t1.setItems(data);

	}

	@SuppressWarnings("unchecked")
	public void display(String Job, String name, String ID) {

		// TODO Auto-generated method stub

		Stage primaryStage = new Stage();

		id = ID;

		Parent root = null;

		try {

			root = FXMLLoader.load(getClass().getResource("Moneyui.fxml"));

		} catch (IOException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

		AnchorPane a = new AnchorPane();

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

		// 得到所有的memberVO

		Moneyui getmon = new Moneyui();

		try {

			memberlist = getmon.getMemberList();

		} catch (RemoteException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

		// ComboBox2

		comboBox2 = new ComboBox();

		comboBox2.setValue("选择客户");

		comboBox2.setLayoutX(413.0);

		comboBox2.setLayoutY(210.0);

		comboBox2.setMinWidth(134.0);

		comboBox2.setMaxWidth(134.0);

		comboBox2.setMinHeight(30);

		comboBox2.setMaxHeight(30);

		comboBox2.getItems().addAll();

		comboBox2

				.setStyle("-fx-border-color:Grey;-fx-border-radius:4;-fx-background-color:null");

		for (int i = 0; i < memberlist.size(); i++) {

			comboBox2.getItems().add(memberlist.get(i).getName());

		}

		comboBox2.getSelectionModel().selectedItemProperty()

		.addListener(new ChangeListener() {

			@Override
			public void changed(ObservableValue arg0, Object arg1,

			Object arg2) {

				// TODO Auto-generated method stub

				int index = comboBox2.getSelectionModel()

				.getSelectedIndex();

				member = memberlist.get(index);

			}

		});

		// ComboBox3

		try {

			mylist = getmon.getBankAccountList();

		} catch (RemoteException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

		comboBox3 = new ComboBox();

		comboBox3.setValue("选择账户");

		comboBox3.setLayoutX(661.0);

		comboBox3.setLayoutY(210.0);

		comboBox3.setMinWidth(134.0);

		comboBox3.setMaxWidth(134.0);

		comboBox3.getItems().addAll();

		comboBox3

				.setStyle("-fx-border-color:Grey;-fx-border-radius:4;-fx-background-color:null");

		for (int i = 0; i < mylist.size(); i++) {

			comboBox3.getItems().add(mylist.get(i).getName());

		}

		comboBox3.getSelectionModel().selectedItemProperty()

		.addListener(new ChangeListener() {

			@Override
			public void changed(ObservableValue arg0, Object arg1,

			Object arg2) {

				// TODO Auto-generated method stub

				int index = comboBox3.getSelectionModel()

				.getSelectedIndex();

				bankaccount = mylist.get(index);

			}

		});

		// 转账金额

		tf1 = new TField();

		tf1.setLayoutX(413);

		tf1.setLayoutY(280);

		tf1.setPrefWidth(134);

		tf1.setMinHeight(30);

		tf1.setMaxHeight(30);

		// 备注

		tf2 = new TField();

		tf2.setLayoutX(661);

		tf2.setLayoutY(280);

		tf2.setMinHeight(30);

		tf2.setMaxHeight(30);

		tf2.setPrefWidth(134);

		// TableView

		t1 = new TableView();

		t1.setLayoutX(21.0);

		t1.setLayoutY(200);

		t1.setMinHeight(229.0);

		t1.setMaxHeight(229.0);

		t1.setMinWidth(223.0);

		t1.setMaxWidth(223.0);

		t2 = new TableColumn("银行账户");

		t2.setPrefWidth(75.0);

		t3 = new TableColumn("转账金额");

		t3.setPrefWidth(75.0);

		t4 = new TableColumn("备注");

		t4.setPrefWidth(75.0);

		t1.getColumns().addAll(t2, t3, t4);

		t1.setStyle("-fx-background-color:null");

		ObservableList<AccountName> data = FXCollections.observableArrayList();

		for (int i = 0; i < accountnamelist.size(); i++) {

			data.add(accountnamelist.get(i));

		}

		// 你不能这样给我显示这个银行账户，因为它必须要与显示的那个类要相关联

		t2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData

		.getValue().GetName()));// 银行账户

		t3.setCellValueFactory(cellData -> new SimpleStringProperty(cellData

		.getValue().GetPrice()));// 转账金额

		t4.setCellValueFactory(cellData -> new SimpleStringProperty(cellData

		.getValue().GetReason()));// 备注

		t1.setItems(data);

		// 时间

		tf3 = new TField();

		tf3.setMinHeight(30);

		tf3.setMaxHeight(30);

		tf3.setPrefWidth(117);

		tf3.setLayoutX(95);

		tf3.setLayoutY(137);

		tf3.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				if (event.getClickCount() == 2) {

					// 获得时间

					Date day = new Date();

					SimpleDateFormat df = new SimpleDateFormat(

					"yyyy-MM-dd HH:mm");

					tf3.setText(df.format(day));

				}

			}

		});

		a.getChildren().addAll(root, text1, text2, comboBox1, comboBox2,

		comboBox3, t1, tf1, tf2, tf3);

		Scene s = new Scene(a, 800, 500);

		primaryStage.setTitle("财务人员");

		primaryStage.setScene(s);

		j = Job;

		n = name;

		pStage = primaryStage;

		primaryStage.show();

	}

	private ArrayList<MemberVO> getMemberList() throws RemoteException {

		MemberHelper helper = MemberHelper.getInstance();

		return helper.getService().getMemberList();

	}

	private ArrayList<BankAccountVO> getBankAccountList()

	throws RemoteException {

		BankAccountHelper helper = BankAccountHelper.getInstance();

		return helper.getService().getBankAccountList();

	}

	public ArrayList<UserVO> getUserList() throws RemoteException {

		UserHelper helper = UserHelper.getInstance();

		ArrayList<UserVO> vo = helper.getService().getAllList();

		return vo;

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
	private void HandleOut(ActionEvent event) {

		pStage.close();

	}

	@FXML
	private void HandleEsc(ActionEvent event) {

		pStage.close();

	}

	// 添加

	@FXML
	private void HandleAdd(ActionEvent event) throws RemoteException {

		LineItemVO vo = new LineItemVO();

		vo.setComment(tf2.getText());

		vo.setId(bankaccount.getId());

		vo.setPrice(Double.parseDouble(tf1.getText()));

		if (vo.getPrice() < 0) {

			// 显示“金额为负，请重新输入”

		}

		else {

			lineItems.add(vo);

		}

		// 进行lineItems和AccountName的转换

		for (int i = 0; i < lineItems.size(); i++) {

			AccountName acc = new AccountName(lineItems.get(i));

			accountnamelist.add(acc);

		}

		// 加完在tableview中显示

		// 需要用ObserValue来添加

		ObservableList<AccountName> data = FXCollections.observableArrayList();
		data.clear();

		for (int i = 0; i < accountnamelist.size(); i++) {

			data.add(accountnamelist.get(i));

		}

		t1.setItems(data);

		t1.setStyle("-fx-background-color:null");

		tf1.setText("");
		tf2.setText("");
		comboBox2.setValue("");
		comboBox3.setValue("");

	}

	@FXML
	private void HandleTI(ActionEvent event) throws RemoteException,
			ParseException {

		FinancialReceiptVO vo = new FinancialReceiptVO();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		vo.setDate(sdf.parse(tf3.getText()));

		vo.setFinancialReceiptType(FinancialReceiptType.BILL);

		vo.setOperatorID(id);

		vo.setState(ReceiptState.SUBMITTED);

		vo.setType(ReceiptType.FINANCIAL_RECEIPT);

		vo.setLineItem(lineItems);

		int index = comboBox2.getSelectionModel().getSelectedIndex();// 要得到选择的member的下下标

		MemberVO member = memberlist.get(index);

		vo.setMemberID(member.getId());

		FinancialReceiptHelper helper = FinancialReceiptHelper.getInstance();

		ResultMessage result = helper.getService().completeAdd(vo);

		if (result == ResultMessage.SUCCESS) {

			OperationLogHelper operationHelper = OperationLogHelper
					.getInstance();

			LogVO log = new LogVO();

			log.setTime(new Date());

			log.setUserID(id);

			String Content = "提交付款单";

			log.setContent(Content);

			ResultMessage logResult = operationHelper.getService().add(log);

			// 显示“付款单提交成功”
			AlertBox a = new AlertBox();
			a.display("提交成功", "提交成功");
			pStage.close();

		}

		else if (result == ResultMessage.FINANCIAL_RECEIPT_SET_UP_FIAL_MAX_ALREADY) {

			// 显示“今天创建单据数目已达上限”
			AlertBox a = new AlertBox();
			a.display("今天创建单据已达上限", "今日创建单据已达上限");
		}

	}

	private void HandleSaveDraft(ActionEvent event) {

	}

}