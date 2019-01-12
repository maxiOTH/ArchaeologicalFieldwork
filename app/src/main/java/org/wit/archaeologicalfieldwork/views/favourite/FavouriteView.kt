package org.wit.archaeologicalfieldwork.views.favourite

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_site_list.*
import org.jetbrains.anko.info
import org.wit.archaeologicalfieldwork.R
import org.wit.archaeologicalfieldwork.models.SiteModel
import org.wit.archaeologicalfieldwork.views.BaseView


class FavouriteView: BaseView(), FavouriteListener {

    lateinit var presenter: FavouritePresenter

    override fun onCreate(savedInstanceState:Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_site_list)
        init(activity_site_list_toolbar, true)
        initDrawerNavigation(activity_site_list_toolbar, drawer_layout, navigation_view)
        presenter = initPresenter(FavouritePresenter(this)) as FavouritePresenter
        recyclerView.adapter = FavouriteAdapter(emptyList(), this)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        presenter.loadSites()


    }

    override fun showSites(sites: List<SiteModel>) {
        info(sites)
        recyclerView.adapter = FavouriteAdapter(sites, this)
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun onSiteClick(site: SiteModel) {
        presenter.doEditSite(site)
    }

    override fun onCheckVisited(site: SiteModel) {
        presenter.doUpdateVisited(site)
    }

    override fun onFavouriteClick(site: SiteModel) {
        presenter.doUpdateFavourite(site)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        presenter.loadSites()
        super.onActivityResult(requestCode, resultCode, data)
    }
}