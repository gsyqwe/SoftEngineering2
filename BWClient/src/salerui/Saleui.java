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
import rmi.CommodityHelper;
import rmi.SalesHelper;
import rmi.MemberHelper;
import VO.CommodityVO;
import VO.LineItemVO;
import VO.MemberVO;
import VO.SalesReceiptVO;
import VO.VoucherVO;
import enums.MemberType;
import enums.ReceiptState;
import enums.ResultMessage;
import enums.SalesReceiptType;

/**
 * 
 * ���⣺����Աid������id����tf2����ģ������tf2����ģ���Ҫһ�������õ�����user
 */

public class Saleui {// �½����۵�

	private static String j;

	private static String n;

	private static Stage pStage;

	private static String id;

	private static ArrayList<LineItemVO> mylist = new ArrayList<>();// ά�����LineItem

	private static ComboBox comboBox1;// ѡ��ͻ�

	private static ComboBox comboBox2;// ѡ��ֿ�

	private static ComboBox com[] = new ComboBox[6];// ѡ����Ʒ

	private static TField t1[] = new TField[6];// ��Ʒ��ţ�ѡ��������

	private static TField t2[] = new TField[6];// ��Ʒ�ͺ�

	private static TField t3[] = new TField[6];// ����

	private static TField t4[] = new TField[6];// ����

	private static TField t5[] = new TField[6];// ��ע

	private static TField tf2;

	private static TField tf3;// ԭ�ܼ�

	private static TField tf4;// ԭ����

	private static ComboBox cm1;// ѡ�����ô���ȯ

	private static TField tf5;// ���ս��

	private static TField Timefield;// ʱ��

	private static boolean isAdd[] = new boolean[6];

	private static int k = 0;

	private static ArrayList<CommodityVO> mylist2 = new ArrayList<>();// �õ�������Ʒ��ArrayList

	private static CommodityVO commodity;// ѡ������Ʒ

	private static MemberVO member;
	private static ArrayList<MemberVO> memberlist = new ArrayList<>();
	private static VoucherVO voucher;

	private static SalesReceiptVO sales = new SalesReceiptVO();// ��Ϊ��֪��rmi�ĵ��ö�Controller��ʼ����Ӱ�죬

	// ���Ծ����ڽ����ά��һ������VO���ڵ����ύ��ʱ���ٽ�������vo�����߼���

	public void display(String Job, String name, String ID,
			SalesReceiptVO SaReceipt) {

		Saleui sal = new Saleui();
		sal.display(Job, name, ID);
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

		comboBox2.setValue("һ�Ųֿ�");

		tf4.setText(Double.toString(SaReceipt.getDiscount()));// ����

		tf3.setText(Double.toString(SaReceipt.getSumBeforeDiscount()));// ԭ�ܼ�

		tf5.setText(Double.toString(SaReceipt.getSumAfterDiscount()));// ���ú��ܼ�

		voucher = SaReceipt.getVoucher();// ����ȯ

		cm1.setValue(Double.toString(voucher.getFaceValue()));

	}

	@SuppressWarnings("unchecked")
	public void display(String Job, String name, String ID) {

		// TODO Auto-generated method stub
		sales.setSalesType(SalesReceiptType.SALES);
		voucher = new VoucherVO();
		voucher.setFaceValue(0);
		Saleui s = new Saleui();
		mylist2 = s.getAllCommodity();
		memberlist = s.getAllMember();
		Stage primaryStage = new Stage();

		id = ID;

		Parent root = null;

		try {

			root = FXMLLoader.load(getClass().getResource("Saleui.fxml"));

		} catch (IOException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

		AnchorPane a = new AnchorPane();

		TextField text1 = new TextField();

		text1.setStyle("-fx-background-color:null");

		text1.setText("��ӭ��:" + name);

		text1.setLayoutX(913.0);

		text1.setLayoutY(40.0);

		text1.setMinHeight(36.0);

		text1.setMaxHeight(36.0);

		text1.setFont(Font.font("Verdana", 18));

		text1.prefWidth(185.0);

		text1.setEditable(false);

		// ComboBox1ѡ��ͻ�

		comboBox1 = new ComboBox();

		comboBox1.setValue("ѡ��ͻ�");

		comboBox1.setLayoutX(107.0);

		comboBox1.setLayoutY(310.0);

		comboBox1
				.setStyle("-fx-border-color:Grey;-fx-border-radius:4;-fx-background-color:null");

		for (int i = 0; i < memberlist.size(); i++) {
			comboBox1.getItems().add(memberlist.get(i).getName());
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
						sales.setMemberID(member.getId());
						for (int i = 0; i < member.getVouchers().size(); i++) {
							cm1.getItems().add(
									member.getVouchers().get(i).getFaceValue());
						}
					}
				});

