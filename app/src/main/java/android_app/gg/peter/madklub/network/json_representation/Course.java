package android_app.gg.peter.madklub.network.json_representation;

/**
 * Created by peter on 2/27/15.
 */
public class Course {
    // For image purposes
    private int courseID = 0;
    // Course image is based of
    private String mainCourse = "";
    // Salat, rice etc.
    private String sideCourse = "";
    // How many days since it was served last
    private int lastCooked;

    public Course(int courseID,String mainCourse,String sideCourse){
        this.courseID = courseID;
        this.mainCourse = mainCourse;
        this.sideCourse = sideCourse;
    }

    public Course(String mainCourse,String sideCourse){
        this.mainCourse = mainCourse;
        this.sideCourse = sideCourse;
    }

    public Course(int courseID,String mainCourse){
        this.courseID = courseID;
        this.mainCourse = mainCourse;
    }

    public Course(String mainCourse){
        this.mainCourse = mainCourse;
    }

    public int getCourseID() {
        return courseID;
    }

    public String getMainCourse() {
        return mainCourse;
    }

    public String getSideCourse() {
        return sideCourse;
    }

    public int getLastCooked() {
        return lastCooked;
    }
}
