package android_app.gg.peter.madklub.overlook;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.DateFormat;
import java.util.Locale;

import android_app.gg.peter.madklub.OverlookActivity;
import android_app.gg.peter.madklub.R;
import android_app.gg.peter.madklub.dinnerclub_detail.DinnerclubDetailActivity;
import android_app.gg.peter.madklub.network.json_representation.DinnerClub;

/**
 * Created by peter on 2/21/15.
 */
public class OverlookAdapter extends RecyclerView.Adapter<OverlookViewHolder> {
    private OverlookActivity mContext;
    private DinnerClub[] data;
    private RecyclerView mRecyclerview;

    public OverlookAdapter(OverlookActivity context,RecyclerView recyclerView, DinnerClub[] data) {
        this.data = data;
        mRecyclerview = recyclerView;
        mContext = context;
    }

    @Override
    public OverlookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.overlook_cardview, parent, false);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = mRecyclerview.getChildPosition(v);
                DinnerClub d = data[i];
                DinnerclubDetailActivity.launchFromOverlook(mContext, v.findViewById(R.id.overlook_image),
                        "bob", v.findViewById(R.id.table_cook_course), d.getMainCourse(),
                        d.getCook().getFirstName(), DateFormat.getDateInstance(DateFormat.SHORT,
                                Locale.getDefault()).format(d.getDate().getTime()), d.isPaticipating());
//                Intent intent = new Intent(mContext, DinnerclubDetailActivity.class);
//                mContext.startActivity(intent);
            }
        });
        return new OverlookViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final OverlookViewHolder holder, int position) {
        final DinnerClub dinnerClub = data[position];
        String dateText = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault()).format(dinnerClub.getDate().getTime());
        holder.date.setText(dateText);
        holder.menu.setText(dinnerClub.getMainCourse());
        holder.cook.setText(dinnerClub.getCook().getFirstName());
        holder.participatingButton.setSelected(dinnerClub.isPaticipating());
        holder.participatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dinnerClub.setPaticipating(true);
                holder.participatingButton.setSelected(true);
                holder.notParticipatingButton.setSelected(false);
            }
        });
        holder.notParticipatingButton.setSelected(!dinnerClub.isPaticipating());
        holder.notParticipatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dinnerClub.setPaticipating(false);
                holder.notParticipatingButton.setSelected(true);
                holder.participatingButton.setSelected(false);
            }
        });
//        holder.dish.setImageDrawable(mContext.getResources().getDrawable(R.drawable.noodles));
    }

    @Override
    public int getItemCount() {
        return data.length;
    }
}
