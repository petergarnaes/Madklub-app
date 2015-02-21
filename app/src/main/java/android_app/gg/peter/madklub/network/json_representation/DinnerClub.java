package android_app.gg.peter.madklub.network.json_representation;

import java.util.Calendar;

/**
 * Created by peter on 2/21/15.
 */
public class DinnerClub {
    private Calendar date;
    private String mainCourse;
    private String sideCourse;
    private Person cook;
    private boolean shopped;
    private boolean paticipating;

    public DinnerClub(Calendar date) {
        this.date = date;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getMainCourse() {
        return mainCourse;
    }

    public void setMainCourse(String mainCourse) {
        this.mainCourse = mainCourse;
    }

    public String getSideCourse() {
        return sideCourse;
    }

    public void setSideCourse(String sideCourse) {
        this.sideCourse = sideCourse;
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
}
