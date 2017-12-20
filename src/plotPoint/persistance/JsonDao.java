package plotPoint.persistance;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

import plotPoint.model.Topic;

public class JsonDao implements DAO {
	private static final String HARD_CODED_TEST = "B:\\Documents\\'s Wordpad\\A Writings\\Plot Point\\test story";
	private static final String FILE_NAME = "data.json";
	private String saveFolder = HARD_CODED_TEST;
	private Gson gson = new Gson();

	public void save(List<Topic> topics) {
		String filePath = saveFolder + "\\" + FILE_NAME;
		String json = gson.toJson(topics);
		try {
			FileWriter writer = new FileWriter(filePath);
			writer.write(json);
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public List<Topic> getTopics() {
		String filePath = saveFolder + "\\" + FILE_NAME;

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		List<Topic> topics = Arrays.asList(gson.fromJson(br, Topic[].class));

		return topics;
	}

}
