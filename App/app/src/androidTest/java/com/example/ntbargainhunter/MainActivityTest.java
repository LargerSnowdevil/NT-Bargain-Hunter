package com.example.ntbargainhunter;


import android.support.test.filters.MediumTest;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

@MediumTest

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

   @Rule
   // public MainActivityTest rule  = new MainActivityTest();
   public PostBargainPage rule = new PostBargainPage();

    private android.content.Context Context;
    @Rule
    public PostUserComment usercomment = new PostUserComment(Context);


    //ensures list view is present
    @Test
    public void checkEnsureListViewIsPresent() throws Exception {
        //  MainActivity activity = rule.getActivity();
        View viewById = rule.findViewById(R.id.bargain_list_view);
        assertThat(viewById,notNullValue());
        assertThat(viewById, instanceOf(ListView.class));
        ListView listView = (ListView) viewById;
        ListAdapter adapter = listView.getAdapter();
        assertThat(adapter, instanceOf(ArrayAdapter.class));
        assertThat(adapter.getCount(), greaterThan(5));

    }
}
