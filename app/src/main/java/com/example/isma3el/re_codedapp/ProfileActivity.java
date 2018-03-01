package com.example.isma3el.re_codedapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.isma3el.re_codedapp.Adapters.FeedAdapter;
import com.example.isma3el.re_codedapp.Models.FeedCard;
import com.github.paolorotolo.expandableheightlistview.ExpandableHeightListView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileActivity extends AppCompatActivity implements DataRefreshListener {


    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference feedsDatabaseReference;

    @BindView(R.id.expandable_listview)
    ExpandableHeightListView expandableListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

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

                FeedAdapter adapter = new FeedAdapter(ProfileActivity.this, feedArrayList);
                expandableListView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

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
