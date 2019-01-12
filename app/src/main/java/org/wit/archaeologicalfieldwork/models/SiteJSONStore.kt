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

private val JSON_FILE = "sites.json"
private val  gsonBuilder = GsonBuilder().setPrettyPrinting().create()
private val listType = object : TypeToken<java.util.ArrayList<SiteModel>>(){}.type

private fun generateRandomId():Long{
    return Random().nextLong()
}

class SiteJSONStore:SiteStore,AnkoLogger{


    suspend override fun findById(id: Long): SiteModel? {
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
    suspend override fun findAll(): List<SiteModel> {
        return sites
    }

    suspend override fun create(site: SiteModel) {
        site.id = generateRandomId()
        sites.add(site)
        serialize()
    }

    suspend override fun update(site: SiteModel) {
       val sitesList = findAll() as ArrayList<SiteModel>
       var foundSite:SiteModel?=sitesList.find { p->p.id == site.id }
       if (foundSite != null){
           foundSite.name = site.name
           foundSite.description = site.description
           foundSite.images = site.images
           foundSite.location = site.location
           foundSite.visited = site.visited
           foundSite.additionalNotes = site.additionalNotes
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

    suspend override fun delete(site: SiteModel) {
        sites.remove(site)
        serialize()
    }

    override suspend fun findAllFavourites(): List<SiteModel> {
        return sites.filter{siteModel-> siteModel.favourite}
    }

}
