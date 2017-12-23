package rak.plotPoint.controllers;


import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import rak.plotPoint.MainManager;
import rak.plotPoint.model.Topic;
import rak.plotPoint.utility.gridHelper.GridHelper;

/**
 * TODO - eventually break this out to control smaller chunks
 *
 */
public class MainMenuController {
	private static final Logger LOG = Logger.getLogger(MainMenuController.class.getName()); 
	
	@FXML private GridPane peopleGrid;
	@FXML private GridPane locationGrid;
	@FXML private GridPane plotPointGrid;
	
	@FXML private TopicDisplayController topicDisplayController = new TopicDisplayController();
	
	private MainManager manager = new MainManager();
	
	private GridHelper<TopicNameStrategy, TopicParam> peopleGridHelper;
	private GridHelper<TopicNameStrategy, TopicParam> locationGridHelper;
	private GridHelper<TopicNameStrategy, TopicParam> plotPointGridHelper;
	
	public void initialize(){
		LOG.info("Initializing main manager");
		
		topicDisplayController.setMainController(this);
		topicDisplayController.setManager(manager);
		
		peopleGridHelper = new GridHelper<TopicNameStrategy, TopicParam>(peopleGrid, new TopicNameStrategy(topicDisplayController));
		locationGridHelper = new GridHelper<TopicNameStrategy, TopicParam>(locationGrid, new TopicNameStrategy(topicDisplayController));
		plotPointGridHelper = new GridHelper<TopicNameStrategy, TopicParam>(plotPointGrid, new TopicNameStrategy(topicDisplayController));
		
		
		manager.runLoadTest();
		displayTopics();
		topicDisplayController.updateTopicWindow(manager.getPeopleTopics().get(0));
	}
	
	@FXML
	private void exit(){
		Platform.exit();
		System.exit(0);
	}
	
	@FXML
	private void save(){
		manager.save();
	}
	
	@FXML
	private void load(){
		manager.load();
	}
	
	/*
	 * Given some text, read through list of topics and display the closest match
	 */
	public void searchForTopic(String text){
		Topic topic = manager.getTopic(text);
		if (topic != null){
			topicDisplayController.updateTopicWindow(topic);
		}
	}
	
	private void displayTopics(){
		peopleGridHelper.refreshList(TopicParam.fromTopics(manager.getPeopleTopics()));
		locationGridHelper.refreshList(TopicParam.fromTopics(manager.getLocationTopics()));
		plotPointGridHelper.refreshList(TopicParam.fromTopics(manager.getPlotPointTopics()));
		
	}
	
	
	
}
