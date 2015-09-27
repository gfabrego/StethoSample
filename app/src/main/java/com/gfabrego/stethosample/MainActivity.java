package com.gfabrego.stethosample;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private DemoHelper mDemoHelper;

    private Button mNetworkButton;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDemoHelper = new DemoHelper();

        // Network
        mNetworkButton = (Button) findViewById(R.id.activity_make_call_button);
        mNetworkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDemoHelper.makeNetworkCall();
            }
        });

        // Preferences
        mTextView = (TextView) findViewById(R.id.activity_text_view);
        String value = mDemoHelper.getPreferenceValue(this);
        if (value.isEmpty()) {
            mDemoHelper.updateSharedPreferences(this);
            value = "Not defined value";
        }
        mTextView.setText(value);

        // SQL
        SQLHelper helper = new SQLHelper(this);
        // Gets the data repository in write mode
        SQLiteDatabase db = helper.getWritableDatabase();
    }
}
