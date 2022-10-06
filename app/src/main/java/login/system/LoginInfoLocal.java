package login.system;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

//This class stores login information in the device locally.
public class LoginInfoLocal {
    SharedPreferences sp;
    SharedPreferences.Editor spe;
    String storedDetails;
    String storedJson;
    String storeFetch;
    List<String> userDetails = new ArrayList<>();
    List<String> listFetch = new ArrayList<>();

    //Method that defines context, sp and spe
    public LoginInfoLocal (Context context) {
        sp = context.getSharedPreferences("session", Context.MODE_PRIVATE);
        spe = sp.edit();
    }

    //Method that sets login status
    public void logStatus (boolean status) { spe.putBoolean("sessionStatus", status); }

    //Method that stores the login data
    public void storeLogin (User user) {
        Gson gson = new Gson();
        String email = user.getEma();
        String password = user.getPass();
        userDetails.add(email);
        userDetails.add(password);
        storedJson = gson.toJson(userDetails);
        spe.putString(storedDetails, storedJson).commit();
    }

    //Method that fetches login data
    public User fetchLogin () {
        Gson gson = new Gson();
        try {
            storeFetch = sp.getString(storedDetails, " ");
            Type type = new TypeToken<List<String>>(){}.getType();
            listFetch = gson.fromJson(storeFetch, type);

            return new User (listFetch.get(0), listFetch.get(1));
        }
        catch (Exception e) { return null; }
    }

    //Method that clears the login data when the user logs out
    public void clearLogin () {
        spe.putBoolean("sessionStatus", false);
        spe.clear().commit();
    }
}
