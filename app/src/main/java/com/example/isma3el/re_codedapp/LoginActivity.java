package com.example.isma3el.re_codedapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.isma3el.re_codedapp.Models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.yarolegovich.lovelydialog.LovelyTextInputDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

public class LoginActivity extends BaseActivity {

    private static final String TAG = "sign in successful";

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference usersDatabaseReference;
    private FirebaseAuth firebaseAuth;

    String userPasswordText, userEmailText;

    @BindView(R.id.log_in_image_view)
    ImageView loginImage;
    @BindView(R.id.forget_password_text_view)
    TextView forgotPasswordTextView;
    @BindView(R.id.email_edit_text)
    MaterialEditText emailEditText;
    @BindView(R.id.password_edit_text)
    MaterialEditText passwordEditText;

    @OnClick(R.id.sign_up_linear_layout)
    void goToSignUp() {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        firebaseDatabase = FirebaseDatabase.getInstance();
        usersDatabaseReference = firebaseDatabase.getInstance().getReference().child("registeredUsers");
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }


    @OnClick(R.id.forget_password_text_view)
    void forgotPasswordAction() {


        LovelyTextInputDialog a = new LovelyTextInputDialog(this, R.style
                .Theme_AppCompat)
                .setTopColorRes(R.color.recodedDarkColor)
                .setTitle("Reset Password")
                .setMessage("Please enter your Re:Coded registered email at the box below")
                .setIcon(R.drawable.ic_reset_password)
                .setConfirmButton(android.R.string.ok, new LovelyTextInputDialog.OnTextInputConfirmListener() {
                    @Override
                    public void onTextInputConfirmed(final String text) {

                        FirebaseAuth.getInstance().sendPasswordResetEmail(text)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {

                                            Log.d("email condition", "Email sent.");
                                            Toast.makeText(LoginActivity.this, "Email sent to " + text, Toast.LENGTH_SHORT).show();

                                        } else {

                                            Toast.makeText(LoginActivity.this, "wrong email",
                                                    Toast.LENGTH_SHORT
                                            ).show();

                                        }
                                    }
                                });
                    }
                });
        a.show();

    }


    @OnClick(R.id.log_in_image_view)
    void login() {

        userEmailText = emailEditText.getText().toString().trim();
        userPasswordText = passwordEditText.getText().toString().trim();

        if (TextUtils.isEmpty(userEmailText) && TextUtils.isEmpty(userPasswordText)) {

            Toast.makeText(LoginActivity.this, "Please Write Your Email and Password", Toast.LENGTH_LONG).show();

        } else if (TextUtils.isEmpty(userEmailText)) {

            Toast.makeText(LoginActivity.this, "Please Write Your Email", Toast.LENGTH_LONG).show();

        } else if (TextUtils.isEmpty(userPasswordText)) {

            Toast.makeText(LoginActivity.this, "Please Write Your Password", Toast.LENGTH_LONG).show();

        } else {

            firebaseAuth.signInWithEmailAndPassword(userEmailText, userPasswordText)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = firebaseAuth.getCurrentUser();

                                usersDatabaseReference.child(user.getUid())
                                        .addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {

                                                User student = dataSnapshot.getValue(User.class);
                                                saveUser(student);

                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {
                                                Log.d("onCancelled", "addListenerForSingleValueEvent " + "cancelled");
                                            }
                                        });

                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();

                                //updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                //updateUI(null);
                            }

                        }
                    });

        }
    }


}