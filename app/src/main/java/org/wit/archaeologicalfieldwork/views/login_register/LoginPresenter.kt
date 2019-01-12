package org.wit.archaeologicalfieldwork.views.login_register

import com.google.firebase.auth.FirebaseAuth
import org.jetbrains.anko.toast
import org.wit.archaeologicalfieldwork.views.BasePresenter
import org.wit.archaeologicalfieldwork.views.BaseView
import org.wit.archaeologicalfieldwork.views.VIEW

class LoginPresenter(view:BaseView):BasePresenter(view){

    fun doLogin(email:String, password:String){

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if(!it.isSuccessful){
                        view?.toast("E-Mail or Password incorrect")
                        return@addOnCompleteListener
                        }else{
                            view?.navigateTo(VIEW.LIST)
                        }
                    }.addOnFailureListener{
                    view?.toast("Faild to sign in")
                }
                }
        }

