package rak.plotPoint;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import rak.plotPoint.model.Topic;
import rak.plotPoint.model.TopicType;
import rak.plotPoint.persistance.DAO;
import rak.plotPoint.persistance.JsonDao;

public class MainManager {
	private static final Logger LOG = Logger.getLogger(MainManager.class.getName()); 
	private DAO dao = new JsonDao();
	private List<Topic> people = new ArrayList<Topic>();
	private List<Topic> locations = new ArrayList<Topic>();
	private List<Topic> plotPoints = new ArrayList<Topic>();
	
	public MainManager(){
		loadTopics();
	}
	
	private void loadTopics(){
		List<Topic> topics = dao.getTopics();
		for (Topic topic : topics){
			if (topic.getType() == TopicType.PERSON){
				people.add(topic);
			} else if (topic.getType() == TopicType.LOCATION){
				locations.add(topic);
			} else if (topic.getType() == TopicType.PLOT_POINT){
				plotPoints.add(topic);
			}
		}
		
	}
	
	public void runLoadTest(){
		LOG.info("Running load test");
		List<Topic> topics = dao.getTopics();
		LOG.info(String.format("grabbed %d topics", topics.size()));
		dao.save(topics);
		
	}

	public List<Topic> getPeopleTopics() {
		return new ArrayList<Topic>(people);
	}
	
	public List<Topic> getLocationTopics() {
		return new ArrayList<Topic>(locations);
	}
	
	public List<Topic> getPlotPointTopics() {
		return new ArrayList<Topic>(plotPoints);
	}
}
