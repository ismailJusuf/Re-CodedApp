package com.example.isma3el.re_codedapp.Fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.isma3el.re_codedapp.R;

import butterknife.ButterKnife;

public class TeacherSignUpFragment extends Fragment {




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_teacher_singup, container, false);
        ButterKnife.bind( this,view );



        return view;
    }
}
