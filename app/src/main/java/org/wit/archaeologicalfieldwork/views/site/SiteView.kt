package org.wit.archaeologicalfieldwork.views.site

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.DatePicker
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_site.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.toast
import org.wit.archaeologicalfieldwork.R
import org.wit.archaeologicalfieldwork.helpers.readImageFromPath
import org.wit.archaeologicalfieldwork.models.SiteModel
import org.wit.archaeologicalfieldwork.views.BaseView
import java.text.SimpleDateFormat
import com.bumptech.glide.Glide
import java.util.*

class SiteView :BaseView(),AnkoLogger,ImageListener, DatePickerDialog.OnDateSetListener {

    lateinit var presenter: SitePresenter
    var site = SiteModel()
    private val snapHelper = PagerSnapHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_site)

        init(toolbar_register, true)

        presenter = initPresenter(SitePresenter(this)) as SitePresenter

        val layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)
        imageRecyclerView.layoutManager = layoutManager
        snapHelper.attachToRecyclerView(imageRecyclerView)
        imageRecyclerView.addItemDecoration(LinePagerIndicatorDecoration(ContextCompat.getColor
          (this,R.color.pageIndecatorActive),ContextCompat.getColor(this,R.color.pageIndecatorInactive)))

        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync{

            presenter.doConfigureMap(it)
        }

        addImage.setOnClickListener{ it ->
            if (site.images.size < 4) {
                presenter.doSelectImage()
            } else {
                toast("you can only pick 4 images")
            }
        }

        siteLocation.setOnClickListener{
           presenter.doSetLocation()
        }

        checkBox_siteVisited.setOnClickListener{
            it -> presenter.doUpdateVisited()
        }

        ratingBar.setOnRatingBarChangeListener{
            ratingBar, rating, b -> presenter.doSetRating(rating.toInt())
        }
    }


    override fun showSite(site:SiteModel){
         siteName.setText(site.name)
         description.setText(site.description)
         checkBox_siteVisited.isChecked = site.visited.visited
         if(site.visited.visited){
             visitedDate.setText(DateUtils.toSimpleString(site.visited.date))
         }else{
             visitedDate.setText("")
         }
         additionalNotes.setText(site.additionalNotes)
         ratingBar.rating = site.rating.toFloat()
         imageRecyclerView.adapter = ImageAdapter(site.images,this)
         imageRecyclerView.adapter?.notifyDataSetChanged()
         this.site = site

        lat.setText("%.6f".format(site.location.lat))
        lng.setText("%.6f".format(site.location.lng))
     }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_site,menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.item_delete->{
                presenter.doDelete()
            }
            R.id.item_cancel->{
                presenter.doCancle()
            }
            R.id.item_save->{
                if(siteName.text.toString().isEmpty()){
                    toast(R.string.enter_site_name)
                }else{
                    presenter.doAddOrSave(siteName.text.toString(),description.text.toString(), additionalNotes.text.toString())
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(data != null){
            presenter.doActivityResult(requestCode,resultCode,data)
        }
    }
    override fun onImageRemove(image: org.wit.archaeologicalfieldwork.models.Image) {
        presenter.doRemoveImage(image)
        imageRecyclerView.adapter?.notifyDataSetChanged()
    }


    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    fun showDatePickerDialog(view:View){
        val newFragment = DatePickerFragment()
        newFragment.show(supportFragmentManager,"datePicker")
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayofMonth: Int) {
        presenter.doUpdateVisitedDate(year, month, dayofMonth)
    }

}
class DatePickerFragment : DialogFragment(){

    private lateinit var replaceFragmentListener: DatePickerDialog.OnDateSetListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        // Create a new instance of DatePickerDialog and return it
        return DatePickerDialog(activity, replaceFragmentListener, year, month, day)

    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        replaceFragmentListener = activity as SiteView
    }
}

object DateUtils{
    @JvmStatic
    fun toSimpleString(date:Date):String{
        val format = SimpleDateFormat("dd/MM/yyy")
        return format.format(date)
    }
}
