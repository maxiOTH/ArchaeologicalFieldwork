package org.wit.archaeologicalfieldwork

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.MenuItem


class ListOfSitesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_of_sites)

        setSupportActionBar(findViewById(R.id.my_toolbar))

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