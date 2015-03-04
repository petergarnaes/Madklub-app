package android_app.gg.peter.madklub.network;

import java.util.List;

import android_app.gg.peter.madklub.network.json_representation.Course;
import android_app.gg.peter.madklub.network.json_representation.DinnerClub;
import android_app.gg.peter.madklub.network.json_representation.Person;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by peter on 3/4/15.
 */
public interface MadklubService {
    @GET("/courses/list/{typeId}")
    public List<Course> getCourseByTypeId(@Path("typeId") int typeId);

    @GET("/dinnerclub/list")
    public List<DinnerClub> getDinnerclubList();

    @GET("/participants/list/{dinnerclubID}")
    public List<Person> getParticipantsForDinnerclub(@Path("dinnerclubID") int dinnerclubID);

    /**
     * Will create a new dinnerclub on the server, will get the true ID returned to be updated
     * @param dinnerClubs DinnerClubs that are new and should be synced
     */
    @POST("/dinnerclub/new")
    public TrueID newDinnerclub(@Body List<DinnerClub> dinnerClubs);

    /**
     * Will update dinnerclubs on the server
     * @param dinnerClubs DinnerClubs that are changed and should be synced
     */
    @POST("/dinnerclub/update")
    public void updateDinnerclub(@Body List<DinnerClub> dinnerClubs);
}
