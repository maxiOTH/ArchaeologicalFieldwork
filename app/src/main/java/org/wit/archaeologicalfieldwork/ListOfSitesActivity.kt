package org.wit.archaeologicalfieldwork

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_list_of_sites.*
import org.jetbrains.anko.toast


class ListOfSitesActivity : AppCompatActivity() {

    var site = SiteModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_of_sites)
        setSupportActionBar(findViewById(R.id.toolbarAdd))

        btnAdd.setOnClickListener(){
            site.name = siteName.text.toString()
            if(site.name.isNotEmpty()){
                toast("create Button pressed")
            }
            else{
                toast("Pleas Enter a name")
            }

        }
    }

    override fun onOptionsItemSelected(item: MenuItem)= when (item.itemId) {
        R.id.action_settings ->{
            true
        }
        R.id.action_favorite ->{
            true
        }
        else ->{
            super.onOptionsItemSelected(item)
        }
    }


}