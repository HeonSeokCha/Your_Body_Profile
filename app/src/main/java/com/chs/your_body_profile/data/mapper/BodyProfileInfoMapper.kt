package com.chs.your_body_profile.data.mapper

import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.common.toLocalDate
import com.chs.your_body_profile.common.toLocalDateTime
import com.chs.your_body_profile.common.toLocalDateToMillis
import com.chs.your_body_profile.common.toMillis
import com.chs.your_body_profile.data.model.entity.BloodPressureInfoEntity
import com.chs.your_body_profile.data.model.entity.BloodSugarInfoEntity
import com.chs.your_body_profile.data.model.entity.DrinkInfoEntity
import com.chs.your_body_profile.data.model.entity.HemoglobinA1cInfoEntity
import com.chs.your_body_profile.data.model.entity.InsulinInfoEntity
import com.chs.your_body_profile.data.model.entity.MedicineInfoEntity
import com.chs.your_body_profile.data.model.entity.WeightInfoEntity
import com.chs.your_body_profile.domain.model.BloodPressureInfo
import com.chs.your_body_profile.domain.model.BloodSugarInfo
import com.chs.your_body_profile.domain.model.DrinkType
import com.chs.your_body_profile.domain.model.HemoglobinA1cInfo
import com.chs.your_body_profile.domain.model.InsulinInfo
import com.chs.your_body_profile.domain.model.MeasureType
import com.chs.your_body_profile.domain.model.MedicineInfo
import com.chs.your_body_profile.domain.model.MedicineType
import com.chs.your_body_profile.domain.model.WeightInfo

fun BloodPressureInfoEntity.toBloodPressureInfo(): BloodPressureInfo {
    return BloodPressureInfo(
        measureDate = this.measureDate.toLocalDate(),
        measureTime = this.measureTime.toLocalDateTime(),
        systolic = this.systolic,
        diastolic = this.diastolic,
        memo = this.memo,
    )
}

fun BloodPressureInfo.toBloodPressureInfoEntity(): BloodPressureInfoEntity {
    return BloodPressureInfoEntity(
        measureDate = this.measureDate.toMillis(),
        measureTime = this.measureTime.toMillis(),
        systolic = this.systolic,
        diastolic = this.diastolic,
        memo = this.memo,
    )
}

fun BloodSugarInfoEntity.toBloodSugarInfo(): BloodSugarInfo {
    return BloodSugarInfo(
        measureDate = this.measureDate.toLocalDate(),
        measureTime = this.measureTime.toLocalDateTime(),
        measureType = MeasureType.values().find { it.mean.first == this.measureType } ?: MeasureType.EMPTY,
        number = this.number,
        memo = this.memo
    )
}

fun BloodSugarInfo.toBloodSugarInfoEntity(): BloodSugarInfoEntity {
    return BloodSugarInfoEntity(
        measureDate = this.measureDate.toMillis(),
        measureTime = this.measureTime.toMillis(),
        measureType = this.measureType.mean.first,
        number = this.number,
        memo = this.memo
    )
}

fun DrinkInfoEntity.toDrinkWaterInfo(): DrinkType.DrinkWaterInfo {
    return DrinkType.DrinkWaterInfo(
        takenDate = takenDate.toLocalDate(),
        totalCups = this.totalCups
    )
}

fun DrinkInfoEntity.toDrinkCoffeeInfo(): DrinkType.DrinkCoffeeInfo {
    return DrinkType.DrinkCoffeeInfo(
        takenDate = takenDate.toLocalDate(),
        totalCups = this.totalCups
    )
}

fun DrinkType.toDrinkInfoEntity(drinkType: DrinkType): DrinkInfoEntity {
    return DrinkInfoEntity(
        takenDate = this.takenDate.toMillis(),
        drinkType = when (drinkType) {
            is DrinkType.DrinkWaterInfo -> Constants.DRINK_TYPE_WATER
            is DrinkType.DrinkCoffeeInfo -> Constants.DRINK_TYPE_COFFEE
        },
        totalCups = this.totalCups
    )
}

fun HemoglobinA1cInfoEntity.toHemoglobinA1cInfo(): HemoglobinA1cInfo {
    return HemoglobinA1cInfo(
        measureDate = this.measureDate.toLocalDate(),
        number = this.number,
        measureHospital = this.measureHospital,
        memo = this.memo
    )
}

fun HemoglobinA1cInfo.toHemoglobinA1cInfoEntity(): HemoglobinA1cInfoEntity {
    return HemoglobinA1cInfoEntity(
        measureDate = System.currentTimeMillis().toLocalDateToMillis(),
        number = this.number,
        measureHospital = this.measureHospital,
        memo = this.memo
    )
}

fun InsulinInfoEntity.toInsulinInfo(): InsulinInfo {
    return InsulinInfo(
        injectDate = this.injectDate.toLocalDate(),
        level = this.level,
        memo = this.memo
    )
}

fun InsulinInfo.toInsulinInfoEntity(): InsulinInfoEntity {
    return InsulinInfoEntity(
        injectDate = this.injectDate.toMillis(),
        level = this.level,
        memo = this.memo
    )
}

fun MedicineInfoEntity.toMedicineInfo(): MedicineInfo {
    return MedicineInfo(
        takenDate = this.takenDate.toLocalDate(),
        takenTime = this.takenTime.toLocalDateTime(),
        medicineType = MedicineType.values().find { it.time.first == this.takeMedicineType } ?: MedicineType.UNKNOWN,
        title = this.title,
        memo = this.memo
    )
}

fun MedicineInfo.toMedicineInfoEntity(): MedicineInfoEntity {
    return MedicineInfoEntity(
        takenDate = this.takenDate.toMillis(),
        takenTime = this.takenTime.toMillis(),
        takeMedicineType = this.medicineType.time.first,
        title = this.title,
        memo = this.memo
    )
}

fun WeightInfoEntity.toWeightInfo(): WeightInfo {
    return WeightInfo(
        measureDate = this.measureDate.toLocalDate(),
        measureTime = this.measureTime.toLocalDateTime(),
        weight = this.weight,
        memo = this.memo
    )
}

fun WeightInfo.toWeightInfoEntity(): WeightInfoEntity {
    return WeightInfoEntity(
        measureDate = this.measureDate.toMillis(),
        measureTime = this.measureTime.toMillis(),
        weight = this.weight,
        memo = this.memo,
    )
}
