package com.example.ntbargainhunter;

import android.support.annotation.NonNull;

import com.google.firebase.firestore.Exclude;

public class BargainPostId {

    @Exclude
    public String BargainPostId;

    public <T extends BargainPostId> T withId(@NonNull final String id) {
        this.BargainPostId = id;
        return (T) this;
    }

}
