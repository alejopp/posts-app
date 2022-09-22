package com.example.posts_app.data.models.dto.user

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Geo(
    val lat: String,
    val lng: String
):Parcelable