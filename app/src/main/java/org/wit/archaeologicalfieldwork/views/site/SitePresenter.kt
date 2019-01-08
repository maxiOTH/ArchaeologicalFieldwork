package org.wit.archaeologicalfieldwork.views.site

import android.content.Intent
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.jetbrains.anko.intentFor
import org.wit.archaeologicalfieldwork.helpers.showImagePicker
import org.wit.archaeologicalfieldwork.main.MainApp
import org.wit.archaeologicalfieldwork.models.Location
import org.wit.archaeologicalfieldwork.models.SiteModel
import org.wit.archaeologicalfieldwork.views.*
import org.wit.archaeologicalfieldwork.views.editlocation.EditLocationView

class SitePresenter(view: BaseView):BasePresenter(view){

    var site = SiteModel()
    var defaultlocation = Location(52.245696, -7.139102, 15f)
    var edit = false
    var map : GoogleMap? = null


    init {
        if(view.intent.hasExtra("site_edit")){
            edit = true
            site = view.intent.extras.getParcelable<SiteModel>("site_edit")
            view.showSite(site)
        }else{
            site.lat = defaultlocation.lat
            site.lng = defaultlocation.lng
        }
    }

    fun doConfigureMap(m:GoogleMap){
        map = m
        locationUpdate(site.lat,site.lng)
    }

    fun locationUpdate(lat:Double,lng:Double){
        site.lat = lat
        site.lng = lng
        site.zoom = 15f
        map?.clear()
        map?.uiSettings?.setZoomControlsEnabled(true)
        val options = MarkerOptions().title(site.name).position(LatLng(site.lat,site.lng))
        map?.addMarker(options)
        map?.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(site.lat,site.lng),site.zoom))
        view?.showSite(site)
    }

    fun doAddOrSave(name:String, description:String){
        site.name = name
        site.description = description
        if(edit){
            app.sites.update(site)
        }else{
            app.sites.create(site)
        }
        view?.finish()
    }

    fun doCancle(){
        view?.finish()
    }

    fun doDelete(){
        app.sites.delete(site)
        view?.finish()
    }

    fun doSelectImage(){
        view?.let {
            showImagePicker(view!!,IMAGE_REQUEST)
        }

    }

    fun doSetLocation(){
        if(edit==false){
            view?.navigateTo(VIEW.LOCATION,LOCATION_REQUEST,"location",defaultlocation)
        }else{
            view?.navigateTo(VIEW.LOCATION, LOCATION_REQUEST,"location", Location(site.lat,site.lng,site.zoom))
        }
    }

    override fun doActivityResult(requestCode:Int, resultCode:Int, data:Intent){
        when(requestCode){
            IMAGE_REQUEST ->{
                site.image = data.data.toString()
                view?.showSite(site)
            }
            LOCATION_REQUEST ->{
                val location = data.extras.getParcelable<Location>("location")
                site.lat = location.lat
                site.lng = location.lng
                site.zoom = location.zoom
                locationUpdate(site.lat,site.lng)
            }
        }
    }
}