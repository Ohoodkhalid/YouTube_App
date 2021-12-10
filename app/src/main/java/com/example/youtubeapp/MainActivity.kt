package com.example.youtubeapp

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer

import androidx.annotation.NonNull
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener




class MainActivity : AppCompatActivity() {
    lateinit var  youTubePlayerView: YouTubePlayerView
    lateinit var player: YouTubePlayer
    var listOfVideo = arrayListOf(
        (YouTubeDetails("How to make Pancakes ", "FLd00Bx4tOk", "Pancakes")),
        (YouTubeDetails("How to Make the Best Waffles!", "iR64hfkGQeU", "Waffles")),
        (YouTubeDetails("How to make Cinnamon Rolls ", "NcT1sT1gkEw", "Cinnamon")),
        (YouTubeDetails("How to Make Blueberry Lemon Cake", "22ExhAyUNFQ", "Blueberry Lemon Cake")),
        (YouTubeDetails("Amazing Carrot Cake Recipe", "zoyhs-EiJxE", "Carrot Cake")))

    lateinit var recView:RecyclerView
    private var currentVideo=0
    private var timeStamp = 0f




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recView = findViewById(R.id.recyclerview)
        youTubePlayerView = findViewById(R.id.youtube_player_view)
        getLifecycle().addObserver(youTubePlayerView)

        checkInternetConnection()




    }
    fun checkInternetConnection() {
        //Internet Connection Layout
        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        activeNetwork?.isConnectedOrConnecting
        //Check internet connection
        if (activeNetwork?.isConnectedOrConnecting == true) {

            initializeRV()

        } else {
            showDialog()

        }
    }


    fun initializeRV() {

        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                player= youTubePlayer
                player.loadVideo(listOfVideo[currentVideo].ID,timeStamp)

                //RecyclerView
                recView.adapter = RecyclerViewAdapter(listOfVideo, youTubePlayer)
                recView.layoutManager = LinearLayoutManager(applicationContext)
                recView.setHasFixedSize(true)
            }
        })

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt("currentVideo", currentVideo)
        outState.putFloat("timeStamp", timeStamp)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        currentVideo = savedInstanceState.getInt("currentVideo", 0)
        timeStamp = savedInstanceState.getFloat("timeStamp", 0f)
    }


    fun showDialog() {

        val builder = AlertDialog.Builder(this)
        //  set title for alert dialog
        builder.setTitle("Error")
        //  set Message for alert dialog
        builder.setMessage("Please make sure you are connected to internet")


        builder.setNegativeButton("OK"){dialogInterface, which ->}
        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()

        alertDialog.show()
    }

}