package com.healvi.speaky.domain.model.assesment

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Assessment(
    val donwloadUrl: String,
    val score: Long,
    val timeStamp: String,
    val blink: Long,
    val disfluency: Long,
    val gaze: Long,
) : Parcelable
