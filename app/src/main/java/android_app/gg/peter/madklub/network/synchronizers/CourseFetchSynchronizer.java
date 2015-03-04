package android_app.gg.peter.madklub.network.synchronizers;

import android.content.ContentProviderClient;
import android.content.ContentProviderOperation;

import java.util.ArrayList;

import android_app.gg.peter.madklub.network.MadklubService;

/**
 * Created by peter on 3/4/15.
 */
public class CourseFetchSynchronizer implements MadklubFetchSynchronizer {
    @Override
    public ArrayList<ContentProviderOperation> fetchAndParse(ContentProviderClient client,
                                                             MadklubService madklubService) {
        // Use client to clear table so we can reinsert all the objects
        // Use retrofit MadklubService to fetch objects, and form inserts
        return null;
    }
}
