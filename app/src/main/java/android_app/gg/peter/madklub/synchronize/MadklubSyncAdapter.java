package android_app.gg.peter.madklub.synchronize;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.Context;
import android.content.OperationApplicationException;
import android.content.SyncResult;
import android.os.Bundle;
import android.os.RemoteException;

import java.util.ArrayList;

import android_app.gg.peter.madklub.network.MadklubService;
import android_app.gg.peter.madklub.network.synchronizers.CourseFetchSynchronizer;
import android_app.gg.peter.madklub.network.synchronizers.DinnerclubFetchSynchronizer;
import android_app.gg.peter.madklub.network.synchronizers.DinnerclubUploadSynchronizer;
import retrofit.RestAdapter;

/**
 * Created by peter on 3/4/15.
 */
public class MadklubSyncAdapter extends AbstractThreadedSyncAdapter {
    public static final String SYNC_COURSES = "syncCourses";
    public static final String SYNC_DINNERCLUBS = "syncDinnerclubs";
    ContentResolver mContentResolver;
    /**
     * Set up the sync adapter
     */
    public MadklubSyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
        /*
         * If your app uses a content resolver, get an instance of it
         * from the incoming Context
         */
        mContentResolver = context.getContentResolver();
    }

    /**
     * Set up the sync adapter. This form of the
     * constructor maintains compatibility with Android 3.0
     * and later platform versions
     */
    public MadklubSyncAdapter(Context context,boolean autoInitialize,boolean allowParallelSyncs) {
        super(context, autoInitialize, allowParallelSyncs);
        /*
         * If your app uses a content resolver, get an instance of it
         * from the incoming Context
         */
        mContentResolver = context.getContentResolver();
    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority,
                              ContentProviderClient provider, SyncResult syncResult) {
        try {
            MadklubService madklubService = getMadklubService();
            final boolean uploadOnly = extras.getBoolean(ContentResolver.SYNC_EXTRAS_UPLOAD, false);
            ArrayList<ContentProviderOperation> batch = new ArrayList<>();
            if(uploadOnly){
                batch.addAll(new DinnerclubUploadSynchronizer().uploadAndParse(provider,madklubService));
            } else {
                final boolean getCourses = extras.getBoolean(SYNC_COURSES, true);
                final boolean getDinnerclubs = extras.getBoolean(SYNC_DINNERCLUBS, true);
                if(getCourses){
                    batch.addAll(new CourseFetchSynchronizer().fetchAndParse(provider,madklubService));
                }
                if(getDinnerclubs){
                    batch.addAll(new DinnerclubFetchSynchronizer().fetchAndParse(provider,madklubService));
                }
            }
            provider.applyBatch(batch);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (OperationApplicationException e) {
            e.printStackTrace();
        }
        //TODO on schedueled sync, perform both upload and fetch
    }

    private MadklubService getMadklubService(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://10.0.0.2:45670")
                .build();
        return restAdapter.create(MadklubService.class);
    }
}
