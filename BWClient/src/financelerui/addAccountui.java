package financelerui;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import mainframeui.AlertBox;
import mainframeui.TField;
import rmi.BankAccountHelper;
import rmi.OperationLogHelper;
import VO.BankAccountVO;
import VO.LogVO;
import enums.ResultMessage;

public class addAccountui {// 新增银行账户

	private static String j;

	private static String n;

	private static String id;

	private static Stage pStage;

	private static TField TxIn1;// 银行账户名称

	private static TField TxIn2;// 银行卡号

	private static TField TxIn3;// 密码

	private static TField TxIn4;// 金额

	private static TableView<BankAccountVO> t1;// 列表显示我已经添加的

	private static TableColumn<BankAccountVO, String> t2;// 账户编号

	private static TableColumn<BankAccountVO, String> t3;// 账户名称

	private static TableColumn<BankAccountVO, String> t4;// 卡号

	private static TableColumn<BankAccountVO, String> t5;// 金额

	private static ArrayList<BankAccountVO> bankaccountlist = new ArrayList<>();// 银行账户的list

	public void display(String Job, String name, String ID) {

		// TODO Auto-generated method stub

		AnchorPane a = new AnchorPane();

		id = ID;

		Stage primaryStage = new Stage();

		Parent root = null;

		try {

			root = FXMLLoader.load(getClass().getResource("addAccountui.fxml"));

		} catch (IOException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

		a.getChildren().addAll(root);

		t1 = new TableView();

		t1.setLayoutX(41.0);

		t1.setLayoutY(205.0);

		t1.setMinHeight(248.0);

		t1.setMaxHeight(248.0);

		t1.prefWidth(397.0);

		t2 = new TableColumn("账户编号");

		t2.setPrefWidth(105.0);

		t3 = new TableColumn("账户名称");

		t3.setPrefWidth(103.0);

		t4 = new TableColumn("银行卡号");

		t4.setPrefWidth(95.0);

		t5 = new TableColumn("金额");

		t5.setPrefWidth(93.0);

		// 需要用ObserValue来添加

		ObservableList<BankAccountVO> data = FXCollections
				.observableArrayList();

		for (int i = 0; i < bankaccountlist.size(); i++) {

			data.add(bankaccountlist.get(i));

		}

		t2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData

		.getValue().getId()));// 编号

		t3.setCellValueFactory(cellData -> new SimpleStringProperty(cellData

		.getValue().getName()));// 名称

		t4.setCellValueFactory(cellData -> new SimpleStringProperty(cellData

		.getValue().getCardNumber()));// 卡号

		t5.setCellValueFactory(cellData -> new SimpleStringProperty(Double
				.toString(cellData.getValue().getAmount())));// 金额

		t1.getColumns().addAll(t2, t3, t4, t5);

		t1.setStyle("-fx-background-color:null");

		t1.setItems(data);

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

		// 银行账户名称

		TxIn1 = new TField();

		TxIn1.setLayoutX(589);

		TxIn1.setLayoutY(210);

		TxIn1.setPrefWidth(198);

		// 银行卡号

		TxIn2 = new TField();

		TxIn2.setLayoutX(589);

		TxIn2.setLayoutY(259);

		TxIn2.setPrefWidth(198);

		// 密码

		TxIn3 = new TField();

		TxIn3.setLayoutX(589);

		TxIn3.setLayoutY(306);

		TxIn3.setPrefWidth(198);

		// 金额

		TxIn4 = new TField();

		TxIn4.setLayoutX(589);

		TxIn4.setLayoutY(360);

		TxIn4.setPrefWidth(198);

		a.getChildren().addAll(t1, text1, text2, TxIn1, TxIn2, TxIn3, TxIn4);

		Scene s = new Scene(a, 800, 500);

		primaryStage.setTitle("财务人员");

		primaryStage.setScene(s);

		j = Job;

		n = name;

		pStage = primaryStage;

