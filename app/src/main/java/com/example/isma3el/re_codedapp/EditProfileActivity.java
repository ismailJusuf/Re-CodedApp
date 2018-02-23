package com.example.isma3el.re_codedapp;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.rengwuxian.materialedittext.MaterialEditText;
import butterknife.BindView;
import butterknife.OnCheckedChanged;

public class EditProfileActivity extends AppCompatActivity {
    @OnCheckedChanged(R.id.cover_picture_image_view)
    public void setCoverPicture() {
    }
    @OnCheckedChanged(R.id.profile_picture)
    public void setProfilePicture() {
    }
    @BindView(R.id.user_name_edit_text)
    MaterialEditText studentFullNameEditText;
    @BindView(R.id.phone_number_edit_text)
    MaterialEditText PhoneNumberEditText;
    @BindView(R.id.birthday_edit_text)
    MaterialEditText BirthdayEditText;
    @BindView(R.id.genedr_radio_group)
    RadioGroup gender;
    @BindView(R.id.male_radio_button)
    RadioButton maleButton;
    @BindView(R.id.female_radio_button)
    RadioButton femaleButton;
    @OnCheckedChanged(R.id.update_button)
    public void updateProfile() {
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
    }
}
