import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Vector;

public class Baseline {
	
	public static void main(String[] args) {
		// When changing the threshold, make sure to change the files in resources as well!!
		int threshold = 5;
		generateBaselineFeatureFile("features\\not_lemmatized_ngrams_threshold_"+threshold+"\\train_ngramsOnly.txt", Dataset.getTrainTweets());
		generateBaselineFeatureFile("features\\not_lemmatized_ngrams_threshold_"+threshold+"\\dev_ngramsOnly.txt", Dataset.getDevTweets());
		generateBaselineFeatureFile("features\\not_lemmatized_ngrams_threshold_"+threshold+"\\test_ngramsOnly.txt", Dataset.getTestTweets());
	}

	public static void generateBaselineFeatureFile(String outputFilename, LinkedHashMap<String, Integer> data) {
		try {
			Vector<Integer> labels = new Vector<Integer>();
			int nbOfTweets = data.size();
			
			for (String tweet : data.keySet()) {
				labels.add(data.get(tweet));
			}
		
			Vector<Vector<Integer>> features = new Vector<Vector<Integer>>();
			for(String tweet : data.keySet()){
				Vector<Integer> feature = Baseline.outputNgramFeatures(tweet);
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

	public static Vector<Integer> outputNgramFeatures(String tweet) {
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
		
		List<String> fourgrams = NGramExtractor.ngrams(4, tweet);
		for (String ngram : Baseline.getFourgrams()) {
			if (fourgrams.contains(ngram)) {
				features.add(1);
			}
			else {
				features.add(0);
			}
		}
		return features;
	}
	
	public static Vector<Integer> outputCharNgramFeatures(String tweet) {
		Vector<Integer> features = new Vector<Integer>();
		
		List<String> trigrams = NGramExtractor.charNgrams(3, tweet);
		for (String ngram : Baseline.getCharTrigrams()) {
			if (trigrams.contains(ngram)) {
				features.add(1);
			}
			else {
				features.add(0);
			}
		}
		
		List<String> fourgrams = NGramExtractor.charNgrams(4, tweet);
		for (String ngram : Baseline.getCharFourgrams()) {
			if (fourgrams.contains(ngram)) {
				features.add(1);
			}
			else {
				features.add(0);
			}
		}
		
		List<String> fivegrams = NGramExtractor.charNgrams(5, tweet);
		for (String ngram : Baseline.getCharFivegrams()) {
			if (fivegrams.contains(ngram)) {
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
    public static HashSet<String> getFourgrams() {
    	HashSet<String> four = new HashSet<String>();
    	try {
	    	BufferedReader br = new BufferedReader(new FileReader("resources\\fourgrams_not_lemmatized.txt"));
			String line;
			while ((line = br.readLine()) != null && line.length()!=0) {
				four.add(line);
			}
			br.close();
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
    	return four;
    }
    public static HashSet<String> getCharTrigrams() {
    	HashSet<String> trigrams = new HashSet<String>();
    	try {
	    	BufferedReader br = new BufferedReader(new FileReader("resources\\char_trigrams_not_lemmatized.txt"));
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
    public static HashSet<String> getCharFourgrams() {
    	HashSet<String> fourgrams = new HashSet<String>();
    	try {
	    	BufferedReader br = new BufferedReader(new FileReader("resources\\char_fourgrams_not_lemmatized.txt"));
			String line;
			while ((line = br.readLine()) != null && line.length()!=0) {
				fourgrams.add(line);
			}
			br.close();
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
    	return fourgrams;
    }
    public static HashSet<String> getCharFivegrams() {
    	HashSet<String> fivegrams = new HashSet<String>();
    	try {
	    	BufferedReader br = new BufferedReader(new FileReader("resources\\char_fivegrams_not_lemmatized.txt"));
			String line;
			while ((line = br.readLine()) != null && line.length()!=0) {
				fivegrams.add(line);
			}
			br.close();
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
    	return fivegrams;
    }
}
