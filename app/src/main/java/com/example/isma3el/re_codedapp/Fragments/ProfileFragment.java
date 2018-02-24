package com.example.isma3el.re_codedapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.isma3el.re_codedapp.EditProfileActivity;
import com.example.isma3el.re_codedapp.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileFragment extends Fragment {


    @OnClick(R.id.profile_text)
    void editProfile(){

        Intent intent = new Intent(getContext(), EditProfileActivity.class);
        startActivity(intent);

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

}
