package com.healvi.speaky.domain.model.pratice

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pratice(
    var key: String,
    var judul: String,
    var cover: String,
    var deskripsi: String,
    var ilustrasi: Ilustration
) : Parcelable {
    @Parcelize
    class Ilustration(
        val durasi: Long,
        val link: String
    ) : Parcelable
}