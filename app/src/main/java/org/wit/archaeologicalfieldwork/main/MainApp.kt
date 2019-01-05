package org.wit.archaeologicalfieldwork.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.archaeologicalfieldwork.models.SiteJSONStore
import org.wit.archaeologicalfieldwork.models.SiteStore


class MainApp: Application(),AnkoLogger{

   lateinit var sites : SiteStore


    override fun onCreate() {
        super.onCreate()
        sites = SiteJSONStore(applicationContext)
        info ("Archaeologicalfieldwork started")
    }

}