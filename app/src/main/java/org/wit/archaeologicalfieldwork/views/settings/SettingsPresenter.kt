package org.wit.archaeologicalfieldwork.views.settings


import com.google.firebase.auth.FirebaseAuth
import org.wit.archaeologicalfieldwork.views.BasePresenter
import org.wit.archaeologicalfieldwork.views.BaseView

class SettingsPresenter(view:BaseView):BasePresenter(view){


    fun showEmailFromUser(){
        val user = FirebaseAuth.getInstance().currentUser
        if(user != null){

        }


    }
}


