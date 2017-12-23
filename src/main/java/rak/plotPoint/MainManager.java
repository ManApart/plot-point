package rak.plotPoint;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;

import rak.plotPoint.model.Reference;
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
		load();
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
	
	private List<Topic> getAllTopics() {
		List<Topic> topics = new ArrayList<Topic>();
		topics.addAll(people);
		topics.addAll(locations);
		topics.addAll(plotPoints);
		return topics;
	}

	public void load() {
		//TODO -file chooser, load save etc
		LOG.info("load");
		people = new ArrayList<Topic>();
		locations = new ArrayList<Topic>();
		plotPoints = new ArrayList<Topic>();
		
		
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

	public void save() {
		LOG.info("save");
		dao.save(getAllTopics());
	}

	/*
	 * Given some text, read through list of topics and display the closest match
	 */
	public Topic getTopic(String text) {
		LOG.info("Manager grabbing topic for " + text);
		for (Topic otherTopic : getAllTopics()){
			if (StringUtils.containsIgnoreCase(otherTopic.getName(), text)){
				//TODO - eventually grab best match instead of first match
				return otherTopic;
			}
		}
		return null;
	}

	public List<Reference> getReferences(Topic topic) {
		LOG.info("Manager grabbing references of " + topic.getName());
		List<Reference> references = new ArrayList<Reference>();
		
		for (Topic otherTopic : getAllTopics()){
			Reference reference = otherTopic.getReference(topic.getName());
			if (reference != null){
				references.add(reference);
			}
		}
		
		return references;
	}
}
