package org.wit.archaeologicalfieldwork.views.site

import android.annotation.SuppressLint
import android.content.Intent
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.wit.archaeologicalfieldwork.helpers.checkLocationPermissions
import org.wit.archaeologicalfieldwork.helpers.isPermissionGranted
import org.wit.archaeologicalfieldwork.helpers.showImagePicker
import org.wit.archaeologicalfieldwork.models.Location
import org.wit.archaeologicalfieldwork.models.SiteModel
import org.wit.archaeologicalfieldwork.models.Image
import org.wit.archaeologicalfieldwork.views.*
import java.util.*

class SitePresenter(view: BaseView):BasePresenter(view){

    var site = SiteModel()
    var defaultlocation = Location(52.245696, -7.139102, 15f)
    var edit = false
    var map : GoogleMap? = null
    var locationService: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(view)

    init {
        if(view.intent.hasExtra("site_edit")){
            edit = true
            site = view.intent.extras.getParcelable<SiteModel>("site_edit")
            view.showSite(site)
        }else{
            if (checkLocationPermissions(view)) {
                doSetCurrentLocation()
            }
        }
    }

    @SuppressLint("MissingPermission")
    fun doSetCurrentLocation() {
        locationService.lastLocation.addOnSuccessListener {
            locationUpdate(Location(it.latitude, it.longitude))
        }
    }

    override fun doRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (isPermissionGranted(requestCode, grantResults)) {
            doSetCurrentLocation()
        } else {
            locationUpdate(defaultlocation)
        }
    }

    fun doConfigureMap(m:GoogleMap){
        map = m
        locationUpdate(site.location)
    }

    fun locationUpdate(location:Location){
        site.location = location
        site.location.zoom = 15f
        map?.clear()
        map?.uiSettings?.setZoomControlsEnabled(true)
        val options = MarkerOptions().title(site.name).position(LatLng(site.location.lat,site.location.lng))
        map?.addMarker(options)
        map?.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(site.location.lat,site.location.lng),site.location.zoom))
        view?.showSite(site)
    }

    fun doUpdateVisited(){
        site.visited.visited = !site.visited.visited
        view?.showSite(site)
    }



    fun doUpdateVisitedDate(year: Int, month: Int, day: Int){
        site.visited.visited = true
        site.visited.date = GregorianCalendar(year, month, day).time
        view?.showSite(site)
    }

    fun doAddOrSave(name:String, description:String, additionalNotes:String){
        site.name = name
        site.description = description
        site.additionalNotes = additionalNotes

        async(UI) {
            if (edit) {
                app.sites.update(site)
            } else {
                app.sites.create(site)
            }
            view?.finish()
        }
    }

    fun doSetRating(rating: Int){
        site.rating = rating
    }

    fun doCancle(){
        view?.finish()
    }

    fun doDelete(){
        async(UI) {
            app.sites.delete(site)
            view?.finish()
        }
    }

    fun doSelectImage(){
        view?.let {
            showImagePicker(view!!,IMAGE_REQUEST)
        }
    }

    fun doRemoveImage(image: Image) {
        site.images.remove(image)
        if (site.images.size > 0 && image.preview) {
            site.images.first().preview = true
        }
    }

    fun doSetLocation(){
            view?.navigateTo(VIEW.LOCATION, LOCATION_REQUEST,"location", Location(site.location.lat,site.location.lng,site.location.zoom))

    }

    override fun doActivityResult(requestCode:Int, resultCode:Int, data:Intent){
        when(requestCode){
            IMAGE_REQUEST ->{
                var previewImage = site.images.size == 0 || site.images.find { image -> image.preview }==null
                site.images.add(Image(data.data.toString(), previewImage))
                view?.showSite(site)
            }
            LOCATION_REQUEST ->{
                val location = data.extras.getParcelable<Location>("location")
                site.location = location
                locationUpdate(location)
            }
        }
    }
}