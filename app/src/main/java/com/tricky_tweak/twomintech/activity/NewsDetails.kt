package com.tricky_tweak.twomintech.activity

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.tricky_tweak.twomintech.R
import kotlinx.android.synthetic.main.activity_news_details.*
import kotlinx.android.synthetic.main.activity_signin.*
import org.jsoup.Jsoup
import java.io.IOException

class NewsDetails : AppCompatActivity(), AsyncResponse {

    override fun noInternetListener(flag: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    lateinit var loadingAnim: LottieAnimationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)

        loadingAnim = findViewById(R.id.lottieAnimationView)

        setSupportActionBar(toolbar)
        toolbar.title = " "
        back_btn.setOnClickListener { onBackPressed() }


        val bundle: Bundle = intent.extras!!
        val title = bundle.getString("newsTitle")
        val imageLink = bundle.getString("imageUrl")
        val pageLink = bundle.getString("newsLink")

//        val bundle: Bundle = intent.extras!!
//        val title = ""
//        val imageLink =""
//        val pageLink = ""


        activity_news_details_tv_title.text = title

        Glide.with(this).load(imageLink).into(activity_news_details_img)

        var newsContent = NewsContent(pageLink!!)
        newsContent.delegate = this
        newsContent.execute()


    }

    override fun processFinish(output: Any?) {
        activity_news_details_tv_content.text = output as String
        if (output.toString().isNotEmpty()) {
            loadingAnim.pauseAnimation()
            loadingAnim.visibility = View.GONE
            activity_news_details_tv_content.visibility = View.VISIBLE
        }
    }

}

class NewsContent(pageLink: String) : AsyncTask<Void, Void, String>() {

    var delegate: AsyncResponse? = null
    var pageLink: String? = pageLink


    override fun doInBackground(vararg params: Void?): String {
        try {
            if (pageLink!!.isNotEmpty()) {
                return getPageData(pageLink!!)
            }
        } catch (e: IOException) {
            e.printStackTrace()
            Log.e("network error debugging", "sa === " + e.message)
            
        }
        return ""
    }

    @Throws(IOException::class)
    fun getPageData(link: String): String {
        val document = Jsoup.connect(link).get()
        val paragraph = document.select("p")
        val build = StringBuilder()
        for (p in paragraph) {
            build.append(p.text())
            build.append("\n\n")
        }
        return build.toString()
    }

    override fun onPostExecute(s: String) {
        delegate!!.processFinish(s)
    }
}