package com.example.isma3el.re_codedapp;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.ImageView;
import android.widget.Toast;

import com.myhexaville.smartimagepicker.ImagePicker;
import com.myhexaville.smartimagepicker.OnImagePickedListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends AppCompatActivity {

    //ImagePicker imagePicker;

    /*@BindView(R.id.logo_image_view)
    ImageView logo;*/

   /* @OnClick(R.id.logo_image_view)
    void changePhoto() {
        imagePicker.choosePicture( true );
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_sign_up );
        ButterKnife.bind( this );

       /* imagePicker = new ImagePicker( this, null, new OnImagePickedListener() {
            @Override
            public void onImagePicked(Uri imageUri) {
                //logo.setImageURI( imageUri );
                Picasso.with( LoginActivity.this ).load( imageUri ).into( logo );
            }
        } ).setWithImageCrop( 1, 1 );*/

    }

    /*@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        imagePicker.handleActivityResult( resultCode, requestCode, data );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult( requestCode, permissions, grantResults );
        imagePicker.handlePermission( requestCode, grantResults );
    }*/

}
