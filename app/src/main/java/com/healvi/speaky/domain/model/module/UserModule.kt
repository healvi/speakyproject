package com.healvi.speaky.domain.model.module

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserModule(
    val key: String,
    val bab: Bab
) : Parcelable {
    @Parcelize
    data class Bab(
        val key: String,
        val status: String,
    ) : Parcelable

}
