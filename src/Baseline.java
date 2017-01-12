import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Vector;

public class Baseline {
	
	public static void main(String[] args) {
		int threshold = 4;
		generateBaselineFeatureFile("features\\not_lemmatized_ngrams_threshold_"+threshold+"\\train_ngramsOnly.txt", Dataset.getTrainTweets(), threshold);
		generateBaselineFeatureFile("features\\not_lemmatized_ngrams_threshold_"+threshold+"\\dev_ngramsOnly.txt", Dataset.getDevTweets(), threshold);
		generateBaselineFeatureFile("features\\not_lemmatized_ngrams_threshold_"+threshold+"\\test_ngramsOnly.txt", Dataset.getTestTweets(), threshold);
	}

	public static void generateBaselineFeatureFile(String outputFilename, LinkedHashMap<String, Integer> data, int threshold) {
		try {
			Vector<Integer> labels = new Vector<Integer>();
			int nbOfTweets = data.size();
			
			for (String tweet : data.keySet()) {
				labels.add(data.get(tweet));
			}
		
			Vector<Vector<Integer>> features = new Vector<Vector<Integer>>();
			for(String tweet : data.keySet()){
				Vector<Integer> feature = Baseline.outputNgramFeatures(tweet,threshold);
				features.addElement(feature);
			}
				
			PrintWriter writer = new PrintWriter(outputFilename, "UTF-8");
			
			int nbOfFeatures = features.elementAt(0).size();

			for(int i=0; i<nbOfTweets; ++i){
				Vector<Integer> tweetFeatures = features.elementAt(i);
				int label = labels.elementAt(i);
				writer.print(label + " ");
				
				for(int j=0; j<nbOfFeatures; ++j){
					Integer f = tweetFeatures.elementAt(j);
					if (f>0) {
						writer.print((j+1)+":"+f+" ");
					}
				}
				writer.println();
			}
			writer.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Vector<Integer> outputNgramFeatures(String tweet, int threshold) {
		Vector<Integer> features = new Vector<Integer>();
		
		List<String> unigrams = NGramExtractor.ngrams(1, tweet);
		for (String ngram : Baseline.getUnigrams()) {
			if (unigrams.contains(ngram)) {
				features.add(1);
			}
			else {
				features.add(0);
			}
		}
		
		List<String> bigrams = NGramExtractor.ngrams(2, tweet);
		for (String ngram : Baseline.getBigrams()) {
			if (bigrams.contains(ngram)) {
				features.add(1);
			}
			else {
				features.add(0);
			}
		}
		
		List<String> trigrams = NGramExtractor.ngrams(3, tweet);
		for (String ngram : Baseline.getTrigrams()) {
			if (trigrams.contains(ngram)) {
				features.add(1);
			}
			else {
				features.add(0);
			}
		}
		return features;
	}
	
    public static HashSet<String> getUnigrams() {
    	HashSet<String> unigrams = new HashSet<String>();
    	try {
	    	BufferedReader br = new BufferedReader(new FileReader("resources\\unigrams_not_lemmatized.txt"));
			String line;
			while ((line = br.readLine()) != null && line.length()!=0) {
				unigrams.add(line);
			}
			br.close();
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
    	return unigrams;
    }
    public static HashSet<String> getBigrams() {
    	HashSet<String> bigrams = new HashSet<String>();
    	try {
	    	BufferedReader br = new BufferedReader(new FileReader("resources\\bigrams_not_lemmatized.txt"));
			String line;
			while ((line = br.readLine()) != null && line.length()!=0) {
				bigrams.add(line);
			}
			br.close();
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
    	return bigrams;
    }
    public static HashSet<String> getTrigrams() {
    	HashSet<String> trigrams = new HashSet<String>();
    	try {
	    	BufferedReader br = new BufferedReader(new FileReader("resources\\trigrams_not_lemmatized.txt"));
			String line;
			while ((line = br.readLine()) != null && line.length()!=0) {
				trigrams.add(line);
			}
			br.close();
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
    	return trigrams;
    }
}
