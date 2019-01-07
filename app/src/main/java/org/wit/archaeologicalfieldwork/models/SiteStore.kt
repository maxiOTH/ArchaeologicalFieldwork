package org.wit.archaeologicalfieldwork.models

interface SiteStore {
    fun findAll():List<SiteModel>
    fun create(site:SiteModel)
    fun update(site:SiteModel)
    fun delete(site:SiteModel)
    fun finById(id:Long):SiteModel?
}