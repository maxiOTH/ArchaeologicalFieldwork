package org.wit.archaeologicalfieldwork.views

import android.content.Intent
import android.os.Parcelable
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import org.jetbrains.anko.AnkoLogger
import org.wit.archaeologicalfieldwork.models.SiteModel
import org.wit.archaeologicalfieldwork.views.editlocation.EditLocationView
import org.wit.archaeologicalfieldwork.views.map.SiteMapView
import org.wit.archaeologicalfieldwork.views.site.SitePresenter
import org.wit.archaeologicalfieldwork.views.site.SiteView
import org.wit.archaeologicalfieldwork.views.sitelist.SiteListView


val IMAGE_REQUEST = 1
val LOCATION_REQUEST = 2

enum class VIEW {
    LOCATION, SITE, MAPS, LIST
}

open abstract class BaseView():AppCompatActivity(), AnkoLogger {

    var basePresenter:BasePresenter? = null

    fun navigateTo(view:VIEW,code:Int=0,key:String="",value:Parcelable?=null){
        var intent = Intent(this,SiteListView::class.java)
        when(view){
            VIEW.LOCATION-> intent = Intent(this,EditLocationView::class.java)
            VIEW.SITE-> intent = Intent(this, SiteView::class.java)
            VIEW.MAPS-> intent = Intent(this,SiteMapView::class.java)
            VIEW.LIST-> intent = Intent(this,SiteListView::class.java)
        }
        if (key != "") {
            intent.putExtra(key, value)
        }
        startActivityForResult(intent, code)
    }

    fun initPresenter(presenter: BasePresenter): BasePresenter {
        basePresenter = presenter
        return presenter
    }

    fun init(toolbar: Toolbar) {
        toolbar.title = title
        setSupportActionBar(toolbar)
    }

    override fun onDestroy() {
        basePresenter?.onDestroy()
        super.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            basePresenter?.doActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        basePresenter?.doRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    open fun showSite(site:SiteModel){}
    open fun showSites(sites:List<SiteModel>){}
    open fun showProgress(){}
    open fun hideProgress(){}

}