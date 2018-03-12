package com.example.isma3el.re_codedapp.Fragments;

import android.content.ContentUris;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.isma3el.re_codedapp.Adapters.FeedAdapter;
import com.example.isma3el.re_codedapp.BaseActivity;
import com.example.isma3el.re_codedapp.Models.FeedCard;
import com.example.isma3el.re_codedapp.R;
import com.example.isma3el.re_codedapp.SharePostActivity;
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


public class SharePostFragment extends Fragment {


    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference tasksDatabaseReference;
    private DatabaseReference feedsDatabaseReference;


    @BindView(R.id.teacher_list_view)
    ListView listView;
    long eventID = 221;


    @OnClick(R.id.add_post)
    public void addPost() {

        /*long startMillis = System.currentTimeMillis();
        Uri.Builder builder = CalendarContract.CONTENT_URI.buildUpon();
        builder.appendPath("time");
        ContentUris.appendId(builder, startMillis);
        Intent intent = new Intent(Intent.ACTION_VIEW).setData(builder.build());
        startActivity(intent);*/

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
        feedsDatabaseReference = firebaseDatabase.getReference().child("feeds");

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

        /*ContentResolver cr = getActivity().getContentResolver();
        ContentValues values = new ContentValues();
        values.put(CalendarContract.Reminders.MINUTES, 15);
        values.put(CalendarContract.Reminders.EVENT_ID, eventID);
        values.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT);
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return TODO;
        }
        Uri uri = cr.insert(CalendarContract.Reminders.CONTENT_URI, values);*/

        return view;
    }

}
