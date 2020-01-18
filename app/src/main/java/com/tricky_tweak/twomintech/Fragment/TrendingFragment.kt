package com.tricky_tweak.twomintech.Fragment


import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tricky_tweak.twomintech.R
import com.tricky_tweak.twomintech.activity.AsyncResponse
import com.tricky_tweak.twomintech.adapter.RecyclerViewAdapter
import com.tricky_tweak.twomintech.model.News
import org.jsoup.Jsoup
import java.io.IOException
import java.net.UnknownHostException
import java.util.ArrayList
import android.content.Context
import android.content.Intent
import com.tricky_tweak.twomintech.activity.NewsDetails
import com.tricky_tweak.twomintech.adapter.onItemClickListener

/* create by pratik katairya
 * date 18:01:2020
 * created under android development training
 * */

class TrendingFragment : Fragment(), AsyncResponse, onItemClickListener {

    private lateinit var recyclerView : RecyclerView
    private lateinit var  recyclerViewAdapter: RecyclerViewAdapter
    private var newsList : ArrayList<News> = ArrayList()
    private var myAsyncTask : MyAsyncTask = MyAsyncTask()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.fragment_trending, container, false)

        myAsyncTask.delegate = this
        myAsyncTask.execute()

        recyclerView = view.findViewById(R.id.recycler_view)
        val context : Context = this.context!!
        recyclerViewAdapter = RecyclerViewAdapter(context, newsList, 0, this)
        val linearLayoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = recyclerViewAdapter
        recyclerViewAdapter.notifyDataSetChanged()

        return view
    }

    override fun processFinish(output: Any) {
        for (x in output as ArrayList<News>) {
            newsList.add(x)
            recyclerViewAdapter.notifyDataSetChanged()
        }
    }

    override fun onItemClicked(news: News) {
        startActivity(
            Intent(activity, NewsDetails::class.java).putExtra("imageUrl", news.ImageUrl).putExtra(
                "newsTitle",
                news.title
            ).putExtra("newsLink", news.pageLink)
        )
    }

}

class MyAsyncTask : AsyncTask<Void, Void, ArrayList<News>>() {


    var delegate: AsyncResponse? = null

    override fun doInBackground(vararg voids: Void): ArrayList<News> {
        val list = ArrayList<News>()

        try {
            val document = Jsoup.connect("https://techcrunch.com").get()
            val element = document.getElementById("root")
            val title = element.select("h2.post-block__title")
            val content = element.select("div.post-block__content")
            val links = element.select("h2 a[href]")
            val img = element.select("img")

            for (i in title.indices) {
                val titlex = title[i].text()
                val contentx = content[i].text()
                val imageUrlx = img[i].attr("src")
                val linkx = links[i].attr("href")
                val n = News(titlex, contentx, imageUrlx, linkx)

                list.add(n)
            }
        } catch (ew: UnknownHostException) {
            println("unable to connect to the host ")
        } catch (ew: NumberFormatException) {
            println("unable to connect to the host ")
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return list
    }

    override fun onPostExecute(result: ArrayList<News>) {
        delegate!!.processFinish(result)
    }
}
