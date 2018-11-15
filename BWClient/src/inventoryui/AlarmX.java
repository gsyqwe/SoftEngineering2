package inventoryui;

import java.io.IOException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import mainframeui.AlertBox;
import mainframeui.TField;
import VO.CommodityVO;
import VO.InventoryReceiptVO;
import VO.LineItemVO;
import VO.LogVO;
import enums.InventoryReceiptType;
import enums.ReceiptState;
import enums.ReceiptType;
import enums.ResultMessage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import rmi.BusinessProcessHelper;
import rmi.CheckReceiptHelper;
import rmi.CommodityHelper;
import rmi.InventoryReceiptHelper;
import rmi.OperationLogHelper;

public class AlarmX {// ��帴�ƻ����ܾ���༭���Ľ���

	private static String j;

	private static String n;

	private static Stage pStage;

	private static String id;

	private static ComboBox<String> c1;// ��һ��ѡ����Ʒ

	private static TField t1;// ��ʾ��һ����Ʒ���

	private static ComboBox<String> c2;// �ڶ���

	private static TField t2;// ��ʾ�ڶ�����Ʒ���

	private static ComboBox<String> c3;// ������

	private static TField t3;// ��ʾ��������Ʒ���

	private static ComboBox<String> c4;// ���ĸ�

	private static TField t4;// ��ʾ���ĸ���Ʒ���

	// �����ִ�����

	private static TField tx[] = new TField[4];

	// ���뱸ע

	private static TField tx1[] = new TField[4];

	// ʱ��

	private static TField tx3;

	// �õ���Ʒ��ArrayList

	private static ArrayList<CommodityVO> commoditylist = new ArrayList<>();

	private static ArrayList<LineItemVO> lineItems = new ArrayList<>();

	private static String receiptID;

