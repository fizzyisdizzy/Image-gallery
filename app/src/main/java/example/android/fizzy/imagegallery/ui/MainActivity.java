package example.android.fizzy.imagegallery.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import example.android.fizzy.imagegallery.R;
import example.android.fizzy.imagegallery.adapter.RVadapter;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    StaggeredGridLayoutManager sglm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set up RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        sglm = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(sglm);

        RVadapter rVadapter = new RVadapter();
        recyclerView.setAdapter(rVadapter);


    }
}
