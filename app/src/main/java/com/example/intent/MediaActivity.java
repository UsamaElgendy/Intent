package com.example.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.ImageView;

import java.net.URL;

public class MediaActivity extends AppCompatActivity implements View.OnClickListener {
    private final static int GET_IMAGE_CAPTURE = 1000;
    private ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);

        findViewById(R.id.btnStartCamera).setOnClickListener(this);
        findViewById(R.id.btnOpenURL).setOnClickListener(this);
        findViewById(R.id.btnCapturePic).setOnClickListener(this);
        findViewById(R.id.btnSendText).setOnClickListener(this);

        imgView = findViewById(R.id.imgCapturePic);
    }

    @Override
    public void onClick(View v) {
        int btnClick = v.getId();

    // TODO: Start the camera in photo mode
        if (btnClick == R.id.btnStartCamera) {
            Intent i = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
            if (i.resolveActivity(getPackageManager()) != null) {
                startActivity(i);
            }


        } else if (btnClick == R.id.btnCapturePic) {
            // Take a picture and consume the returned result bitmap
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            // check method onActivityResult
            startActivityForResult(i, GET_IMAGE_CAPTURE);

            if (i.resolveActivity(getPackageManager()) != null) {
                // TODO: start the activity and indicate that we expect a result back

            }
        } else if (btnClick == R.id.btnSendText) {
            final String message = "This is a text message";

            Intent i = new Intent(Intent.ACTION_SENDTO);

            // TODO: Use the setData function to indicate the type of data that will be sent
            // this will help the system figure out what apps to include in the chooser
            i.setData(Uri.parse("sms:01207968016"));
            i.putExtra("sms_body",message);
            if (i.resolveActivity(getPackageManager()) != null) {
                startActivity(i);
            }
        } else if (btnClick == R.id.btnOpenURL) {
            String url = "http://www.google.com";
            // TODO: Parse the URL string using the Uri class
            Uri webpage = Uri.parse(url);

            Intent i = new Intent(Intent.ACTION_VIEW, webpage);

            if (i.resolveActivity(getPackageManager()) != null) {
                startActivity(i);
            }
        }
    }

    // This function will be called when an activity that was started for the purpose
    // of returning a result has some data for our app to process
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_IMAGE_CAPTURE && resultCode == RESULT_OK) {

            // TODO: Retrieve the data from the result intent and look for the bitmap
            Bundle extra = data.getExtras();
            Bitmap bitmap = (Bitmap) extra.get("data");
            imgView.setImageBitmap(bitmap);

        }
    }
}
