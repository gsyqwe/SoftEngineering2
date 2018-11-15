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
import rmi.PromotionStrategyHelper;
import rmi.SalesHelper;
import VO.CommodityVO;
import VO.LineItemVO;
import VO.PromotionByLevelVO;
import VO.VoucherVO;
import enums.MemberVipLevel;
import enums.ResultMessage;

/**
 *
 */

public class PromotionByLevelui {
	private static String j;
	private static String n;
	private static Stage pStage;
	private static String id;
	private static TField tf1;// ��ʼʱ��
	private static TField tf2;// ����ʱ��
	private static ComboBox tf11[] = new ComboBox[7];// ��Ʒ����
	private static TField tf21[] = new TField[7];// ��Ʒ����
	private static TField tf3[] = new TField[7];// ����ȯ���
	private static TField tf4[] = new TField[7];// ����ȯ����
	private static TField tf5[] = new TField[7]; // �������ȯ����ʱ��
	private static TField tf6;// ���ý��
	private static ArrayList<CommodityVO> mylist = new ArrayList<>();// �õ�������Ʒ��ArrayList
	private static CommodityVO commodity = new CommodityVO();// ѡ������Ʒ

	@SuppressWarnings("unchecked")
	public void display(String Job, String name, String ID) {
		// TODO Auto-generated method stub
		id = ID;

		PromotionByLevelui p = new PromotionByLevelui();
		p.getCommodityList();

		ArrayList<MemberVipLevel> LevelList = new ArrayList<>();// �������е�Level,��ʾ��ʱ��Levelʹ��toString��������
		LevelList.add(MemberVipLevel.ORDINARY);
		LevelList.add(MemberVipLevel.BRONZE);
		LevelList.add(MemberVipLevel.SLIVER);
		LevelList.add(MemberVipLevel.GOLD);
		LevelList.add(MemberVipLevel.DIAMOND);

		Stage primaryStage = new Stage();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("Promotion1ui.fxml"));
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
		comboBox1.setPrefWidth(180.0);
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

		// ����ScrollPane
		ScrollPane s1 = new ScrollPane();
		s1.setPrefWidth(630);
		s1.setMinHeight(246);
		s1.setMaxHeight(246);
		s1.setLayoutX(85);
		s1.setLayoutY(196);

		AnchorPane a1 = new AnchorPane();
		a1.setMinHeight(280);
		a1.setMaxHeight(280);
		a1.setPrefWidth(608);

		ImageView i1 = new ImageView(new Image("/backgroud.png", true));
		i1.setFitWidth(280);
		i1.setFitHeight(630);
		a1.getChildren().add(i1);

		// ��Ʒ����
		TField tx1 = new TField();
		tx1.setText("��Ʒ����");
		tx1.setEditable(false);
		tx1.setLayoutX(0);
		tx1.setLayoutY(0);
		tx1.setPrefWidth(123);

		// ������Ʒ����
		for (int i = 0; i < 7; i++) {
			tf11[i] = new ComboBox();
			tf11[i].setLayoutX(0);
			tf11[i].setStyle("-fx-border-color:Grey;-fx-border-radius:4;-fx-background-color:null");
			tf11[i].setMinHeight(35);
			tf11[i].setMaxHeight(35);
			for (int j = 0; j < mylist.size(); j++) {
				tf11[i].getItems().add(mylist.get(j).getName());
			}
			tf11[i].setLayoutY(35 * (i + 1));
			tf11[i].setPrefWidth(123);
			a1.getChildren().add(tf11[i]);
		}

		// ��Ʒ����
		TField tx2 = new TField();
		tx2.setText("��Ʒ����");
		tx2.setLayoutX(123);
		tx2.setLayoutY(0);
		tx2.setPrefWidth(117);
		tx2.setEditable(false);

		// ������Ʒ����
		for (int i = 0; i < 7; i++) {
			tf21[i] = new TField();
			tf21[i].setLayoutX(123);
			tf21[i].setLayoutY(35 * (1 + i));
			tf21[i].setPrefWidth(117);
			a1.getChildren().add(tf21[i]);
		}

