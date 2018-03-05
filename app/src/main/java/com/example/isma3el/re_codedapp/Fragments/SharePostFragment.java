package com.example.isma3el.re_codedapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.isma3el.re_codedapp.Adapters.FeedAdapter;
import com.example.isma3el.re_codedapp.BaseActivity;
import com.example.isma3el.re_codedapp.DataRefreshListener;
import com.example.isma3el.re_codedapp.MainActivity;
import com.example.isma3el.re_codedapp.Models.FeedCard;
import com.example.isma3el.re_codedapp.R;
import com.example.isma3el.re_codedapp.SharePostActivity;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SharePostFragment extends Fragment  {


    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference tasksDatabaseReference;

    @BindView(R.id.floating_button)
    FloatingActionsMenu floatingActionButton;
    @BindView(R.id.teacher_list_view)
    ListView listView;

    @OnClick(R.id.add_post)
    public void addPost(){
        FeedCard card = new FeedCard(BaseActivity.getInstance().getUser(), null, "Deneme 2", FeedCard.TASK, BaseActivity.getInstance().getUser().getBootcamp());
        final String key = tasksDatabaseReference.push().getKey();
        card.setId(key);
        tasksDatabaseReference.child(key).setValue(card).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getContext(), key, Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(getContext() , task.getException().getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_share_post, container, false);
        ButterKnife.bind(this, view);
        firebaseDatabase = FirebaseDatabase.getInstance();
        tasksDatabaseReference = firebaseDatabase.getReference().child("tasks");

        final ArrayList<FeedCard> taskArrayList = new ArrayList<>();

        tasksDatabaseReference.orderByChild("id").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                FeedCard card = null;
                taskArrayList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    card = snapshot.getValue(FeedCard.class);
                    taskArrayList.add(card);

                }

                FeedAdapter adapter = new FeedAdapter(getActivity(), taskArrayList);
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        FloatingActionButton addedOnce = new FloatingActionButton(getContext());
        addedOnce.setTitle("Added once");
        floatingActionButton.addButton(addedOnce);


        return view;
    }

}
