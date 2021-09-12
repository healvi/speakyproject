package com.healvi.speaky.domain.model.assesment

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AssessmentPack(
    var id: String,
    var title: String,
    var type: String,
    var petunjuk: String,
    var guide: List<String>
) : Parcelable