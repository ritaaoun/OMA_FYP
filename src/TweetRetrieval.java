import java.util.LinkedHashMap;

public class TweetRetrieval {
	public static void main(String[] args) {
		LinkedHashMap<String, Integer> tweets = Dataset.getTrainTweets();
		FeatureExtractor.generateFeatureFile("features\\train.txt", tweets, false);
		tweets = Dataset.getDevTweets();
		FeatureExtractor.generateFeatureFile("features\\dev.txt", tweets, false);
		tweets = Dataset.getTestTweets();
		FeatureExtractor.generateFeatureFile("features\\test.txt", tweets, false);
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
