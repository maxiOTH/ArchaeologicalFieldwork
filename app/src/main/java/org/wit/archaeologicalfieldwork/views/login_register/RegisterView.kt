package org.wit.archaeologicalfieldwork.views.login_register

import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast
import org.wit.archaeologicalfieldwork.R
import org.wit.archaeologicalfieldwork.views.BaseView
import org.wit.archaeologicalfieldwork.views.VIEW

class RegisterView : BaseView() {

    lateinit var presenter: RegisterPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        presenter = initPresenter(RegisterPresenter(this)) as RegisterPresenter



        register_button_register.setOnClickListener{
            val email = email_edittext_register.text.toString()
            val password = password_edittext_register.text.toString()
            if (email == "" || password == "") {
                toast("Please enter E-Mail and Password")
            }else{
                presenter.doRegister(email, password)
            }
        }

        already_have_account_text_view_register.setOnClickListener{
            val intent = Intent (this@RegisterView, LoginView::class.java)
            startActivity(intent)
        }

    }
}