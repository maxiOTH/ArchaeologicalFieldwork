package org.wit.archaeologicalfieldwork.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.archaeologicalfieldwork.models.SiteJSONStore
import org.wit.archaeologicalfieldwork.models.SiteStore
import org.wit.archaeologicalfieldwork.models.UserStore


class MainApp: Application(),AnkoLogger{

   lateinit var sites : SiteStore
   lateinit var users: UserStore


    override fun onCreate() {
        super.onCreate()
        sites = SiteJSONStore(applicationContext)
        info ("Archaeologicalfieldwork started")
    }

}