package com.example.isma3el.re_codedapp.Adapters;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.isma3el.re_codedapp.Models.User;
import com.example.isma3el.re_codedapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class ClassStudentAdapter extends ArrayAdapter<User> {
    private static final String LOG_TAG =ClassStudentAdapter.class.getSimpleName();

    public ClassStudentAdapter(Activity context,ArrayList<User> androidFlavors) {

        super(context, 0, androidFlavors);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        User userImage = getItem(position);
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.class_room_list_item, parent, false);
        }

        User name = getItem(position);
        ImageView studentImage = (ImageView) listItemView.findViewById(R.id.profile_class_room_student_image);
        TextView studentName = (TextView) listItemView.findViewById(R.id.student_class_room_name);



        boolean isPhoto = userImage.getImage() != null;
        if (isPhoto) {
            studentImage.setImageResource(Integer.parseInt(userImage.getImage()));
        } else {
            studentImage.setVisibility(View.GONE);
        }

        studentName.setText(name.getFullName());
        return listItemView;
    }
}
