package com.example.malikhimani;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    private TextView textView;
    private PreferenceHandler preferenceHandler;
    private static final String KEY_USER = "com.example.malikhimani.USER_KEY1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        textView = (TextView) findViewById(R.id.textView);
        if (getIntent().getStringExtra(KEY_USER) == null) {
            textView.setText("Welcome User");
        } else {
            textView.setText("Welcome " + getIntent().getStringExtra(KEY_USER));
        }

        preferenceHandler = new PreferenceHandler(getApplicationContext());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout_file, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.logout) {
            preferenceHandler.writeLoginStatus(false);
            startActivity(new Intent(HomeActivity.this, MainActivity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
