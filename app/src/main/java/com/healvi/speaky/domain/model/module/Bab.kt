package com.healvi.speaky.domain.model.module

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Bab(
    val key: String,
    val no: String,
    val time: Long,
    val konten: String,
    val judul: String,
    val video: String,
    val practice: List<practices>,
) : Parcelable {
    @Parcelize
    data class practices(
        val key: String,
        val time: Long
    ) : Parcelable
}

