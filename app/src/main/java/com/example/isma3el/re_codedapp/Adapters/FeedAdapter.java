package com.example.isma3el.re_codedapp.Adapters;

import android.app.Activity;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.isma3el.re_codedapp.Models.FeedCard;
import com.example.isma3el.re_codedapp.R;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * Created by Recodedharran on 9.2.2018.
 */

public class FeedAdapter extends ArrayAdapter<FeedCard> {
    public FeedAdapter(Activity context, ArrayList<FeedCard> cards) {
        super(context, 0, cards);
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.feed_card_item, parent, false);
        }

        FeedCard feedCard = getItem(position);

        RelativeLayout postTypeRelativeLayout = listItemView.findViewById(R.id.post_type_relative_layout);

        TextView postTypeTextView = listItemView.findViewById(R.id.post_type_text_view);

        int whichPostType = feedCard.getPostType();
        if (whichPostType == 1) {

            postTypeRelativeLayout.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.primary_dark));
            postTypeTextView.setText("STATUS");

        } else {

            postTypeRelativeLayout.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.primary));
            postTypeTextView.setText("PROGRESS");

        }

        ImageView imagePost = listItemView.findViewById(R.id.card_image_view);

        boolean isPhoto = feedCard.getImage() != null;
        if (isPhoto) {
            imagePost.setImageResource(Integer.parseInt(feedCard.getImage()));
        } else {
            imagePost.setVisibility(View.GONE);
        }


        TextView addPostText = listItemView.findViewById(R.id.card_text_view);
        addPostText.setText(feedCard.getText());


        return listItemView;
    }

}