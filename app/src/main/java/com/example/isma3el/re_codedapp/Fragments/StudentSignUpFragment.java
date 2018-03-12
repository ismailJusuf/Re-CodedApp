package com.example.isma3el.re_codedapp.Fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.isma3el.re_codedapp.BaseActivity;
import com.example.isma3el.re_codedapp.MainActivity;
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
import com.google.gson.Gson;
import com.myhexaville.smartimagepicker.ImagePicker;
import com.myhexaville.smartimagepicker.OnImagePickedListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StudentSignUpFragment extends Fragment {

    private static final String TAG = "sign up status";

    private DatabaseReference usersDatabaseReference;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference recodedUsersDatabaseReference;
    private StorageReference storageReference;
    private StorageReference imageStorageReference;

    Fragment studentFragment;
    ImagePicker imagePicker;
    String studentEmail, studentPassword, studentFullName, studentPhoneNumber, bootcamp, nationality;
    UploadTask uploadTask;
    String downloadImageUrl;
    SharedPreferences preferences;

    @BindView(R.id.student_add_image_image_view)
    ImageView studentProfilePicture;
    @BindView(R.id.student_email_edit_text)
    MaterialEditText studentEmailEditText;
    @BindView(R.id.student_password_edit_text)
    MaterialEditText studentPasswordEditText;
    @BindView(R.id.student_full_name_edit_text)
    MaterialEditText studentFullNameEditText;
    @BindView(R.id.student_phone_number_edit_text)
    MaterialEditText studentPhoneNumberEditText;
    @BindView(R.id.bootcamp_location_edit_text)
    MaterialEditText bootcampEditText;
    @BindView(R.id.nationality_edit_text)
    MaterialEditText nationalityEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student_signup, container, false);
        ButterKnife.bind(this, view);
        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        studentFragment = new Fragment().getTargetFragment();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        usersDatabaseReference = FirebaseDatabase.getInstance().getReference().child("users");
        firebaseAuth = FirebaseAuth.getInstance();
        recodedUsersDatabaseReference = FirebaseDatabase.getInstance().getReference().child("recodedUsers");
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

        //selecting and uploading image
        imagePicker = new ImagePicker(getActivity(), studentFragment, new OnImagePickedListener() {
            @Override
            public void onImagePicked(Uri imageUri) {
                studentProfilePicture.setImageURI(imageUri);
                if (BaseActivity.getInstance().isOnline() == true) {

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

    @OnClick(R.id.student_add_image_image_view)
    public void setStudentImage() {
        imagePicker.choosePicture(true);
    }


    @OnClick(R.id.student_login_button)
    public void studentSignUp() {

        if (BaseActivity.getInstance().isOnline()) {

            studentEmail = studentEmailEditText.getText().toString().trim();
            studentPassword = studentPasswordEditText.getText().toString().trim();
            studentFullName = studentFullNameEditText.getText().toString();
            studentPhoneNumber = studentPhoneNumberEditText.getText().toString().trim();
            bootcamp = bootcampEditText.getText().toString().trim();
            nationality = nationalityEditText.getText().toString().trim();

            recodedUsersDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    boolean isInList = false;
                    loop:
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        String email = data.getValue(String.class);
                        if (email.equals(studentEmail)) {
                            isInList = true;
                            break loop;
                        }
                    }
                    if (isInList) {
                        firebaseAuth.createUserWithEmailAndPassword(studentEmail, studentPassword)
                                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>
                                        () {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            // Sign in success, update UI with the signed-in user's information
                                            Log.d(TAG, "createUserWithEmail:success");
                                            FirebaseUser user = firebaseAuth.getCurrentUser();

                                            User newStudent = new User(user.getUid(), studentFullName, downloadImageUrl,
                                                    studentEmail, studentPhoneNumber,
                                                    bootcamp, nationality, 0);
                                            usersDatabaseReference.child(user.getUid()).setValue(newStudent);

                                            BaseActivity.getInstance().saveUser(newStudent);

                                            Intent intent = new Intent(getActivity(), MainActivity.class);
                                            startActivity(intent);
                                            getActivity().finish();

                                        } else {
                                            // If sign in fails, display a message to the user.
                                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                            Toast.makeText(getActivity(), "Authentication failed.", Toast.LENGTH_SHORT).show();

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
            Toast.makeText(getContext(), "no internet", Toast.LENGTH_SHORT).show();
        }

    }


}