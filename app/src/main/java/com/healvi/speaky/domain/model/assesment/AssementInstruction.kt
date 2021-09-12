package com.healvi.speaky.domain.model.assesment

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AssementInstruction(
    var type: String,
    var intruksi: List<String>
) : Parcelable