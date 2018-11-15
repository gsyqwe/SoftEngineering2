package manganerui;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import mainframeui.AlertBox;
import rmi.PromotionStrategyHelper;
import rmi.SalesHelper;
import VO.CommodityVO;
import VO.LineItemVO;
import VO.PromotionByCombinationListVO;
import enums.ResultMessage;

public class PromotionByCombinationui {
	private static String j;
	private static String n;
	private static Stage pStage;
	private static ComboBox comboBox2;// ѡ����Ʒ
	private static TField t1;// ��һ����Ʒ
	private static TField t2;// �ڶ�����Ʒ
	private static TField t3;// ��������Ʒ
	private static TField t4;// ���ĸ���Ʒ
	private static TField tf11;// ��һ����Ʒ������
	private static TField tf21;// �ڶ�����Ʒ������
	private static TField tf31;// ��������Ʒ������
	private static TField tf41;// ���ĸ���Ʒ������
	private static TField t5;// �Ż�ǰ�ܼ�
	private static TField t6;// �Żݽ��
	private static TField t7;// �Żݺ��ܼ�
	private static TField tx1;// ���뿪ʼʱ��
	private static TField tx2;// �������ʱ��
	private static TField tx3;// �����Ʒ����
	private static String id;
	private static ArrayList<CommodityVO> mylist = new ArrayList<>();// �õ�������Ʒ��ArrayList
	private static ArrayList<LineItemVO> lineItems = new ArrayList<>();
	private static CommodityVO commodityChosen;// ѡ������Ʒ

	@SuppressWarnings("unchecked")
	public void display(String Job, String name, String ID) {
		// TODO Auto-generated method stub
		Stage primaryStage = new Stage();
		id = ID;
		PromotionByCombinationui p = new PromotionByCombinationui();
		p.getCommodityList();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("Promotion2ui.fxml"));
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
		@SuppressWarnings("rawtypes")
		ComboBox comboBox1 = new ComboBox();
		comboBox1
				.setStyle("-fx-border-color:Grey;-fx-border-radius:4;-fx-background-color:null");
		comboBox1.setPrefWidth(180.0);
		comboBox1.setLayoutX(94.0);
		comboBox1.setLayoutY(90.0);
		comboBox1.setValue("�ƶ���������        ");
		comboBox1.getItems()
				.addAll("�ƶ���ͬ�û��ȼ���������", "�ƶ�����Żݰ���������", "�ƶ��Ż��ܼ۴�������");
		comboBox1.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener() {
					@SuppressWarnings("rawtypes")
					@Override
					public void changed(ObservableValue arg0, Object arg1,
							Object arg2) {
						// TODO Auto-generated method stub
						if (arg2.toString() == "�ƶ���ͬ�û��ȼ���������") {// �޸�������Ӧ��Ҫ���µ�¼
							PromotionByLevelui de = new PromotionByLevelui();
							try {
								de.display(Job, name, id);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else if (arg2.toString() == "�ƶ�����Żݰ���������") {
							PromotionByCombinationui de = new PromotionByCombinationui();
							de.display(Job, name, id);
						} else if (arg2.toString() == "�ƶ��Ż��ܼ۴�������") {
							PromotionBySum de = new PromotionBySum();
							de.display(Job, name, id);
						}
					}
				});

		// ����comboBoxѡ����Ʒ
		@SuppressWarnings("rawtypes")
		ComboBox comboBox2 = new ComboBox();
		comboBox2.setPrefWidth(150.0);
		comboBox2.setLayoutX(429.0);
		comboBox2.setLayoutY(192.0);
		comboBox2
				.setStyle("-fx-border-color:Grey;-fx-border-radius:4;-fx-background-color:null");
		comboBox2.setValue("ѡ����Ʒ        ");
		for (int i = 0; i < mylist.size(); i++) {
			comboBox2.getItems().add(mylist.get(i).getName());
		}
		comboBox2.getItems().addAll();
		comboBox2.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener() {
					@Override
					public void changed(ObservableValue arg0, Object arg1,
							Object arg2) {
						// TODO Auto-generated method stub
						int index = comboBox2.getSelectionModel()
								.getSelectedIndex();
						if (t1.getText().equals("")) {
							t1.setText(comboBox2.getValue().toString());
						} else if (t2.getText().equals("")) {
							t2.setText(comboBox2.getValue().toString());
						} else if (t3.getText().equals("")) {
							t3.setText(comboBox2.getValue().toString());
						} else if (t4.getText().equals("")) {
							t4.setText(comboBox2.getValue().toString());
						}
					}
				});

		// ����Ʒ��textField��ѡ��һ��
		t1 = new TField();
		t1.setLayoutX(429.0);
		t1.setLayoutY(235.0);
		t1.setMinWidth(150.0);
		t1.setMaxWidth(150.0);
		t1.setMinHeight(30.0);
		t1.setMaxHeight(30.0);
		t1.setFont(Font.font("Verdana", 17));

