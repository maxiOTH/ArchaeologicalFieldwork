package org.wit.archaeologicalfieldwork.views.login_register

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast
import org.wit.archaeologicalfieldwork.R
import org.wit.archaeologicalfieldwork.views.BaseView

class LoginView : BaseView() {

    lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        progressBar.visibility= View.GONE
        presenter = initPresenter(LoginPresenter(this)) as LoginPresenter

        login_button_login.setOnClickListener{
            val email = email_edittext_login.text.toString()
            val password = password_edittext_login.text.toString()
            if(email == "" || password == ""){
                toast("Please enter an email and password")
            }else{
                presenter.doLogin(email,password)
            }
        }


            back_to_register_text_view_login.setOnClickListener{
                finish()

            }

    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }
}