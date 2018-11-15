package financelerui;

import java.io.IOException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
import rmi.BankAccountHelper;
import rmi.CommodityHelper;
import rmi.FinancialReceiptHelper;
import rmi.OperationLogHelper;
import rmi.UserHelper;
import VO.BankAccountVO;
import VO.CommodityVO;
import VO.FinancialReceiptVO;
import VO.LineItemVO;
import VO.LogVO;
import VO.UserVO;
import enums.FinancialReceiptType;
import enums.ReceiptState;
import enums.ReceiptType;
import enums.ResultMessage;

public class cost {// �ƶ��ֽ���õ�

	private static String j;

	private static String n;

	private static Stage pStage;

	private static TField tx1[] = new TField[6];// ����ԭ��

	private static TField tx2[] = new TField[6];// ������

	private static ComboBox tx3[] = new ComboBox[6];// ѡ�������˻�

	private static TField tf1;// �����˱��

	private static TField tf2;// ������ְ��

	private static TField tf3;// ʱ��

	private static String id;

	private static ArrayList<BankAccountVO> bankaccountlist = new ArrayList<>();

	public void display(String Job, String name, String ID, FinancialReceiptVO f) {// ����һ���Ӳݸ������ù������ֽ���õ�

		cost c = new cost();

		c.display(Job, name, ID);

		int size = f.getLineItem().size();

		tf1.setText(f.getMemberID());// �����˱��

		UserVO u = new UserVO();

		try {

			u = c.getUserByID(f.getMemberID());

		} catch (RemoteException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

		tf2.setText(u.getJob().toString());// ������ְ��

		tf3.setText("" + f.getDate());// ����ʱ��

		ArrayList<LineItemVO> lineItem = f.getLineItem();

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

		// �Զ���ʾ

		for (int i = 0; i < size; i++) {

			tx1[i].setText(f.getLineItem().get(i).getComment());// ԭ��

			tx2[i].setText(Double.toString(f.getLineItem().get(i).getPrice()));// ���

			tx3[i].setPromptText(bankAccounts.get(i));// �����˻�������

		}

	}

	@SuppressWarnings("unchecked")
	public void display(String Job, String name, String ID) {

		// TODO Auto-generated method stub

		Stage primaryStage = new Stage();

		id = ID;

		cost c = new cost();
		try {
			bankaccountlist = c.getBankAccountList();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Parent root = null;

		try {

			root = FXMLLoader.load(getClass().getResource("cost.fxml"));

		} catch (IOException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

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

		ScrollPane s1 = new ScrollPane();

		s1.setPrefWidth(412);

		s1.setMinHeight(196);

		s1.setMaxHeight(196);

		s1.setLayoutX(267);

		s1.setLayoutY(200);

		AnchorPane a1 = new AnchorPane();

		a1.setMinHeight(245);

		a1.setMaxHeight(245);

		a1.setPrefWidth(392);

		s1.setContent(a1);

		ImageView i1 = new ImageView(new Image("/backgroud.png", true));

		i1.setFitWidth(392);

		i1.setFitHeight(245);

		a1.getChildren().add(i1);

		// ����ԭ��

		TField t1 = new TField();

		t1.setText("����ԭ��");

		t1.setPrefWidth(118);

		t1.setLayoutX(0);

		t1.setLayoutY(0);

		t1.setEditable(false);

		// ��������ԭ��

		for (int i = 0; i < 6; i++) {

			tx1[i] = new TField();

			tx1[i].setPrefWidth(118);

			tx1[i].setLayoutX(0);

			tx1[i].setLayoutY(35 * (1 + i));

			a1.getChildren().add(tx1[i]);

		}

		// ������

		TField t2 = new TField();

		t2.setText("������");

		t2.setPrefWidth(118);

		t2.setLayoutX(118);

		t2.setLayoutY(0);

		t2.setEditable(false);

		// ����������

		for (int i = 0; i < 6; i++) {

			tx2[i] = new TField();

			tx2[i].setPrefWidth(118);

			tx2[i].setLayoutX(118);

			tx2[i].setLayoutY(35 * (1 + i));

			a1.getChildren().add(tx2[i]);

		}

		// �����˻�

		TField t3 = new TField();

		t3.setText("�����˻�");

		t3.setPrefWidth(156);

		t3.setLayoutX(236);

		t3.setLayoutY(0);

		t3.setEditable(false);

		// ���������˻�

		for (int i = 0; i < 6; i++) {

			tx3[i] = new ComboBox();

			for (int j = 0; j < bankaccountlist.size(); j++) {

				tx3[i].getItems().add(bankaccountlist.get(j).getName());

				tx3[i].getSelectionModel().selectedItemProperty()

				.addListener(new ChangeListener() {

					@Override
					public void changed(ObservableValue arg0, Object arg1,

					Object arg2) {

						// TODO Auto-generated method stub

						int index = 0;

					}

				});

			}

			tx3[i].setMinHeight(35);

			tx3[i].setEditable(false);

			tx3[i].setMaxHeight(35);

			tx3[i].setPrefWidth(156);

			tx3[i].setLayoutX(236);

			tx3[i].setStyle("-fx-border-color:Grey;-fx-border-radius:4;-fx-background-color:null");

			tx3[i].setLayoutY(35 * (1 + i));

			a1.getChildren().add(tx3[i]);

		}

		a1.getChildren().addAll(t1, t2, t3);

		// �����˵ı��

		tf1 = new TField();

		tf1.setMinHeight(30);

		tf1.setMaxHeight(30);

		tf1.setPrefWidth(117);

		tf1.setLayoutX(132);

		tf1.setLayoutY(167);

		// ������ְ��

		tf2 = new TField();

		tf2.setMinHeight(30);

		tf2.setMaxHeight(30);

		tf2.setPrefWidth(117);

		tf2.setLayoutX(132);

		tf2.setLayoutY(210);

		// ʱ��

		tf3 = new TField();

		tf3.setMinHeight(30);

		tf3.setMaxHeight(30);

		tf3.setPrefWidth(117);

		tf3.setLayoutX(132);

		tf3.setLayoutY(250);

		// ����ʱ��

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

		AnchorPane a = new AnchorPane();

		a.getChildren()

		.addAll(root, text1, text2, comboBox1, s1, tf1, tf2, tf3);

		Scene s = new Scene(a, 800, 500);

		primaryStage.setTitle("������Ա");

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
	private void HandleSaveDraft(ActionEvent event) {
		AlertBox a = new AlertBox();
		a.display("����ɹ�", "����ɹ�");
		pStage.close();

	}

	@FXML
	private void HandleTI(ActionEvent event) throws RemoteException,
			ParseException {

		FinancialReceiptVO vo = new FinancialReceiptVO();

		ArrayList<BankAccountVO> bankAccounts = new ArrayList<BankAccountVO>();

		ArrayList<LineItemVO> lineItems = new ArrayList<LineItemVO>();

		// ���û�ѡ����˻����뵽bankAccounts��,��������ԭ����������˳��ÿһ�����붼��һ�������˻���

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

				mistake = mistake + "��" + i + "��������Ƿ�\n";

			} else {

				lineItems.add(temp);

			}

		}

		if (mistake.equals("")) {

			FinancialReceiptHelper helper = FinancialReceiptHelper
					.getInstance();

			ResultMessage result = helper.getService().completeAdd(vo);

			if (result == ResultMessage.FINANCIAL_RECEIPT_SET_UP_SUCCESS) {

				OperationLogHelper operationHelper = OperationLogHelper
						.getInstance();

				LogVO log = new LogVO();

				log.setTime(new Date());

				log.setUserID(id);

				String Content = "�ύ�ֽ���õ�";

				log.setContent(Content);

				ResultMessage logResult = operationHelper.getService().add(log);

				// ��ʾ���ֽ���õ��ύ�ɹ���
				AlertBox a = new AlertBox();
				a.display("�ύ�ɹ�", "�ύ�ɹ�");
				pStage.close();

			}

			else if (result == ResultMessage.FINANCIAL_RECEIPT_SET_UP_FIAL_MAX_ALREADY) {

				// ��ʾ�����촴��������Ŀ�Ѵ����ޡ�

				AlertBox a = new AlertBox();
				a.display("�ﵽ����", "������");
			}

		} else {

			// ��ʾmistake������
			AlertBox a = new AlertBox();
			a.display(mistake, mistake);

		}

	}

	private ArrayList<BankAccountVO> getBankAccountList()

	throws RemoteException {

		BankAccountHelper helper = BankAccountHelper.getInstance();

		return helper.getService().getBankAccountList();

	}

}
