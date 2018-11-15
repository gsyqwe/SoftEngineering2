package salerui;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;

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
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import mainframeui.AlertBox;
import rmi.MemberHelper;
import VO.MemberVO;
import enums.ResultMessage;

/**
 * 
 *
 * 
 * @author 82646 我筛选id做的筛选条件是id，name，postcode，salesman，type
 * 
 *
 */

public class GetMember {// 查看客户

	private static String j;

	private static String n;

	private static Stage pStage;

	private static String id;

	private static TableView<Person> t1;// 列表显示

	private static TableColumn<Person, String> t2;// 编号

	private static TableColumn<Person, String> t3;// 分类

	private static TableColumn<Person, String> t4;// 级别

	private static TableColumn<Person, String> t5;// 姓名

	private static TableColumn<Person, String> t6;// 电话

	private static TableColumn<Person, String> t7;// 地址

	private static TableColumn<Person, String> t8;// 邮政

	private static TableColumn<Person, String> t9;// 电子邮箱

	private static TableColumn<Person, String> t10;// 应收额度

	private static TableColumn<Person, String> t11;// 应收

	private static TableColumn<Person, String> t12;// 应付

	private static TableColumn<Person, String> t13;// 默认业务员

	private static TableColumn<Person, CheckBox> t14;// 复选框

	private static TField tf1;// 筛选

	private static CheckBox ch1;// 等级 id

	private static CheckBox ch2;// 邮编 name

	private static CheckBox ch3;// 电话 postcode

	private static CheckBox ch4;// 地址 salesman

	private static CheckBox ch5;// 应收 type

	private static CheckBox ch7;// 默认业务员level

	private static ArrayList<MemberVO> mylist = new ArrayList();

