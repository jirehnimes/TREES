package com.royce.sample3;


import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by johnroycepunay on 9/18/2016.
 */

public class Cover extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public void onBackPressed() {
        this.getParent().onBackPressed();
    }
}