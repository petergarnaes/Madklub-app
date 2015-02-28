package android_app.gg.peter.madklub.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * Created by peter on 2/27/15.
 */
public class MadklubContentProvider extends ContentProvider {
    private static final int DINNERCLUB_QUERY = 2;
    private static final int COURSE_QUERY = 3;
    private static final int USER_QUERY = 4;
    private static final int DINNERCLUB_USER_JOIN = 5;
    private static final int DINNERCLUB_USER_QUERY = 6;
    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    // URI's to identify by URImatcher
    static{
        sURIMatcher.addURI(DbContract.AUTHORITY,DbContract.DinnerClubs.URI_TAG_DINNERCLUB_QUERY,
                DINNERCLUB_QUERY);
        sURIMatcher.addURI(DbContract.AUTHORITY,DbContract.Courses.URI_TAG_COURSES_FROM_TYPE,
                COURSE_QUERY);
        sURIMatcher.addURI(DbContract.AUTHORITY,DbContract.Users.URI_TAG_USER_QUERY,
                USER_QUERY);
        sURIMatcher.addURI(DbContract.AUTHORITY,DbContract.DinnerClubUsers.URI_TAG_JOIN_DINNERCLUB_USERS,
                DINNERCLUB_USER_JOIN);
        sURIMatcher.addURI(DbContract.AUTHORITY,DbContract.DinnerClubUsers.URI_TAG_DINNERCLUB_USER_QUERY,
                DINNERCLUB_USER_QUERY);
    }
    private DbHelper dbHelper;

    @Override
    public boolean onCreate() {
        dbHelper = new DbHelper(getContext());
        return !(dbHelper == null);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        int match = sURIMatcher.match(uri);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        switch (match){
            case DINNERCLUB_QUERY:
                return db.query(DbContract.DinnerClubs.TABLE_NAME, projection, selection,
                        selectionArgs, null, null, sortOrder);
            case COURSE_QUERY:
                return db.query(DbContract.Courses.TABLE_NAME, projection, selection,
                        selectionArgs, null, null, sortOrder);
            case USER_QUERY:
                return db.query(DbContract.Users.TABLE_NAME, projection, selection,
                        selectionArgs, null, null, sortOrder);
            case DINNERCLUB_USER_QUERY:
                return db.query(DbContract.DinnerClubUsers.TABLE_NAME, projection, selection,
                        selectionArgs, null, null, sortOrder);
            case DINNERCLUB_USER_JOIN:
                // Joins DinnerClubUsers on Users, so we just select the ones with the right dinnerclub id
                String tableName = DbContract.DinnerClubUsers.TABLE_NAME+
//                        " JOIN "+DbContract.DinnerClubs.TABLE_NAME+
//                        " ON ("+DbContract.DinnerClubUsers.TABLE_NAME+"."+DbContract.DinnerClubUsers.dinnerClubId+
//                        "="+DbContract.DinnerClubs.TABLE_NAME+"."+DbContract.DinnerClubs._ID+")"+
                        " INNER JOIN "+DbContract.Users.TABLE_NAME+
                        " ON ("+DbContract.DinnerClubUsers.TABLE_NAME+"."+DbContract.DinnerClubUsers.userId+
                        "="+DbContract.Users.TABLE_NAME+"."+DbContract.Users._ID+")";
                return db.query(tableName, projection, selection,
                        selectionArgs, null, null, sortOrder);
            default:
                return null;
        }
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] split = uri.toString().split("/");
        long rowsId = db.insert(split[3],null,values);
        return Uri.withAppendedPath(uri,""+rowsId);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] split = uri.toString().split("/");
        return db.delete(split[3],selection,selectionArgs);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] split = uri.toString().split("/");
        return db.update(split[3],values,selection,selectionArgs);
    }
}
