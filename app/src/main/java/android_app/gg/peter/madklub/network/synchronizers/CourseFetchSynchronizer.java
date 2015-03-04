package android_app.gg.peter.madklub.network.synchronizers;

import android.content.ContentProviderClient;
import android.content.ContentProviderOperation;
import android.content.ContentValues;
import android.os.RemoteException;

import java.util.ArrayList;
import java.util.List;

import android_app.gg.peter.madklub.db.DbContract;
import android_app.gg.peter.madklub.network.MadklubService;
import android_app.gg.peter.madklub.network.json_representation.Course;

/**
 * Created by peter on 3/4/15.
 */
public class CourseFetchSynchronizer implements MadklubFetchSynchronizer {
    @Override
    public ArrayList<ContentProviderOperation> fetchAndParse(ContentProviderClient client,
                                                             MadklubService madklubService) throws RemoteException {
        // Use client to clear table so we can reinsert all the objects
        client.delete(DbContract.Courses.CONTENT_URI,"1",null);
        // Use retrofit MadklubService to fetch objects, and form inserts
        ArrayList<ContentProviderOperation> returnVal = new ArrayList<>();
        List<Course> courses = madklubService.getCourses();
        for(Course course : courses){
            ContentValues vals = new ContentValues();
            vals.put(DbContract.Courses._ID,course.getCourseID());
            vals.put(DbContract.Courses.existsOnServer,1);
            vals.put(DbContract.Courses.syncedWithServer,1);
            vals.put(DbContract.Courses.courseName,course.getCourse());
            vals.put(DbContract.Courses.courseTypeId,course.getCourseTypeId());
            //TODO image
            vals.put(DbContract.Courses.imageUrlTag,"");
            returnVal.add(ContentProviderOperation.newInsert(DbContract.DinnerClubs.CONTENT_URI).withValues(vals).build());
        }
        return returnVal;
    }
}
