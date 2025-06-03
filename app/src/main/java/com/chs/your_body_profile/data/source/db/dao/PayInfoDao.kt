package com.chs.your_body_profile.data.source.db.dao

import androidx.room.Dao
import androidx.room.MapColumn
import androidx.room.Query
import com.chs.your_body_profile.data.source.db.entity.PayInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class PayInfoDao() : BaseDao<PayInfoEntity> {
    @Query("""
        SELECT * 
          FROM pay_info
         ORDER BY paymentTime DESC LIMIT 1
    """)
    abstract fun getDayLastInfo(): Flow<PayInfoEntity?>

    @Query("""
        SELECT unixepoch(DATE(paymentTime / 1000, 'unixepoch', 'localtime')) * 1000 as date, *
          FROM pay_info
         ORDER BY date DESC
         LIMIT 15
         OFFSET :page
    """)
    abstract suspend fun getPagingDayInfoList(
        page: Int
    ): Map<@MapColumn("date") Long, List<PayInfoEntity>>
}
