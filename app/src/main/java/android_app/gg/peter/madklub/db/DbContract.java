package android_app.gg.peter.madklub.db;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by peter on 2/27/15.
 */
public class DbContract {
    public static final String AUTHORITY = "android_app.gg.peter.madklub.provider";
    public static final String CONTENT_PREFIX = "content://"+AUTHORITY;
    public DbContract(){}

    public static abstract class DinnerClubs implements BaseColumns {
        //Meta data
        public static final Uri CONTENT_URI = Uri.parse(CONTENT_PREFIX + "/" + Courses.TABLE_NAME);
        public static final String TABLE_NAME = "DinnerClub";
        public static final String URI_TAG_DINNERCLUB_QUERY = "DinnerClubQuery";
        //Fields
        public static final String date = "date";
        public static final String courseId = "courseId";
        public static final String userCookId = "userCookId";
        public static final String isShopped = "isShopped";
        public static final String youParticipating = "youParticipating";
        //Statements
        public static final String CREATE_SQL_TABLE = "CREATE TABLE "+ DinnerClubs.TABLE_NAME+" ("+
                _ID+" INTEGER PRIMARY KEY, "+
                date+" TEXT NOT NULL, "+
                courseId+" INTEGER NOT NULL, "+
                userCookId+" INTEGER NOT NULL, "+
                isShopped+"INTEGER DEFAULT 0, "+
                youParticipating+"INTEGER DEFAULT 0, " +
                "FOREIGN KEY ("+courseId+") REFERENCES "+Courses.TABLE_NAME+"("+Courses._ID+"), "+
                "FOREIGN KEY ("+userCookId+") REFERENCES "+Users.TABLE_NAME+"("+Users._ID+"));";
        public static final String DELETE_SQL_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME+";";
    }

    public static abstract class Courses implements BaseColumns {
        //Meta data
        public static final Uri CONTENT_URI = Uri.parse(CONTENT_PREFIX + "/" + Courses.TABLE_NAME);
        public static final String TABLE_NAME = "Course";
        public static final String URI_TAG_COURSES_QUERY = "allCourses";
        public static final int MAIN_COURSE_ID = 0;
        public static final int SIDE_COURSE_ID = 1;
        //Fields
        public static final String courseTypeId = "courseTypeId";
        public static final String courseName = "courseName";
        public static final String imageUrlTag = "imageUrlTag";
        //Statements
        public static final String CREATE_SQL_TABLE = "CREATE TABLE "+ Courses.TABLE_NAME+" ("+
                _ID+" INTEGER PRIMARY KEY, "+
                courseTypeId+" INTEGER NOT NULL, "+
                imageUrlTag+" TEXT, "+
                courseName+" TEXT NOT NULL);";
        public static final String DELETE_SQL_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME+";";
    }

    public static abstract class Users implements BaseColumns {
        //Meta data
        public static final Uri CONTENT_URI = Uri.parse(CONTENT_PREFIX + "/" + Users.TABLE_NAME);
        public static final String TABLE_NAME = "Users";
        public static final String URI_TAG_USER_QUERY = "userQuery";
        // Fields
        public static final String name = "name";
        //Statements
        public static final String CREATE_SQL_TABLE = "CREATE TABLE "+Users.TABLE_NAME+" ("+
                _ID+" INTEGER PRIMARY KEY,"+
//                courseTypeId+" INTEGER,"+
                name+" TEXT);";
        public static final String DELETE_SQL_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME+";";
    }

    public static abstract class DinnerClubUsers implements BaseColumns {
        //Meta data
        public static final Uri CONTENT_URI = Uri.parse(CONTENT_PREFIX + "/" + DinnerClubUsers.TABLE_NAME);
        public static final String TABLE_NAME = "DinnerClubUsers";
        public static final String URI_TAG_JOIN_DINNERCLUB_USERS = "joinDinnerClubUsers";
        public static final String URI_TAG_DINNERCLUB_USER_QUERY = "dinnerclubQuery";
        //Fields
        public static final String dinnerClubId = "dinnerClubId";
        public static final String userId = "userId";
        //Statements
        public static final String CREATE_SQL_TABLE = "CREATE TABLE "+ DinnerClubUsers.TABLE_NAME+" ("+
                _ID+" INTEGER PRIMARY KEY, "+
                dinnerClubId+" INTEGER NOT NULL, "+
                userId+" INTEGER NOT NULL, "+
                "FOREIGN KEY ("+dinnerClubId+") REFERENCES "+DinnerClubs.TABLE_NAME+"("+ DinnerClubs._ID+"),"+
                "FOREIGN KEY ("+userId+") REFERENCES "+Users.TABLE_NAME+"("+Users._ID+"));";
        public static final String DELETE_SQL_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME+";";
    }
}
