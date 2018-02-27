package com.example.isma3el.re_codedapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.isma3el.re_codedapp.BaseActivity;
import com.example.isma3el.re_codedapp.DataRefreshListener;
import com.example.isma3el.re_codedapp.MainActivity;
import com.example.isma3el.re_codedapp.Models.FeedCard;
import com.example.isma3el.re_codedapp.R;
import com.example.isma3el.re_codedapp.SharePostActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SharePostFragment extends Fragment implements DataRefreshListener {


    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference feedsDatabaseReference;


    @OnClick(R.id.share_post)
    public void intent() {

        Intent intent = new Intent(getActivity(), SharePostActivity.class);
        startActivity(intent);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_share_post, container, false);
        ButterKnife.bind(this, view);
        ((MainActivity) getActivity()).sharePostListener = this;
        firebaseDatabase = FirebaseDatabase.getInstance();
        feedsDatabaseReference = firebaseDatabase.getReference().child("feeds");


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