	public void display(String Job, String name, String ID,

	InventoryReceiptVO inventory) {// ͨ���ݸ������������

		// ��ҪlineItem��ÿһ���������ݣ�������Ʒ�����ݺ���Ʒ��ţ�����Ʒ�Ǹ���һ��ID����ʲô�����Լ�ȥѰ�һ�����ֱ�Ӹ���һ��vo

		// ��Ʒ���ƣ���ţ�����

		// Ȼ����Ҫ��ע��ʱ��

		receiptID = inventory.getId();

		lineItems = inventory.getLineItem();

		AlarmX alarm = new AlarmX();

		alarm.display(Job, name, ID);

		try {

			commoditylist = alarm.getCommodityList();

		} catch (RemoteException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

		tx3.setText("" + inventory.getDate());

		lineItems = inventory.getLineItem();

		for (int i = 0; i < lineItems.size(); i++) {

			if (i == 0) {

				// ����ID��һ��name,��Ҫ��Ʒ��name

				t1.setText(lineItems.get(i).getId());

				tx[i].setText("" + lineItems.get(i).getQuantity());

				tx1[i].setText(lineItems.get(i).getComment());

			} else if (i == 1) {

				// ����ID��һ��name,��Ҫ��Ʒ��name

				t2.setText(lineItems.get(i).getId());

				tx[i].setText("" + lineItems.get(i).getQuantity());

				tx1[i].setText(lineItems.get(i).getComment());

			} else if (i == 2) {

				// ����ID��һ��name,��Ҫ��Ʒ��name

				t3.setText(lineItems.get(i).getId());

				tx[i].setText("" + lineItems.get(i).getQuantity());

				tx1[i].setText(lineItems.get(i).getComment());

			} else if (i == 3) {

				// ����ID��һ��name,��Ҫ��Ʒ��name

				t4.setText(lineItems.get(i).getId());

				tx[i].setText("" + lineItems.get(i).getQuantity());

				tx1[i].setText(lineItems.get(i).getComment());

			}

		}

	}

	@SuppressWarnings("unchecked")
	public void display(String Job, String name, String ID) {

		// TODO Auto-generated method stub

		Stage primaryStage = new Stage();

		Parent root = null;

		id = ID;

		try {

			root = FXMLLoader.load(getClass().getResource("loss.fxml"));

		} catch (IOException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

		// �ȵõ�commidtyVO��ArrayList

		AlarmX al = new AlarmX();

		try {

			commoditylist = al.getCommodityList();

		} catch (RemoteException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

		// ��ʾ��¼�˵���Ϣ

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

		AnchorPane a = new AnchorPane();

		a.getChildren().add(root);

		// ѡ����Ʒ1,������һ��TextField,������ʾ��Ʒ�ı��

		t1 = new TField();

		t1.setEditable(false);

		t1.setMinHeight(30);

		t1.setMaxHeight(30);

		t1.setPrefWidth(152);

		t1.setLayoutX(271);

		t1.setLayoutY(245);

		// ѡ����Ʒ

		c1 = new ComboBox();

		c1.setStyle("-fx-border-color:Grey;-fx-border-radius:4;-fx-background-color:null");

		for (int i = 0; i < commoditylist.size(); i++) {

			c1.getItems().add(commoditylist.get(i).getName());

		}

		c1.getSelectionModel().selectedItemProperty()

		.addListener(new ChangeListener() {

			@SuppressWarnings("rawtypes")
			@Override
			public void changed(ObservableValue arg0, Object arg1,

			Object arg2) {

				// TODO Auto-generated method stub
				int index = c1.getSelectionModel().getSelectedIndex();
				t1.setText(commoditylist.get(index).getId());

			}

		});

		c1.setLayoutX(119.0);

		c1.setLayoutY(245.0);

		c1.setPrefWidth(152.0);

		c1.setMinHeight(30.0);

		c1.setMaxHeight(30.0);

		// ѡ����Ʒ2

		t2 = new TField();

		t2.setEditable(false);

		t2.setMinHeight(30);

		t2.setMaxHeight(30);

		t2.setPrefWidth(152);

		t2.setLayoutX(271);

		t2.setLayoutY(275);

		c2 = new ComboBox();

		c2.setStyle("-fx-border-color:Grey;-fx-border-radius:4;-fx-background-color:null");

		for (int i = 0; i < commoditylist.size(); i++) {

			c2.getItems().add(commoditylist.get(i).getName());

		}

		c2.getSelectionModel().selectedItemProperty()

		.addListener(new ChangeListener() {

			@SuppressWarnings("rawtypes")
			@Override
			public void changed(ObservableValue arg0, Object arg1,

			Object arg2) {

				// TODO Auto-generated method stub
				int index = c2.getSelectionModel().getSelectedIndex();
				t2.setText(commoditylist.get(index).getId());
			}

		});

		c2.setLayoutX(119.0);

		c2.setLayoutY(275.0);

		c2.setPrefWidth(152.0);

		c2.setMinHeight(30.0);

		c2.setMaxHeight(30.0);

		// ѡ����Ʒ3

		t3 = new TField();

		t3.setEditable(false);

		t3.setMinHeight(30);

		t3.setMaxHeight(30);

		t3.setPrefWidth(152);

		t3.setLayoutX(271);

		t3.setLayoutY(305);

		c3 = new ComboBox();

		c3.setStyle("-fx-border-color:Grey;-fx-border-radius:4;-fx-background-color:null");

		for (int i = 0; i < commoditylist.size(); i++) {

			c3.getItems().add(commoditylist.get(i).getName());

		}

		c3.getSelectionModel().selectedItemProperty()

		.addListener(new ChangeListener() {

			@SuppressWarnings("rawtypes")
			@Override
			public void changed(ObservableValue arg0, Object arg1,

			Object arg2) {

				// TODO Auto-generated method stub
				int index = c3.getSelectionModel().getSelectedIndex();
				t3.setText(commoditylist.get(index).getId());
			}

		});

		c3.setLayoutX(119.0);

		c3.setLayoutY(305.0);

		c3.setPrefWidth(152.0);

		c3.setMinHeight(30.0);

		c3.setMaxHeight(30.0);

		// ѡ����Ʒ4

		t4 = new TField();

		t4.setEditable(false);

		t4.setMinHeight(30);

		t4.setMaxHeight(30);

		t4.setPrefWidth(152);

		t4.setLayoutX(271);

		t4.setLayoutY(335);

		c4 = new ComboBox();

		c4.setStyle("-fx-border-color:Grey;-fx-border-radius:4;-fx-background-color:null");

		for (int i = 0; i < commoditylist.size(); i++) {

			c4.getItems().add(commoditylist.get(i).getName());

		}

		c4.getSelectionModel().selectedItemProperty()

		.addListener(new ChangeListener() {

			@SuppressWarnings("rawtypes")
			@Override
			public void changed(ObservableValue arg0, Object arg1,

			Object arg2) {

				// TODO Auto-generated method stub
				int index = c4.getSelectionModel().getSelectedIndex();
				t4.setText(commoditylist.get(index).getId());
			}

		});

		c4.setLayoutX(119.0);

		c4.setLayoutY(335.0);

		c4.setPrefWidth(152.0);

		c4.setMinHeight(30.0);

		c4.setMaxHeight(30.0);

		// ��Ʒ����

		TField tf1 = new TField();

		tf1.setEditable(false);

		tf1.setText("��Ʒ����");

		tf1.setLayoutX(119);

		tf1.setLayoutY(215);

		tf1.setPrefWidth(152);

		tf1.setMinHeight(30);

		tf1.setMaxHeight(30);

		// ��Ʒ���

		TField tf2 = new TField();

		tf2.setEditable(false);

		tf2.setText("��Ʒ���");

		tf2.setLayoutX(271);

		tf2.setLayoutY(215);

		tf2.setPrefWidth(152);

		tf2.setMinHeight(30);

		tf2.setMaxHeight(30);

		// �ִ�����/��������

		TField tf3 = new TField();

		tf3.setLayoutX(423);

		tf3.setText("��������");

		tf3.setEditable(false);

		tf3.setLayoutY(215);

		tf3.setPrefWidth(152);

		tf3.setMinHeight(30);

		tf3.setMaxHeight(30);

		// �����ִ�����/��������

		for (int i = 0; i < 4; i++) {

			tx[i] = new TField();

			tx[i].setMinHeight(30);

			tx[i].setMaxHeight(30);

			tx[i].setPrefWidth(152);

			tx[i].setLayoutX(423);

			tx[i].setLayoutY(245 + 30 * i);

			a.getChildren().add(tx[i]);

		}

		// ��ע

		TField tf4 = new TField();

		tf4.setLayoutX(575);

		tf4.setText("��ע");

		tf4.setEditable(false);

		tf4.setLayoutY(215);

		tf4.setPrefWidth(152);

		tf4.setMinHeight(30);

		tf4.setMaxHeight(30);

		// ���뱸ע

		for (int i = 0; i < 4; i++) {

			tx1[i] = new TField();

			tx1[i].setLayoutX(575);

			tx1[i].setLayoutY(245 + 30 * i);

			tx1[i].setMinHeight(30);

			tx1[i].setMaxHeight(30);

			tx1[i].setPrefWidth(152);

			a.getChildren().add(tx1[i]);

		}

		// ���ɱ��

		Text text = new Text();

		text.setStyle("-fx-background-color:null");

		text.setText("���Ϊ");

		text.setLayoutX(119.0);

		text.setLayoutY(219.0);

		// ʱ��

		tx3 = new TField();

		tx3.setMinHeight(30);

		tx3.setMaxHeight(30);

		tx3.setPrefWidth(117);

		tx3.setLayoutX(60);

		tx3.setLayoutY(155);

		tx3.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				if (event.getClickCount() == 2) {

					// ���ʱ��

					Date day = new Date();

					SimpleDateFormat df = new SimpleDateFormat(

					"yyyy-MM-dd HH:mm:ss");

					tx3.setText(df.format(day));

				}

			}

		});

		a.getChildren().addAll(c1, text1, text2, c2, c3, c4, t1, t2, t3, t4,

		tf1, tf2, tf3, tf4, tx3);

		Scene s = new Scene(a, 800, 500);

		primaryStage.setTitle("��������Ա");

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
	private void HandleTI(ActionEvent event) throws RemoteException,
			ParseException {// ���ӵķ���

		InventoryReceiptVO vo = new InventoryReceiptVO();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		vo.setDate(sdf.parse(tx3.getText()));

		vo.setInventoryType(InventoryReceiptType.ALARM);

		vo.setType(ReceiptType.INVENTORY_RECEIPT);

		vo.setOperatorID(id);

		ArrayList<LineItemVO> lineitems = new ArrayList<LineItemVO>();

		String mistake = "";

		for (int i = 0; i < 4; i++) {

			LineItemVO temp = new LineItemVO();

			if (i == 0) {

				temp.setId(t1.getText());

			}

			else if (i == 1) {

				if (t2.getText().equals("")) {

				} else {
					temp.setId(t2.getText());
				}

			}

			else if (i == 2) {

				if (t3.getText().equals("")) {

				} else {
					temp.setId(t3.getText());
				}
			}

			else if (i == 3) {

				if (t4.getText().equals("")) {

				} else {
					temp.setId(t4.getText());
				}
			}

			if (tx1[i].getText().equals("")) {

			} else {
				temp.setComment(tx1[i].getText());
			}

			if (tx[i].getText().equals("")) {

			} else {
				temp.setQuantity(Integer.parseInt(tx[i].getText()));
				if (Integer.parseInt(tx[i].getText()) < 0) {

					mistake = mistake + "��Ʒ����Ϊ��������������\n";

				} else {

					temp.setQuantity(Integer.parseInt(tx[i].getText()));

					lineitems.add(temp);

				}

			}
		}
		vo.setLineItem(lineitems);

		if (mistake.equals("")) {
			// ��ʾmistake������
			InventoryReceiptHelper helper = InventoryReceiptHelper
					.getInstance();

			ResultMessage result = helper.getService().completeAdd(vo);

			if (result == ResultMessage.SUCCESS) {

				OperationLogHelper operationHelper = OperationLogHelper
						.getInstance();

				LogVO log = new LogVO();

				log.setTime(new Date());

				log.setUserID(id);

				String Content = "�ύ��汨����";

				log.setContent(Content);

				ResultMessage logResult = operationHelper.getService().add(log);

				AlertBox a = new AlertBox();
				a.display("�ύ�ɹ�", "�ύ�ɹ�");
				pStage.close();
				// ��ʾ����汨�����ύ�ɹ���

			}
		} else {
			AlertBox a = new AlertBox();
			a.display(mistake, mistake);

		}

	}

	@FXML
	private void HandleEsc(ActionEvent event) {

		pStage.close();

	}

	@FXML
	private void HandleSaveDraft(ActionEvent event) throws ParseException,
			RemoteException {

		InventoryReceiptVO vo = new InventoryReceiptVO();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		vo.setDate(sdf.parse(tx3.getText()));

		vo.setInventoryType(InventoryReceiptType.ALARM);

		vo.setType(ReceiptType.INVENTORY_RECEIPT);

		vo.setOperatorID(id);

		vo.setState(ReceiptState.UNCOMMITTED);

		ArrayList<LineItemVO> lineitems = new ArrayList<LineItemVO>();

		for (int i = 0; i < 4; i++) {

			LineItemVO temp = new LineItemVO();

			if (i == 0) {

				temp.setId(t1.getText());

			}

			else if (i == 1) {

				temp.setId(t2.getText());

			}

			else if (i == 2) {

				temp.setId(t3.getText());

			}

			else if (i == 3) {

				temp.setId(t4.getText());

			}

			temp.setComment(tx1[i].getText());

			temp.setQuantity(Integer.parseInt(tx[i].getText()));

			System.out.println(temp);

			lineitems.add(temp);

		}

		vo.setLineItem(lineItems);

		InventoryReceiptHelper helper = InventoryReceiptHelper.getInstance();

		ResultMessage result = helper.getService().endReceipt(vo);

		if (result == ResultMessage.SUCCESS) {

			// ����ɹ�
			AlertBox alert = new AlertBox();
			alert.display("����ɹ�", "����ɹ�");
			pStage.close();

		} else {

			// ����ʧ��
			AlertBox alert = new AlertBox();
			alert.display("����ʧ��", "����ʧ��");

		}

	}

	private ArrayList<CommodityVO> getCommodityList() throws RemoteException {

		CommodityHelper helper = CommodityHelper.getInstance();

		return (helper.getService().showCommodityList());

	}

}
