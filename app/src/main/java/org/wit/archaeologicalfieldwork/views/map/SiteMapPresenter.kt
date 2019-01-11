package org.wit.archaeologicalfieldwork.views.map

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.wit.archaeologicalfieldwork.models.SiteModel
import org.wit.archaeologicalfieldwork.views.BasePresenter
import org.wit.archaeologicalfieldwork.views.BaseView

class SiteMapPresenter(view: BaseView):BasePresenter(view) {

    fun doPopulateMap(map:GoogleMap,sites:List<SiteModel>){
        map.uiSettings.setZoomControlsEnabled(true)
        sites.forEach {
            val loc = LatLng(it.location.lat,it.location.lng)
            val options = MarkerOptions().title(it.name).position(loc)
            map.addMarker(options).tag = it.id
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(loc,it.location.zoom))

        }
    }

    fun doMarkterSelected(marker: Marker){
        async(UI){
            val tag = marker.tag as Long
            val site = app.sites.findById(tag)
            if(site != null)view?.showSite(site)
        }

    }

    fun loadSites(){
        async(UI) {
            view?.showSites(app.sites.findAll())
        }
    }
}