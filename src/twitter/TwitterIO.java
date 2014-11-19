package twitter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;



public class TwitterIO {
	
	 static  long userId;
	static long postId;
	static String userName;
	static String post;

	public static void main(String[] args) throws Exception {
		

		ConfigurationBuilder cb = new ConfigurationBuilder();

		cb.setDebugEnabled(true)
				.setOAuthConsumerKey("lUovo3BZqnUZtHZ93bMswt3Eo")
				.setOAuthConsumerSecret("fjOLhctYpofr67r3leuRYc80fs7wv0HWbGVaPPgXDMpoaQ18bq")
				.setOAuthAccessToken("2859592708-wYYGmguzOaImRPjxirc1TZj3EkNdWWHOJDCNskq")
				.setOAuthAccessTokenSecret("0r61bdwmrYEXlYheU2lNEGyvEMhJQTd7deAFCzYcuRaNk");

		TwitterFactory tf = new TwitterFactory(cb.build());
		twitter4j.Twitter tw = tf.getInstance();
		

		// POSTING MESSAGE************

		//Status stat = tw.updateStatus(""); // Текст поста
		//System.out.println("Posted!!!"); // подтверждение о проведении операции

		// Reading****(USERNAME,POST TEXT,STAT ID,USER ID************

		List<Status> statuses = tw.getHomeTimeline();
		

		for (Status status : statuses) {
			
				 userId = status.getUser().getId();
				 postId = status.getId();
				 post = status.getText();
				 userName = status.getUser().getName();
				
				 System.out.println(userName + ":"+ post);
					System.out.println("STATUS ID:" + "  " + postId);
					System.out.println("USER ID:" + userId);
					
					/*погнали в базу*********************************************************************************************************/
					
					Connection con = null;
			        Statement st = null;

			        String url = "jdbc:postgresql://localhost:5433/postgres";
			        String user = "postgres";
			        String password = "12345";

			        try {

			          con = DriverManager.getConnection(url, user, password);

			          st = con.createStatement();
			          
			          con.setAutoCommit(false);
			          
			          st.addBatch("DROP TABLE IF EXISTS twitter");
			          st.addBatch("CREATE TABLE twitter(id serial, name text, userid numeric,post text,postid bigint)");
			          st.addBatch("INSERT INTO twitter(name) VALUES " + TwitterIO.userName);
			          st.addBatch("INSERT INTO twitter(userid) VALUES " + TwitterIO.userId);
			          st.addBatch("INSERT INTO twitter(post) VALUES " +TwitterIO.post);
			          st.addBatch("INSERT INTO twitter(postid) VALUES " + TwitterIO.postId);
			                           

			          int counts[] = st.executeBatch();

			          con.commit();

			          System.out.println("Committed " + counts.length + " updates");

			        } catch (SQLException ex) {

			            System.out.println(ex.getNextException());
			            
			            if (con != null) {
			                try {
			                    con.rollback();
			                } catch (SQLException ex1) {
			                    Logger lgr = Logger.getLogger(PostgreSample.class.getName());
			                    lgr.log(Level.WARNING, ex1.getMessage(), ex1);
			                }
			            }

			            Logger lgr = Logger.getLogger(PostgreSample.class.getName());
			            lgr.log(Level.SEVERE, ex.getMessage(), ex);

			        } finally {

			            try {
			 
			                if (st != null) {
			                    st.close();
			                }
			                if (con != null) {
			                    con.close();
			                }

			            } catch (SQLException ex) {
			                Logger lgr = Logger.getLogger(PostgreSample.class.getName());
			                lgr.log(Level.WARNING, ex.getMessage(), ex);
			            }
			        }
				
		
	}
	}}	
	

