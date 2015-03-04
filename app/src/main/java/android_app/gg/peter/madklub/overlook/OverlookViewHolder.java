package android_app.gg.peter.madklub.overlook;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import android_app.gg.peter.madklub.R;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by peter on 2/21/15.
 */
public class OverlookViewHolder extends RecyclerView.ViewHolder {
    @InjectView(R.id.overlook_image) ImageView dish;
    @InjectView(R.id.date_text_view) TextView date;
    @InjectView(R.id.menu_text_view) TextView menu;
    @InjectView(R.id.cook_text_view) TextView cook;
    @InjectView(R.id.button_participating) Button participatingButton;
    @InjectView(R.id.button_not_participating) Button notParticipatingButton;

    public OverlookViewHolder(View itemView) {
        super(itemView);
        ButterKnife.inject(this, itemView);
    }
}
