package org.wit.archaeologicalfieldwork.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

var lastId = 0L

internal fun getId():Long{
    return lastId++
}


class SiteMemStore : SiteStore , AnkoLogger{
    suspend override fun delete(site: SiteModel) {
        sites.remove(site)
    }

    val sites = ArrayList<SiteModel>()

    suspend override fun findAll(): List<SiteModel> {
        return sites
    }

    suspend override fun create(site: SiteModel) {
        site.id = getId()
        sites.add(site)
        logAll()
    }

    suspend override fun update(site: SiteModel) {
        var foundSite:SiteModel?=sites.find { p->p.id == site.id }
        if (foundSite!=null){
            foundSite.name = site.name
            foundSite.description = site.description
            foundSite.images = site.images
            foundSite.location = site.location
            logAll()
        }
    }

    fun logAll(){
        sites.forEach{info("${it}")}
    }

    suspend override fun findById(id: Long): SiteModel? {
        val foundSite:SiteModel?=sites.find{it.id == id}
        return foundSite
    }
}