package com.example.posts_app.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String,
    val isFavourite: Boolean
): Parcelable

