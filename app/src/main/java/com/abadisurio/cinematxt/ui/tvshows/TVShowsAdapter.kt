package com.abadisurio.cinematxt.ui.tvshows

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abadisurio.cinematxt.R
import com.abadisurio.cinematxt.data.source.local.entity.TVShowEntity
import com.abadisurio.cinematxt.databinding.ItemsRowBinding
import com.abadisurio.cinematxt.ui.detail.DetailActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class TVShowsAdapter : RecyclerView.Adapter<TVShowsAdapter.TVShowsViewHolder>() {
    private var listTVShows = ArrayList<TVShowEntity>()

    fun setTVShows(tvshows: List<TVShowEntity>?) {
//        Log.d("kursus: ", tvshows.toString())
        if (tvshows == null) return
        this.listTVShows.clear()
        this.listTVShows.addAll(tvshows)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVShowsViewHolder {
        val itemsAcademyBinding = ItemsRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TVShowsViewHolder(itemsAcademyBinding)
    }

    override fun onBindViewHolder(holder: TVShowsViewHolder, position: Int) {
        val tvShow = listTVShows[position]
        holder.bind(tvShow)
    }

    override fun getItemCount(): Int = listTVShows.size


    class TVShowsViewHolder(private val binding: ItemsRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TVShowEntity) {
            with(binding) {
                tvItemTitle.text = tvShow.title
                tvItemDescription.text = tvShow.description
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_SHOW, tvShow.tvShowId)
                    intent.putExtra(DetailActivity.EXTRA_TYPE, "TVShow")
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(tvShow.imagePath)
                    .override(600, 200)
                    .fitCenter()
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error))
                    .into(imgPoster)
            }
        }
    }
}