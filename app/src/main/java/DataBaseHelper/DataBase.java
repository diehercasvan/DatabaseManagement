package DataBaseHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;

/**
 * Created by DIEGO CASALLAS on 22/02/2016.
 */
public class DataBase  {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase dataBaseUser;

    public DataBase(Context context) {
        openHelper= new DataBaseUser(context);
    }
    public SQLiteDatabase open()throws SQLException{

        dataBaseUser=openHelper.getWritableDatabase();
        return dataBaseUser;
    }
    public void  close()throws SQLException{

        this.openHelper.close();
    }


}
