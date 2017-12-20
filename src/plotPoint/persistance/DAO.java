package plotPoint.persistance;

import java.util.List;

import plotPoint.model.Topic;

public interface DAO {
	void save(List<Topic> topics);
	List<Topic> getTopics();
}
