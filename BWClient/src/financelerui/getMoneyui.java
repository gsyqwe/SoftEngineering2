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

public class getMoneyui {// 制定收款单

	static String j;

	static String n;

	static Stage pStage;

	private static String id;

	private static ComboBox comboBox2;// 选择客户

	private static ComboBox comboBox3;// 选择账户

	private static TableView<AccountName> t1;// 显示的转账列表

	private static TableColumn<AccountName, String> t2;// 银行账户

	private static TableColumn<AccountName, String> t3;// 转账金额

	private static TableColumn<AccountName, String> t4;// 备注

	private static TField tf1; // 转账金额

	private static TField tf2;// 备注

	private static TField tf3;// 时间

	private static ArrayList<MemberVO> memberlist = new ArrayList<>();// 全部member的List

	private static ArrayList<BankAccountVO> mylist = new ArrayList<>();// 全部银行账户的list

	private static BankAccountVO bankaccount;// bankaccount

	private static MemberVO member = new MemberVO();// 客户

	private static ArrayList<LineItemVO> lineItems = new ArrayList<>();

	private static ArrayList<AccountName> accountnamelist = new ArrayList<>();

	private static ArrayList<MemberVO> myMember = new ArrayList<>();

	public void display(String Job, String name, String ID,

	FinancialReceiptVO f1) {

		getMoneyui g = new getMoneyui();

		g.display(Job, name, ID);

		// 先得到memberID,然后进行查找

		String memberID = f1.getMemberID();

		MemberHelper helper = MemberHelper.getInstance();

		BankAccountHelper bankHelper = BankAccountHelper.getInstance();

		try {

			member = helper.getService().findByID(memberID);

		} catch (RemoteException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

		// 显示客户

		comboBox2.setPromptText(member.getName());

		// 然后利用TableView显示

		// 这就是根据id找到的member和每个lineItem的银行账户

		// 然后我要根据你的ID找到那个单据

		int size = f1.getLineItem().size();

		for (int i = 0; i < size; i++) {

			AccountName acc = new AccountName(lineItems.get(i));

			accountnamelist.add(acc);

		}

		// 需要用ObserValue来添加

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

		Parent root = null;

		try {

			root = FXMLLoader.load(getClass().getResource("getMoneyui.fxml"));

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

		comboBox1

				.setStyle("-fx-border-color:Grey;-fx-border-radius:4;-fx-background-color:null");

		comboBox1.setValue("单据类型");

		comboBox1.setLayoutX(55.0);

		comboBox1.setLayoutY(90.0);

		comboBox1.minWidth(150.0);

		comboBox1.maxWidth(150.0);

		comboBox1.setMinHeight(30);

		comboBox1.setMaxHeight(30);

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

		// ComboBox2

		getMoneyui getmon = new getMoneyui();

		try {

			memberlist = getmon.getMemberList();

		} catch (RemoteException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

		comboBox2 = new ComboBox();

		comboBox2.setValue("选择客户");

		comboBox2

				.setStyle("-fx-border-color:Grey;-fx-border-radius:4;-fx-background-color:null");

		comboBox2.setLayoutX(413.0);

		comboBox2.setLayoutY(210.0);

		comboBox2.setMinWidth(134.0);

		comboBox2.setMaxWidth(134.0);

		comboBox2.setMinHeight(30);

		comboBox2.setMaxHeight(30);

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

		comboBox3

				.setStyle("-fx-border-color:Grey;-fx-border-radius:4;-fx-background-color:null");

		comboBox3.setLayoutX(661.0);

		comboBox3.setLayoutY(210.0);

		comboBox3.setMinWidth(134.0);

		comboBox3.setMaxWidth(134.0);

		comboBox3.setMinHeight(30);

		comboBox3.setMaxHeight(30);

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

		// TableView

		t1 = new TableView();

		t1.setLayoutX(21.0);

		t1.setLayoutY(200);

		t1.setMinHeight(209.0);

		t1.setMaxHeight(209.0);

		t1.setMinWidth(223.0);

		t1.setMaxWidth(223.0);

		t2 = new TableColumn("银行账户");

		t2.setPrefWidth(75.0);

		t3 = new TableColumn("转账金额");

		t3.setPrefWidth(75.0);

		t4 = new TableColumn("备注");

		t4.setPrefWidth(75.0);

		// 进行显示

		// 需要用ObserValue来添加

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

		t1.getColumns().addAll(t2, t3, t4);

		t1.setStyle("-fx-background-color:null");

		// 转账金额

		tf1 = new TField();

		tf1.setMinHeight(30);

		tf1.setMaxHeight(30);

		tf1.setPrefWidth(134);

		tf1.setLayoutX(413);

		tf1.setLayoutY(290);

		// 备注

		tf2 = new TField();

		tf2.setMinHeight(30);

		tf2.setMaxHeight(30);

		tf2.setPrefWidth(134);

		tf2.setLayoutX(661);

		tf2.setLayoutY(290);

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

		id = ID;

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

		vo.setFinancialReceiptType(FinancialReceiptType.DEBIT_NOTE);

		vo.setOperatorID(id);

		vo.setState(ReceiptState.SUBMITTED);

		vo.setType(ReceiptType.FINANCIAL_RECEIPT);

		vo.setLineItem(lineItems);

		int index = -1;// 要得到选择的member的下下标

		MemberVO member = myMember.get(index);

		vo.setMemberID(member.getId());

		FinancialReceiptHelper helper = FinancialReceiptHelper.getInstance();

		ResultMessage result = helper.getService().completeAdd(vo);

		if (result == ResultMessage.SUCCESS) {

			OperationLogHelper operationHelper = OperationLogHelper

			.getInstance();

			LogVO log = new LogVO();

			log.setTime(new Date());

			log.setUserID(id);

			String Content = "提交收款单";

			log.setContent(Content);

			ResultMessage logResult = operationHelper.getService().add(log);

			// 显示“收款单提交成功”
			AlertBox a = new AlertBox();
			a.display("收款成功", "收款成功");

		}

		else if (result == ResultMessage.FINANCIAL_RECEIPT_SET_UP_FIAL_MAX_ALREADY) {

			// 显示“今天创建单据数目已达上限”
			AlertBox a = new AlertBox();
			a.display("今日创建数目已达上限", "今日创建数目已达上限");

		}

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
	private void HandleSaveDraft(ActionEvent event) {

	}

}