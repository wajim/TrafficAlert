package com.luxjim.trafficalert.db;

/**
 * Created by Kanku on 02/04/2015.
 */

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class RouteDBDAO {

    protected SQLiteDatabase database;
    private DataBaseHelper dbHelper;
    private Context mContext;

    public RouteDBDAO(Context context) {
        this.mContext = context;
        dbHelper = DataBaseHelper.getHelper(mContext);
        open();

    }

    public void open() throws SQLException {
        if(dbHelper == null)
            dbHelper = DataBaseHelper.getHelper(mContext);
        database = dbHelper.getWritableDatabase();
    }

	/*public void close() {
		dbHelper.close();
		database = null;
	}*/
}