		primaryStage.show();

	}

	@FXML
	private void HandleHelp(ActionEvent event) { // 帮助按钮

		HelpF a = new HelpF();

		a.display(j, n, id);

	}

	@FXML
	private void HandleOut(ActionEvent event) { // 退出按钮

		pStage.close();

	}

	@FXML
	private void HandleEsc(ActionEvent event) { // 退出按钮

		pStage.close();

	}

	@FXML
	private void HandleAdd(ActionEvent event) throws RemoteException {// 增加账户

		String namefield;// 名称

		namefield = TxIn1.getText();

		String t2;// 卡号

		t2 = TxIn2.getText();

		String t3;// 密码

		t3 = TxIn3.getText();

		String t4;// 余额

		t4 = TxIn4.getText();

		BankAccountVO vo = new BankAccountVO();

		vo.setAmount(Double.parseDouble(t4));

		vo.setCardNumber(t2);

		vo.setDate(new Date());

		vo.setId(null);

		vo.setName(namefield);

		vo.setPassword(t3);

		if (Double.parseDouble(t4) < 0) {

			// 显示“银行卡余额不能为负”

			return;

		}

		BankAccountHelper helper = BankAccountHelper.getInstance();

		ResultMessage result = helper.getService().addBankAccount(vo);

		if (result == ResultMessage.BANK_ACCOUNT_ADD_FAIL_CARD_NUMBER_REPEATED) {

			// 显示“该银行卡已创建账户，请勿重复创建”
			AlertBox alert=new AlertBox();
			alert.display("该银行卡已创建账户，请勿重复创建","该银行卡已创建账户，请勿重复创建");

		} else if (result == ResultMessage.BANK_ACCOUNT_ADD_FAIL_NAME_REPEATED) {

			// 显示“账户名称已存在，请重新输入”
			AlertBox alert=new AlertBox();
			alert.display("该账户名称已存在，请重新输入","该账户名称已存在，请重新输入");
		} else if (result == ResultMessage.BANK_ACCOUNT_ADD_FAIL_ILLEGAL_CARD_NUMBER) {

			// 显示“银行卡号非法，请重新输入”
			AlertBox alert=new AlertBox();
			alert.display("银行卡号非法，请重新输入","银行卡号非法，请重新输入");
		} else if (result == ResultMessage.BANK_ACCOUNT_ADD_FAIL_ILLEGAL_PASSWORD) {

			// 显示“账户密码非法，请重新输入”
			AlertBox alert=new AlertBox();
			alert.display("账户密码非法，请重新输入","账户密码非法，请重新输入");
		} else if (result == ResultMessage.BANK_ACCOUNT_ADD_FAIL_NEGATIVE_AMOUNT) {

			// 显示“账户余额为负，请重新输入”
			AlertBox alert=new AlertBox();
			alert.display("账户余额为负，请重新输入","账户余额为负，请重新输入");
		} else if (result == ResultMessage.BANK_ACCOUNT_ADD_SUCCESS) {
			

			OperationLogHelper operationHelper = OperationLogHelper

			.getInstance();

			LogVO log = new LogVO();

			log.setTime(new Date());

			log.setUserID(id);

			String Content = "新建账户：" + vo.getName();

			log.setContent(Content);

			ResultMessage logResult = operationHelper.getService().add(log);

			// 显示“新建账户成功”

			bankaccountlist.add(vo);

			// 显示添加的银行账户

			// 需要用ObserValue来添加

			ObservableList<BankAccountVO> data = FXCollections

			.observableArrayList();

			for (int i = 0; i < bankaccountlist.size(); i++) {

				data.add(bankaccountlist.get(i));

			}

			t1.setItems(data);
			AlertBox alert=new AlertBox();
			alert.display("添加成功","添加成功");

		}

	}

	@FXML
	private void HandleCancle(ActionEvent event) {// 取消

		pStage.close();

	}

}