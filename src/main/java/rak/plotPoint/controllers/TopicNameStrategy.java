package rak.plotPoint.controllers;

import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import rak.plotPoint.utility.gridHelper.RowStrategy;

public class TopicNameStrategy implements RowStrategy<TopicParam>{
	private static final Logger LOG = Logger.getLogger(TopicNameStrategy.class.getName()); 
	private TopicDisplayController topicDisplayController;
	
	public TopicNameStrategy(TopicDisplayController topicDisplayController){
		this.topicDisplayController = topicDisplayController;
	}

	public Node getNode(final TopicParam param) {
		Button btn = new Button(param.getTopic().getName());
		btn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				LOG.info("Clicked button for " + param.getTopic().getName());
				topicDisplayController.updateTopicWindow(param.getTopic());
			}
		});
		return btn;
	}
	
	

}
