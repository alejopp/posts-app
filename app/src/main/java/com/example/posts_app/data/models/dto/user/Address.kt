package com.example.posts_app.data.models.dto.user

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


data class Address(
    val city: String,
    val geo: Geo,
    val street: String,
    val suite: String,
    val zipcode: String
)

