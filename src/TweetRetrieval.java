import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;

public class TweetRetrieval {
	public static void main(String[] args) {
		// NOTE: When changing the treshold, make sure to change files in resources as well!!!
//		LinkedHashMap<String, Integer> tweets = Dataset.getTrainTweets();
//		int threshold = 5;
//		boolean ngramOnly = false;
//		FeatureExtractor.generateFeatureFile("features\\not_lemmatized_ngrams_threshold_"+threshold+"\\train.txt", tweets, ngramOnly);
//		tweets = Dataset.getDevTweets();
//		FeatureExtractor.generateFeatureFile("features\\not_lemmatized_ngrams_threshold_"+threshold+"\\dev.txt", tweets, ngramOnly);
//		tweets = Dataset.getTestTweets();
//		FeatureExtractor.generateFeatureFile("features\\not_lemmatized_ngrams_threshold_"+threshold+"\\test.txt", tweets, ngramOnly);
		
		String country = "UAE";
		String folder = "dataset tweets\\"+country+"\\";
		LinkedHashMap<String, Integer> tweets = getTweetsFromFile(folder+country+".txt");
		FeatureExtractor.generateFeatureFile(folder+country+"_features.txt", tweets, false);
	}
	
	public static LinkedHashMap<String, Integer> getTweetsFromFile(String filename) {
		LinkedHashMap<String, Integer> tweets = new LinkedHashMap<String, Integer>();
		try {
			String line = "";
			BufferedReader br = new BufferedReader(new FileReader(filename));
	
			while ((line = br.readLine()) != null && line.length()!=0) {
				tweets.put(line, 0);
			}
			br.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return tweets;
	}
}

//	@SuppressWarnings("unchecked")
//	public static void main(String[] args) {
//		TwitterManager twitterManager = new TwitterManager();  
//		try {
//			twitterManager.performQuery("");
//			HashMap<String, Object> data = Dataset.getTestDataset();
//			Vector<Vector<String>> data = Dataset.getTrainDataset();
//			for (int label=0; label<3; ++label) {
//				Vector<String> tweets = (Vector<String>) data.get(label);
//	
//				Vector<HashMap<String, Object>> preprocessed = new Vector<HashMap<String, Object>>();
//				
//				for (String tweet : tweets)
//				{
//					preprocessed.add(Preprocessor.preprocess(tweet));
//				}
//				
//				String input = "in.xml";
//				String output = "out.xml";
//				
//				XMLParser.inputXML(preprocessed,input);
//				Madamira.lemmatize(input, output);
//				HashMap<String, Object> outputXML = XMLParser.outputXML(output, preprocessed);
//				
//				Vector<Vector<String>> lemmatizedTweets = (Vector<Vector<String>>)(outputXML.get("lemmas"));
//				Vector<HashMap<String, Integer>> posTags = (Vector<HashMap<String, Integer>>)(outputXML.get("pos"));
//				Vector<Vector<Vector<String>>> hashtags = ((Vector<Vector<Vector<String>>>)(outputXML.get("hashtags")));
//				
//				Vector<Vector<Integer>> features = new Vector<Vector<Integer>>();
//				for(int i = 0; i < preprocessed.size(); i++){
//					Vector<Integer> feature = FeatureExtractor.outputFeatures(preprocessed.get(i), 
//							posTags.get(i), lemmatizedTweets.get(i), hashtags.get(i));
//					features.addElement(feature);
//				}
//				
//				PrintWriter writer = new PrintWriter("features"+label+"_libsvm.csv", "UTF-8");
//				
//				for(Vector<Integer> tweet : features){
//					writer.print(label + " ");
//					int size = tweet.size();
//					for(int i=0; i<size; ++i){
//						Integer f = tweet.elementAt(i);
//						writer.print((i+1)+":"+f+" ");
//					}
//					writer.println();
//				}
//				writer.close();
//			}
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//}