		comboBox1.setMinHeight(30.0);

		comboBox1.setMaxHeight(30.0);

		comboBox1.setMinWidth(133.0);

		comboBox1.setMaxWidth(133.0);

		// ComboBox2ѡ��ֿ�

		comboBox2 = new ComboBox();

		comboBox2.setValue("ѡ��ֿ�");

		comboBox2
				.setStyle("-fx-border-color:Grey;-fx-border-radius:4;-fx-background-color:null");

		comboBox2.setLayoutX(645.0);

		comboBox2.setLayoutY(255.0);

		comboBox2.getItems().addAll("1�Ųֿ�");

		comboBox2.setMinHeight(30.0);

		comboBox2.setMaxHeight(30.0);

		comboBox2.setMinWidth(125.0);

		comboBox2.setMaxWidth(125.0);

		TextField text2 = new TextField();

		text2.setStyle("-fx-background-color:null");

		text2.setText("��ǰְ��:" + Job);

		text2.setLayoutX(907.0);

		text2.setLayoutY(68.0);

		text2.setMinHeight(30.0);

		text2.setMaxHeight(30.0);

		text2.setFont(Font.font("Verdana", 18));

		text2.prefWidth(185.0);

		text2.setEditable(false);

		// ����ScollPane

		ScrollPane s1 = new ScrollPane();

		s1.setLayoutX(150);

		s1.setLayoutY(400);

		s1.setMinHeight(270);

		s1.setMaxHeight(270);

		s1.setPrefWidth(866);

		AnchorPane a1 = new AnchorPane();

		a1.setMinHeight(270);

		a1.setMaxHeight(270);

		a1.setPrefWidth(866);

		ImageView i1 = new ImageView(new Image("/backgroud.png", true));

		i1.setFitWidth(866);

		i1.setFitHeight(270);

		a1.getChildren().add(i1);

		// ��Ʒ����

		TField tx1 = new TField();

		tx1.setText("��Ʒ����");

		tx1.setEditable(false);

		tx1.setMinHeight(35);

		tx1.setMaxHeight(35);

		tx1.setPrefWidth(160);

		tx1.setLayoutX(0);

		tx1.setLayoutY(0);

		// ѡ����Ʒ����

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

		// ���

		TField tx2 = new TField();

		tx2.setText("��Ʒ���");

		tx2.setEditable(false);

		tx2.setPrefWidth(90);

		tx2.setLayoutX(160);

		tx2.setMinHeight(35);

		tx2.setMaxHeight(35);

		tx2.setLayoutY(0);

		// ����ѡ�����Ʒ���ɱ��

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
		// �����¼���com��t1����
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

		// �ͺ�

		TField tx3 = new TField();

		tx3.setText("�ͺ�");

		tx3.setEditable(false);

		tx3.setMinHeight(35);

		tx3.setMaxHeight(35);

		tx3.setPrefWidth(110);

		tx3.setLayoutX(250);

		tx3.setLayoutY(0);

		// �����ͺ�

		for (int i = 0; i < 6; i++) {

			t2[i] = new TField();

			t2[i].setMinHeight(35);

			t2[i].setMaxHeight(35);

			t2[i].setPrefWidth(110);

			t2[i].setLayoutX(250);

			t2[i].setLayoutY(35 * (i + 1));

			a1.getChildren().add(t2[i]);

		}

		// ����

		TField tx4 = new TField();

		tx4.setText("����");

		tx4.setEditable(false);

		tx4.setMinHeight(35);

		tx4.setMaxHeight(35);

		tx4.setPrefWidth(110);

		tx4.setLayoutX(360);

