package rak.plotPoint.model;

import lombok.Getter;
import rak.plotPoint.utility.gridHelper.RowParameter;

public class Reference implements RowParameter{
	@Getter
	private Topic topic;
	
	@Getter
	private String excerpt;
	
	public Reference(Topic topic, String excerpt) {
		this.topic = topic;
		this.excerpt = excerpt;
	}

}
