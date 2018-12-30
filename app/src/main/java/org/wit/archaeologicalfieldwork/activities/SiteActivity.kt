package org.wit.archaeologicalfieldwork.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_site.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.toast
import org.wit.archaeologicalfieldwork.R
import org.wit.archaeologicalfieldwork.main.MainApp
import org.wit.archaeologicalfieldwork.models.SiteModel


class SiteActivity : AppCompatActivity(),AnkoLogger{

    var site = SiteModel()
    lateinit var app : MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_site)
        setSupportActionBar(findViewById(R.id.toolbar))
        app = application as MainApp

        btnAdd.setOnClickListener(){
            site.name = siteName.text.toString()

            if(site.name.isNotEmpty()){
                app!!.sites.add(site.copy())

            }
            else{
                toast("Pleas Enter a name")
            }

        }
    }




}