package android_app.gg.peter.madklub;

import android.os.Bundle;

import java.util.Calendar;

import android_app.gg.peter.madklub.calendar.CalendarFragment;
import android_app.gg.peter.madklub.network.json_representation.DinnerClub;
import android_app.gg.peter.madklub.overlook.OverlookFragment;


public class OverlookActivity extends BaseActivity implements CalendarFragment.CalendarFragmentListener, OverlookFragment.OverlookListener {
    private static final String[] courses = {"Nudler","Lassagne","Wok","Mørbrad","Bøf med Løg",
            "Firkadeller","Reje Suppe","Pizza","Forloren hare","Risengrød"};
    private static final String[] cooks = {"Peter Alexander Garnæs","Andreas Braun","Urd schrøder",
            "Niels Thorbjørn","Julie Kror","Maria Damm","Bitta Baksbur","Erik Gräs",
            "Christian Raahauge","Trine Fryjana"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction()
                .replace(R.id.overlook_container, OverlookFragment.newInstance())
                .commit();
    }

    @Override
    protected int getLayoutResource(){
        return R.layout.activity_overlook;
    }


    @Override
    public void selectCalendarView() {
        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.right_in, R.anim.left_out, R.anim.left_in, R.anim.right_out)
                .replace(R.id.overlook_container, CalendarFragment.newInstance())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void selectCardView() {
        onBackPressed();
    }

    public DinnerClub[] getTestData(){
        DinnerClub[] dc = new DinnerClub[11];
        for(int i = 0;i < 10;i++){
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_MONTH,i);
//            dc[i] = new DinnerClub(cal,cooks[i],courses[i]);
        }
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH,40);
//        dc[10] = new DinnerClub(cal,"bobby","Salat");
        return dc;
    }
}
