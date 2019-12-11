package com.example.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the UI controls
        findViewById(R.id.create_explicit).setOnClickListener(this);
        findViewById(R.id.create_implicit).setOnClickListener(this);
        findViewById(R.id.btnMediaIntents).setOnClickListener(this);
        findViewById(R.id.btnAppIntents).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // Figure out which button was clicked
        int viewClicked = v.getId();

        // TODO : make an explicit intent
        if (viewClicked == R.id.create_explicit) {

            Intent intent = new Intent(this, DestinationActivity.class);
            intent.putExtra("StringData", "this is our string");
            intent.putExtra("IntData", 111);
            startActivity(intent);





            // TODO: Build an implicit intent to handle a type of action
        } else if (viewClicked == R.id.create_implicit) {
            String textMessage = "This is a sample message";
            Intent i = new Intent();
            i.setAction(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_TEXT, textMessage);

            //TODO : use an intent chooser to force a choose dialog
            Intent chooser = Intent.createChooser(i,"Select an app");


            // Verify that the intent will resolve to an activity
            if (i.resolveActivity(getPackageManager()) != null) {
                startActivity(chooser);
            }else {
                Toast.makeText(this, "you can't open any app ", Toast.LENGTH_SHORT).show();
            }
        }



        // Handle button clicks to start the other intent examples
        if (viewClicked == R.id.btnAppIntents) {
            Intent i = new Intent(this, AppsActivity.class);
            startActivity(i);
        } else if (viewClicked == R.id.btnMediaIntents) {
            Intent i = new Intent(this, MediaActivity.class);
            startActivity(i);
        }
    }
}
