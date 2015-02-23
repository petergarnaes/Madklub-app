package android_app.gg.peter.madklub.network.json_representation;

import java.util.Calendar;
import java.util.List;

/**
 * Created by peter on 2/21/15.
 */
public class DinnerClubDetail extends DinnerClub {
    private List<Person> participants;

    public DinnerClubDetail(Calendar date,String cook,String course) {
        super(date,cook,course);
    }

    public List<Person> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Person> participants) {
        this.participants = participants;
    }
}
