package org.wit.archaeologicalfieldwork

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreenActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {

        val SPLASH_TIME_OUT = 4000
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        Handler().postDelayed(object:Runnable{
             override fun run(){
                val home = Intent(this@SplashScreenActivity, LoginActivity::class.java)
                 startActivity(home)
                 finish()
            }
        },SPLASH_TIME_OUT.toLong())



    }
}
