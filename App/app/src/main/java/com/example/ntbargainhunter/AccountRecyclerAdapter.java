package com.example.ntbargainhunter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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

import static com.facebook.login.widget.ProfilePictureView.TAG;

public class AccountRecyclerAdapter extends RecyclerView.Adapter<AccountRecyclerAdapter.ViewHolder> {

    public List<BargainPost> bargain_list;
    public Context context;

    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;

    public AccountRecyclerAdapter(List<BargainPost> bargain_list){

        this.bargain_list = bargain_list;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.account_list, parent, false);
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
        final String title_data = bargain_list.get(position).getTitle();
        holder.setTitleText(title_data);
        final String image_url = bargain_list.get(position).getImage_url();
        final String thumbUri = bargain_list.get(position).getImage_thumb();
        holder.setbargainImage(image_url, thumbUri);
        long millisecond = bargain_list.get(position).getTimestamp().getTime();
        final String dateString = DateFormat.format("dd/mm/yyyy", new Date(millisecond)).toString();
        holder.setTime(dateString);
        final String user_id = bargain_list.get(position).getUser_id();
        //User Data will be retrieved here...

        //Account DeleteFeature
        holder.deletePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firebaseFirestore.collection("Posts").document(bargainPostId)
                        .delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                Log.d(TAG, "DocumentSnapshot successfully deleted!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error deleting document", e);
                            }
                        });


            }

        });


    }


    @Override
    public int getItemCount() {
        return bargain_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private View mView;

        private TextView titleView;
        private ImageView bargainImageView;
        private TextView bargainDate;
        private ImageView deletePost;


        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            deletePost = mView.findViewById(R.id.delete_post);
        }


        public void setTitleText(String titleText){

            titleView = mView.findViewById(R.id.bargain_title);
            titleView.setText(titleText);

        }


        public void setbargainImage(String downloadUri, String thumbUri){

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



    }

}
