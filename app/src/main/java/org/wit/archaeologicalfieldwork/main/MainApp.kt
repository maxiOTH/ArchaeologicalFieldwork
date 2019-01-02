package org.wit.archaeologicalfieldwork.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.archaeologicalfieldwork.models.SiteMemStore


class MainApp: Application(),AnkoLogger{

    val sites = SiteMemStore()


    override fun onCreate() {
        super.onCreate()
        info ("Archaeologicalfieldwork started")

    }

}