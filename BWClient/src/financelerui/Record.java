package financelerui;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import rmi.InitializeAccountHelper;
import VO.InitializeAccountInShortVO;
import VO.InitializeAccountVO;

public class Record {// 期初建账的记录
	private static String j;
	private static String n;
	private static Stage pStage;
	private static String id;
	private static TableView<InitializeAccountInShortVO> t1;// 列表显示
	private static TableColumn<InitializeAccountInShortVO,String> t2;// 建账时间
	private static TableColumn<InitializeAccountInShortVO,String> t3;// 操作员编号
	private static ArrayList<InitializeAccountInShortVO> initializeaccountshortlist=new ArrayList<>();
	
	
	public void display(String Job, String name, String ID) {
		// TODO Auto-generated method stub
		Stage primaryStage = new Stage();
		id = ID;
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("Record.fxml"));
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
		t1.setLayoutX(126.0);
		t1.setLayoutY(179.0);
		t1.setMinHeight(220.0);
		t1.setMaxHeight(220.0);
		t1.setMinWidth(477.0);
		t1.setMaxWidth(477.0);
		t2 = new TableColumn("建账时间");
		t2.setPrefWidth(231.0);
		t3 = new TableColumn("操作员编号");
		t3.setPrefWidth(245.0);
		t1.setStyle("-fx-background-color:null");

		t1.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				if (event.getClickCount() == 2) {// 双击查看详情
					//我要知道用户点击的是什么
					int index=0;
					index=t1.getSelectionModel().getSelectedIndex();
					InitializeAccountInShortVO r1=new InitializeAccountInShortVO();
					r1=initializeaccountshortlist.get(index);
					Record record=new Record();
					InitializeAccountVO initialize=new InitializeAccountVO();
					try {
						initialize=record.getDetail(r1.getDate());
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					makeAccountFirst make=new makeAccountFirst();
				    make.display(j,n,id,initialize);	
				}
			}
		});

		t1.getColumns().addAll(t2, t3);

		a.getChildren().addAll(root, text1, text2, t1);

		Scene s = new Scene(a, 800, 500);
		primaryStage.setTitle("财务人员");
		primaryStage.setScene(s);
		j = Job;
		n = name;
		pStage = primaryStage;
		primaryStage.show();
	}

	private ArrayList<InitializeAccountInShortVO> getInitializeList()
			throws RemoteException {

		InitializeAccountHelper helper = InitializeAccountHelper.getInstance();

		return helper.getService().getList();

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
	private void HandleOut(ActionEvent event) {
		pStage.close();
	}

	@FXML
	private void HandleEsc(ActionEvent event) {
		pStage.close();
	}
	private InitializeAccountVO getDetail(Date date) throws RemoteException{
		InitializeAccountHelper helper=InitializeAccountHelper.getInstance();
		return helper.getService().findByDate(date);
	}
}
