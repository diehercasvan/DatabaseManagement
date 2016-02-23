package DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import java.sql.SQLException;
import java.util.ArrayList;
import DTO.DtoUser;
import DataBaseHelper.DataBase;
import Interfaces.IntUser;

/**
 * Created by DIEGO CASALLAS on 22/02/2016.
 */
public class DaoUser  extends DataBase implements IntUser{

    private SQLiteDatabase database;
    public DaoUser(Context context) {
        super(context);
    }

    @Override
    public int insertUser(DtoUser dtoUser) throws SQLException {
        try
        {
            this.database=super.open();
        }
        catch (SQLException e){

        }finally {
            super.close();
        }

        return 0;
    }

    @Override
    public boolean searchUserMail(DtoUser dtoUser) throws SQLException {
        try
        {
            this.database=super.open();
        }
        catch (SQLException e){

        }finally {
            super.close();
        }

        return false;
    }

    @Override
    public ArrayList<DtoUser> consultUser(DtoUser dtoUser, int iTypeConsult) throws SQLException {
        try
        {
            this.database=super.open();
        }
        catch (SQLException e){

        }finally {
            super.close();
        }
        return null;
    }

    @Override
    public int deleteUser(DtoUser dtoUser) throws SQLException {

        try
        {
            this.database=super.open();
        }
        catch (SQLException e){

        }finally {
            super.close();
        }
        return 0;
    }

    @Override
    public ArrayList<DtoUser> updateUser(DtoUser dtoUser) throws SQLException {
        try
        {
            this.database=super.open();
        }
        catch (SQLException e){

        }finally {
            super.close();
        }

        return null;
    }
}
