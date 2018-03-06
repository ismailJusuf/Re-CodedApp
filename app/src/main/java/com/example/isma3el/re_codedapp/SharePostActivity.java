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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_post);
        ButterKnife.bind(this);

        firebaseDatabase = FirebaseDatabase.getInstance();
        feedsDatabaseReference = firebaseDatabase.getReference().child("feeds");
        tasksDatabaseReference = firebaseDatabase.getReference().child("tasks");


    }

}
