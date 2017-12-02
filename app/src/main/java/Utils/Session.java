package Utils;

import android.content.Context;

public class Session {

    private static Session instance = null;
    Context context;

    public static Session getSession(Context context) {
        if(instance == null) {
            instance = new Session(context);
        }
        return instance;
    }

    protected Session(Context context) {
        this.context = context;
    }

    public String getUserId() {
        return Utils.getShared(context, Utils.userId,"");
    }

    public void setUserId(String userId) {//done clear
        Utils.setShared(context, Utils.userId,userId);
    }

}
