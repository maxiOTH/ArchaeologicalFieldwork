package org.wit.archaeologicalfieldwork.views.favourite

import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.wit.archaeologicalfieldwork.models.SiteModel
import org.wit.archaeologicalfieldwork.views.BasePresenter
import org.wit.archaeologicalfieldwork.views.BaseView
import org.wit.archaeologicalfieldwork.views.VIEW
import java.util.*


class FavouritePresenter(view: BaseView) : BasePresenter(view) {

    fun doEditSite(site: SiteModel) {
        view?.navigateTo(VIEW.SITE, 0, "site_edit", site)
    }

    fun loadSites() {
        async(UI) {
            var sites =app.sites.findAllFavourites()
            view?.showSites(sites)
        }
    }

    fun doUpdateFavourite(site: SiteModel){
        site.favourite = !site.favourite
        async(UI) {
            app.sites.update(site)

        }
        loadSites()
    }

    fun doUpdateVisited(site: SiteModel){

        if(!site.visited.visited){

            site.visited.date = Date()
        }
        site.visited.visited = !site.visited.visited
        async(UI) {
            app.sites.update(site)
        }
    }


}