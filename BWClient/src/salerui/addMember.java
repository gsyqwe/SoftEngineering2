package salerui;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import mainframeui.AlertBox;
import rmi.MemberHelper;
import VO.MemberVO;
import VO.UserVO;
import enums.MemberType;
import enums.MemberVipLevel;
import enums.ResultMessage;
import enums.level;

public class addMember {// ���ӿͻ�

	private static String j;

	private static String n;

	private static Stage pStage;

	private static String id;

	private static ComboBox comboBox1;// ѡ��ͻ�����

	private static ComboBox comboBox2;// ѡ��ͻ�����

	private static ComboBox comboBox3;// ѡ��ҵ��Ա

	private static TField t1;// ���ɿͻ����

	private static TField t2;// ����

	private static TField t3;// �绰

	private static TField t4;// סַ

	private static TField t5;// ����

	private static TField t6;// ��������

	private static TField t7;// Ӧ�ն��
	private static MemberVO member;

	@SuppressWarnings("unchecked")
	public void display(String Job, String name, String ID) {

		// TODO Auto-generated method stub

		id = ID;

		Stage primaryStage = new Stage();

		Parent root = null;

		try {

			root = FXMLLoader.load(getClass().getResource("addMember.fxml"));

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

		// ComboBox1ѡ��ͻ�����

		comboBox1 = new ComboBox();

		comboBox1.setValue("ѡ��ͻ�����");

		comboBox1
				.setStyle("-fx-border-color:Grey;-fx-border-radius:4;-fx-background-color:null");

		comboBox1.getItems().addAll();

		comboBox1.setMinHeight(32.0);

		comboBox1.setMaxHeight(32.0);

		comboBox1.setMinWidth(128.0);

		comboBox1.setMaxWidth(128.0);

		comboBox1.setLayoutX(138.0);

		comboBox1.getItems().addAll(MemberType.SUPPLIER, MemberType.RETAILER);

		comboBox1.setLayoutY(225.0);

		// ComboBox2ѡ�񼶱�

		comboBox2 = new ComboBox();

		comboBox2.setValue("ѡ�񼶱�");

		comboBox2
				.setStyle("-fx-border-color:Grey;-fx-border-radius:4;-fx-background-color:null");

		comboBox2.setLayoutX(368.0);

		comboBox2.setLayoutY(237.0);

		comboBox2.getItems().addAll(level.ordinary, level.Bronze, level.Sliver,
				level.gold, level.Diamond);

		comboBox2.setMinHeight(32.0);

		comboBox2.setMaxHeight(32.0);

		comboBox2.setMinWidth(142.0);

		comboBox2.setMaxWidth(142.0);

		comboBox2.setLayoutX(368.0);

		comboBox2.setLayoutY(225);

		// ComboBox3ѡ��ҵ��Ա

		comboBox3 = new ComboBox();

		comboBox3.setValue("ѡ��ҵ��Ա");

		comboBox3
				.setStyle("-fx-border-color:Grey;-fx-border-radius:4;-fx-background-color:null");

		comboBox3.setLayoutX(627.0);

		comboBox3.setLayoutY(225.0);

		comboBox3.getItems().addAll();

		comboBox3.setMinHeight(32.0);

		comboBox3.setMaxHeight(32.0);

		comboBox3.setMinWidth(142.0);

		comboBox3.setMaxWidth(142.0);

		comboBox3.setLayoutX(627.0);

		comboBox3.setLayoutY(225);

		comboBox3.setValue(id);
		// �ͻ����

		t1 = new TField();

		t1.setEditable(false);

		t1.setPrefWidth(128);

		t1.setLayoutX(138);

		t1.setLayoutY(177);

		// ����

		t2 = new TField();

		t2.setPrefWidth(128);

		t2.setLayoutX(138);

		t2.setLayoutY(290);

		// �绰

		t3 = new TField();

		t3.setPrefWidth(128);

		t3.setLayoutX(368);

		t3.setLayoutY(290);

		// סַ

		t4 = new TField();

		t4.setPrefWidth(128);

		t4.setLayoutX(627);

		t4.setLayoutY(290);

		// ����

		t5 = new TField();

		t5.setPrefWidth(128);

		t5.setLayoutX(138);

		t5.setLayoutY(352);

		// ��������

		t6 = new TField();

		t6.setPrefWidth(128);

		t6.setLayoutX(368);

		t6.setLayoutY(352);

		// Ӧ�ն��

		t7 = new TField();

		t7.setPrefWidth(128);

		t7.setLayoutX(627);

		t7.setLayoutY(352);

		a.getChildren().addAll(root, text1, text2, comboBox1, comboBox2,
				comboBox3, t1, t2, t3, t4, t5, t6, t7);

		Scene s = new Scene(a, 800, 500);

		primaryStage.setTitle("����������Ա");

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
	private void HandleOut(ActionEvent event) {

		pStage.close();

	}

	@FXML
	private void HandleEsc(ActionEvent event) {

		pStage.close();

	}

	@FXML
	private void HandleAdd(ActionEvent event) {// ���ӻ�Ա���¼�

		AlertBox alert = new AlertBox();
		alert.display("���ӳɹ�", "���ӳɹ�");
		pStage.close();


		member.setAddress("nanjing");

		if (t7.getText() == null || t7.getText().equals("")) {

			String warning = "The credit cannot be empty.";
			AlertBox a = new AlertBox();
			a.display(warning, warning);

			return;

		}

		member.setCredit(Double.parseDouble(t7.getText()));

		if (comboBox3.getPromptText() == null
				|| comboBox3.getPromptText().equals("")) {

			member.setDefaultSalesman(id);

		}

		else {

			UserVO user = new UserVO();// ѡ��һ��user��������user��id

			member.setDefaultSalesman(user.getId());

		}

		if (t6.getText() == null || t6.getText().equals("")) {

			String warning = "The email cannot be empty";

			AlertBox a = new AlertBox();
			a.display(warning, warning);

			return;

		}

		member.setEmail(t6.getText());

		if (comboBox1.getValue().toString() == null
				|| comboBox1.getValue().toString().equals("")) {

			String warning = "The member type cannot be empty.";

			AlertBox a = new AlertBox();
			a.display(warning, warning);

			return;

		}

		// ��֪��MemberType������ʾ����ʲô���Ⱥ���������ٸ�

		if (comboBox1.getValue().toString().equals("Retailer")) {

			member.setMemberType(MemberType.RETAILER);

		}

		else {

			member.setMemberType(MemberType.SUPPLIER);

		}

		if (comboBox2.getValue().toString() == null
				|| comboBox2.getValue().toString().equals("")) {

			String warning = "The vip level cannot be empty.";

			AlertBox a = new AlertBox();
			a.display(warning, warning);

			return;

		}

		member.setMemberVipLevel(getVipLevel(comboBox2.getValue().toString()));

		if (t2.getText() == null || t2.getText().equals("")) {

			String warning = "The name of the member cannot be empty.";
			AlertBox a = new AlertBox();
			a.display(warning, warning);

			return;

		}

		member.setName(t2.getText());

		member.setPayment(0);

		member.setPhoneNumber(t3.getText());

		member.setPostcode(t5.getText());

		member.setReceivable(0);

		member.setVouchers(new ArrayList<>());

		MemberHelper helper = new MemberHelper();

		try {

			ResultMessage message = helper.getService().completeAdd(member);

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

			} else {
				AlertBox a = new AlertBox();
				a.display("�ɹ�", "�ɹ�");
				pStage.close();
			}

		} catch (RemoteException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

	}

	// ����ѡ�е�String����MemberVipLevel

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

	// ˫��id���õ�id

	public String getMemberID(MemberType memberType) {

		MemberHelper helper = new MemberHelper();

		try {

			String memberID = helper.getService().getMemberId(memberType);

			return memberID;

		} catch (RemoteException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

		return "";// ��������ء���

	}

}