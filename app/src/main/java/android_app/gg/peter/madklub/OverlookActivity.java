package android_app.gg.peter.madklub;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Calendar;

import android_app.gg.peter.madklub.network.json_representation.DinnerClub;
import android_app.gg.peter.madklub.overlook.OverlookAdapter;


public class OverlookActivity extends ActionBarActivity {
//    private static final DinnerClub[] myDataset = {new DinnerClub(Calendar.getInstance())};
    private static final String[] courses = {"Nudler","Lassagne","Wok","Mørbrad","Bøf med Løg",
                    "Firkadeller","Reje Suppe","Pizza","Forloren hare","Risengrød"};
    private static final String[] cooks = {"Peter Alexander Garnæs","Andreas Braun","Urd schrøder",
            "Niels Thorbjørn","Julie Kror","Maria Damm","Bitta Baksbur","Erik Gräs",
            "Christian Raahauge","Trine Fryjana"};
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overlook);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mRecyclerView = (RecyclerView) findViewById(R.id.overlook_recycler);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new OverlookAdapter(this, getTestData());
        mRecyclerView.setAdapter(mAdapter);
//        toolbar.getBackground().setAlpha(0);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_overlook, menu);
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
    private DinnerClub[] getTestData(){
        DinnerClub[] dc = new DinnerClub[10];
        for(int i = 0;i < 10;i++){
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_MONTH,i);
            dc[i] = new DinnerClub(cal,cooks[i],courses[i]);
        }
        return dc;
    }
}
