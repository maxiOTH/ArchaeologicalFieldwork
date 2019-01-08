package org.wit.archaeologicalfieldwork.activities

import android.content.Intent
import android.os.Bundle

import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import org.wit.archaeologicalfieldwork.R
import org.wit.archaeologicalfieldwork.views.sitelist.SiteListView

class LoginActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        login_button_login.setOnClickListener{
            val email = email_edittext_login.text.toString()
            val password = password_edittext_login.text.toString()

            Log.d("Login","Attempt login with email/pw $email/***")

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
                .addOnCompleteListener {
                    if(!it.isSuccessful){

                        return@addOnCompleteListener
                    }else {
                        Toast.makeText(this,"Successfully signed in", Toast.LENGTH_SHORT).show()
                        Log.d("Main", "Successfully signed in: ${it.result.user.uid}")
                        val intent = Intent(this, SiteListView::class.java)
                        startActivity(intent)
                }
        }.addOnFailureListener {
                    Log.d("Main","Faild to sign in${it.message}")
                    Toast.makeText(this,"Faild to sign in", Toast.LENGTH_SHORT).show()
                }

        }

        back_to_register_text_view_login.setOnClickListener{
            finish()
        }

    }
}