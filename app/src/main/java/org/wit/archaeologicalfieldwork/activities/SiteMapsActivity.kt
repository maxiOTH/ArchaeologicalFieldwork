package org.wit.archaeologicalfieldwork.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import org.wit.archaeologicalfieldwork.R
import kotlinx.android.synthetic.main.activity_site_maps.*
import kotlinx.android.synthetic.main.content_site_maps.*
import org.wit.archaeologicalfieldwork.helpers.readImageFromPath
import org.wit.archaeologicalfieldwork.main.MainApp


class SiteMapsActivity : AppCompatActivity(), GoogleMap.OnMarkerClickListener{
     lateinit var map: GoogleMap
    lateinit var app:MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_site_maps)
        setSupportActionBar(toolbarMaps)
        app = application as MainApp

        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync{
            map = it
            configureMap()
        }

    }

    fun configureMap() {
        map.uiSettings.setZoomControlsEnabled(true)
        map.setOnMarkerClickListener(this)
        app.sites.findAll().forEach {
            val loc = LatLng(it.lat, it.lng)
            val options = MarkerOptions().title(it.name).position(loc)
            map.addMarker(options).tag = it.id
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, it.zoom))

        }
    }

    override fun onMarkerClick(marker: Marker):Boolean{
        val tag = marker.tag as Long
        val site = app.sites.finById(tag)
        currentName.text = site!!.name
        currentDescription.text = site!!.description
        imageView.setImageBitmap(readImageFromPath(this@SiteMapsActivity, site.image))
        return true
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
    }
    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }






}
