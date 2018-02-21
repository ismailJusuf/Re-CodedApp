package com.example.isma3el.re_codedapp.Adapters;

import android.app.Activity;
import android.os.Build;
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

        String whichPostType = feedCard.getPostType();
        if (whichPostType == "progress"){

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                postTypeRelativeLayout.setBackgroundColor(getContext().getColor(R.color.colorPrimary));
            }
            postTypeTextView.setText(feedCard.getPostType());
        } else {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                postTypeRelativeLayout.setBackgroundColor(getContext().getColor(R.color.postTypeProgress));
            }
            postTypeTextView.setText(feedCard.getPostType());
        }

        ImageView imagePost = listItemView.findViewById(R.id.card_image_view);

        boolean isPhoto = feedCard.getImage() != null;
        if (isPhoto) {
            imagePost.setImageResource(Integer.parseInt(feedCard.getImage()));
        } else {
            imagePost.setVisibility(View.GONE);
        }

        // ImageView personalIcon = listItemView.findViewById(R.id.personal_name_icon);
        // personalIcon.setImageResource(feedCard.getImagPersonalIcontId());

        TextView personalNameText = listItemView.findViewById(R.id.card_user_name_text_view);
        personalNameText.setText(feedCard.getUserName());

        TextView addPostText = listItemView.findViewById(R.id.card_text_view);
        addPostText.setText(feedCard.getText());

        // ImageView heartIcon = listItemView.findViewById(R.id.emoji_icon_heart);
        // heartIcon.setImageResource(feedCard.getImagHeartIconId());
        TextView heartText = listItemView.findViewById(R.id.heart_counter_text_view);
        heartText.setText(feedCard.getHeartCounter());

        // ImageView happyIcon = listItemView.findViewById(R.id.emoji_icon_happy);
        // happyIcon.setImageResource(feedCard.getImagHappyIconId());
        TextView happyText = listItemView.findViewById(R.id.happy_counter_text_view);
        happyText.setText(feedCard.getHappyCounter());

        //  ImageView winkIcon = listItemView.findViewById(R.id.emoji_icon_wink);
        //  winkIcon.setImageResource(feedCard.getImagWinkIconId());
        TextView winkText = listItemView.findViewById(R.id.wink_counter_text_view);
        winkText.setText(feedCard.getWinkCounter());

        //  ImageView nerdIcon = listItemView.findViewById(R.id.emoji_icon_nerd);
        //  nerdIcon.setImageResource(feedCard.getImagNerdIconId());
        TextView nerdText = listItemView.findViewById(R.id.nerd_counter_text_view);
        nerdText.setText(feedCard.getNerdCounter());

        //  ImageView inloveIcon = listItemView.findViewById(R.id.emoji_icon_inlove);
        // inloveIcon.setImageResource(feedCard.getImagInloveIconId());
        TextView inloveText = listItemView.findViewById(R.id.inlove_counter_text_view);
        inloveText.setText(feedCard.getInLoveCounter());

        //  ImageView thumbsIcon = listItemView.findViewById(R.id.emoji_icon_thumbs);
        //  thumbsIcon.setImageResource(feedCard.getImagThumbsIconId());
        TextView thumbsText = listItemView.findViewById(R.id.thumbs_up_counter_text_view);
        thumbsText.setText(feedCard.getThumbsUpCounter());

        return listItemView;
    }

}
