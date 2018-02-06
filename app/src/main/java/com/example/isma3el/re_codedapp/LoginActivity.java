package com.example.isma3el.re_codedapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    String passwordText, emailText;

    @BindView(R.id.log_in_image_view)
    ImageView loginImage;
    @BindView(R.id.sign_up_text_view)
    TextView signUpTextView;
    @BindView(R.id.forget_password_text_view)
    TextView forgotPasswordTextView;
    @BindView(R.id.email_edit_text)
    MaterialEditText emailEditText;
    @BindView(R.id.password_edit_text)
    MaterialEditText passwordEditText;

    @OnClick(R.id.log_in_image_view)
    void login() {

        emailText = emailEditText.getText().toString().trim();
        passwordText = passwordEditText.getText().toString().trim();
        if (TextUtils.isEmpty( emailText ) && TextUtils.isEmpty( passwordText )) {

            Toast.makeText( LoginActivity.this, "Please Write Your Email and Password", Toast.LENGTH_LONG ).show();

        } else if (TextUtils.isEmpty( emailText )) {

            Toast.makeText( LoginActivity.this, "Please Write Your Email", Toast.LENGTH_LONG )
                    .show();

        } else if (!Patterns.EMAIL_ADDRESS.matcher( emailText ).matches()) {

            Toast.makeText( LoginActivity.this, "You didn't enter a valid email address.", Toast.LENGTH_LONG ).show();

        } else if (!emailText.equalsIgnoreCase( "ismailyoussef44@gmail.com" )) {

            Toast.makeText( LoginActivity.this, "Your email is not registered", Toast.LENGTH_LONG ).show();

        } else if (TextUtils.isEmpty( passwordText )) {

            Toast.makeText( LoginActivity.this, "Please Write Your Password", Toast.LENGTH_LONG ).show();

        } else if (passwordEditText.length() < 8) {

            Toast.makeText( LoginActivity.this, "Password must be at least 8 characters", Toast.LENGTH_LONG ).show();

        } else if (!passwordText.equals( "asdfgh66" )) {

            Toast.makeText( LoginActivity.this, "You entered a wrong password", Toast.LENGTH_LONG ).show();

        } else {
            Intent intent = new Intent( LoginActivity.this, MainActivity.class );
            startActivity( intent );
        }
    }

    @OnClick(R.id.sign_up_text_view)
    void goToSignUp() {
        Intent intent = new Intent( LoginActivity.this, SignUpActivity.class );
        startActivity( intent );

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );
        ButterKnife.bind( this );

    }
}
