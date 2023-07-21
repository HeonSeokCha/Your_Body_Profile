package com.chs.your_body_profile.data.source.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chs.your_body_profile.data.source.db.entity.BodyInfoEntity

@Database(
    entities = [
        BodyInfoEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class BodyProfileDataBase : RoomDatabase() {
}