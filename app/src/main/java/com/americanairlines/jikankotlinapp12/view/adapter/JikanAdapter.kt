package com.americanairlines.jikankotlinapp12.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.americanairlines.jikankotlinapp12.R
import com.americanairlines.jikankotlinapp12.model.JikanResult
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class JikanAdapter(private var jikanList: List<JikanResult>): RecyclerView.Adapter<JikanAdapter.JikanViewHolder>() {

    fun updateJikanList(jikanList: List<JikanResult>) {
        this.jikanList = jikanList
        notifyDataSetChanged()
    }

     class JikanViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
         val posterImageView: ImageView = itemView.findViewById(R.id.jikan_poster_imageview)
         val movieTitleTextView: TextView = itemView.findViewById(R.id.title_textview)
         val movieSynopsis: TextView = itemView.findViewById(R.id.synopsis_textview)
     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JikanViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.jikan_result_item, parent, false)
        return JikanViewHolder(itemView)
    }

    override fun getItemCount(): Int = jikanList.size

    override fun onBindViewHolder(holder: JikanViewHolder, position: Int) {
        val jikanItem = jikanList[position]

        holder.apply {
            movieSynopsis.text = jikanItem.synopsis
            movieTitleTextView.text = jikanItem.title

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(RequestOptions().centerCrop())
                .load(jikanItem.image_url)
                .into(posterImageView)
        }
    }

}