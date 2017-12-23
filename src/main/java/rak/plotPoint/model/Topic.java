package rak.plotPoint.model;

import org.apache.commons.lang3.StringUtils;

import lombok.Data;

@Data
public class Topic {
	private String name;
	private TopicType type;
	private String text;
	
	public Reference getReference(String topicName){
		int index = StringUtils.indexOfIgnoreCase(text, topicName);
		if (index != -1){
			String sentence = getSentenceSurrounding(index);
			return new Reference(this, sentence);
		}
		return null;
	}

	private String getSentenceSurrounding(int index) {
		int start = Math.max(0, StringUtils.lastIndexOf(text.substring(0, index),"."));
		int end = Math.min(text.length(), StringUtils.indexOf(text, ".", index));
		
		return text.substring(start, end);
	}

}
