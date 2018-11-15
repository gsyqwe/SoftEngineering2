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
	private static TField tf1;// 开始时间
	private static TField tf2;// 结束时间
	private static PieChart pchart;// 饼状图
	private static StackedBarChart bchart;// 柱状图
	private static RadioButton r1;// 饼状图
	private static RadioButton r2;// 柱状图
	private static ToggleGroup group;// 选择组
	private static CategoryAxis xAxis = new CategoryAxis();
	private static NumberAxis yAxis = new NumberAxis();
	final XYChart.Series<String, Number> series1 = new XYChart.Series<>();
	final XYChart.Series<String, Number> series2 = new XYChart.Series<>();// 没用只是改变一个颜色
	final XYChart.Series<String, Number> series3 = new XYChart.Series<>();// 没用只是改变一个颜色
	private static BusinessConditionVO business;// 得到的businessConditionVO

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

		r1 = new RadioButton("饼状图");
		r2 = new RadioButton("柱状图");
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
		comboBox1.setValue("报表类型");
		comboBox1.setLayoutX(55.0);
		comboBox1.setLayoutY(160.0);
		comboBox1.minWidth(150.0);
		comboBox1.maxWidth(150.0);
		comboBox1.getItems().addAll("经营状况表", "经营历程表", "销售明细表");
		comboBox1.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener() {
					@Override
					public void changed(ObservableValue arg0, Object arg1,
							Object arg2) {
						// TODO Auto-generated method stub
						if (arg2.toString() == "经营状况表") {// 修改完密码应该要重新登录
							businessconditionui b = new businessconditionui();
							primaryStage.close();
							b.display(Job, name, id);
						} else if (arg2.toString() == "经营历程表") {
							businessprocessui b = new businessprocessui();
							primaryStage.close();
							b.display(Job, name, id);
						} else if (arg2.toString() == "销售明细表") {
							salelistui s = new salelistui();
							primaryStage.close();
							s.display(Job, name, id);
						}
					}
				});
		// 创建饼状图
		ObservableList<PieChart.Data> pieChartData = FXCollections
				.observableArrayList(
						new PieChart.Data("销售收入", business.getSaleIncome()),
						new PieChart.Data("商品报溢收入", business
								.getCommidityOverFlowIncome()),
						new PieChart.Data("成本调价收入", business
								.getCommidityCostChangeIncome()),
						new PieChart.Data("代金券与实际金额差价收入", business
								.getVoucherActualDifferenceIncome()),
						new PieChart.Data("折让后总收入", business
								.getIncomeAfterdiscount()),
						new PieChart.Data("销售成本", business.getSaleCost()),
						new PieChart.Data("商品报损", business
								.getCommidityLostCost()),
						new PieChart.Data("商品赠送", business
								.getCommidityGiftCost()),
						new PieChart.Data("进货退货差价收入", business
								.getBuySaleDifferenceIncome()),
						new PieChart.Data("总支出", business.getSumOfCost()),
						new PieChart.Data("利润", business
								.getIncomeAfterdiscount()
								- business.getSumOfCost()));
		pchart = new PieChart(pieChartData);
		pchart.setLegendVisible(false);
		pchart.setTitle("经营情况表");
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

		// 创建柱状图
		bchart = new StackedBarChart(xAxis, yAxis);
		bchart.setTitle("经营情况表");
		xAxis.setCategories(FXCollections.<String> observableArrayList(Arrays
				.asList("销售收入", "报溢收入", "调价收入", "代金券收入", "总收入", "销售成本", "报损支出",
						"赠送支出", "进退货收入", "总支出", "利润")));
		yAxis.setLabel("Value");
		bchart.setMinWidth(800);
		bchart.setMaxWidth(800);
		bchart.setMinHeight(440);
		bchart.setMaxHeight(440);
		bchart.setLayoutX(100);
		bchart.setLayoutY(250);
		bchart.setVisible(false);

		// // 添加数据
		series1.getData().add(
				new XYChart.Data<>("销售收入", business.getSaleIncome()));
		series1.getData().add(
				new XYChart.Data<>("报溢收入", business
						.getCommidityOverFlowIncome()));
		series1.getData().add(
				new XYChart.Data<>("调价收入", business
						.getCommidityCostChangeIncome()));
		series1.getData().add(
				new XYChart.Data<>("代金券收入", business
						.getVoucherActualDifferenceIncome()));
		series1.getData().add(
				new XYChart.Data<>("总收入",
						business.getIncomeAfterdiscount() + 100));
		series1.getData().add(
				new XYChart.Data<>("销售成本", business.getSaleCost()));
		series1.getData().add(
				new XYChart.Data<>("报损支出", business.getCommidityLostCost()));
		series1.getData().add(
				new XYChart.Data<>("赠送支出", business.getCommidityGiftCost()));
		series1.getData().add(
				new XYChart.Data<>("进退货收入", business
						.getBuySaleDifferenceIncome()));
		series1.getData().add(
				new XYChart.Data<>("总支出", business.getSumOfCost() + 100));
		series1.getData().add(
				new XYChart.Data<>("利润", business.getIncomeAfterdiscount()
						- business.getSumOfCost() + 100));
		bchart.getData().addAll(series1);
		bchart.setLegendVisible(false);

		a.getChildren().addAll(pchart, caption);
		TextField text1 = new TextField();
		text1.setStyle("-fx-background-color:null");
		text1.setText("欢迎您:" + name);
		text1.setLayoutX(900.0);
		text1.setLayoutY(40.0);
		text1.setMinHeight(36.0);
		text1.setMaxHeight(36.0);
		text1.setFont(Font.font("Verdana", 18));
		text1.prefWidth(185.0);
		text1.setEditable(false);

		TextField text2 = new TextField();
		text2.setStyle("-fx-background-color:null");
		text2.setText("当前职务:" + Job);
		text2.setLayoutX(900.0);
		text2.setLayoutY(68.0);
		text2.setMinHeight(30.0);
		text2.setMaxHeight(30.0);
		text2.setFont(Font.font("Verdana", 18));
		text2.prefWidth(185.0);
		text2.setEditable(false);

		// 开始时间
		tf1 = new TField();
		tf1.setLayoutX(140);
		tf1.setLayoutY(200);
		tf1.setPrefWidth(153);
		tf1.setMinHeight(30);
		tf1.setMaxHeight(30);
		// 结束时间
		tf2 = new TField();
		tf2.setLayoutX(210);
		tf2.setLayoutY(248);
		tf2.setPrefWidth(100);
		tf2.setMinHeight(30);
		tf2.setMaxHeight(30);
		// 显示时间
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
		// 显示时间
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

		// 设置r1,r2的事件
		// 选择事件
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

		// 给结束时间的输入框设置事件
		tf2.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER) {
					// 搜索事件
					double d = (Math.random() % 5);
					business = new BusinessConditionVO(123 * d, 234 * d,
							985 * d, 434 * d, 654 * d, 11 * d, 108 * d, 556 * d);

					series1.getData()
							.add(new XYChart.Data<>("销售收入", business
									.getSaleIncome()));
					series1.getData().add(
							new XYChart.Data<>("报溢收入", business
									.getCommidityOverFlowIncome()));
					series1.getData().add(
							new XYChart.Data<>("调价收入", business
									.getCommidityCostChangeIncome()));
					series1.getData().add(
							new XYChart.Data<>("代金券收入", business
									.getVoucherActualDifferenceIncome()));
					series1.getData().add(
							new XYChart.Data<>("总收入", business
									.getIncomeAfterdiscount() + 100));
					series1.getData().add(
							new XYChart.Data<>("销售成本", business.getSaleCost()));
					series1.getData().add(
							new XYChart.Data<>("报损支出", business
									.getCommidityLostCost()));
					series1.getData().add(
							new XYChart.Data<>("赠送支出", business
									.getCommidityGiftCost()));
					series1.getData().add(
							new XYChart.Data<>("进退货收入", business
									.getBuySaleDifferenceIncome()));
					series1.getData().add(
							new XYChart.Data<>("总支出",
									business.getSumOfCost() + 100));
					series1.getData().add(
							new XYChart.Data<>("利润", business
									.getIncomeAfterdiscount()
									- business.getSumOfCost() + 100));

					// pchart的显示
					ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
							new PieChart.Data("销售收入", business.getSaleIncome()),
							new PieChart.Data("商品报溢收入", business
									.getCommidityOverFlowIncome()),
							new PieChart.Data("成本调价收入", business
									.getCommidityCostChangeIncome()),
							new PieChart.Data("代金券与实际金额差价收入", business
									.getVoucherActualDifferenceIncome()),
							new PieChart.Data("折让后总收入", business
									.getIncomeAfterdiscount()),
							new PieChart.Data("销售成本", business.getSaleCost()),
							new PieChart.Data("商品报损", business
									.getCommidityLostCost()),
							new PieChart.Data("商品赠送", business
									.getCommidityGiftCost()),
							new PieChart.Data("进货退货差价收入", business
									.getBuySaleDifferenceIncome()),
							new PieChart.Data("总支出", business.getSumOfCost()
									+ 100 * d),
							new PieChart.Data("总收入", 100 * d),
							new PieChart.Data("利润", business
									.getIncomeAfterdiscount()
									- business.getSumOfCost() + 100 * d));
					pchart.setData(pieChartData);
				}
			}
		});
		a.getChildren().addAll(text1, text2, comboBox1, tf1, tf2, bchart);
		Scene s = new Scene(a, 1080, 800);
		primaryStage.setTitle("财务人员");
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
		filechooser.setTitle("导出");
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

	private static Date strToDate(String time) {// 将字符串转化为Date,如果格式不对，返回null
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = date.parse(time, pos);
		return strtodate;
	}

	private BusinessConditionVO getInitCondition() {
		Date nowDate = new Date();
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(nowDate);
		rightNow.add(Calendar.YEAR, -1);// 日期减1年
		Date preDate = rightNow.getTime();

		BusinessConditionVO condition = new BusinessConditionVO();

		BusinessConditionHelper helper = BusinessConditionHelper.getInstance();
		try {
			System.out.println(preDate);
			System.out.println(nowDate);
			condition = helper.getService().getBusinessConditionList(preDate,
					nowDate);// 得到businessCondition
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return condition;
	}

}
