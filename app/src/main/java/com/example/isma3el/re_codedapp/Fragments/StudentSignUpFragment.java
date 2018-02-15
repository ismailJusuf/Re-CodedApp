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

import com.example.isma3el.re_codedapp.MainActivity;
import com.example.isma3el.re_codedapp.Models.Student;
import com.example.isma3el.re_codedapp.Models.User;
import com.example.isma3el.re_codedapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.myhexaville.smartimagepicker.ImagePicker;
import com.myhexaville.smartimagepicker.OnImagePickedListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.concurrent.Executor;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class StudentSignUpFragment extends Fragment {

    private static final String TAG = "sign up status";

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference usersDatabaseReference;
    private FirebaseAuth firebaseAuth;

    ImagePicker imagePicker;
    String studentEmail, studentPassword, studentFullName, studentPhoneNumber, bootcamp, nationality;

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
    @BindView(R.id.student_add_image_image_view)
    ImageView profilePicture;

    @OnClick(R.id.student_add_image_image_view)
    public void setImage() {
        imagePicker.choosePicture( true );
    }

    @OnClick(R.id.student_login_image_view)
    public void studentSignUp() {

        studentEmail = studentEmailEditText.getText().toString().trim();
        studentPassword = studentPasswordEditText.getText().toString().trim();
        studentFullName = studentFullNameEditText.getText().toString();
        studentPhoneNumber = studentPhoneNumberEditText.getText().toString().trim();
        bootcamp = bootcampEditText.getText().toString().trim();
        nationality = nationalityEditText.getText().toString().trim();

        assert getActivity() != null;
        firebaseAuth.createUserWithEmailAndPassword( studentEmail, studentPassword )
                .addOnCompleteListener( getActivity(), new OnCompleteListener<AuthResult>
                        () {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d( TAG, "createUserWithEmail:success" );
                            FirebaseUser user = firebaseAuth.getCurrentUser();

                            Student newStudent = new Student( user.getUid(), studentFullName, null, studentEmail, studentPhoneNumber, bootcamp, nationality );
                            usersDatabaseReference.push().setValue( newStudent );

                            Intent intent = new Intent( getActivity(), MainActivity.class );
                            startActivity( intent );
                            getActivity().finish();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w( TAG, "createUserWithEmail:failure", task.getException() );
                            Toast.makeText( getActivity(), "Authentication failed.", Toast.LENGTH_SHORT ).show();
                        }

                    }
                } );
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate( R.layout.fragment_student_signup, container, false );
        ButterKnife.bind( this, view );

        firebaseDatabase = FirebaseDatabase.getInstance();
        usersDatabaseReference = firebaseDatabase.getInstance().getReference().child( "registeredStudents" );
        firebaseAuth = FirebaseAuth.getInstance();


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
