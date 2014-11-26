
package twitter;
import java.util.*;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class TwitterIO {
	
	 
    static int stop = 0;
    static int connect = 0;
    static final String JDBC_DRIVER = "org.postgresql.jdbc.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost:5433/postgres";

    // Database credentials
    static final String USER = "postgres";
    static final String PASS = "12345";
    
//Types
   
   
   java.sql.PreparedStatement st;
   
   
    public static void main(String[] args)  throws Exception{
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true);
        cb.setOAuthConsumerKey("GrO1fdysw80wN0t7AjsP4yMtg");
        cb.setOAuthConsumerSecret("vkXFw1iwKmR7QkErBHielcvno9uwy82nS20G2EZP3iL6f3ybAR");
        cb.setOAuthAccessToken("2859592708-wYYGmguzOaImRPjxirc1TZj3EkNdWWHOJDCNskq");
        cb.setOAuthAccessTokenSecret("0r61bdwmrYEXlYheU2lNEGyvEMhJQTd7deAFCzYcuRaNk");

        TwitterStream ts = new TwitterStreamFactory(cb.build()).getInstance();
                
        
        StatusListener listener = new StatusListener() {

            @Override
            public void onException(Exception arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScrubGeo(long arg0, long arg1) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onStatus(Status status) {
                
                Connection conn = null;
                Statement stmt = null;
                
                
                
                try {
                    
                        if (connect != 1) {
                        	

                            // STEP 3: Open a connection
                            System.out.println("Connecting to    database...");
                            conn = DriverManager.getConnection(DB_URL, USER,
                                    PASS);
                            // no need to display the fields... directly insert
                            // them and work on it
                            
                            
                            
                            long userId=status.getUser().getId();
                		    long postId= status.getId();
                			String post = status.getText();
                			String userName = status.getUser().getName();
                			
                			
                			
                			java.sql.PreparedStatement st;
                			stmt = conn.createStatement();
                     	    ResultSet res1= stmt.executeQuery("SELECT * from twitter where userid = " + userId + "");
                             
                              if(!res1.next())
                              {
                     	      st =  conn.prepareStatement("INSERT INTO twitter (userid,username, postid,post) VALUES (?,?,?,?)");
                     	     st.setLong(1, userId);
                     	    st.setString(2, userName);
                     	   st.setLong(3, postId);
                     	    st.setString(4,post);
                     	   st.executeUpdate(); 
                     	  System.out.println(userId + userName + postId + post);
                          System.out.println("Inserting records into the table...");
                              } 
                     	     
                     	     
                     	      
                     	      
                     	     
                              
                     	   
                			
                			/*try {
                		        userId = Long.valueOf(status.getUser().getId());
                		        postId = Long.valueOf(status.getId());
                		        
                		        //do more validations here
                		    } catch (NumberFormatException e) {
                		        throw new RuntimeException("Not a valid input.");
                		    }*/
                            
                			
                			//System.out.println(userId + userName + postId + post);
                            /*System.out.println("Inserting records into the table...");
                                    
                            stmt = conn.createStatement();
                        
                            connect = 1;
                           
                          while(conn!=null){
                        	   PreparedStatement ps = conn.prepareStatement
                                    
                                    
                     			     ("insert into  twitter values(?,?,?,?)");
                        	  ps.setString(1,post);
      						  ps.setLong(2,userId);
      						  ps.setString(3,userName);
      						  ps.setLong(4,postId);
      						  
      						  ps.executeUpdate();
             
                           	    	
             						  
             						  
             			   System.out.println(ps);
                          }
                              	    
                					
                              	      
                              	      String query = "select * from twitter  ";
                              	        ResultSet rs =  stmt.executeQuery(query);
                              	      System.out.println("TweetID tweetmsg userid username");
                             while (rs.next()) {
                              	         long TweetID = rs.getLong("TwitterID");
                              	         String tweetmsg = rs.getString("tweetmsg");
                              	         long userid = rs.getLong("userid");
                              	         String username = rs.getString("username");
                              	         
                              	       
                              	   
                              	        System.out.println(TweetID + "  " + tweetmsg+"   "+userid+"   "+username);
                            while(conn!=null){
                            	
                           	 
                                String sql1 =
                                "INSERT INTO twitter(tweetmsg,userid,username,twitterid)" + " VALUES ( " + post + "   " + userId + "   " + userName + "   " + postId +")";
                                System.out.println(sql1);
	                            stmt.executeUpdate(sql1);
	                           System.exit(0);
                            }
                             }*/

                        
                        
                			
                     	      
                   
                    
                    	
                   
                                      
                              
                                      
                              
                              
                              
                               
                              
                        

                            
                            

                    
                        System.out
                                .println("Inserted records into the table...");
                        stop = 1;
                        System.exit(0);
                        stmt.close();
                        conn.close();
                        }
                } catch (SQLException se) {
                    // Handle errors for JDBC
                    se.printStackTrace();
                } catch (Exception e) {
                    // Handle errors for Class.forName
                    e.printStackTrace();
                } finally {
                    // finally block used to close resources
                    try {
                        if (stmt != null)
                            stmt.close();
                    } catch (SQLException se2) {
                    }// nothing we can do
                    try {
                        if (conn != null)
                            conn.close();
                    } catch (SQLException se) {
                        se.printStackTrace();
                    }// end finally try
                }// end try

            }

            @Override
            public void onTrackLimitationNotice(int arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onStallWarning(StallWarning arg0) {
                // TODO Auto-generated method stub
            
            }
            
        };

        // More robust filtering like by location/time zone/etc here itself?
        FilterQuery fq = new FilterQuery();

        String keywords[] = { "Iphone6" };

        fq.track(keywords);

        ts.addListener(listener);
        ts.filter(fq);
        
    }
}