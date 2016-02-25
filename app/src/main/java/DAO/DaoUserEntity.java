package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;

import DTO.DtoUser;
import Class.*;
import Interfaces.IntUser;

/**
 * Created by DIEGO CASALLAS on 22/02/2016.
 */
public class DaoUserEntity implements IntUser {

    private SQLiteDatabase database;
    private ContentValues contentValues;

    public DaoUserEntity(SQLiteDatabase db) {
        this.database = db;
        this.contentValues = null;
    }

    @Override
    public int insertUser(DtoUser dtoUser) throws SQLException {

        contentValues = new ContentValues();
        contentValues.put(ShemaDataBase.USER_NAME, dtoUser.getsName());
        contentValues.put(ShemaDataBase.USER_SURNAME, dtoUser.getsLast_Name());
        contentValues.put(ShemaDataBase.USER_EMAIL, dtoUser.getsMail());
        contentValues.put(ShemaDataBase.USER_TELEPHONE, dtoUser.getsTelephone());
        contentValues.put(ShemaDataBase.USER_URI_IMG, dtoUser.getsUri());

        return (int) database.insert(ShemaDataBase.FeedEntry.TABLE_NAME, null, contentValues);
    }

    @Override
    public boolean searchUserMail(DtoUser dtoUser) throws SQLException {
        try {

        } catch (Exception e) {
            Log.w("Error :", "Is Error insert :" + e.getMessage());
        }

        return false;
    }

    @Override
    public ArrayList<DtoUser> consultUser(DtoUser dtoUser, int iTypeConsult) throws SQLException {
        ArrayList<DtoUser> PersonList = new ArrayList<>();
        String sSQL=null;
        switch (iTypeConsult)
        {
            case 0:
                sSQL =ShemaDataBase.SELECT_ALL_PERSON;
                break;
            case 1:
                sSQL =ShemaDataBase.SELECT_ALL_PERSON +ShemaDataBase.WHERE+ShemaDataBase.FeedEntry.TABLE_USER[3]+ShemaDataBase.LIKE+" '%"+dtoUser.getsName()+"%' "+ShemaDataBase.OR+ShemaDataBase.FeedEntry.TABLE_USER[2]+ShemaDataBase.LIKE+" '%"+dtoUser.getsName()+"%' ";
                break;
        }

        Cursor personCursor = database.rawQuery(sSQL, null);
        if(personCursor.moveToFirst()){
            do{
                DtoUser personaBO = new DtoUser(
                        personCursor.getInt(0),
                        personCursor.getString(1),
                        personCursor.getString(2),
                        personCursor.getString(3),
                        personCursor.getString(4),
                        personCursor.getString(5));
                PersonList.add(personaBO);
            }while(personCursor.moveToNext());
        }
        return PersonList;
    }

    @Override
    public int deleteUser(DtoUser dtoUser) throws SQLException {

        try {

        } catch (Exception e) {
            Log.w("Error :", "Is Error insert :" + e.getMessage());
        }
        return 0;
    }

    @Override
    public ArrayList<DtoUser> updateUser(DtoUser dtoUser) throws SQLException {
        try {

        } catch (Exception e) {
            Log.w("Error :", "Is Error insert :" + e.getMessage());
        }

        return null;
    }
}
