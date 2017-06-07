package example.android.fizzy.imagegallery.ui;
import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import example.android.fizzy.imagegallery.R;
import example.android.fizzy.imagegallery.adapter.RVadapter;
import example.android.fizzy.imagegallery.api.ApiUtil;
import example.android.fizzy.imagegallery.api.FlickerApi;
import example.android.fizzy.imagegallery.model.Item;
import example.android.fizzy.imagegallery.model.Photo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    StaggeredGridLayoutManager sglm;
    FlickerApi mFlickerService;
    RVadapter rVadapter;
    ArrayList<Item> mItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFlickerService = ApiUtil.getPhoto();

        //set up RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        sglm = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(sglm);

        rVadapter = new RVadapter(mItems, getApplicationContext());
        recyclerView.setAdapter(rVadapter);

        loadImages();

        rVadapter.setOnItemClickListener(onItemClickListener);

    }

    public void loadImages() {
        String format = "json";
        Integer callback = 1;

        mFlickerService.getPhotos(format, callback).enqueue(new Callback<Photo>() {
            @Override
            public void onResponse(Call<Photo> call, Response<Photo> response) {

                if(response.isSuccessful()) {
                    Log.d("result", response.body().toString());
                    rVadapter.updatePhotos((ArrayList<Item>) response.body().getItems());
                    Log.d("MainActivity", "posts loaded from API");
                } else {
                    int statusCode  = response.code();
                    // handle request errors depending on status code
                }
            }

            @Override
            public void onFailure(Call<Photo> call, Throwable t) {
                Log.d("MainActivity", "error loading from API");

            }
        });
    }


    RVadapter.OnItemClickListener onItemClickListener = new RVadapter.OnItemClickListener() {

        @Override
        public void onItemClick(View view, int position) {

            ImageView placeImage = (ImageView) view.findViewById(R.id.image);

            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                    MainActivity.this, placeImage, "image");

            Intent intent = new Intent(getApplicationContext(), FullscreenImageActivity.class);
            ArrayList<Item> mItems = rVadapter.getItems();

            intent.putExtra("image", mItems.get(position).getMedia().getM());
            startActivity(intent, options.toBundle());

            //ImageView placeImage = (ImageView) view.findViewById(R.id.image);

        }
    };
}