package com.example.ntbargainhunter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import static com.facebook.login.widget.ProfilePictureView.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {

    private RecyclerView account_list_view;
    private List<BargainPost> bargain_list;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    private AccountRecyclerAdapter accountRecyclerAdapter;
    private ImageView deletePost;
    private String current_user_id;
    private DocumentSnapshot lastVisible;
    private Boolean isFirstPageFirstLoad = true;

    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_account, container, false);

        bargain_list = new ArrayList<>();
        account_list_view = view.findViewById(R.id.account_list_view);

        firebaseAuth = FirebaseAuth.getInstance();

        accountRecyclerAdapter = new AccountRecyclerAdapter(bargain_list);
        account_list_view.setLayoutManager(new LinearLayoutManager(container.getContext()));
        account_list_view.setAdapter(accountRecyclerAdapter);
        account_list_view.setHasFixedSize(true);

        current_user_id = firebaseAuth.getCurrentUser().getUid();
        if(firebaseAuth.getCurrentUser() != null) {

            firebaseFirestore = FirebaseFirestore.getInstance();

            account_list_view.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    Boolean reachedBottom = !recyclerView.canScrollVertically(1);

                    if(reachedBottom){

                        loadMorePost();

                    }

                }
            });

            Query firstQuery = firebaseFirestore.collection("Posts").whereEqualTo("user_id", current_user_id).limit(3);
            firstQuery.addSnapshotListener(getActivity(), new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                    if (!documentSnapshots.isEmpty()) {

                        if (isFirstPageFirstLoad) {

                            lastVisible = documentSnapshots.getDocuments().get(documentSnapshots.size() - 1);
                            bargain_list.clear();

                        }

                        for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {

                            if (doc.getType() == DocumentChange.Type.ADDED) {

                                String bargainPostId = doc.getDocument().getId();
                                BargainPost bargainPost = doc.getDocument().toObject(BargainPost.class).withId(bargainPostId);

                                if (isFirstPageFirstLoad) {

                                    bargain_list.add(bargainPost);

                                } else {

                                    bargain_list.add(0, bargainPost);

                                }


                                accountRecyclerAdapter.notifyDataSetChanged();

                            }
                        }

                        isFirstPageFirstLoad = false;

                    }

                }

            });

        }

        // Inflate the layout for this fragment
        return view;
    }

    public void refreshBargain(){
        accountRecyclerAdapter.notifyDataSetChanged();

    }


    interface RefreshFragment{
        public void mainAct();


    }





    public void loadMorePost(){

        if(firebaseAuth.getCurrentUser() != null) {

            Query nextQuery = firebaseFirestore.collection("Posts").whereEqualTo("user_id", current_user_id)
                    .startAfter(lastVisible).limit(3);

            nextQuery.addSnapshotListener(getActivity(), new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                    if (!documentSnapshots.isEmpty()) {

                        lastVisible = documentSnapshots.getDocuments().get(documentSnapshots.size() - 1);
                        for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {

                            if (doc.getType() == DocumentChange.Type.ADDED) {

                                String bargainPostId = doc.getDocument().getId();
                                BargainPost bargainPost = doc.getDocument().toObject(BargainPost.class).withId(bargainPostId);
                                bargain_list.add(bargainPost);

                                accountRecyclerAdapter.notifyDataSetChanged();
                            }

                        }
                    }

                }
            });

        }

    }

}
