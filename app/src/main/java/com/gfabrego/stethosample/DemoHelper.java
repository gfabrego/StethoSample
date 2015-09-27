package com.gfabrego.stethosample;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import com.facebook.stetho.okhttp.StethoInterceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Helper class for the demo. Don't use anything of it in a real project
 */
public class DemoHelper {
    private static final String TAG = "DemoHelper";
    private static final String CALL_URL = "https://api.github.com/users/gfabrego";
    private static final String PREFERENCES_NAME = "preferences";
    private static final String PREFERENCE_KEY_NAME = "preferenceName";

    /**
     * Make a network request
     */
    public void makeNetworkCall() {
        new MakeCallTask().execute();
    }

    /**
     * Save dummy values on shared preferences
     * @param context Android context
     */
    public void updateSharedPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PREFERENCE_KEY_NAME, "Test");
        editor.commit();
    }

    /**
     * Retrieve preferences
     * @return
     */
    public String getPreferenceValue(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        String value = sharedPreferences.getString(PREFERENCE_KEY_NAME, "");
        return value;
    }

    /**
     * Request task
     */
    private class MakeCallTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            OkHttpClient client = new OkHttpClient();

            client.networkInterceptors().add(new StethoInterceptor());
            Request request = new Request.Builder().url(CALL_URL).build();
            Response response = null;
            try {
                response = client.newCall(request).execute();
                Log.d(TAG, response.body().string());
            } catch (IOException e) {
                Log.e(TAG, "IO exception");
            }

            return "";
        }
    }
}
