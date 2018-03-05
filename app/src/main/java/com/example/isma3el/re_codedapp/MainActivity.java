package com.example.isma3el.re_codedapp;

import android.content.Intent;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ListView;

import com.example.isma3el.re_codedapp.Adapters.FeedAdapter;
import com.example.isma3el.re_codedapp.Fragments.FeedFragment;
import com.example.isma3el.re_codedapp.Fragments.ProfileFragment;
import com.example.isma3el.re_codedapp.Fragments.SharePostFragment;
import com.example.isma3el.re_codedapp.Models.FeedCard;
import com.example.isma3el.re_codedapp.Models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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

public class MainActivity extends BaseActivity {

    private FirebaseAuth firebaseAuth;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference feedsDatabaseReference;
    public DataRefreshListener profileListener;
    public DataRefreshListener feedListener;
    public DataRefreshListener sharePostListener;

    @BindView(R.id.main_toolbar)
    android.support.v7.widget.Toolbar toolbar;
    @BindView(R.id.main_container)
    ViewPager viewPager;
    @BindView(R.id.main_activity_tabs)
    TabLayout tabLayout;


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
                tabLayout.getTabAt(0).setIcon(R.drawable.user_yellow);
                tabLayout.getTabAt(1).setIcon(R.drawable.news_paper_grey);
                tabLayout.getTabAt(2).setIcon(R.drawable.post);
                break;
            case 1:
                tabLayout.getTabAt(0).setIcon(R.drawable.tab_user);
                tabLayout.getTabAt(1).setIcon(R.drawable.news_paper);
                tabLayout.getTabAt(2).setIcon(R.drawable.post);
                break;
            case 2:
                tabLayout.getTabAt(0).setIcon(R.drawable.tab_user);
                tabLayout.getTabAt(1).setIcon(R.drawable.news_paper_grey);
                tabLayout.getTabAt(2).setIcon(R.drawable.ic_plus_yellow);
                break;
            default:
                tabLayout.getTabAt(0).setIcon(R.drawable.user_yellow);
                tabLayout.getTabAt(1).setIcon(R.drawable.news_paper_grey);
                tabLayout.getTabAt(2).setIcon(R.drawable.post);
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
                    return new ProfileFragment();
                case 1:
                    return new FeedFragment();
                case 2:
                    return new SharePostFragment();
                default:
                    return new FeedFragment();
            }
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
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
                .addDrawerItems(item1, item2, item3, item4)
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
                                break;
                            case 3:
                                break;
                            case 4:
                                firebaseAuth.signOut();
                                Intent intent3 = new Intent(MainActivity.this, LoginActivity.class);
                                startActivity(intent3);
                                break;
                        }

                        return true;
                    }
                }).build();


    }

}
