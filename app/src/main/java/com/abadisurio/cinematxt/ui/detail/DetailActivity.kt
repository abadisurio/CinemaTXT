package com.abadisurio.cinematxt.ui.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.abadisurio.cinematxt.R
import com.abadisurio.cinematxt.data.MovieEntity
import com.abadisurio.cinematxt.data.TVShowEntity
import com.abadisurio.cinematxt.databinding.ActivityDetailBinding
import com.abadisurio.cinematxt.databinding.ContentDetailBinding
import com.abadisurio.cinematxt.viewmodel.ViewModelFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class DetailActivity : AppCompatActivity() {

    private lateinit var contentDetailBinding: ContentDetailBinding
    private lateinit var activityDetailBinding: ActivityDetailBinding
    private lateinit var detailEntity: DetailEntity
    private var showTitle: String = "film"

    companion object {
        const val EXTRA_SHOW = "extra_show"
        const val EXTRA_TYPE = "extra_type"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        contentDetailBinding = activityDetailBinding.detailContent

        activityDetailBinding.progressBar.visibility = View.VISIBLE
        activityDetailBinding.content.visibility = View.INVISIBLE

        setContentView(activityDetailBinding.root)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        contentDetailBinding.btnShare.setOnClickListener{
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type="text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Lihat $showTitle sekarang!")
            startActivity(Intent.createChooser(shareIntent, "Bagikan" ))
        }

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]
        val extras = intent.extras


        if (extras != null) {
            val showId = extras.getString(EXTRA_SHOW)
            if (showId != null) {
                viewModel.setSelectedShow(showId)
                if (extras.getString(EXTRA_TYPE) == "Movie") {
                    viewModel.getMovie().observe(this, { movie ->
                        detailEntity = DetailEntity(
                            movie.movieId,
                            movie.title,
                            movie.description,
                            movie.releaseDate,
                            movie.imagePath
                        )
                        populateDetail(detailEntity)
                    })
                } else {
                    viewModel.getTVShow().observe(this, { tvShow ->
                        detailEntity = DetailEntity(
                            tvShow.tvShowId,
                            tvShow.title,
                            tvShow.description,
                            tvShow.releaseDate,
                            tvShow.imagePath
                        )
                        populateDetail(detailEntity)
                    })
                }
            }
        }

    }

    private fun loadSuccess(){
        activityDetailBinding.progressBar.visibility = View.GONE
        activityDetailBinding.content.visibility = View.VISIBLE
    }

    private fun populateDetail(detailEntity: DetailEntity) {
        contentDetailBinding.textTitle.text = detailEntity.title
        contentDetailBinding.textDescription.text = detailEntity.description
        contentDetailBinding.textDate.text = detailEntity.releaseDate
        showTitle = detailEntity.title
        activityDetailBinding.collapseToolbar.title = detailEntity.title
        val glide = Glide.with(this)
            .load(detailEntity.imagePath)
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error))

        glide.into(contentDetailBinding.imagePoster)
        glide.into(activityDetailBinding.imagePoster2)
        loadSuccess()
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}

data class DetailEntity(
    var ShowId: String,
    var title: String,
    var description: String,
    var releaseDate: String,
    var imagePath: String
)