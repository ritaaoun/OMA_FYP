import java.util.Vector;

public class PolarityInfo
{
	public static Vector<Integer> getWordsInfo(Vector<String> lemmatizedTweet, Vector<String> normalizedTweet, Vector<Boolean> isNegated)
	{
		
//		if(normalizedTweet.size()!= lemmatizedTweet.size()){
//			for(String n : lemmatizedTweet){
//				System.out.print(n+" ");
//			}
//			System.out.println();
//			for(String s : normalizedTweet){
//				System.out.print(s+" ");
//			}
//		}
		
		int numberOfWords = lemmatizedTweet.size();
		Vector<Integer> out = new Vector<Integer>();
		int positive = 0;
		int negative = 0;
		for (int i=0; i<numberOfWords; ++i) {
			Boolean negated;
			try{
			 negated = isNegated.elementAt(i);
			}
			catch(Exception e) {
				System.out.println("ISNEGATED!!");
				e.printStackTrace();
				negated=false;
				for(String ss : lemmatizedTweet){
					System.out.print(ss+" ");
				}
				System.out.println();
			}
			String word = lemmatizedTweet.elementAt(i);
			if (LexiconMapping.arsenl.containsKey(word)) {
				Polarity pol = getPolarity(LexiconMapping.arsenl.get(word));
				if (pol == Polarity.Positive) {
					if(negated == false){
						++positive;
					}else{
						++negative;
					}
				}
				else if (pol == Polarity.Negative) {
					if(negated == false){
						++negative;
					}else{
						++positive;
					}
				}
			}
			else
			{
				try{
					word = normalizedTweet.elementAt(i);
				}
				catch(Exception e) {
					System.out.println("NORMALIZEDTWEET!!");
					e.printStackTrace();
					word = "";
					for(String ss : normalizedTweet){
						System.out.print(ss+" ");
					}
					System.out.println();
				}
				if (LexiconMapping.arasenti.containsKey(word))
				{
					double pol = LexiconMapping.arasenti.get(word);
					
					if (pol > 0)
					{
						if(!negated){
							++positive;
						}else{
							++negative;
						}
					}
					else if (pol < 0)
					{
						if(!negated){
							++negative;
						}else{
							++positive;
						}
					}
				}
				else if (LexiconMapping.dahl.containsKey(word))
				{
					double pol = LexiconMapping.dahl.get(word);
					if (pol > 0)
					{
						if(!negated){
							++positive;
						}else{
							++negative;
						}
					}
					else if (pol < 0)
					{
						if(!negated){
							++negative;
						}else{
							++positive;
						}
					}
				}
			}
		}
		out.addElement(positive);
		out.addElement(negative);
		
		return out;
	}

	public static Vector<Integer> getEmoticonsInfo(Vector<String> emoticons, Vector<String> emojis) {
		Vector<Integer> out = new Vector<Integer>();
		int positives = 0;
		int negatives = 0;
		for (String e : emoticons) {
			Polarity pol = EmoticonExtractor.polarity(e);
			if (pol == Polarity.Positive) {
				++positives;
			}
			else if (pol == Polarity.Negative) {
				++negatives;
			}
		}
		
		for (String e : emojis) {
			if (LexiconMapping.emojisLexicon.containsKey(e)) {
				Polarity pol = getPolarity(LexiconMapping.emojisLexicon.get(e));
				if (pol == Polarity.Positive) {
					++positives;
				}
				else if (pol == Polarity.Negative) {
					++negatives;
				}
			}
		}
		out.addElement(positives);
		out.addElement(negatives);
		
		return out;
	}
	
	private static Polarity getPolarity(Vector<Double> scores) {
		double pos = scores.get(0);
		double neg = scores.get(1);
		if (pos > neg) { // if a word has a + score that is twice as much as its - score
			return Polarity.Positive;
		}
		else if (neg > pos) {
			return Polarity.Negative;
		}
		else {
			return null;
		}
	}
}
