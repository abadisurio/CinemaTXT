package com.abadisurio.cinematxt.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.abadisurio.cinematxt.data.source.local.entity.MovieEntity
import com.abadisurio.cinematxt.data.source.local.entity.TVShowEntity

@Database(entities = [MovieEntity::class, TVShowEntity::class],
    version = 1,
    exportSchema = false)
abstract class CinemaTXTDatabase : RoomDatabase() {
    abstract fun cinemaTXTDao(): CinemaTXTDao

    companion object {

        @Volatile
        private var INSTANCE: CinemaTXTDatabase? = null

        fun getInstance(context: Context): CinemaTXTDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    CinemaTXTDatabase::class.java,
                    "Academies.db"
                ).build().apply {
                    INSTANCE = this
                }
            }
    }
}