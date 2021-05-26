package com.abadisurio.cinematxt.di

import android.content.Context
import com.abadisurio.cinematxt.data.CinemaTXTRepository
import com.abadisurio.cinematxt.data.source.local.LocalDataSource
import com.abadisurio.cinematxt.data.source.local.room.CinemaTXTDatabase
import com.abadisurio.cinematxt.data.source.remote.response.RemoteDataSource
import com.abadisurio.cinematxt.utils.AppExecutors
import com.abadisurio.cinematxt.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): CinemaTXTRepository {
        val database = CinemaTXTDatabase.getInstance(context)
        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val localDataSource = LocalDataSource.getInstance(database.cinemaTXTDao())
        val appExecutors = AppExecutors()
        return CinemaTXTRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}