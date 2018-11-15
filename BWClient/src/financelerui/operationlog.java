package financelerui;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import manganerui.TField;
import rmi.OperationLogHelper;
import VO.LogVO;

public class operationlog {// 系统日志
	private static String j;
	private static String n;
	private static Stage pStage;
	private static String id;
	private static TableView<LogVO> t1;// 显示的列表
	private static TableColumn<LogVO,String> t2;// 日志编号
	private static TableColumn<LogVO,String> t3;// 日志操作时间
	private static TableColumn<LogVO,String> t4;// 操作员编号
	private static TableColumn<LogVO,String> t5;// 日志操作内容
	private static TField tf1;// 搜索
	private static CheckBox ch1;// 操作类型
	private static CheckBox ch2;// 操作员
	private static CheckBox ch3;// 操作时间
	private static ArrayList<LogVO>loglist=new ArrayList<>();

	public void display(String Job, String name, String ID) {
		// TODO Auto-generated method stub
		Stage primaryStage = new Stage();
		//得到operationloglist
		operationlog op=new operationlog();
		try {
			loglist=op.getLogList();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		id = ID;
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("operationlog.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		AnchorPane a = new AnchorPane();
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

		t1 = new TableView();
		t1.setLayoutX(69.0);
		t1.setLayoutY(193);
		t1.setMinHeight(254.0);
		t1.setMaxHeight(254.0);
		t1.setMinWidth(678.0);
		t1.setMaxWidth(678.0);
		t2 = new TableColumn("日志编号");
		t2.setPrefWidth(101.0);
		t3 = new TableColumn("日志操作时间");
		t3.setPrefWidth(149.0);
		t4 = new TableColumn("操作员编号");
		t4.setPrefWidth(137.0);
		t5 = new TableColumn("日志操作内容");
		t5.setPrefWidth(290);
		t1.getColumns().addAll(t2, t3, t4, t5);
		
		ObservableList<LogVO> data = FXCollections.observableArrayList();
		for (int i = 0; i < loglist.size(); i++) {
			data.add(loglist.get(i));
		}

		// 你不能这样给我显示这个银行账户，因为它必须要与显示的那个类要相关联

		t2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData
				.getValue().getId()));// 日志编号

		t3.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData
				.getValue().getTime()));// 日志操作时间
		t4.setCellValueFactory(cellData -> new SimpleStringProperty(cellData
				.getValue().getUserID()));// 操作员编号
		t5.setCellValueFactory(cellData -> new SimpleStringProperty(cellData
				.getValue().getContent()));// 内容

		t1.setItems(data);
		
		t1.setStyle("-fx-background-color:null");

		// 搜索
		tf1 = new TField();
		tf1.setMinHeight(30);
		tf1.setMaxHeight(30);
		tf1.setLayoutX(624);
		tf1.setLayoutY(144);
		tf1.setPrefWidth(163);

		ch1 = new CheckBox("操作类型");// 操作类型的复选框
		ch1.setLayoutX(487);
		ch1.setLayoutY(161);
		ch2 = new CheckBox("操作员");// 操作员的复选框
		ch2.setLayoutX(583);
		ch2.setLayoutY(161);
		ch3 = new CheckBox("操作时间");// 操作时间的复选框
		ch3.setLayoutX(666);
		ch3.setLayoutY(161);

		// 筛选
		tf1 = new TField();
		tf1.setLayoutX(536);
		tf1.setLayoutY(153);
		tf1.setMinHeight(25);
		tf1.setMaxHeight(25);
		tf1.setPrefWidth(252);
		tf1.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER) {

					OperationLogHelper helper = OperationLogHelper
							.getInstance();

					try {

						ArrayList<LogVO> searchResult = new ArrayList<LogVO>();

						if (ch1.isSelected()) {

							searchResult.addAll(helper.getService()
									.findLogByCommetWithKeyword(tf1.getText()));

						}

						if (ch2.isSelected()) {

							searchResult.addAll(helper.getService().findByUser(
									tf1.getText()));

						}

						// findbytime需要开始时间和结束时间，界面上只有一个框，我不知道怎么得到

						// if(ch3.isSelected()){

						// searchResult.addAll(helper.getService().findByTime(tf1.getText()));

						// }

						// searchResult里面是搜索结果

					} catch (RemoteException e) {

						// TODO Auto-generated catch block

						e.printStackTrace();

					}

				}

			}
		});

		a.getChildren().addAll(root, text1, text2, t1, tf1);

		Scene s = new Scene(a, 800, 500);
		primaryStage.setTitle("财务人员");
		primaryStage.setScene(s);
		j = Job;
		n = name;
		pStage = primaryStage;
		primaryStage.show();
	}

	private ArrayList<LogVO> getLogList() throws RemoteException {

		OperationLogHelper helper = OperationLogHelper.getInstance();

		return helper.getService().getAllLog();

	}

	@FXML
	private void HandleHelp(ActionEvent event) {
		HelpF f = new HelpF();
		f.display(j, n, id);
	}

	@FXML
	private void HandleSure(ActionEvent event) {
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
				"EXCEL files (*.excel)", "*.excel");
		filechooser.getExtensionFilters().add(extFilter);
		File file = filechooser.showSaveDialog(pStage);
		if (file != null) {
			// 将要写入的内容写到file文件中
		}
	}

	@FXML
	private void HandleOut(ActionEvent event) {
		pStage.close();
	}

	@FXML
	private void HandleEsc(ActionEvent event) {
		pStage.close();
	}

}
