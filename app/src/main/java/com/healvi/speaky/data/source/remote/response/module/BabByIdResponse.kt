package com.healvi.speaky.data.source.remote.response.module

import android.os.Parcelable
import com.healvi.speaky.domain.model.module.Bab
import kotlinx.parcelize.Parcelize

@Parcelize
data class BabByIdResponse(
    var module: Bab? = null,
    var exception: Exception? = null
) : Parcelable