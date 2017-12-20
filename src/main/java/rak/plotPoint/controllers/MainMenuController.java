package rak.plotPoint.controllers;


import java.util.List;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import rak.plotPoint.MainManager;
import rak.plotPoint.model.Topic;

/**
 * TODO - eventually break this out to control smaller chunks
 *
 */
public class MainMenuController {
	private static final Logger LOG = Logger.getLogger(MainMenuController.class.getName()); 
	private static final int NUMBER_OF_HEADER_ROWS = 1;
	
	@FXML private TextField topicTitle;
	@FXML private TextArea topicDescription;
	@FXML private Button saveBtn;
	@FXML private Button loadBtn;
	@FXML private GridPane peopleGrid;
	@FXML private GridPane locationGrid;
	@FXML private GridPane plotPointGrid;
	
	private MainManager manager = new MainManager();
	
	public void initialize(){
		LOG.info("Initializing main manager");
		manager.runLoadTest();
		displayTopics();
	}
	
	@FXML
	private void exit(){
		Platform.exit();
		System.exit(0);
	}
	
	private void displayTopics(){
		refreshList(peopleGrid, manager.getPeopleTopics());
		refreshList(locationGrid, manager.getLocationTopics());
		refreshList(plotPointGrid, manager.getPlotPointTopics());
		
	}
	
	private void refreshList(GridPane grid, List<Topic> topics){
		if (grid != null){
			clearAllRowsButHeader(grid);
			addRows(grid, topics);
		}
	}
	
	private void clearAllRowsButHeader(GridPane grid) {
		for (int i=grid.getChildren().size()-1; i>0; i--){
			Node child = grid.getChildren().get(i);
			if (isBodyRow(child)){
				grid.getChildren().remove(i);
			}
		}
	}
	
	private boolean isBodyRow(Node child){
		Integer row = GridPane.getRowIndex(child);
		return row != null && row > NUMBER_OF_HEADER_ROWS-1;
	}

	private void addRows(GridPane grid, List<Topic> topics) {
		
		for (int i=0; i<topics.size(); i++){
			Topic topic = topics.get(i);
			int row = i + NUMBER_OF_HEADER_ROWS;
			addRow(grid, row, topic);
		}
	}

	private void addRow(GridPane grid, int row, final Topic topic) {
		Button btn = new Button(topic.getName());
		btn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				LOG.info("Clicked button for " + topic.getName());
			}
		});
			
		GridPane.setRowIndex(btn, row);
		GridPane.setColumnIndex(btn, 0);
		grid.getChildren().add(btn);
	}
	

}
