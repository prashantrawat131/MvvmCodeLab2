package com.example.mvvmcodelab2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmcodelab2.databinding.RepoItemBinding

class RvAdapter : RecyclerView.Adapter<MainViewHolder>() {
    var movies = mutableListOf<Repo>()

    fun setMovieList(movies: List<Repo>) {
        this.movies = movies.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RepoItemBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val movie = movies[position]
        holder.binding.repoName.text = movie.name
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}

class MainViewHolder(val binding: RepoItemBinding) : RecyclerView.ViewHolder(binding.root) {
}