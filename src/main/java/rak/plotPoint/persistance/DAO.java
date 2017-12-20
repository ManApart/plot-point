package rak.plotPoint.persistance;

import java.util.List;

import rak.plotPoint.model.Topic;

public interface DAO {
	void save(List<Topic> topics);
	List<Topic> getTopics();
}
