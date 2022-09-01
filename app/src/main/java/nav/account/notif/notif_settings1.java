package nav.account.notif;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.letngo.R;

public class notif_settings1 extends AppCompatActivity {

    //TextView save;
    ImageButton back;
    TableRow allNotif, accActivity, guessPolicies, offersUpdates, reminder;
    Switch allNotifSwitch, accActivitySwitch, guessPoliciesSwitch, offersUpdatesSwitch, reminderSwitch;
    //int numNotifOff = 0;
    boolean accActivityOn, guessPoliciesOn, offersUpdatesOn, reminderOn;

    //
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_notif_settings1);

        sharedPreferences = getSharedPreferences("pref", 0); //file that saves notification preference
        int allNotifSP = sharedPreferences.getInt("allNotifSP", 0); //sets radio button to unselected at first
        int accActivitySP = sharedPreferences.getInt("accActivitySP", 0); //sets radio button to unselected at first
        int guessPoliciesSP = sharedPreferences.getInt("guessPoliciesSP", 0); //sets radio button to unselected at first
        int offersUpdatesSP = sharedPreferences.getInt("offersUpdatesSP", 0); //sets radio button to unselected at first
        int reminderSP = sharedPreferences.getInt("reminderSP", 0); //sets radio button to unselected at first

        editor = sharedPreferences.edit();

        allNotif = findViewById(R.id.allNotif);
        accActivity = findViewById(R.id.accActivity);
        guessPolicies = findViewById(R.id.guessPolicies);
        offersUpdates = findViewById(R.id.offersUpdates);
        reminder = findViewById(R.id.reminder);

        allNotifSwitch = findViewById(R.id.allNotifSwitch);
        accActivitySwitch = findViewById(R.id.accActivitySwitch);
        guessPoliciesSwitch = findViewById(R.id.guessPoliciesSwitch);
        offersUpdatesSwitch = findViewById(R.id.offersUpdatesSwitch);
        reminderSwitch = findViewById(R.id.reminderSwitch);

        back = findViewById(R.id.img_back);
        back.setOnClickListener(v -> onBackPressed());

//        notifRadioGroup = findViewById(R.id.notifRadioGroup);
//        getNotif = findViewById(R.id.radioButton);
//        dontGetNotif = findViewById(R.id.radioButton2);
        switch (allNotifSP) {
            case 0:
                allNotifSwitch.setChecked(false);
                accActivity.setVisibility(View.GONE);
                guessPolicies.setVisibility(View.GONE);
                offersUpdates.setVisibility(View.GONE);
                reminder.setVisibility(View.GONE);
                break;
            case 1:
                allNotifSwitch.setChecked(true);
                break;
        }
        switch (accActivitySP) {
            case 0:
                accActivitySwitch.setChecked(false);
                accActivityOn = false;
                break;
            case 1:
                accActivitySwitch.setChecked(true);
                accActivityOn = true;
                break;
        }
        switch (guessPoliciesSP){
            case 0:
                guessPoliciesSwitch.setChecked(false);
                guessPoliciesOn = false;
                break;
            case 1:
                guessPoliciesSwitch.setChecked(true);
                guessPoliciesOn = true;
                break;
        }
        switch (offersUpdatesSP){
            case 0:
                offersUpdatesSwitch.setChecked(false);
                offersUpdatesOn = false;
                break;
            case 1:
                offersUpdatesSwitch.setChecked(true);
                offersUpdatesOn = true;
                break;
        }
        switch (reminderSP){
            case 0:
                reminderSwitch.setChecked(false);
                reminderOn = false;
                break;
            case 1:
                reminderSwitch.setChecked(true);
                reminderOn = true;
                break;
        }

        allNotifSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked == true){
                    accActivity.setVisibility(View.VISIBLE);
                    guessPolicies.setVisibility(View.VISIBLE);
                    offersUpdates.setVisibility(View.VISIBLE);
                    reminder.setVisibility(View.VISIBLE);
                    accActivitySwitch.setChecked(true);
                    guessPoliciesSwitch.setChecked(true);
                    offersUpdatesSwitch.setChecked(true);
                    reminderSwitch.setChecked(true);
                    editor.putInt("allNotifSP", 1);
                    editor.putInt("accActivitySP", 1);
                    editor.putInt("guessPoliciesSP", 1);
                    editor.putInt("offersUpdatesSP", 1);
                    editor.putInt("reminderSwitchSP", 1);
                }
                else{
                    accActivity.setVisibility(View.GONE);
                    guessPolicies.setVisibility(View.GONE);
                    offersUpdates.setVisibility(View.GONE);
                    reminder.setVisibility(View.GONE);
                    editor.putInt("allNotifSP", 0);
                }
                editor.commit();
            }
        });

        accActivitySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked == true){
                    accActivityOn = true;
                    editor.putInt("accActivitySP", 1);
                }
                else{
                    accActivityOn = false;
                    SetAllNotifOff();
                    editor.putInt("accActivitySP", 0);
                }
                editor.commit();
            }
        });

        guessPoliciesSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked == true){
                    guessPoliciesOn = true;
                    editor.putInt("guessPoliciesSP", 1);
                }
                else{
                    guessPoliciesOn = false;
                    SetAllNotifOff();
                    editor.putInt("guessPoliciesSP", 0);
                }
                editor.commit();
            }
        });

        offersUpdatesSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked == true){
                    offersUpdatesOn = true;
                    editor.putInt("offersUpdatesSP", 1);
                }
                else{
                    offersUpdatesOn = false;
                    SetAllNotifOff();
                    editor.putInt("offersUpdatesSP", 0);
                }
                editor.commit();
            }
        });

        reminderSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked == true){
                    reminderOn = true;
                    editor.putInt("reminderSP", 1);
                }
                else{
                    reminderOn = false;
                    SetAllNotifOff();
                    editor.putInt("reminderSP", 0);
                }
                editor.commit();
            }
        });
    }
    protected void SetAllNotifOff(){
        if (accActivityOn == false && guessPoliciesOn == false && offersUpdatesOn == false && reminderOn == false){
            allNotifSwitch.setChecked(false);
            accActivity.setVisibility(View.GONE);
            guessPolicies.setVisibility(View.GONE);
            offersUpdates.setVisibility(View.GONE);
            reminder.setVisibility(View.GONE);
            editor.putInt("allNotifSP", 0);
            editor.commit();
        }
    }

}