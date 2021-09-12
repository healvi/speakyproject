package com.healvi.speaky.data.source.remote.response.pratice

import android.os.Parcelable
import com.healvi.speaky.domain.model.pratice.Pratice
import kotlinx.parcelize.Parcelize

@Parcelize
data class PracticeResponse(
    var pratice: List<Pratice>? = null,
    var exception: Exception? = null
) : Parcelable