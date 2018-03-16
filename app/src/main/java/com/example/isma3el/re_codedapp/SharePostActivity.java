package com.example.isma3el.re_codedapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.isma3el.re_codedapp.Models.FeedCard;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.myhexaville.smartimagepicker.ImagePicker;
import com.myhexaville.smartimagepicker.OnImagePickedListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by apple on 27.02.2018.
 */

public class SharePostActivity extends BaseActivity {
    @BindView(R.id.user_name)
    TextView user_name;
    @BindView(R.id.selected_image_view)
    ImageView selectedImageView;
    @BindView(R.id.edit_your_text)
    EditText textEditText;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference feedsDatabaseReference;
    private DatabaseReference tasksDatabaseReference;
    private StorageReference imageStorageReference;
    private StorageReference storageReference;

    int postType;
    ImagePicker imagePicker;
    UploadTask uploadTask;
    String downloadImageUrl;
    String shredTextString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_post);
        ButterKnife.bind(this);

        user_name.setText(getUser().getFullName());

        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        storageReference = firebaseStorage.getReference();
        feedsDatabaseReference = firebaseDatabase.getReference().child("feeds");
        tasksDatabaseReference = firebaseDatabase.getReference().child("tasks");

        //selecting and uploading image
        imagePicker = new ImagePicker(this, null, new OnImagePickedListener() {
            @Override
            public void onImagePicked(Uri imageUri) {
                selectedImageView.setImageURI(imageUri);
                if (isOnline()) {

                    waitDialog();

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    imageStorageReference = storageReference.child("images/" + imageUri.getLastPathSegment());

                    uploadTask = imageStorageReference.putFile(imageUri);
                    uploadTask.addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                            materialDialog.dismiss();
                            Toast.makeText(SharePostActivity.this, "failed", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                            materialDialog.dismiss();
                            Toast.makeText(SharePostActivity.this, "sucsesful", Toast.LENGTH_SHORT).show();
                            downloadImageUrl = taskSnapshot.getDownloadUrl().toString();
                        }
                    });
                } else {
                    Toast.makeText(SharePostActivity.this, "no internet", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @OnClick(R.id.ic_done_share_post)
    public void sharePost() {

        Bundle extras = getIntent().getExtras();
        shredTextString = textEditText.getText().toString();
        postType = extras.getInt("postType");

        if (postType == 0 || postType == 1) {

            FeedCard card = new FeedCard(BaseActivity.getInstance().getUser(), downloadImageUrl, shredTextString, postType, BaseActivity.getInstance().getUser().getBootcamp());
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

            FeedCard card = new FeedCard(BaseActivity.getInstance().getUser(), downloadImageUrl, shredTextString, postType, BaseActivity.getInstance().getUser().getBootcamp());
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imagePicker.handleActivityResult(resultCode, requestCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        imagePicker.handlePermission(requestCode, grantResults);
    }

    @OnClick(R.id.ic_camera_shar_post)
    public void sharePhoto() {
        imagePicker.choosePicture(true);
    }

}
