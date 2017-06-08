package example.android.fizzy.imagegallery.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import example.android.fizzy.imagegallery.R;

/**
 * Created by Fizzy on 07/06/2017.
 * Activity which loads after clicking an image
 */

public class FullscreenImageActivity extends AppCompatActivity {

    String image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fullscreen_activity);

        //Declare Image and Text Views from layout
        ImageView imageView = (ImageView) findViewById(R.id.image);
        TextView textView = (TextView) findViewById(R.id.title);

        //Get Image and Title from Main Activity
        String title = getIntent().getStringExtra("title");
        image = getIntent().getStringExtra("image");

        //Set Image and Title to Views
        Picasso.with(getApplicationContext()).load(String.valueOf(image)).into(imageView);
        textView.setText(title);


    }

}
