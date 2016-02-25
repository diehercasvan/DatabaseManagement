package com.edibca.databasemanagement;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.edibca.databasemanagement.*;

public class UserDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView[] imageViews;
    private TextView[] textViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_detail);
        loadView();
    }

    public void loadView() {

        imageViews = new ImageView[2];
        textViews = new TextView[2];

        textViews[0] = (TextView) findViewById(R.id.telephoneUser);
        textViews[1] = (TextView) findViewById(R.id.mailUser);

        imageViews[0] = (ImageView) findViewById(R.id.imgTepephone);
        imageViews[1] = (ImageView) findViewById(R.id.imgMail);

        for (int i = 0; i < imageViews.length; i++) {
            imageViews[i].setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View v) {
        String[] TO = {textViews[1].getText().toString()};
        String[] CC = {textViews[1].getText().toString()};
        /*Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + textViews[0].getText().toString()));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(intent);*/
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL,TO);
        intent.putExtra(Intent.EXTRA_CC, CC);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
        intent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");
        try {
            startActivity(Intent.createChooser(intent, "Send mail..."));
            finish();
           // Log.i("Finished sending email...", " ");
        }
        catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }

    }

}