		// t2ѡ�ڶ�����Ʒ
		t2 = new TField();
		t2.setLayoutX(429.0);
		t2.setLayoutY(272.0);
		t2.setMinWidth(150.0);
		t2.setMaxWidth(150.0);
		t2.setMinHeight(30);
		t2.setMaxHeight(30.0);
		t2.setFont(Font.font("Verdana", 17));

		// t3ѡ������
		t3 = new TField();
		t3.setLayoutX(429.0);
		t3.setLayoutY(309.0);
		t3.setMinWidth(150.0);
		t3.setMaxWidth(150.0);
		t3.setMinHeight(30.0);
		t3.setMaxHeight(30.0);
		t3.setFont(Font.font("Verdana", 17));

		// t4ѡ���ĸ�
		t4 = new TField();
		t4.setLayoutX(429.0);
		t4.setLayoutY(346.0);
		t4.setMinWidth(150.0);
		t4.setMaxWidth(150.0);
		t4.setMinHeight(30.0);
		t4.setMaxHeight(30.0);
		t4.setFont(Font.font("Verdana", 17));
		TField XIField = new TField();
		XIField.setEditable(false);
		XIField.setLayoutX(590);
		XIField.setLayoutY(192);
		XIField.setMinHeight(30);
		XIField.setMaxHeight(30);
		XIField.setPrefWidth(150);
		XIField.setFont(Font.font("Verdana", 17));
		XIField.setText("��Ʒ����");
		//
		tf11 = new TField();
		tf11.setLayoutX(590);
		tf11.setLayoutY(235);
		tf11.setPrefWidth(150);
		tf11.setMinHeight(30);
		tf11.setMaxHeight(30);
		tf11.setFont(Font.font("Verdana", 17));

		tf21 = new TField();
		tf21.setLayoutX(590);
		tf21.setLayoutY(272);
		tf21.setPrefWidth(150);
		tf21.setMinHeight(30);
		tf21.setMaxHeight(30);
		tf21.setFont(Font.font("Verdana", 17));

		tf31 = new TField();
		tf31.setLayoutX(590);
		tf31.setLayoutY(309);
		tf31.setPrefWidth(150);
		tf31.setMinHeight(30);
		tf31.setMaxHeight(30);
		tf31.setFont(Font.font("Verdana", 17));

		tf41 = new TField();
		tf41.setLayoutX(590);
		tf41.setLayoutY(346);
		tf41.setPrefWidth(150);
		tf41.setMinHeight(30);
		tf41.setMaxHeight(30);
		tf41.setFont(Font.font("Verdana", 17));