	private static ArrayList<Person> personlist = new ArrayList();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void display(String Job, String name, String ID) {

		// TODO Auto-generated method stub

		Stage primaryStage = new Stage();

		id = ID;

		GetMember get = new GetMember();
		mylist.clear();
		personlist.clear();
		try {
			mylist = get.getMemberList();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// 做转换
		for (int i = 0; i < mylist.size(); i++) {
			Person p = new Person(mylist.get(i));
			personlist.add(p);
		}

		Parent root = null;

		try {

			root = FXMLLoader.load(getClass().getResource("GetMember.fxml"));

		} catch (IOException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

		AnchorPane a = new AnchorPane();

		TextField text1 = new TextField();

		text1.setStyle("-fx-background-color:null");

		text1.setText("欢迎您:" + name);

		text1.setLayoutX(913.0);

		text1.setLayoutY(40.0);

		text1.setMinHeight(36.0);

		text1.setMaxHeight(36.0);

		text1.setFont(Font.font("Verdana", 18));

		text1.prefWidth(185.0);

		text1.setEditable(false);

		TextField text2 = new TextField();

		text2.setStyle("-fx-background-color:null");

		text2.setText("当前职务:" + Job);

		text2.setLayoutX(913.0);

		text2.setLayoutY(68.0);

		text2.setMinHeight(30.0);

		text2.setMaxHeight(30.0);

		text2.setFont(Font.font("Verdana", 18));

		text2.prefWidth(185.0);

		text2.setEditable(false);

		t1 = new TableView();

		t2 = new TableColumn("编号");

		t2.setPrefWidth(67.0);

		t3 = new TableColumn("分类");

		t3.setPrefWidth(62.0);

		t4 = new TableColumn("级别");

		t4.setPrefWidth(64.0);

		t5 = new TableColumn("姓名");

		t5.setPrefWidth(59.0);

		t6 = new TableColumn("电话");

		t6.setPrefWidth(52.0);

		t7 = new TableColumn("地址");

		t7.setPrefWidth(74.0);

		t8 = new TableColumn("邮政");

		t8.setPrefWidth(61.0);

		t9 = new TableColumn("电子邮箱");

		t9.setPrefWidth(78.0);

		t10 = new TableColumn("应收额度");

		t10.setPrefWidth(69.0);

		t11 = new TableColumn("应收");

		t11.setPrefWidth(49.0);

		t12 = new TableColumn("应付");

		t12.setPrefWidth(47.0);

		t13 = new TableColumn("默认业务员");

		t13.setPrefWidth(125.0);

		t14 = new TableColumn();
		t14.setPrefWidth(40);

		t1.setStyle("-fx-background-color:null");

		t1.setLayoutX(120.0);

		t1.setLayoutY(285.0);

		t1.setMinHeight(450.0);

		t1.setMaxHeight(450.0);

		t1.setMinWidth(838.0);

		t1.setMaxWidth(838.0);

		// 使用ObserValue

		ObservableList<Person> data = FXCollections.observableArrayList();

		for (int i = 0; i < personlist.size(); i++) {

			data.add(personlist.get(i));

		}

		t3.setCellValueFactory(cellData -> new SimpleStringProperty(cellData

		.getValue().getMember().getMemberType().toString()));// 客户类型

		t2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData

		.getValue().getMember().getId()));// 客户名称

		t4.setCellValueFactory(cellData -> new SimpleStringProperty(cellData

		.getValue().getMember().getMemberVipLevel().toString()));// 客户等级

		t5.setCellValueFactory(cellData -> new SimpleStringProperty(cellData

		.getValue().getMember().getName()));// 姓名

		t6.setCellValueFactory(cellData -> new SimpleStringProperty(cellData

		.getValue().getMember().getPhoneNumber()));// 电话

		t7.setCellValueFactory(cellData -> new SimpleStringProperty(cellData

		.getValue().getMember().getAddress()));// 地址

		t8.setCellValueFactory(cellData -> new SimpleStringProperty(cellData

		.getValue().getMember().getPostcode()));
		;// 邮编

		t9.setCellValueFactory(cellData -> new SimpleStringProperty(cellData

		.getValue().getMember().getEmail()));// 电子邮箱

		t10.setCellValueFactory(cellData -> new SimpleStringProperty(Double
				.toString(cellData

				.getValue().getMember().getCredit())));// 应收额度

		t11.setCellValueFactory(cellData -> new SimpleStringProperty(Double
				.toString(cellData

				.getValue().getMember().getReceivable())));// 应收

		t12.setCellValueFactory(cellData -> new SimpleStringProperty(Double
				.toString(cellData

				.getValue().getMember().getPayment())));// 应付

		t13.setCellValueFactory(cellData -> new SimpleStringProperty(cellData

		.getValue().getMember().getDefaultSalesman()));// 默认业务员

		t14.setCellValueFactory(cellData -> cellData.getValue().cb
				.getCheckBox());

		t1.getColumns().addAll(t2, t3, t4, t5, t6, t7, t8, t9, t10, t11,

		t12, t13, t14);

		t1.setStyle("-fx-background-color:null");

		t1.setItems(data);

		// 增加右键按钮

		ContextMenu cme = new ContextMenu();

		// 增加右键菜单的一些项

		MenuItem m1 = new MenuItem("增加");

		MenuItem m2 = new MenuItem("删除");

		MenuItem m3 = new MenuItem("修改");

		cme.getItems().addAll(m1, m2, m3);

		t1.setContextMenu(cme);

		// 增加这个事件

		m1.setOnAction(new EventHandler<ActionEvent>() {// 增加

			@Override
			public void handle(ActionEvent e) {
				addMember a = new addMember();
				a.display(j, n, id);

			}

		});

		m2.setOnAction(new EventHandler<ActionEvent>() {// 删除

			@Override
			public void handle(ActionEvent e) {

				// 得到被选择的index,得到编号

				int index;

				index = t1.getSelectionModel().getSelectedIndex();

				MemberVO member = new MemberVO();
				member = mylist.get(index);
				ArrayList<String> warnings = new ArrayList<>(); // 存放错误信息

				if (member.getPayment() != 0 || member.getReceivable() != 0) {

					String warning = "The member "
							+ member.getName()

							+ "'s payment or receivable is not zero,you can't delete him";

					warnings.add(warning);

				}
				if (!warnings.isEmpty()) {

					// 打印错误信息
					AlertBox a = new AlertBox();
					String warning = "";
					for (int i = 0; i < warnings.size(); i++) {
						warning = warning + "\n" + warnings.get(i);
					}
					a.display(warning, warning);

				} else {

					MemberHelper helper = new MemberHelper();

					try {

						ResultMessage message = helper.getService()
								.deleteMember(

								member.getId());

						if (message != ResultMessage.SUCCESS) {

							String warning = "Unexcepted data fault";// 打印错误信息

							AlertBox a = new AlertBox();
							a.display(warning, warning);

							return;

						} else {
							AlertBox a = new AlertBox();
							a.display("删除成功", "删除成功");
							personlist.remove(index);
							ObservableList<Person> data = FXCollections
									.observableArrayList();

							for (int i = 0; i < personlist.size(); i++) {

								data.add(personlist.get(i));

							}
							t1.setItems(data);

						}

					} catch (RemoteException exception) {

						// TODO Auto-generated catch block

						exception.printStackTrace();

					}

				}

			}

		});

		// 编辑

		m3.setOnAction(new EventHandler<ActionEvent>() {// 修改

			@Override
			public void handle(ActionEvent e) {

				// 得到被选择的index,得到编号

				int index;

				index = t1.getSelectionModel().getSelectedIndex();

				// 写你的编辑事件，跳到一个编辑界面去,根据它的Type跳到你对应的新建的那个界面去
				MemberVO m = mylist.get(index);
				deviseMember d = new deviseMember();
				d.display(j, n, id, m);

			}

		});

		// 筛选

		tf1 = new TField();

		tf1.setMinHeight(25);

		tf1.setMaxHeight(25);

		tf1.setPrefWidth(202);

		tf1.setLayoutX(71);

		tf1.setLayoutY(250);

		// 在设置键盘筛选事件

		tf1.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {

				// TODO Auto-generated method stub

				if (event.getCode() == KeyCode.ENTER) {

				}
			}

		});

		ch1 = new CheckBox("编号");

		ch1.setLayoutX(269);

		ch1.setLayoutY(250);

		ch2 = new CheckBox("姓名");

		ch2.setLayoutX(342);

		ch2.setLayoutY(250);

		ch3 = new CheckBox("邮编");

		ch3.setLayoutX(417);

		ch3.setLayoutY(250);

		ch4 = new CheckBox("默认业务员");

		ch4.setLayoutX(479);

		ch4.setLayoutY(250);

		ch5 = new CheckBox("类型");

		ch5.setLayoutX(600);

		ch5.setLayoutY(250);

		ch7 = new CheckBox("等级");

		ch7.setLayoutX(679);

		ch7.setLayoutY(250);

		// 筛选

		tf1 = new TField();

		tf1.setMinHeight(25);

		tf1.setMaxHeight(25);

		tf1.setPrefWidth(202);

		tf1.setLayoutX(60);

		tf1.setLayoutY(250);

		a.getChildren().addAll(root, text1, text2, t1, tf1, ch1, ch2, ch3, ch4,

		ch5, ch7);

		Scene s = new Scene(a, 1080, 800);

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
	private void Handlesure(ActionEvent event) {

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

	private ArrayList<MemberVO> getMemberList() throws RemoteException {

		MemberHelper helper = MemberHelper.getInstance();

		return helper.getService().getMemberList();

	}

	@FXML
	private void HandleDelete(ActionEvent event) {// 删除的事件

		// 得到所有的被选择的员工

		ArrayList<MemberVO> dele = new ArrayList<>();

		for (int i = 0; i < mylist.size(); i++) {

			if (personlist.get(i).cb.isSelected()) {

				dele.add(mylist.get(i));

			}

		}

		ArrayList<String> warnings = new ArrayList<>(); // 存放错误信息

		for (MemberVO member : dele) {

			if (member.getPayment() != 0 || member.getReceivable() != 0) {

				String warning = "The member " + member.getName()

				+ "'s payment or receivable is not zero,you can't delete him";

				warnings.add(warning);

			}

		}

		if (!warnings.isEmpty()) {

			// 打印错误信息

		} else {

			MemberHelper helper = new MemberHelper();

			for (MemberVO member : dele) {

				try {

					ResultMessage message = helper.getService().deleteMember(

					member.getId());

					if (message != ResultMessage.SUCCESS) {

						String warning = "Unexcepted data fault";// 打印错误信息
						AlertBox a = new AlertBox();
						a.display(warning, warning);
						return;

					} else {
						AlertBox a = new AlertBox();
						a.display("删除成功", "删除成功");
						for (int i = 0; i < personlist.size(); i++) {
							if (personlist.get(i).cb.isSelected()) {
								personlist.remove(i);
								i = i - 1;
							}
						}
						ObservableList<Person> data = FXCollections
								.observableArrayList();

						for (int i = 0; i < personlist.size(); i++) {

							data.add(personlist.get(i));

						}
						t1.setItems(data);
					}

				} catch (RemoteException e) {

					// TODO Auto-generated catch block

					e.printStackTrace();

				}

			}

		}

	}
}

class Person {

	MemberVO m;

	checkbox cb = new checkbox();

	Person(MemberVO m) {

		this.m = m;

	}

	MemberVO getMember() {

		return m;

	}

}