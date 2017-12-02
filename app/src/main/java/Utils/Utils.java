package Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.preference.PreferenceManager;

public class Utils {
    public static Location location;
    public static String userTable = "users";
    public static String userId = "userId";

    // shared methods
    public static void setShared(Context context, String name, String value) {
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(name, value);
        editor.commit();
    }

    public static String getShared(Context context, String name, String defaultValue) {
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        return prefs.getString(name, defaultValue);

    }
}
