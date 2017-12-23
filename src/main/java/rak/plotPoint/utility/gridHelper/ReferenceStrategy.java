package rak.plotPoint.utility.gridHelper;

import java.util.logging.Logger;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import rak.plotPoint.controllers.TopicDisplayController;
import rak.plotPoint.controllers.TopicNameStrategy;
import rak.plotPoint.model.Reference;

public class ReferenceStrategy implements RowStrategy<Reference>{
	private static final Logger LOG = Logger.getLogger(TopicNameStrategy.class.getName()); 
	private TopicDisplayController topicDisplayController;
	
	public ReferenceStrategy(TopicDisplayController topicDisplayController){
		this.topicDisplayController = topicDisplayController;
	}
	
	public Node getNode(final Reference param) {
		Text text = new Text(param.getExcerpt());
		text.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				topicDisplayController.updateTopicWindow(param.getTopic());
			}
		});
		return text;
	}

}
