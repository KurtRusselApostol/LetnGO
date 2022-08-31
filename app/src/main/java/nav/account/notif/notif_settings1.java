package nav.account.notif;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.letngo.R;

public class notif_settings1 extends AppCompatActivity {

    TextView save;
    ImageButton back;
    //
    RadioGroup notifRadioGroup;
    RadioButton getNotif, dontGetNotif;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_notif_settings1);

        sharedPreferences = getSharedPreferences("pref", 0); //file that saves notification preference
        int notifSP = sharedPreferences.getInt("notifSP", 3); //sets radio button to unselected at first
        editor = sharedPreferences.edit();

        notifRadioGroup = findViewById(R.id.notifRadioGroup);
        getNotif = findViewById(R.id.radioButton);
        dontGetNotif = findViewById(R.id.radioButton2);

        if (notifSP == 1){
            getNotif.setChecked(true); //checks get notification radio button
        }else if (notifSP == 0){
            dontGetNotif.setChecked(true); //checks dont get notification radio button
        }


        save = findViewById(R.id.textView81);
        back = findViewById(R.id.img_back);
        back.setOnClickListener(v -> onBackPressed());

        if(dontGetNotif.isChecked()){
            save.setOnClickListener(v -> onBackPressed()); //sets the 'Save' button into a back button when 'dont get notification' section is set
        }

    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioButton:
                if (checked){
                    save.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            editor.putInt("notifSP", 1);
                            Intent intent = new Intent(notif_settings1.this, notif_settings2.class);
                            startActivity(intent);
                        }
                    });
                }
                break;
            case R.id.radioButton2:
                if (checked) {
                    editor.putInt("notifSP", 0);
                    save.setOnClickListener(v -> onBackPressed());
                }
                break;
        }
        editor.commit();

    }
}