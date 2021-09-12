package com.healvi.speaky.data.source.remote.response.assesment

import android.os.Parcelable
import com.healvi.speaky.domain.model.assesment.Assessment
import kotlinx.parcelize.Parcelize


@Parcelize
data class UserAssesmentResponse(
    var history: List<Assessment>? = null,
    var exception: Exception? = null
) : Parcelable