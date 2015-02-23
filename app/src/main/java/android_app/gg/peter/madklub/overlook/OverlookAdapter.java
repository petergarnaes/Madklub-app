package android_app.gg.peter.madklub.overlook;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.DateFormat;
import java.util.Locale;

import android_app.gg.peter.madklub.R;
import android_app.gg.peter.madklub.network.json_representation.DinnerClub;

/**
 * Created by peter on 2/21/15.
 */
public class OverlookAdapter extends RecyclerView.Adapter<OverlookViewHolder> {
    private DinnerClub[] data;
    private Context mContext;

    public OverlookAdapter(Context context, DinnerClub[] data) {
        this.data = data;
        mContext = context;
    }

    @Override
    public OverlookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.overlook_cardview, parent, false);
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
        holder.participatingButton.setSelected(!dinnerClub.isPaticipating());
        holder.notParticipatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
