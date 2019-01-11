package org.wit.archaeologicalfieldwork.views

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import org.wit.archaeologicalfieldwork.R
import org.wit.archaeologicalfieldwork.views.login_register.RegisterView

class SplashScreenActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {

        val SPLASH_TIME_OUT = 4000
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        Handler().postDelayed(object:Runnable{
             override fun run(){
                val home = Intent(this@SplashScreenActivity, RegisterView::class.java)
                 startActivity(home)
                 finish()
            }
        },SPLASH_TIME_OUT.toLong())



    }
}
