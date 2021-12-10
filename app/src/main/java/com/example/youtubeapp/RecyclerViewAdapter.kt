package com.example.youtubeapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.android.synthetic.main.item_video.view.*

class RecyclerViewAdapter (var videos:List< YouTubeDetails >,val youTubePlayer: YouTubePlayer) : RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent,false))
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val  video =  videos[position]


        holder.itemView.apply {
            val videoTitle = video.title
            val videoID = video.ID
            val videoImage = video.Image

            titleTv.text = videoTitle









            var image = 0
            when {
                videoImage == "Pancakes" ->image = R.drawable.cake
                videoImage == "Waffles" -> image = R.drawable.waffles1
                videoImage== "Cinnamon" -> image = R.drawable.cinnamon
                videoImage == "Blueberry Lemon Cake" -> image = R.drawable.blueberrylemoncake
                videoImage == "Carrot Cake" -> image = R.drawable.carrot


            }
           videoImageView.setImageResource(image)
           titleTv.setOnClickListener{
               youTubePlayer.loadVideo(videoID, 0f)

           }
        }


    }

    override fun getItemCount() =  videos.size
}