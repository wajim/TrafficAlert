package com.luxjim.trafficalert;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.os.AsyncTask;

import com.luxjim.trafficalert.db.RouteDAO;
import com.luxjim.trafficalert.obj.Route;

import java.lang.ref.WeakReference;


public class RegisterActivity extends Activity {

    // UI references
    private EditText departEtxt, pointbEtxt;

    Route route = null;
    private RouteDAO routeDAO;
    private AddRouteTask task;

    public static final String ARG_ITEM_ID = "register_activity";

    EditText start,dest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/Roboto-Regular.ttf"); // font from assets: "assets/fonts/Roboto-Regular.ttf
        //Toast.makeText(getApplicationContext(), "Activity created",Toast.LENGTH_LONG).show();
        routeDAO = new RouteDAO(this);
    }

    public void instructions(View v) {
        Intent i_back=new Intent(getApplicationContext(), NoteActivity.class);
        startActivity(i_back);

    }

    public void storeInDB(View v) {
        departEtxt=(EditText) this.findViewById(R.id.editText1);
        pointbEtxt=(EditText) this.findViewById(R.id.editText2);
        route = new Route();
        route.setOrigine(departEtxt.getText().toString());
        route.setArrivee(pointbEtxt.getText().toString());

        task = new AddRouteTask(this);
        task.execute((Void) null);


    }

    public void showDB(View v) {
        Intent i_showDB=new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i_showDB);

    }

    public class AddRouteTask extends AsyncTask<Void, Void, Long> {

        private final WeakReference<Activity> activityWeakRef;

        public AddRouteTask(Activity context) {
            this.activityWeakRef = new WeakReference<Activity>(context);
        }

        @Override
        protected Long doInBackground(Void... arg0) {
            long result = routeDAO.save(route);
            return result;
        }

        @Override
        protected void onPostExecute(Long result) {
            if (activityWeakRef.get() != null
                    && !activityWeakRef.get().isFinishing()) {
                if (result != -1)
                    Toast.makeText(activityWeakRef.get(), "Route Saved",
                            Toast.LENGTH_LONG).show();
            }
        }



    }
}