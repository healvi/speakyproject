package com.healvi.speaky.data.source.remote.response.assesment

import android.os.Parcelable
import com.healvi.speaky.domain.model.assesment.AssementInstruction
import kotlinx.parcelize.Parcelize

@Parcelize
class InstructionResponse(
    var intruction: AssementInstruction? = null,
    var exception: Exception? = null
) : Parcelable