package org.wit.archaeologicalfieldwork.views.site

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_site.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.toast
import org.wit.archaeologicalfieldwork.R
import org.wit.archaeologicalfieldwork.helpers.readImageFromPath
import org.wit.archaeologicalfieldwork.models.SiteModel
import org.wit.archaeologicalfieldwork.views.BaseView

class SiteView :BaseView(),AnkoLogger{

    lateinit var presenter: SitePresenter
    val site = SiteModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_site)

        init(activity_site_toolbar,true)

        presenter = initPresenter(SitePresenter(this)) as SitePresenter

        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync{
            presenter.doConfigureMap(it)
            it.setOnMapClickListener { presenter.doSetLocation() }
        }

        addImage.setOnClickListener{
           presenter.doSelectImage()
        }




    }

    override fun showSite(site: SiteModel) {
       siteName.setText(site.name)
       description.setText(site.description)
       siteImage.setImageBitmap(readImageFromPath(this,site.image))
        if(site.image != null){
            addImage.setText(R.string.change_site_image)
        }
        lat.setText("%.6f".format(site.lat))
        lng.setText("%.6f".format(site.lng))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_site,menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.item_delete->{
                presenter.doDelete()
            }
            R.id.item_cancel->{
                presenter.doCancel()
            }
            R.id.item_save->{
                if(siteName.text.toString().isEmpty()){
                    toast(R.string.enter_site_name)
                }else{
                    presenter.doAddOrSave(siteName.text.toString(),description.text.toString())
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(data != null){
            presenter.doActivityResult(requestCode,resultCode,data)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
        presenter.doResartLocationUpdates()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }



}