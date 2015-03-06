package android_app.gg.peter.madklub.dinnerclub_detail;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import android_app.gg.peter.madklub.BaseActivity;
import android_app.gg.peter.madklub.R;
import android_app.gg.peter.madklub.Utils;
import android_app.gg.peter.madklub.db.DbContract;
import android_app.gg.peter.madklub.network.json_representation.DinnerClub;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class DinnerclubDetailActivity extends BaseActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    public static final String EXTRA_IMAGE = "DetailActivity:image";
    public static final String EXTRA_TABLE = "DetailActivity:table";
    private static final String KEY_COURSE = "DetailActivity:course";
    private static final String KEY_COOK = "DetailActivity:cook";
    public static final String KEY_DATE = "DetailActivity:date";
    private static final String KEY_PARTICIPATING = "DetailActivity:participating";
    private static final String LOADER_KEY_DATE = "dateKey";
    @InjectView(R.id.dinnerclub_detail_image_view) ImageView image;
    @InjectView(R.id.table_cook_course_detail) TableLayout table;
    @InjectView(R.id.cook_text_view_detail) TextView cookTextView;
    @InjectView(R.id.menu_text_view_detail) TextView courseTextView;
    @InjectView(R.id.button_participating_detail) Button participatingButton;
    @InjectView(R.id.button_not_participating_detail) Button notParticipatingButton;

    @OnClick(R.id.button_participating_detail) void isParticipating(View v){
                    participatingButton.setSelected(true);
                    notParticipatingButton.setSelected(false);
    }
    @OnClick(R.id.button_participating_detail) void isNotParticipating(View v){
                    notParticipatingButton.setSelected(true);
                    participatingButton.setSelected(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.inject(this);
        Intent intent = getIntent();
        image.setImageResource(R.drawable.noodles);
        // Set table information
        String date = intent.getStringExtra(KEY_DATE);
        String cookString = (intent.hasExtra(KEY_COOK) ? intent.getStringExtra(KEY_COOK) : "");
        String courseString = (intent.hasExtra(KEY_COURSE) ? intent.getStringExtra(KEY_COURSE) : "");
//        ((TextView)table.findViewById(R.id.cook_text_view_detail)).setText(cookString);
//        ((TextView)table.findViewById(R.id.menu_text_view_detail)).setText(courseString);
        cookTextView.setText(cookString);
        courseTextView.setText(courseString);
        // We have the date string no matter what
        setTitle(date);
        // If EXTRA_IMAGE exists, we transit from overlook activity
        if(intent.hasExtra(EXTRA_IMAGE)){
            ViewCompat.setTransitionName(image, EXTRA_IMAGE);
            ViewCompat.setTransitionName(table, EXTRA_TABLE);
            boolean isParticipating = getIntent().getBooleanExtra(KEY_PARTICIPATING,false);
            participatingButton.setSelected(isParticipating);
            notParticipatingButton.setSelected(!isParticipating);
        } else {
            // We need to load data that should be displayed with a loader
            Bundle b = new Bundle();
            b.putString(LOADER_KEY_DATE,date);
            getLoaderManager().initLoader(0,b,this);
        }
    }

    @Override
    protected int getLayoutResource(){
        return R.layout.activity_dinnerclub_detail;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dinneclub_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
//        overridePendingTransition(R.anim.old_dinnerclub_detail_activity_in,R.anim.old_dinnerclub_detail_activity_out);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle loaderArgs) {
        Uri uri = Uri.parse(DbContract.CONTENT_PREFIX+"/"+DbContract.DinnerClubs.URI_TAG_DINNERCLUB_WITH_COOK_AND_COURSE);
        String[] projection = new String[]{
                DbContract.DinnerClubs.SELECTION_MAIN_COURSE_TABLE_JOINED_NAME+"."+DbContract.Courses.courseName,
                DbContract.DinnerClubs.SELECTION_SIDE_COURSE_TABLE_JOINED_NAME+"."+DbContract.Courses.courseName,
//                DbContract.DinnerClubs.isShopped,
                DbContract.Users.name,
                DbContract.DinnerClubs.youParticipating
        };
        String selection = DbContract.DinnerClubs.date+"=?";
        String[] args = new String[]{loaderArgs.getString(LOADER_KEY_DATE)};
        return new CursorLoader(getApplicationContext(),uri,projection,selection,args,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if(cursor != null && cursor.moveToFirst()){
            courseTextView.setText(cursor.getString(0)+" - "+cursor.getString(1));
            cookTextView.setText(cursor.getString(2));
            boolean participating = (cursor.getInt(3)>0);
            participatingButton.setSelected(participating);
            notParticipatingButton.setSelected(!participating);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    public static void launchFromOverlook(Activity activity, View imageView, String imageIdentifier,
                                          View tableView, String course, String cook, String date,
                                          boolean participating) {
        ActivityOptionsCompat options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                        activity, new Pair<>(imageView,EXTRA_IMAGE),new Pair<>(tableView,EXTRA_TABLE));
        Intent intent = new Intent(activity, DinnerclubDetailActivity.class);
        intent.putExtra(EXTRA_IMAGE, imageIdentifier);
        intent.putExtra(KEY_COURSE,course);
        intent.putExtra(KEY_COOK,cook);
        intent.putExtra(KEY_DATE,date);
        intent.putExtra(KEY_PARTICIPATING,participating);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
//        activity.overridePendingTransition(R.anim.old_dinnerclub_detail_activity_in,R.anim.old_dinnerclub_detail_activity_out);
    }
    public static void launchFromCalendar(Activity activity,Date date){
        Intent intent = new Intent(activity, DinnerclubDetailActivity.class);
        intent.putExtra(KEY_DATE, Utils.formatDateToString(date));
        ActivityCompat.startActivity(activity,intent,null);
    }

}
