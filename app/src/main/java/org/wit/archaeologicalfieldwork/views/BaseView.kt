package org.wit.archaeologicalfieldwork.views

import android.content.Intent
import android.os.Parcelable
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.navigation_view.*
import org.jetbrains.anko.AnkoLogger
import org.wit.archaeologicalfieldwork.R
import org.wit.archaeologicalfieldwork.models.SiteModel
import org.wit.archaeologicalfieldwork.models.UserModel
import org.wit.archaeologicalfieldwork.views.editlocation.EditLocationView
import org.wit.archaeologicalfieldwork.views.favourite.FavouriteView
import org.wit.archaeologicalfieldwork.views.login_register.LoginView
import org.wit.archaeologicalfieldwork.views.map.SiteMapView
import org.wit.archaeologicalfieldwork.views.settings.SettingsView
import org.wit.archaeologicalfieldwork.views.site.SitePresenter
import org.wit.archaeologicalfieldwork.views.site.SiteView
import org.wit.archaeologicalfieldwork.views.sitelist.SiteListView



val IMAGE_REQUEST = 1
val LOCATION_REQUEST = 2

enum class VIEW {
    LOCATION, SITE, MAPS, LIST, SETTINGS, LOGIN, FAVOURITE
}

open abstract class BaseView():AppCompatActivity(), AnkoLogger {

    var basePresenter:BasePresenter? = null

    fun navigateTo(view:VIEW,code:Int=0,key:String="",value:Parcelable?=null){
        var intent = Intent(this,SiteListView::class.java)
        when(view){
            VIEW.LOCATION-> intent = Intent(this,EditLocationView::class.java)
            VIEW.SITE-> intent = Intent(this, SiteView::class.java)
            VIEW.MAPS-> intent = Intent(this,SiteMapView::class.java)
            VIEW.LIST-> intent = Intent(this,SiteListView::class.java)
            VIEW.SETTINGS-> intent = Intent(this, SettingsView::class.java)
            VIEW.LOGIN -> intent = Intent(this, LoginView::class.java)
            VIEW.FAVOURITE -> intent = Intent(this, FavouriteView::class.java)

        }
        if (key != "") {
            intent.putExtra(key, value)
        }
        startActivityForResult(intent, code)
    }

    fun initPresenter(presenter: BasePresenter): BasePresenter {
        basePresenter = presenter
        return presenter
    }

    fun init(toolbar: Toolbar, upEnabled:Boolean) {
        toolbar.title = title
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(upEnabled)
        val user = FirebaseAuth.getInstance().currentUser
        if(user != null){
            toolbar.title = "${title}: ${user.email}"
        }
    }

    fun initDrawerNavigation(toolbar: Toolbar, drawerLayout: DrawerLayout, navigationView: NavigationView) {
        val drawerToggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
          this, drawerLayout, toolbar,
           R.string.navigation_drawer_open,
           R.string.navigation_drawer_close
        ){
            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)

            }
        }
        drawerToggle.isDrawerIndicatorEnabled = true
        drawerLayout.addDrawerListener((drawerToggle))
        drawerToggle.syncState()

        navigationView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.allSites -> navigateTo(VIEW.LIST)
                R.id.sitesmaps-> navigateTo(VIEW.MAPS)
                R.id.favourites -> navigateTo(VIEW.FAVOURITE)
                R.id.settings -> navigateTo(VIEW.SETTINGS)
                R.id.logout -> {
                    FirebaseAuth.getInstance().signOut()
                    navigateTo(VIEW.LOGIN)
                }

            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true

        }

    }

    override fun onDestroy() {
        basePresenter?.onDestroy()
        super.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            basePresenter?.doActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        basePresenter?.doRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    open fun showSite(site:SiteModel){}
    open fun showSites(sites:List<SiteModel>){}
    open fun showProgress(){}
    open fun hideProgress(){}
    open fun showUser(user:UserModel){}

}