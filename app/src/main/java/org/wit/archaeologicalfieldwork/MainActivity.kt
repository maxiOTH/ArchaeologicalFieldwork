package org.wit.archaeologicalfieldwork

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class MainActivity : AppCompatActivity(), AnkoLogger {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        info("MainActivity started...")

        login_btn.setOnClickListener{
            var status = if(usereamil_et.text.toString().equals("maximilian-bergbauer@web.de")
            && password_et.text.toString().equals("1234")){
                "Logged in successfully"
            }else{
                "LogIn Failed"
            }
            Toast.makeText(this,status,Toast.LENGTH_SHORT).show()
        }
    }@
}
