package com.tricky_tweak.twomintech.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tricky_tweak.twomintech.R
import kotlinx.android.synthetic.main.activity_signin.*

/* create by pratik katairya
* date 07:01:2020
* created under android development training
* */

class Signin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        activity_login_mb_login.setOnClickListener {
            startActivity(Intent(this, Signup :: class.java))
        }

        activity_login_tv_create_account.setOnClickListener {
            startActivity(Intent(this, Signup :: class.java))
        }
    }
}
