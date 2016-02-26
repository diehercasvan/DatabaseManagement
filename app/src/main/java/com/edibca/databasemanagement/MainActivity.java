package com.edibca.databasemanagement;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BL.BusinessLogic;
import DTO.DtoUser;
import adapters.ListAdapter;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private ListAdapter listAdapter;
    private ArrayList<DtoUser>dataList;
    private ListView listView;
    private DtoUser dtoUser;
    private BusinessLogic businessLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Intent intent=new Intent(getApplicationContext(),UserInsert.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        try {
            loadActivity();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void  loadActivity() throws SQLException {

        dataList= new ArrayList<>();

      /*  for(int i=0;i<10;i++){

            DtoUser  dtoUser=new DtoUser();
            dtoUser.setsName("Diego :" + i);
            dtoUser.setsLast_Name("Casallas :" + i);
            dtoUser.setsMail("diehercasvan@gmail.com" + i);
            dtoUser.setiImgUrl(R.mipmap.ic_launcher);

            dataList.add(dtoUser);

        }
        listView=(ListView)findViewById(R.id.listView);
        listAdapter =new ListAdapter(this,dataList);
        listView.setAdapter(listAdapter);*/
        searchUser(0);

    }
    public void  searchUser(int iTypeSearch) throws SQLException {
        dtoUser=new DtoUser();
        businessLogic=new BusinessLogic(this);

        dataList=businessLogic.consultUserBl(dtoUser,iTypeSearch);

        for(int i=0;i<dataList.size();i++){
            Log.w("List", String.valueOf(dataList.get(i).getsName()));
            Log.w("List", String.valueOf(dataList.get(i).getsLast_Name()));
            Log.w("List", String.valueOf(dataList.get(i).getsMail()));
            Log.w("List", String.valueOf(dataList.get(i).getsTelephone()));
            Log.w("List", String.valueOf(dataList.get(i).getsUri()));
        }
        /*dtoUser.setsNewMail("diehercasvan@hotmail.com");
        dtoUser.setsName("Diego Hernando");
        dtoUser.setsLast_Name("Vanegas");
        dtoUser.setsMail("diehercasvan@gmail.com");//Validate

       businessLogic.updateUserBl(dtoUser);*/

/*02-26 13:39:52.378 20687-20687/com.edibca.databasemanagement W/List: diego
02-26 13:39:52.378 20687-20687/com.edibca.databasemanagement W/List: casallas
02-26 13:39:52.378 20687-20687/com.edibca.databasemanagement W/List: diehercasvan@gmail.com
02-26 13:39:52.378 20687-20687/com.edibca.databasemanagement W/List: 3012528242
02-26 13:39:52.378 20687-20687/com.edibca.databasemanagement W/List: content://com.android.providers.media.documents/document/image%3A6681*/


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {




    }

}
