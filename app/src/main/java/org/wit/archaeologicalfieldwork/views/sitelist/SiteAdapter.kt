package org.wit.archaeologicalfieldwork.views.sitelist

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_settings.view.*
import kotlinx.android.synthetic.main.card_site.view.*
import org.wit.archaeologicalfieldwork.R
import org.wit.archaeologicalfieldwork.helpers.readImageFromPath
import org.wit.archaeologicalfieldwork.models.SiteModel
import org.wit.archaeologicalfieldwork.models.UserModel

interface SiteListener{
    fun onSiteClick(site:SiteModel)
    fun onCheckVisited(site:SiteModel)
    fun onFavouriteClick(site:SiteModel)
}

class SiteAdapter constructor(private var sites:List<SiteModel>,
                              private val listener: SiteListener): RecyclerView.Adapter<SiteAdapter.MainHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, vieType: Int): MainHolder {
        return MainHolder(
            LayoutInflater.from(parent?.context).inflate(
                R.layout.card_site,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val site = sites[holder.adapterPosition]
        holder.bind(site, listener)
    }

    override fun getItemCount(): Int = sites.size

    class MainHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bind(site: SiteModel, listener: SiteListener){
            itemView.siteName.text = site.name
            itemView.description.text = site.description
            itemView.checkBox_card_site.isChecked = site.visited.visited
            itemView.lat_textview_card_site.text = site.location.lat.toString().format("%.4f")
            itemView.lng_textview_card_site.text = site.location.lng.toString().format("%.4f")
            Glide.with(itemView.context).load(site.images.find{image -> image.preview}?.image).into(itemView.imageIcon)
            itemView.setOnClickListener{listener.onSiteClick(site)}
            itemView.ratingBarView.rating = site.rating.toFloat()
            itemView.checkBox_card_site.setOnCheckedChangeListener{compoundButton, b-> listener.onCheckVisited(site)}
            itemView.favourite.isChecked = site.favourite
            itemView.favourite.setOnCheckedChangeListener{compoundButton, b-> listener.onFavouriteClick(site)}

        }
    }

}