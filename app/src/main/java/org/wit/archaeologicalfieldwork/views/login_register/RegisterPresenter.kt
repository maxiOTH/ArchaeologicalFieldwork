package org.wit.archaeologicalfieldwork.views.login_register

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.toast
import org.wit.archaeologicalfieldwork.models.UserModel
import org.wit.archaeologicalfieldwork.views.BasePresenter
import org.wit.archaeologicalfieldwork.views.BaseView
import org.wit.archaeologicalfieldwork.views.VIEW


class RegisterPresenter(view: BaseView): BasePresenter(view) {




    fun doRegister(email:String, password:String){
        val user = UserModel()

        if(email.isEmpty()|| password.isEmpty()){
            view?.toast("Please enter an E-Mail and Password to register")
            return
        }

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if(!it.isSuccessful){
                    return@addOnCompleteListener
                }else{
                    async(UI){
                        user.email = email
                        user.password = password
                        app.users.create(user)
                        app.users.setCurrentUser(user)
                    }
                    view?.toast("Successfully created user and signed in")
                    view?.navigateTo(VIEW.LIST)
                }
            }.addOnCompleteListener {

            }
    }
}