package com.abadisurio.cinematxt.ui.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.abadisurio.cinematxt.R
import com.abadisurio.cinematxt.databinding.ActivityDetailBinding
import com.abadisurio.cinematxt.databinding.ContentDetailBinding
import com.abadisurio.cinematxt.viewmodel.ViewModelFactory
import com.abadisurio.cinematxt.vo.Status
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class DetailActivity : AppCompatActivity() {

    private lateinit var activityDetailBinding: ActivityDetailBinding
    private lateinit var contentDetailBinding: ContentDetailBinding
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
                if (extras.getString(EXTRA_TYPE) == "Movie") {
                    viewModel.setSelectedMovie(showId)
                    viewModel.detailMovie.observe(this, { movieWithModuleResource ->
                        if (movieWithModuleResource != null) {
                            val data = movieWithModuleResource.data
                            when (movieWithModuleResource.status) {
                                Status.LOADING -> activityDetailBinding.progressBar.visibility = View.VISIBLE
                                Status.SUCCESS -> if (data != null) {
                                    activityDetailBinding.progressBar.visibility = View.GONE
                                    activityDetailBinding.content.visibility = View.VISIBLE
                                    detailEntity = DetailEntity(
                                        data.movieId,
                                        data.title,
                                        data.description,
                                        data.releaseDate,
                                        data.imagePath
                                    )
                                    populateDetail(detailEntity)
                                }
                                Status.ERROR -> {
                                    activityDetailBinding.progressBar.visibility = View.GONE
                                    Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    })
                } else {
                    viewModel.setSelectedTVShow(showId)
                    viewModel.detailTVShow.observe(this, { tvShowWithModuleResource ->
                        if (tvShowWithModuleResource != null) {
                            val data = tvShowWithModuleResource.data
                            when (tvShowWithModuleResource.status) {
                                Status.LOADING -> activityDetailBinding.progressBar.visibility = View.VISIBLE
                                Status.SUCCESS -> if (data != null) {
                                    activityDetailBinding.progressBar.visibility = View.GONE
                                    activityDetailBinding.content.visibility = View.VISIBLE
                                    detailEntity = DetailEntity(
                                        data.tvShowId,
                                        data.title,
                                        data.description,
                                        data.releaseDate,
                                        data.imagePath
                                    )
                                    populateDetail(detailEntity)
                                }
                                Status.ERROR -> {
                                    activityDetailBinding.progressBar.visibility = View.GONE
                                    Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
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