		// ����ȯ���
		TField tx3 = new TField();
		tx3.setText("����ȯ���");
		tx3.setLayoutX(240);
		tx3.setLayoutY(0);
		tx3.setPrefWidth(137);
		tx3.setEditable(false);
		// ѡ�����ȯ���
		for (int i = 0; i < 7; i++) {
			tf3[i] = new TField();
			tf3[i].setLayoutX(240);
			tf3[i].setMinHeight(35);
			tf3[i].setMaxHeight(35);
			tf3[i].setPrefWidth(137);
			tf3[i].setLayoutY(35 * (1 + i));
			a1.getChildren().add(tf3[i]);
		}

		// ����ȯ����
		TField tx4 = new TField();
		tx4.setText("����ȯ����");
		tx4.setLayoutX(377);
		tx4.setLayoutY(0);
		tx4.setPrefWidth(136);
		tx4.setEditable(false);

		// �������ȯ����
		for (int i = 0; i < 7; i++) {
			tf4[i] = new TField();
			tf4[i].setPrefWidth(136);
			tf4[i].setLayoutX(377);
			tf4[i].setLayoutY(35 * (1 + i));
			a1.getChildren().add(tf4[i]);
		}

		// ���ý��
		TField tx5 = new TField();
		tx5.setText("���ý��");
		tx5.setLayoutX(513);
		tx5.setLayoutY(0);
		tx5.setPrefWidth(95);
		tx5.setEditable(false);

		// �������ý��
		for (int i = 0; i < 7; i++) {
			tf5[i] = new TField();
			tf5[i].setPrefWidth(95);
			tf5[i].setLayoutX(513);
			tf5[i].setLayoutY(35 * (1 + i));
			a1.getChildren().add(tf5[i]);
		}
		a1.getChildren().addAll(tx1, tx2, tx3, tx4, tx5);
		s1.setContent(a1);

		// ��ʼʱ��
		tf1 = new TField();
		tf1.setMinHeight(30);
		tf1.setMaxHeight(30);
		tf1.setLayoutX(126);
		tf1.setLayoutY(154);
		tf1.setFont(Font.font(17));
		tf1.setPrefWidth(139);

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

		// ����ʱ��
		tf2 = new TField();
		tf2.setMinHeight(30);
		tf2.setMaxHeight(30);
		tf2.setLayoutX(360);
		tf2.setLayoutY(154);
		tf2.setFont(Font.font(17));
		tf2.setPrefWidth(139);

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

		// ѡ���û��ȼ�
		ComboBox com = new ComboBox();
		com.setStyle("-fx-border-color:Grey;-fx-border-radius:4;-fx-background-color:null");
		com.getItems().addAll(MemberVipLevel.ORDINARY, MemberVipLevel.GOLD,
				MemberVipLevel.BRONZE, MemberVipLevel.DIAMOND,
				MemberVipLevel.SLIVER);
		com.setMinHeight(30);
		com.setMaxHeight(30);
		com.setPrefWidth(139);
		com.setLayoutX(590);
		com.setLayoutY(154);

