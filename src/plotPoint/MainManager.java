package plotPoint;

import java.util.List;
import java.util.logging.Logger;

import plotPoint.model.Topic;
import plotPoint.persistance.DAO;
import plotPoint.persistance.JsonDao;

public class MainManager {
	private static final Logger LOG = Logger.getLogger(MainManager.class.getName()); 
	private DAO dao = new JsonDao();
	private List<Topic> topics;
	
	public void runLoadTest(){
		LOG.info("Running load test");
		topics = dao.getTopics();
		LOG.info(String.format("grabbed %d topics", topics.size()));
		dao.save(topics);
		
	}
}
