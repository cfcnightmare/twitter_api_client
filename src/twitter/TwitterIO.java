
package twitter;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import twitter4j.Status;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;


public class TwitterIO {
	
	//база,таблица
    static int stop = 0;
    final static int connect = 0;
    final String JDBC_DRIVER = "org.postgresql.jdbc.Driver";
    final static String DB_URL = "jdbc:postgresql://localhost:5433/postgres";

    // логин,пароль
    final static String USER = "postgres";
    final static String PASS = "12345";
   
    public static void main(String[] args)  throws Exception{
    	
ConfigurationBuilder cb = new ConfigurationBuilder();
        
        cb.setDebugEnabled(true);
        cb.setOAuthConsumerKey("GrO1fdysw80wN0t7AjsP4yMtg");
        cb.setOAuthConsumerSecret("vkXFw1iwKmR7QkErBHielcvno9uwy82nS20G2EZP3iL6f3ybAR");
        cb.setOAuthAccessToken("2859592708-wYYGmguzOaImRPjxirc1TZj3EkNdWWHOJDCNskq");
        cb.setOAuthAccessTokenSecret("0r61bdwmrYEXlYheU2lNEGyvEMhJQTd7deAFCzYcuRaNk");
        
        TwitterFactory tf = new TwitterFactory(cb.build());
        
        
        
        
        twitter4j.Twitter tw = tf.getInstance();
		
        
        
        
        
    	List <Status> statuses = tw.getHomeTimeline();
        for (Status status:statuses){
     	    long userId=status.getUser().getId();
 		    long postId= status.getId();
 			String post = status.getText();
 			String userName = status.getUser().getName();
        
        
        
        //Status stat = tw.updateStatus("HelloMoto");
        
        
        
			
			
			
			
			
			System.out.println(userName+ ":"+ userId+ " write:"+ post + "with postID:" + postId);
			Connection conn = null;
			
            
            try {
                
                    if (connect != 1) {
                    	

                        // STEP 3: Open a connection
                        System.out.println("Connecting to    database...");
                        conn = DriverManager.getConnection(DB_URL, USER,
                                PASS);
                        // no need to display the fields... directly insert
                        // them and work on it
                        
                        
                 	      
                           
                        
                          
                        
                        
                       String query = "insert into twitter (userid, username, postid,post) values (?,?,?,?)" ;
                        
                        Statement st = conn.createStatement();
                        PreparedStatement pst = new PreparedStatement() {
							
							@Override
							public <T> T unwrap(Class<T> arg0) throws SQLException {
								// TODO Auto-generated method stub
								return null;
							}
							
							@Override
							public boolean isWrapperFor(Class<?> arg0) throws SQLException {
								// TODO Auto-generated method stub
								return false;
							}
							
							@Override
							public void setQueryTimeout(int arg0) throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void setPoolable(boolean arg0) throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void setMaxRows(int arg0) throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void setMaxFieldSize(int arg0) throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void setFetchSize(int arg0) throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void setFetchDirection(int arg0) throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void setEscapeProcessing(boolean arg0) throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void setCursorName(String arg0) throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public boolean isPoolable() throws SQLException {
								// TODO Auto-generated method stub
								return false;
							}
							
							@Override
							public boolean isClosed() throws SQLException {
								// TODO Auto-generated method stub
								return false;
							}
							
							@Override
							public boolean isCloseOnCompletion() throws SQLException {
								// TODO Auto-generated method stub
								return false;
							}
							
							@Override
							public SQLWarning getWarnings() throws SQLException {
								// TODO Auto-generated method stub
								return null;
							}
							
							@Override
							public int getUpdateCount() throws SQLException {
								// TODO Auto-generated method stub
								return 0;
							}
							
							@Override
							public int getResultSetType() throws SQLException {
								// TODO Auto-generated method stub
								return 0;
							}
							
							@Override
							public int getResultSetHoldability() throws SQLException {
								// TODO Auto-generated method stub
								return 0;
							}
							
							@Override
							public int getResultSetConcurrency() throws SQLException {
								// TODO Auto-generated method stub
								return 0;
							}
							
							@Override
							public ResultSet getResultSet() throws SQLException {
								// TODO Auto-generated method stub
								return null;
							}
							
							@Override
							public int getQueryTimeout() throws SQLException {
								// TODO Auto-generated method stub
								return 0;
							}
							
							@Override
							public boolean getMoreResults(int arg0) throws SQLException {
								// TODO Auto-generated method stub
								return false;
							}
							
							@Override
							public boolean getMoreResults() throws SQLException {
								// TODO Auto-generated method stub
								return false;
							}
							
							@Override
							public int getMaxRows() throws SQLException {
								// TODO Auto-generated method stub
								return 0;
							}
							
							@Override
							public int getMaxFieldSize() throws SQLException {
								// TODO Auto-generated method stub
								return 0;
							}
							
							@Override
							public ResultSet getGeneratedKeys() throws SQLException {
								// TODO Auto-generated method stub
								return null;
							}
							
							@Override
							public int getFetchSize() throws SQLException {
								// TODO Auto-generated method stub
								return 0;
							}
							
							@Override
							public int getFetchDirection() throws SQLException {
								// TODO Auto-generated method stub
								return 0;
							}
							
							@Override
							public Connection getConnection() throws SQLException {
								// TODO Auto-generated method stub
								return null;
							}
							
							@Override
							public int executeUpdate(String arg0, String[] arg1) throws SQLException {
								// TODO Auto-generated method stub
								return 0;
							}
							
							@Override
							public int executeUpdate(String arg0, int[] arg1) throws SQLException {
								// TODO Auto-generated method stub
								return 0;
							}
							
							@Override
							public int executeUpdate(String arg0, int arg1) throws SQLException {
								// TODO Auto-generated method stub
								return 0;
							}
							
							@Override
							public int executeUpdate(String arg0) throws SQLException {
								// TODO Auto-generated method stub
								return 0;
							}
							
							@Override
							public ResultSet executeQuery(String arg0) throws SQLException {
								// TODO Auto-generated method stub
								return null;
							}
							
							@Override
							public int[] executeBatch() throws SQLException {
								// TODO Auto-generated method stub
								return null;
							}
							
							@Override
							public boolean execute(String arg0, String[] arg1) throws SQLException {
								// TODO Auto-generated method stub
								return false;
							}
							
							@Override
							public boolean execute(String arg0, int[] arg1) throws SQLException {
								// TODO Auto-generated method stub
								return false;
							}
							
							@Override
							public boolean execute(String arg0, int arg1) throws SQLException {
								// TODO Auto-generated method stub
								return false;
							}
							
							@Override
							public boolean execute(String arg0) throws SQLException {
								// TODO Auto-generated method stub
								return false;
							}
							
							@Override
							public void closeOnCompletion() throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void close() throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void clearWarnings() throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void clearBatch() throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void cancel() throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void addBatch(String arg0) throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void setUnicodeStream(int parameterIndex, InputStream x, int length)
									throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void setURL(int parameterIndex, URL x) throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal)
									throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void setTimestamp(int parameterIndex, Timestamp x)
									throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void setTime(int parameterIndex, Time x, Calendar cal)
									throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void setTime(int parameterIndex, Time x) throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void setString(int parameterIndex, String x) throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void setShort(int parameterIndex, short x) throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void setSQLXML(int parameterIndex, SQLXML xmlObject)
									throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void setRowId(int parameterIndex, RowId x) throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void setRef(int parameterIndex, Ref x) throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void setObject(int parameterIndex, Object x, int targetSqlType,
									int scaleOrLength) throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void setObject(int parameterIndex, Object x, int targetSqlType)
									throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void setObject(int parameterIndex, Object x) throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void setNull(int parameterIndex, int sqlType, String typeName)
									throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void setNull(int parameterIndex, int sqlType) throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void setNString(int parameterIndex, String value)
									throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void setNClob(int parameterIndex, Reader reader, long length)
									throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void setNClob(int parameterIndex, Reader reader) throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void setNClob(int parameterIndex, NClob value) throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void setNCharacterStream(int parameterIndex, Reader value,
									long length) throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void setNCharacterStream(int parameterIndex, Reader value)
									throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void setLong(int parameterIndex, long x) throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void setInt(int parameterIndex, int x) throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void setFloat(int parameterIndex, float x) throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void setDouble(int parameterIndex, double x) throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void setDate(int parameterIndex, Date x, Calendar cal)
									throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void setDate(int parameterIndex, Date x) throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void setClob(int parameterIndex, Reader reader, long length)
									throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void setClob(int parameterIndex, Reader reader) throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void setClob(int parameterIndex, Clob x) throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void setCharacterStream(int parameterIndex, Reader reader,
									long length) throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void setCharacterStream(int parameterIndex, Reader reader, int length)
									throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void setCharacterStream(int parameterIndex, Reader reader)
									throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void setBytes(int parameterIndex, byte[] x) throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void setByte(int parameterIndex, byte x) throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void setBoolean(int parameterIndex, boolean x) throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void setBlob(int parameterIndex, InputStream inputStream, long length)
									throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void setBlob(int parameterIndex, InputStream inputStream)
									throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void setBlob(int parameterIndex, Blob x) throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void setBinaryStream(int parameterIndex, InputStream x, long length)
									throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void setBinaryStream(int parameterIndex, InputStream x, int length)
									throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void setBinaryStream(int parameterIndex, InputStream x)
									throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void setBigDecimal(int parameterIndex, BigDecimal x)
									throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void setAsciiStream(int parameterIndex, InputStream x, long length)
									throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void setAsciiStream(int parameterIndex, InputStream x, int length)
									throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void setAsciiStream(int parameterIndex, InputStream x)
									throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void setArray(int parameterIndex, Array x) throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public ParameterMetaData getParameterMetaData() throws SQLException {
								// TODO Auto-generated method stub
								return null;
							}
							
							@Override
							public ResultSetMetaData getMetaData() throws SQLException {
								// TODO Auto-generated method stub
								return null;
							}
							
							@Override
							public int executeUpdate() throws SQLException {
								// TODO Auto-generated method stub
								return 0;
							}
							
							@Override
							public ResultSet executeQuery() throws SQLException {
								// TODO Auto-generated method stub
								return null;
							}
							
							@Override
							public boolean execute() throws SQLException {
								// TODO Auto-generated method stub
								return false;
							}
							
							@Override
							public void clearParameters() throws SQLException {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void addBatch() throws SQLException {
								// TODO Auto-generated method stub
								
							}
						};
                        try {
							pst.setLong(1, userId);
							pst.setString(2, userName);
							pst.setLong(3, postId);
							pst.setString(4, post);
                  st.executeUpdate(query);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                        	
                        	
                       
                        
                        
                    
                          
                        
                   	 
                        System.out
                        .println("Inserted records into the table...");
                stop = 1;
                System.exit(0);
                st.close();
                conn.close();
                }
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
   
   
}

   
    }}}	
                         
                         
                         
                         
                         
                         
                        
                         
                        
                          
                        	  
                        	  
								  
									
								
							
                             
                                       
								
								
							
						
                         
                
          
       
       
       
       
		
		
       
			
  
			
			
			
    	   
    	  
    	   
     