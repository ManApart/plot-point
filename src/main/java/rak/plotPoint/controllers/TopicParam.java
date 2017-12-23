package rak.plotPoint.controllers;

import java.util.ArrayList;
import java.util.List;

import rak.plotPoint.model.Topic;
import rak.plotPoint.utility.gridHelper.RowParameter;

public class TopicParam implements RowParameter {
	private Topic topic;
	
	public TopicParam(Topic topic){
		this.topic = topic;
	}
	
	public Topic getTopic(){
		return topic;
	}
	
	public static List<TopicParam> fromTopics(List<Topic> topics){
		List<TopicParam> topicParams = new ArrayList<TopicParam>();
		for (Topic topic : topics){
			topicParams.add(new TopicParam(topic));
		}
		return topicParams;
	}

}
