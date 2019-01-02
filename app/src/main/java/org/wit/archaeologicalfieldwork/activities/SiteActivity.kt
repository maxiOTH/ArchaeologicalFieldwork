package org.wit.archaeologicalfieldwork.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_site.*
import kotlinx.android.synthetic.main.card_site.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.archaeologicalfieldwork.R
import org.wit.archaeologicalfieldwork.main.MainApp
import org.wit.archaeologicalfieldwork.models.SiteModel

class SiteActivity :AppCompatActivity(),AnkoLogger{
    var site = SiteModel()
    lateinit var app : MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_site)
        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)
        info("Placemark Activity started..")

        app = application as MainApp

        if(intent.hasExtra("site_edit")){
            site = intent.extras.getParcelable<SiteModel>("site_edit")
            siteName.setText(site.name)
            description.setText(site.description)
        }

        btnAdd.setOnClickListener() {
            site.name = siteName.text.toString()
            if(site.name.isNotEmpty()){
                app.sites.create(site.copy())
                info ("add Button Pressed: $siteName")

                setResult(AppCompatActivity.RESULT_OK)
                finish()
            }else{
                toast ("Please Enter a title")
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.item_cancel->{
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }


}