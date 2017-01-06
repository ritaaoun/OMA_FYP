import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import twitter4j.GeoLocation;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterManager {
	public static void main(String[] args) {
		TwitterManager twitterManager = new TwitterManager();  
		try {
			twitterManager.performQuery("");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
    int LIMIT= 1000; //the number of retrieved tweets  
    ConfigurationBuilder cb;  
    Twitter twitter;  
    public TwitterManager() {  
       cb = new ConfigurationBuilder();  
       cb.setOAuthConsumerKey("mAYWadGPx7fpeWkPFXIkVbimI");  
       cb.setOAuthConsumerSecret("V9jwOkmEwNsaMOzwvxeXBJAndvyq5qwupQbVSf8XIUTI3dth90");  
       cb.setOAuthAccessToken("92957562-VSKY6SOwxxVGj0Dttc2aIIUeIuSzYvf4WsGEWPmRk");  
       cb.setOAuthAccessTokenSecret("RMhC9Xg6GhXKAufEWbxVFKLYxnW3cFP4tKNwGeOlgx2ts");  
       twitter = new TwitterFactory(cb.build()).getInstance();
    }  
    public void performQuery(String inQuery) throws InterruptedException, IOException {  
       Query query = new Query(inQuery);  
       query.setCount(100);
       // Lebanon
       //query.setGeoCode(new GeoLocation(33.8547,35.8623), 450, Query.KILOMETERS);
       // Egypt
       query.setGeoCode(new GeoLocation(27,30),750, Query.KILOMETERS);
       try {  
    	   int count=0;
    	   QueryResult r;  
    	   // create new file
    	   File file = new File("tweets_egyptian.txt");

    	   // if file doesn't exists, then create it
    	   if (!file.exists()) {
    		   file.createNewFile();
    	   }

    	   FileWriter fw = new FileWriter(file.getAbsoluteFile());
    	   BufferedWriter bw = new BufferedWriter(fw);
    	   ArrayList<Status> tweets = new ArrayList<Status>();
    	   long lastID = Long.MAX_VALUE;
    	   do {
    		   if (LIMIT - tweets.size() > 100) {
    			   query.setCount(100);
    		   }
    		   else { 
    			   query.setCount(LIMIT - tweets.size());
    		   }
           
               r = twitter.search(query);
               ArrayList<Status> ts = new ArrayList<Status>(r.getTweets());
               tweets.addAll(ts);
               
               for (Status t: ts) {
            	   if(t.getId() < lastID) lastID = t.getId();
               }
               query.setMaxId(lastID-1);
               count = tweets.size();
               //ArabicStemmer Stemmer = new ArabicStemmer();
    	   } while (count < LIMIT);  
    	   for (int i = 0; i < tweets.size() && i < LIMIT; i++) {  
        	   count++;  
        	   Status t = (Status) tweets.get(i);
        	   if (!t.isRetweet()) {
        			String text = t.getText(); 
        			System.out.println("Text: " + text);  
                
	                String name = t.getUser().getScreenName();  
	                System.out.println("User: " + name);
	                
	                int favorites = t.getFavoriteCount();
	                System.out.println("Favorites: " + favorites);
	                
	                int retweets = t.getRetweetCount();
	                System.out.println("Retweets: " + retweets + '\n');
	               
	                String location = t.getUser().getLocation();
	                System.out.println("Location: " + location + '\n');
	                bw.write("Username: " + name + '\n' + "Tweet: " + text + '\n' + "Likes/Favorites: " 
	                		+ Integer.toString(favorites) + '\n' + "Retweets: " + Integer.toString(retweets)
	                		+ "\nLocation: " + location + "\n\n");
	               // AnalysedTweet analysedTweet = new AnalysedTweet(t.getText(), t.getUser().getScreenName(), Stemmer/*, allWords*/);
	                //System.out.println("Stemmed: " + analysedTweet.getStemmedTweet());
	               // bw.write(""Stemmed: " + analysedTweet.getStemmedTweet()");
        	   }
           }
          bw.close();
       }  
       catch (TwitterException te) {  
          System.out.println("Couldn't connect: " + te);  
       }  
    }  
 }  