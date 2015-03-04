package android_app.gg.peter.madklub.overlook;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.DateFormat;
import java.util.Locale;

import android_app.gg.peter.madklub.OverlookActivity;
import android_app.gg.peter.madklub.R;
import android_app.gg.peter.madklub.dinnerclub_detail.DinnerclubDetailActivity;

/**
 * Created by peter on 2/21/15.
 */
public class OverlookAdapter extends CursorRecyclerViewAdapter<OverlookViewHolder> {
    private OverlookActivity mContext;
    private RecyclerView mRecyclerview;

    public OverlookAdapter(OverlookActivity context,RecyclerView recyclerView) {
        super(context,null);
        mRecyclerview = recyclerView;
        mContext = context;
    }

    @Override
    public OverlookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.overlook_cardview, parent, false);
        final OverlookViewHolder viewHolder = new OverlookViewHolder(v);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DinnerclubDetailActivity.launchFromOverlook(mContext, v.findViewById(R.id.overlook_image),
                        "bob", v.findViewById(R.id.table_cook_course), viewHolder.menu.getText().toString(),
                        viewHolder.cook.getText().toString(), viewHolder.date.getText().toString(),
                        (viewHolder.participatingButton.isSelected()));
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final OverlookViewHolder viewHolder, Cursor cursor) {
        // Cursor column 0 is _id
        // Cursor column 1 is the date text
        String dateText = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault()).format(cursor.getString(1));
        viewHolder.date.setText(dateText);
        // Cursor column 2 is the main course and column 3 is the side course
        viewHolder.menu.setText(cursor.getString(2)+" med "+cursor.getString(3));
        // Cursor column 4 is the cooks full name
        viewHolder.cook.setText(cursor.getString(4));
        // Cursor column 5 is the boolean for wether user participates
        boolean participates = (cursor.getInt(5)==1);
        viewHolder.participatingButton.setSelected(participates);
        viewHolder.participatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.participatingButton.setSelected(true);
                viewHolder.notParticipatingButton.setSelected(false);
            }
        });
        viewHolder.notParticipatingButton.setSelected(!participates);
        viewHolder.notParticipatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.notParticipatingButton.setSelected(true);
                viewHolder.participatingButton.setSelected(false);
            }
        });
//        holder.dish.setImageDrawable(mContext.getResources().getDrawable(R.drawable.noodles));
    }
}
