package com.abadisurio.cinematxt.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tvshowentities")
data class TVShowEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var tvShowId: String,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "description")
    var description: String,
    @ColumnInfo(name = "releaseDate")
    var releaseDate: String,
    @ColumnInfo(name = "imagePath")
    var imagePath: String,
    @ColumnInfo(name = "favorited")
    var favorited: Boolean = false
)