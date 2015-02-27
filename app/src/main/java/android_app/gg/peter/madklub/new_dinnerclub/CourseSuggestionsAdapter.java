package android_app.gg.peter.madklub.new_dinnerclub;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

/**
 * Created by peter on 2/27/15.
 */
public class CourseSuggestionsAdapter extends CursorAdapter {
    public CourseSuggestionsAdapter(Context context, Cursor c) {
        // Note, play around with setting requery to true
        super(context, c, false);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return null;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

    }
}
