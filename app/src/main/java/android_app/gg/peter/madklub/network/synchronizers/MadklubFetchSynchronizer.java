package android_app.gg.peter.madklub.network.synchronizers;

import android.content.ContentProviderOperation;

import java.util.ArrayList;

import android_app.gg.peter.madklub.network.MadklubService;

/**
 * Created by peter on 3/4/15.
 */
public interface MadklubFetchSynchronizer {
    public ArrayList<ContentProviderOperation> fetchAndParse(MadklubService madklubService);
}
