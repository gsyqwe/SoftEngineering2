package financelerui;

import java.io.IOException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import VO.BankAccountVO;
import VO.FinancialReceiptVO;
import VO.LineItemVO;
import VO.MemberVO;
import VO.UserVO;
import enums.FinancialReceiptType;
import enums.ReceiptState;
import enums.ReceiptType;
import enums.ResultMessage;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import mainframeui.TField;
import rmi.BankAccountHelper;
import rmi.BusinessProcessHelper;
import rmi.CheckReceiptHelper;
import rmi.MemberHelper;
import rmi.UserHelper;

public class MoneyuiX {// �������ܾ���༭���������,ͨ��Job������

	static String j;

	static String n;

	static Stage pStage;

	private static String id;

	private static TableView<AccountName> t1;// ��ʾ�б�

	private static TableColumn<AccountName, String> t2;// �����˻�

	private static TableColumn<AccountName, String> t3;// ת�˽��

	private static TableColumn<AccountName, String> t4;// ��ע

	private static TField tf1;// ת�˽��

	private static TField tf2;// ��ע

	private static ComboBox comboBox2;// ѡ��ͻ�

	private static ComboBox comboBox3;// ѡ���˻�

	private static TField tf3;// ʱ��

	private static ArrayList<BankAccountVO> mylist = new ArrayList<>();

	private static BankAccountVO bankaccount;// bankaccount

	private static MemberVO member;// �ͻ�

	private static ArrayList<LineItemVO> lineItems = new ArrayList<>();

	private static ArrayList<MemberVO> memberlist = new ArrayList<>();

	private static ArrayList<AccountName> accountnamelist = new ArrayList<>();

