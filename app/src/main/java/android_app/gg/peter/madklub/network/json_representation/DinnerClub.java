package android_app.gg.peter.madklub.network.json_representation;

import java.util.Calendar;
import java.util.List;

/**
 * Created by peter on 2/21/15.
 */
public class DinnerClub {
    private int id;
    private Calendar date;
    private Course course;
    private Person cook;
    private boolean shopped;
    private boolean paticipating;
    private float price;
    private List<Person> participants;

    public DinnerClub(Calendar date,String cook,String course) {
        this.date = date;
        this.cook = new Person(cook);
        this.course = new Course(course);
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public Person getCook() {
        return cook;
    }

    public void setCook(Person cook) {
        this.cook = cook;
    }

    public boolean isShopped() {
        return shopped;
    }

    public void setShopped(boolean shopped) {
        this.shopped = shopped;
    }

    public boolean isPaticipating() {
        return paticipating;
    }

    public void setPaticipating(boolean paticipating) {
        this.paticipating = paticipating;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public float getPrice() {
        return price;
    }
    public List<Person> getParticipants() {
        return participants;
    }
}
