package com.example.posts_app.data.db

import android.database.sqlite.SQLiteException
import com.example.posts_app.R
import com.example.posts_app.utils.ResponseStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun <T> makeDatabaseCall(
    call: suspend () -> T
): ResponseStatus<T> = withContext(Dispatchers.IO) {
    try {
        ResponseStatus.Success(call())
    } catch (e: SQLiteException) {
        ResponseStatus.Error(R.string.unknown_sql_exception)
    } catch (e: Exception) {
        val errorMessage = R.string.database_error
        ResponseStatus.Error(errorMessage)
    }
}