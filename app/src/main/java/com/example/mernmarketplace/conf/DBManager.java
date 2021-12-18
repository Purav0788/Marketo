package com.example.mernmarketplace.conf;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.mernmarketplace.models.Product;

import java.util.ArrayList;
import java.util.List;

public class DBManager {

    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String name, String quantity, String price) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.NAME, name);
        contentValue.put(DatabaseHelper.QUANTITY, quantity);
        contentValue.put(DatabaseHelper.PRICE,price);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }


    public List<Product> getProducts(){
       List<Product> offlineData = new ArrayList<Product>();
        String[] columns = new String[] { DatabaseHelper.NAME, DatabaseHelper.QUANTITY,DatabaseHelper.PRICE };
        Cursor  cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                offlineData.add(new Product(cursor.getString(cursor.getColumnIndex("name")),Integer.parseInt(cursor.getString(cursor.getColumnIndex("quantity"))),Double.valueOf(cursor.getString(cursor.getColumnIndex("price")))));


            } while (cursor.moveToNext());
        }
        cursor.close();

        return  offlineData;
    }



    public void delete() {

        database.execSQL("DELETE FROM " +DatabaseHelper.TABLE_NAME + ";" );
//        database.setTransactionSuccessful();
    }
    public void clearTable(){
        database.execSQL("delete from "+ DatabaseHelper.TABLE_NAME);
    }
}
