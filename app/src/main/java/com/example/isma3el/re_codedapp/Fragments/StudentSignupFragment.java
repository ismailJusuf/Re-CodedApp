package com.example.isma3el.re_codedapp.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.isma3el.re_codedapp.LoginActivity;
import com.example.isma3el.re_codedapp.R;
import com.myhexaville.smartimagepicker.ImagePicker;
import com.myhexaville.smartimagepicker.OnImagePickedListener;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class StudentSignupFragment extends Fragment {
    ImagePicker imagePicker;
    CircleImageView profilePicture;

   /* @BindView(R.id.add_image_student_image_view)
    ImageView profilePicture;*/

    /*@OnClick(R.id.add_image_student_image_view)
    public void setImage() {

        imagePicker.choosePicture( true );

    }*/

    public StudentSignupFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate( R.layout.fragment_student_signup, container, false );
        ButterKnife.bind( view );

        profilePicture = view.findViewById( R.id.add_image_student_image_view );

        profilePicture.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagePicker.choosePicture( false );
            }
        } );

        imagePicker = new ImagePicker( getActivity(), this, new OnImagePickedListener() {
            @Override
            public void onImagePicked(Uri imageUri) {
                profilePicture.setImageURI( imageUri );
                //Picasso.with( getContext() ).load( imageUri ).into( profilePicture );
            }
        } );

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        imagePicker.handleActivityResult( resultCode, requestCode, data );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult( requestCode, permissions, grantResults );
        imagePicker.handlePermission( requestCode, grantResults );
    }

}
