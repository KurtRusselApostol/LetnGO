package nav.account;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.letngo.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Edit_Profile extends AppCompatActivity {

    //    String email, password;
    EditText Firstname;
    EditText Lastname;
    EditText Email;
    EditText Birthday;
    EditText ContactNo;
    EditText Age;
    EditText Gender;
    EditText Address;
    EditText Description;
    EditText BankAccount;
    EditText BankNumber;

    ImageButton back;
    Button update;

    //FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    //static final String Users = "User_account";

    //private FirebaseAuth mAuth;
    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
    String userUid;

    {
        assert currentUser != null;
        userUid = currentUser.getUid();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        back = findViewById(R.id.editprofile_back);
        update = findViewById(R.id.btn_update);


        //Intent intent = getIntent();
        //email = getIntent().getStringExtra("email");
        //Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();


        //Hooks
        Firstname = findViewById(R.id.ed_Fname);
        Lastname = findViewById(R.id.ed_Lname);
        Email = findViewById(R.id.ed_email);
        Birthday = findViewById(R.id.ed_birthday);
        ContactNo = findViewById(R.id.ed_contact_no);
        Age = findViewById(R.id.ed_Age);

        Gender = findViewById(R.id.ed_Gender);
        Address = findViewById(R.id.ed_Address);
        Description = findViewById(R.id.ed_Description);
        BankAccount = findViewById(R.id.ed_bank_account);
        BankNumber = findViewById(R.id.ed_bank_number);

        //Firstname.setText(email);
        //Firstname.setText(text);

        reference = FirebaseDatabase.getInstance().getReference("User_account");
        reference.child(userUid).get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.e("firebase", "Error getting data", task.getException());
            } else {
                if (task.getResult().exists()) {
                    //Toast.makeText(getApplicationContext(),"SUCCESS",Toast.LENGTH_SHORT).show();
                    DataSnapshot dataSnapshot = task.getResult();

                    String firstname = String.valueOf(dataSnapshot.child("FirstName").getValue());
                    String lastname = String.valueOf(dataSnapshot.child("LastName").getValue());
                    String email = String.valueOf(dataSnapshot.child("Email").getValue());
                    String birthday = String.valueOf(dataSnapshot.child("Birthday").getValue());

                    String mobile = String.valueOf(dataSnapshot.child("Mobile").getValue());
                    String age = String.valueOf(dataSnapshot.child("Age").getValue());
                    String gender = String.valueOf(dataSnapshot.child("Gender").getValue());
                    String address = String.valueOf(dataSnapshot.child("Address").getValue());

                    String description = String.valueOf(dataSnapshot.child("Description").getValue());
//                    String bank_account = String.valueOf(dataSnapshot.child("BankAccount").getValue());
//                    String bank_number = String.valueOf(dataSnapshot.child("BankNumber").getValue());

                    Firstname.setText(firstname);
                    Lastname.setText(lastname);
                    Email.setText(email);
                    Birthday.setText(birthday);
                    ContactNo.setText(mobile);
                    Age.setText(age);
                    Gender.setText(gender);
                    Address.setText(address);
                    Description.setText(description);

                } else {
                    Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();
                }
            }
        });


        back.setOnClickListener(v -> onBackPressed());

        update.setOnClickListener(view -> {
            /*
            reference.child(userUid).child("FirstName").setValue(Firstname.getText().toString());
            reference.child(userUid).child("LastName").setValue(Lastname.getText().toString());
            reference.child(userUid).child("Email").setValue(Email.getText().toString());
            reference.child(userUid).child("Birthday").setValue(Birthday.getText().toString());
            reference.child(userUid).child("Mobile").setValue(ContactNo.getText().toString());

            reference.child(userUid).child("Age").setValue(Age.getText().toString());
            reference.child(userUid).child("Gender").setValue(Gender.getText().toString());
            reference.child(userUid).child("Address").setValue(Address.getText().toString());
            reference.child(userUid).child("Description").setValue(Description.getText().toString());
            reference.child(userUid).child("BankAccount").setValue(BankAccount.getText().toString());

            reference.child(userUid).child("BankNumber").setValue(BankNumber.getText().toString());

             */
            reference = FirebaseDatabase.getInstance().getReference("User_account");
            reference.child(userUid).get().addOnCompleteListener(task -> {
                task.getResult();

                if (Firstname.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "First Name must not be empty", Toast.LENGTH_LONG).show();
                    Firstname.requestFocus();
                } else {

                    if (Lastname.getText().toString().equals("")) {
                        Toast.makeText(getApplicationContext(), "Last Name must not be empty", Toast.LENGTH_LONG).show();
                        Lastname.requestFocus();
                    } else {

                        if (Birthday.getText().toString().equals("")) {
                            Toast.makeText(getApplicationContext(), "Birthday must not be empty", Toast.LENGTH_LONG).show();
                            Birthday.requestFocus();
                        } else {

                            if (ContactNo.getText().toString().equals("")) {
                                Toast.makeText(getApplicationContext(), "Contact No. must not be empty", Toast.LENGTH_LONG).show();
                                ContactNo.requestFocus();
                            }
                            if (ContactNo.getText().toString().length() != 10) {
                                Toast.makeText(getApplicationContext(), "Contact No. must be 10 digits", Toast.LENGTH_LONG).show();
                                ContactNo.requestFocus();
                            } else {

                                if (Age.getText().toString().equals("")) {
                                    Toast.makeText(getApplicationContext(), "Age must be not be empty", Toast.LENGTH_LONG).show();
                                    Age.requestFocus();
                                }
                                if (Integer.parseInt(Age.getText().toString()) <= 18) {
                                    Toast.makeText(getApplicationContext(), "Age must be equal or greater than 18", Toast.LENGTH_LONG).show();
                                    Age.requestFocus();
                                } else {

                                    if (Gender.getText().toString().equals("")) {
                                        Toast.makeText(getApplicationContext(), "Gender must not be empty", Toast.LENGTH_LONG).show();
                                        Gender.requestFocus();
                                    } else {

                                        if (Address.getText().toString().equals("")) {
                                            Toast.makeText(getApplicationContext(), "Address must not be empty", Toast.LENGTH_LONG).show();
                                            Address.requestFocus();
                                        } else {

                                            if (Description.getText().toString().equals("")) {
                                                Toast.makeText(getApplicationContext(), "Description must not be empty", Toast.LENGTH_LONG).show();
                                                Description.requestFocus();
                                            } else {

                                                reference.child(userUid).child("FirstName").setValue(Firstname.getText().toString());
                                                reference.child(userUid).child("LastName").setValue(Lastname.getText().toString());
                                                reference.child(userUid).child("Birthday").setValue(Birthday.getText().toString());
                                                reference.child(userUid).child("Age").setValue(Age.getText().toString());
                                                reference.child(userUid).child("Gender").setValue(Gender.getText().toString());
                                                reference.child(userUid).child("Address").setValue(Address.getText().toString());
                                                reference.child(userUid).child("Description").setValue(Description.getText().toString());

                                                Toast.makeText(getApplicationContext(), "Profile Updated Successfully", Toast.LENGTH_LONG).show();
                                                Intent i = new Intent(getApplicationContext(), Edit_Profile.class);
                                                finish();
                                                startActivity(i);

                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            });

        });
    }
}


