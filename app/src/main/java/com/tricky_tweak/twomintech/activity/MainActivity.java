package com.tricky_tweak.twomintech.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.tricky_tweak.twomintech.R;
import com.tricky_tweak.twomintech.adapter.RecyclerViewAdapter;
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

public class MainActivity extends AppCompatActivity implements AsyncResponse {

    RecyclerView recyclerView;
    ArrayList<News> arrayList = new ArrayList<>();
    RecyclerViewAdapter rvAdapter;

    MyAsyncTask asyncTask = new MyAsyncTask();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);


        asyncTask.delegate = this;

        asyncTask.execute();
    }

    void init_recyclerView(ArrayList<News> arrayList) {
        rvAdapter = new RecyclerViewAdapter(this, arrayList, 0);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(rvAdapter);
        rvAdapter.notifyDataSetChanged();
    }

    @Override
    public void processFinish(@NotNull ArrayList<News> output) {
//        arrayList = output;
//        for (News x : arrayList) {
//            arrayList.add(x);
//            rvAdapter.notifyDataSetChanged();
//        }
//        for (News x : arrayList) Log.e("NEWS :::::::: ", x.getTitle() + x.getDescription());

        init_recyclerView(output);

    }
}

class MyAsyncTask  extends AsyncTask<Void, Void, ArrayList<News>> {



    public AsyncResponse delegate = null;

    @Override
    protected ArrayList<News> doInBackground(Void... voids) {
        ArrayList<News> list = new ArrayList<>();

        try {
            Document document = Jsoup.connect("https://techcrunch.com").get();
            Element element = document.getElementById("root");
            Elements title = element.select("h2.post-block__title");
            Elements content = element.select("div.post-block__content");
            Elements links = element.select("h2 a[href]");
            Elements img = element.select("img");

            for (int i = 0; i < title.size(); i++) {
//                System.out.println("title ::: "+title.get(i).text());
//                System.out.println("page link ::: " + links.get(i).attr("href"));
//                System.out.println("content ::: "+content.get(i).text() +" \n\n");
//                System.out.println("Img :::  "+img.get(i).attr("src"));
                    String titlex = title.get(i).text();
                    String contentx = content.get(i).text();
                    News n = new News(titlex, contentx);

                    list.add(n);

//                System.out.println("----------------------------------------------------------------------------------------");
//                System.out.println("#######################################   paragraph  ###################################");
////                getPageData(links.get(i).attr("href"));
//                System.out.println("#######################################   paragraph  ###################################");
//                System.out.println("----------------------------------------------------------------------------------------");
            }
        } catch (UnknownHostException | NumberFormatException ew) {
            System.out.println("unable to connect to the host ");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    protected void onPostExecute(ArrayList<News> result) {
        delegate.processFinish(result);
    }
}
