package org.wit.archaeologicalfieldwork

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class LoginActivity : AppCompatActivity(), AnkoLogger {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        info("LoginActivity started...")

        login_btn.setOnClickListener{
            var status = if(usereamil_et.text.toString().equals("maximilian")
            && password_et.text.toString().equals("1234")){
                val nextPageIntent = Intent(this, ListOfSitesActivity::class.java)
                startActivity(nextPageIntent)
                "Logged in successfully"


            }else{
                "Login Failed"
            }
            Toast.makeText(this,status,Toast.LENGTH_SHORT).show()

        }
    }
}
