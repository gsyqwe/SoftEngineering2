package salerui;

import java.io.IOException;
import java.rmi.RemoteException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mainframeui.AlertBox;
import rmi.MemberHelper;
import VO.MemberVO;
import enums.MemberType;
import enums.MemberVipLevel;
import enums.ResultMessage;
import enums.level;

public class deviseMember {// 修改客户

	private static String j;

	private static String n;

	private static Stage pStage;

	private static String id;

	private static TField t1;// 选择客户

	private static ComboBox t2;// 客户分类

	private static ComboBox t3;// 级别

	private static TField t4;// 业务员

	private static TField t5;// 编号

	private static TField t6;// 电话

	private static TField t7;// 住址

	private static TField t8;// 邮编

	private static TField t9;// 电子邮箱

	private static TField t10;// 应收额度

	private static Text t11;// 应收

	private static Text t12;// 应付

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void display(String Job, String name, String ID, MemberVO mem) {

		// TODO Auto-generated method stub

		Stage primaryStage = new Stage();

		id = ID;

		Parent root = null;

		try {

			root = FXMLLoader.load(getClass().getResource("deviseMember.fxml"));

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

		// 客户姓名,利用选择

		t1 = new TField();

		t1.setText(mem.getName());

		t1.setMinHeight(30);

		t1.setMaxHeight(30);

		t1.setPrefWidth(128);

		t1.setLayoutX(138);

		t1.setLayoutY(177);

		// 客户分类

		t2 = new ComboBox();

		t2.setValue(mem.getMemberType());

		t2.getItems().addAll(MemberType.SUPPLIER, MemberType.RETAILER);

		t2.setPrefWidth(128);

		t2.setLayoutX(138.0);

		t2.setLayoutY(225.0);

		// 级别

		t3 = new ComboBox();

		t3.setValue(mem.getMemberVipLevel());

		t3.getItems().addAll(level.ordinary, level.Bronze, level.Sliver,
				level.gold, level.Diamond);

		t3.setLayoutX(368.0);

		t3.setLayoutY(225);

		t3.setPrefWidth(128);

		// 业务员

		t4 = new TField();

		t4.setText(mem.getDefaultSalesman());

		t4.setEditable(false);

		t4.setPrefWidth(128);

		t4.setLayoutX(627.0);

		t4.setLayoutY(225);

		// 编号

		t5 = new TField();

		t5.setText(mem.getId());

		t5.setEditable(false);

		t5.setPrefWidth(128);

		t5.setLayoutX(138);

		t5.setLayoutY(290);

		// 电话

		t6 = new TField();

		t6.setText(mem.getPhoneNumber());

		t6.setPrefWidth(128);

		t6.setLayoutX(368);

		t6.setLayoutY(290);

		// 住址

		t7 = new TField();

		t7.setText(mem.getAddress());

		t7.setPrefWidth(128);

		t7.setLayoutX(627);

		t7.setLayoutY(290);

		// 邮政

		t8 = new TField();

		t8.setText(mem.getPostcode());

		t8.setPrefWidth(128);

		t8.setLayoutX(138);

		t8.setLayoutY(352);

		// 电子邮箱

		t9 = new TField();

		t9.setText(mem.getEmail());

		t9.setPrefWidth(128);

		t9.setLayoutX(368);

		t9.setLayoutY(352);

		// 应收额度

		t10 = new TField();

		t10.setText(Double.toString(mem.getCredit()));

		t10.setPrefWidth(128);

		t10.setLayoutX(627);

		t10.setLayoutY(352);

		// 应收

		t11 = new Text();

		t11.setText(Double.toString(mem.getReceivable()));

		t11.setFont(Font.font("System", 18));

		t11.setLayoutX(310);

		t11.setLayoutY(434);

		// 应付

		t12 = new Text();

		t12.setFont(Font.font("System", 18));

		t12.setText(Double.toString(mem.getPayment()));

		t12.setLayoutX(530);

		t12.setLayoutY(434);

		a.getChildren().addAll(root, text1, text2, t1, t2, t3, t4, t5, t6, t7,
				t8, t9, t10, t11, t12);

		Scene s = new Scene(a, 800, 500);

		primaryStage.setTitle("进货销售人员");

		primaryStage.setScene(s);

		j = Job;

		n = name;

		pStage = primaryStage;

		primaryStage.show();

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

	/**
	 * 
	 *
	 * 
	 * @param 允许改动的项
	 *            ，剩下的项在界面上不能修改，级别应该是ComboBox
	 * 
	 *            private static TField t3;//级别 private static TField t4;//业务员
	 * 
	 *            private static TField t6;//电话
	 * 
	 *            private static TField t7;//住址 private static TField t8;//邮编
	 * 
	 *            private static TField t9;//电子邮箱
	 */

	@FXML
	private void HandleDevise(ActionEvent event) {

		AlertBox alert = new AlertBox();
		alert.display("修改成功", "修改成功");
		pStage.close();

		MemberVO member = new MemberVO();// 假设界面上选择了一个memberVO,关联一个memberVO

		if (t3.getValue().toString() == null
				|| t3.getValue().toString().equals("")) {

			String warning = "The vip level cannot be empty.";

			AlertBox a = new AlertBox();
			a.display(warning, warning);

			return;

		}

		member.setMemberVipLevel(getVipLevel(t3.getValue().toString()));

		if (t4.getText() == null || t4.getText().equals("")) {

			String warning = "The default salesman cannot be empty.";
			AlertBox a = new AlertBox();
			a.display(warning, warning);

			return;

		}

		member.setPhoneNumber(t6.getText());

		member.setAddress(t7.getText());

		member.setPostcode(t8.getText());

		if (t9.getText() == null || t9.getText().equals("")) {

			String warning = "The email cannot be empty";
			AlertBox a = new AlertBox();
			a.display(warning, warning);

			return;

		}

		member.setEmail(t9.getText());

		MemberHelper helper = new MemberHelper();

		try {

			ResultMessage message = helper.getService().ModifyMember(
					member.getId(), member);

			if (message == ResultMessage.MEMBER_EMAIL_FAULT) {

				String warning = "The email format is wrong,please check it";

				AlertBox a = new AlertBox();
				a.display(warning, warning);

				return;

			}

			else if (message != ResultMessage.SUCCESS) {

				String warning = "Unexcepted data fault.";

				AlertBox a = new AlertBox();
				a.display(warning, warning);

				return;

			}

		} catch (RemoteException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

	}

	// 根据选中的String返回MemberVipLevel

	public MemberVipLevel getVipLevel(String level) {

		/**
		 * 
		 * ordinary("ordinary"), Bronze("Bronze"), Sliver("Sliver"),
		 * gold("gold"), Diamond("Diamond");
		 */

		if (level.equals("ordinary")) {

			return MemberVipLevel.ORDINARY;

		}

		else if (level.equals("Bronze")) {

			return MemberVipLevel.BRONZE;

		}

		else if (level.equals("Sliver")) {

			return MemberVipLevel.SLIVER;

		}

		else if (level.equals("gold")) {

			return MemberVipLevel.GOLD;

		}

		else if (level.equals("Diamond")) {

			return MemberVipLevel.DIAMOND;

		}

		else {

			return null;

		}

	}

}