		tx4.setLayoutY(0);

		// ��������

		for (int i = 0; i < 6; i++) {

			t3[i] = new TField();

			t3[i].setMinHeight(35);

			t3[i].setMaxHeight(35);

			t3[i].setPrefWidth(110);

			t3[i].setLayoutX(360);

			t3[i].setLayoutY(35 * (i + 1));

			a1.getChildren().add(t3[i]);

		}

		// ����

		TField tx5 = new TField();

		tx5.setText("����");

		tx5.setEditable(false);

		tx5.setMinHeight(35);

		tx5.setMaxHeight(35);

		tx5.setPrefWidth(110);

		tx5.setLayoutX(470);

		tx5.setLayoutY(0);

		// ���뵥��

		for (int i = 0; i < 6; i++) {

			t4[i] = new TField();

			t4[i].setMinHeight(35);

			t4[i].setMaxHeight(35);

			t4[i].setPrefWidth(110);

			t4[i].setLayoutX(470);

			t4[i].setLayoutY(35 * (i + 1));

			a1.getChildren().add(t4[i]);

		}

		// ��ע

		TField tx6 = new TField();

		tx6.setText("��ע");

		tx6.setLayoutX(580);

		tx6.setLayoutY(0);

		tx6.setPrefWidth(216);

		tx6.setMinHeight(35);

		tx6.setMaxHeight(35);

		tx6.setEditable(false);

		// ���뱸ע

		for (int i = 0; i < 6; i++) {

			t5[i] = new TField();

			t5[i].setMinHeight(35);

			t5[i].setMaxHeight(35);

			t5[i].setPrefWidth(216);

			t5[i].setLayoutX(580);

			t5[i].setLayoutY(35 * (i + 1));

			a1.getChildren().add(t5[i]);

		}

		// ���ӵİ�ť

		Button b[] = new Button[6];

