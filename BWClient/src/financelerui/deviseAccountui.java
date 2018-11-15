package financelerui;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import mainframeui.AlertBox;
import mainframeui.TField;
import rmi.BankAccountHelper;
import VO.BankAccountVO;
import enums.ResultMessage;

public class deviseAccountui {
	static String j;
	static String n;
	static Stage pStage;
	private static String id;
	private static TField c1;// 账户名称
	private static TField t1;// 编号的textfield
	private static TField t2;// 卡号的textfield
	private static TField t3 = new TField();// 余额的textfield
	private static TableView table = new TableView();
	private static ArrayList<BankAccountVO> blist = new ArrayList<>();
	private static BankAccountVO bank;

	public void display(String Job, String name, String ID, BankAccountVO b,
			TableView t, ArrayList<BankAccountVO> banklist) {
		// TODO Auto-generated method stub
		Stage primaryStage = new Stage();
		id = ID;
		table = t;
		blist = banklist;
		bank = b;
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource(
					"deviseAccountui.fxml"));
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

		// 账户名称
		c1 = new TField();
		c1.setEditable(false);
		c1.setText(b.getName());
		c1.setLayoutX(225.0);
		c1.setLayoutY(210.0);
		c1.setPrefWidth(138.0);
		c1.setMinHeight(30.0);
		c1.setMaxHeight(30.0);
		c1.setStyle("-fx-border-color:Grey;-fx-border-radius:4;-fx-background-color:null");

		// 编号的textfield
		t1 = new TField();
		t1.setEditable(false);
		t1.setText(b.getId());
		t1.setLayoutX(549.0);
		t1.setLayoutY(210.0);
		t1.setMinHeight(30.0);
		t1.setMaxHeight(30.0);
		t1.setPrefWidth(138.0);

		// 卡号的textfield
		t2 = new TField();
		t2.setText(b.getCardNumber());
		t2.setLayoutX(225.0);
		t2.setLayoutY(280.0);
		t2.setMinHeight(30.0);
		t2.setMaxHeight(30.0);
		t2.setPrefWidth(138.0);

		// 余额的textfield
		t3 = new TField();
		String str = Double.toString(b.getAmount());
		t3.setText(str);
		t3.setLayoutX(549.0);
		t3.setLayoutY(280.0);
		t3.setMinHeight(30.0);
		t3.setMaxHeight(30.0);
		t3.setPrefWidth(138.0);

		a.getChildren().addAll(root, text1, text2, t1, t2, t3, c1);
		Scene s = new Scene(a, 800, 500);
		primaryStage.setTitle("财务人员");
		primaryStage.setScene(s);
		j = Job;
		n = name;
		pStage = primaryStage;
		primaryStage.show();
	}

	@FXML
	private void HandleHelp(ActionEvent event) {
		HelpF f = new HelpF();
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
	private void HandleDevise(ActionEvent event) throws RemoteException {
		String name = c1.getText();
		double amount = Double.parseDouble(t3.getText());
		String ID = t1.getText();
		String cardNumber = t2.getText();
		BankAccountVO vo = new BankAccountVO();
		vo.setAmount(amount);
		vo.setCardNumber(cardNumber);
		vo.setId(ID);
		vo.setName(name);
		BankAccountHelper helper = BankAccountHelper.getInstance();
		ResultMessage result = helper.getService().modifyBankAccount(ID, vo);
		if (result == ResultMessage.SUCCESS) {
			// 显示修改成功
			AlertBox a = new AlertBox();
			a.display("修改成功", "修改成功");
		} else if (result == ResultMessage.BANK_ACCOUNT_UPDATE_FAIL_NEGATIVE_AMOUNT) {
			// 显示“修改失败，账户余额不能为负”
			AlertBox a = new AlertBox();
			a.display("修改失败，账户余额不能为负", "修改失败,账户余额不能为负");
		}
		for (int i = 0; i < blist.size(); i++) {
			if (blist.get(i).getId().equals(bank.getId())) {
				blist.remove(i);
			}
		}
		blist.add(vo);
		ObservableList<BankAccountVO> data = FXCollections
				.observableArrayList();

		for (int i = 0; i < blist.size(); i++) {

			data.add(blist.get(i));

		}
		table.setItems(data);
		pStage.close();
	}
}
