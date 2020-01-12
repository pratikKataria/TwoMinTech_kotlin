package com.tricky_tweak.twomintech.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.tricky_tweak.twomintech.R
import com.tricky_tweak.twomintech.model.News

/* create by pratik katairya
* date 09:01:2020
* created under android development training
* */

class RecyclerViewAdapter(context: Context, list: ArrayList<News>?, currentView: Int) :  RecyclerView.Adapter<ViewHolder>() {

    var context: Context = context
    var list: ArrayList<News>? = list
    var currentView : Int ?= currentView

    private val VIEW_TYPE_NEWS = 1
    private val VIEW_TYPE_EMPTY = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var viewHolder : ViewHolder

        if (viewType == VIEW_TYPE_NEWS) {
            val view : View = LayoutInflater.from(parent.context).inflate(R.layout.news_card, parent, false)
            viewHolder = NewsViewHolder(view)
        } else {
            val view : View = LayoutInflater.from(parent.context).inflate(R.layout.layout_empty, parent, false)
            viewHolder = EmptyViewHolder(view)
        }

        return viewHolder
    }

    override fun getItemCount(): Int {
         if (list!!.size == 0) {
         return 1
        } else
            return list!!.size
    }

    override fun getItemViewType(position: Int): Int {
      if (list!!.size == 0) {
          return VIEW_TYPE_EMPTY
      } else return VIEW_TYPE_NEWS
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when(holder.itemViewType) {
            VIEW_TYPE_EMPTY -> {

            }
            VIEW_TYPE_NEWS -> {
                var newLayout : NewsViewHolder = holder as NewsViewHolder
                newLayout.setCard(list!!.get(position).title, list!!.get(position).Description, list!!.get(position).ImageUrl)
            }
        }
    }

    inner class NewsViewHolder(itemView: View) : ViewHolder(itemView) {

        fun setCard(title : String, desc : String, imageUrl : String) {
            itemView.findViewById<TextView>(R.id.news_cardview_title).text = title
            itemView.findViewById<TextView>(R.id.news_cardView_description).text = desc
            var newsThumb : ImageView = itemView.findViewById<ImageView>(R.id.news_cardView_image)

            Log.e("IMageView ", imageUrl)
            Glide.with(context).load(imageUrl).into(newsThumb)

        }

    }

    class EmptyViewHolder(itemView: View) : ViewHolder(itemView) {

    }

}

