package com.healvi.speaky.data.source.remote.response.assesment

import android.os.Parcelable
import com.healvi.speaky.domain.model.assesment.AssessmentPack
import kotlinx.parcelize.Parcelize

@Parcelize
data class AssessmentPackResponse(
    var pack: List<AssessmentPack>? = null,
    var exception: Exception? = null
) : Parcelable