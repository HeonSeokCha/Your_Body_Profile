package com.chs.your_body_profile.domain.repository


interface BaseRepository <T> {

    suspend fun upsertInfo(info: T)

    suspend fun deleteInfo(info: T)

}