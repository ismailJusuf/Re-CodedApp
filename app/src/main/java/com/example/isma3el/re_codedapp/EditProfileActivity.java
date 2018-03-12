package com.example.isma3el.re_codedapp;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.isma3el.re_codedapp.Models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.myhexaville.smartimagepicker.ImagePicker;
import com.myhexaville.smartimagepicker.OnImagePickedListener;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfileActivity extends BaseActivity {

    DatePickerDialog datePickerDialog;
    Date currentDate;
    Calendar calendar;
    int createBirthdayDay, createBirthdayMonth, createBirthdayYear;

    private StorageReference storageReference;
    private StorageReference imageStorageReference;
    private DatabaseReference reference;
    private FirebaseDatabase database;

    private DatabaseReference feedsDatabaseReference;

    ImagePicker imagePicker;
    UploadTask uploadTask;
    String downloadImageUrl;
    //public FirebaseUser user;
    public FirebaseAuth mAuth;

    @BindView(R.id.edit_profile_picture)
    CircleImageView profilePicture;
    @BindView(R.id.cover_picture_image_view)
    ImageView coverImage;
    @BindView(R.id.emil_text_view)
    TextView email;
    @BindView(R.id.user_name_text_view)
    TextView userName;
    @BindView(R.id.phone_number_edit_text)
    EditText PhoneNumberEditText;
    @BindView(R.id.birthday_text_view)
    TextView birthdayTextView;
    @BindView(R.id.genedr_radio_group)
    RadioGroup gender;
    @BindView(R.id.male_radio_button)
    RadioButton maleButton;
    @BindView(R.id.female_radio_button)
    RadioButton femaleButton;
    @BindView(R.id.birtday_date_text_view)
    TextView birtdayDateTextView;

    String birthday;
  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ButterKnife.bind(this);

        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();
        mAuth = FirebaseAuth.getInstance();
        //user = mAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("users");

        /*email.setText(getUser().getEmail());
        userName.setText(getUser().getFullName());
        Picasso.with( this ).load( getUser().getImage() ).into( profilePicture );*/

        //selecting and uploading image
        imagePicker = new ImagePicker(this, null, new OnImagePickedListener() {
            @Override
            public void onImagePicked(Uri imageUri) {
                profilePicture.setImageURI(imageUri);
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
                            Toast.makeText(EditProfileActivity.this, "failed", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                            materialDialog.dismiss();
                            Toast.makeText(EditProfileActivity.this, "sucsesful", Toast.LENGTH_SHORT).show();
                            downloadImageUrl = taskSnapshot.getDownloadUrl().toString();
                        }
                    });
                } else {
                    Toast.makeText(EditProfileActivity.this, "no internet", Toast.LENGTH_SHORT).show();
                }
            }
        });

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

    @OnClick(R.id.cover_picture_image_view)
    public void setCoverPicture() {

    }

    @OnClick(R.id.edit_profile_picture)
    public void setProfilePicture() {
        imagePicker.choosePicture(true);
    }


    @OnClick(R.id.birtday_date_text_view)
    public void getBirthDay() {
        datePickerDialog = new DatePickerDialog( EditProfileActivity.this,AlertDialog.THEME_HOLO_DARK, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                birtdayDateTextView.setText( dayOfMonth + "/" + month + "/" + year );
                birthday = dayOfMonth + "/" + month + "/" + year;
            }
        }, createBirthdayYear, createBirthdayMonth, createBirthdayDay);
        datePickerDialog.show();
    }

    @OnClick(R.id.update_button)
    public void updateProfile() {

        reference.child(getUser().getId())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        final User user = dataSnapshot.getValue(User.class);
                        user.setImage(downloadImageUrl);

                        reference.child(user.getId())
                                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(
                                    @NonNull Task<Void> task
                            ) {
                                if (task.isSuccessful()) {
                                    saveUser(user);
                                    Toast.makeText(EditProfileActivity.this, "update successful", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

}
