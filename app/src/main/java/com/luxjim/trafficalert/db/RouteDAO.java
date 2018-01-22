package com.luxjim.trafficalert.db;


/**
 * Created by Kanku on 02/04/2015.
 */

import java.text.ParseException;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.luxjim.trafficalert.obj.Route;

public class RouteDAO extends RouteDBDAO {

    private static final String WHERE_ID_EQUALS = DataBaseHelper.ID_COLUMN
            + " =?";

    public RouteDAO(Context context) {
        super(context);
    }

    public long save(Route Route) {
        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.ORIGINE_COLUMN, Route.getOrigine());

        values.put(DataBaseHelper.ARRIVEE_COLUMN, Route.getArrivee());


        return database.insert(DataBaseHelper.ROUTE_TABLE, null, values);
    }

    public long update(Route Route) {
        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.ORIGINE_COLUMN, Route.getOrigine());

        values.put(DataBaseHelper.ARRIVEE_COLUMN, Route.getArrivee());


        long result = database.update(DataBaseHelper.ROUTE_TABLE, values,
                WHERE_ID_EQUALS,
                new String[] { String.valueOf(Route.getId()) });
        Log.d("Update Result:", "=" + result);
        return result;

    }

    public int delete(Route Route) {
        return database.delete(DataBaseHelper.ROUTE_TABLE, WHERE_ID_EQUALS,
                new String[] { Route.getId() + "" });
    }

    //USING query() method
    public ArrayList<Route> getRoutes() {
        ArrayList<Route> Routes = new ArrayList<Route>();

        Cursor cursor = database.query(DataBaseHelper.ROUTE_TABLE,
                new String[] { DataBaseHelper.ID_COLUMN,
                        DataBaseHelper.ORIGINE_COLUMN,

                        DataBaseHelper.ARRIVEE_COLUMN }, null, null, null,

                null, null);

        while (cursor.moveToNext()) {
            Route Route = new Route();
            Route.setId(cursor.getInt(0));
            Route.setOrigine(cursor.getString(1));

            Route.setArrivee(cursor.getString(2));


            Routes.add(Route);
        }
        return Routes;
    }

    //Retrieves a single Route record with the given id
    public Route getRoute(long id) {
        Route Route = null;

        String sql = "SELECT * FROM " + DataBaseHelper.ROUTE_TABLE
                + " WHERE " + DataBaseHelper.ID_COLUMN + " = ?";

        Cursor cursor = database.rawQuery(sql, new String[] { id + "" });

        if (cursor.moveToNext()) {
            Route = new Route();
            Route.setId(cursor.getInt(0));
            Route.setOrigine(cursor.getString(1));

            Route.setArrivee(cursor.getString(2));


        }
        return Route;
    }
}