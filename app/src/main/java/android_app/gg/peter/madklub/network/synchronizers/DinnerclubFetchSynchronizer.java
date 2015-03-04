package android_app.gg.peter.madklub.network.synchronizers;

import android.content.ContentProviderClient;
import android.content.ContentProviderOperation;
import android.content.ContentValues;
import android.os.RemoteException;

import java.util.ArrayList;
import java.util.List;

import android_app.gg.peter.madklub.db.DbContract;
import android_app.gg.peter.madklub.network.MadklubService;
import android_app.gg.peter.madklub.network.json_representation.DinnerClub;

/**
 * Created by peter on 3/4/15.
 */
public class DinnerclubFetchSynchronizer implements MadklubFetchSynchronizer {
    @Override
    public ArrayList<ContentProviderOperation> fetchAndParse(ContentProviderClient client,
                                                             MadklubService madklubService) throws RemoteException {
        // Use client to clear table so we can reinsert all the objects
        client.delete(DbContract.DinnerClubs.CONTENT_URI,"1",null);
        // Use retrofit MadklubService to fetch objects, and form inserts
        ArrayList<ContentProviderOperation> returnVal = new ArrayList<>();
        List<DinnerClub> dinnerClubs = madklubService.getDinnerclubList();
        for(DinnerClub dinnerClub : dinnerClubs){
            ContentValues vals = new ContentValues();
            vals.put(DbContract.DinnerClubs._ID,dinnerClub.getId());
            vals.put(DbContract.DinnerClubs.existsOnServer,1);
            vals.put(DbContract.DinnerClubs.syncedWithServer,1);
            vals.put(DbContract.DinnerClubs._ID,dinnerClub.getDate());
            vals.put(DbContract.DinnerClubs.youParticipating,dinnerClub.isPaticipating());
            vals.put(DbContract.DinnerClubs.isShopped,dinnerClub.isShopped());
            vals.put(DbContract.DinnerClubs.price,dinnerClub.getPrice());
            returnVal.add(ContentProviderOperation.newInsert(DbContract.DinnerClubs.CONTENT_URI).withValues(vals).build());
            //TODO add cook if exists else update
            //TODO add participants if exists else update
        }
        return returnVal;
    }
}
