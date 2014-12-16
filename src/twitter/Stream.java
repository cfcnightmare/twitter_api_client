package twitter;

import java.sql.*;

import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.AsyncTwitter;
    public class Stream extends TwitterIO  {
    	
    	
    	//����,�������
        static int stop = 0;
        final static int connect = 0;
        final String JDBC_DRIVER = "org.postgresql.jdbc.Driver";
        final static String DB_URL = "jdbc:postgresql://localhost:5433/postgres";

        // �����,������
        final static String USER = "postgres";
        final static String PASS = "12345";
        
        
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
                                
                               for (int i =0; i<1000;i++) {
                                String sql = "insert into twitter (userid, username, postid,post) values (?, ?, ?,?)" ;	
                                 PreparedStatement ps = conn.prepareStatement(sql);
                                 
								ps.setLong(1, status.getUser().getId());
                                 ps.setString(2, status.getUser().getName());
                                 ps.setLong(3, status.getId());
                                 ps.setString(4, status.getText());
                                 ps.executeUpdate();
                                 ps.executeQuery();
                                 
                                 System.out
                                 .println("Inserted records into the table...");
                         stop = 1;
                         System.exit(0);
                         ps.close();
                         conn.close();
                         }
                                 
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

            String keywords[] = { "Putin" };

            fq.track(keywords);

            ts.addListener(listener);
            ts.filter(fq);
            
        }
    }