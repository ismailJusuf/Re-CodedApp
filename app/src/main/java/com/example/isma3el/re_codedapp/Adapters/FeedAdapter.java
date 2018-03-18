package com.example.isma3el.re_codedapp.Adapters;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.isma3el.re_codedapp.Models.FeedCard;
import com.example.isma3el.re_codedapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedAdapter extends ArrayAdapter<FeedCard> {
    private ArrayList<FeedCard> cards;
    int[][] flag;

    public FeedAdapter(Activity context, ArrayList<FeedCard> cards) {
        super(context, 0, cards);
        this.cards = cards;
        flag = new int[cards.size()][6];
        for (int i = 0; i < cards.size(); i++) {
            for (int j = 0; j < 6; j++) {
                flag[i][j] = 0;
            }
        }
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = LayoutInflater.from(getContext()).inflate(
                    R.layout.feed_card_item, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        final FeedCard feedCard = getItem(position);
        int whichPostType = feedCard.getPostType();

        if (whichPostType == 1) {

            holder.postTypeRelativeLayout.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.recodedDarkColor));
            holder.postTypeTextView.setText("STATUS");

        } else if (whichPostType == 2) {

            holder.postTypeRelativeLayout.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.primary_dark));
            holder.postTypeTextView.setText("Task");

        } else {
            holder.postTypeRelativeLayout.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.primary));
            holder.postTypeTextView.setText("PROGRESS");
        }

        boolean isPhoto = feedCard.getImage() != null;
        if (isPhoto) {
            Picasso.get().load(feedCard.getImage()).into(holder.imagePost);
            holder.imagePost.setVisibility(View.VISIBLE);
        } else {
            holder.imagePost.setVisibility(View.GONE);
        }
        holder.checkBoxHeart.setChecked(false);
        holder.checkBoxHappy.setChecked(false);
        holder.checkBoxWink.setChecked(false);
        holder.checkBoxInLove.setChecked(false);
        holder.checkBoxThumbsUp.setChecked(false);
        holder.checkBoxThumbsDown.setChecked(false);
        holder.textViewHeart.setText(String.valueOf(flag[position][0]));
        holder.textViewHappy.setText(String.valueOf(flag[position][1]));
        holder.textViewWink.setText(String.valueOf(flag[position][2]));
        holder.textViewInLove.setText(String.valueOf(flag[position][3]));
        holder.textViewThumbsUp.setText(String.valueOf(flag[position][4]));
        holder.textViewThumbsDown.setText(String.valueOf(flag[position][5]));

        holder.checkBoxHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.textViewHeart.setText(String.valueOf(flag[position][0]++));

                Toast.makeText(getContext(), "aaassaaaaas", Toast.LENGTH_SHORT).show();
            }
        });
        holder.checkBoxHappy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (holder.checkBoxHappy.isChecked()) {
                    holder.textViewHappy.setText(String.valueOf(flag[position][1]--));
                } else {
                    holder.textViewHappy.setText(String.valueOf(flag[position][1]++));
                }
            }
        });
        holder.checkBoxWink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.checkBoxWink.isChecked()) {
                    holder.textViewWink.setText(String.valueOf(flag[position][2]--));
                } else {
                    holder.textViewWink.setText(String.valueOf(flag[position][2]++));
                }
            }
        });
        holder.checkBoxInLove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (holder.checkBoxInLove.isChecked()) {
                    holder.textViewInLove.setText(String.valueOf(flag[position][3]--));
                } else {
                    holder.textViewInLove.setText(String.valueOf(flag[position][3]++));
                }
            }
        });

        holder.checkBoxThumbsUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.checkBoxThumbsUp.isChecked()) {
                    holder.textViewThumbsUp.setText(String.valueOf(flag[position][4]--));
                } else {
                    holder.textViewThumbsUp.setText(String.valueOf(flag[position][4]++));
                }

            }
        });
        holder.checkBoxThumbsDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.checkBoxThumbsDown.isChecked()) {
                    holder.textViewThumbsDown.setText(String.valueOf(flag[position][5]--));
                } else {
                    holder.textViewThumbsDown.setText(String.valueOf(flag[position][5]++));
                }
            }
        });
        holder.addPostText.setText(feedCard.getText());
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.post_type_relative_layout)
        RelativeLayout postTypeRelativeLayout;
        @BindView(R.id.post_type_text_view)
        TextView postTypeTextView;
        @BindView(R.id.card_image_view)
        ImageView imagePost;
        @BindView(R.id.card_text_view)
        TextView addPostText;
        @BindView(R.id.emoji_heart)
        CheckBox checkBoxHeart;
        @BindView(R.id.emoji_happy)
        CheckBox checkBoxHappy;
        @BindView(R.id.emoji_wink)
        CheckBox checkBoxWink;
        @BindView(R.id.emoji_inlove)
        CheckBox checkBoxInLove;
        @BindView(R.id.emoji_thumbs_up)
        CheckBox checkBoxThumbsUp;
        @BindView(R.id.emoji_thumbs_down)
        CheckBox checkBoxThumbsDown;
        @BindView(R.id.heart_counter_text_view)
        TextView textViewHeart;
        @BindView(R.id.happy_counter_text_view)
        TextView textViewHappy;
        @BindView(R.id.inlove_counter_text_view)
        TextView textViewInLove;
        @BindView(R.id.thumbs_up_counter_text_view)
        TextView textViewThumbsUp;
        @BindView(R.id.emoji_thumbs_down_text_view)
        TextView textViewThumbsDown;
        @BindView(R.id.wink_counter_text_view)
        TextView textViewWink;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

    }


}
