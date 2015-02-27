package android_app.gg.peter.madklub.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by peter on 2/27/15.
 */
public class DbHelper  extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "MadklubDb";

    public DbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    /**
     * Creates tables for the app
     * @param db the database object the tables will be created in
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON;");
        // No foreign keys
        db.execSQL(DbContract.Courses.CREATE_SQL_TABLE);
        // No foreign keys
        db.execSQL(DbContract.Users.CREATE_SQL_TABLE);
        // References both courses and users
        db.execSQL(DbContract.DinnerClubs.CREATE_SQL_TABLE);
        // References Users and DinnerClubs
        db.execSQL(DbContract.DinnerClubUsers.CREATE_SQL_TABLE);
    }

    /**
     * Called when database is upgraded with new tables. Clears all data
     * @param db Database to be upgraded
     * @param oldVersion old version number
     * @param newVersion new version number
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DbContract.Courses.DELETE_SQL_TABLE);
        db.execSQL(DbContract.Users.DELETE_SQL_TABLE);
        db.execSQL(DbContract.DinnerClubs.DELETE_SQL_TABLE);
        db.execSQL(DbContract.DinnerClubUsers.DELETE_SQL_TABLE);
        onCreate(db);
    }
}
