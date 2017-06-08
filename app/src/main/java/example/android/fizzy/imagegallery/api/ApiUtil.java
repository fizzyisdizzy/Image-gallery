package example.android.fizzy.imagegallery.api;

/**
 * Created by Fizzy on 07/06/2017.
 * Wrapper for Flickr API
 */

public class ApiUtil {
    public static final String BASE_URL = "https://api.flickr.com/";

    public static FlickrApi getPhoto() {
        return RetrofitClient.getClient(BASE_URL).create(FlickrApi.class);
    }
}