package org.wit.archaeologicalfieldwork.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_site_list.*
import kotlinx.android.synthetic.main.card_site.view.*
import org.wit.archaeologicalfieldwork.R
import org.wit.archaeologicalfieldwork.main.MainApp
import org.wit.archaeologicalfieldwork.models.SiteModel

class SiteListActivity:AppCompatActivity(){
    lateinit var app : MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_site_list)
        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = SiteAdapter(app.sites)

    }

}

class SiteAdapter constructor(private var sites:List<SiteModel>):RecyclerView.Adapter<SiteAdapter.MainHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, vieType: Int):MainHolder {
        return MainHolder(LayoutInflater.from(parent?.context).inflate(R.layout.card_site,parent,false))
    }

    override fun onBindViewHolder(holder:MainHolder, position: Int) {
        val site = sites[holder.adapterPosition]
        holder.bind(site)
    }

    override fun getItemCount(): Int = sites.size

    class MainHolder constructor(itemView: View):RecyclerView.ViewHolder(itemView){

        fun bind(site:SiteModel){
            itemView.siteName.text = site.name
        }
    }

}