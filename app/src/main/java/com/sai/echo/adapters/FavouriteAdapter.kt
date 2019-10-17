package com.sai.echo.adapters

import android.content.Context
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.sai.echo.R
import com.sai.echo.Songs
import com.sai.echo.fragments.SongPlayingFragment

class FavouriteAdapter(_songDetails: ArrayList<Songs>, _context: Context) : RecyclerView.Adapter<FavouriteAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent?.context)
                .inflate(R.layout.row_custom_mainscreen_adapter, parent, false)
        return MyViewHolder(itemView)

    }


    var songDetails: ArrayList<Songs>? = null
    var mContext: Context? = null

    /*In the init block we assign the data received from the params to our local variables*/
    init {
        this.songDetails = _songDetails
        this.mContext = _context
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val songObject = songDetails?.get(position)

        /*The holder object of our MyViewHolder class has two properties i.e
        * trackTitle for holding the name of the song and
        * trackArtist for holding the name of the artist*/
        holder.trackTitle?.text = songObject?.songTitle
        holder.trackArtist?.text = songObject?.artist

        /*Handling the click event i.e. the action which happens when we click on any song*/
        holder.contentHolder?.setOnClickListener({


            val songPlayingFragment = SongPlayingFragment()

            /*A bundle is used to transfer data from one point in your activity to another
            * Here we create an object of Bundle to send the sond details to the fragment so that we can display the song details there and also play the song*/
            var args = Bundle()

            args.putString("songArtist", songObject?.artist)
            args.putString("songTitle", songObject?.songTitle)
            args.putString("path", songObject?.songData)
            args.putInt("SongID", songObject?.songID?.toInt() as Int)
            args.putInt("songPosition", position)

            /*Here the complete array list is sent*/
            args.putParcelableArrayList("songData", songDetails)

            /*Using this we pass the arguments to the song playing fragment*/
            songPlayingFragment.arguments = args

            /*Now after placing the song details inside the bundle, we inflate the song playing fragment*/
            (mContext as FragmentActivity).supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.details_fragment, songPlayingFragment)
                    .addToBackStack("SongPlayingFragmentFavourite")
                    .commit()
        })
    }


    override fun getItemCount(): Int {


        if (songDetails == null) {
            return 0
        }


        else {
            return (songDetails as ArrayList<Songs>).size
        }
    }


    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        /*Declaring the widgets and the layout used*/
        var trackTitle: TextView? = null
        var trackArtist: TextView? = null
        var contentHolder: RelativeLayout? = null

        /*Constructor initialisation for the variables*/
        init {
            trackTitle = view.findViewById(R.id.trackTitle) as TextView
            trackArtist = view.findViewById(R.id.trackArtist) as TextView
            contentHolder = view.findViewById(R.id.contentRow) as RelativeLayout
        }
    }
}