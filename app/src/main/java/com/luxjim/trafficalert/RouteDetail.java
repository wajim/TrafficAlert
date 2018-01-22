package com.luxjim.trafficalert;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Kanku on 21/07/2015.
 */
public class RouteDetail extends Activity {

    private Bundle bundle;
    private Intent intent;
    private Calendar cal;
    private TextView a, d, e, f;
    private EditText b,c;
    private String depart,destination,distance,duration;
    String startaddress, endaddress;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.route_detail);
        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/Roboto-Regular.ttf"); // font from assets: "assets/fonts/Roboto-Regular.ttf
        Thread myThread = null;

        Runnable myRunnableThread = new CountDownRunner();
        myThread= new Thread(myRunnableThread);
        myThread.start();

        a = (TextView) findViewById(R.id.tv_12); //date
        b = (EditText) findViewById(R.id.tv_14); //depart
        c = (EditText) findViewById(R.id.tv_16); //destination
        d = (TextView)findViewById(R.id.tv_23); //distance
        e = (TextView)findViewById(R.id.tv_20); //duration
        f = (TextView)findViewById(R.id.tv_22); //duration traffic

        populateViews();
    }

    @Override
    public void onResume() {

        super.onResume();

    }

    @Override
    public void onPause() {

        super.onPause();
    }

    public void onDestroy () {

        super.onDestroy();
    }

    private void populateViews() {

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            depart = bundle.getString("startaddress");
            destination = bundle.getString("endaddress");
            distance = bundle.getString("distance");
            duration = bundle.getString("duration");
        }
        //Date and time...
        //cal = Calendar.getInstance();
        //SimpleDateFormat df = new SimpleDateFormat("dd-MM-yy HH:mm:ss");
        //String formattedDate = df.format(cal.getTime());
        //a.setText(formattedDate);
        showDate();

        b.setText(depart);
        c.setText(destination);
        d.setText(distance);
        e.setText(duration);
    }

    public void showDate() {
        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    cal = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yy HH:mm:ss");
                    String formattedDate = df.format(cal.getTime());
                    a.setText(formattedDate);
                } catch (Exception e) {
                }
            }
        });
    }

    class CountDownRunner implements Runnable{
        // @Override
        public void run() {
            while(!Thread.currentThread().isInterrupted()){
                try {
                    showDate();
                    Thread.sleep(1000); // Pause of 1 Second
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }catch(Exception e){
                }
            }
        }
    }

    public void showmap(View v) {
        Intent i_showmap=new Intent(RouteDetail.this,MapActivity.class);

        //Create a bundle object
        Bundle c = new Bundle();

        //Inserts a String value into the mapping of this Bundle
        c.putString("startaddress", b.getText().toString());
        //c.putString("endaddress", endaddress);

        //Add the bundle to the intent.
        i_showmap.putExtras(c);

        startActivity(i_showmap);

    }

    public void savedroutes(View v) {
        Intent i_savedroutes=new Intent(RouteDetail.this,MainActivity.class);
        startActivity(i_savedroutes);

    }
}




