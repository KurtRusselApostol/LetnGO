package login.system;

import android.content.Context;
import android.content.SharedPreferences;

//This class sets and gets the status of Google Sign In
public class GLoginChecker {
    SharedPreferences sp;
    SharedPreferences.Editor spe;
    boolean isGLoggedIn;

    public GLoginChecker (Context context) {
        sp = context.getSharedPreferences("gstatus", Context.MODE_PRIVATE);
        spe = sp.edit();
    }

    //Fetches status of Google Sign In
    public boolean getStatGLogin () {
        isGLoggedIn = sp.getBoolean("storedGStat", false);
        return isGLoggedIn;
    }

    //Sets Google Sign In True
    public void onGLogin() {
        spe.putBoolean("storedGStat", true).commit();
    }

    //Sets Google Sign In False
    public void offGLogin () {
        spe.putBoolean("storedGStat", false).commit();
    }
}
