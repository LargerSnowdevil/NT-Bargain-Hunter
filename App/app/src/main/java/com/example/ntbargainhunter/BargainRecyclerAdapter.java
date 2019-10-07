package com.example.ntbargainhunter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class BargainRecyclerAdapter extends RecyclerView.Adapter<BargainRecyclerAdapter.ViewHolder> {

    public List<BargainPost> bargain_list;
    public Context context;

    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;

    public BargainRecyclerAdapter(List<BargainPost> bargain_list){

        this.bargain_list = bargain_list;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bargain_list_item, parent, false);
        context = parent.getContext();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.setIsRecyclable(false);

        final String bargainPostId = bargain_list.get(position).BargainPostId;
        final String currentUserId = firebaseAuth.getCurrentUser().getUid();

        String desc_data = bargain_list.get(position).getDesc();
        holder.setDescText(desc_data);

        String image_url = bargain_list.get(position).getImage_url();
        String thumbUri = bargain_list.get(position).getImage_thumb();
        holder.setBlogImage(image_url, thumbUri);

        String user_id = bargain_list.get(position).getUser_id();
        //User Data will be retrieved here...
        firebaseFirestore.collection("Users").document(user_id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if(task.isSuccessful()){

                    String userName = task.getResult().getString("name");
                    String userImage = task.getResult().getString("image");

                    holder.setUserData(userName, userImage);


                } else {

                    //Firebase Exception

                }

            }
        });

        try {
            long millisecond = bargain_list.get(position).getTimestamp().getTime();
            String dateString = DateFormat.format("MM/dd/yyyy", new Date(millisecond)).toString();
            holder.setTime(dateString);
        } catch (Exception e) {

            Toast.makeText(context, "Exception : " + e.getMessage(), Toast.LENGTH_SHORT).show();

        }

        //Get Likes Count
        firebaseFirestore.collection("Posts/" + bargainPostId + "/Likes").addSnapshotListener( new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                if(!documentSnapshots.isEmpty()){

                    int count = documentSnapshots.size();

                    holder.updateLikesCount(count);


                } else {

                    holder.updateLikesCount(0);

                }

            }
        });
        //Get comments Count
        firebaseFirestore.collection("Posts/" + bargainPostId + "/Comments").addSnapshotListener( new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                if(!documentSnapshots.isEmpty()){

                    int count = documentSnapshots.size();
                    holder.updateCommentCount(count);

                } else {
                    holder.updateCommentCount(0);
                }

            }
        });



        //Get Likes
        firebaseFirestore.collection("Posts/" + bargainPostId + "/Likes").document(currentUserId).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {

                if(documentSnapshot.exists()){

                    holder.bargainLikeBtn.setImageDrawable(context.getDrawable(R.mipmap.action_like_accent));

                } else {

                    holder.bargainLikeBtn.setImageDrawable(context.getDrawable(R.mipmap.action_like_gray));

                }

            }
        });

        //Likes Feature
        holder.bargainLikeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firebaseFirestore.collection("Posts/" + bargainPostId + "/Likes").document(currentUserId).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        if(!task.getResult().exists()){

                            Map<String, Object> likesMap = new HashMap<>();
                            likesMap.put("timestamp", FieldValue.serverTimestamp());

                            firebaseFirestore.collection("Posts/" + bargainPostId + "/Likes").document(currentUserId).set(likesMap);

                        } else {

                            firebaseFirestore.collection("Posts/" + bargainPostId + "/Likes").document(currentUserId).delete();

                        }

                    }
                });
            }
        });

        holder.bargainCommentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent commentIntent = new Intent(context, CommentsActivity.class);
                commentIntent.putExtra("bargain_post_id", bargainPostId);
                context.startActivity(commentIntent);

            }
        });

    }


    @Override
    public int getItemCount() {
        return bargain_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private View mView;

        private TextView descView;
        private ImageView bargainImageView;
        private TextView bargainDate;

        private TextView bargainUserName;
        private CircleImageView bargainUserImage;

        private ImageView bargainLikeBtn;
        private TextView bargainLikeCount;
        private TextView bargainCommentCount;

        private ImageView bargainCommentBtn;


        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            bargainLikeBtn = mView.findViewById(R.id.bargain_like_btn);
            bargainCommentBtn = mView.findViewById(R.id.bargain_comment_icon);

        }

        public void setDescText(String descText){

            descView = mView.findViewById(R.id.bargain_desc);
            descView.setText(descText);

        }

        public void setBlogImage(String downloadUri, String thumbUri){

            bargainImageView = mView.findViewById(R.id.bargain_image);

            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.image_placeholder);

            Glide.with(context).applyDefaultRequestOptions(requestOptions).load(downloadUri).thumbnail(
                    Glide.with(context).load(thumbUri)
            ).into(bargainImageView);

        }

        public void setTime(String date) {

            bargainDate = mView.findViewById(R.id.bargain_date);
            bargainDate.setText(date);

        }

        public void setUserData(String name, String image){

            bargainUserImage = mView.findViewById(R.id.bargain_user_image);
            bargainUserName = mView.findViewById(R.id.bargain_user_name);

            bargainUserName.setText(name);

            RequestOptions placeholderOption = new RequestOptions();
            placeholderOption.placeholder(R.drawable.profile_placeholder);

            Glide.with(context).applyDefaultRequestOptions(placeholderOption).load(image).into(bargainUserImage);

        }



        public void updateCommentCount(int count){

            bargainCommentCount = mView.findViewById(R.id.bargain_comment_count);
            bargainCommentCount.setText(count + " Comments");

        }
        public void updateLikesCount(int count){

            bargainLikeCount = mView.findViewById(R.id.bargain_like_count);
            bargainLikeCount.setText(count + " Likes");

        }

    }

}
