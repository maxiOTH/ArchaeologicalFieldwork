package org.wit.archaeologicalfieldwork.views.sitelist

import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.startActivityForResult
import org.wit.archaeologicalfieldwork.views.map.SiteMapView
import org.wit.archaeologicalfieldwork.main.MainApp
import org.wit.archaeologicalfieldwork.models.SiteModel
import org.wit.archaeologicalfieldwork.views.BasePresenter
import org.wit.archaeologicalfieldwork.views.BaseView
import org.wit.archaeologicalfieldwork.views.VIEW
import org.wit.archaeologicalfieldwork.views.site.SiteView

class SiteListPresenter(view:BaseView):BasePresenter(view) {

    fun doAddSite(){
        view?.navigateTo(VIEW.SITE)
    }

    fun doEditSite(site:SiteModel){
        view?.navigateTo(VIEW.SITE,0,"site_edit",site)
    }

    fun doShowSitesMap(){
        view?.navigateTo(VIEW.MAPS)
    }

    fun loadSites(){
        view?.showSites(app.sites.findAll())
    }
}