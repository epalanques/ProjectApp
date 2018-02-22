package ptdomains.tinderlikeapp.Matches;

/**
 * Created by eric on 22/02/18.
 */

public class MatchObject {
    private String userId;

    public MatchObject (String userId){
        this.userId = userId;
    }

    public String getUserId(){
        return userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }
}
