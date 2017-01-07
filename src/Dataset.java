import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Vector;

public class Dataset {
	public static void main(String[] args) {
		try {
			String filename;
			filename = "Resources\\ASTD Tweets.txt";
			
			String line = "";
			BufferedReader br = new BufferedReader(new FileReader(filename));
			PrintWriter writerPos = new PrintWriter("Resources\\positive tweets.txt", "UTF-8");
			PrintWriter writerNeg = new PrintWriter("Resources\\negative tweets.txt", "UTF-8");
			PrintWriter writerNeu = new PrintWriter("Resources\\neutral tweets.txt", "UTF-8");
			PrintWriter writerAll = new PrintWriter("Resources\\all tweets.txt", "UTF-8");
			
			while ((line = br.readLine()) != null && line.length()!=0) {
				String originalLine = line;
				if(line.contains("NEG")){
					line = line.replace("NEG", "");
					writerNeg.println(line);
				}
				else if(line.contains("POS")){
					line = line.replace("POS", "");
					writerPos.println(line);
				}
				else if(line.contains("NEUTRAL")){
					line = line.replace("NEUTRAL", "");
					writerNeu.println(line);
				}
				else {
					continue;
				}
				writerAll.println(originalLine);
			}
			br.close();
			writerPos.close();
			writerNeg.close();
			writerNeu.close();
			writerAll.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static Vector<Vector<String>> getTrainDataset()
	{
		Vector<Vector<String>> dataset = new Vector<Vector<String>>();
		try {
			String [] names = {"negative", "neutral", "positive"};
			for (int label=0; label<3; ++label) {
				String filename = "Resources\\"+ names[label] +" tweets.txt";
				String line = "";
				BufferedReader br = new BufferedReader(new FileReader(filename));
	
				Vector<String> tweets = new Vector<String>();
				
				while ((line = br.readLine()) != null && line.length()!=0) {
					tweets.addElement(line);
				}
				dataset.addElement(tweets);
				br.close();
			}
			return dataset;
		}
		catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static HashMap<String, Object> getTestDataset()
	{
		try {
			String filename = "Resources\\ASTD Tweets Testing.txt";
			String line = "";
			BufferedReader br = new BufferedReader(new FileReader(filename));

			Vector<String> tweets = new Vector<String>();
			Vector<Integer> labels = new Vector<Integer>();
			
			while ((line = br.readLine()) != null && line.length()!=0) {
				if(line.contains("NEG")){
					line = line.replace("NEG", "");
					labels.addElement(0);
				}
				else if(line.contains("POS")){
					line = line.replace("POS", "");
					labels.addElement(2);
				}
				else {
					line = line.replace("OBJ", "");
					line = line.replace("NEUTRAL", "");
					labels.addElement(1);
				}
				tweets.addElement(line);
			}
			br.close();
			HashMap<String, Object> out = new HashMap<String, Object>();
			out.put("tweets",tweets);
			out.put("labels",labels);
			return out;
		}
		catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
