package com.example.isma3el.re_codedapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.isma3el.re_codedapp.Adapters.ClassStudentAdapter;
import com.example.isma3el.re_codedapp.Models.User;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClassRoomStudentsActivity extends AppCompatActivity {
    @BindView(R.id.list_class_mates_item) ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_room_students);
        ButterKnife.bind(this);
        ArrayList students=new ArrayList();
        students.add(new User("sad",null));
        students.add(new User("sad",null));
        students.add(new User("sad",null));
        students.add(new User("sad",null));
        students.add(new User("sad",null));
        students.add(new User("sad",null));
        ClassStudentAdapter adapter=new ClassStudentAdapter(this,students);
        listView.setAdapter(adapter);

    }
}
