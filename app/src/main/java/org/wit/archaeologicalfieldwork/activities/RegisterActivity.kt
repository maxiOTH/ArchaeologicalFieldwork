package org.wit.archaeologicalfieldwork.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*
import org.wit.archaeologicalfieldwork.R

class RegisterActivity:AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        register_button_register.setOnClickListener{
            val email = email_edittext_register.text.toString()
            val password = password_edittext_register.text.toString()

            Log.d("MainActivity","Email is: " + email)
            Log.d("MainActivity","Password is $password")

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener {
                    if(!it.isSuccessful){
                        return@addOnCompleteListener
                    }else {
                        Log.d("Main", "Successfully created user with uid: ${it.result.user.uid}")
                    }
                }
            already_have_account_text_view_register.setOnClickListener{

                Log.d("MainActivity","Try to go to LoginActivity")
                val intent = Intent(this,LoginActivity::class.java)
                startActivity(intent)

            }

        }
    }

}