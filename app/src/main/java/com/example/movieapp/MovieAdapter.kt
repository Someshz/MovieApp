package com.example.movieapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.model.Result
import java.util.zip.Inflater

class MovieAdapter : PagingDataAdapter<Result, MovieAdapter.MovieViewHolder>(COMPARATOR) {

    lateinit var listItems: List<Result>

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val movieName = view.findViewById<TextView>(R.id.movieName)
        val movieImage = view.findViewById<ImageView>(R.id.movieImage)
        val movieOverView = view.findViewById<TextView>(R.id.overView)
    }

    companion object {
        val COMPARATOR = object : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = getItem(position)
        Log.d("checking", item?.original_title.toString())
        holder.movieName.text = item?.title
        holder.movieOverView.text = item?.overview
        Glide.with(holder.itemView.context)
            .load("https://image.tmdb.org/t/p/original" + item?.poster_path)
            .placeholder(R.drawable.default_image).into(holder.movieImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        listItems = snapshot().items
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(view)
    }

    suspend fun filter(query: String?) {
        query?.let {
            val filteredMovies = listItems.filter { movie ->
                movie.title.contains(query, true)
            }
            submitData(PagingData.from(filteredMovies))
        }
    }
}