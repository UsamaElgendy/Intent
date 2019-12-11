package com.example.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DestinationActivity extends AppCompatActivity {

    // TODO : make filter intent in your manifest
    // by this step we can get some data shared from the browser
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);

        TextView values = findViewById(R.id.txtValues);
        Intent intent = getIntent();
        if (intent != null) {
            String s = intent.getStringExtra("StringData");
            int i = intent.getIntExtra("IntData", 0);

            String data = s + "\n" + i;
            values.setText(data);
        }
        // TODO: extract any data passed by the caller

    }
}
