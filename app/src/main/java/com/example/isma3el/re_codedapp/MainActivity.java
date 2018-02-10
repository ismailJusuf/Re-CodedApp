package com.example.isma3el.re_codedapp;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.example.isma3el.re_codedapp.Fragments.FeedFragment;
import com.example.isma3el.re_codedapp.Fragments.ProfileFragment;
import com.example.isma3el.re_codedapp.Fragments.SharePostFragment;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private SectionsPagerAdapter mSectionspagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSectionspagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        viewPager = findViewById(R.id.main_container);
        viewPager.setAdapter(mSectionspagerAdapter);

        tabLayout = (TabLayout) findViewById(R.id.main_activity_tabs);

        tabLayout.setupWithViewPager(viewPager);
        setUpTabIcons ();
    }

    public void setUpTabIcons () {
        tabLayout.getTabAt(0).setIcon(R.drawable.tab_user );
        tabLayout.getTabAt(1).setIcon(R.drawable.news_paper);
        tabLayout.getTabAt(2).setIcon(R.drawable.post);
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



