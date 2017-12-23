package rak.plotPoint.controllers;

import java.util.List;
import java.util.logging.Logger;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import rak.plotPoint.MainManager;
import rak.plotPoint.model.Reference;
import rak.plotPoint.model.Topic;
import rak.plotPoint.utility.gridHelper.GridHelper;
import rak.plotPoint.utility.gridHelper.ReferenceStrategy;

public class TopicDisplayController {
	private static final Logger LOG = Logger.getLogger(TopicDisplayController.class.getName()); 
	@FXML private TextField topicTitle;
	@FXML private TextArea topicDescription;
	@FXML private GridPane referenceGrid;
	
	private MainMenuController mainController;
	private MainManager manager;
	private Topic topic;
	private GridHelper<ReferenceStrategy, Reference> gridHelper;
	
	public void initialize(){
		gridHelper = new GridHelper<ReferenceStrategy, Reference>(referenceGrid, new ReferenceStrategy(this));
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
		topicDescription.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (KeyCode.CONTROL == event.getCode()){
					String selectedText = topicDescription.getSelectedText().trim();
					mainController.searchForTopic(selectedText);
				}
			}
		});
	}
	
	public void updateTopicWindow(Topic topic){
		this.topic = topic;
		topicTitle.setText(topic.getName());
		topicDescription.setText(topic.getText());
		searchForReferences(topic);
	}

	public void setMainController(MainMenuController mainMenuController) {
		this.mainController = mainMenuController;		
	}
	
	public void setManager(MainManager manager) {
		this.manager = manager;		
	}
	
	private void searchForReferences(Topic topic){
		List<Reference> references = manager.getReferences(topic);
		gridHelper.refreshList(references);
	}

}
