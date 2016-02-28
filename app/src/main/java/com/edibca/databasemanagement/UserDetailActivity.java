package com.edibca.databasemanagement;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import Class.*;
import com.edibca.databasemanagement.*;

import java.sql.SQLException;
import java.util.ArrayList;

import BL.BusinessLogic;
import DTO.DtoUser;
import adapters.ListAdapter;

public class UserDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView[] imageViews;
    private TextView[] textViews;
    private Bundle bundle;
    private ArrayList<DtoUser> dataList;
    private ListView listView;
    private DtoUser dtoUser;
    private String sUserUpdate;
    private BusinessLogic businessLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_detail);
        try {
            loadView();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadView() throws SQLException {

        imageViews = new ImageView[3];
        textViews = new TextView[3];

        textViews[0] = (TextView) findViewById(R.id.telephoneUser);
        textViews[1] = (TextView) findViewById(R.id.mailUser);
        textViews[2] = (TextView) findViewById(R.id.textName);

        imageViews[0] = (ImageView) findViewById(R.id.imgTepephone);
        imageViews[1] = (ImageView) findViewById(R.id.imgMail);
        imageViews[2] = (ImageView) findViewById(R.id.imgUser);

        for (int i = 0; i < imageViews.length - 1; i++) {
            imageViews[i].setOnClickListener(this);
        }
        //bundle = getIntent().getExtras();

        dtoUser = new DtoUser();
        businessLogic = new BusinessLogic(this);
        dataList = new ArrayList<>();

        listView = (ListView) findViewById(R.id.listView);
        dtoUser.setsMail(General.EMAIL_USER);
        dataList = businessLogic.consultUserBl(dtoUser, 2);

        for (int i = 0; i < dataList.size(); i++) {

            textViews[0].setText(dataList.get(i).getsTelephone());
            textViews[1].setText(dataList.get(i).getsMail());
            textViews[2].setText(dataList.get(i).getsName() + "  " + dataList.get(i).getsLast_Name());
            imageViews[2].setImageURI(Uri.parse(dataList.get(i).getsUri()));

            sUserUpdate = dataList.get(i).getsName()+","+dataList.get(i).getsLast_Name()+","+dataList.get(i).getsMail()+","+dataList.get(i).getsTelephone()+","+dataList.get(i).getsUri();
        }

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        int Type = 0;
        switch (v.getId()) {
            case R.id.imgTepephone:
                intent = new Intent(Intent.ACTION_CALL);
                Type = 1;
                break;
            case R.id.imgMail:
                intent = new Intent(Intent.ACTION_SEND);
                Type = 0;
                break;
        }

        createInten(intent, Type);

    }

    public void createInten(Intent intent, int iType) {
        switch (iType) {
            case 0:
                String[] TO = {textViews[1].getText().toString()};
                String[] CC = {""};

                intent = new Intent(Intent.ACTION_SEND);
                intent.setData(Uri.parse("mailto:"));
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, TO);
                intent.putExtra(Intent.EXTRA_CC, CC);
                intent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
                intent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");
                break;
            case 1:

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
                break;
        }

        try {
            startActivity(Intent.createChooser(intent, "Send mail..."));
            // finish();
            // Log.i("Finished sending email...", " ");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete:
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("User Delete");
                alert.setMessage("To delete the user");
                alert.setCancelable(false);
                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            deleteUser();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                });
                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Cancel User Deleted", Toast.LENGTH_LONG).show();
                    }
                });
                alert.show();
                return true;


            case R.id.update:
                Intent intent = new Intent(this, UserUpdate.class);
                intent.putExtra("DataUser", sUserUpdate);
                startActivity(intent);
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    public void deleteUser() throws SQLException {
        dtoUser = new DtoUser();
        dtoUser.setsMail(textViews[1].getText().toString());
        businessLogic = new BusinessLogic(this);
        if (businessLogic.deleteUserBl(dtoUser) == -1) {
            Toast.makeText(this, "User Deleted", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MainActivity.class);
            finish();
            startActivity(intent);
        } else {
            Toast.makeText(this, "User not Deleted", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MainActivity.class);
            finish();
            startActivity(intent);
        }
    }

}
