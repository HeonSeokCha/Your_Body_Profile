package com.chs.your_body_profile.data.mapper

import com.chs.your_body_profile.common.toLocalDate
import com.chs.your_body_profile.common.toLocalDateTime
import com.chs.your_body_profile.common.toLocalDateToMillis
import com.chs.your_body_profile.common.toMillis
import com.chs.your_body_profile.data.source.db.entity.BloodPressureInfoEntity
import com.chs.your_body_profile.data.source.db.entity.BloodSugarInfoEntity
import com.chs.your_body_profile.data.source.db.entity.DrinkInfoEntity
import com.chs.your_body_profile.data.source.db.entity.HemoglobinA1cInfoEntity
import com.chs.your_body_profile.data.source.db.entity.InsulinInfoEntity
import com.chs.your_body_profile.data.source.db.entity.MealInfoEntity
import com.chs.your_body_profile.data.source.db.entity.PayInfoEntity
import com.chs.your_body_profile.data.source.db.entity.WeightInfoEntity
import com.chs.your_body_profile.domain.model.BloodPressureInfo
import com.chs.your_body_profile.domain.model.BloodSugarInfo
import com.chs.your_body_profile.domain.model.DrinkInfo
import com.chs.your_body_profile.domain.model.DrinkType
import com.chs.your_body_profile.domain.model.HemoglobinA1cInfo
import com.chs.your_body_profile.domain.model.InsulinInfo
import com.chs.your_body_profile.domain.model.MealHistoryInfo
import com.chs.your_body_profile.domain.model.MealType
import com.chs.your_body_profile.domain.model.MeasureType
import com.chs.your_body_profile.domain.model.PaymentInfo
import com.chs.your_body_profile.domain.model.WeightInfo
import java.time.LocalDateTime

fun BloodPressureInfoEntity.toBloodPressureInfo(): BloodPressureInfo {
    return BloodPressureInfo(
        measureDateTime = this.measureDateTime.toLocalDateTime(),
        systolic = this.systolic,
        diastolic = this.diastolic,
        memo = this.memo,
    )
}

fun BloodPressureInfo.toBloodPressureInfoEntity(): BloodPressureInfoEntity {
    return BloodPressureInfoEntity(
        measureDateTime = this.measureDateTime.toMillis(),
        systolic = this.systolic,
        diastolic = this.diastolic,
        memo = this.memo,
    )
}

fun BloodSugarInfoEntity.toBloodSugarInfo(mealList: List<MealInfoEntity>): BloodSugarInfo {
    return BloodSugarInfo(
        measureDateTime = this.measureDateTime.toLocalDateTime(),
        measureTypeIdx = MeasureType.entries[this.measureType],
        number = this.number,
        memo = this.memo,
        mealInfo = mealList.map { it.toMealHistoryInfo() }
    )
}

fun BloodSugarInfo.toBloodSugarInfoEntity(): BloodSugarInfoEntity {
    return BloodSugarInfoEntity(
        measureDateTime = this.measureDateTime.toMillis(),
        measureType = this.measureTypeIdx.mean.first,
        number = this.number,
        memo = this.memo
    )
}

fun MealInfoEntity.toMealHistoryInfo(): MealHistoryInfo {
    return MealHistoryInfo(
        measureTime = this.bloodSugarMeasureTime.toLocalDateTime(),
        mealName = this.mealName,
        mealType = MealType.entries[this.mealType]
    )
}

fun MealHistoryInfo.toMealInfoEntity(): MealInfoEntity {
    return MealInfoEntity(
        bloodSugarMeasureTime = measureTime.toMillis(),
        mealName = this.mealName,
        mealType = this.mealType.mean.first
    )
}

fun DrinkInfoEntity.toDrinkInfo(): DrinkInfo {
    return DrinkInfo(
        takenDateTime = this.takenDateTime.toLocalDateTime(),
        drinkType = DrinkType.entries.find { it.name == this.drinkType }!!
    )
}

fun DrinkInfo.toDrinkInfoEntity(): DrinkInfoEntity {
    return DrinkInfoEntity(
        takenDateTime = this.takenDateTime.toMillis(),
        drinkType = this.drinkType.name
    )
}

fun HemoglobinA1cInfoEntity.toHemoglobinA1cInfo(): HemoglobinA1cInfo {
    return HemoglobinA1cInfo(
        measureDate = this.measureDateTime.toLocalDateTime(),
        number = this.number,
        measureHospital = this.measureHospital,
        memo = this.memo
    )
}

fun HemoglobinA1cInfo.toHemoglobinA1cInfoEntity(): HemoglobinA1cInfoEntity {
    return HemoglobinA1cInfoEntity(
        measureDateTime = System.currentTimeMillis().toLocalDateToMillis(),
        number = this.number,
        measureHospital = this.measureHospital,
        memo = this.memo
    )
}

fun InsulinInfoEntity.toInsulinInfo(): InsulinInfo {
    return InsulinInfo(
        injectDateTime = this.injectDateTime.toLocalDateTime(),
        level = this.level,
        memo = this.memo
    )
}

fun InsulinInfo.toInsulinInfoEntity(): InsulinInfoEntity {
    return InsulinInfoEntity(
        injectDateTime = this.injectDateTime.toMillis(),
        level = this.level,
        memo = this.memo
    )
}

fun WeightInfoEntity.toWeightInfo(): WeightInfo {
    return WeightInfo(
        measureDateTime = this.measureDateTime.toLocalDateTime(),
        weight = this.weight,
        memo = this.memo
    )
}

fun WeightInfo.toWeightInfoEntity(): WeightInfoEntity {
    return WeightInfoEntity(
        measureDateTime = this.measureDateTime.toMillis(),
        weight = this.weight,
        memo = this.memo,
    )
}

fun PaymentInfo.toPayInfoEntity(): PayInfoEntity {
    return PayInfoEntity(
        paymentTime = this.paymentTime.toMillis(),
        title = this.title,
        subTitle = this.subTitle,
        amount = this.amount,
        memo = memo
    )
}

fun PayInfoEntity.toPaymentInfo(): PaymentInfo {
    return PaymentInfo(
        paymentTime = this.paymentTime.toLocalDateTime(),
        title = this.title,
        subTitle = this.subTitle,
        amount = this.amount,
        memo = memo
    )
}