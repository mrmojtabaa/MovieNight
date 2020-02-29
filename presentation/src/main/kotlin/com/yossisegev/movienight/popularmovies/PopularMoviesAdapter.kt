package com.yossisegev.movienight.popularmovies


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yossisegev.movienight.R
import com.yossisegev.movienight.common.ImageLoader
import com.yossisegev.movienight.entities.Movie
import kotlinx.android.synthetic.main.popular_movies_adapter_cell.view.*

/**
 * Created by Yossi Segev on 14/11/2017.
 */
class PopularMoviesAdapter constructor(private val imageLoader: ImageLoader,
                                       private val onMovieSelected: (Movie, View) -> Unit) : RecyclerView.Adapter<PopularMoviesAdapter.MovieCellViewHolder>() {

    private val movies: MutableList<Movie> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCellViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
                R.layout.popular_movies_adapter_cell,
                parent,
                false)
        return MovieCellViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieCellViewHolder, position: Int) {
        holder.bind(position, imageLoader)
    }

    fun addMovies(movies: List<Movie>) {
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }

    fun getMovie(position: Int) = movies[position]

    inner class MovieCellViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener { onMovieSelected(getMovie(adapterPosition), itemView) }
        }

        fun bind(position: Int, imageLoader: ImageLoader) = with(itemView) {
            title.text = getMovie(position).originalTitle
            getMovie(position).posterPath?.let { imageLoader.load(it, image) }
            //setOnClickListener { listener(movie, itemView) }
        }

    }
}