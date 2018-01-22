package com.luxjim.trafficalert.adapter;

/**
 * Created by Kanku on 02/04/2015.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.luxjim.trafficalert.R;
import com.luxjim.trafficalert.obj.Route;

import java.util.List;


public class RouteListAdapter extends ArrayAdapter<Route> {

    private Context context;
    List<Route> routes;

    public RouteListAdapter(Context context, List<Route> routes) {
        super(context, R.layout.route_list_item, routes);
        this.context = context;
        this.routes = routes;
    }

    private class ViewHolder {
        TextView routeIdTxt;
        TextView origineTxt;
        TextView arriveeTxt;

    }

    @Override
    public int getCount() {
        return routes.size();
    }

    @Override
    public Route getItem(int position) {
        return routes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.route_list_item, null);
            //convertView.setBackgroundColor((position & 1) == 1 ? Color.GRAY : Color.LTGRAY);

            holder = new ViewHolder();

            holder.routeIdTxt = (TextView) convertView
                    .findViewById(R.id.txt_route_id);
            holder.origineTxt = (TextView) convertView
                    .findViewById(R.id.txt_origine);

            holder.arriveeTxt = (TextView) convertView
                    .findViewById(R.id.txt_arrivee);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Route route = (Route) getItem(position);
        holder.routeIdTxt.setText(route.getId() + "");
        holder.origineTxt.setText(route.getOrigine());

        holder.arriveeTxt.setText(route.getArrivee());
        //check for odd or even to set alternate colors to the row background
        //if(position % 2 == 0){
        //   convertView.setBackgroundColor(Color.rgb(153, 199, 255));
        // }
        // else {
        //  convertView.setBackgroundColor(Color.rgb(51, 143, 255));
        // }
        return convertView;
    }

    @Override
    public void add(Route route) {
        routes.add(route);
        notifyDataSetChanged();
        super.add(route);
    }

    @Override
    public void remove(Route route) {
        routes.remove(route);
        notifyDataSetChanged();
        super.remove(route);
    }
}




