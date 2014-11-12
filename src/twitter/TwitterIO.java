package twitter;

import java.util.*;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterIO {
	

	public static void main(String[] args) throws TwitterException {

		ConfigurationBuilder cb = new ConfigurationBuilder();

		cb.setDebugEnabled(true)
				.setOAuthConsumerKey("lUovo3BZqnUZtHZ93bMswt3Eo")
				.setOAuthConsumerSecret("fjOLhctYpofr67r3leuRYc80fs7wv0HWbGVaPPgXDMpoaQ18bq")
				.setOAuthAccessToken("2859592708-wYYGmguzOaImRPjxirc1TZj3EkNdWWHOJDCNskq")
				.setOAuthAccessTokenSecret("0r61bdwmrYEXlYheU2lNEGyvEMhJQTd7deAFCzYcuRaNk");

		TwitterFactory tf = new TwitterFactory(cb.build());
		twitter4j.Twitter tw = tf.getInstance();

		// POSTING MESSAGE************

		//Status stat = tw.updateStatus(""); // “екст поста
		//System.out.println("Posted!!!"); // подтверждение о проведении операции

		// Reading****(USERNAME,POST TEXT,STAT ID,USER ID************

		List<Status> statuses = tw.getHomeTimeline();

		for (Status status : statuses) {
			long userId = status.getUser().getId();
			long postId = status.getId();
			String post = status.getText();
			String user = status.getUser().getName();
			System.out.println(user + ":"+ post);
			System.out.println("STATUS ID:" + "  " + postId);
			System.out.println("USER ID:" + userId);
		}

	}
}
