package com.chs.your_body_profile.data.mapper

import com.chs.your_body_profile.data.model.entity.BloodPressureInfoEntity
import com.chs.your_body_profile.data.model.entity.BloodSugarInfoEntity
import com.chs.your_body_profile.data.model.entity.DrinkInfoEntity
import com.chs.your_body_profile.data.model.entity.HemoglobinA1cInfoEntity
import com.chs.your_body_profile.data.model.entity.InsulinInfoEntity
import com.chs.your_body_profile.data.model.entity.MedicineInfoEntity
import com.chs.your_body_profile.domain.model.BloodPressureInfo
import com.chs.your_body_profile.domain.model.BloodSugarInfo
import com.chs.your_body_profile.domain.model.DrinkCoffeeInfo
import com.chs.your_body_profile.domain.model.DrinkType
import com.chs.your_body_profile.domain.model.DrinkWaterInfo
import com.chs.your_body_profile.domain.model.HemoglobinA1cInfo
import com.chs.your_body_profile.domain.model.InsulinInfo
import com.chs.your_body_profile.domain.model.MeasureType
import com.chs.your_body_profile.domain.model.MedicineInfo
import com.chs.your_body_profile.domain.model.MedicineType

fun BloodPressureInfoEntity.toBloodPressureInfo(): BloodPressureInfo {
    return BloodPressureInfo(
        measureTime = this.measureTime,
        systolic = this.systolic,
        diastolic = this.diastolic,
        useMedication = this.useMedication,
        memo = this.memo,
    )
}

fun BloodPressureInfo.toBloodPressureInfoEntity(): BloodPressureInfoEntity {
    return BloodPressureInfoEntity(
        measureTime = this.measureTime,
        systolic = this.systolic,
        diastolic = this.diastolic,
        useMedication = this.useMedication,
        memo = this.memo,
        lastModifyTime = System.currentTimeMillis()
    )
}

fun BloodSugarInfoEntity.toBloodSugarInfo(): BloodSugarInfo {
    return BloodSugarInfo(
        measureTime = this.measureTime,
        measureType = MeasureType.valueOf(this.measureType),
        number = this.number,
        memo = this.memo
    )
}

fun BloodSugarInfo.toBloodSugarInfoEntity(): BloodSugarInfoEntity {
    return BloodSugarInfoEntity(
        measureTime = this.measureTime,
        measureType = this.measureType.mean.first,
        number = this.number,
        memo = this.memo,
        lastModifyTime = System.currentTimeMillis()
    )
}

fun DrinkInfoEntity.toDrinkWaterInfo(): DrinkWaterInfo {
    return DrinkWaterInfo(totalCups = this.totalCups)
}

fun DrinkInfoEntity.toDrinkCoffeeInfo(): DrinkCoffeeInfo {
    return DrinkCoffeeInfo(totalCups = this.totalCups)
}

fun DrinkType.toDrinkInfoEntity(drinkType: DrinkType): DrinkInfoEntity {
    return DrinkInfoEntity(
        drinkType = when (drinkType) {
            is DrinkWaterInfo -> "water"
            is DrinkCoffeeInfo -> "coffee"
        },
        totalCups = drinkType.totalCups(),
        lastModified = System.currentTimeMillis()
    )
}

fun HemoglobinA1cInfoEntity.toHemoglobinA1cInfo(): HemoglobinA1cInfo {
    return HemoglobinA1cInfo(
        number = this.number,
        measureHospital = this.measureHospital,
        memo = this.memo
    )
}

fun HemoglobinA1cInfo.toHemoglobinA1cInfoEntity(): HemoglobinA1cInfoEntity {
    return HemoglobinA1cInfoEntity(
        number = this.number,
        measureHospital = this.measureHospital,
        memo = this.memo,
        lastModifyTime = System.currentTimeMillis()
    )
}

fun InsulinInfoEntity.toInsulinInfo(): InsulinInfo {
    return InsulinInfo(
        level = this.level,
        memo = this.memo
    )
}

fun InsulinInfo.toInsulinInfoEntity(): InsulinInfoEntity {
    return InsulinInfoEntity(
        level = this.level,
        memo = this.memo,
        lastModifyTime = System.currentTimeMillis()
    )
}

fun MedicineInfoEntity.toMedicineInfo(): MedicineInfo {
    return MedicineInfo(
        medicineType = MedicineType.valueOf(this.medicineType),
        title = this.title,
        memo = this.memo
    )
}

fun MedicineInfo.toMedicineInfoEntity(): MedicineInfoEntity {
    return MedicineInfoEntity(
        medicineType = this.medicineType.time.first,
        title = this.title,
        memo = this.memo,
        lastModifyTime = System.currentTimeMillis()
    )
}