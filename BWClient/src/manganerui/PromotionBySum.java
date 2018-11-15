package manganerui;

import java.io.IOException;
import java.rmi.RemoteException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import mainframeui.AlertBox;
import rmi.PromotionStrategyHelper;
import rmi.SalesHelper;
import VO.CommodityVO;
import VO.LineItemVO;
import VO.PromotionBySumVO;
import VO.VoucherVO;
import enums.ResultMessage;

public class PromotionBySum {
	static String j;
	static String n;
	static Stage pStage;
	private static String id;
	private static ToggleGroup group;
	private static RadioButton r1;// ����
	private static RadioButton r2;// ������
	private static TField tx[] = new TField[6];// �����Ż������ܼ�
	private static ComboBox com[] = new ComboBox[6];// ѡ����Ʒ����
	private static TField tx2[] = new TField[6];// ������Ʒ����
	private static TField com1[] = new TField[6];// ѡ�����ȯ��ֵ,����ȯ��ֵ�������
	private static TField tx3[] = new TField[6];// �������ȯ����
	private static TField tx4[] = new TField[6];// �������ȯʧЧʱ��
	private static TField tf1;// ��ʼʱ��
	private static TField tf2;// ����ʱ��
	private static ArrayList<CommodityVO> mylist = new ArrayList<>();
	private static CommodityVO nowCommodity;// �ӽ���������ѡ���commodity

