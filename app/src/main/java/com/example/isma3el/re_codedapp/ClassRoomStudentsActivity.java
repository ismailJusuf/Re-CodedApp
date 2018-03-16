package com.example.isma3el.re_codedapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;


import com.example.isma3el.re_codedapp.Adapters.ClassStudentAdapter;
import com.example.isma3el.re_codedapp.Models.User;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

public class ClassRoomStudentsActivity extends AppCompatActivity {
    @BindView(R.id.list_class_mates_list_view)
    ListView listView;
    @BindView(R.id.class_room_student_back)
    ImageView back;

    private   FirebaseDatabase firebaseDatabase;
    private DatabaseReference usersDatabaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_room_students);
        ButterKnife.bind(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView,View view,int i,long l) {
                Intent intent =new Intent(ClassRoomStudentsActivity.this,ProfileActivity.class);
                startActivity(intent);
            }
        });

        final ArrayList<User> students=new ArrayList();


       firebaseDatabase = FirebaseDatabase.getInstance();
       usersDatabaseReference = firebaseDatabase.getReference().child("users");
       usersDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
              User user = null;
               students.clear();
               for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                   user = snapshot.getValue(User.class);
                  students.add(user);

               }

              ClassStudentAdapter adapter = new ClassStudentAdapter(ClassRoomStudentsActivity.this,students);
              listView.setAdapter(adapter);

           }


           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       });

    }
    @OnClick(R.id.class_room_student_back)
    void classRoomBackOnClick (){
        finish();
    }


}
