package BL;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.DaoUserEntity;
import DTO.DtoUser;
import DataBaseHelper.DataBase;

/**
 * Created by DIEGO CASALLAS on 22/02/2016.
 */
public class BusinessLogic  extends DataBase {
    private ArrayList<DtoUser> arrayList;
    private SQLiteDatabase db;
    private DaoUserEntity daoUserEntity;

    public BusinessLogic(Context context) {
        super(context);
        this.daoUserEntity=null;
        this.db=null;
    }

    public int insertUserBl(DtoUser dtoUser) throws SQLException {
        this.db=super.open();
        int idUser=0;
        try
        {
            daoUserEntity=new DaoUserEntity(db);
            db.beginTransaction();
             idUser=daoUserEntity.insertUser(dtoUser);
            db.setTransactionSuccessful();
        }
        catch(Exception e)
        {
            throw  e;
        }
        finally {
            db.endTransaction();
            super.close();
        }

        return idUser;
    }
    public  ArrayList<DtoUser> consultUserBl(DtoUser dtoUser, int iTypeSearch) throws SQLException {
        this.db = super.open();
        ArrayList<DtoUser> listPerson=null;
        try {
            daoUserEntity  = new DaoUserEntity(db);
            db.beginTransaction();
            listPerson = daoUserEntity.consultUser(dtoUser, iTypeSearch);
            db.setTransactionSuccessful();

        } catch (Exception e) {
            throw e;
        }finally{
            db.endTransaction();
            super.close();
        }

        return listPerson;
    }
    public int deleteUserBl(DtoUser dtoUser) throws SQLException {
        this.db=super.open();
        int idUser=0;
        try
        {
            daoUserEntity=new DaoUserEntity(db);
            db.beginTransaction();
            idUser=daoUserEntity.deleteUser(dtoUser);
            db.setTransactionSuccessful();
        }
        catch(Exception e)
        {
            throw  e;
        }
        finally {
            db.endTransaction();
            super.close();
        }
        return idUser;
    }

    public ArrayList<DtoUser> updateUserBl(DtoUser dtoUser) throws SQLException {

        this.db = super.open();
        ArrayList<DtoUser> listPerson=null;
        try {
            daoUserEntity  = new DaoUserEntity(db);
            db.beginTransaction();
            listPerson = daoUserEntity.updateUser(dtoUser);
            db.setTransactionSuccessful();

        } catch (Exception e) {
            throw e;
        }finally{
            db.endTransaction();
            super.close();
        }
        return listPerson;
    }
    public boolean searchUserMail(DtoUser dtoUser) throws SQLException {

        this.db = super.open();
        boolean listPerson=false;
        try {
            daoUserEntity  = new DaoUserEntity(db);
            db.beginTransaction();
            listPerson = daoUserEntity.searchUserMail(dtoUser);
            db.setTransactionSuccessful();

        } catch (Exception e) {
            throw e;
        }finally{
            db.endTransaction();
            super.close();
        }
        return listPerson;
    }

}