	@SuppressWarnings("unchecked")
	public void display(String Job, String name, String ID) {
		// TODO Auto-generated method stub
		Stage primaryStage = new Stage();
		id = ID;
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("Promotion3ui.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PromotionBySum p = new PromotionBySum();
		p.getCommodityList();

		group = new ToggleGroup();
		r1 = new RadioButton("����");
		r2 = new RadioButton("������");
		r2.setSelected(true);
		r1.setToggleGroup(group);
		r2.setToggleGroup(group);
		r1.setLayoutX(640);
		r1.setLayoutY(130);
		r2.setLayoutX(720);
		r2.setLayoutY(130);

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
		comboBox1.setMinHeight(30.0);
		comboBox1.setMaxHeight(30.0);
		comboBox1.setLayoutX(94.0);
		comboBox1.setLayoutY(90.0);
		comboBox1.setValue("�ƶ���������        ");
		comboBox1.getItems()
				.addAll("�ƶ���ͬ�û��ȼ���������", "�ƶ�����Żݰ���������", "�ƶ��Ż��ܼ۴�������");
		comboBox1.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener() {
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

		ScrollPane sc = new ScrollPane();
		sc.setLayoutX(50.0);
		sc.setLayoutY(203.0);
		sc.setMaxWidth(690.0);
		sc.setMinWidth(690.0);
		sc.setMinHeight(230.0);
		sc.setMaxHeight(230.0);
		AnchorPane a1 = new AnchorPane();
		a1.setMaxHeight(245.0);
		a1.setMinHeight(245.0);
		a1.setPrefWidth(670.0);
		sc.setContent(a1);

		ImageView i1 = new ImageView(new Image("/backgroud.png", true));
		i1.setFitWidth(670);
		i1.setFitHeight(245);
		a1.getChildren().add(i1);

		// �Ż������ܼ�
		TField t = new TField();
		t.setText("�Ż������ܼ�");
		t.setLayoutX(0);
		t.setLayoutY(0);
		t.setEditable(false);
		t.setPrefWidth(140);
		t.setFont(Font.font(17));
		// �����Ż������ܼ�
		for (int i = 0; i < 6; i++) {
			tx[i] = new TField();
			tx[i].setLayoutX(0);
			tx[i].setLayoutY(35 * (i + 1));
			tx[i].setPrefWidth(140);
			tx[i].setFont(Font.font(17));
			a1.getChildren().add(tx[i]);
		}
		// ��Ʒ��������comboBox
		TField t1 = new TField();
		t1.setText("��Ʒ����");
		t1.setLayoutX(140);
		t1.setLayoutY(0);
		t1.setEditable(false);
		t1.setPrefWidth(115);
		t1.setFont(Font.font(17));
		// ѡ����Ʒ����
		for (int i = 0; i < 6; i++) {
			com[i] = new ComboBox();
			com[i].setStyle("-fx-border-color:Grey;-fx-border-radius:4;-fx-background-color:null");
			com[i].setLayoutX(140);
			com[i].setLayoutY(35 * (i + 1));
			com[i].setMinHeight(35);
			com[i].setMaxHeight(35);
			com[i].setPrefWidth(115);
			for (int j = 0; j < mylist.size(); j++) {
				com[i].getItems().add(mylist.get(j).getName());
			}
			a1.getChildren().add(com[i]);
		}
		// ��Ʒ����
		TField t2 = new TField();
		t2.setText("��Ʒ����");
		t2.setLayoutX(255);
		t2.setLayoutY(0);
		t2.setEditable(false);
		t2.setMinHeight(35);
		t2.setMaxHeight(35);
		t2.setPrefWidth(125);
		t2.setFont(Font.font(17));
		// ������Ʒ����
		for (int i = 0; i < 6; i++) {
			tx2[i] = new TField();
			tx2[i].setLayoutX(255);
			tx2[i].setLayoutY(35 * (i + 1));
			tx2[i].setPrefWidth(125);
			tx2[i].setFont(Font.font(17));
			a1.getChildren().add(tx2[i]);
		}
		// ����ȯ�������comboBox
		TField t3 = new TField();
		t3.setText("����ȯ���");
		t3.setLayoutX(380);
		t3.setLayoutY(0);
		t3.setEditable(false);
		t3.setPrefWidth(145);
		t3.setFont(Font.font(17));
		// ѡ�����ȯ��ֵ
		for (int i = 0; i < 6; i++) {
			com1[i] = new TField();
			com1[i].setStyle("-fx-border-color:Grey;-fx-border-radius:4;-fx-background-color:null");
			com1[i].setMinHeight(35);
			com1[i].setMaxHeight(35);
			com1[i].setPrefWidth(145);
			com1[i].setLayoutX(380);
			com1[i].setLayoutY(35 * (i + 1));
			a1.getChildren().add(com1[i]);
		}
		// ����ȯ����
		TField t4 = new TField();
		t4.setText("����ȯ����");
		t4.setLayoutX(525);
		t4.setLayoutY(0);
		t4.setEditable(false);
		t4.setPrefWidth(145);
		t4.setFont(Font.font(17));
		// �������ȯ����
		for (int i = 0; i < 6; i++) {
			tx3[i] = new TField();
			tx3[i].setPrefWidth(145);
			tx3[i].setLayoutX(525);
			tx3[i].setLayoutY(35 * (i + 1));
			a1.getChildren().add(tx3[i]);
		}
		a1.getChildren().addAll(t, t1, t2, t3, t4);

		HBox h = new HBox();
		h.setSpacing(10);
		h.getChildren().addAll(r1, r2);
		h.setLayoutX(541);
		h.setLayoutY(160);

		// ��ʼʱ��
		tf1 = new TField();
		tf1.setMinHeight(30);
		tf1.setMaxHeight(30);
		tf1.setPrefWidth(144);
		tf1.setLayoutX(170);
		tf1.setLayoutY(167);
		// ����ʱ��
		tf2 = new TField();
		tf2.setMinHeight(30);
		tf2.setMaxHeight(30);
		tf2.setPrefWidth(144);
		tf2.setLayoutX(559);
		tf2.setLayoutY(167);

		tf1.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				if (event.getClickCount() == 2) {

					// ���ʱ��

					Date day = new Date();

					SimpleDateFormat df = new SimpleDateFormat(

					"yyyy-MM-dd HH:mm");

					tf1.setText(df.format(day));

				}

			}

		});
		tf2.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				if (event.getClickCount() == 2) {

					// ���ʱ��

					Date day = new Date();

					SimpleDateFormat df = new SimpleDateFormat(

					"yyyy-MM-dd HH:mm");

					tf2.setText(df.format(day));

				}

			}

		});

		a.getChildren().addAll(root, text1, text2, comboBox1, sc, h, tf1, tf2,
				r1, r2);

		Scene s = new Scene(a, 800, 500);
		primaryStage.setTitle("�ܾ���");
		primaryStage.setScene(s);
		j = Job;
		n = name;
		pStage = primaryStage;
		primaryStage.show();
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

		AlertBox alert = new AlertBox();
		alert.display("��ӳɹ�", "��ӳɹ�");
		pStage.close();

		boolean isSelete = r1.isSelected();
		String beginTime = tf1.getText();
		String endTime = tf2.getText();
		Date begin = strToDate(beginTime);
		Date end = strToDate(endTime);

		if (begin == null || end == null) {
			String warning = "date format fault";
			AlertBox a = new AlertBox();
			a.display(warning, warning);
			return;
		}

		PromotionStrategyHelper helper = PromotionStrategyHelper.getInstance();
		ArrayList<CommodityVO> commoditys = new ArrayList<>();
		try {
			commoditys = helper.getService().getCommodityList();// Ҫ������е�commodity�б�
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		ArrayList<PromotionBySumVO> promotionList = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			if (!tx[i].getText().equals("")) {
				PromotionBySumVO now = new PromotionBySumVO();
				boolean existsBefore = false;
				double min = Double.parseDouble(tx[i].getText());
				for (PromotionBySumVO promotion : promotionList) {
					if (promotion.getMinAmount() == min) {
						existsBefore = true;
						now = promotion;
						break;
					}
				}

				if (!existsBefore) {
					now = new PromotionBySumVO();
				}

				now.setStartTime(begin);
				now.setEndTime(end);
				now.setMinAmount(min);
				if (isSelete) {// ����
					now.setMaxAmount(Double.MAX_VALUE);// ������Ӵ������ͽ��������ΪMax
				}

				String commodityID = nowCommodity.getId();
				double price = nowCommodity.getBid();

				if (tx2[i].getText() == null || tx2[i].getText().equals("")) {
					String warning = "Must write down the quantity of the commodity.";
					return;
				}
				int quantity = Integer.parseInt(tx2[i].getText());
				if (quantity <= 0) {
					String warning = "The quantity can not be negitive or zero.";
					return;
				}

				LineItemVO item = new LineItemVO(commodityID, quantity, price,
						"");
				now.addGift(item);

				ArrayList<VoucherVO> voucherInLine = new ArrayList<>();
				int times = 0;

				if (!com[i].getValue().toString().equals("")) {
					if (tx3[i].getText() == null || tx3[i].getText().equals("")) {
						String warning = "Must write down the quantity of the voucher.";
						AlertBox a = new AlertBox();
						a.display(warning, warning);
						return;
					}

					times = Integer.parseInt(tx3[i].getText());
					if (times <= 0) {
						String warning = "The quantity of the voucher can't be negative.";
						AlertBox a = new AlertBox();
						a.display(warning, warning);
						return;
					}

					for (int j = 0; j < times; j++) {
						VoucherVO voucher = new VoucherVO();
						if (Double.parseDouble(com1[1].getText().toString()) <= 0) {
							String warning = "The value of the voucher cannot be negative.";
							AlertBox a = new AlertBox();
							a.display(warning, warning);
						}
						voucher.setFaceValue(Double.parseDouble(com1[i]
								.getPromptText()));
						voucher.setStartTime(begin);// ��ʼʱ��Ĭ��Ϊ�������ԵĿ�ʼʱ��
						String effectiveTime = tx4[i].getText();
						if (effectiveTime == null || effectiveTime.equals("")) {
							String warning = "The end time of the voucher cannot be empty.";
							AlertBox a = new AlertBox();
							a.display(warning, warning);
							return;
						} else {
							Date effectiveDate = strToDate(effectiveTime);
							if (effectiveDate == null) {
								String warning = "The date format is wrong.";
								AlertBox a = new AlertBox();
								a.display(warning, warning);
								return;
							}

							voucher.setEndTime(effectiveDate);
						}

						voucherInLine.add(voucher);
					}
				}

				ArrayList<VoucherVO> allVoucherList = now.getVoucherList();
				allVoucherList.addAll(voucherInLine);
				now.setVoucherList(allVoucherList);

				if (!existsBefore) {
					promotionList.add(now);// ���֮ǰû����ӹ����ټ���
				}
			} else {
				Collections.sort(promotionList,
						new Comparator<PromotionBySumVO>() {
							@Override
							public int compare(PromotionBySumVO fruit1,
									PromotionBySumVO fruit2) {

								return Double.compare(fruit1.getMinAmount(),
										fruit2.getMinAmount());
							}
						});// ��promotion����minAmount��С��������

				if (!isSelete) {
					for (int j = 0; j < promotionList.size() - 1; j++) {
						promotionList.get(j).setMaxAmount(
								promotionList.get(j + 1).getMinAmount());// Ϊÿ��promotion�趨maxAmount
					}

					promotionList.get(promotionList.size() - 1).setMaxAmount(
							Double.MAX_VALUE);// ������Ǹ�promotion��maxAmount�趨Ϊmax
				}

				for (PromotionBySumVO promotion : promotionList) {
					try {
						ResultMessage message = helper.getService()
								.completeAddPromotionBySum(promotion);
						if (message == ResultMessage.PROMOTION_ADD_FALL) {
							String warning = "The data operate fault.";
							AlertBox a = new AlertBox();
							a.display(warning, warning);
							return;
						}
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			}
		}
	}

	private static Date strToDate(String time) {// ���ַ���ת��ΪDate,�����ʽ���ԣ�����null
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = date.parse(time, pos);
		return strtodate;
	}

}
