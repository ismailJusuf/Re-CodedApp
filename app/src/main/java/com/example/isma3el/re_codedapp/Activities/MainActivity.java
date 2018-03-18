package com.example.isma3el.re_codedapp.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.isma3el.re_codedapp.BaseActivity;
import com.example.isma3el.re_codedapp.Fragments.FeedFragment;
import com.example.isma3el.re_codedapp.Fragments.taskFragment;
import com.example.isma3el.re_codedapp.Models.FeedCard;
import com.example.isma3el.re_codedapp.Models.User;
import com.example.isma3el.re_codedapp.R;
import com.github.clans.fab.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    private FirebaseAuth firebaseAuth;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference feedsDatabaseReference;

    @BindView(R.id.main_toolbar)
    android.support.v7.widget.Toolbar toolbar;
    @BindView(R.id.main_container)
    ViewPager viewPager;
    @BindView(R.id.main_activity_tabs)
    TabLayout tabLayout;
    @BindView(R.id.task_fab)
    FloatingActionButton taskFab;


    private SectionsPagerAdapter mSectionspagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        new DrawerBuilder().withActivity(this).build();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        feedsDatabaseReference = firebaseDatabase.getReference().child("feeds");

        mSectionspagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mSectionspagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        if (getUser().getType() == 0) {
            taskFab.setVisibility(View.GONE);
        } else if (getUser().getType() == 1) {
            taskFab.setVisibility(View.VISIBLE);
        }


        final ArrayList<FeedCard> feedArrayList = new ArrayList<>();
        User user = getUser();
        setUpTabIcons(0);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                setUpTabIcons(position);

                switch (position) {

                    case 0:
                        //profileListener.onProfileRefreshed();
                        break;
                    case 1:
                        //feedListener.onFeedRefreshed();
                        break;

                    case 2:
                        //sharePostListener.onSharePostRefreshed();
                        break;

                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        navigationDrawer();

    }

    public void setUpTabIcons(int position) {
        switch (position) {

            case 0:
                tabLayout.getTabAt(0).setIcon(R.drawable.news_paper);
                tabLayout.getTabAt(1).setIcon(R.drawable.post);
                break;
            case 1:
                tabLayout.getTabAt(0).setIcon(R.drawable.news_paper_grey);
                tabLayout.getTabAt(1).setIcon(R.drawable.ic_plus_yellow);
                break;
            default:
                tabLayout.getTabAt(0).setIcon(R.drawable.news_paper_grey);
                tabLayout.getTabAt(1).setIcon(R.drawable.ic_plus_yellow);
                break;

        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return new FeedFragment();
                case 1:
                    return new taskFragment();
                default:
                    return new FeedFragment();
            }
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }
    }

    public void navigationDrawer() {


        //PrimaryDrawerItem primaryDrawerItem = new PrimaryDrawerItem().withIdentifier( 1 ).withName( "test" );
        SecondaryDrawerItem item1 = new SecondaryDrawerItem().withIdentifier(1)
                .withName("profile");
        SecondaryDrawerItem item2 = new SecondaryDrawerItem().withIdentifier(2)
                .withName("share this app");
        SecondaryDrawerItem item3 = new SecondaryDrawerItem().withIdentifier(3)
                .withName("contact us");
        SecondaryDrawerItem item4 = new SecondaryDrawerItem().withIdentifier(4)
                .withName("classroom");
        SecondaryDrawerItem item5 = new SecondaryDrawerItem().withIdentifier(5)
                .withName("rate app");
        SecondaryDrawerItem item6 = new SecondaryDrawerItem().withIdentifier(6)
                .withName("sign out");
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.profile_background)
                .addProfiles(
                        new ProfileDrawerItem().withName(getUser().getFullName()).withEmail(
                                getUser().getEmail()).withIcon(getResources().getDrawable(R.drawable.ibrahim_pp
                        ))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();

        //create the drawer and remember the `Drawer` result object
        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult)
                .addDrawerItems(item1, item2, item3, item4, item5, item6)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D
                        switch (position) {

                            case 1:
                                Intent intent1 = new Intent(MainActivity.this, ProfileActivity.class);
                                startActivity(intent1);
                                break;
                            case 2:
                                Intent shareIntent = new Intent();
                                shareIntent.setAction(Intent.ACTION_SEND);
                                shareIntent.putExtra(Intent.EXTRA_TEXT, "download Re:Coded app Now from Play Store " + " https://www.re-coded.com/");
                                shareIntent.setType("text/plain");
                                startActivity(shareIntent);
                                break;
                            case 3:
                                break;
                            case 4:
                                Intent intent4 = new Intent(MainActivity.this, ClassRoomStudentsActivity.class);
                                startActivity(intent4);
                                break;
                            case 5:
                                Intent intent5 = new Intent(android.content.Intent.ACTION_VIEW);
                                intent5.setData(Uri.parse("https://www.re-coded.com/"));
                                startActivity(intent5);
                                break;
                            case 6:
                                firebaseAuth.signOut();
                                removeUser();
                                Intent intent6 = new Intent(MainActivity.this, LoginActivity.class);
                                startActivity(intent6);
                                finish();

                                break;
                        }

                        return true;
                    }
                }).build();


    }

    @OnClick(R.id.progress_fab)
    public void progressFab() {

        Intent progressIntent = new Intent(MainActivity.this, SharePostActivity.class);
        progressIntent.putExtra("postType", 0);
        startActivity(progressIntent);

    }

    @OnClick(R.id.status_fab)
    public void statusFab() {

        Intent statusIntent = new Intent(MainActivity.this, SharePostActivity.class);
        statusIntent.putExtra("postType", 1);
        startActivity(statusIntent);

    }

    @OnClick(R.id.task_fab)
    public void taskFab() {

        Intent taskIntent = new Intent(MainActivity.this, SharePostActivity.class);
        taskIntent.putExtra("postType", 2);
        startActivity(taskIntent);

    }

}
