package example.android.fizzy.imagegallery.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import example.android.fizzy.imagegallery.R;
import example.android.fizzy.imagegallery.model.Item;
import example.android.fizzy.imagegallery.model.Photo;

/**
 * Created by Fizzy on 06/06/2017.
 */

public class RVadapter extends RecyclerView.Adapter<RVadapter.ViewHolder> {

    List<Photo> photoList;
    List<Item> mItems;
    Context mContext;

    public RVadapter(ArrayList<Item> mItems, Context context ) {
        this.mItems = mItems;
        this.mContext = context;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        //Photo photo = photoList.get(i);
        viewHolder.photoTitle.setText(mItems.get(i).getTitle());
        Picasso.with(viewHolder.context).load(String.valueOf(mItems.get(i).getMedia())).into(viewHolder.photoImage);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void updatePhotos(List<Item> photos) {
        mItems = photos;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView photoImage;
        public TextView photoTitle;
        public Context context;

        public ViewHolder(final View itemView) {
            super(itemView);
            photoImage = (ImageView) itemView.findViewById(R.id.image);
            photoTitle = (TextView) itemView.findViewById(R.id.title);
            context = itemView.getContext();
        }

    }
}
