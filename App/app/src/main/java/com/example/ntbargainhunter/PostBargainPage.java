package com.example.ntbargainhunter;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.HashMap;
import java.util.Map;


public class PostBargainPage extends AppCompatActivity {

//    private Toolbar newpostToolbar;
    private ImageView newPostImage;
    private EditText newPostDesc;
    private Button newPostBtn;

    private Uri postImageUri=null;
    private ProgressBar newPostProgress;
    private StorageReference storageReference;
    private FirebaseFirestore firebaseFirestore;
//    private FirebaseAuth firebaseAuth;

//    private String current_user_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_bargain_page);

        storageReference = FirebaseStorage.getInstance().getReference();
        firebaseFirestore=firebaseFirestore.getInstance();
//        firebaseAuth = FirebaseAuth.getInstance();

//        current_user_id = firebaseAuth.getCurrentUser().getUid();

//        newpostToolbar = findViewById(R.id.new_post_toolbar);
//        setSupportActionBar(newpostToolbar);
//        getSupportActionBar().setTitle("Add New Post");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        newPostImage=findViewById(R.id.new_post_image);

        newPostDesc=findViewById(R.id.new_post_desc);
        newPostBtn=findViewById(R.id.post_btn);
        newPostProgress=findViewById(R.id.new_post_progress);

        newPostImage.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {


                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setMinCropResultSize(512,512)
                        .setAspectRatio(1, 1)
                        .start(PostBargainPage.this);
//permission pic
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

                    if(ContextCompat.checkSelfPermission(PostBargainPage.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){

                        Toast.makeText(PostBargainPage.this, "Permission Denied", Toast.LENGTH_LONG).show();
                        ActivityCompat.requestPermissions(PostBargainPage.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

                    } else {

                        Toast.makeText(PostBargainPage.this, "you have permission", Toast.LENGTH_LONG).show();

                    }

                }

            }
        });
        newPostBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                final String desc = newPostDesc.getText().toString();
                if (!TextUtils.isEmpty(desc) && postImageUri != null) {

                    newPostProgress.setVisibility(View.VISIBLE);

                    String randomName = FieldValue.serverTimestamp().toString();

                    final StorageReference filepath= storageReference.child("post_image").child(randomName + ".jpg");
                    filepath.putFile(postImageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {

                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                            if (task.isSuccessful()){

                                filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        final String downloadUri=uri.toString();
                                        Map<String, Object> postMap = new HashMap<>();
                                        postMap.put("image_url", downloadUri);
//                                      postMap.put("image_thumb", downloadthumbUri);
                                        postMap.put("desc", desc);
//                                postMap.put("user_id", current_user_id);
                                        postMap.put("timestamp", FieldValue.serverTimestamp());
                                    }
                                });

//                                Task<Uri>result=task.getResult().getMetadata().getReference().getDownloadUrl();
//                                String downloadUri= task.getResult().getUploadSessionUri().toString();





                                firebaseFirestore.collection("posts").add(postImageUri).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentReference> task) {

                                        if(task.isSuccessful()){

                                            Toast.makeText(PostBargainPage.this, "Post was added", Toast.LENGTH_LONG).show();
                                            Intent mainIntent = new Intent(PostBargainPage.this, HomePage.class);
                                            startActivity(mainIntent);
                                            finish();

                                        }else{
                                          System.out.println("Action could not be completed this time.");

                                        }

                                        newPostProgress.setVisibility(View.INVISIBLE);


                                    }
                                });

                            }else{

                                newPostProgress.setVisibility(View.INVISIBLE);


                            }



                        }




                    });


                }
            }
                });
            }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result=CropImage.getActivityResult(data);
            if(resultCode==RESULT_OK){
               postImageUri=result.getUri();
               newPostImage.setImageURI(postImageUri);

            }else if (resultCode==CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
                Exception error = result.getError();
            }
        }
    }
    }

