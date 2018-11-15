package inventoryui;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import mainframeui.AlertBox;
import VO.CategoryVO;
import enums.CategoryType;
import enums.ResultMessage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import rmi.CategoryHelper;

public class addCommodityKind {// �½���Ʒ����

	private static String j;

	private static String n;

	private static Stage pStage;

	private static RadioButton r1;// Ҷ����

	private static RadioButton r2;// ��Ҷ����

	private static ToggleGroup group;// ѡ����

	private static TField t;// ������Ʒ��������

	private static String id;
	private static String f;

	@SuppressWarnings("unchecked")
	public void display(String id, TreeItem t1, String father) {
		
		f=father;

		// TODO Auto-generated method stub

		Stage primaryStage = new Stage();

		// ����һ�����ƾͿ���

		AnchorPane a = new AnchorPane();

		a.setPrefWidth(100);

		a.setMinHeight(100);

		a.setMaxHeight(100);

		t = new TField();

		r1 = new RadioButton("Ҷ����");

		r2 = new RadioButton("��Ҷ����");

		r1.setLayoutX(30);

		r1.setLayoutY(20);

		r2.setLayoutX(120);

		r2.setLayoutY(20);

		r2.setSelected(true);

		group = new ToggleGroup();

		r1.setToggleGroup(group);

		r2.setToggleGroup(group);

		t.setLayoutX(30);

		t.setLayoutY(100);

		Label l = new Label("��Ʒ��������");

		l.setLayoutX(60);

		l.setLayoutY(60);

		Button bu = new Button("ȷ��");

		bu.setLayoutX(50);

		bu.setLayoutY(150);

		bu.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				CategoryVO vo = new CategoryVO();

				vo.setName(t.getText());

				if (r1.isSelected())

					vo.setType(CategoryType.LEAF);

				else if (r2.isSelected())

					vo.setType(CategoryType.NODE);

				vo.setFather(f);

				TreeItem tree = new TreeItem(t.getText());
				t1.getChildren().add(tree);
				// AlertBox a = new AlertBox();
				// a.display("��ӳɹ�", "��ӳɹ�");
				// primaryStage.close();

				CategoryHelper helper = CategoryHelper.getInstance();

				try {

					ResultMessage result = helper.getService().addCategory(vo);

					if (result == ResultMessage.SUCCESS) {

						// �½��ɹ�
						AlertBox a = new AlertBox();
						a.display("�½��ɹ�", "�½��ɹ�");

					}

					else if (result == ResultMessage.CATEGORY_INSERT_FIAL_NAME_ALREADY_EXIST) {

						// �����Ѵ��ڣ�����������
						AlertBox a = new AlertBox();
						a.display("�����Ѵ�����������", "�����Ѵ�����������");
					}

					else {

						// �½�ʧ��

					}

				} catch (RemoteException e) {

					// TODO Auto-generated catch block

					e.printStackTrace();

				}

			}

		});

		Button b = new Button("ȡ��");

		b.setLayoutX(150);

		b.setLayoutY(150);

		b.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				// ȡ��

				primaryStage.close();

			}

		});

		ImageView i1 = new ImageView(new Image("/backgroud.png", true));

		i1.setFitWidth(300);

		i1.setFitHeight(200);

		a.getChildren().addAll(i1, r1, r2, t, bu, b, l);

		Scene s = new Scene(a, 300, 200);

		primaryStage.setScene(s);

		pStage = primaryStage;

		primaryStage.show();

	}

}