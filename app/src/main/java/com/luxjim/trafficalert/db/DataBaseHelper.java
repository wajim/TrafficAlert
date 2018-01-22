package com.luxjim.trafficalert.db;

/**
 * Created by Kanku on 02/04/2015.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "routedb";
    private static final int DATABASE_VERSION = 1;

    public static final String ROUTE_TABLE = "route";

    public static final String ID_COLUMN = "id";
    public static final String ORIGINE_COLUMN = "origine";

    public static final String ARRIVEE_COLUMN = "arrivee";


    public static final String CREATE_ROUTE_TABLE = "CREATE TABLE "
            + ROUTE_TABLE + "(" + ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ORIGINE_COLUMN + " TEXT, "

            + ARRIVEE_COLUMN + " TEXT" + ")";


    private static DataBaseHelper instance;

    public static synchronized DataBaseHelper getHelper(Context context) {
        if (instance == null)
            instance = new DataBaseHelper(context);
        return instance;
    }

    private DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ROUTE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
