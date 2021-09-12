package com.healvi.speaky.data.source.remote

import android.os.Parcelable
import com.healvi.speaky.data.source.local.entity.UserEntity
import com.healvi.speaky.domain.model.User
import com.healvi.speaky.domain.model.assesment.Assessment
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserDashboardResponse(
    var dashboardData: UserEntity? = null,
    var exception: Exception? = null
) : Parcelable