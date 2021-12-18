package com.example.mernmarketplace.conf;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.util.Base64;
import android.util.Log;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.Context.MODE_PRIVATE;

public class AppUtils {
    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static void showCancelAlert(final Context context, String message) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setMessage(message);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        dialog.dismiss();
                    }
                });

//        builder1.setNegativeButton(
//                "Cancel",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        dialog.cancel();
//                    }
//                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
    public static String encodeToBase64(Bitmap image) {
        Bitmap myImage = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        myImage.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

        Log.d("Image Log:", imageEncoded);
        return imageEncoded;
    }

    public static Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory
                .decodeByteArray(decodedByte, 0, decodedByte.length);
    }
    public static void setUserIDSharedPreference(Context context, String key, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(AppConstants.userID, MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.apply();
    }
    public static String getUserNameDSharedPreference(Context context, String key) {

        SharedPreferences editor = context.getSharedPreferences(AppConstants.userName, MODE_PRIVATE);
        return editor.getString(key, null);
    }
    public static void setUserEmailSharedPreference(Context context, String key, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(AppConstants.userEmail, MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.apply();
    }
    public static String getbidIdSharedPreference(Context context, String key) {

        SharedPreferences editor = context.getSharedPreferences(AppConstants.bidID, MODE_PRIVATE);
        return editor.getString(key, null);
    }
    public static void setbidSharedPreference(Context context, String key, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(AppConstants.bidID, MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.apply();
    }
    public static String getUserEmailDSharedPreference(Context context, String key) {

        SharedPreferences editor = context.getSharedPreferences(AppConstants.userEmail, MODE_PRIVATE);
        return editor.getString(key, null);
    }
    public static void setTotalSP(Context context, String key, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(AppConstants.total, MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.apply();
    }
    public static String getTotalSP(Context context, String key) {

        SharedPreferences editor = context.getSharedPreferences(AppConstants.total, MODE_PRIVATE);
        return editor.getString(key, null);
    }
    public static void removeTotal(Context context, String key) {

        SharedPreferences.Editor editor = context.getSharedPreferences(AppConstants.total, MODE_PRIVATE).edit();
        editor.remove(key);
        editor.commit();
    }
    public static void setUserNameSharedPreference(Context context, String key, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(AppConstants.userName, MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.apply();
    }
    public static String getUserIDSharedPreference(Context context, String key) {

        SharedPreferences editor = context.getSharedPreferences(AppConstants.userID, MODE_PRIVATE);
        return editor.getString(key, null);
    }
    public static void setUserTokenSharedPreference(Context context, String key, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(AppConstants.token, MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.apply();
    }
    public static String getUserTokenSharedPreference(Context context, String key) {

        SharedPreferences editor = context.getSharedPreferences(AppConstants.token, MODE_PRIVATE);
        return editor.getString(key, null);
    }
    public static void setShopIdSharedPreference(Context context, String key, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(AppConstants.shopID, MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.apply();
    }
    public static String getShopIdSharedPreference(Context context, String key) {

        SharedPreferences editor = context.getSharedPreferences(AppConstants.shopID, MODE_PRIVATE);
        return editor.getString(key, null);
    }
    public static void removeBackupSharedPreference(Context context, String key) {

        SharedPreferences.Editor editor = context.getSharedPreferences(AppConstants.userID, MODE_PRIVATE).edit();
        editor.remove(key);
        editor.commit();
    }

}
