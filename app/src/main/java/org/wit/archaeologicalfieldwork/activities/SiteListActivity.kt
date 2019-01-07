package org.wit.archaeologicalfieldwork.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager

import android.view.*
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_site_list.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivity

import org.jetbrains.anko.startActivityForResult
import org.wit.archaeologicalfieldwork.R
import org.wit.archaeologicalfieldwork.main.MainApp
import org.wit.archaeologicalfieldwork.models.SiteModel


class SiteListActivity:AppCompatActivity(),SiteListener{
    lateinit var app : MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_site_list)
        app = application as MainApp
        activity_site_list_toolbar.title = title
        setSupportActionBar(findViewById(R.id.activity_site_list_toolbar))

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        loadSites()


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem)= when (item.itemId) {
       // when(item?.itemId){
        //    R.id.item_add -> startActivityForResult<SiteActivity>(0)
       // }
        //return super.onOptionsItemSelected(item)
        R.id.item_add->{
            startActivityForResult<SiteActivity>(200)
            true
        }

        R.id.item_map ->{
            startActivity<SiteMapsActivity>()
            true
        }

        R.id.action_logout ->{
            Toast.makeText(this,"Successfull log out",Toast.LENGTH_SHORT).show()
            startActivityForResult<LoginActivity>(0)
            true
        }

        R.id.action_settings -> {
            true
        }

        else->{
            super.onOptionsItemSelected(item)
        }

    }

    override fun onSiteClick(site:SiteModel){
        startActivityForResult(intentFor<SiteActivity>().putExtra("site_edit",site), 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        loadSites()
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun loadSites(){
        showSites(app.sites.findAll())
    }

    fun showSites(sites:List<SiteModel>){
        recyclerView.adapter = SiteAdapter(sites,this)
        recyclerView.adapter?.notifyDataSetChanged()
    }

}

