package com.tricky_tweak.twomintech.activity;


import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.tricky_tweak.twomintech.Fragment.SearchFragment;
import com.tricky_tweak.twomintech.Fragment.TrendingFragment;
import com.tricky_tweak.twomintech.Fragment.YourLibraryFragment;
import com.tricky_tweak.twomintech.R;

import static com.google.android.material.bottomnavigation.BottomNavigationView.*;

/* create by pratik katairya
 * date 12:01:2020
 * created under android development training
 * */

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "trending";
    private static final String TAG2 = "search";
    private static final String TAG3 = "library";

    final Fragment fragment_trending = new TrendingFragment();
    final Fragment fragment_search= new SearchFragment();
    final Fragment fragment_library = new YourLibraryFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragment_trending;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView bottomNavigationView = findViewById(R.id.activity_main_bnv);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fm.beginTransaction().add(R.id.activity_main_fl, fragment_library, "3").hide(fragment_library).commit();
        fm.beginTransaction().add(R.id.activity_main_fl, fragment_search, "2").hide(fragment_search).commit();
        fm.beginTransaction().add(R.id.activity_main_fl, fragment_trending, "1").commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = menuItem -> {
        switch (menuItem.getItemId()) {
            case R.id.navigation_trending:
                fm.beginTransaction().hide(active).show(fragment_trending).commit();
                active = fragment_trending;
                return  true;
            case R.id.navigation_search:
                fm.beginTransaction().hide(active).show(fragment_search).commit();
                active = fragment_search;
                return  true;
            case R.id.navigation_library:
                fm.beginTransaction().hide(active).show(fragment_library).commit();
                active = fragment_library;
                return  true;
        }
        return false;
    };
}
