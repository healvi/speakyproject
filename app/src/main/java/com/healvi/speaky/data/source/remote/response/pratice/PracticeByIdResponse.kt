package com.healvi.speaky.data.source.remote.response.pratice

import android.os.Parcelable
import com.healvi.speaky.domain.model.pratice.Pratice
import kotlinx.parcelize.Parcelize

@Parcelize
data class PracticeByIdResponse(
    var pratice: Pratice? = null,
    var exception: Exception? = null
) : Parcelable