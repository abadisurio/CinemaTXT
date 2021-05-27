package com.abadisurio.cinematxt.ui.bookmarktvshows

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.abadisurio.cinematxt.R
import com.abadisurio.cinematxt.data.source.local.entity.TVShowEntity
import com.abadisurio.cinematxt.databinding.ItemsRowBinding
import com.abadisurio.cinematxt.ui.detail.DetailActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class BookmarkTVShowsAdapter : PagedListAdapter<TVShowEntity, BookmarkTVShowsAdapter.TVShowsViewHolder>(DIFF_CALLBACK) {
    private var listTVShows = ArrayList<TVShowEntity>()

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TVShowEntity>() {
            override fun areItemsTheSame(oldItem: TVShowEntity, newItem: TVShowEntity): Boolean {
                return oldItem.tvShowId == newItem.tvShowId
            }
            override fun areContentsTheSame(oldItem: TVShowEntity, newItem: TVShowEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    fun setTVShows(tvshows: List<TVShowEntity>?) {
        if (tvshows == null) return
        this.listTVShows.clear()
        this.listTVShows.addAll(tvshows)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVShowsViewHolder {
        val itemsAcademyBinding = ItemsRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TVShowsViewHolder(itemsAcademyBinding)
    }

    override fun onBindViewHolder(holder: TVShowsViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null) {
            holder.bind(tvShow)
        }
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