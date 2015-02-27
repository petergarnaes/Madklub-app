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
    private static final int ALL_DINNERCLUBS = 1;
    private static final int DINNERCLUB_WITH_DATE = 2;
    private static final int ALL_COURSES_WTIH_TYPE = 3;
    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    // URI's to identify by URImatcher
    static{
        sURIMatcher.addURI(DbContract.AUTHORITY,DbContract.DinnerClubs.URI_TAG_ALL_COLUMNS,
                ALL_DINNERCLUBS);
        sURIMatcher.addURI(DbContract.AUTHORITY,DbContract.DinnerClubs.URI_TAG_DINNERCLUB_WITH_DATE,
                DINNERCLUB_WITH_DATE);
        sURIMatcher.addURI(DbContract.AUTHORITY,DbContract.Courses.URI_TAG_COURSES_FROM_TYPE,
                ALL_COURSES_WTIH_TYPE);
    }
    private DbHelper dbHelper;

    @Override
    public boolean onCreate() {
        dbHelper = new DbHelper(getContext());
        return !(dbHelper == null);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
