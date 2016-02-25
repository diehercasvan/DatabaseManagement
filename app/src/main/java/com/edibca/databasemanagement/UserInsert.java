package com.edibca.databasemanagement;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.sql.SQLException;

import BL.BusinessLogic;
import Class.*;
import DTO.DtoUser;

public class UserInsert extends AppCompatActivity implements View.OnClickListener {
    private ImageView imageView;
    private static final int SELECT_PICTURE = 1;
    private Uri selectedImagePath;
    private EditText[] editTexts;
    private Button button;
    private DtoUser dtoUser;
    private BusinessLogic businessLogic;
    private String sUriImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_insert);
        loadView();
    }

    public void loadView() {
        imageView = (ImageView) findViewById(R.id.btnGallery);
        imageView.setOnClickListener(this);

        editTexts = new EditText[4];
        editTexts[0] = (EditText) findViewById(R.id.textName);
        editTexts[1] = (EditText) findViewById(R.id.textLastName);
        editTexts[2] = (EditText) findViewById(R.id.textEmail);
        editTexts[3] = (EditText) findViewById(R.id.textTelephone);

        button = (Button) findViewById(R.id.btnSave);
        button.setOnClickListener(this);
        selectedImagePath=null;
        sUriImage="android.resource://com.edibca.databasemanagement/"+R.drawable.ic_gallery;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnSave:
                if (validateBoxText()) {

                    try {
                        insertUser();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }


                } else {

                    Toast.makeText(this,getResources().getString(R.string.errorValidateTextBox),Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btnGallery:
                selectPicture();
                break;
        }


    }
    public void insertUser() throws SQLException {
        businessLogic=new BusinessLogic(this);
        dtoUser=new DtoUser();
        dtoUser.setsName(editTexts[0].getText().toString());
        dtoUser.setsLast_Name(editTexts[1].getText().toString());
        dtoUser.setsMail(editTexts[2].getText().toString());
        dtoUser.setsTelephone(editTexts[3].getText().toString());
        dtoUser.setsUri(sUriImage);


      if(businessLogic.insertUserBl(dtoUser)==-1){
            Toast.makeText(this,R.string.errorInsert,Toast.LENGTH_LONG).show();
      }
        else{
          Toast.makeText(this,R.string.userInsert,Toast.LENGTH_LONG).show();
          cleaningTextBox();
      }


    }
    public void  cleaningTextBox(){
        editTexts[0].setText("");
        editTexts[1].setText("");
        editTexts[2].setText("");
        editTexts[3].setText("");
        imageView.setImageResource(R.drawable.ic_gallery);
    }

    public void selectPicture() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }

    public boolean validateBoxText() {

        boolean bValidateBoxText = false;

        if (General.validateEmpty(editTexts)) {
            bValidateBoxText = true;
        }

        return bValidateBoxText;

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Uri selectedImageUri=null;

        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                 selectedImageUri = data.getData();
                selectedImagePath = selectedImageUri;
                sUriImage=selectedImagePath.toString();
            }
            else{
                selectedImageUri=Uri.parse("android.resource://com.edibca.databasemanagement/"+R.drawable.ic_gallery);
                selectedImagePath=selectedImageUri;

            }
        }
        //Toast.makeText(this, " Uri Image:" + selectedImagePath, Toast.LENGTH_LONG).show();
        imageView.setImageURI(selectedImagePath);



    }
}
