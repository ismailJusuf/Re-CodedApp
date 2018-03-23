package com.example.isma3el.re_codedapp.Adapters;


import android.app.Activity;
import android.text.TextUtils;
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
import butterknife.BindView;
import butterknife.ButterKnife;

public class ClassRoomStudentAdapter extends ArrayAdapter<User> {


    public ClassRoomStudentAdapter(Activity context, ArrayList<User> cards) {
        super(context,0,cards);
    }

    @Override public View getView(int position,View view,ViewGroup parent) {
        ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view =LayoutInflater.from(getContext()).inflate(R.layout.class_room_list_item, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        User user=getItem(position);
        holder.studentsClassRoomName.setText(user.getFullName());

        boolean isPhoto = TextUtils.isEmpty(user.getImage());
        if (!isPhoto) {
            Picasso.get().load(user.getImage()).into(holder.studentClassRoomeImage);
        } else {
            holder.studentClassRoomeImage.setImageResource(R.drawable.recoded_logo);
        }

        return view;
    }

    static class ViewHolder {
        @BindView(R.id.profile_class_room_student_image)
        ImageView studentClassRoomeImage;
        @BindView(R.id.student_class_room_name)
        TextView studentsClassRoomName;




        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}