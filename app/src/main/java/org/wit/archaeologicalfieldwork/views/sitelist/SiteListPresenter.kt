package org.wit.archaeologicalfieldwork.views.sitelist

import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.wit.archaeologicalfieldwork.models.SiteModel
import org.wit.archaeologicalfieldwork.views.BasePresenter
import org.wit.archaeologicalfieldwork.views.BaseView
import org.wit.archaeologicalfieldwork.views.VIEW
import java.util.*


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

    fun doShowSettings(){
        view?.navigateTo(VIEW.SETTINGS)
    }


    fun loadSites(){
        async(UI){
        view?.showSites(app.sites.findAll())
        }
    }

    fun doUpdateVisited(site: SiteModel){

        if(!site.visited.visited){

            site.visited.date = Date()
        }
        site.visited.visited = !site.visited.visited
            async(UI){
            app.sites.update(site)
            }
    }

}