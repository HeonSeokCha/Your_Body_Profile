package com.chs.your_body_profile.data.source.db.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

interface BaseDao<T> {
    @Upsert
    suspend fun upsert(entity: T)

    @Delete
    suspend fun delete(entity: T)
}