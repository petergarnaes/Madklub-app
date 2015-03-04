package android_app.gg.peter.madklub.network.json_representation;

/**
 * Created by peter on 2/27/15.
 */
public class Course {
    // For image purposes
    private int courseID = 0;
    // Course image is based of
    private String course = "";
    // Is it main or side course?
    private int courseTypeId;
    // How many days since it was served last
    private int lastCooked;

    public Course(String course){
        this.course = course;
    }

    public Course(int courseID,String course){
        this.courseID = courseID;
        this.course = course;
    }

    public int getCourseID() {
        return courseID;
    }

    public String getCourse() {
        return course;
    }

    public int getLastCooked() {
        return lastCooked;
    }

    public int getCourseTypeId() {
        return courseTypeId;
    }
}
