package org.wit.archaeologicalfieldwork.views.sitelist

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager

import android.view.*
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_site_list.*

import org.jetbrains.anko.startActivityForResult
import org.wit.archaeologicalfieldwork.R
import org.wit.archaeologicalfieldwork.activities.LoginActivity
import org.wit.archaeologicalfieldwork.models.SiteModel
import org.wit.archaeologicalfieldwork.views.BaseView


class SiteListView:BaseView(), SiteListener {

    lateinit var presenter: SiteListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_site_list)
        init(activity_site_list_toolbar)

        presenter = initPresenter(SiteListPresenter(this))as SiteListPresenter

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        presenter.loadSites()
    }

    override fun showSites(sites:List<SiteModel>){
        recyclerView.adapter = SiteAdapter(sites,this)
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.item_add -> {
                presenter.doAddSite()
            }

            R.id.item_map -> {
                presenter.doShowSitesMap()
            }

            R.id.action_logout -> {
                Toast.makeText(this, "Successfull log out", Toast.LENGTH_SHORT).show()
                startActivityForResult<LoginActivity>(0)
                true
            }

            R.id.action_settings -> {
                true
            }

            else -> {
                super.onOptionsItemSelected(item)
            }
        }
        return super.onOptionsItemSelected(item)
    }


        override fun onSiteClick(site: SiteModel) {
            presenter.doEditSite(site)
        }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
           presenter.loadSites()
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

