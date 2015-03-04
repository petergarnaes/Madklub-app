package android_app.gg.peter.madklub.network.synchronizers;

import android.content.ContentProviderClient;
import android.content.ContentProviderOperation;

import java.util.ArrayList;

import android_app.gg.peter.madklub.network.MadklubService;

/**
 * Created by peter on 3/4/15.
 */
public class DinnerclubUploadSynchronizer implements MadklubUploadSynchronizer {
    @Override
    public ArrayList<ContentProviderOperation> uploadAndParse(ContentProviderClient provider, MadklubService service) {
        // If new to server then update ID, existsOnServer and syncedWithServer field,
        // if just update then update the syncedWithServer field
        return null;
    }
}
