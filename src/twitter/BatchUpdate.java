package twitter;
import twitter4j.*;
import twitter4j.conf.*;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
	


	public class BatchUpdate extends TwitterIO  {

	        public static void main(String[] args) {
            
	        Connection con = null;
	        Statement st = null;

	        String url = "jdbc:postgresql://localhost/twitterdb";
	        String user = "postgres";
	        String password = "12345";

	        try {

	        	con = DriverManager.getConnection(url, user, password);

	          st = con.createStatement();
	          
	          con.setAutoCommit(false);
	          
	          try {
				st.addBatch("DROP TABLE IF EXISTS Twitter");
				st.addBatch("(CREATE TABLE Twitter )");

				
				  
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}                  

	          int counts[] = st.executeBatch();

	          con.commit();

	          System.out.println("Committed " + counts.length + " updates");

	        } catch (SQLException ex) {

	            System.out.println(ex.getNextException());
	            
	            if (con != null) {
	                try {
	                    con.rollback();
	                } catch (SQLException ex1) {
	                    Logger lgr = Logger.getLogger(BatchUpdate.class.getName());
	                    lgr.log(Level.WARNING, ex1.getMessage(), ex1);
	                }
	            }

	            Logger lgr = Logger.getLogger(BatchUpdate.class.getName());
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
	                Logger lgr = Logger.getLogger(BatchUpdate.class.getName());
	                lgr.log(Level.WARNING, ex.getMessage(), ex);
	            }
	        }
	    }
	}
