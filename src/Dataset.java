import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;

public class Dataset {

	public static LinkedHashMap<String, Integer> getTrainTweets()
	{
		LinkedHashMap<String, Integer> dataset = new LinkedHashMap<String, Integer>();

		try {
			String line = "";
			BufferedReader br = new BufferedReader(new FileReader("Resources\\train tweets.txt"));
			BufferedReader labelsReader = new BufferedReader(new FileReader("Resources\\train tweet labels.txt"));
	
			while ((line = br.readLine()) != null && line.length()!=0) {
				Integer score = Integer.decode(labelsReader.readLine());
				dataset.put(line, score);
			}
			br.close();
			labelsReader.close();
		}
		catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return dataset;
	}
	
	public static LinkedHashMap<String, Integer> getDevTweets()
	{
		LinkedHashMap<String, Integer> dataset = new LinkedHashMap<String, Integer>();
		try {
			String line = "";
			BufferedReader br = new BufferedReader(new FileReader("Resources\\dev tweets.txt"));
			BufferedReader labelsReader = new BufferedReader(new FileReader("Resources\\dev tweet labels.txt"));
	
			while ((line = br.readLine()) != null && line.length()!=0) {
				Integer score = Integer.decode(labelsReader.readLine());
				dataset.put(line, score);
			}
			br.close();
			labelsReader.close();
		}
		catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return dataset;
	}
	
	public static LinkedHashMap<String, Integer> getTestTweets()
	{
		LinkedHashMap<String, Integer> dataset = new LinkedHashMap<String, Integer>();
		try {
			String line = "";
			BufferedReader br = new BufferedReader(new FileReader("Resources\\test tweets.txt"));
			BufferedReader labelsReader = new BufferedReader(new FileReader("Resources\\test tweet labels.txt"));
	
			while ((line = br.readLine()) != null && line.length()!=0) {
				Integer score = Integer.decode(labelsReader.readLine());
				dataset.put(line, score);
			}
			br.close();
			labelsReader.close();
		}
		catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return dataset;
	}
	
	

}
	
//	public static Vector<Vector<String>> getTrainDataset()
//	{
//		Vector<Vector<String>> dataset = new Vector<Vector<String>>();
//		try {
//			String [] names = {"negative", "neutral", "positive"};
//			for (int label=0; label<3; ++label) {
//				String filename = "Resources\\"+ names[label] +" tweets.txt";
//				String line = "";
//				BufferedReader br = new BufferedReader(new FileReader(filename));
//	
//				Vector<String> tweets = new Vector<String>();
//				
//				while ((line = br.readLine()) != null && line.length()!=0) {
//					tweets.addElement(line);
//				}
//				dataset.addElement(tweets);
//				br.close();
//			}
//			return dataset;
//		}
//		catch (IOException e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//	public static HashMap<String, Object> getTestDataset()
//	{
//		try {
//			String filename = "Resources\\ASTD Tweets Testing.txt";
//			String line = "";
//			BufferedReader br = new BufferedReader(new FileReader(filename));
//
//			Vector<String> tweets = new Vector<String>();
//			Vector<Integer> labels = new Vector<Integer>();
//			
//			while ((line = br.readLine()) != null && line.length()!=0) {
//				if(line.contains("NEG")){
//					line = line.replace("NEG", "");
//					labels.addElement(0);
//				}
//				else if(line.contains("POS")){
//					line = line.replace("POS", "");
//					labels.addElement(2);
//				}
//				else {
//					line = line.replace("OBJ", "");
//					line = line.replace("NEUTRAL", "");
//					labels.addElement(1);
//				}
//				tweets.addElement(line);
//			}
//			br.close();
//			HashMap<String, Object> out = new HashMap<String, Object>();
//			out.put("tweets",tweets);
//			out.put("labels",labels);
//			return out;
//		}
//		catch (IOException e) {
//			e.printStackTrace();
//			return null;
//		}
//	}

