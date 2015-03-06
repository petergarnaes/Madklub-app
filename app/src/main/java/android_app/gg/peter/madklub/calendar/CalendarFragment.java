package android_app.gg.peter.madklub.calendar;

import android.app.Activity;
import android.app.Fragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.timessquare.CalendarPickerView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android_app.gg.peter.madklub.R;
import android_app.gg.peter.madklub.Utils;
import android_app.gg.peter.madklub.db.DbContract;
import android_app.gg.peter.madklub.dinnerclub_detail.DinnerclubDetailActivity;
import android_app.gg.peter.madklub.network.json_representation.DinnerClub;
import android_app.gg.peter.madklub.new_dinnerclub.NewDinnerclubActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link android_app.gg.peter.madklub.calendar.CalendarFragment.CalendarFragmentListener} interface
 * to handle interaction events.
 * Use the {@link CalendarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalendarFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{
    public static final int CALENDAR_RANGE_MONTHS = 2;
    private CalendarFragmentListener mListener;
    private CalendarPickerView calendar;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CalendarFragment.
     */
    public static CalendarFragment newInstance() {
        CalendarFragment fragment = new CalendarFragment();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
        return fragment;
    }

    public CalendarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_overlook_calendar, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_cardview:
                mListener.selectCardView();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.overlook_calendar, container, false);
        calendar = (CalendarPickerView)rootView.findViewById(R.id.calendar_view);
        Calendar startDate = Calendar.getInstance();
        Calendar maxDate = Calendar.getInstance();
        maxDate.add(Calendar.MONTH, 2);
        calendar.init(startDate.getTime(),maxDate.getTime(), Locale.getDefault()).inMode(CalendarPickerView.SelectionMode.MULTIPLE);
        calendar.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {
                calendar.selectDate(date);
                // Open CreateDinnerClub
                Intent intent = new Intent(getActivity(), NewDinnerclubActivity.class);
                intent.putExtra(NewDinnerclubActivity.KEY_DATE,Utils.formatDateToString(date));
                ActivityCompat.startActivity(getActivity(), intent, null);
            }

            @Override
            public void onDateUnselected(Date date) {
                calendar.selectDate(date);
                Log.d("Wiosoft", "Date is: " + DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault()).format(date));
                DinnerclubDetailActivity.launchFromCalendar(getActivity(), date);
            }
        });
        getLoaderManager().initLoader(0, null, this);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (CalendarFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle loadArgs) {
        Uri uri = Uri.parse(DbContract.CONTENT_PREFIX+"/"+DbContract.DinnerClubs.URI_TAG_DINNERCLUB_QUERY);
        String[] projection = new String[]{DbContract.DinnerClubs.date};
        String selection = "julianday("+DbContract.DinnerClubs.date+") < julianday(?)";
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, CALENDAR_RANGE_MONTHS);
        String[] args = new String[]{Utils.formatDateToString(cal.getTime())};
        return new CursorLoader(getActivity().getApplicationContext(),uri,projection,selection,args,null);
    }
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        // Cursor holds data
        if(cursor != null && cursor.moveToFirst()){
            Calendar cal = Calendar.getInstance();
            while(cursor.moveToNext()){
                cal.setTime(Utils.parseToDate(cursor.getString(0)));
                calendar.selectDate(cal.getTime());
            }
            // Cursor not needed anymore
            cursor.close();
            calendar.smoothScrollToPositionFromTop(0,0,0);
        }
    }
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface CalendarFragmentListener {
        public void selectCardView();
//        public void onFragmentInteraction(Uri uri);
    }

}
