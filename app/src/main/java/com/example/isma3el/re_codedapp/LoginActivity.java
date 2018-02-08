package com.example.isma3el.re_codedapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.isma3el.re_codedapp.Models.User;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {

    String userPasswordText, userEmailText;

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

        userEmailText = emailEditText.getText().toString().trim();
        userPasswordText = passwordEditText.getText().toString().trim();

        if (TextUtils.isEmpty( userEmailText ) && TextUtils.isEmpty( userPasswordText )) {

            Toast.makeText( LoginActivity.this, "Please Write Your Email and Password", Toast.LENGTH_LONG ).show();

        } else if (TextUtils.isEmpty( userEmailText )) {

            Toast.makeText( LoginActivity.this, "Please Write Your Email", Toast.LENGTH_LONG ).show();

        } else if (!Patterns.EMAIL_ADDRESS.matcher( userEmailText ).matches()) {

            Toast.makeText( LoginActivity.this, "You didn't enter a valid email address.", Toast.LENGTH_LONG ).show();

        } else if (TextUtils.isEmpty( userPasswordText )) {

            Toast.makeText( LoginActivity.this, "Please Write Your Password", Toast.LENGTH_LONG ).show();

        } else if (passwordEditText.length() < 8) {

            Toast.makeText( LoginActivity.this, "Password must be at least 8 characters", Toast.LENGTH_LONG ).show();


        } else {


            JsonObject json = new JsonObject();
            json.addProperty( "phone", userEmailText );
            json.addProperty( "password", userPasswordText );

            Ion.with( this )
                    .load( "http://www.re-coded.com/api/login" )
                    .setJsonObjectBody( json )
                    .as( new TypeToken<User>() {} )
                    .setCallback( new FutureCallback<User>() {
                        @Override
                        public void onCompleted(Exception e, User user) {

                            getUser();

                            Intent intent = new Intent( LoginActivity.this, MainActivity.class );

                            startActivity( intent );

                        }
                    } );
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
