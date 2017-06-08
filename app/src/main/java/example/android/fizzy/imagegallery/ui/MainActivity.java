package example.android.fizzy.imagegallery.ui;
import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import java.util.ArrayList;
import example.android.fizzy.imagegallery.R;
import example.android.fizzy.imagegallery.adapter.RVAdapter;
import example.android.fizzy.imagegallery.api.ApiUtil;
import example.android.fizzy.imagegallery.api.FlickrApi;
import example.android.fizzy.imagegallery.model.Photo;
import example.android.fizzy.imagegallery.model.PhotoList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Fizzy on 06/06/2017.
 * Activity which loads and displays images from Flickr API.
 */


public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    StaggeredGridLayoutManager sglm;
    FlickrApi mFlickerService;
    RVAdapter rVadapter;
    ArrayList<Photo> mItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFlickerService = ApiUtil.getPhoto();

        //Setup RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        sglm = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(sglm);

        //Set Adapter
        rVadapter = new RVAdapter(mItems, getApplicationContext());
        recyclerView.setAdapter(rVadapter);

        //Setup OnItemClickListener
        rVadapter.setOnItemClickListener(onItemClickListener);
        loadImages();


    }

    //Method to get put images into Adapter
    public void loadImages() {
        String format = "json";
        Integer callback = 1;

        mFlickerService.getPhotos(format, callback).enqueue(new Callback<PhotoList>() {
            @Override
            public void onResponse(Call<PhotoList> call, Response<PhotoList> response) {

                if(response.isSuccessful()) {
                    Log.d("result", response.body().toString());
                    rVadapter.updatePhotos((ArrayList<Photo>) response.body().getItems());
                    Log.d("MainActivity", "posts loaded from API");
                } else {
                    int statusCode  = response.code();
                    // handle request errors depending on status code
                }
            }

            @Override
            public void onFailure(Call<PhotoList> call, Throwable t) {
                Log.d("MainActivity", "error loading from API");

            }
        });
    }

    //Action to take once an image is clicked
    RVAdapter.OnItemClickListener onItemClickListener = new RVAdapter.OnItemClickListener() {

        @Override
        public void onItemClick(View view, int position) {

            ImageView placeImage = (ImageView) view.findViewById(R.id.image);

            //Transitions
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                    MainActivity.this, placeImage, "image");

            //Intent to go to load FullscreenImageActivity
            Intent intent = new Intent(getApplicationContext(), FullscreenImageActivity.class);
            ArrayList<Photo> mItems = rVadapter.getItems();

            //Send data to FullscreenImageActivity
            intent.putExtra("image", mItems.get(position).getMedia().getM());
            intent.putExtra("title", mItems.get(position).getTitle());
            startActivity(intent, options.toBundle());
        }
    };

}