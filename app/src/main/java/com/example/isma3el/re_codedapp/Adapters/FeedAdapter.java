package com.example.isma3el.re_codedapp.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.feed_card_item, parent, false);
        }

        // Get the {@link Tour} object located at this position in the list
        FeedCard currentTour = getItem( position);

        ImageView imagePost = listItemView.findViewById(R.id.card_image_view );
        imagePost.setImageResource(currentTour.getImage());

        // ImageView personalIcon = listItemView.findViewById(R.id.personal_name_icon);
        //   personalIcon.setImageResource(currentTour.getImagPersonalIcontId());

        TextView personalNameText = listItemView.findViewById(R.id.card_user_name_text_view );
        personalNameText.setText(currentTour.getUserName());

        TextView addPostText = listItemView.findViewById(R.id.card_text_view );
        addPostText.setText(currentTour.getText());

        // ImageView heartIcon = listItemView.findViewById(R.id.emoji_icon_heart);
        // heartIcon.setImageResource(currentTour.getImagHeartIconId());
        TextView heartText = listItemView.findViewById(R.id.heart_counter_text_view );
        heartText.setText(currentTour.getHeartCounter());

        // ImageView happyIcon = listItemView.findViewById(R.id.emoji_icon_happy);
        // happyIcon.setImageResource(currentTour.getImagHappyIconId());
        TextView happyText = listItemView.findViewById(R.id.happy_counter_text_view );
        happyText.setText(currentTour.getHappyCounter());

        //  ImageView winkIcon = listItemView.findViewById(R.id.emoji_icon_wink);
        //  winkIcon.setImageResource(currentTour.getImagWinkIconId());
        TextView winkText = listItemView.findViewById(R.id.wink_counter_text_view );
        winkText.setText(currentTour.getWinkCounter());

        //  ImageView inloveIcon = listItemView.findViewById(R.id.emoji_icon_inlove);
        // inloveIcon.setImageResource(currentTour.getImagInloveIconId());
        TextView inloveText = listItemView.findViewById(R.id.inlove_counter_text_view );
        inloveText.setText(currentTour.getInLoveCounter());

        //  ImageView thumbsIcon = listItemView.findViewById(R.id.emoji_icon_thumbs);
        //  thumbsIcon.setImageResource(currentTour.getImagThumbsIconId());
        TextView thumbsText = listItemView.findViewById(R.id.thumbs_up_counter_text_view );
        thumbsText.setText(currentTour.getThumbsUpCounter());

        return listItemView;
    }

}