		for (int i = 0; i < 6; i++) {

			b[i] = new Button();

			b[i].setPrefWidth(30);

			b[i].setMinHeight(30);

			b[i].setMaxHeight(30);

			b[i].setLayoutX(796);

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
				String time = Timefield.getText();
				System.out.println(time);
				Saleui s = new Saleui();
				Date d = s.strToDate(time);
				sales.setDate(d);
				System.out.println(sales.getDate());

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

				} else {// ɾ��
					isAdd[0] = true;
					mylist.remove(0);
					b[0].setStyle("-fx-background-image:url('@../add.jpg')");
					// ������������
					for (int i = 1; i < 6; i++) {
						if (t1[i].getText().equals("")) {
							// ����
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

				SalesHelper helper = SalesHelper.getInstance();

				ArrayList<String> discription = new ArrayList<>();// ������������promotion������

				sales.setLineItem(mylist);

				ArrayList<Double> discount = new ArrayList<>();// ���������������õ�

				try {
					sales = helper.getService().addLineItem(sales, discription,
							discount);// ������õ�����,���ص�discount�Ĵ�Сֻ��1�������ڵ����ý��
					AlertBox a = new AlertBox();
					String des = "";
					for (int i = 0; i < discription.size(); i++) {
						des = des + " " + discription.get(i) + "\n";
					}
					a.display(des, sales.getDiscount() + des);

					// ��������˵����sales����Ӧ�����������ԣ������ð�����õ���Ʒ�����ȵ�

				} catch (RemoteException e) {

					// TODO Auto-generated catch bloc0

					e.printStackTrace();

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

				} else {// ɾ��
					isAdd[0] = true;
					mylist.remove(1);
					b[1].setStyle("-fx-background-image:url('@../add.jpg')");
					// ������������
					for (int i = 2; i < 6; i++) {
						if (t1[i].getText().equals("")) {
							// ����
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

				SalesHelper helper = SalesHelper.getInstance();

				ArrayList<String> discription = new ArrayList<>();// ������������promotion������

				sales.setLineItem(mylist);

				ArrayList<Double> discount = new ArrayList<>();// ���������������õ�

				try {

					sales = helper.getService().addLineItem(sales, discription,
							discount);// ������õ�����,���ص�discount�Ĵ�Сֻ��1�������ڵ����ý��

					sales.setDiscount(discount.get(0));

					AlertBox a = new AlertBox();
					String des = "";
					for (int i = 0; i < discription.size(); i++) {
						des = des + " " + discription.get(i) + "\n";
					}
					a.display(des, des);

					// ��������˵����sales����Ӧ�����������ԣ������ð�����õ���Ʒ�����ȵ�

				} catch (RemoteException e) {

					// TODO Auto-generated catch bloc0

					e.printStackTrace();

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

						return;

					}

					LineItemVO l1 = new LineItemVO(id, Integer
							.parseInt(quantity), Double.parseDouble(danjia),

					commence);
					isAdd[2] = false;
					mylist.add(l1);
					b[2].setStyle("-fx-background-image:url('@../delete.jpg')");

				} else {// ɾ��
					isAdd[2] = true;
					mylist.remove(2);
					b[2].setStyle("-fx-background-image:url('@../add.jpg')");
					// ������������
					for (int i = 3; i < 6; i++) {
						if (t1[i].getText().equals("")) {
							// ����
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

				SalesHelper helper = SalesHelper.getInstance();

				ArrayList<String> discription = new ArrayList<>();// ������������promotion������

				sales.setLineItem(mylist);

				ArrayList<Double> discount = new ArrayList<>();// ���������������õ�

				try {

					sales = helper.getService().addLineItem(sales, discription,
							discount);// ������õ�����,���ص�discount�Ĵ�Сֻ��1�������ڵ����ý��

					sales.setDiscount(discount.get(0));

					AlertBox a = new AlertBox();
					String des = "";
					for (int i = 0; i < discription.size(); i++) {
						des = des + " " + discription.get(i) + "\n";
					}
					a.display(des, des);

					// ��������˵����sales����Ӧ�����������ԣ������ð�����õ���Ʒ�����ȵ�

				} catch (RemoteException e) {

					// TODO Auto-generated catch bloc0

					e.printStackTrace();

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

				} else {// ɾ��
					isAdd[0] = true;
					mylist.remove(3);
					b[3].setStyle("-fx-background-image:url('@../add.jpg')");
					// ������������
					for (int i = 4; i < 6; i++) {
						if (t1[i].getText().equals("")) {
							// ����
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

				SalesHelper helper = SalesHelper.getInstance();

				ArrayList<String> discription = new ArrayList<>();// ������������promotion������

				sales.setLineItem(mylist);

				ArrayList<Double> discount = new ArrayList<>();// ���������������õ�

				try {

					sales = helper.getService().addLineItem(sales, discription,
							discount);// ������õ�����,���ص�discount�Ĵ�Сֻ��1�������ڵ����ý��

					sales.setDiscount(discount.get(0));

					AlertBox a = new AlertBox();
					String des = "";
					for (int i = 0; i < discription.size(); i++) {
						des = des + " " + discription.get(i) + "\n";
					}
					a.display(des, des);

					// ��������˵����sales����Ӧ�����������ԣ������ð�����õ���Ʒ�����ȵ�

				} catch (RemoteException e) {

					// TODO Auto-generated catch bloc0

					e.printStackTrace();

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

				} else {// ɾ��
					isAdd[4] = true;
					mylist.remove(4);
					b[4].setStyle("-fx-background-image:url('@../add.jpg')");
					// ������������
					for (int i = 1; i < 6; i++) {
						if (t1[i].getText().equals("")) {
							// ����
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

				SalesHelper helper = SalesHelper.getInstance();

				ArrayList<String> discription = new ArrayList<>();// ������������promotion������

				sales.setLineItem(mylist);

				ArrayList<Double> discount = new ArrayList<>();// ���������������õ�

				try {

					sales = helper.getService().addLineItem(sales, discription,
							discount);// ������õ�����,���ص�discount�Ĵ�Сֻ��1�������ڵ����ý��

					sales.setDiscount(discount.get(0));

					AlertBox a = new AlertBox();
					String des = "";
					for (int i = 0; i < discription.size(); i++) {
						des = des + " " + discription.get(i) + "\n";
					}
					a.display(des, des);

					// ��������˵����sales����Ӧ�����������ԣ������ð�����õ���Ʒ�����ȵ�

				} catch (RemoteException e) {

					// TODO Auto-generated catch bloc0

					e.printStackTrace();

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

				} else {// ɾ��
					isAdd[5] = true;
					mylist.remove(5);
					b[5].setStyle("-fx-background-image:url('@../add.jpg')");
					// ������������

				}

				SalesHelper helper = SalesHelper.getInstance();

				ArrayList<String> discription = new ArrayList<>();// ������������promotion������

				sales.setLineItem(mylist);

				ArrayList<Double> discount = new ArrayList<>();// ���������������õ�

				try {

					sales = helper.getService().addLineItem(sales, discription,
							discount);// ������õ�����,���ص�discount�Ĵ�Сֻ��1�������ڵ����ý��

					sales.setDiscount(discount.get(0));

					AlertBox a = new AlertBox();
					String des = "";
					for (int i = 0; i < discription.size(); i++) {
						des = des + " " + discription.get(i) + "\n";
					}
					a.display(des, des);

					// ��������˵����sales����Ӧ�����������ԣ������ð�����õ���Ʒ�����ȵ�

				} catch (RemoteException e) {

					// TODO Auto-generated catch bloc0

					e.printStackTrace();

				}

			}

		});

		a1.getChildren().addAll(tx1, tx2, tx3, tx4, tx5, tx6);

		s1.setContent(a1);

		// ҵ��Ա

		tf2 = new TField();

		tf2.setMinHeight(30);

		tf2.setMaxHeight(30);

		tf2.setPrefWidth(133);

		tf2.setLayoutX(380);

		tf2.setLayoutY(260);

		tf2.setEditable(false);
		tf2.setText(name);

		// ԭ�ܽ��,��������ļ���

		tf3 = new TField();

		tf3.setEditable(false);

		tf3.setLayoutX(85);

		tf3.setLayoutY(707);

		tf3.setPrefWidth(80);

		// ԭ����

		tf4 = new TField();

		tf4.setLayoutX(300);

		tf4.setLayoutY(707);

		tf4.setPrefWidth(80);

		// ���ô���ȯ

		cm1 = new ComboBox();

		cm1.setStyle("-fx-border-color:Grey;-fx-border-radius:4;-fx-background-color:null");

		cm1.setMinHeight(30);

		cm1.setMaxHeight(30);

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

		cm1.setLayoutX(593);

		cm1.setLayoutY(707);

		cm1.setPrefWidth(80);

		// �����˻����

		tf5 = new TField();

		tf5.setLayoutX(890);

		tf5.setLayoutY(707);

		tf5.setPrefWidth(85);

		tf5.setMinHeight(30);
		tf5.setMaxHeight(30);

		tf5.setEditable(false);

		// ʱ��

		Timefield = new TField();

		Timefield.setMinHeight(30);

		Timefield.setMaxHeight(30);

		Timefield.setPrefWidth(117);

		Timefield.setLayoutX(90);

		Timefield.setLayoutY(260);

		Timefield.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				if (event.getClickCount() == 2) {

					// ���ʱ��

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

					// ����ܼ�

					double sum = 0;

					for (LineItemVO item : mylist) {

						sum = sum + item.getPrice() * item.getQuantity();

					}

					tf3.setText(Double.toString(sum));

				}

			}

		});

		a.getChildren().addAll(root, text1, text2, comboBox1, comboBox2, s1,
				tf3, tf4, tf5, cm1, tf2,

				Timefield);

		Scene scene = new Scene(a, 1080, 800);

		primaryStage.setTitle("����������Ա");

		primaryStage.setScene(scene);

		j = Job;

		n = name;

		pStage = primaryStage;

		primaryStage.show();

	}

	private String calculate() {// ��д��ĵõ��Ĵ������Ե��¼�

		// �����ڵ�LineItem��ArrayList��������������ӵ�LineItemVO��

		// �㻹Ҫ���������ѽ�������

		return null;

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
	private void HandleSave(ActionEvent event) {

		sales.setOperatorID(id);

		String date = Timefield.getText();

		if (date == null || date.equals("")) {

			String warning = "The date cannot be empty even when you save it.";
			AlertBox a = new AlertBox();
			a.display(warning, warning);

			return;

		}

		Date makeDate = strToDate(date);

		if (makeDate == null) {

			String warning = "The date format is not right.";
			AlertBox a = new AlertBox();
			a.display(warning, warning);
			return;

		}

		sales.setDate(makeDate);

		sales.setMemberID(member.getId());

		sales.setRepositoryName(comboBox2.getPromptText());

		sales.setOperatorID(id);// �Ҳ²⵱ǰ������Ա��idӦ����id

		sales.setSalesType(SalesReceiptType.SALES);// �趨���ݵ����ͣ������˻�

		ArrayList<LineItemVO> lineItem = new ArrayList<>();

		ArrayList<String> description = new ArrayList<>();

		if (tf4.getText() == null || tf4.getText().equals("")) {

			sales.setDiscount(0);

		} else {

			sales.setDiscount(Double.parseDouble(tf4.getText()));

		}

		for (int i = 0; i < 6; i++) {

			if (t1[i].getText() == null || t1[i].getText().equals("")) {

				break;

			} else {

				int quantity = 0;

				double price = 0;

				if (t3[i].getText() != null && !t3[i].getText().equals("")) {

					quantity = Integer.parseInt(t3[i].getText());

				}

				String priceInString = t4[i].getText();

				if (priceInString != null && !priceInString.equals("")) {

					price = Double.parseDouble(priceInString);

				}

				LineItemVO item = new LineItemVO(t1[i].getText(), quantity,
						price, t5[i].getText());// �����ݸ嵥��ʱ���ж���û����д���������û����д��������0

				lineItem.add(item);

			}

		}

		sales.setLineItem(lineItem);

		SalesHelper helper = SalesHelper.getInstance();

		try {
			sales.setSalesType(SalesReceiptType.SALES);
			ResultMessage message = helper.getService().endSale(sales);

			if (message != ResultMessage.SUCCESS) {

				String warning = "Unexception data fault.";
				AlertBox a = new AlertBox();
				a.display(warning, warning);
				return;

			} else {
				AlertBox a = new AlertBox();
				a.display("����ɹ�", "����ɹ�");
				pStage.close();
			}

		} catch (RemoteException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

	}

	@FXML
	// ���ǵ���ύʱ�����İ�ť
	private void HandleTI(ActionEvent event) {

		// ���ڼ��蹩Ӧ�̹�����һ��memberVO

		String date = Timefield.getText();

		if (date == null || date.equals("")) {

			String warning = "The date cannot be empty.";
			AlertBox a = new AlertBox();
			a.display(warning, warning);

			return;

		}

		Date makeDate = strToDate(date);

		if (makeDate == null) {

			String warning = "The date format is not right.";
			AlertBox a = new AlertBox();
			a.display(warning, warning);

			return;

		}

		if (voucher.getFaceValue() == 0) {

			sales.setVoucher(new VoucherVO());

		} else {

			sales.setVoucher(voucher);// �趨���ڵĴ���ȯ

		}

		SalesHelper helper = SalesHelper.getInstance();

		try {

			sales.setOperatorID(id);
			sales.setSalesType(SalesReceiptType.SALES);
			ResultMessage message = helper.getService().sendAudit(sales);

			if (message != ResultMessage.SUCCESS) {

				String warning = "Unexcepted bug occurs.";

				AlertBox a = new AlertBox();
				a.display(warning, warning);
				return;

			} else {
				AlertBox a = new AlertBox();
				a.display("�ύ�ɹ�", "�ύ�ɹ�");
				pStage.close();
			}

		} catch (RemoteException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

	}

	private ArrayList<MemberVO> getAllMember() {

		SalesHelper helper = SalesHelper.getInstance();

		try {

			ArrayList<MemberVO> memberList = helper.getService().getNowMembers(
					MemberType.RETAILER);// �������Ѿ���ǷǮ�����member�޳�����������

			return memberList;

		} catch (RemoteException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

		return new ArrayList<>();// �������ͨ�ŷ������⣬����һ���µ�ArrayList
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

	private static Date strToDate(String time) {// ���ַ���ת��ΪDate,�����ʽ���ԣ�����null

		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		ParsePosition pos = new ParsePosition(0);

		Date strtodate = date.parse(time, pos);

		return strtodate;

	}

}