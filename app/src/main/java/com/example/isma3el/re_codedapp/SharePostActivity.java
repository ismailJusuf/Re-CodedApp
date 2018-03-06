package com.example.isma3el.re_codedapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.isma3el.re_codedapp.Models.FeedCard;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by apple on 27.02.2018.
 */

public class SharePostActivity extends BaseActivity {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference feedsDatabaseReference;
    private DatabaseReference tasksDatabaseReference;

    int postType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_post);
        ButterKnife.bind(this);

        firebaseDatabase = FirebaseDatabase.getInstance();
        feedsDatabaseReference = firebaseDatabase.getReference().child("feeds");
        tasksDatabaseReference = firebaseDatabase.getReference().child("tasks");


    }


    @OnClick(R.id.ic_done_share_post)
    public void sharePost() {

        Bundle extras = getIntent().getExtras();

        postType = extras.getInt("postType");

        if (postType == 0 || postType == 1) {

            FeedCard card = new FeedCard(BaseActivity.getInstance().getUser(), null, "Deneme asdasdasd", postType, BaseActivity.getInstance().getUser().getBootcamp());
            final String key = feedsDatabaseReference.push().getKey();
            card.setId(key);
            feedsDatabaseReference.child(key).setValue(card).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(SharePostActivity.this, key, Toast.LENGTH_LONG).show();
                        return;
                    }
                    Toast.makeText(SharePostActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            });


        } else if (postType == 2) {

            FeedCard card = new FeedCard(BaseActivity.getInstance().getUser(), null, "Deneme asdasdasddfgdhfjgkhghkjghxfgzd", postType, BaseActivity.getInstance().getUser().getBootcamp());
            final String key = tasksDatabaseReference.push().getKey();
            card.setId(key);
            tasksDatabaseReference.child(key).setValue(card).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(SharePostActivity.this, key, Toast.LENGTH_LONG).show();
                        return;
                    }
                    Toast.makeText(SharePostActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            });

        }

    }

}
