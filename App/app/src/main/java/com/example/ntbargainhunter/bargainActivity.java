package com.example.ntbargainhunter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;


public class bargainActivity extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;

    private String bargain_post_id;
    private String current_user_id;
    private TextView titleView;
    private TextView descView;
    private TextView bargainDate;
    private TextView expiry;
    private TextView bargainUserName;
    private CircleImageView bargainUserImage;
    private Uri userImageURI;

    private ImageView backView;
    private Uri mainImageURI;
    private ImageView bargainLikeBtn;
    private ImageView bargainImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bargain_expanded);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        current_user_id = firebaseAuth.getCurrentUser().getUid();
        bargain_post_id = getIntent().getStringExtra("bargain_post_id");

        String desc_data = getIntent().getStringExtra("bargain_post_desc");
        String title_data = getIntent().getStringExtra("bargain_post_title");
        String user_id = getIntent().getStringExtra("bargain_post_user");
        String downloadUri = getIntent().getStringExtra("bargain_post_imgURI");
        String thumbUri = getIntent().getStringExtra("bargain_post_thumbURI");
        String expiryText = getIntent().getStringExtra("bargain_post_expiry");
        titleView = findViewById(R.id.bargain_title);
        titleView.setText(title_data);
        descView = findViewById(R.id.bargain_desc);
        descView.setText(desc_data);
        expiry = findViewById(R.id.bargain_expiry);
        expiry.setText(expiryText);
        backView = findViewById(R.id.return_home);
        bargainLikeBtn = findViewById(R.id.bargain_like_btn);
        bargainImageView = findViewById(R.id.bargain_image);
        bargainDate = findViewById(R.id.bargain_date);

        String date = getIntent().getStringExtra("bargain_post_date");
        bargainDate.setText(date);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.image_placeholder);

        Glide.with(bargainActivity.this).setDefaultRequestOptions(requestOptions).load(downloadUri).thumbnail(Glide.with(bargainActivity.this).load(thumbUri)).into(bargainImageView);

        firebaseFirestore.collection("Users").document(user_id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if(task.isSuccessful()){

                    final String userName = task.getResult().getString("name");
                    final String userImage = task.getResult().getString("image");
                    bargainUserImage = findViewById(R.id.bargain_user_image);
                    bargainUserName = findViewById(R.id.bargain_user_name);
                    userImageURI = Uri.parse(userImage);

                    bargainUserName.setText(userName + " posted");

                    RequestOptions placeholderOption = new RequestOptions();
                    placeholderOption.placeholder(R.drawable.profile_placeholder);
                    Glide.with(bargainActivity.this).setDefaultRequestOptions(placeholderOption).load(userImage).into(bargainUserImage);


                } else {

                    //Firebase Exception

                }


            }
        });


        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bargainActivity.this.finish();

            }
        });



        //Get Likes
        firebaseFirestore.collection("Posts/" + bargain_post_id + "/Likes").document(current_user_id).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {

                if(documentSnapshot.exists()){

                    bargainLikeBtn.setImageDrawable(getDrawable(R.mipmap.action_like_accent));

                } else {

                    bargainLikeBtn.setImageDrawable(getDrawable(R.mipmap.action_like_gray));

                }

            }
        });


        //Likes Feature
        bargainLikeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firebaseFirestore.collection("Posts/" + bargain_post_id + "/Likes").document(current_user_id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        if(!task.getResult().exists()){

                            Map<String, Object> likesMap = new HashMap<>();
                            likesMap.put("timestamp", FieldValue.serverTimestamp());

                            firebaseFirestore.collection("Posts/" + bargain_post_id + "/Likes").document(current_user_id).set(likesMap);

                        } else {

                            firebaseFirestore.collection("Posts/" + bargain_post_id + "/Likes").document(current_user_id).delete();

                        }

                    }
                });

                firebaseFirestore.collection("Users/" + current_user_id + "/Favourites").document(bargain_post_id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        if(!task.getResult().exists()){

                            Map<String, Object> likesMap = new HashMap<>();
                            likesMap.put("timestamp", FieldValue.serverTimestamp());

                            firebaseFirestore.collection("Users/" + current_user_id + "/Favourites").document(bargain_post_id).set(likesMap);

                        } else {

                            firebaseFirestore.collection("Users/" + current_user_id + "/Favourites").document(bargain_post_id).delete();

                        }

                    }
                });





            }
        });

    }



}
