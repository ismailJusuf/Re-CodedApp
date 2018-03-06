package com.example.isma3el.re_codedapp;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

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

    @BindView(R.id.profile_picture)
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

    @OnClick(R.id.update_button)
    public void updateProfile() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ButterKnife.bind(this);

        /*email.setText(getUser().getEmail());
        userName.setText(getUser().getFullName());
        Picasso.with( this ).load( getUser().getImage() ).into( profilePicture );*/

    }

    @OnClick(R.id.cover_picture_image_view)
    public void setCoverPicture() {

    }

    @OnClick(R.id.profile_picture)
    public void setProfilePicture() {

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
        }, createBirthdayYear, createBirthdayMonth, createBirthdayDay );
        datePickerDialog.show();
    }
}
