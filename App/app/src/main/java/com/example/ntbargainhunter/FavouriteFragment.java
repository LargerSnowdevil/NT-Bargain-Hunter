
package com.example.ntbargainhunter;

        import android.os.Bundle;
        import android.support.annotation.NonNull;
        import android.support.v4.app.Fragment;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;

        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class FavouriteFragment extends Fragment {

    private RecyclerView bargain_list_view;
    private List<BargainPost> bargain_list;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    private BargainRecyclerAdapter bargainRecyclerAdapter;

    private String user_id;
    private DocumentSnapshot lastVisible;
    private Boolean isFirstPageFirstLoad = true;
    private String bargain_post_id;

    private int i;

    public FavouriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favourite, container, false);

        bargain_list = new ArrayList<>();
        bargain_list_view = view.findViewById(R.id.favorite_list_view);

        firebaseAuth = FirebaseAuth.getInstance();
        bargainRecyclerAdapter = new BargainRecyclerAdapter(bargain_list);
        bargain_list_view.setLayoutManager(new LinearLayoutManager(container.getContext()));
        bargain_list_view.setAdapter(bargainRecyclerAdapter);
        bargain_list_view.setHasFixedSize(true);
        user_id = firebaseAuth.getCurrentUser().getUid();
        if(firebaseAuth.getCurrentUser() != null) {

            firebaseFirestore = FirebaseFirestore.getInstance();

            bargain_list_view.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    Boolean reachedBottom = !recyclerView.canScrollVertically(1);

                    if(reachedBottom){

                        //loadMorePost();

                    }

                }
            });

            //todo ---------------------------------------------------------------------------------

            final ArrayList<String> favPosts = new ArrayList<>();

            firebaseFirestore.collection("Users/").document(firebaseAuth.getCurrentUser().getUid()).collection("/Favourites").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (DocumentSnapshot document : task.getResult()) {
                            favPosts.add(document.getId());
                        }
                        getFavPosts(favPosts);
                    } else {
                        //todo if the favourites cant be found
                    }
                }
            });

            //todo ---------------------------------------------------------------------------------

            /*Query firstQuery = firebaseFirestore.collection("Posts").orderBy("timestamp", Query.Direction.DESCENDING).limit(3);
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


                                bargainRecyclerAdapter.notifyDataSetChanged();

                            }
                        }

                        isFirstPageFirstLoad = false;

                    }

                }

            });*/

        }

        // Inflate the layout for this fragment
        return view;
    }

    private void getFavPosts(final ArrayList<String> postList) {
        final List<DocumentSnapshot> favPosts = new ArrayList<>();
        i = 0;

        while (i < postList.size()) {
            firebaseFirestore.collection("Posts").document(postList.get(i)).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                    if (task.isSuccessful()) {

                        favPosts.add(task.getResult());

                    }
                    if (favPosts.size() == postList.size()) {

                        displayFavPosts(favPosts);
                    }
                }
            });
            i++;
        }
    }

    private void displayFavPosts(List<DocumentSnapshot> favPosts) {

        if (isFirstPageFirstLoad) {
            lastVisible = favPosts.get(favPosts.size() -1);
            bargain_list.clear();
        }
        i = 0;
        while (i < favPosts.size()) {
            BargainPost post = favPosts.get(i).toObject(BargainPost.class).withId(favPosts.get(i).getId());
            if (isFirstPageFirstLoad) {
                bargain_list.add(post);
            } else {
                bargain_list.add(0, post);
            }
            bargainRecyclerAdapter.notifyDataSetChanged();
            i++;
        }
        isFirstPageFirstLoad = false;
    }

    /*public void loadMorePost(){

        if(firebaseAuth.getCurrentUser() != null) {

            Query nextQuery = firebaseFirestore.collection("Posts")
                    .orderBy("timestamp", Query.Direction.DESCENDING)
                    .startAfter(lastVisible)
                    .limit(3);

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

                                bargainRecyclerAdapter.notifyDataSetChanged();
                            }

                        }
                    }

                }
            });

        }

    }*/

}