		// ȷ����ӵ�button
		Button b1 = new Button("ȷ�����");
		b1.setLayoutX(237.0);
		b1.setLayoutY(462.0);
		b1.setOnAction((ActionEvent e) -> { // ȷ����ӵ��¼�
			PromotionStrategyHelper helper = PromotionStrategyHelper
					.getInstance();
			AlertBox al = new AlertBox();
			al.display("��ӳɹ�", "��ӳɹ�");
			pStage.close();
			MemberVipLevel level = null;// ���������Ҫ������ѡ�е�memberVipLevel

			PromotionByLevelVO promotion = new PromotionByLevelVO();

			promotion.setLevel(level);
			String begin = tx1.getText();
			String end = tx2.getText();
			Date beginDate = strToDate(begin);
			Date endDate = strToDate(end);

			if (begin == null || end == null) {
				String warning = "date format fault";
				AlertBox alert = new AlertBox();
				alert.display(warning, warning);
				return;
			}

			promotion.setBeginDate(beginDate);
			promotion.setEndDate(endDate);

			String discountInString = tf6.getText();
			double discount = Double.parseDouble(discountInString);

			promotion.setDiscount(discount);
			for (int i = 0; i < 7; i++) {
				if (tf11[i].getValue().toString() == null
						|| tf11[i].getValue().toString().equals("")) {// ������
					try {
						ResultMessage message = helper.getService()
								.completeAddPromotionByLevel(promotion);
						if (message == ResultMessage.PROMOTION_ADD_FALL) {
							String warning = "The data operate fault.";
							AlertBox alert = new AlertBox();
							return;
						}
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					String commodityID = commodity.getId();
					double price = commodity.getBid();

					if (tf21[i].getText() == null
							|| tf21[i].getText().equals("")) {
						String warning = "Must write down the quantity of the commodity.";
						AlertBox alert = new AlertBox();
						alert.display(warning, warning);
						return;
					}
					int quantity = Integer.parseInt(tf21[i].getText());
					if (quantity <= 0) {
						String warning = "The quantity can not be negitive or zero.";
						AlertBox alert = new AlertBox();
						alert.display(warning, warning);
						return;
					}

					LineItemVO item = new LineItemVO(commodityID, quantity,
							price, "");
					ArrayList<LineItemVO> tem = promotion.getCommodityVOs();
					tem.add(item);
					promotion.setCommodityVOs(tem);// �����LineItem����

					ArrayList<VoucherVO> voucherInLine = new ArrayList<>();
					int times = 0;

					if (!tf3[i].getPromptText().equals("")) {
						if (tf4[i].getText() == null
								|| tf4[i].getText().equals("")) {
							String warning = "Must write down the quantity of the voucher.";
							return;
						}

						times = Integer.parseInt(tf4[i].getText());
						if (times <= 0) {
							String warning = "The quantity of the voucher can't be negative.";
							return;
						}

						for (int j = 0; j < times; j++) {
							VoucherVO voucher = new VoucherVO();
							if (Double.parseDouble(tf3[i].getPromptText()) <= 0) {
								String warning = "The value of the voucher cannot be negative.";
							}
							voucher.setFaceValue(Double.parseDouble(tf3[i]
									.getPromptText()));
							voucher.setStartTime(beginDate);// ��ʼʱ��Ĭ��Ϊ�������ԵĿ�ʼʱ��
							String effectiveTime = tf5[i].getText();
							if (effectiveTime == null
									|| effectiveTime.equals("")) {
								String warning = "The end time of the voucher cannot be empty.";
								return;
							} else {
								Date effectiveDate = strToDate(effectiveTime);
								if (effectiveDate == null) {
									String warning = "The date format is wrong.";
									return;
								}

								voucher.setEndTime(effectiveDate);
							}

							voucherInLine.add(voucher);
						}
					}

					ArrayList<VoucherVO> allVoucherList = promotion
							.getVoucherPOs();
					allVoucherList.addAll(voucherInLine);
					promotion.setVoucherVOs(allVoucherList);

				}

			}

		});
		a.getChildren().addAll(root, s1, text1, text2, comboBox1, b1, tf1, tf2,
				com);
		Scene s = new Scene(a, 800, 500);
		primaryStage.setTitle("�ܾ���");
		primaryStage.setScene(s);
		j = Job;
		n = name;
		pStage = primaryStage;
		primaryStage.show();
	}

	@FXML
	private void HandleDesign(ActionEvent event) {
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
	private void HandleOut(ActionEvent event) {
		pStage.close();
	}

	private static Date strToDate(String time) {// ���ַ���ת��ΪDate,�����ʽ���ԣ�����null
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = date.parse(time, pos);
		return strtodate;
	}

	@FXML
	private void HandleEsc(ActionEvent event) {
		pStage.close();
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