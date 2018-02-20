package com.example.isma3el.re_codedapp;

import android.content.Intent;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Toolbar;

import com.example.isma3el.re_codedapp.Fragments.FeedFragment;
import com.example.isma3el.re_codedapp.Fragments.ProfileFragment;
import com.example.isma3el.re_codedapp.Fragments.SharePostFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference usersDatabaseReference;
    private FirebaseAuth firebaseAuth;

    @BindView(R.id.main_toolbar)
    android.support.v7.widget.Toolbar toolbar;
    @BindView(R.id.main_container)
    ViewPager viewPager;
    @BindView(R.id.main_activity_tabs)
    TabLayout tabLayout;

    private SectionsPagerAdapter mSectionspagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        ButterKnife.bind( this );
        new DrawerBuilder().withActivity( this ).build();

        firebaseDatabase = FirebaseDatabase.getInstance();
        usersDatabaseReference = firebaseDatabase.getInstance().getReference().child( "registeredStudents" );
        firebaseAuth = FirebaseAuth.getInstance();


        mSectionspagerAdapter = new SectionsPagerAdapter( getSupportFragmentManager() );
        viewPager.setAdapter( mSectionspagerAdapter );
        tabLayout.setupWithViewPager( viewPager );

        setUpTabIcons();

        //PrimaryDrawerItem primaryDrawerItem = new PrimaryDrawerItem().withIdentifier( 1 ).withName( "test" );
        SecondaryDrawerItem item1 = new SecondaryDrawerItem().withIdentifier( 1 )
                .withName( "login" );
        SecondaryDrawerItem item2 = new SecondaryDrawerItem().withIdentifier( 2 )
                .withName( "signup" );
        SecondaryDrawerItem item3 = new SecondaryDrawerItem().withIdentifier( 3 )
                .withName( "sign out" );


        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity( this )
                .withHeaderBackground( R.drawable.profile_background )
                .addProfiles(
                        new ProfileDrawerItem().withName( "user name" ).withEmail(
                                "Email" ).withIcon( getResources().getDrawable( R.drawable.ibrahim_pp
                        ) )
                )
                .withOnAccountHeaderListener( new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                } )
                .build();

        //create the drawer and remember the `Drawer` result object
        Drawer result = new DrawerBuilder()
                .withActivity( this )
                .withToolbar( toolbar )
                .withAccountHeader( headerResult )
                .addDrawerItems( item1, item2, item3 )
                .withOnDrawerItemClickListener( new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D
                        switch (position) {

                            case 1:
                                Intent intent1 = new Intent( MainActivity.this, LoginActivity.class );
                                startActivity( intent1 );
                                finish();
                                break;
                            case 2:
                                Intent intent2 = new Intent( MainActivity.this, SignUpActivity.class );
                                startActivity( intent2 );
                                finish();
                                break;
                            case 3:
                                firebaseAuth.signOut();

                                Intent intent3 = new Intent( MainActivity.this, LoginActivity.class );
                                startActivity( intent3 );
                                finish();
                        }

                        return true;
                    }
                } ).build();
    }

    public void setUpTabIcons() {
        tabLayout.getTabAt( 0 ).setIcon( R.drawable.tab_user );
        tabLayout.getTabAt( 1 ).setIcon( R.drawable.news_paper );
        tabLayout.getTabAt( 2 ).setIcon( R.drawable.post );
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(android.support.v4.app.FragmentManager fm) {
            super( fm );
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
            }
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return new FeedFragment();
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }

}



