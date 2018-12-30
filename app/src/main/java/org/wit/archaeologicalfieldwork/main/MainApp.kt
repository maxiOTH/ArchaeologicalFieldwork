package org.wit.archaeologicalfieldwork.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.archaeologicalfieldwork.models.SiteModel

class MainApp: Application(),AnkoLogger{

    val sites = ArrayList<SiteModel>()


    override fun onCreate() {
        super.onCreate()
        info ("Archaeologicalfieldwork started")
        sites.add(SiteModel("Freiheitsstatue"))
        sites.add(SiteModel("RockyMountains"))
        sites.add(SiteModel("Rohrenfels"))

    }

}