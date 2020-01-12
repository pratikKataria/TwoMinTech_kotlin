package com.tricky_tweak.twomintech.model


import android.os.AsyncTask
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import java.io.IOException
import java.net.UnknownHostException
import java.util.ArrayList
import java.util.HashMap
import android.R
import android.widget.TextView



class TechCrunchScraper(var id : Int) {
//


    //                System.out.println("title ::: "+title.get(i).text());
    //                System.out.println("page link ::: " + links.get(i).attr("href"));
    //                System.out.println("content ::: "+content.get(i).text() +" \n\n");
    //                System.out.println("Img :::  "+img.get(i).attr("src"));
    //                System.out.println("----------------------------------------------------------------------------------------");
    //                System.out.println("#######################################   paragraph  ###################################");
    ////                getPageData(links.get(i).attr("href"));
    //                System.out.println("#######################################   paragraph  ###################################");
    //                System.out.println("----------------------------------------------------------------------------------------");
    public val data: Map<String, ArrayList<String>>
        get() {

            val news = HashMap<String, ArrayList<String>>()

            try {
                val document = Jsoup.connect("https://techcrunch.com").get()
                val element = document.getElementById("root")
                val title = element.select("h2.post-block__title")
                val content = element.select("div.post-block__content")
                val links = element.select("h2 a[href]")
                val img = element.select("img")

                for (i in title.indices) {
                    val newsData = ArrayList<String>()
                    newsData.add(title[i].text())
                    newsData.add(content[i].text())
                    news["news $i"] = newsData
                }
            } catch (ew: UnknownHostException) {
                println("unable to connect to the host ")
            } catch (ew: NumberFormatException) {
                println("unable to connect to the host ")
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return news

        }



    companion object {

        @Throws(IOException::class)
        fun getPageData(link: String) {
            val document = Jsoup.connect(link).get()
            val paragraph = document.select("p")
            for (p in paragraph) {
                println(p.text())
            }
        }

        fun getData(): Map<String, ArrayList<String>> {
            val news = HashMap<String, ArrayList<String>>()

            try {
                val document = Jsoup.connect("https://techcrunch.com").get()
                val element = document.getElementById("root")
                val title = element.select("h2.post-block__title")
                val content = element.select("div.post-block__content")
                val links = element.select("h2 a[href]")
                val img = element.select("img")

                for (i in title.indices) {
                    val newsData = ArrayList<String>()
                    newsData.add(title[i].text())
                    newsData.add(content[i].text())
                    news["news $i"] = newsData
                }
            } catch (ew: UnknownHostException) {
                println("unable to connect to the host ")
            } catch (ew: NumberFormatException) {
                println("unable to connect to the host ")
            } catch (e: IOException) {
                e.printStackTrace()
            }

            return news
        }

    }

}
