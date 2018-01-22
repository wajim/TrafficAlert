package com.luxjim.trafficalert;

import android.app.Activity;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;
import com.luxjim.trafficalert.adapter.RouteListAdapter;
import com.luxjim.trafficalert.db.RouteDAO;
import com.luxjim.trafficalert.obj.Route;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class MainActivity extends Activity implements OnItemClickListener,
        OnItemLongClickListener {

    public static final String ARG_ITEM_ID = "route_list";
    private Context context;
    Activity activity;
    ListView routeListView;
    ArrayList<Route> routes;
    String startaddress, endaddress;

    RouteListAdapter routeListAdapter;
    RouteDAO routeDAO;

    private GetRouteTask task;
    private FloatingActionButton myFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/Roboto-Regular.ttf"); // font from assets: "assets/fonts/Roboto-Regular.ttf
        routeListView = (ListView)findViewById(R.id.list_route);

        routeListView.setOnItemClickListener(this);
        routeListView.setOnItemLongClickListener(this);

        routeDAO = new RouteDAO(this);
        task = new GetRouteTask(this);
        task.execute((Void) null);

        myFab = findViewById(R.id.floatingActionButton);
        myFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Click action
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> list, View arg1, int position,
                            long arg3) {
        Route route = (Route) list.getItemAtPosition(position);
        final String startaddress = route.getOrigine().toString();
        final String endaddress = route.getArrivee().toString();
        //Toast.makeText(getActivity(), route + "selected",
        // Toast.LENGTH_LONG).show();

        AlertDialog.Builder builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Light_Dialog_Alert);
        builder.setMessage("Do you want to start alerting with this route ?");
        builder.setCancelable(false);
        final AlertDialog.Builder builder1 = builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MainActivity
                        .this, RouteDetail.class);
                //Create a bundle object
                Bundle b = new Bundle();

                //Inserts a String value into the mapping of this Bundle
                b.putString("startaddress", startaddress);
                b.putString("endaddress", endaddress);

                //Add the bundle to the intent.
                intent.putExtras(b);

                //startActivity(intent, 0);
                startActivityForResult(intent, 0);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view,
                                   int position, long arg3) {
        // Store selected item in global variable
        final Route route = (Route) parent.getItemAtPosition(position);
        startaddress = route.getOrigine();
        endaddress = route.getArrivee();

        AlertDialog.Builder builderb = new AlertDialog.Builder(this,android.R.style.Theme_Material_Light_Dialog_Alert);
        builderb.setMessage("Do you want to remove this route :" + " "
                + startaddress + "-" + endaddress + "?");
        builderb.setCancelable(false);
        builderb.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Use AsyncTask to delete from database
                routeDAO.delete(route);
                routeListAdapter.remove(route);
                routeListAdapter.notifyDataSetChanged();
                //return true;
                Toast.makeText(MainActivity.this,
                        route + " has been removed.",
                        Toast.LENGTH_SHORT).show();

            }
        });
        builderb.setNegativeButton("No", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builderb.show();
        return true;

    }

    public class GetRouteTask extends AsyncTask<Void, Void, ArrayList<Route>> {

        private final WeakReference<Activity> activityWeakRef;

        public GetRouteTask(Activity context) {
            this.activityWeakRef = new WeakReference<Activity>(context);
        }

        @Override
        protected ArrayList<Route> doInBackground(Void... arg0) {
            ArrayList<Route> routeList = routeDAO.getRoutes();
            return routeList;
        }

        @Override
        protected void onPostExecute(ArrayList<Route> routeList) {
            if (activityWeakRef.get() != null
                    && !activityWeakRef.get().isFinishing()) {
                Log.d("routes", routeList.toString());
                routes = routeList;
                if (routeList != null) {
                    if (routeList.size() != 0) {
                        routeListAdapter = new RouteListAdapter(getApplicationContext(),
                                routeList);
                        routeListView.setAdapter(routeListAdapter);
                    } else {
                        Toast.makeText(getApplicationContext(), "No Route Recorded",
                                Toast.LENGTH_LONG).show();
                    }
                }

            }
        }
    }
}