		// t5�Ż�ǰ�ܼ�
		t5 = new TField();
		t5.setLayoutX(108.0);
		t5.setLayoutY(310.0);
		t5.setMinWidth(150.0);
		t5.setMaxWidth(150.0);
		t5.setMinHeight(30.0);
		t5.setMaxHeight(30.0);
		t5.setFont(Font.font("Verdana", 17));
		t5.setEditable(false);
		t5.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				if (event.getClickCount() == 2) {
					double d = Math.random() * 5;
					t5.setText(Double.toString((double) 300.0 * d));
				}

			}

		});

		// t6�Żݽ��
		t6 = new TField();
		t6.setLayoutX(108.0);
		t6.setLayoutY(360.0);
		t6.setMinWidth(150.0);
		t6.setMaxWidth(150.0);
		t6.setMinHeight(30.0);
		t6.setMaxHeight(30.0);
		t6.setFont(Font.font("Verdana", 17));

		// t7�Żݺ��ܼ�
		t7 = new TField();
		t7.setLayoutX(108.0);
		t7.setLayoutY(406.0);
		t7.setMinWidth(150.0);
		t7.setMaxWidth(150.0);
		t7.setMinHeight(30.0);
		t7.setMaxHeight(30.0);
		t7.setFont(Font.font("Verdana", 17));
		t7.setEditable(false);
		// ��t5��˫���¼�����ʾ�����Ʒ�ܼ�
		t7.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() == 2) {
					// ��ʾ�ܼ۵��¼�
					String t = t6.getText();
					double d = Double.parseDouble(t);
					String t1 = t5.getText();
					double d1 = Double.parseDouble(t1);
					d1 = d1 - d;
					t7.setText(Double.toString(d1));
				}
			}
		});

		// ��ʼʱ��
		TField tf1 = new TField();
		tf1.setText("��ʼʱ��");
		tf1.setEditable(false);
		tf1.setLayoutX(14);
		tf1.setLayoutY(215);
		tf1.setPrefWidth(109);

		// ���뿪ʼʱ��
		tx1 = new TField();
		tx1.setLayoutX(14);
		tx1.setLayoutY(250);
		tx1.setPrefWidth(109);
		tx1.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				if (event.getClickCount() == 2) {

					// ���ʱ��

					Date day = new Date();

					SimpleDateFormat df = new SimpleDateFormat(

					"yyyy-MM-dd HH:mm");

					tx1.setText(df.format(day));

				}

			}

		});

		// ����ʱ��
		TField tf2 = new TField();
		tf2.setText("����ʱ��");
		tf2.setEditable(false);
		tf2.setLayoutX(123);
		tf2.setLayoutY(215);
		tf2.setPrefWidth(109);

		// �������ʱ��
		tx2 = new TField();
		tx2.setLayoutX(123);
		tx2.setLayoutY(250);
		tx2.setPrefWidth(109);
		tx2.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER) {
					// ���������ʾ���
				}
			}
		});
		tx2.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				if (event.getClickCount() == 2) {

					// ���ʱ��

					Date day = new Date();

					SimpleDateFormat df = new SimpleDateFormat(

					"yyyy-MM-dd HH:mm");

					tx2.setText(df.format(day));

				}

			}

		});

		// �����Ʒ����
		TField tf3 = new TField();
		tf3.setText("�����Ʒ����");
		tf3.setEditable(false);
		tf3.setLayoutX(232);
		tf3.setLayoutY(215);
		tf3.setPrefWidth(135);
		// ���������Ʒ����
		tx3 = new TField();
		tx3.setLayoutX(232);
		tx3.setLayoutY(250);
		tx3.setPrefWidth(135);
		a.getChildren().addAll(root, text1, text2, comboBox1, comboBox2, t1,
				t2, t3, t4, t5, t6, t7, tf1, tf2, tf3, tx1, tx2, tx3, tf11,
				tf21, tf31, tf41, XIField);
		Scene s = new Scene(a, 800, 500);
		primaryStage.setTitle("�ܾ���");
		primaryStage.setScene(s);
		pStage = primaryStage;
		j = Job;
		n = name;
		primaryStage.show();
	}

	@FXML
	private void HandleHelp(ActionEvent event) {
		HelpMan f = new HelpMan();
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
	private void HandleAdd(ActionEvent event) {// ��Ӵ�������
		AlertBox a = new AlertBox();
		a.display("��ӳɹ�", "��ӳɹ�");
		pStage.close();
		PromotionStrategyHelper helper = PromotionStrategyHelper.getInstance();
		try {
			ArrayList<CommodityVO> commoditys = helper.getService()
					.getCommodityList();// Ҫ������е�commodity�б�
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PromotionByCombinationListVO promotion = new PromotionByCombinationListVO();
		String begin = tx1.getText();
		String end = tx2.getText();
		Date beginDate = strToDate(begin);
		Date endDate = strToDate(end);
		if (begin == null || end == null) {
			String warning = "date format fault";
			AlertBox alert = new AlertBox();
			a.display(warning, warning);
			return;
		}
		promotion.setBeginTime(beginDate);
		promotion.setEndTime(endDate);
		if (tx3.getText() == null || tx3.getText().equals("")) {
			String warning = "The promotion name cannot be empty.";
			AlertBox alert = new AlertBox();
			a.display(warning, warning);
			return;
		}
		promotion.setPromotionName(tx3.getText());
		for (int i = 0; i < 4; i++) {
			if (commodityChosen != null) {// �Ҳ�֪��������жϵ�ǰ�ǲ���Ϊ�գ�������д
				String quantityInString = getQuantity(i);
				if (quantityInString == null || quantityInString.equals("")) {
					String warning = "the quantity cannot be empty.";
					AlertBox alert = new AlertBox();
					a.display(warning, warning);
					return;
				}
				LineItemVO newItem = new LineItemVO(commodityChosen.getId(),
						Integer.parseInt(quantityInString),
						commodityChosen.getRetailPrice(), "");
				lineItems.add(newItem);
			} else {
				break;
			}
		}
		promotion.setCombination(lineItems);

		if (t6.getText() == null || t6.getText().equals("")) {
			String warning = "The discount cannot be empty.";
			AlertBox alert = new AlertBox();
			a.display(warning, warning);
			return;
		}
		double discount = Double.parseDouble(t6.getText());

		if (t7.getText() == null || t7.getText().equals("")) {
			String warning = "The Price after cannot be empty.";
			AlertBox alert = new AlertBox();
			a.display(warning, warning);
			return;
		}

		promotion.setPriceAfter(Double.parseDouble(t7.getText()));
		helper = PromotionStrategyHelper.getInstance();
		try {
			ResultMessage message = helper.getService()
					.completeAddPromotionByCombinationList(promotion);
			if (message == ResultMessage.PROMOTION_ADD_FALL) {
				String warning = "Data add fault!";
				AlertBox alert = new AlertBox();
				a.display(warning, warning);
				return;
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static Date strToDate(String time) {// ���ַ���ת��ΪDate,�����ʽ���ԣ�����null
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = date.parse(time, pos);
		return strtodate;
	}

	private String getQuantity(int i) {
		switch (i) {
		case 0:
			return tf11.getText();
		case 1:
			return tf21.getText();
		case 2:
			return tf31.getText();
		case 3:
			return tf41.getText();
		default:
			return "";
		}
	}

	private void getCommodityList() {// �õ�������Ʒ���б�
		SalesHelper helper = SalesHelper.getInstance();
		try {
			mylist = helper.getService().getCommodities();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}