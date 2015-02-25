package android_app.gg.peter.madklub.calendar;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.timessquare.CalendarPickerView;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android_app.gg.peter.madklub.R;
import android_app.gg.peter.madklub.network.json_representation.DinnerClub;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link android_app.gg.peter.madklub.calendar.CalendarFragment.CalendarFragmentListener} interface
 * to handle interaction events.
 * Use the {@link CalendarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalendarFragment extends Fragment {
    private CalendarFragmentListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CalendarFragment.
     */
    public static CalendarFragment newInstance() {
        CalendarFragment fragment = new CalendarFragment();
        Bundle args = new Bundle();
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
//        hasOptionsMenu();
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Log.d("Wiosoft", "OPTIONS MENU BITTE!");
        inflater.inflate(R.menu.menu_overlook_calendar, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_cardview:
                mListener.selectCardView();
                return true;
//            case R.id.fragment_menu_item:
//                // Not implemented here
//                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.overlook_calendar, container, false);
        final CalendarPickerView calendar = (CalendarPickerView)rootView.findViewById(R.id.calendar_view);
        Calendar startDate = Calendar.getInstance();
        Calendar maxDate = Calendar.getInstance();
        maxDate.add(Calendar.MONTH, 2);
        calendar.init(startDate.getTime(),maxDate.getTime(), Locale.getDefault()).inMode(CalendarPickerView.SelectionMode.MULTIPLE);
        for(DinnerClub club : mListener.getTestData()){
            calendar.selectDate(club.getDate().getTime());
        }
        calendar.smoothScrollToPositionFromTop(0,0,0);
        calendar.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {
                calendar.selectDate(date);
                // Open CreateDinneClub
            }

            @Override
            public void onDateUnselected(Date date) {
                calendar.selectDate(date);
                // Open DinnerClubDetail
            }
        });
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
        public DinnerClub[] getTestData();
//        public void onFragmentInteraction(Uri uri);
    }

}
