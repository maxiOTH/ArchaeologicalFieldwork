package org.wit.archaeologicalfieldwork.views.settings


import android.os.Bundle
import android.text.Editable
import android.widget.TextView
import androidx.preference.EditTextPreference
import androidx.preference.PreferenceFragmentCompat
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.notification_template_lines_media.view.*
import org.jetbrains.anko.AnkoLogger
import org.wit.archaeologicalfieldwork.R
import org.wit.archaeologicalfieldwork.models.SiteModel
import org.wit.archaeologicalfieldwork.views.BaseView
import org.wit.archaeologicalfieldwork.views.sitelist.SiteAdapter
import org.wit.archaeologicalfieldwork.views.sitelist.SiteListener

class SettingsView :BaseView(), AnkoLogger {

    lateinit var presenter : SettingsPresenter


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        init(toolbar_settings, true)
        presenter = initPresenter(SettingsPresenter(this)) as SettingsPresenter

        val user = FirebaseAuth.getInstance().currentUser
        email_from_user.setText("${user?.email}")

        number_of_sites_textview_settings.setText("2")

        number_visited_textview_settings.setText("2")

    }

}


private operator fun Editable.invoke(s: String) {

}

