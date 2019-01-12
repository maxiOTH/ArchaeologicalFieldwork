package org.wit.archaeologicalfieldwork.models

interface SiteStore {
   suspend fun findAll():List<SiteModel>
    suspend fun create(site:SiteModel)
    suspend fun update(site:SiteModel)
    suspend fun delete(site:SiteModel)
    suspend fun findById(id:Long):SiteModel?
    suspend fun findAllFavourites(): List<SiteModel>
}