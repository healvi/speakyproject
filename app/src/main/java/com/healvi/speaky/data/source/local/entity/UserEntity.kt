package com.healvi.speaky.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity(tableName = "User")
@Parcelize
data class UserEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id :Int? = 0,

    @ColumnInfo(name = "uuid")
    val uuid : String? = null,

    @ColumnInfo(name = "name")
    val name: String? = null,

    @ColumnInfo(name = "username")
    val username: String? = null,

    @ColumnInfo(name = "email")
    val email: String? = null,

    @ColumnInfo(name = "imgPhoto")
    val imgPhoto: String? = null,

    @ColumnInfo(name = "level")
    val level: String? = null,

    @ColumnInfo(name = "status")
    val status: Boolean? = false,

    @ColumnInfo(name = "score")
    val score: Int = 0
) : Parcelable
