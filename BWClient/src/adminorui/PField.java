package adminorui;

import javafx.event.EventHandler;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;

public class PField extends PasswordField{
	PField t1;

	public PField() {
		super();
		this.setMinHeight(30);
		this.setMaxHeight(30);
		t1 = this;
		t1.setStyle("-fx-border-color:Grey;-fx-border-radius:4;-fx-background-color:transparent");
		this.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				t1.setStyle("-fx-border-color:Blue;-fx-border-radius:4;-fx-background-color:null");
			}
		});
		this.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				t1.setStyle("-fx-border-color:Grey;-fx-border-radius:4;-fx-background-color:null");
			}
		});
	}
}
