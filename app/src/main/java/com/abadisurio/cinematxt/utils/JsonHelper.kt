package com.abadisurio.cinematxt.utils

import android.content.Context
import com.abadisurio.cinematxt.data.source.remote.response.MovieResponse
import com.abadisurio.cinematxt.data.source.remote.response.TVShowResponse
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class JsonHelper(private val context: Context) {

    private fun parsingFileToString(fileName: String): String? {
        return try {
            val `is` = context.assets.open(fileName)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)

        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

    fun loadTVShows(): List<TVShowResponse> {
        val list = ArrayList<TVShowResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("TVShowResponses.json").toString())
            val listArray = responseObject.getJSONArray("tvShows")
            for (i in 0 until listArray.length()) {
                val tvShow = listArray.getJSONObject(i)

                val id = tvShow.getString("id")
                val title = tvShow.getString("title")
                val description = tvShow.getString("description")
                val releaseDate = tvShow.getString("date")
                val imagePath = tvShow.getString("imagePath")

                val tvShowResponse = TVShowResponse(id, title, description, releaseDate, imagePath)
                list.add(tvShowResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }

    fun loadMovies(): List<MovieResponse> {
        val list = ArrayList<MovieResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("MovieResponses.json").toString())
            val listArray = responseObject.getJSONArray("movies")
            for (i in 0 until listArray.length()) {
                val movie = listArray.getJSONObject(i)

                val id = movie.getString("id")
                val title = movie.getString("title")
                val description = movie.getString("description")
                val releaseDate = movie.getString("date")
                val imagePath = movie.getString("imagePath")

                val movieResponse = MovieResponse(id, title, description, releaseDate, imagePath)
                list.add(movieResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }

    fun loadDetailMovies(filmId: String): List<MovieResponse> {
        val fileName = String.format("MovieResponses.json", filmId)
        val list = ArrayList<MovieResponse>()
        try {
            val result = parsingFileToString(fileName)
            if (result != null) {
                val responseObject = JSONObject(result)
                val listArray = responseObject.getJSONArray("movies")
                for (i in 0 until listArray.length()) {
                    val movie = listArray.getJSONObject(i)

                    val id = movie.getString("id")
                    val title = movie.getString("title")
                    val description = movie.getString("description")
                    val releaseDate = movie.getString("date")
                    val imagePath = movie.getString("imagePath")

                    val movieResponse = MovieResponse(id, title, description, releaseDate, imagePath)
                    list.add(movieResponse)
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }

    fun loadDetailTVShows(filmId: String): List<TVShowResponse> {
        val fileName = String.format("TVShowResponses.json", filmId)
        val list = ArrayList<TVShowResponse>()
        try {
            val result = parsingFileToString(fileName)
            if (result != null) {
                val responseObject = JSONObject(result)
                val listArray = responseObject.getJSONArray("tvShows")
                for (i in 0 until listArray.length()) {
                    val tvShow = listArray.getJSONObject(i)

                    val id = tvShow.getString("id")
                    val title = tvShow.getString("title")
                    val description = tvShow.getString("description")
                    val releaseDate = tvShow.getString("date")
                    val imagePath = tvShow.getString("imagePath")

                    val tvShowResponse = TVShowResponse(id, title, description, releaseDate, imagePath)
                    list.add(tvShowResponse)
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }

}