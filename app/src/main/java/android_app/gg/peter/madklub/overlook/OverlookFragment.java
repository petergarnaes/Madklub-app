package android_app.gg.peter.madklub.overlook;

import android.app.Activity;
import android.app.Fragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android_app.gg.peter.madklub.OverlookActivity;
import android_app.gg.peter.madklub.R;
import android_app.gg.peter.madklub.db.DbContract;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link android_app.gg.peter.madklub.overlook.OverlookFragment.OverlookListener} interface
 * to handle interaction events.
 * Use the {@link OverlookFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OverlookFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final int GET_DINNERCLUBS = 701;
    private RecyclerView mRecyclerView;
    private CursorRecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private OverlookListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment OverlookFragment.
     */
    public static OverlookFragment newInstance() {
        OverlookFragment fragment = new OverlookFragment();
        Bundle args = new Bundle();
//        fragment.setArguments(args);
        return fragment;
    }

    public OverlookFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_overlook_cardview, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_calendar:
                mListener.selectCalendarView();
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
        View rootView = inflater.inflate(R.layout.overlook_default, container, false);
        // Inflate the layout for this fragment
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.overlook_recycler);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new OverlookAdapter((OverlookActivity)getActivity(),mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
        getLoaderManager().initLoader(GET_DINNERCLUBS,null,this);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OverlookListener) activity;
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
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {DbContract.DinnerClubs._ID,
            DbContract.DinnerClubs.date,
            DbContract.DinnerClubs.mainCourseId,
            DbContract.DinnerClubs.sideCourseId,
            DbContract.Users.name,
            DbContract.DinnerClubs.youParticipating};
        String uri = DbContract.CONTENT_PREFIX+"/"+DbContract.DinnerClubs.URI_TAG_DINNERCLUB_WITH_COOK_NAME;
        return new CursorLoader(getActivity().getApplicationContext(), Uri.parse(uri),projection,null,null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if(cursor != null && cursor.moveToFirst()){
            mAdapter.changeCursor(cursor);
            mAdapter.notifyDataSetChanged();
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
    public interface OverlookListener {
        public void selectCalendarView();
//        public void onFragmentInteraction(Uri uri);
    }
}
