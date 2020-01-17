package com.tricky_tweak.twomintech.Fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tricky_tweak.twomintech.R


/**
 * A simple [Fragment] subclass.
 */
class TrendingFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.fragment_trending, container, false)


        return view
    }

}// Required empty public constructor
