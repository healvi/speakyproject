package com.healvi.speaky.data.source.remote.response.module

import android.os.Parcelable
import com.healvi.speaky.domain.model.module.Module
import kotlinx.parcelize.Parcelize

@Parcelize
data class ModuleByIdResponse(
    var module: Module? = null,
    var exception: Exception? = null
) : Parcelable