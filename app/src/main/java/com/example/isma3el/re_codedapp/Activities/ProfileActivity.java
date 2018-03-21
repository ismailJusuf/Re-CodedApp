package com.example.isma3el.re_codedapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.isma3el.re_codedapp.Adapters.FeedAdapter;
import com.example.isma3el.re_codedapp.BaseActivity;
import com.example.isma3el.re_codedapp.Models.FeedCard;
import com.example.isma3el.re_codedapp.Models.User;
import com.example.isma3el.re_codedapp.R;
import com.github.paolorotolo.expandableheightlistview.ExpandableHeightListView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends BaseActivity {


    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference feedsDatabaseReference;

    @BindView(R.id.expandable_listview)
    ExpandableHeightListView expandableListView;
    @BindView(R.id.user_name_text_view)
    TextView userName;
    @BindView(R.id.profile_picture)
    CircleImageView profilePicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        expandableListView.setExpanded(true);

        userName.setText(getUser().getFullName());
        firebaseDatabase = FirebaseDatabase.getInstance();
        feedsDatabaseReference = firebaseDatabase.getReference().child("feeds");

        User user = getUser();

        userName.setText(getUser().getFullName());
        //profilePicture.setImageURI(Uri.parse(getUser().getImage()));


        final ArrayList<FeedCard> feedArrayList = new ArrayList<>();

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
    protected void onResume() {
        super.onResume();

        Picasso.get().load(getUser().getImage()).into(profilePicture);
    }

    @OnClick(R.id.profile_back_image_view)
    public void backClickListener() {
        finish();
    }

    @OnClick(R.id.edit_profile_button)
    public void editProfile() {

        Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
        startActivity(intent);

    }
}
