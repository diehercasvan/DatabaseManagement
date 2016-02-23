package DataBaseHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by DIEGO CASALLAS on 22/02/2016.
 */
public class DataBaseUser extends SQLiteOpenHelper {
    public static final String NAME_DATABASE = "MusicaDB";
    public static final int VERSION_DATABASE = 1;
    public static final String CREATE_TABLE = "";
    public static final String TABLE_USER = "";
    public static final String DROP_TABLE = "";

    public DataBaseUser(Context context) {
        super(context, NAME_DATABASE, null, VERSION_DATABASE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE+TABLE_USER);
        onCreate(db);
    }
}
