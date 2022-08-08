package com.example.letngo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class Create_post extends AppCompatActivity {

    Button gallery;
    ImageView showimage;
//    Button btPicker;
    EditText write_post;
    ImageButton back;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference().child("Posting");
    static final int PICK_IMAGE_REQUEST = 2;
    Uri mImageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);



        gallery = findViewById(R.id.btn_gallery);
        showimage = findViewById(R.id.image_view);
        write_post = findViewById(R.id.instruction_create);
        back = findViewById(R.id.img_exit);


        back.setOnClickListener(v -> onBackPressed());

        gallery.setOnClickListener(v -> openFileChooser());


        Button move = findViewById(R.id.btn_post);
        move.setOnClickListener(v -> {

            String postText = write_post.getText().toString();

            if (TextUtils.isEmpty(postText)){
                Toast.makeText(Create_post.this, "Create post text.", Toast.LENGTH_LONG).show();
            }
            else {
                HashMap <String, String> Post= new HashMap<>();
                Post.put("create post", postText);
                reference.push().setValue(Post);
                Toast.makeText(Create_post.this, "Success", Toast.LENGTH_LONG).show();
                Intent intent=new Intent(Create_post.this,FragmentPost.class);
                startActivity(intent);
            }
        });
    }

    public void openFileChooser()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }

    //Button add Photo/Videos still need to modify try this tutorial https://youtu.be/HVzjeE3EOuQ
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null)
        {
            mImageUri = data.getData();
            Picasso.with(this).load(mImageUri).into(showimage);
        }

    }
}