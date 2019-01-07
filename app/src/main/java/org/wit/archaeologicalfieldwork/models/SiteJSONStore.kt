package org.wit.archaeologicalfieldwork.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import org.wit.archaeologicalfieldwork.helpers.exists
import org.wit.archaeologicalfieldwork.helpers.read
import org.wit.archaeologicalfieldwork.helpers.write
import java.util.*
import kotlin.collections.ArrayList

val JSON_FILE = "sites.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<SiteModel>>(){}.type

fun generateRandomId():Long{
    return Random().nextLong()
}

class SiteJSONStore:SiteStore,AnkoLogger{
    override fun finById(id: Long): SiteModel? {
        val foundSite: SiteModel? = sites.find { it.id == id }
        return foundSite    }


    val context:Context
    var sites = mutableListOf<SiteModel>()

    constructor(context: Context){
        this.context = context
        if(exists(context, JSON_FILE)){
            deserialize()
        }
    }
    override fun findAll(): List<SiteModel> {
        return sites
    }

    override fun create(site: SiteModel) {
        site.id = generateRandomId()
        sites.add(site)
        serialize()
    }

    override fun update(site: SiteModel) {
       val sitesList = findAll() as ArrayList<SiteModel>
       var foundSite:SiteModel?=sitesList.find { p->p.id == site.id }
       if (foundSite != null){
           foundSite.name = site.name
           foundSite.description = site.description
           foundSite.image = site.image
           foundSite.lat = site.lat
           foundSite.lng = site.lng
           foundSite.zoom = site.zoom
       }
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(sites, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize(){
        val jsonString = read(context, JSON_FILE)
        sites = Gson().fromJson(jsonString, listType)
    }

    override fun delete(site: SiteModel) {
        sites.remove(site)
        serialize()
    }

}
