package example.android.fizzy.imagegallery.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import example.android.fizzy.imagegallery.R;

/**
 * Created by Fizzy on 07/06/2017.
 */

public class FullscreenImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fullscreen_activity);

        ImageView imageView = (ImageView) findViewById(R.id.image);

        //String title = getIntent().getStringExtra("title");
        String image = getIntent().getStringExtra("image");
        Picasso.with(getApplicationContext()).load(String.valueOf(image)).into(imageView);

    }
}
