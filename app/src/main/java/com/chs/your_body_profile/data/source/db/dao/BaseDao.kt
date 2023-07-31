package com.chs.your_body_profile.data.source.db.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

interface BaseDao<T> {
    @Insert
    suspend fun<T> insert(entity: T)

    @Delete
    suspend fun<T> delete(entity: T)

    @Update
    suspend fun<T> update(entity: T)
}