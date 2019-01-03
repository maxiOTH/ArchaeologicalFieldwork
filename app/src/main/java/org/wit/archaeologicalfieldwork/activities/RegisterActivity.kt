package org.wit.archaeologicalfieldwork.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.startActivity
import org.wit.archaeologicalfieldwork.R



class RegisterActivity : AppCompatActivity(), AnkoLogger {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        info("RegisterActivity started...")

        register_button_register.setOnClickListener{
            val email =  email_edittext_register.text.toString()
            val password = password_edittext_register.text.toString()

            Log.d("RegisterActivity", "Email is: " + email)
            Log.d("RegisterActivity", "Password is: $password")
        }

        already_have_an_account_text_view.setOnClickListener{
            Log.d("RegisterActivity", "Try to show login activity")
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }

    }
}
