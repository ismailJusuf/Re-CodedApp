package com.example.isma3el.re_codedapp.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.isma3el.re_codedapp.BaseActivity;
import com.example.isma3el.re_codedapp.Activities.MainActivity;
import com.example.isma3el.re_codedapp.Models.User;
import com.example.isma3el.re_codedapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TeacherSignUpFragment extends Fragment {

    private static final String TAG = "sign up status";

    private DatabaseReference registeredUsersDatabaseReference;
    private DatabaseReference recodedUsersDatabaseReference;
    private FirebaseAuth firebaseAuth;
    private StorageReference storageReference;
    private StorageReference imageStorageReference;

    UploadTask uploadTask;
    ImagePicker imagePicker;
    String teacherEmail, teacherPassword, teacherFullName, teacherPhoneNumber;
    String downloadImageUrl;

    @BindView(R.id.teacher_add_image_image_view)
    ImageView profilePicture;
    @BindView(R.id.teacher_email_edit_text)
    MaterialEditText teacherEmailEditText;
    @BindView(R.id.teacher_password_edit_text)
    MaterialEditText teacherPasswordEditText;
    @BindView(R.id.teacher_full_name_edit_text)
    MaterialEditText teacherFullNameEditText;
    @BindView(R.id.teacher_phone_number_edit_text)
    MaterialEditText teacherPhoneNumberEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teacher_singup, container, false);
        ButterKnife.bind(this, view);

        registeredUsersDatabaseReference = FirebaseDatabase.getInstance().getReference().child("users");
        recodedUsersDatabaseReference = FirebaseDatabase.getInstance().getReference().child("recodedUsers");
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

        //selecting and uploading image
        imagePicker = new ImagePicker(getActivity(), this, new OnImagePickedListener() {
            @Override
            public void onImagePicked(Uri imageUri) {
                profilePicture.setImageURI(imageUri);

                if (BaseActivity.getInstance().isOnline() == true) {

                    //create reference to images folder and assing a name to the file that will be uploaded
                    imageStorageReference = storageReference.child("images/" + imageUri.getLastPathSegment());

                    uploadTask = imageStorageReference.putFile(imageUri);
                    uploadTask.addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                            downloadImageUrl = taskSnapshot.getDownloadUrl().toString();
                        }

                    });
                } else {
                    Toast.makeText(getContext(), "no internet", Toast.LENGTH_SHORT).show();

                }
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imagePicker.handleActivityResult(resultCode, requestCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        imagePicker.handlePermission(requestCode, grantResults);
    }


    @OnClick(R.id.teacher_add_image_image_view)
    public void setTeacherImage() {
        imagePicker.choosePicture(true);
    }

    @OnClick(R.id.teacher_login_button)
    void teacherSignUp() {


        if (BaseActivity.getInstance().isOnline()) {

            teacherEmail = teacherEmailEditText.getText().toString().trim();
            teacherPassword = teacherPasswordEditText.getText().toString().trim();
            teacherFullName = teacherFullNameEditText.getText().toString();
            teacherPhoneNumber = teacherPhoneNumberEditText.getText().toString().trim();

            if(!teacherEmail.isEmpty() && !teacherPassword.isEmpty() && !teacherFullName.isEmpty() && !teacherPhoneNumber.isEmpty()){
            recodedUsersDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    boolean isInList = false;
                    loop:
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        String email = data.getValue(String.class);
                        if (email.equals(teacherEmail)) {
                            isInList = true;
                            break loop;
                        }
                    }
                    if (isInList) {
                        firebaseAuth.createUserWithEmailAndPassword(teacherEmail, teacherPassword)
                                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>
                                        () {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            // Sign in success, update UI with the signed-in user's information
                                            Log.d(TAG, "createUserWithEmail:success");
                                            FirebaseUser user = firebaseAuth.getCurrentUser();

                                            User newTeacher = new User(user.getUid(), teacherFullName, downloadImageUrl,
                                                    teacherEmail, teacherPhoneNumber, "", "", 1);
                                            registeredUsersDatabaseReference.push().setValue(newTeacher);

                                            BaseActivity.getInstance().saveUser(newTeacher);

                                            Intent intent = new Intent(getActivity(), MainActivity.class);
                                            startActivity(intent);
                                            getActivity().finish();

                                        } else {
                                            // If sign in fails, display a message to the user.
                                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                            Toast.makeText(getActivity(), "Your email is already used", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    } else {
                        Toast.makeText(getActivity(), "Your email is not registered", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });
            } else {
                Toast.makeText(getContext(), "please fill all the feilds", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getContext(), "no internet", Toast.LENGTH_SHORT).show();
        }
    }


}