package org.wit.archaeologicalfieldwork.views.site

import org.wit.archaeologicalfieldwork.models.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.card_image.view.*
import org.wit.archaeologicalfieldwork.R

interface ImageListener{
    fun onImageRemove(image:Image)
}

class ImageAdapter constructor(private var images : List<Image>, private val listener:ImageListener):RecyclerView.Adapter<ImageAdapter.MainHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(LayoutInflater.from(parent?.context).inflate(R.layout.card_image, parent, false))
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val image = images[holder.adapterPosition]
        holder.bind(image, listener)
    }

    override fun getItemCount(): Int = images.size


    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(image:Image, listener: ImageListener){
            Glide.with(itemView.context).load(image.image).into(itemView.siteImage)
            itemView.siteImage.setOnClickListener { listener.onImageRemove(image) }
        }
    }

}