	@SuppressWarnings("unchecked")
	public void display(String Job, String name, String ID,

	FinancialReceiptVO f1) {

		// TODO Auto-generated method stub

		Stage primaryStage = new Stage();

		id = ID;

		Parent root = null;

		try {

			root = FXMLLoader.load(getClass().getResource("MoneyuiX.fxml"));

		} catch (IOException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

		AnchorPane a = new AnchorPane();

		TextField text1 = new TextField();

		text1.setStyle("-fx-background-color:null");

		text1.setText("��ӭ��:" + name);

		text1.setLayoutX(613.0);

		text1.setLayoutY(40.0);

		text1.setMinHeight(36.0);

		text1.setMaxHeight(36.0);

		text1.setFont(Font.font("Verdana", 18));

		text1.prefWidth(185.0);

		text1.setEditable(false);

		TextField text2 = new TextField();

		text2.setStyle("-fx-background-color:null");

		text2.setText("��ǰְ��:" + Job);

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

		comboBox1.setValue("��������");

		comboBox1.setLayoutX(55.0);

		comboBox1.setLayoutY(90.0);

		comboBox1.minWidth(150.0);

		comboBox1.maxWidth(150.0);

		comboBox1.getItems().addAll("�ֽ���õ�", "�տ", "���");

		comboBox1.getSelectionModel().selectedItemProperty()

		.addListener(new ChangeListener() {

			@Override
			public void changed(ObservableValue arg0, Object arg1,

			Object arg2) {

				// TODO Auto-generated method stub

				if (arg2.toString() == "�ֽ���õ�") {

					cost b = new cost();

					b.display(Job, name, id);

				} else if (arg2.toString() == "�տ") {

					getMoneyui b = new getMoneyui();

					b.display(Job, name, id);

				} else if (arg2.toString() == "���") {

					Moneyui b = new Moneyui();

					b.display(Job, name, id);

				}

			}

		});

		comboBox1

				.setStyle("-fx-border-color:Grey;-fx-border-radius:4;-fx-background-color:null");

		// �õ����е�memberVO

		MoneyuiX getmon = new MoneyuiX();

		try {

			memberlist = getmon.getMemberList();

		} catch (RemoteException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

		// ComboBox2

		comboBox2 = new ComboBox();

		comboBox2.setValue("ѡ��ͻ�");

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

		comboBox3.setValue("ѡ���˻�");

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

		// ת�˽��

		tf1 = new TField();

		tf1.setLayoutX(413);

		tf1.setLayoutY(280);

		tf1.setPrefWidth(134);

		tf1.setMinHeight(30);

		tf1.setMaxHeight(30);

		// ��ע

		tf2 = new TField();

		tf2.setLayoutX(661);

		tf2.setLayoutY(280);

		tf2.setMinHeight(30);

		tf2.setMaxHeight(30);

		tf2.setPrefWidth(134);

		tf2.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {

				if (event.getCode() == KeyCode.ENTER) {

					// ���

				}

			}

		});

		// TableView

		t1 = new TableView();

		t1.setLayoutX(21.0);

		t1.setLayoutY(200);

		t1.setMinHeight(229.0);

		t1.setMaxHeight(229.0);

		t1.setMinWidth(223.0);

		t1.setMaxWidth(223.0);

		t2 = new TableColumn("�����˻�");

		t2.setPrefWidth(75.0);

		t3 = new TableColumn("ת�˽��");

		t3.setPrefWidth(75.0);

		t4 = new TableColumn("��ע");

		t4.setPrefWidth(75.0);

		t1.getColumns().addAll(t2, t3, t4);

		t1.setStyle("-fx-background-color:null");

		ObservableList<AccountName> data = FXCollections.observableArrayList();

		for (int i = 0; i < accountnamelist.size(); i++) {

			data.add(accountnamelist.get(i));

		}

		// �㲻������������ʾ��������˻�����Ϊ������Ҫ����ʾ���Ǹ���Ҫ�����

		t2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData

		.getValue().GetName()));// �����˻�

		t3.setCellValueFactory(cellData -> new SimpleStringProperty(cellData

		.getValue().GetPrice()));// ת�˽��

		t4.setCellValueFactory(cellData -> new SimpleStringProperty(cellData

		.getValue().GetReason()));// ��ע

		t1.setItems(data);

		// ʱ��

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

					// ���ʱ��

					Date day = new Date();

					SimpleDateFormat df = new SimpleDateFormat(

					"yyyy-MM-dd HH:mm");

					tf3.setText(df.format(day));

				}

			}

		});

		// �ȵõ�memberID,Ȼ����в���

		f1.getMemberID();

		int size = f1.getLineItem().size();

		for (int i = 0; i < size; i++) {

			f1.getLineItem().get(i).getId();// �����˻�

			f1.getLineItem().get(i).getPrice();// ���

			f1.getLineItem().get(i).getComment();// ��ע

		}

		a.getChildren().addAll(root, text1, text2, comboBox1, comboBox2,

		comboBox3, t1, tf1, tf2, tf3);

		Scene s = new Scene(a, 800, 500);

		primaryStage.setTitle("������Ա");

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

	@FXML
	private void HandleTI(ActionEvent event) throws ParseException {

		boolean isRedCopy = false;

		// ��Ҫ֪���û���ѡ��
		if (j.equals("������Ա")) {
			isRedCopy = true;
		} else if (j.equals("�ܾ���")) {
			isRedCopy = false;
		}

		FinancialReceiptVO vo = new FinancialReceiptVO();

		vo.setId("");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		vo.setDate(sdf.parse(tf3.getText()));

		vo.setMemberID(comboBox2.getPromptText());

		vo.setFinancialReceiptType(FinancialReceiptType.BILL);

		vo.setType(ReceiptType.FINANCIAL_RECEIPT);

		vo.setState(ReceiptState.SUBMITTED);

		vo.setLineItem(lineItems);

		vo.setOperatorID(id);

		vo.setState(ReceiptState.SUBMITTED);

		double sum = 0;

		for (LineItemVO temp : lineItems) {

			sum = sum + temp.getPrice();

		}

		vo.setSum(sum);

		if (isRedCopy) {// Ϊ��岢���Ʒ���

			BusinessProcessHelper helper = BusinessProcessHelper.getInstance();

			try {

				ResultMessage message = helper.getService()
						.completeModifyFinancial(vo, id);

				if (message == ResultMessage.RED_COPY_MODIFY_TWICE) {

					vo.setState(ReceiptState.UNCOMMITTED);

					// ��ʾ�������ظ���岢�޸�ͬһ�ŵ������Ρ�

				}

			} catch (RemoteException e) {

				// TODO Auto-generated catch block

				e.printStackTrace();

			}

		}

		else {// Ϊ�ܾ����޸����۵�����

			CheckReceiptHelper helper = CheckReceiptHelper.getInstance();

			try {

				ResultMessage message = helper.getService()
						.completeModifyFinancial(vo, id);

				if (message == ResultMessage.DATA_OPERATE_FAIL_ID_NOT_FOUND) {

					// ��ʾ������ʧ�ܡ�

				}

			} catch (RemoteException e) {

				// TODO Auto-generated catch block

				e.printStackTrace();

			}

		}

	}

	@FXML
	private void HandleExport(ActionEvent event) {

	}

	@FXML
	private void HandleAdd(ActionEvent event) {
		LineItemVO vo = new LineItemVO();

		vo.setComment(tf2.getText());

		vo.setId(bankaccount.getId());

		vo.setPrice(Double.parseDouble(tf1.getText()));

		if (vo.getPrice() < 0) {

			// ��ʾ�����Ϊ�������������롱

		}

		else {

			lineItems.add(vo);

		}

		// ����lineItems��AccountName��ת��

		for (int i = 0; i < lineItems.size(); i++) {

			AccountName acc = new AccountName(lineItems.get(i));

			accountnamelist.add(acc);

		}

		// ������tableview����ʾ

		// ��Ҫ��ObserValue�����

		ObservableList<AccountName> data = FXCollections.observableArrayList();

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

}