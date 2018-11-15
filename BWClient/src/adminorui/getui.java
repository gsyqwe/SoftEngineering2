package adminorui;

import inventoryui.checkbox;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mainframeui.AlertBox;
import rmi.OperationLogHelper;
import rmi.UserHelper;
import VO.LogVO;
import VO.UserVO;
import enums.JobType;
import enums.ResultMessage;

public class getui {
	private static String j;
	private static String n;
	private static Stage pStage;
	private static ArrayList<UserVO> mylist = new ArrayList<>();
	private static ArrayList<Person> mylist1 = new ArrayList<>();
	private static TableView<Person> table;
	private static TableColumn<Person, String> c1;// 员工姓名
	private static TableColumn<Person, String> c2;// 员工编号
	private static TableColumn<Person, String> c3;// 员工职务
	private static TableColumn<Person, CheckBox> c4;// 批量选择的复选按钮
	private static String id;

	public void display(String Job, String name, String ID) {
		j = Job;
		n = name;
		id = ID;
		mylist.clear();
		mylist1.clear();
		Stage primaryStage = new Stage();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("getui.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 得到所有的UserList
		getui g = new getui();
		try {
			mylist = g.getUserList();
		} catch (RemoteException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		for (int i = 0; i < mylist.size(); i++) {
			if (mylist.get(i).getId().equals(id)) {
				mylist.remove(i);
			}
		}
		for (int i = 0; i < mylist.size(); i++) {
			Person p = new Person(mylist.get(i));
			mylist1.add(p);
		}
		AnchorPane a = new AnchorPane();
		a.prefHeight(800);
		a.prefWidth(1080);
		Text t = new Text("欢迎您:" + name);
		t.setLayoutX(960.0);
		t.setLayoutY(68.0);
		t.setFont(Font.font("Verdana", 18));
		t.setStyle("-fx-background-color:null");

		// 添加TableView进入,进行表格初始化,要给表格添加Radio Button,对员工进行单选，来进行删除
		table = new<UserVO> TableView();
		table.setLayoutX(80.0);
		table.setLayoutY(270.0);
		table.setMinHeight(400);
		table.setMaxHeight(400);
		table.prefWidth(910.0);

		c1 = new<Person, String> TableColumn("员工姓名");
		c1.setPrefWidth(291.0);

		c2 = new<Person, String> TableColumn("员工编号");
		c2.setPrefWidth(317.0);

		c3 = new<Person, String> TableColumn("员工职务");
		c3.setPrefWidth(288.0);
		// 用来显示
		c4 = new<Person, CheckBox> TableColumn();
		c4.setPrefWidth(30);
		ObservableList<Person> data = FXCollections.observableArrayList();
		for (int i = 0; i < mylist1.size(); i++) {
			data.add(mylist1.get(i));
		}
		c1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData
				.getValue().getUser().getName()));// 姓名
		c2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData
				.getValue().getUser().getId()));// 编号
		c3.setCellValueFactory(cellData -> new SimpleStringProperty(cellData
				.getValue().getUser().getJobName()));// 职务
		c4.setCellValueFactory(cellData -> cellData.getValue().cb.getCheckBox());
		table.getColumns().addAll(c1, c2, c3, c4);
		table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		table.setStyle("-fx-background-color:null");
		table.setItems(data);

		// 增加右键按钮
		ContextMenu cme = new ContextMenu();
		// 增加右键菜单的一些项
		MenuItem m1 = new MenuItem("删除员工");
		cme.getItems().add(m1);
		MenuItem m2 = new MenuItem("修改资料");
		cme.getItems().add(m2);
		MenuItem m3 = new MenuItem("修改密码");
		cme.getItems().add(m3);
		table.setContextMenu(cme);
		m1.setOnAction(new EventHandler<ActionEvent>() {// 删除
			public void handle(ActionEvent e) {
				// 得到被选择的index,得到编号
				int index;
				index = table.getSelectionModel().getSelectedIndex();
				UserVO u = mylist.get(index);// 得到对应Index的UserVO
				UserHelper helper = UserHelper.getInstance();
				OperationLogHelper logHelper = OperationLogHelper.getInstance();
				ResultMessage result;
				try {
					result = helper.getService().deleteUser(u.getId());
					if (result == ResultMessage.USER_DELETE_SUCCESS) {
						// LogVO vo = new LogVO();
						// vo.setUserID(id);
						// vo.setTime(new Date());
						// String content = "删除用户：" + u.getId();
						// vo.setContent(content);
						// ResultMessage logResult = logHelper.getService()
						// .add(vo);
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				mylist.remove(index);
				mylist1.remove(index);
				// 删除完了要显示删除后的结果
				ObservableList<Person> data = FXCollections
						.observableArrayList();
				for (int i = 0; i < mylist1.size(); i++) {
					data.add(mylist1.get(i));
				}
				table.setItems(data);
			}
		});
		m2.setOnAction(new EventHandler<ActionEvent>() {// 修改职务
			public void handle(ActionEvent e) {
				// 得到被选择的index,得到编号
				int index;
				index = table.getSelectionModel().getSelectedIndex();
				UserVO u = mylist.get(index);// 得到对应Index的UserVO
				devise d = new devise();
				d.deviseJob(Job, name, ID, u, table);
				// 然后显示新的信息
			}
		});
		m3.setOnAction(new EventHandler<ActionEvent>() {// 修改密码
			public void handle(ActionEvent e) {
				// 得到被选择的index,得到编号
				int index;
				index = table.getSelectionModel().getSelectedIndex();
				UserVO u = mylist.get(index);// 得到对应Index的UserVO
				// 只修改密码
				devise d = new devise();
				d.devisePassword(Job, name, ID, u);
			}
		});

		Text t1 = new Text("当前职务:" + Job);
		t1.setLayoutX(940.0);
		t1.setLayoutY(90.0);
		t1.setFont(Font.font("Verdana", 18));
		t1.setStyle("-fx-background-color:null");

		a.getChildren().addAll(root, t, t1, table);

		Scene s = new Scene(a, 1080, 800);
		primaryStage.setTitle("管理员");
		primaryStage.setScene(s);
		pStage = primaryStage;
		primaryStage.show();
	}

	@FXML
	private void HandleHelp(ActionEvent event) { // 帮助按钮
		HelpAD a = new HelpAD();
		a.display(j, n, id);
	}

	@FXML
	private void HandleESC(ActionEvent event) { // 退出
		pStage.close();
	}

	@FXML
	private void HandleOut(ActionEvent event) {// 退出
		pStage.close();
	}

	@FXML
	public void HandleDelete(ActionEvent event) throws RemoteException {
		// 先获得被选择的要删除的员工的ArrayList
		ArrayList<UserVO> delete = new ArrayList();
		for (int i = 0; i < mylist1.size(); i++) {
			if (mylist1.get(i).cb.isSelected()) {
				delete.add(mylist.get(i));
			}
		}
		String mistake = "";
		for (int i = 0; i < delete.size(); i++) {
			ResultMessage result = delete(delete.get(i).getId(), delete.get(i)
					.getName());
			for(int j=0;j<mylist.size();j++){
				if(mylist.get(j).getId().equals(delete.get(i).getId())){
					mylist.remove(j);
				}
			}
			if (result != ResultMessage.SUCCESS) {
				mistake = mistake + "第" + (i + 1) + "行删除失败\n";
			} else {
			}
		}
		if (mistake.equals("")) {
			// 显示“删除成功”
			AlertBox alert = new AlertBox();
			alert.display("删除成功", "删除成功");
		} else {
			// 显示mistake的内容
		}
		// 删完的ArrayList你要给我的
		// 删除完还要继续显示
		// 需要用ObserValue来添加
		mylist1.clear();
		for (int i = 0; i < mylist.size(); i++) {
			Person p = new Person(mylist.get(i));
			mylist1.add(p);
		}
		ObservableList<Person> data = FXCollections.observableArrayList();
		for (int i = 0; i < mylist1.size(); i++) {
			data.add(mylist1.get(i));
		}
		table.setItems(data);
	}

	@FXML
	public void HandleCancle(ActionEvent event) {
		mylist.clear();
		mylist1.clear();
		pStage.close();
	}

	public ResultMessage delete(String userid, String username) {
		UserHelper helper = UserHelper.getInstance();
		OperationLogHelper logHelper = OperationLogHelper.getInstance();
		ResultMessage result = null;
		try {
			result = helper.getService().deleteUser(userid);
			if (result == ResultMessage.SUCCESS) {
				LogVO vo = new LogVO();
				vo.setUserID(id);
				vo.setId("");
				vo.setTime(new Date());
				String content = "删除用户：" + username;
				vo.setContent(content);
				ResultMessage logResult = logHelper.getService().add(vo);
			}
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return result;
	}

	public ArrayList<UserVO> getUserList() throws RemoteException {
		UserHelper helper = UserHelper.getInstance();
		ArrayList<UserVO> vo = helper.getService().getAllList();
		return vo;
	}
}

class Person {
	private UserVO u = new UserVO();
	public checkbox cb = new checkbox();

	Person(UserVO u1) {
		u.setName(u1.getName());
		u.setJob(u1.getJob());
		u.setId(u1.getId());
		u.setPassword(u1.getPassword());
	}

	public UserVO getUser() {
		return u;
	}
}
