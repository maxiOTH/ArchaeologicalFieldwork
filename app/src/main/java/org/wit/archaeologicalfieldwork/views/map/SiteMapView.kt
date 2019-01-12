package org.wit.archaeologicalfieldwork.views.map

import android.os.Bundle
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.bumptech.glide.Glide
import org.wit.archaeologicalfieldwork.R
import kotlinx.android.synthetic.main.activity_site_maps.*
import kotlinx.android.synthetic.main.content_site_maps.*
import org.wit.archaeologicalfieldwork.helpers.readImageFromPath
import org.wit.archaeologicalfieldwork.models.Image
import org.wit.archaeologicalfieldwork.models.SiteModel
import org.wit.archaeologicalfieldwork.views.BaseView
import org.wit.archaeologicalfieldwork.views.site.ImageAdapter
import org.wit.archaeologicalfieldwork.views.site.ImageListener


class SiteMapView : BaseView(), GoogleMap.OnMarkerClickListener, ImageListener {

    lateinit var presenter: SiteMapPresenter
    lateinit var map : GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_site_maps)
        init(toolbarMaps, true)
        initDrawerNavigation(toolbarMaps, drawer_layout, navigation_view)
        presenter = initPresenter(SiteMapPresenter(this))as SiteMapPresenter

        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync{
           map = it
           map.setOnMarkerClickListener(this)
           presenter.loadSites()
        }

    }

    override fun showSite(site:SiteModel){
        currentName.text = site.name
        currentDescription.text = site.description
        Glide.with(this).load(site.images.find{image->image.preview}?.image).into(imageView)
    }

    override fun showSites(sites:List<SiteModel>){
        presenter.doPopulateMap(map,sites)
    }

    override fun onImageRemove(image: Image) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onMarkerClick(marker: Marker):Boolean{
        presenter.doMarkterSelected(marker)
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
