package android_app.gg.peter.madklub.new_dinnerclub;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import android_app.gg.peter.madklub.R;

/**
 * Created by peter on 2/27/15.
 */
public class CourseSuggestionsAdapter extends CursorAdapter {
    protected LayoutInflater inflater;
    public CourseSuggestionsAdapter(Context context, Cursor c) {
        // Note, play around with setting requery to true
        super(context, c, false);
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        CourseSuggestionHolder holder = new CourseSuggestionHolder();
        View convertView = inflater.inflate(R.layout.list_course_suggestion,parent,false);
        holder.course = (TextView)convertView.findViewById(R.id.courseName);
        convertView.setTag(holder);
        return convertView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        CourseSuggestionHolder holder = (CourseSuggestionHolder)view.getTag();
        holder.course.setText(cursor.getString(0));
    }

    private class CourseSuggestionHolder {
        TextView course;
    }
}
