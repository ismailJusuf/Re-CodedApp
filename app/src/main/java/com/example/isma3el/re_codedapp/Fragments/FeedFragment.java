package com.example.isma3el.re_codedapp.Fragments;

/**
 * Created by Recodedharran on 7.2.2018.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.isma3el.re_codedapp.Adapters.FeedAdapter;
import com.example.isma3el.re_codedapp.Models.FeedCard;
import com.example.isma3el.re_codedapp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedFragment extends Fragment {

    private int[] image;
    private String[] userName;
    private String[] text;
    private String[] heartCounter;
    private String[] happyCounter;
    private String[] winkCounter;
    private String[] nerdCounter;
    private String[] inLoveCounter;
    private String[] thumbsUpCounter;

    @BindView(R.id.feed_list_view)
    ListView feedListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_feed, container, false );
        ButterKnife.bind( this,view );


        ArrayList<FeedCard> feedArrayList = new ArrayList<>();

        image = new int[]{R.drawable.student, R.drawable.student, R.drawable.student, R.drawable.student};
        userName = new String[]{"Ismail Youssef", "Abdullah Al-Jadaan", "Ismail Youssef", "Abdullah Al-Jadaan"};
        text = new String[]{"My name is Abdullah. I was born and raised in Al-Hasakah in Syria ....",
                "My name is Abdullah. I was born and raised in Al-Hasakah in Syria ....",
                "My name is Abdullah. I was born and raised in Al-Hasakah in Syria ....",
                "My name is Abdullah. I was born and raised in Al-Hasakah in Syria ...."};

        heartCounter = new String[]{"5", "9", "13", "17"};
        happyCounter = new String[]{"6", "10", "14", "18"};
        winkCounter = new String[]{"7", "11", "15", "19"};
        nerdCounter = new String[]{"8", "12", "16", "20"};
        inLoveCounter = new String[]{"9", "13", "17", "21"};
        thumbsUpCounter = new String[]{"10", "14", "18", "22"};

        FeedCard card[] = new FeedCard[userName.length];

        for (int i = 0; i < userName.length; i++) {

            card[i] = new FeedCard( image[i], userName[i], text[i], heartCounter[i], happyCounter[i], winkCounter[i], nerdCounter[i], inLoveCounter[i], thumbsUpCounter[i] );

            feedArrayList.add( card[i] );
        }

        FeedAdapter adapter = new FeedAdapter( getActivity(), feedArrayList );
        feedListView.setAdapter( adapter );

        return view;
    }

}
