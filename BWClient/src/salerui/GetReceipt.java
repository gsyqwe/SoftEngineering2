package salerui;

import java.io.IOException;
import java.util.ArrayList;

import VO.ReceiptVO;
import VO.SalesReceiptVO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GetReceipt {
	static String j;
	static String n;
	static Stage pStage;
	private static String id;
	private static ArrayList<SalesReceiptVO> mylist = new ArrayList<>();// �õ����еĵ���
	private static RadioButton r1;
	private static RadioButton r2;
	private static RadioButton r3;
	private static RadioButton r4;
	private static ToggleGroup group;
	private static TableView<SalesReceiptVO> t;// ��ʾȫ�����б�
	private static TableColumn<SalesReceiptVO, String> t1;// ��������
	private static TableColumn<SalesReceiptVO, String> t2;// �����ƶ�ʱ��
	private static TableColumn<SalesReceiptVO, String> t3;// ��������

	@SuppressWarnings("unchecked")
	public void display(String Job, String name, String ID) {
		// TODO Auto-generated method stub
		Stage primaryStage = new Stage();
		id = ID;
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("GetReceipt.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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

		// �б���ʾ
		t = new TableView();
		t.setMinHeight(165);
		t.setMaxHeight(165);
		t.setPrefWidth(634);
		t.setLayoutX(87);
		t.setLayoutY(190);
		t.setStyle("-fx-background-color:null");
		// ��Ӿ�������
		t1 = new TableColumn("��������");
		t1.setPrefWidth(110);
		t2 = new TableColumn("�ƶ�ʱ��");
		t2.setPrefWidth(110);
		t3 = new TableColumn("��������");
		t3.setPrefWidth(414);
		t.getColumns().addAll(t1, t2, t3);
		// �����Ҽ���ť
		ContextMenu cme = new ContextMenu();
		// �����Ҽ��˵���һЩ��
		MenuItem m1 = new MenuItem("�ƶ�������");
		cme.getItems().add(m1);
		MenuItem m2 = new MenuItem("�ƶ������˻���");
		cme.getItems().add(m2);
		MenuItem m3 = new MenuItem("�ƶ����۵�");
		cme.getItems().add(m3);
		MenuItem m4 = new MenuItem("�ƶ������˻���");
		cme.getItems().add(m4);
		t.setContextMenu(cme);
		// ��������¼�
		m1.setOnAction(new EventHandler<ActionEvent>() {// �ƶ�������
			public void handle(ActionEvent e) {
				// �õ���ѡ���index,�õ����
				int index;
				index = t.getSelectionModel().getSelectedIndex();
				// д���ɾ���¼�
			}
		});
		m2.setOnAction(new EventHandler<ActionEvent>() {// �ƶ������˻���
			public void handle(ActionEvent e) {
				// �õ���ѡ���index,�õ����
				int index;
				index = t.getSelectionModel().getSelectedIndex();
				// д��ı༭�¼�
			}
		});
		m3.setOnAction(new EventHandler<ActionEvent>() {// �ƶ����۵�
			public void handle(ActionEvent e) {
				// �õ���ѡ���index,�õ����
				int index;
				index = t.getSelectionModel().getSelectedIndex();
				// д��ı༭�¼�
			}
		});
		m4.setOnAction(new EventHandler<ActionEvent>() {// �ƶ������˻���
			public void handle(ActionEvent e) {
				// �õ���ѡ���index,�õ����
				int index;
				index = t.getSelectionModel().getSelectedIndex();
				// д��ı༭�¼�
			}
		});

		// ���õ�ѡ��������ɸѡ
		r1 = new RadioButton("���۵�");
		r2 = new RadioButton("�����˻���");
		r3 = new RadioButton("������");
		r4 = new RadioButton("�����˻���");
		r1.setLayoutX(869);
		r1.setLayoutY(240);
		r2.setLayoutX(966);
		r2.setLayoutY(240);
		r3.setLayoutX(869);
		r3.setLayoutY(270);
		r4.setLayoutX(966);
		r4.setLayoutY(270);
		group = new ToggleGroup();
		r1.setToggleGroup(group);
		r2.setToggleGroup(group);
		r3.setToggleGroup(group);
		r4.setToggleGroup(group);
		group.selectedToggleProperty().addListener(
				new ChangeListener<Toggle>() {
					public void changed(ObservableValue<? extends Toggle> ov,
							Toggle toggle, Toggle new_toggle) {
						if (r1.isSelected()) {
							
						} else if (r2.isSelected()) {
						} else if (r3.isSelected()) {

						} else if (r4.isSelected()) {

						}
					}
				});

		AnchorPane a = new AnchorPane();
		a.getChildren().addAll(root, text1, text2, t, r1, r2, r3, r4);
		Scene s = new Scene(a, 800, 500);
		primaryStage.setTitle("������Ա");
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
	private void HandleSure(ActionEvent event) {
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
}
