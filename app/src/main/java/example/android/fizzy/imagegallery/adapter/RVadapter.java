package example.android.fizzy.imagegallery.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
    Context mContext;
    List<Photo> photoList;
    ArrayList<Item> mItems;
    static OnItemClickListener mItemClickListener;

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
        ///Photo photo = photoList.get(i);
        //viewHolder.photoTitle.setText(mItems.get(i).getTitle());
        String url = String.valueOf(mItems.get(i).getMedia().getM());
        Picasso.with(viewHolder.context).load(url).into(viewHolder.photoImage);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void updatePhotos(ArrayList<Item> photos) {
        mItems = photos;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public View mView;
        public ImageView photoImage;
        public TextView photoTitle;
        public Context context;
        public LinearLayout placeholder;

        public ViewHolder(final View itemView) {
            super(itemView);
            mView = itemView;
            photoImage = (ImageView) itemView.findViewById(R.id.image);
            //photoTitle = (TextView) itemView.findViewById(R.id.title);
            context = itemView.getContext();
            placeholder = (LinearLayout) itemView.findViewById(R.id.main);
            photoImage.setOnClickListener(this);
        }

        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(itemView, getLayoutPosition());
                System.out.print("test");

            }
        }

    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        RVadapter.mItemClickListener = mItemClickListener;
    }
}
