package com.example.maciek.projekt.dbaccess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.arch.persistence.room.RoomDatabase;

import com.example.maciek.projekt.Shop;

public class ShopDbAdapter {

    private static final String DEBUG_TAG = "SqliteTodoManager";

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "database.db";
    private static final String DB_SHOPS_TABLE = "shops";

    public static final String KEY_ID = "_id";
    public static final String ID_OPTIONS = "INTEGER PRIMARY KEY AUTOINCREMENT";
    public static final int ID_COLUMN = 0;

    public static final String KEY_NAME = "name";
    public static final String NAME_OPTIONS = "TEXT NOT NULL";
    public static final int NAME_COLUMN = 1;

    public static final String KEY_TYPE = "type";
    public static final String TYPE_OPTIONS = "TEXT NOT NULL";
    public static final int TYPE_COLUMN = 2;

    public static final String KEY_LATITUDE = "latitude";
    public static final String LATITUDE_OPTIONS = "REAL NOT NULL";
    public static final int LATITUDE_COLUMN = 3;

    public static final String KEY_LONGITUDE = "longitude";
    public static final String LONGITUDE_OPTIONS = "REAL NOT NULL";
    public static final int LONGITUDE_COLUMN = 4;


    private static final String DB_CREATE_SHOPS_TABLE =
            "CREATE TABLE " + DB_SHOPS_TABLE + "( " +
                    KEY_ID + " " + ID_OPTIONS + ", " +
                    KEY_NAME + " " + NAME_OPTIONS + ", " +
                    KEY_TYPE + " " + TYPE_OPTIONS + ", " +
                    KEY_LATITUDE + " " + LATITUDE_OPTIONS + ", " +
                    KEY_LONGITUDE + " " + LONGITUDE_OPTIONS +
                    ");";
    private static final String DROP_SHOPS_TABLE =
            "DROP TABLE IF EXISTS " + DB_SHOPS_TABLE;

    private SQLiteDatabase db;
    private Context ctx;
    private DatabaseHelper dbHelper;

    public ShopDbAdapter(Context ctx) {
        this.ctx = ctx;
    }

    public ShopDbAdapter open() {
        dbHelper = new DatabaseHelper(ctx, DB_NAME, null, DB_VERSION);
        try {
            db = dbHelper.getWritableDatabase();
        } catch(SQLException e) {
            db = dbHelper.getReadableDatabase();
        }
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public long insertShop(Shop shop) {
        ContentValues newShopValues = new ContentValues();
        newShopValues.put(KEY_NAME, shop.getName());
        newShopValues.put(KEY_TYPE, shop.getType().toString());
        newShopValues.put(KEY_LATITUDE, shop.getLatitude());
        newShopValues.put(KEY_LONGITUDE, shop.getLongitude());
        return db.insert(DB_SHOPS_TABLE, null, newShopValues);
    }

    public boolean updateShop(Shop shop) {
        long id = shop.getId();
        String name = shop.getName();
        Shop.ShopType type = shop.getType();
        double latitude = shop.getLatitude();
        double longitude = shop.getLongitude();
        return updateShop(id, name, type, latitude, longitude);
    }

    public boolean updateShop(long id, String name, Shop.ShopType type, double latitude, double longitude) {
        String where = KEY_ID + "=" + id;
        String typeString = type.toString();
        ContentValues updatedShopValues = new ContentValues();
        updatedShopValues.put(KEY_NAME, name);
        updatedShopValues.put(KEY_TYPE, typeString);
        updatedShopValues.put(KEY_LATITUDE, latitude);
        updatedShopValues.put(KEY_LONGITUDE, longitude);
        return db.update(DB_SHOPS_TABLE, updatedShopValues, where, null) > 0;
    }

    public boolean deleteShop(long id) {
        String where = KEY_ID + "=" + id;
        return db.delete(DB_SHOPS_TABLE, where, null) > 0;
    }

    public Cursor getAllShops() {
        String[] columns = {KEY_ID, KEY_NAME, KEY_TYPE, KEY_LATITUDE, KEY_LONGITUDE};
        return db.query(DB_SHOPS_TABLE, columns, null, null, null, null, null);
    }

    public Shop getShop(long id) {
        String[] columns = {KEY_ID, KEY_NAME, KEY_TYPE, KEY_LATITUDE, KEY_LONGITUDE};
        String where = KEY_ID + "=" + id;
        Cursor cursor = db.query(DB_SHOPS_TABLE, columns, where, null, null, null, null);
        Shop shop = null;
        if(cursor != null && cursor.moveToFirst()) {
            String name = cursor.getString(NAME_COLUMN);
            String typeString = cursor.getString(TYPE_COLUMN);
            double latitude = cursor.getDouble(LATITUDE_COLUMN);
            double longitude = cursor.getDouble(LONGITUDE_COLUMN);

            shop = new Shop(id, name, Shop.ShopType.valueOf(typeString), latitude, longitude);
        }
        return shop;
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context, String name,
                              SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(DB_CREATE_SHOPS_TABLE);

            Log.d(DEBUG_TAG, "Database creating...");
            Log.d(DEBUG_TAG, "Table " + DB_SHOPS_TABLE + " ver. " + DB_VERSION + " created");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
            sqLiteDatabase.execSQL(DROP_SHOPS_TABLE);

            Log.d(DEBUG_TAG, "Database updating...");
            Log.d(DEBUG_TAG, "Table " + DB_SHOPS_TABLE + " updated from ver." + oldVersion + " to ver." + newVersion);
            Log.d(DEBUG_TAG, "All data is lost.");

            onCreate(sqLiteDatabase);
        }

    }

}
