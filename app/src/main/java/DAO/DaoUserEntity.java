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
    public ArrayList<DtoUser> consultUser(DtoUser dtoUser, int iTypeConsult) throws SQLException {
        ArrayList<DtoUser> PersonList = new ArrayList<>();
        String sSQL = null;
        switch (iTypeConsult) {
            case 0:
                sSQL = ShemaDataBase.SELECT_ALL_PERSON;
                break;
            case 1:
                sSQL = ShemaDataBase.SELECT_ALL_PERSON + ShemaDataBase.WHERE + ShemaDataBase.FeedEntry.TABLE_USER[3] + ShemaDataBase.LIKE + " '%" + dtoUser.getsName() + "%' " + ShemaDataBase.OR + ShemaDataBase.FeedEntry.TABLE_USER[2] + ShemaDataBase.LIKE + " '%" + dtoUser.getsName() + "%' ";
                break;
        }
        Cursor personCursor = database.rawQuery(sSQL, null);
        if (personCursor.moveToFirst()) {
            do {
                DtoUser personaBO = new DtoUser(
                        personCursor.getInt(0),
                        personCursor.getString(1),
                        personCursor.getString(2),
                        personCursor.getString(3),
                        personCursor.getString(4),
                        personCursor.getString(5));
                PersonList.add(personaBO);
            } while (personCursor.moveToNext());
        }
        return PersonList;
    }

    @Override
    public int deleteUser(DtoUser dtoUser) throws SQLException {

        return database.delete(ShemaDataBase.FeedEntry.TABLE_NAME, ShemaDataBase.FeedEntry.TABLE_USER[4] + "=" + "'" + dtoUser.getsMail() + "'", null);

    }

    @Override
    public ArrayList<DtoUser> updateUser(DtoUser dtoUser) throws SQLException {

        ArrayList<DtoUser> PersonList = new ArrayList<>();
        if (!searchUserMail(dtoUser)) {
            contentValues = new ContentValues();
            if (dtoUser.getsName() == null) {
                Log.w("Error", " es error name");
            } else {
                contentValues.put(ShemaDataBase.USER_NAME, dtoUser.getsName());
            }
            if (dtoUser.getsLast_Name() == null) {
                Log.w("Error", " es error surname");
            } else {
                contentValues.put(ShemaDataBase.USER_SURNAME, dtoUser.getsLast_Name());
            }
            if (dtoUser.getsNewMail() == null) {
                Log.w("Error", " es error mail");
            } else {
                contentValues.put(ShemaDataBase.USER_EMAIL, dtoUser.getsNewMail());
            }
            if (dtoUser.getsPhoto() == null) {
                Log.w("Error", " es error telephone");
            } else {
                contentValues.put(ShemaDataBase.USER_TELEPHONE, dtoUser.getsTelephone());
            }
            if (dtoUser.getsUri() == null) {
                Log.w("Error", " es error uri");
            } else {
                contentValues.put(ShemaDataBase.USER_URI_IMG, dtoUser.getsUri());
            }
            if (database.update(ShemaDataBase.FeedEntry.TABLE_NAME, contentValues, ShemaDataBase.FeedEntry.TABLE_USER[4] + "=" + "'" + dtoUser.getsMail() + "'", null) == 1) {
                PersonList = consultUser(dtoUser, 0);
            } else {
                Log.w("Error", " es error update");
            }
        } else {
            Log.w("Error", "It already exists");
        }
        return PersonList;
    }

    @Override
    public boolean searchUserMail(DtoUser dtoUser) throws SQLException {
        boolean bValidate = false;
        String sSQL = ShemaDataBase.SELECT_ALL_PERSON + ShemaDataBase.WHERE + ShemaDataBase.FeedEntry.TABLE_USER[4] + "=" + "'" + dtoUser.getsNewMail() + "'";
        Cursor personCursor = database.rawQuery(sSQL, null);
        if (personCursor.moveToFirst()) {
            do {
                bValidate = true;
            } while (personCursor.moveToNext());
        }
        return bValidate;
    }
}
