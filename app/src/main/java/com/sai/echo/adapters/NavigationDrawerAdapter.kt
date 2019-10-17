package com.sai.echo.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.sai.echo.fragments.AboutUsFragment
import com.sai.echo.fragments.FavouriteFragment
import com.sai.echo.fragments.SettingsFragment
import com.sai.echo.R
import com.sai.echo.activities.MainActivity
import com.sai.echo.fragments.MainScreenFragment


class NavigationDrawerAdapter(_contentList: ArrayList<String>, _getImages: IntArray, _context: Context)
    : RecyclerView.Adapter<NavigationDrawerAdapter.NavViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavViewHolder {


        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.row_custom_navigationdrawer, parent, false)

        val returnThis = NavViewHolder(itemView)
        return returnThis

    }

    override fun onBindViewHolder(holder: NavViewHolder, position: Int) {

        /*Here we set the icon and the name of that icon with the setBackgroundResource() and the setText() method respectively*/
        holder?.icon_GET?.setBackgroundResource(getImages?.get(position) as Int)
        holder?.text_GET?.setText(contentList?.get(position))

        /*Now since we want to open a new fragment at the click for every item we place the click listener according to the position of the items*/
        holder?.contentHolder?.setOnClickListener({

            /*Loading the Main Screen Fragment as the first(remember that the index starts at 0) item is All songs and the fragment corresponding to it is the Main Screen fragment*/
            if (position == 0) {
                val mainScreenFragment = MainScreenFragment()
                (mContext as MainActivity).supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.details_fragment, mainScreenFragment)
                        .commit()
            }

            /*The next item is the Favorites option and the fragment corresponding to it is the favorite fragment at position 1*/
            else if (position == 1) {
                val favoriteFragment = FavouriteFragment()
                (mContext as MainActivity).supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.details_fragment, favoriteFragment)
                        .commit()
            }

            /*Similarly to the above we load the Settings and the About Us fragment respectively*/
            else if (position == 2) {
                val settingsFragment = SettingsFragment()
                (mContext as MainActivity).supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.details_fragment, settingsFragment)
                        .commit()
            } else if (position == 3) {
                val aboutUsFragment = AboutUsFragment()
                (mContext as MainActivity).supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.details_fragment, aboutUsFragment)
                        .commit()
            }


            MainActivity.Statified.drawerLayout?.closeDrawers()
        })
    }

    /*Declaring the variables used*/
    var contentList: ArrayList<String>? = null
    var getImages: IntArray? = null
    var mContext: Context? = null

    /*This is the constructor initialisation of the parameters. This converts the data passed from the parameters as the local params, which are used in this class*/
    init {
        this.contentList = _contentList
        this.getImages = _getImages
        this.mContext = _context
    }




       override fun getItemCount(): Int {


        return (contentList as ArrayList).size
    }

    /*Class for creating a view holder for our recycler view. This class sets up the single object for our recycler view*/
    class NavViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        /*Declaring the widgets and the layout used*/
        var icon_GET: ImageView? = null
        var text_GET: TextView? = null
        var contentHolder: RelativeLayout? = null

        /*Constructor initialisation for the variables*/
        init {
            icon_GET = itemView?.findViewById(R.id.icon_navdrawer)
            text_GET = itemView?.findViewById(R.id.text_navdrawer)
            contentHolder = itemView?.findViewById(R.id.navdrawer_item_content_holder)
        }
    }
}