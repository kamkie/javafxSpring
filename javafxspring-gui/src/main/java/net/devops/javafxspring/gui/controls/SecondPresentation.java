package net.devops.javafxspring.gui.controls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import net.devops.javafxspring.gui.config.ScreensConfig;
import net.devops.javafxspring.gui.model.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;

public class SecondPresentation extends Presentation {

	public SecondPresentation(ScreensConfig config) {
		super(config);
	}
	
	@Autowired
	private MessageModel model;
	
	@FXML
	TextField messageTf;
	
	@FXML
	void initialize() {
		messageTf.setText(model.getMessage());
		
		model.addObserver((o, arg) -> messageTf.setText(model.getMessage()));
	}
	
	@FXML 
	void onApply(ActionEvent event){
		model.setMessage(messageTf.getText());
	}
	
	@FXML
	void prevView(ActionEvent event){
		config.loadFirst();
	}
	
	@FXML 
	void openPopup(ActionEvent event){
		config.loadPopup();
	}
	

}
