package rak.plotPoint.controllers;

import java.util.logging.Logger;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import rak.plotPoint.model.Topic;

public class TopicDisplayController {
	private static final Logger LOG = Logger.getLogger(TopicDisplayController.class.getName()); 
	@FXML private TextField topicTitle;
	@FXML private TextArea topicDescription;
	
	private Topic topic;
	
	public void initialize(){
		topicTitle.textProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				topic.setName(newValue);
				
			}
		});
		topicDescription.textProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				topic.setText(newValue);
			}
		});
	}
	
	public void updateTopicWindow(Topic topic){
		this.topic = topic;
		topicTitle.setText(topic.getName());
		topicDescription.setText(topic.getText());
	}

}
