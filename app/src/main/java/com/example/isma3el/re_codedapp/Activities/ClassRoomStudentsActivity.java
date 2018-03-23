package com.example.isma3el.re_codedapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;


import com.example.isma3el.re_codedapp.Adapters.ClassRoomStudentAdapter;
import com.example.isma3el.re_codedapp.BaseActivity;
import com.example.isma3el.re_codedapp.Models.User;

import com.example.isma3el.re_codedapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class ClassRoomStudentsActivity extends BaseActivity {
    @BindView(R.id.list_class_mates_list_view)
    ListView listView;
    @BindView(R.id.class_room_student_back)
    ImageView back;

    final ArrayList<User> students = new ArrayList();


    private   FirebaseDatabase firebaseDatabase;
    private DatabaseReference usersDatabaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_room_students);
        ButterKnife.bind(this);


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

               ClassRoomStudentAdapter adapter = new ClassRoomStudentAdapter(ClassRoomStudentsActivity.this,students);
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




    @OnItemClick(R.id.list_class_mates_list_view)
    public void listItemClickListener(int pos){
        Intent intent =new Intent(ClassRoomStudentsActivity.this,ProfileActivity.class);
        String usedId = students.get(pos).getId();
        intent.putExtra("user_id",usedId );
        startActivity(intent);
    }

}
