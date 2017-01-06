import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class NGramExtractor {
	public static final HashSet<String> trigrams = getTrigrams();
	public static List<String> ngrams(int n, String str) {
        List<String> ngrams = new ArrayList<String>();
        String[] words = str.split(" ");
        for (int i = 0; i < words.length - n + 1; i++)
            ngrams.add(concat(words, i, i+n));
        return ngrams;
    }

    public static String concat(String[] words, int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < end; i++)
            sb.append((i > start ? " " : "") + words[i]);
        return sb.toString();
    }
    public static HashSet<String> getTrigrams() {
    	HashSet<String> trigrams = new HashSet<String>();
    	try {
	    	BufferedReader br = new BufferedReader(new FileReader("Resources\\trigrams.txt"));
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
