package manganerui;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class TField extends TextField {

	TField t1;

	public TField() {
		super();
		this.setMinHeight(35);
		this.setMaxHeight(35);
		t1 = this;
		t1.setStyle("-fx-border-color:Grey;-fx-border-radius:4;-fx-background-color:null");
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