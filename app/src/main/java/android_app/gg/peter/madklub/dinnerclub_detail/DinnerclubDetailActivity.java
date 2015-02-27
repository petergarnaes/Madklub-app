package android_app.gg.peter.madklub.dinnerclub_detail;

import android.app.Activity;
import android.content.Intent;
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
import java.util.Locale;

import android_app.gg.peter.madklub.BaseActivity;
import android_app.gg.peter.madklub.R;
import android_app.gg.peter.madklub.network.json_representation.DinnerClub;

public class DinnerclubDetailActivity extends BaseActivity {
    public static final String EXTRA_IMAGE = "DetailActivity:image";
    public static final String EXTRA_TABLE = "DetailActivity:table";
    private static final String KEY_COURSE = "DetailActivity:course";
    private static final String KEY_COOK = "DetailActivity:cook";
    private static final String KEY_DATE = "DetailActivity:date";
    private static final String KEY_PARTICIPATING = "DetailActivity:participating";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ImageView image = (ImageView) findViewById(R.id.dinnerclub_detail_image_view);
        TableLayout table = (TableLayout) findViewById(R.id.table_cook_course_detail);
        ViewCompat.setTransitionName(image, EXTRA_IMAGE);
        ViewCompat.setTransitionName(table, EXTRA_TABLE);
        image.setImageResource(R.drawable.noodles);
        ((TextView)table.findViewById(R.id.cook_text_view_detail)).setText(getIntent().getStringExtra(KEY_COOK));
        ((TextView)table.findViewById(R.id.menu_text_view_detail)).setText(getIntent().getStringExtra(KEY_COURSE));
        setTitle(getIntent().getStringExtra(KEY_DATE));
        boolean isParticipating = getIntent().getBooleanExtra(KEY_PARTICIPATING,false);
        final Button participatingButton = (Button)findViewById(R.id.button_participating_detail);
        final Button notParticipatingButton = (Button)findViewById(R.id.button_not_participating_detail);
        participatingButton.setSelected(isParticipating);
        notParticipatingButton.setSelected(!isParticipating);
        participatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                dinnerClub.setPaticipating(true);
                participatingButton.setSelected(true);
                notParticipatingButton.setSelected(false);
            }
        });
        notParticipatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                dinnerClub.setPaticipating(false);
                notParticipatingButton.setSelected(true);
                participatingButton.setSelected(false);
            }
        });
    }

    @Override
    protected int getLayoutResource(){
        return R.layout.activity_dinnerclub_detail;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dinneclub_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
//        overridePendingTransition(R.anim.old_dinnerclub_detail_activity_in,R.anim.old_dinnerclub_detail_activity_out);
    }

    public static void launchFromOverlook(Activity activity, View imageView, String imageIdentifier, View tableView, String course, String cook, String date, boolean participating) {
        ActivityOptionsCompat options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                        activity, new Pair<View,String>(imageView,EXTRA_IMAGE),new Pair<View,String>(tableView,EXTRA_TABLE));
        Intent intent = new Intent(activity, DinnerclubDetailActivity.class);
        intent.putExtra(EXTRA_IMAGE, imageIdentifier);
        intent.putExtra(KEY_COURSE,course);
        intent.putExtra(KEY_COOK,cook);
        intent.putExtra(KEY_DATE,date);
        intent.putExtra(KEY_PARTICIPATING,participating);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
//        activity.overridePendingTransition(R.anim.old_dinnerclub_detail_activity_in,R.anim.old_dinnerclub_detail_activity_out);
    }
    public static void launchFromCalendar(Activity activity,DinnerClub dinnerClub){
        Intent intent = new Intent(activity, DinnerclubDetailActivity.class);
        intent.putExtra(KEY_COURSE,dinnerClub.getCourse().getMainCourse());
        intent.putExtra(KEY_COOK,dinnerClub.getCook().getFirstName());
        String dateText = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault()).format(dinnerClub.getDate().getTime());
        intent.putExtra(KEY_DATE,dateText);
        intent.putExtra(KEY_PARTICIPATING,dinnerClub.isPaticipating());
        ActivityCompat.startActivity(activity,intent,null);
    }
}
