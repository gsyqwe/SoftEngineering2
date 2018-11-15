package mainframeui;

import java.io.IOException;

import manganerui.EmailMan;
import manganerui.HelpMan;
import manganerui.PromotionByLevelui;
import manganerui.PromotionByCombinationui;
import manganerui.PromotionBySum;
import manganerui.businessconditionui;
import manganerui.businessprocessui;
import manganerui.operationlogui;
import manganerui.checkreportui;
import manganerui.salelistui;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

public class Manganer {
	static String j;
	static String n;
	static Stage pStage;
	private static String id;

	@SuppressWarnings("unchecked")
	public void display(String Job, String name, String ID) {// 总经理
		Stage primaryStage = new Stage();
		id = ID;
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("Manganer.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 审核单据
		ComboBox<String> comboBox = new ComboBox<String>();
		comboBox.setValue("审核单据    ");
		comboBox.getItems().addAll(// 含通过与未通过的以及未审核的
				"审核单据");
		comboBox.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener() {
					@Override
					public void changed(ObservableValue arg0, Object arg1,
							Object arg2) {
						// TODO Auto-generated method stub
						if (arg2.toString() == "审核单据") {
							checkreportui c = new checkreportui();
							try {
								c.display(Job, name, id);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							comboBox.setValue("审核单据");
						}
					}
				});
		comboBox.setLayoutX(86.0);
		comboBox.setLayoutY(95.0);
		comboBox.setPrefWidth(150.0);

		// 查看报表
		ComboBox<String> comboBox1 = new ComboBox<String>();
		comboBox1.setValue("查看报表              ");
		comboBox1.getItems().addAll("查看经营历程表", "查看经营情况表", "查看销售明细表");
		comboBox1.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener() {
					@Override
					public void changed(ObservableValue arg0, Object arg1,
							Object arg2) {
						// TODO Auto-generated method stub
						if (arg2.toString() == "查看经营历程表") {
							businessprocessui b = new businessprocessui();
							try {
								b.display(Job, name, id);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else if (arg2.toString() == "查看经营情况表") {
							businessconditionui b = new businessconditionui();
							try {
								b.display(Job, name, id);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else if (arg2.toString() == "查看销售明细表") {
							salelistui b = new salelistui();
							try {
								b.display(Job, name, id);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				});
		comboBox1.setLayoutX(236.0);
		comboBox1.setLayoutY(95.0);
		comboBox1.setPrefWidth(150.0);

		// 制定促销策略
		ComboBox<String> comboBox2 = new ComboBox<String>();
		comboBox2.setValue("制定促销策略");
		comboBox2.getItems().addAll("制定不同用户等级促销策略", "制定组合商品包促销策略",
				"制定优惠总价促销策略", "查看促销策略");
		comboBox2.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener() {
					@Override
					public void changed(ObservableValue arg0, Object arg1,
							Object arg2) {
						// TODO Auto-generated method stub
						if (arg2.toString() == "制定不同用户等级促销策略") {
							PromotionByLevelui p = new PromotionByLevelui();
							try {
								p.display(Job, name, id);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else if (arg2.toString() == "制定组合商品包促销策略") {
							PromotionByCombinationui p = new PromotionByCombinationui();
							try {
								p.display(Job, name, id);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else if (arg2.toString() == "制定优惠总价促销策略") {
							PromotionBySum p = new PromotionBySum();
							try {
								p.display(Job, name, id);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				});
		comboBox2.setLayoutX(386.0);
		comboBox2.setLayoutY(95.0);
		comboBox2.setPrefWidth(150.0);

		// 查看系统操作日志
		ComboBox<String> comboBox3 = new ComboBox<String>();
		comboBox3.setValue("查看系统操作日志");
		comboBox3.getItems().addAll("查看全部系统日志");
		comboBox3.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener() {
					@Override
					public void changed(ObservableValue arg0, Object arg1,
							Object arg2) {
						// TODO Auto-generated method stub
						if (arg2.toString() == "查看全部系统日志") {
							operationlogui c = new operationlogui();
							try {
								c.display(Job, name, id);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				});
		comboBox3.setLayoutX(536.0);
		comboBox3.setLayoutY(95.0);
		comboBox3.setPrefWidth(182.0);

		AnchorPane a = new AnchorPane();
		// 左边
		TextField t2 = new TextField();
		t2.setLayoutX(54.0);
		t2.setEditable(false);
		t2.setLayoutY(456.0);
		t2.setMinHeight(30.0);
		t2.setMaxHeight(30.0);
		t2.setPrefWidth(362.0);
		t2.setStyle("-fx-background-color: null");
		// 右边
		TextField t3 = new TextField();
		t3.setStyle("-fx-background-color:null");
		t3.setEditable(false);
		t3.setLayoutX(468.0);
		t3.setLayoutY(456.0);
		t3.setMinHeight(30.0);
		t3.setMaxHeight(30.0);
		t3.setPrefWidth(327.0);

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

		Text te = new Text("查看报表");
		te.setLayoutX(90);
		te.setLayoutY(330);
		te.setFont(Font.font(20));

		Text te1 = new Text("审核报表");
		te1.setLayoutX(257);
		te1.setLayoutY(330);
		te1.setFont(Font.font(20));

		Text te2 = new Text("制定促销策略");
		te2.setLayoutX(412);
		te2.setLayoutY(330);
		te2.setFont(Font.font(20));

		Text te3 = new Text("查看操作日志");
		te3.setLayoutX(595);
		te3.setLayoutY(330);
		te3.setFont(Font.font(20));

		a.getChildren().addAll(root, text1, text2, t2, t3, comboBox, comboBox1,
				comboBox2, comboBox3, te, te1, te2, te3);

		Scene s3 = new Scene(a, 800, 500);
		primaryStage.setTitle("总经理");
		primaryStage.setScene(s3);
		j = Job;
		n = name;
		pStage = primaryStage;
		primaryStage.show();
	}

	@FXML
	private void HandleMail(ActionEvent event) {
		EmailMan e = new EmailMan();
		e.display(j, n, id);
	}

	@FXML
	private void HandleOut(ActionEvent event) {
		pStage.close();
		Log l = new Log();
		try {
			l.start(new Stage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	private void HandleEsc(ActionEvent event) {
		pStage.close();
		Log l = new Log();
		try {
			l.start(new Stage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	private void HandleCheck(ActionEvent event) {// 查看报表
		businessconditionui b = new businessconditionui();
		b.display(j, n, id);
	}

	@FXML
	private void HandleReport(ActionEvent event) {// 审核报表
		checkreportui c = new checkreportui();
		c.display(j, n, id);
	}

	@FXML
	private void HandlePromotion(ActionEvent event) {// 制定促销策略
		PromotionByLevelui p = new PromotionByLevelui();
		p.display(j, n, id);
	}

	@FXML
	private void HandleLog(ActionEvent event) {// 查看系统操作日志
		operationlogui o = new operationlogui();
		o.display(j, n, id);
	}

	@FXML
	private void HandleHelp(ActionEvent event) {
		HelpMan h = new HelpMan();
		h.display(j, n, id);
	}

	@FXML
	private void HandleDevisePassword(ActionEvent event) {
		devisePassword d = new devisePassword();
		d.display(j, n, id);
	}
}