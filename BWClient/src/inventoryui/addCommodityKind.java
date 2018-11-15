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

public class addCommodityKind {// 新建商品分类

	private static String j;

	private static String n;

	private static Stage pStage;

	private static RadioButton r1;// 叶分类

	private static RadioButton r2;// 非叶分类

	private static ToggleGroup group;// 选择组

	private static TField t;// 输入商品分类名称

	private static String id;
	private static String f;

	@SuppressWarnings("unchecked")
	public void display(String id, TreeItem t1, String father) {
		
		f=father;

		// TODO Auto-generated method stub

		Stage primaryStage = new Stage();

		// 输入一个名称就可以

		AnchorPane a = new AnchorPane();

		a.setPrefWidth(100);

		a.setMinHeight(100);

		a.setMaxHeight(100);

		t = new TField();

		r1 = new RadioButton("叶分类");

		r2 = new RadioButton("非叶分类");

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

		Label l = new Label("商品分类名称");

		l.setLayoutX(60);

		l.setLayoutY(60);

		Button bu = new Button("确认");

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
				// a.display("添加成功", "添加成功");
				// primaryStage.close();

				CategoryHelper helper = CategoryHelper.getInstance();

				try {

					ResultMessage result = helper.getService().addCategory(vo);

					if (result == ResultMessage.SUCCESS) {

						// 新建成功
						AlertBox a = new AlertBox();
						a.display("新建成功", "新建成功");

					}

					else if (result == ResultMessage.CATEGORY_INSERT_FIAL_NAME_ALREADY_EXIST) {

						// 名称已存在，请重新输入
						AlertBox a = new AlertBox();
						a.display("名称已存在重新输入", "名称已存在重新输入");
					}

					else {

						// 新建失败

					}

				} catch (RemoteException e) {

					// TODO Auto-generated catch block

					e.printStackTrace();

				}

			}

		});

		Button b = new Button("取消");

		b.setLayoutX(150);

		b.setLayoutY(150);

		b.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				// 取消

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