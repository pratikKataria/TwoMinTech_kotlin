package com.tricky_tweak.twomintech.Fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.tricky_tweak.twomintech.R

/* create by pratik katairya
 * date 18:01:2020
 * created under android development training
 * */

class YourLibraryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_your_library, container, false)
    }


}
