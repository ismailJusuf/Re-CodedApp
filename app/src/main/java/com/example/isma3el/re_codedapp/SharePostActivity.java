package com.example.isma3el.re_codedapp;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.isma3el.re_codedapp.Models.FeedCard;
import com.github.angads25.filepicker.controller.DialogSelectionListener;
import com.github.angads25.filepicker.model.DialogConfigs;
import com.github.angads25.filepicker.model.DialogProperties;
import com.github.angads25.filepicker.view.FilePickerDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by apple on 27.02.2018.
 */

public class SharePostActivity extends BaseActivity {

    private FirebaseDatabase firebaseDatabase;
    private StorageReference storageReference;
    private StorageReference fileStorageReference;
    private DatabaseReference feedsDatabaseReference;
    private DatabaseReference tasksDatabaseReference;

    UploadTask uploadTask;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_post);
        ButterKnife.bind(this);
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

        firebaseDatabase = FirebaseDatabase.getInstance();
        feedsDatabaseReference = firebaseDatabase.getReference().child("feeds");
        tasksDatabaseReference = firebaseDatabase.getReference().child("tasks");


        DialogProperties properties = new DialogProperties();

        properties.selection_mode = DialogConfigs.SINGLE_MODE;
        properties.selection_type = DialogConfigs.FILE_SELECT;
        properties.root = new File(DialogConfigs.DEFAULT_DIR+ Environment
                .getExternalStorageDirectory().getPath());
        properties.error_dir = new File(DialogConfigs.DEFAULT_DIR);
        properties.offset = new File(DialogConfigs.DEFAULT_DIR);
        properties.extensions = null;

        final FilePickerDialog dialog = new FilePickerDialog(SharePostActivity.this,properties);
        dialog.setTitle("Please Select A File");

        dialog.setDialogSelectionListener(new DialogSelectionListener() {
            @Override
            public void onSelectedFilePaths(String[] files) {
                //files is the array of the paths of files selected by the Application User.

                String fileString = files[0];

                File fileToSave = new File(fileString);

                Uri myUri = Uri.parse("file://" + fileToSave.getPath());

                fileStorageReference = storageReference.child("files/" + myUri.getLastPathSegment());
                uploadTask = fileStorageReference.putFile(myUri);

                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.

                    }
                });
            }
        });

         ImageView image=(ImageView) findViewById(R.id.add_photo_image_view);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.show();
            }
        });
    }

    @OnClick(R.id.share_file_layout)
    public void sharePost() {


        FeedCard card = new FeedCard(BaseActivity.getInstance().getUser(), null, "Deneme asdasdasd", FeedCard.STATUS, BaseActivity.getInstance().getUser().getBootcamp());
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

    }

    @OnClick(R.id.share_photo_layout)
    public void shareTask(){

        FeedCard card = new FeedCard(BaseActivity.getInstance().getUser(), null, "Deneme asdasdasddfgdhfjgkhghkjghxfgzd", FeedCard.TASK, BaseActivity.getInstance().getUser().getBootcamp());
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

    @OnClick(R.id.profile_photo)
    public void shareProgress(){

        FeedCard card = new FeedCard(BaseActivity.getInstance().getUser(), null, "Deneme asdasdasd", FeedCard.PROGRESS, BaseActivity.getInstance().getUser().getBootcamp());
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

    }

}
