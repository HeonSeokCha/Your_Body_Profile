package com.chs.your_body_profile.data.source.db.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

interface BaseDao<T> {
    @Insert
    suspend fun insert(vararg entity: T)

    @Delete
    suspend fun delete(entity: T)

    @Update
    suspend fun update(entity: T)
}