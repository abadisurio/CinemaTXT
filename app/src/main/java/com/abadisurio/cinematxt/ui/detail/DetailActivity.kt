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
    private var showTitle: String = "film"

    companion object {
        const val EXTRA_SHOW = "extra_show"
        const val EXTRA_TYPE = "extra_type"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        contentDetailBinding = activityDetailBinding.detailContent

        setContentView(activityDetailBinding.root)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
//        val adapter = DetailAdapter()

        activityDetailBinding.progressBar.visibility = View.VISIBLE

        contentDetailBinding.btnShare.setOnClickListener{
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type="text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Lihat $showTitle sekarang!")
            startActivity(Intent.createChooser(shareIntent, "Bagikan" ))
        }
        val factory = ViewModelFactory.getInstance(this)
//            val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MoviesViewModel::class.java]
        val viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]
//        val viewModel = ViewModelProvider(
//            this,
//            ViewModelProvider.NewInstanceFactory()
//        )[DetailViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val showId = extras.getString(EXTRA_SHOW)
            if (showId != null) {
                viewModel.setSelectedShow(showId)
                if (extras.getString(EXTRA_TYPE) == "Movie") {
                    populateMovie(viewModel.getMovie())
                } else {
                    populateTVShow(viewModel.getTVShow())
                }
            }
        }

    }

    private fun populateMovie(movieEntity: MovieEntity) {
        contentDetailBinding.textTitle.text = movieEntity.title
        contentDetailBinding.textDescription.text = movieEntity.description
        contentDetailBinding.textDate.text = movieEntity.releaseDate
        supportActionBar?.title = movieEntity.title
        showTitle = movieEntity.title
            Glide.with(this)
            .load(movieEntity.imagePath)
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error))
            .into(contentDetailBinding.imagePoster)
        Glide.with(this)
            .load(movieEntity.imagePath)
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error))
            .into(activityDetailBinding.imagePoster2)
        activityDetailBinding.progressBar.visibility = View.GONE
    }
    private fun populateTVShow(tvShowEntity: TVShowEntity) {
        contentDetailBinding.textTitle.text = tvShowEntity.title
        contentDetailBinding.textDescription.text = tvShowEntity.description
        contentDetailBinding.textDate.text = tvShowEntity.releaseDate
        supportActionBar?.title = tvShowEntity.title
        showTitle = tvShowEntity.title
        Glide.with(this)
            .load(tvShowEntity.imagePath)
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error))
            .into(contentDetailBinding.imagePoster)
        Glide.with(this)
            .load(tvShowEntity.imagePath)
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error))
            .into(activityDetailBinding.imagePoster2)
        activityDetailBinding.progressBar.visibility = View.GONE
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}