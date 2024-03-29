package android_app.gg.peter.madklub.network.json_representation;

import java.util.List;

/**
 * Created by peter on 2/21/15.
 */
public class DinnerClub {
    private int id;
    private String date;
    private Course course;
    private User cook;
    private boolean shopped;
    private boolean paticipating;
    private float price;
    private List<User> participants;

    public DinnerClub() {}

    public String getDate() {
        return date;
    }

    public User getCook() {
        return cook;
    }

    public boolean isShopped() {
        return shopped;
    }

    public boolean isPaticipating() {
        return paticipating;
    }

    public Course getCourse() {
        return course;
    }

    public float getPrice() {
        return price;
    }
    public List<User> getParticipants() {
        return participants;
    }

    public int getId() {
        return id;
    }
}
