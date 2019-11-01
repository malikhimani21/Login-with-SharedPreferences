package com.example.malikhimani;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText username, password;
    private Button button;
    private PreferenceHandler preferenceHandler;
    private static final String KEY_USER = "com.example.malikhimani.USER_KEY1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        button = (Button) findViewById(R.id.login);

        preferenceHandler = new PreferenceHandler(getApplicationContext());

        if (preferenceHandler.readLoginStatus()) {
            startActivity(new Intent(this, HomeActivity.class));
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String USER = username.getText().toString().trim();
                String PASS = password.getText().toString().trim();

                if(USER.isEmpty()){
                    username.setError("Username required");
                    username.requestFocus();
                    return;
                }

                if(PASS.isEmpty()){
                    password.setError("Password required");
                    password.requestFocus();
                    return;
                }

                if (USER.equals(getResources().getString(R.string.user)) && PASS.equals(getResources().getString(R.string.pass))) {

                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    intent.putExtra(KEY_USER,USER);
                    startActivity(intent);
                    // startActivity(new Intent(MainActivity.this, HomeActivity.class));
                    preferenceHandler.writeLoginStatus(true);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Error occurred", Toast.LENGTH_SHORT).show();
                    username.setText("");
                    password.setText("");
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
