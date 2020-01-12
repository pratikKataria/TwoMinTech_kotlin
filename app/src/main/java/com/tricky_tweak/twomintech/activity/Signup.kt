package com.tricky_tweak.twomintech.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tricky_tweak.twomintech.R
import kotlinx.android.synthetic.main.activity_signup.*

/* create by pratik katairya
* date 07:01:2020
* created under android development training
* */

class Signup : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        activity_signup_mb_signup.setOnClickListener {
            startActivity(Intent(this, Signin :: class.java))
        }

        activity_signup_tv_login.setOnClickListener {
            startActivity(Intent(this, Signin :: class.java))
        }

    }
}
