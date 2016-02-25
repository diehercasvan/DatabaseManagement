package Class;

import android.provider.BaseColumns;

/**
 * Created by DIEGO  CASALLAS  on 25/11/2015.
 */
public  final  class ShemaDataBase {

    private ShemaDataBase() {
    }

    public static final String DATABASE_NAME="BDperson";
    public static final int DATABASE_VERSION=2;

    //Create  structure basic
    private static final String TEXT_TYPE=" TEXT";
    private static final String TEXT_INTEGER=" INTEGER";
    private static final String COMMA_SEP=",";
    private static final String CREATE_TABLE=" CREATE TABLE ";
    public static final String SELECT=" SELECT ";
    public static final String DELETE=" DELETE ";
    public static final String UPDATE=" UPDATE ";
    private static final String INSERT=" INSERT INTO";
    public static final String VALUES=" VALUES ";
    private static final String SET =" SET  ";
    private static final String FROM =" FROM  ";
    public static final String LIKE =" LIKE  ";
    public static final String ORDER_BY=" ORDER BY ";
    public static final String WHERE=" WHERE ";
    private static final String PRIMARY_KEY=" PRIMARY KEY ";
    public static final String DROP_TABLE=" DROP TABLE IF EXISTS ";
    private static final String NOT_NULL=" NOT NULL ";
    private static final String UNIQUE =" UNIQUE  ";
    public static final String OR =" OR  ";
    private static final String AUTOINCREMENT=" AUTOINCREMENT ";
    //Create  table
    public static final String SQL_CREATE_ENTRIES=CREATE_TABLE+ FeedEntry.TABLE_NAME+"("+
            FeedEntry.TABLE_USER[0]+TEXT_INTEGER+PRIMARY_KEY+AUTOINCREMENT+COMMA_SEP+
            FeedEntry.TABLE_USER[1]+TEXT_TYPE+NOT_NULL+COMMA_SEP+
            FeedEntry.TABLE_USER[2]+TEXT_TYPE+NOT_NULL+COMMA_SEP+
            FeedEntry.TABLE_USER[3]+TEXT_TYPE+NOT_NULL+COMMA_SEP+
            FeedEntry.TABLE_USER[4]+TEXT_TYPE+NOT_NULL+UNIQUE+COMMA_SEP+
            FeedEntry.TABLE_USER[5]+TEXT_TYPE+NOT_NULL+")";
    //Insert person
    public  static  final  String SQL_INSERT_ENTRIES=INSERT+ FeedEntry.TABLE_NAME
            +"("
            + FeedEntry.TABLE_USER[1]+COMMA_SEP
            + FeedEntry.TABLE_USER[2]+COMMA_SEP
            + FeedEntry.TABLE_USER[3]+COMMA_SEP
            + FeedEntry.TABLE_USER[4]+COMMA_SEP
            + FeedEntry.TABLE_USER[5]
            +")"+ VALUES+"(";
    //Update person
    public  static  final  String SQL_UPDATE_ENTRIES=UPDATE+ FeedEntry.TABLE_NAME+SET;

    //Delete person
    public  static  final  String SQL_DELETE_ENTRIES=DELETE+FROM+ FeedEntry.TABLE_NAME+WHERE+ FeedEntry.TABLE_USER[4]+"=";

    /*Campos T_PERSONA*/
    public static final String USER_NAME="User_Name";
    public static final String USER_SURNAME = "User_Last_Name";
    public static final String USER_TELEPHONE = "User_Telephone";
    public static final String USER_EMAIL = "User_Email";
    public static final String USER_URI_IMG = "User_Url_Img";
    /*Create  selects*/

    public static  final  String SELECT_ALL_PERSON=SELECT
            + FeedEntry.TABLE_USER[0]+COMMA_SEP
            + FeedEntry.TABLE_USER[1]+COMMA_SEP
            + FeedEntry.TABLE_USER[2]+COMMA_SEP
            + FeedEntry.TABLE_USER[3]+COMMA_SEP
            + FeedEntry.TABLE_USER[4]+COMMA_SEP
            + FeedEntry.TABLE_USER[5]+FROM+ FeedEntry.TABLE_NAME;

    public static  final  String SELECT_ALL_PERSON_LIKE=SELECT
            + FeedEntry.TABLE_USER[0]+COMMA_SEP
            + FeedEntry.TABLE_USER[1]+COMMA_SEP
            + FeedEntry.TABLE_USER[2]+COMMA_SEP
            + FeedEntry.TABLE_USER[3]+COMMA_SEP
            + FeedEntry.TABLE_USER[4]+COMMA_SEP
            + FeedEntry.TABLE_USER[5]+FROM+ FeedEntry.TABLE_NAME;

    /* Inner class that defines the table contents */
    public static abstract class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = " user ";
        public static final String [] TABLE_USER=new String[]{"id"," User_Name","User_Last_Name","User_Telephone","User_Email","User_Url_Img"};

    }

}
