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
	private static TableColumn<Person, String> c1;// Ա������
	private static TableColumn<Person, String> c2;// Ա�����
	private static TableColumn<Person, String> c3;// Ա��ְ��
	private static TableColumn<Person, CheckBox> c4;// ����ѡ��ĸ�ѡ��ť
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
		// �õ����е�UserList
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
		Text t = new Text("��ӭ��:" + name);
		t.setLayoutX(960.0);
		t.setLayoutY(68.0);
		t.setFont(Font.font("Verdana", 18));
		t.setStyle("-fx-background-color:null");

		// ���TableView����,���б���ʼ��,Ҫ��������Radio Button,��Ա�����е�ѡ��������ɾ��
		table = new<UserVO> TableView();
		table.setLayoutX(80.0);
		table.setLayoutY(270.0);
		table.setMinHeight(400);
		table.setMaxHeight(400);
		table.prefWidth(910.0);

		c1 = new<Person, String> TableColumn("Ա������");
		c1.setPrefWidth(291.0);

		c2 = new<Person, String> TableColumn("Ա�����");
		c2.setPrefWidth(317.0);

		c3 = new<Person, String> TableColumn("Ա��ְ��");
		c3.setPrefWidth(288.0);
		// ������ʾ
		c4 = new<Person, CheckBox> TableColumn();
		c4.setPrefWidth(30);
		ObservableList<Person> data = FXCollections.observableArrayList();
		for (int i = 0; i < mylist1.size(); i++) {
			data.add(mylist1.get(i));
		}
		c1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData
				.getValue().getUser().getName()));// ����
		c2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData
				.getValue().getUser().getId()));// ���
		c3.setCellValueFactory(cellData -> new SimpleStringProperty(cellData
				.getValue().getUser().getJobName()));// ְ��
		c4.setCellValueFactory(cellData -> cellData.getValue().cb.getCheckBox());
		table.getColumns().addAll(c1, c2, c3, c4);
		table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		table.setStyle("-fx-background-color:null");
		table.setItems(data);

		// �����Ҽ���ť
		ContextMenu cme = new ContextMenu();
		// �����Ҽ��˵���һЩ��
		MenuItem m1 = new MenuItem("ɾ��Ա��");
		cme.getItems().add(m1);
		MenuItem m2 = new MenuItem("�޸�����");
		cme.getItems().add(m2);
		MenuItem m3 = new MenuItem("�޸�����");
		cme.getItems().add(m3);
		table.setContextMenu(cme);
		m1.setOnAction(new EventHandler<ActionEvent>() {// ɾ��
			public void handle(ActionEvent e) {
				// �õ���ѡ���index,�õ����
				int index;
				index = table.getSelectionModel().getSelectedIndex();
				UserVO u = mylist.get(index);// �õ���ӦIndex��UserVO
				UserHelper helper = UserHelper.getInstance();
				OperationLogHelper logHelper = OperationLogHelper.getInstance();
				ResultMessage result;
				try {
					result = helper.getService().deleteUser(u.getId());
					if (result == ResultMessage.USER_DELETE_SUCCESS) {
						// LogVO vo = new LogVO();
						// vo.setUserID(id);
						// vo.setTime(new Date());
						// String content = "ɾ���û���" + u.getId();
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
				// ɾ������Ҫ��ʾɾ����Ľ��
				ObservableList<Person> data = FXCollections
						.observableArrayList();
				for (int i = 0; i < mylist1.size(); i++) {
					data.add(mylist1.get(i));
				}
				table.setItems(data);
			}
		});
		m2.setOnAction(new EventHandler<ActionEvent>() {// �޸�ְ��
			public void handle(ActionEvent e) {
				// �õ���ѡ���index,�õ����
				int index;
				index = table.getSelectionModel().getSelectedIndex();
				UserVO u = mylist.get(index);// �õ���ӦIndex��UserVO
				devise d = new devise();
				d.deviseJob(Job, name, ID, u, table);
				// Ȼ����ʾ�µ���Ϣ
			}
		});
		m3.setOnAction(new EventHandler<ActionEvent>() {// �޸�����
			public void handle(ActionEvent e) {
				// �õ���ѡ���index,�õ����
				int index;
				index = table.getSelectionModel().getSelectedIndex();
				UserVO u = mylist.get(index);// �õ���ӦIndex��UserVO
				// ֻ�޸�����
				devise d = new devise();
				d.devisePassword(Job, name, ID, u);
			}
		});

		Text t1 = new Text("��ǰְ��:" + Job);
		t1.setLayoutX(940.0);
		t1.setLayoutY(90.0);
		t1.setFont(Font.font("Verdana", 18));
		t1.setStyle("-fx-background-color:null");

		a.getChildren().addAll(root, t, t1, table);

		Scene s = new Scene(a, 1080, 800);
		primaryStage.setTitle("����Ա");
		primaryStage.setScene(s);
		pStage = primaryStage;
		primaryStage.show();
	}

	@FXML
	private void HandleHelp(ActionEvent event) { // ������ť
		HelpAD a = new HelpAD();
		a.display(j, n, id);
	}

	@FXML
	private void HandleESC(ActionEvent event) { // �˳�
		pStage.close();
	}

	@FXML
	private void HandleOut(ActionEvent event) {// �˳�
		pStage.close();
	}

	@FXML
	public void HandleDelete(ActionEvent event) throws RemoteException {
		// �Ȼ�ñ�ѡ���Ҫɾ����Ա����ArrayList
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
				mistake = mistake + "��" + (i + 1) + "��ɾ��ʧ��\n";
			} else {
			}
		}
		if (mistake.equals("")) {
			// ��ʾ��ɾ���ɹ���
			AlertBox alert = new AlertBox();
			alert.display("ɾ���ɹ�", "ɾ���ɹ�");
		} else {
			// ��ʾmistake������
		}
		// ɾ���ArrayList��Ҫ���ҵ�
		// ɾ���껹Ҫ������ʾ
		// ��Ҫ��ObserValue�����
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
				String content = "ɾ���û���" + username;
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
