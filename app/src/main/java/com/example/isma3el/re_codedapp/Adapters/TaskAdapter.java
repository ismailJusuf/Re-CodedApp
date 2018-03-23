package com.example.isma3el.re_codedapp.Adapters;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.isma3el.re_codedapp.Models.FeedCard;
import com.example.isma3el.re_codedapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Recodedharran on 6.3.2018.
 */

public class TaskAdapter extends ArrayAdapter<FeedCard> {

    public TaskAdapter(Activity context, ArrayList<FeedCard> cards) {
        super(context, 0, cards);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = LayoutInflater.from(getContext()).inflate(R.layout.task_card_item, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }

        FeedCard feedCard = getItem(position);

        holder.postTypeRelativeLayout.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.primary_dark));
        holder.postTypeTextView.setText("Task");

        boolean isPhoto = feedCard.getImage() != null;
        if (isPhoto) {
            //imagePost.setImageResource(Integer.parseInt(feedCard.getImage()));
            holder.imagePost.setVisibility(View.VISIBLE);
            Picasso.get().load(feedCard.getImage()).into(holder.imagePost);

        } else {
            holder.imagePost.setVisibility(View.GONE);
        }

        holder.addPostText.setText(feedCard.getText());

        return view;
    }

    static class ViewHolder {

        @BindView(R.id.card_task_image_view)
        ImageView imagePost;
        @BindView(R.id.post_type_relative_layout)
        RelativeLayout postTypeRelativeLayout;
        @BindView(R.id.post_type_text_view)
        TextView postTypeTextView;
        @BindView(R.id.card_task_text_view)
        TextView addPostText;



        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}


/*


public class TaskAdapter extends ArrayAdapter<FeedCard> {
    public TaskAdapter(Activity context, ArrayList<FeedCard> cards) {
        super(context, 0, cards);
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.task_card_item, parent, false);
        }

        FeedCard feedCard = getItem(position);

        RelativeLayout postTypeRelativeLayout = listItemView.findViewById(R.id.post_type_relative_layout);

        TextView postTypeTextView = listItemView.findViewById(R.id.post_type_text_view);


        postTypeRelativeLayout.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.primary_dark));
        postTypeTextView.setText("Task");


        ImageView imagePost = listItemView.findViewById(R.id.card_task_image_view);

        boolean isPhoto = feedCard.getImage() != null;
        if (isPhoto) {
            //imagePost.setImageResource(Integer.parseInt(feedCard.getImage()));
            Picasso.get().load(feedCard.getImage()).into(imagePost);
        } else {
            imagePost.setVisibility(View.GONE);
        }


        TextView addPostText = listItemView.findViewById(R.id.card_task_text_view);
        addPostText.setText(feedCard.getText());


        return listItemView;
    }


}

*/

