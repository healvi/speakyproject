package com.healvi.speaky.data.source.remote.response.module

import android.os.Parcelable
import com.healvi.speaky.domain.model.module.UserModule
import kotlinx.parcelize.Parcelize


@Parcelize
data class UserModuleResponse(
    var userModule: List<UserModule>? = null,
    var exception: Exception? = null
) : Parcelable