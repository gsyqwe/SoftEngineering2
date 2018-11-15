package financelerui;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import mainframeui.AlertBox;
import rmi.InitializeAccountHelper;
import VO.BankAccountVO;
import VO.InitializeAccountVO;
import enums.ResultMessage;

public class makeAccountThird {
	private static String j;
	private static String n;
	private static Stage pStage;
	private static String id;
	private static TableView<BankAccountVO> t;//显示列表
	private static TableColumn<BankAccountVO,String> t1;//账户名称
	private static TableColumn<BankAccountVO,String> t2;//账户编号
	private static TableColumn<BankAccountVO,String> t3;//账户余额
	private static ArrayList<BankAccountVO> bankaccountlist=new ArrayList<>();
	
	public void display(String Job, String name,String ID,InitializeAccountVO intaialize){
		Stage primaryStage = new Stage();
		Parent root = null;
		bankaccountlist=intaialize.getBankAccountPOList();
		id=ID;
		AnchorPane a = new AnchorPane();
		try {
			root = FXMLLoader.load(getClass().getResource(
					"makeAccountThird.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

		TableView t = new TableView();
		t.setLayoutX(108.0);
		t.setLayoutY(177.0);
		t.setMinHeight(243.0);
		t.setMaxHeight(243.0);
		t.setPrefWidth(600.0);
		t1 = new TableColumn("账户名称");
		t1.setPrefWidth(213.0);
		t2 = new TableColumn("账户编号");
		t2.setPrefWidth(183.0);
		t3 = new TableColumn("账户余额");
		t3.setPrefWidth(203.0);
		t.getColumns().addAll(t1, t2, t3);
		
		ObservableList<BankAccountVO> data = FXCollections.observableArrayList();
		for (int i = 0; i < bankaccountlist.size(); i++) {
			data.add(bankaccountlist.get(i));
		}

		// 你不能这样给我显示这个银行账户，因为它必须要与显示的那个类要相关联

		t1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData
				.getValue().getName()));// 名称

		t2.setCellValueFactory(cellData -> new SimpleStringProperty(""
				+ cellData.getValue().getId()));// 编号
		t3.setCellValueFactory(cellData -> new SimpleStringProperty(Double.toString(cellData
				.getValue().getAmount())));// 余额
		
		t.setStyle("-fx-background-color:null");
		t.setItems(data);

		a.getChildren().addAll(root, t, text1, text2);

		Scene s = new Scene(a, 800, 500);
		primaryStage.setTitle("财务人员");
		primaryStage.setScene(s);
		j = Job;
		n = name;
		pStage = primaryStage;
		primaryStage.show();
	}

	@FXML
	private void HandleFinish(ActionEvent event) throws RemoteException {// 完成期初建账的操作
		InitializeAccountVO vo=new InitializeAccountVO();//我要上一个界面传过来的vo
		InitializeAccountHelper helper=InitializeAccountHelper.getInstance();
		ResultMessage result=helper.getService().setUp(vo);
		if(result==ResultMessage.SUCCESS){
			//成功
			AlertBox a=new AlertBox();
			a.display("成功","成功");
			pStage.close();
		}else{
			//失败
			AlertBox a=new AlertBox();
			a.display("失败","失败");
		}
	}


	@FXML
	private void HandleCancle(ActionEvent event) {
		pStage.close();
	}

	@FXML
	private void HandleHelp(ActionEvent event) {
		HelpF h = new HelpF();
		h.display(j, n,id);
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
