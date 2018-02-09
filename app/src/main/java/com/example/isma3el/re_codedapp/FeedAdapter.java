package com.example.isma3el.re_codedapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Recodedharran on 9.2.2018.
 */

public class FeedAdapter extends ArrayAdapter<feedCard> {
    public FeedAdapter(Activity context, ArrayList<feedCard> resturants) {
        super(context, 0, resturants);
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.card_view_news, parent, false);
        }

        // Get the {@link Tour} object located at this position in the list
        feedCard currentTour = getItem(position);

        ImageView imagePost = listItemView.findViewById(R.id.add_image_post);
        imagePost.setImageResource(currentTour.getImagePostId());

        // ImageView personalIcon = listItemView.findViewById(R.id.personal_name_icon);
        //   personalIcon.setImageResource(currentTour.getImagPersonalIcontId());

        TextView personalNameText = listItemView.findViewById(R.id.personal_name_text);
        personalNameText.setText(currentTour.getPersonalName());

        TextView addPostText = listItemView.findViewById(R.id.add_post_text);
        addPostText.setText(currentTour.getAddPostText());

        // ImageView heartIcon = listItemView.findViewById(R.id.emoji_icon_heart);
        // heartIcon.setImageResource(currentTour.getImagHeartIconId());
        TextView heartText = listItemView.findViewById(R.id.heart_text);
        heartText.setText(currentTour.getNumberHeartText());

        // ImageView happyIcon = listItemView.findViewById(R.id.emoji_icon_happy);
        // happyIcon.setImageResource(currentTour.getImagHappyIconId());
        TextView happyText = listItemView.findViewById(R.id.happy_text);
        happyText.setText(currentTour.getNumberHappyText());

        //  ImageView winkIcon = listItemView.findViewById(R.id.emoji_icon_wink);
        //  winkIcon.setImageResource(currentTour.getImagWinkIconId());
        TextView winkText = listItemView.findViewById(R.id.wink_text);
        winkText.setText(currentTour.getNumberWinkText());

        //  ImageView nerdIcon = listItemView.findViewById(R.id.emoji_icon_nerd);
        //  nerdIcon.setImageResource(currentTour.getImagNerdIconId());
        TextView nerdText = listItemView.findViewById(R.id.nerd_text);
        nerdText.setText(currentTour.getNumberNerdText());

        //  ImageView inloveIcon = listItemView.findViewById(R.id.emoji_icon_inlove);
        // inloveIcon.setImageResource(currentTour.getImagInloveIconId());
        TextView inloveText = listItemView.findViewById(R.id.inlove_text);
        inloveText.setText(currentTour.getNumberInloveText());

        //  ImageView thumbsIcon = listItemView.findViewById(R.id.emoji_icon_thumbs);
        //  thumbsIcon.setImageResource(currentTour.getImagThumbsIconId());
        TextView thumbsText = listItemView.findViewById(R.id.thumbs_text);
        thumbsText.setText(currentTour.getNumberThumbsText());

        return listItemView;
    }

}
