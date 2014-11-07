package twitter;

public interface Twitterclient {

	final static String postStatus = "tw.updateStatus";
    public void postStatus();

final static String getStatus  = "statuses";
public Object getStatus();

final static String getUserTimeline = "tw.getHomeTimeline";
public void getUserTimeline();


    }

