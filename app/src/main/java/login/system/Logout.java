package login.system;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

public class Logout extends AppCompatActivity {

    public Logout() {

    }

    public void clearingLocal () {
        LoginInfoLocal loginInfoLocal = new LoginInfoLocal(Logout.this);
        loginInfoLocal.clearLogin();
    }
}
