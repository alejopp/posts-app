package com.example.posts_app.data.di

import android.content.Context
import androidx.room.Room
import com.example.posts_app.data.db.PostDatabase
import com.example.posts_app.utils.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomProvider {

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, PostDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideMovieDao(db: PostDatabase) = db.getPostDao()
}