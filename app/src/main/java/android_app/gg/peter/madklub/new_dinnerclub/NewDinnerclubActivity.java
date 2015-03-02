package android_app.gg.peter.madklub.new_dinnerclub;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ToggleButton;

import android_app.gg.peter.madklub.BaseActivity;
import android_app.gg.peter.madklub.R;
import android_app.gg.peter.madklub.db.DbContract;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnFocusChange;

public class NewDinnerclubActivity extends BaseActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    // Load ids for getting suggestions from DB with loaderManager
    private static final int SUGGEST_MAIN_COURSES = 201;
    private static final int SUGGEST_SIDE_COURSES = 202;
    @InjectView(R.id.main_course_text_edit) EditText mainCourse;
    @InjectView(R.id.side_course_text_edit) EditText sideCourse;
    @InjectView(android.R.id.list) ListView suggestions;
    private CursorAdapter mAdapter;

    @OnFocusChange(R.id.main_course_text_edit) void focusOnMainCourse(View v,boolean hasFocus){
        // Suggest main courses
        Log.d("Madklub","focus on main courses");
        if(hasFocus){
            getLoaderManager().initLoader(SUGGEST_MAIN_COURSES,null,this);
        }
    }

    @OnFocusChange(R.id.side_course_text_edit) void focusOnSideCourse(View v,boolean hasFocus){
        // Suggest side courses
        Log.d("Madklub","focus on side courses");
        if(hasFocus){
            getLoaderManager().initLoader(SUGGEST_SIDE_COURSES,null,this);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getResources().getString(R.string.new_dinnerclub_title));
        ButterKnife.inject(this);
        mAdapter = new CourseSuggestionsAdapter(getApplicationContext(),null);
        suggestions.setAdapter(mAdapter);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_new_dinnerclub;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_dinnerclub, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){
            case R.id.action_price:
                // Handle price selected
                return true;
            default:
                Log.d("Madklub","Why you no go back?!");
                return super.onOptionsItemSelected(item);
        }
    }

    public void onIsShoppedToggle(View view){
        // Is the toggle on?
        boolean on = ((ToggleButton) view).isChecked();

        if (on) {
            Log.d("Madklub","ON! Toggle works?");
            // Enable vibrate
        } else {
            Log.d("Madklub","OFF! Toggle works?");
            // Disable vibrate
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {DbContract.Courses.courseName};
        String selection = DbContract.Courses.courseName+"=?";
        switch (id){
            case SUGGEST_MAIN_COURSES:
                String uri = DbContract.CONTENT_PREFIX+"/"+DbContract.Courses.URI_TAG_COURSES_QUERY;
                String[] selectionArgs = {""+DbContract.Courses.MAIN_COURSE_ID};
                return new CursorLoader(getApplicationContext(), Uri.parse(uri),projection,selection,selectionArgs,null);
            case SUGGEST_SIDE_COURSES:
                String uri2 = DbContract.CONTENT_PREFIX+"/"+DbContract.Courses.URI_TAG_COURSES_QUERY;
                String[] selectionArgs2 = {""+DbContract.Courses.SIDE_COURSE_ID};
                return new CursorLoader(getApplicationContext(), Uri.parse(uri2),projection,selection,selectionArgs2,null);
            default:
                return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if(cursor != null && cursor.moveToFirst()){
            mAdapter.changeCursor(cursor);
            mAdapter.notifyDataSetChanged();
        }
        // TODO else download from server
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {}
}
