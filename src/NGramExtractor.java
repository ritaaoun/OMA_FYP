import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class NGramExtractor {
	public static final HashSet<String> unigrams = getUnigrams();
	public static final HashSet<String> bigrams = getBigrams();
	public static final HashSet<String> trigrams = getTrigrams();
	public static final HashSet<String> fourgrams = getFourgrams();
	public static final HashSet<String> charTrigrams = getCharTrigrams();
	public static final HashSet<String> charFourgrams = getCharFourgrams();
	public static final HashSet<String> charFivegrams = getCharFivegrams();
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
	public static List<String> charNgrams(int n, String str) {
        List<String> charNgrams = new ArrayList<String>();
        for (int i = 0; i <= str.length() - n; ++i) {
            charNgrams.add(str.substring(i, i+n));
        }
        return charNgrams;
    }
    public static HashSet<String> getUnigrams() {
    	HashSet<String> unigrams = new HashSet<String>();
    	try {
	    	BufferedReader br = new BufferedReader(new FileReader("Resources\\unigrams.txt"));
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
	    	BufferedReader br = new BufferedReader(new FileReader("Resources\\bigrams.txt"));
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
    public static HashSet<String> getFourgrams() {
    	HashSet<String> fourgrams = new HashSet<String>();
    	try {
	    	BufferedReader br = new BufferedReader(new FileReader("Resources\\fourgrams.txt"));
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
    public static HashSet<String> getCharTrigrams() {
    	HashSet<String> charTrigrams = new HashSet<String>();
    	try {
	    	BufferedReader br = new BufferedReader(new FileReader("Resources\\char_trigrams.txt"));
			String line;
			while ((line = br.readLine()) != null && line.length()!=0) {
				charTrigrams.add(line);
			}
			br.close();
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
    	return charTrigrams;
    }
    public static HashSet<String> getCharFourgrams() {
    	HashSet<String> charFourgrams = new HashSet<String>();
    	try {
	    	BufferedReader br = new BufferedReader(new FileReader("Resources\\char_fourgrams.txt"));
			String line;
			while ((line = br.readLine()) != null && line.length()!=0) {
				charFourgrams.add(line);
			}
			br.close();
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
    	return charFourgrams;
    }
    public static HashSet<String> getCharFivegrams() {
    	HashSet<String> getCharFivegrams = new HashSet<String>();
    	try {
	    	BufferedReader br = new BufferedReader(new FileReader("Resources\\char_fivegrams.txt"));
			String line;
			while ((line = br.readLine()) != null && line.length()!=0) {
				getCharFivegrams.add(line);
			}
			br.close();
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
    	return getCharFivegrams;
    }
}
