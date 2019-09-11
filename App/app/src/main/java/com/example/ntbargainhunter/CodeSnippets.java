/*
package com.example.ntbargainhunter;

import android.content.Intent;
import android.view.View;

public class CodeSnippets {

    //will override the action taken when the user presses the back key on their physical device.
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, Destination.class);
        startActivity(intent);
        finish();//if this is included the onBackPressed() on the next page as it will not be able to return here without a manual override.
    }

    //method for changing activity
    public void goToClass(View v) need the view to call with an onClick() {
        Intent i = new Intent(this, Destination.class);
        i.putExtra("exerciseType", "Walking");//will include extra data in the intent that will transfer between pages
        startActivity(i);

    }


}
*/
