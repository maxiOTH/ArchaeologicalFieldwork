package org.wit.archaeologicalfieldwork.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_site.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.archaeologicalfieldwork.R
import org.wit.archaeologicalfieldwork.helpers.readImage
import org.wit.archaeologicalfieldwork.helpers.readImageFromPath
import org.wit.archaeologicalfieldwork.helpers.showImagePicker
import org.wit.archaeologicalfieldwork.main.MainApp
import org.wit.archaeologicalfieldwork.models.SiteModel

class SiteActivity :AppCompatActivity(),AnkoLogger{

    var edit = true
    var site = SiteModel()
    lateinit var app : MainApp
    val IMAGE_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_site)
        activity_site_toolbar.title = title
        setSupportActionBar(findViewById(R.id.activity_site_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        info("Site Activity started..")

        app = application as MainApp

        if(intent.hasExtra("site_edit")){
            edit= true
            site = intent.extras.getParcelable<SiteModel>("site_edit")
            siteName.setText(site.name)
            description.setText(site.description)
            siteImage.setImageBitmap(readImageFromPath(this,site.image))
            
            btnAdd.setText(R.string.save_Site)

        }

        btnAdd.setOnClickListener() {
            site.name = siteName.text.toString()
            site.description = description.text.toString()
            if(site.name.isEmpty()) {
                toast(R.string.enter_site_name)
            }else{
                if(edit){
                    app.sites.update(site.copy())
                }else{
                    app.sites.create(site.copy())
                }
            }
            info ("add Button Pressed: $siteName")
            setResult(AppCompatActivity.RESULT_OK)
            finish()
        }

        addImage.setOnClickListener{
           showImagePicker(this,IMAGE_REQUEST)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_site,menu)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            IMAGE_REQUEST->{
                if(data!=null){
                    site.image = data.getData().toString()
                    siteImage.setImageBitmap(readImage(this,resultCode,data))
                }
            }
        }
    }



}