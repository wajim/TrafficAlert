package com.luxjim.trafficalert;

/**
 * Created by Jimny on 20/01/2018.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class NoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/Roboto-Regular.ttf"); // font from assets: "assets/fonts/Roboto-Regular.ttf
    }

    public void mapcheck(View v) {
        Intent i_mapcheck=new Intent(getApplicationContext() ,RouteList.class);
        startActivity(i_mapcheck);

    }

    public void addroute(View v) {
        Intent i_addroute=new Intent(getApplicationContext() ,RegisterActivity.class);
        startActivity(i_addroute);

    }
}

