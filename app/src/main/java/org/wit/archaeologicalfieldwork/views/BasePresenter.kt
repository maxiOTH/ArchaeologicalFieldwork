package org.wit.archaeologicalfieldwork.views

import android.content.Intent
import org.wit.archaeologicalfieldwork.main.MainApp
import org.wit.archaeologicalfieldwork.models.UserModel

open class BasePresenter (var view:BaseView?){

    var app:MainApp = view?.application as MainApp

    var user = UserModel()


    open fun doActivityResult(requestCode: Int, resultCode:Int,data:Intent){

    }

    open fun doRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
    }

    open fun onDestroy() {
        view = null
    }
}