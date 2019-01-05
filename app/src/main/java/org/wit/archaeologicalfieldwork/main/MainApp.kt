package org.wit.archaeologicalfieldwork.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.archaeologicalfieldwork.models.SiteMemStore


class MainApp: Application(),AnkoLogger{

   lateinit var sites : SiteMemStore


    override fun onCreate() {
        super.onCreate()
        sites = SiteMemStore()
        info ("Archaeologicalfieldwork started")
    }

}