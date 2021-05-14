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

    fun loadMovies(): List<MovieResponse> {
        val list = ArrayList<MovieResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("CourseResponses.json").toString())
            val listArray = responseObject.getJSONArray("courses")
            for (i in 0 until listArray.length()) {
                val course = listArray.getJSONObject(i)

                val id = course.getString("id")
                val title = course.getString("title")
                val description = course.getString("description")
                val releaseDate = course.getString("date")
                val imagePath = course.getString("imagePath")

                val courseResponse = MovieResponse(id, title, description, releaseDate, imagePath)
                list.add(courseResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }

    fun loadTVShows(): List<TVShowResponse> {
        val list = ArrayList<TVShowResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("CourseResponses.json").toString())
            val listArray = responseObject.getJSONArray("courses")
            for (i in 0 until listArray.length()) {
                val course = listArray.getJSONObject(i)

                val id = course.getString("id")
                val title = course.getString("title")
                val description = course.getString("description")
                val releaseDate = course.getString("date")
                val imagePath = course.getString("imagePath")

                val courseResponse = TVShowResponse(id, title, description, releaseDate, imagePath)
                list.add(courseResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }
}