package org.wit.archaeologicalfieldwork.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import org.wit.archaeologicalfieldwork.R

class SplashScreenActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {

        val SPLASH_TIME_OUT = 4000
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        Handler().postDelayed(object:Runnable{
             override fun run(){
                val home = Intent(this@SplashScreenActivity, RegisterActivity::class.java)
                 startActivity(home)
                 finish()
            }
        },SPLASH_TIME_OUT.toLong())



    }
}
