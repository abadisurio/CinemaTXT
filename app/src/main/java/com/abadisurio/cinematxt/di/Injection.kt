package com.abadisurio.cinematxt.di

import android.content.Context
import com.abadisurio.cinematxt.data.source.CinemaTXTRepository
import com.abadisurio.cinematxt.data.source.remote.response.RemoteDataSource
import com.abadisurio.cinematxt.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): CinemaTXTRepository {

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))

        return CinemaTXTRepository.getInstance(remoteDataSource)
    }
}