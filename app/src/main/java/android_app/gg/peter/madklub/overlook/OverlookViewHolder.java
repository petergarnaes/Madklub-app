package android_app.gg.peter.madklub.overlook;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import android_app.gg.peter.madklub.R;

/**
 * Created by peter on 2/21/15.
 */
public class OverlookViewHolder extends RecyclerView.ViewHolder {
    public ImageView dish;
    public TextView date;
    public TextView menu;
    public TextView cook;
    public Button participatingButton;
    public Button notParticipatingButton;

    public OverlookViewHolder(View itemView) {
        super(itemView);
        dish = (ImageView) itemView.findViewById(R.id.overlook_image);
        date = (TextView) itemView.findViewById(R.id.date_text_view);
        menu = (TextView) itemView.findViewById(R.id.menu_text_view);
        cook = (TextView) itemView.findViewById(R.id.cook_text_view);
        participatingButton = (Button) itemView.findViewById(R.id.button_participating);
        notParticipatingButton = (Button) itemView.findViewById(R.id.button_not_participating);
    }
}
