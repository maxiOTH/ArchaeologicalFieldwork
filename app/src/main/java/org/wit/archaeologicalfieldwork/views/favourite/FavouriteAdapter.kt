package org.wit.archaeologicalfieldwork.views.favourite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.card_site.view.*
import org.wit.archaeologicalfieldwork.R
import org.wit.archaeologicalfieldwork.models.SiteModel

interface FavouriteListener{
    fun onSiteClick(site: SiteModel)
    fun onCheckVisited(site: SiteModel)
    fun onFavouriteClick(site: SiteModel)
}

class FavouriteAdapter constructor(private var sites: List<SiteModel>,
                                   private val listener: FavouriteListener) : RecyclerView.Adapter<FavouriteAdapter.MainHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(LayoutInflater.from(parent?.context).inflate(R.layout.card_site, parent, false))
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val site = sites[holder.adapterPosition]
        holder.bind(site, listener)
    }

    override fun getItemCount(): Int = sites.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(site: SiteModel, listener: FavouriteListener) {
            itemView.siteName.text = site.name
            itemView.description.text = site.description
            itemView.checkBox_card_site.isChecked = site.visited.visited
            itemView.lat_textview_card_site.text = site.location.lat.toString().format("%.4f")
            itemView.lng_textview_card_site.text = site.location.lng.toString().format("%.4f")
            Glide.with(itemView.context).load(site.images.find { image -> image.preview}?.image).into(itemView.imageIcon)
            itemView.setOnClickListener { listener.onSiteClick(site) }
            itemView.ratingBarView.rating = site.rating.toFloat()
            itemView.checkBox_card_site.setOnCheckedChangeListener { compoundButton, b ->  listener.onCheckVisited(site) }
            itemView.favourite.isChecked = site.favourite
            itemView.favourite.setOnCheckedChangeListener{compoundButton, b ->  listener.onFavouriteClick(site)}
        }
    }
}