package financelerui;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import mainframeui.TField;
import rmi.BusinessConditionHelper;
import VO.BusinessConditionVO;
import export.implement.Export;
import export.interpreter.ExportInterpreter;

public class businessconditionui {
	private static String j;
	private static String n;
	private static Stage pStage;
	private static String id;
	private static TField tf1;// ��ʼʱ��
	private static TField tf2;// ����ʱ��
	private static PieChart pchart;// ��״ͼ
	private static StackedBarChart bchart;// ��״ͼ
	private static RadioButton r1;// ��״ͼ
	private static RadioButton r2;// ��״ͼ
	private static ToggleGroup group;// ѡ����
	private static CategoryAxis xAxis = new CategoryAxis();
	private static NumberAxis yAxis = new NumberAxis();
	final XYChart.Series<String, Number> series1 = new XYChart.Series<>();
	final XYChart.Series<String, Number> series2 = new XYChart.Series<>();// û��ֻ�Ǹı�һ����ɫ
	final XYChart.Series<String, Number> series3 = new XYChart.Series<>();// û��ֻ�Ǹı�һ����ɫ
	private static BusinessConditionVO business;// �õ���businessConditionVO

	@SuppressWarnings("unchecked")
	public void display(String Job, String name, String ID) {
		// TODO Auto-generated method stub
		AnchorPane a = new AnchorPane();
		id = ID;
		businessconditionui businesss = new businessconditionui();
		business = new BusinessConditionVO(57, 120, 213, 320, 430, 120, 134,
				145);
		Stage primaryStage = new Stage();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource(
					"businessconditionui.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		r1 = new RadioButton("��״ͼ");
		r2 = new RadioButton("��״ͼ");
		group = new ToggleGroup();
		r1.setSelected(true);
		r1.setToggleGroup(group);
		r2.setToggleGroup(group);
		r1.setLayoutX(719);
		r1.setLayoutY(200);
		r2.setLayoutX(645);
		r2.setLayoutY(200);

		a.getChildren().addAll(root, r1, r2);

		// ComboBox
		@SuppressWarnings("rawtypes")
		ComboBox comboBox1 = new ComboBox();
		comboBox1.setValue("��������");
		comboBox1.setLayoutX(55.0);
		comboBox1.setLayoutY(160.0);
		comboBox1.minWidth(150.0);
		comboBox1.maxWidth(150.0);
		comboBox1.getItems().addAll("��Ӫ״����", "��Ӫ���̱�", "������ϸ��");
		comboBox1.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener() {
					@Override
					public void changed(ObservableValue arg0, Object arg1,
							Object arg2) {
						// TODO Auto-generated method stub
						if (arg2.toString() == "��Ӫ״����") {// �޸�������Ӧ��Ҫ���µ�¼
							businessconditionui b = new businessconditionui();
							primaryStage.close();
							b.display(Job, name, id);
						} else if (arg2.toString() == "��Ӫ���̱�") {
							businessprocessui b = new businessprocessui();
							primaryStage.close();
							b.display(Job, name, id);
						} else if (arg2.toString() == "������ϸ��") {
							salelistui s = new salelistui();
							primaryStage.close();
							s.display(Job, name, id);
						}
					}
				});
		// ������״ͼ
		ObservableList<PieChart.Data> pieChartData = FXCollections
				.observableArrayList(
						new PieChart.Data("��������", business.getSaleIncome()),
						new PieChart.Data("��Ʒ��������", business
								.getCommidityOverFlowIncome()),
						new PieChart.Data("�ɱ���������", business
								.getCommidityCostChangeIncome()),
						new PieChart.Data("����ȯ��ʵ�ʽ��������", business
								.getVoucherActualDifferenceIncome()),
						new PieChart.Data("���ú�������", business
								.getIncomeAfterdiscount()),
						new PieChart.Data("���۳ɱ�", business.getSaleCost()),
						new PieChart.Data("��Ʒ����", business
								.getCommidityLostCost()),
						new PieChart.Data("��Ʒ����", business
								.getCommidityGiftCost()),
						new PieChart.Data("�����˻��������", business
								.getBuySaleDifferenceIncome()),
						new PieChart.Data("��֧��", business.getSumOfCost()),
						new PieChart.Data("����", business
								.getIncomeAfterdiscount()
								- business.getSumOfCost()));
		pchart = new PieChart(pieChartData);
		pchart.setLegendVisible(false);
		pchart.setTitle("��Ӫ�����");
		pchart.setMinHeight(440);
		pchart.setMaxHeight(440);
		pchart.setMinWidth(700);
		pchart.setMaxWidth(700);
		pchart.setLayoutX(130);
		pchart.setLayoutY(285);

		final Label caption = new Label("");
		caption.setTextFill(Color.WHITE);
		caption.setStyle("-fx-font: 24 arial;");

		for (final PieChart.Data data : pchart.getData()) {
			data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
					new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent e) {
							caption.setTranslateX(e.getSceneX());
							caption.setTranslateY(e.getSceneY());
							caption.setText(String.valueOf(
									100 * data.getPieValue() / 1100).substring(
									0, 5)
									+ "%");
						}
					});
		}

		// ������״ͼ
		bchart = new StackedBarChart(xAxis, yAxis);
		bchart.setTitle("��Ӫ�����");
		xAxis.setCategories(FXCollections.<String> observableArrayList(Arrays
				.asList("��������", "��������", "��������", "����ȯ����", "������", "���۳ɱ�", "����֧��",
						"����֧��", "���˻�����", "��֧��", "����")));
		yAxis.setLabel("Value");
		bchart.setMinWidth(800);
		bchart.setMaxWidth(800);
		bchart.setMinHeight(440);
		bchart.setMaxHeight(440);
		bchart.setLayoutX(100);
		bchart.setLayoutY(250);
		bchart.setVisible(false);

		// // �������
		series1.getData().add(
				new XYChart.Data<>("��������", business.getSaleIncome()));
		series1.getData().add(
				new XYChart.Data<>("��������", business
						.getCommidityOverFlowIncome()));
		series1.getData().add(
				new XYChart.Data<>("��������", business
						.getCommidityCostChangeIncome()));
		series1.getData().add(
				new XYChart.Data<>("����ȯ����", business
						.getVoucherActualDifferenceIncome()));
		series1.getData().add(
				new XYChart.Data<>("������",
						business.getIncomeAfterdiscount() + 100));
		series1.getData().add(
				new XYChart.Data<>("���۳ɱ�", business.getSaleCost()));
		series1.getData().add(
				new XYChart.Data<>("����֧��", business.getCommidityLostCost()));
		series1.getData().add(
				new XYChart.Data<>("����֧��", business.getCommidityGiftCost()));
		series1.getData().add(
				new XYChart.Data<>("���˻�����", business
						.getBuySaleDifferenceIncome()));
		series1.getData().add(
				new XYChart.Data<>("��֧��", business.getSumOfCost() + 100));
		series1.getData().add(
				new XYChart.Data<>("����", business.getIncomeAfterdiscount()
						- business.getSumOfCost() + 100));
		bchart.getData().addAll(series1);
		bchart.setLegendVisible(false);

		a.getChildren().addAll(pchart, caption);
		TextField text1 = new TextField();
		text1.setStyle("-fx-background-color:null");
		text1.setText("��ӭ��:" + name);
		text1.setLayoutX(900.0);
		text1.setLayoutY(40.0);
		text1.setMinHeight(36.0);
		text1.setMaxHeight(36.0);
		text1.setFont(Font.font("Verdana", 18));
		text1.prefWidth(185.0);
		text1.setEditable(false);

		TextField text2 = new TextField();
		text2.setStyle("-fx-background-color:null");
		text2.setText("��ǰְ��:" + Job);
		text2.setLayoutX(900.0);
		text2.setLayoutY(68.0);
		text2.setMinHeight(30.0);
		text2.setMaxHeight(30.0);
		text2.setFont(Font.font("Verdana", 18));
		text2.prefWidth(185.0);
		text2.setEditable(false);

		// ��ʼʱ��
		tf1 = new TField();
		tf1.setLayoutX(140);
		tf1.setLayoutY(200);
		tf1.setPrefWidth(153);
		tf1.setMinHeight(30);
		tf1.setMaxHeight(30);
		// ����ʱ��
		tf2 = new TField();
		tf2.setLayoutX(210);
		tf2.setLayoutY(248);
		tf2.setPrefWidth(100);
		tf2.setMinHeight(30);
		tf2.setMaxHeight(30);
		// ��ʾʱ��
		tf1.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() == 2) {
					Date day = new Date();
					SimpleDateFormat df = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm");
					tf1.setText(df.format(day));
				}
			}
		});
		// ��ʾʱ��
		tf2.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() == 2) {
					Date day = new Date();
					SimpleDateFormat df = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm");
					tf2.setText(df.format(day));
				}
			}
		});

		// ����r1,r2���¼�
		// ѡ���¼�
		group.selectedToggleProperty().addListener(
				new ChangeListener<Toggle>() {
					@Override
					public void changed(ObservableValue<? extends Toggle> ov,
							Toggle old_toggle, Toggle new_toggle) {
						if (r1.isSelected()) {
							pchart.setVisible(true);
							bchart.setVisible(false);
						} else if (r2.isSelected()) {
							pchart.setVisible(false);
							bchart.setVisible(true);
						}
					}
				});

		// ������ʱ�������������¼�
		tf2.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER) {
					// �����¼�
					double d = (Math.random() % 5);
					business = new BusinessConditionVO(123 * d, 234 * d,
							985 * d, 434 * d, 654 * d, 11 * d, 108 * d, 556 * d);

					series1.getData()
							.add(new XYChart.Data<>("��������", business
									.getSaleIncome()));
					series1.getData().add(
							new XYChart.Data<>("��������", business
									.getCommidityOverFlowIncome()));
					series1.getData().add(
							new XYChart.Data<>("��������", business
									.getCommidityCostChangeIncome()));
					series1.getData().add(
							new XYChart.Data<>("����ȯ����", business
									.getVoucherActualDifferenceIncome()));
					series1.getData().add(
							new XYChart.Data<>("������", business
									.getIncomeAfterdiscount() + 100));
					series1.getData().add(
							new XYChart.Data<>("���۳ɱ�", business.getSaleCost()));
					series1.getData().add(
							new XYChart.Data<>("����֧��", business
									.getCommidityLostCost()));
					series1.getData().add(
							new XYChart.Data<>("����֧��", business
									.getCommidityGiftCost()));
					series1.getData().add(
							new XYChart.Data<>("���˻�����", business
									.getBuySaleDifferenceIncome()));
					series1.getData().add(
							new XYChart.Data<>("��֧��",
									business.getSumOfCost() + 100));
					series1.getData().add(
							new XYChart.Data<>("����", business
									.getIncomeAfterdiscount()
									- business.getSumOfCost() + 100));

					// pchart����ʾ
					ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
							new PieChart.Data("��������", business.getSaleIncome()),
							new PieChart.Data("��Ʒ��������", business
									.getCommidityOverFlowIncome()),
							new PieChart.Data("�ɱ���������", business
									.getCommidityCostChangeIncome()),
							new PieChart.Data("����ȯ��ʵ�ʽ��������", business
									.getVoucherActualDifferenceIncome()),
							new PieChart.Data("���ú�������", business
									.getIncomeAfterdiscount()),
							new PieChart.Data("���۳ɱ�", business.getSaleCost()),
							new PieChart.Data("��Ʒ����", business
									.getCommidityLostCost()),
							new PieChart.Data("��Ʒ����", business
									.getCommidityGiftCost()),
							new PieChart.Data("�����˻��������", business
									.getBuySaleDifferenceIncome()),
							new PieChart.Data("��֧��", business.getSumOfCost()
									+ 100 * d),
							new PieChart.Data("������", 100 * d),
							new PieChart.Data("����", business
									.getIncomeAfterdiscount()
									- business.getSumOfCost() + 100 * d));
					pchart.setData(pieChartData);
				}
			}
		});
		a.getChildren().addAll(text1, text2, comboBox1, tf1, tf2, bchart);
		Scene s = new Scene(a, 1080, 800);
		primaryStage.setTitle("������Ա");
		primaryStage.setScene(s);
		j = Job;
		n = name;
		pStage = primaryStage;
		primaryStage.show();
	}

	@FXML
	private void HandleHelp(ActionEvent event) {
		HelpF a = new HelpF();
		a.display(j, n, id);
	}

	@FXML
	private void Handlesure(ActionEvent event) {
		pStage.close();
	}

	@FXML
	private void HandleCancle(ActionEvent event) {
		pStage.close();
	}

	@FXML
	private void HandleExport(ActionEvent event) {
		FileChooser filechooser = new FileChooser();
		filechooser.setTitle("����");
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
				"EXCEL files (*.excel)", "*.xlsx");
		filechooser.getExtensionFilters().add(extFilter);
		File file = filechooser.showSaveDialog(pStage);
		ExportInterpreter<BusinessConditionVO> interpreter = new ExportInterpreter<>(
				business);
		Export export = new Export(interpreter.getExportContent(),
				file.getPath());
		export.export();
	}

	@FXML
	private void HandleOut(ActionEvent event) {
		pStage.close();
	}

	@FXML
	private void HandleEsc(ActionEvent event) {
		pStage.close();
	}

	private static Date strToDate(String time) {// ���ַ���ת��ΪDate,�����ʽ���ԣ�����null
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = date.parse(time, pos);
		return strtodate;
	}

	private BusinessConditionVO getInitCondition() {
		Date nowDate = new Date();
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(nowDate);
		rightNow.add(Calendar.YEAR, -1);// ���ڼ�1��
		Date preDate = rightNow.getTime();

		BusinessConditionVO condition = new BusinessConditionVO();

		BusinessConditionHelper helper = BusinessConditionHelper.getInstance();
		try {
			System.out.println(preDate);
			System.out.println(nowDate);
			condition = helper.getService().getBusinessConditionList(preDate,
					nowDate);// �õ�businessCondition
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return condition;
	}

}
