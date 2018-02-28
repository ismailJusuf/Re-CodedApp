
package com.example.isma3el.re_codedapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.example.isma3el.re_codedapp.Adapters.FeedAdapter;
import com.example.isma3el.re_codedapp.BaseActivity;
import com.example.isma3el.re_codedapp.DataRefreshListener;
import com.example.isma3el.re_codedapp.EditProfileActivity;
import com.example.isma3el.re_codedapp.Models.FeedCard;
import com.example.isma3el.re_codedapp.R;
import com.github.paolorotolo.expandableheightlistview.ExpandableHeightListView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileFragment extends Fragment implements DataRefreshListener {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference feedsDatabaseReference;

    @BindView(R.id.expandable_listview)
    ExpandableHeightListView expandableListView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);

        firebaseDatabase = FirebaseDatabase.getInstance();
        feedsDatabaseReference = firebaseDatabase.getReference().child("feeds");

        final ArrayList<FeedCard> feedArrayList = new ArrayList<>();

        expandableListView.setExpanded(true);

        feedsDatabaseReference.orderByChild("user/id").equalTo(BaseActivity.getInstance().getUser().getId()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                FeedCard card = null;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    card = snapshot.getValue(FeedCard.class);
                    feedArrayList.add(card);

                }

                FeedAdapter adapter = new FeedAdapter(getActivity(), feedArrayList);
                expandableListView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;

    }

    @Override
    public void onProfileRefreshed() {

    }

    @Override
    public void onFeedRefreshed() {

    }

    @Override
    public void onSharePostRefreshed() {

    }
}