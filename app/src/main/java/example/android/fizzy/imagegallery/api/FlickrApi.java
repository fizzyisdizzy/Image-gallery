package example.android.fizzy.imagegallery.api;

import example.android.fizzy.imagegallery.model.PhotoList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Fizzy on 07/06/2017.
 * Make request to Flickr API using Retrofit
 */

public interface FlickrApi {
    @GET("/services/feeds/photos_public.gne")
    Call<PhotoList> getPhotos(@Query("format") String format, @Query("nojsoncallback") int callback);
}