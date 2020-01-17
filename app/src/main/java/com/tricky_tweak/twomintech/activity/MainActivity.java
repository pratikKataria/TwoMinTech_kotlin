package com.tricky_tweak.twomintech.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.tricky_tweak.twomintech.Fragment.SearchFragment;
import com.tricky_tweak.twomintech.Fragment.TrendingFragment;
import com.tricky_tweak.twomintech.Fragment.YourLibraryFragment;
import com.tricky_tweak.twomintech.R;
import com.tricky_tweak.twomintech.adapter.RecyclerViewAdapter;
import com.tricky_tweak.twomintech.adapter.onItemClickListener;
import com.tricky_tweak.twomintech.model.News;
import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;


/* create by pratik katairya
 * date 12:01:2020
 * created under android development training
 * */

public class MainActivity extends AppCompatActivity {

//    private static final int ARRAY_LIST = 0;
//    private static final int STRING = 1;
//    RecyclerView recyclerView;
//    ArrayList<News> arrayList = new ArrayList<>();
//    RecyclerViewAdapter rvAdapter;
//    MyAsyncTask asyncTask = new MyAsyncTask();
//    private String content = "";

    private BottomNavigationView bottomNavigationView;

    private TrendingFragment trendingFragment;
    private SearchFragment searchFragment;
    private YourLibraryFragment yourLibraryFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bottomNavigationView = findViewById(R.id.activity_main_bnv);

        trendingFragment = new TrendingFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_fl, trendingFragment).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            Fragment fragment;
            switch (menuItem.getItemId()) {
                case R.id.navigation_trending:
                    fragment = new TrendingFragment();
                    loadFragment(fragment);
                    return  true;
                case R.id.navigation_search:
                    fragment = new SearchFragment();
                    loadFragment(fragment);
                    return  true;
                case R.id.navigation_library:
                    fragment = new YourLibraryFragment();
                    loadFragment(fragment);
                    return  true;
            }
            return false;
        });

//        recyclerView = findViewById(R.id.recycler_view);
//
//        asyncTask.delegate = this;
//        asyncTask.execute();
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.activity_main_fl, fragment).addToBackStack(null).commit();
    }

//    void init_recyclerView(ArrayList<News> arrayList) {
//        rvAdapter = new RecyclerViewAdapter(this, arrayList, 0, this);
//        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
//        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setAdapter(rvAdapter);
//        rvAdapter.notifyDataSetChanged();
//    }


//    @Override
//    public void processFinish(Object output) {
//////        arrayList = output;
//////        for (News x : arrayList) {
//////            arrayList.add(x);
//////            rvAdapter.notifyDataSetChanged();
//////        }
//////        for (News x : output) Log.e("NEWS :::::::: ", x.getTitle() + x.getDescription() + ":::::::::::::::::::" + x.getImageUrl());
//
//                init_recyclerView((ArrayList<News>) output);
//
//    }

//    @Override
//    public void onItemClicked(@NotNull News news) {
//            startActivity(new Intent(this, NewsDetails.class).putExtra("imageUrl", news.getImageUrl()).putExtra("newsTitle", news.getTitle()).putExtra("newsLink", news.getPageLink()));
//    }
}

//class MyAsyncTask extends AsyncTask<Void, Void, ArrayList<News>> {
//
//
//    public AsyncResponse delegate = null;
//
//    @Override
//    protected ArrayList<News> doInBackground(Void... voids) {
//        ArrayList<News> list = new ArrayList<>();
//
//        try {
//            Document document = Jsoup.connect("https://techcrunch.com").get();
//            Element element = document.getElementById("root");
//            Elements title = element.select("h2.post-block__title");
//            Elements content = element.select("div.post-block__content");
//            Elements links = element.select("h2 a[href]");
//            Elements img = element.select("img");
//
//            for (int i = 0; i < title.size(); i++) {
//                String titlex = title.get(i).text();
//                String contentx = content.get(i).text();
//                String imageUrlx = img.get(i).attr("src");
//                String linkx = links.get(i).attr("href");
//                News n = new News(titlex, contentx, imageUrlx, linkx);
//
//                list.add(n);
//            }
//        } catch (UnknownHostException | NumberFormatException ew) {
//            System.out.println("unable to connect to the host ");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return list;
//    }
//
//    @Override
//    protected void onPostExecute(ArrayList<News> result) {
//        delegate.processFinish(result);
//    }
//}
