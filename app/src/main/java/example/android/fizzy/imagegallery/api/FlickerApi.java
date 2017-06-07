package example.android.fizzy.imagegallery.api;

import example.android.fizzy.imagegallery.model.Photo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Fizzy on 07/06/2017.
 */

public interface FlickerApi {

    @GET("/services/feeds/photos_public.gne")
    Call<Photo> getPhotos(@Query("format") String format, @Query("nojsoncallback") int callback);

}