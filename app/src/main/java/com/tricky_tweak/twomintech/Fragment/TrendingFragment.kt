package com.tricky_tweak.twomintech.Fragment


import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.tricky_tweak.twomintech.R
import com.tricky_tweak.twomintech.activity.AsyncResponse
import com.tricky_tweak.twomintech.activity.NewsDetails
import com.tricky_tweak.twomintech.adapter.RecyclerViewAdapter
import com.tricky_tweak.twomintech.adapter.onItemClickListener
import com.tricky_tweak.twomintech.model.News
import kotlinx.android.synthetic.main.fragment_trending.*
import org.jsoup.Jsoup
import java.io.IOException
import java.net.UnknownHostException
import java.util.*

/* create by pratik katairya
 * date 18:01:2020
 * created under android development training
 * */

class TrendingFragment : Fragment(), AsyncResponse, onItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private var newsList: ArrayList<News> = ArrayList()
    private var myAsyncTask: MyAsyncTask = MyAsyncTask()


    fun init_reyclerView(view: View) {
        val context: Context = this.context!!
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerViewAdapter = RecyclerViewAdapter(context, newsList, 0, this)
        val linearLayoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = recyclerViewAdapter
        recyclerViewAdapter.notifyDataSetChanged()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_trending, container, false)


        init_reyclerView(view)

        val refresh: SwipeRefreshLayout = view.findViewById(R.id.fragment_trend_srl)

        myAsyncTask.delegate = this
        myAsyncTask.execute()

        refresh.setOnRefreshListener {
            myAsyncTask = MyAsyncTask()
            myAsyncTask.delegate = this
            newsList.clear()
            recyclerViewAdapter.notifyDataSetChanged()

            showActiveLayout()

            Handler().postDelayed({
                myAsyncTask.execute()
                refresh.isRefreshing = false
            }, 2000)
        }


        return view
    }

    override fun processFinish(output: Any) {
        for (x in output as ArrayList<News>) {
            newsList.add(x)
            recyclerViewAdapter.notifyDataSetChanged()
        }
    }

    override fun noInternetListener(flag: Int) {
        Log.e("messga", "no internet connection")

        activity?.runOnUiThread {
            hideActiveLayout()
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

    private fun showActiveLayout() {
        empty_layout.visibility = GONE

        constraintLayout.visibility = VISIBLE
        textView2.visibility = VISIBLE
        recyclerView.visibility = VISIBLE
    }

    fun hideActiveLayout() {
        empty_layout.visibility = VISIBLE

        constraintLayout.visibility = GONE
        textView2.visibility = GONE
        recyclerView.visibility = GONE
    }

//    fun showSnak(boolean: Boolean) {
//        var message: String
//        lateinit var snackbar : Snackbar
//        var layout: CoordinatorLayout = viewSnack
//        if (boolean) {
//         message = "Good! connected to internet"
//            snackbar = Snackbar.make(layout, message, Snackbar.LENGTH_SHORT)
//        } else {
//            message = "Sorry! Not Connected to internet"
//            snackbar = Snackbar.make(layout, message, Snackbar.LENGTH_INDEFINITE)
//            snackbar.setAction("Retry", {
//            })
//        }
//        var view : View  = snackbar.view
//        var textView : TextView = view.findViewById(com.google.android.material.R.id.snackbar_text)
//        snackbar.show()
//    }

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
            delegate!!.noInternetListener(1)
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
