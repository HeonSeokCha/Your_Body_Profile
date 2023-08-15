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
import com.chs.your_body_profile.domain.model.DrinkCoffeeInfo
import com.chs.your_body_profile.domain.model.DrinkType
import com.chs.your_body_profile.domain.model.DrinkWaterInfo
import com.chs.your_body_profile.domain.model.HemoglobinA1cInfo
import com.chs.your_body_profile.domain.model.InsulinInfo
import com.chs.your_body_profile.domain.model.MeasureType
import com.chs.your_body_profile.domain.model.MedicineInfo
import com.chs.your_body_profile.domain.model.MedicineType
import com.chs.your_body_profile.domain.model.WeightInfo

fun BloodPressureInfoEntity.toBloodPressureInfo(): BloodPressureInfo {
    return BloodPressureInfo(
        measureTime = this.measureTime.toLocalDate(),
        systolic = this.systolic,
        diastolic = this.diastolic,
        useMedication = this.useMedication,
        memo = this.memo,
    )
}

fun BloodPressureInfo.toBloodPressureInfoEntity(): BloodPressureInfoEntity {
    return BloodPressureInfoEntity(
        measureTime = this.measureTime.toMillis(),
        systolic = this.systolic,
        diastolic = this.diastolic,
        useMedication = this.useMedication,
        memo = this.memo,
        lastModifyTime = System.currentTimeMillis()
    )
}

fun BloodSugarInfoEntity.toBloodSugarInfo(): BloodSugarInfo {
    return BloodSugarInfo(
        measureTime = this.measureTime.toLocalDate(),
        measureType = MeasureType.valueOf(this.measureType),
        number = this.number,
        memo = this.memo
    )
}

fun BloodSugarInfo.toBloodSugarInfoEntity(): BloodSugarInfoEntity {
    return BloodSugarInfoEntity(
        measureTime = this.measureTime.toMillis(),
        measureType = this.measureType.mean.first,
        number = this.number,
        memo = this.memo,
        lastModifyTime = System.currentTimeMillis()
    )
}

fun DrinkInfoEntity.toDrinkWaterInfo(): DrinkWaterInfo {
    return DrinkWaterInfo(
        measureTime = this.insertDate.toLocalDate(),
        totalCups = this.totalCups
    )
}

fun DrinkInfoEntity.toDrinkCoffeeInfo(): DrinkCoffeeInfo {
    return DrinkCoffeeInfo(
        measureTime = this.insertDate.toLocalDate(),
        totalCups = this.totalCups
    )
}

fun DrinkType.toDrinkInfoEntity(drinkType: DrinkType): DrinkInfoEntity {
    return DrinkInfoEntity(
        insertDate = this.measureTime().toMillis(),
        drinkType = when (drinkType) {
            is DrinkWaterInfo -> Constants.DRINK_TYPE_WATER
            is DrinkCoffeeInfo -> Constants.DRINK_TYPE_COFFEE
        },
        totalCups = drinkType.totalCups(),
        lastModified = System.currentTimeMillis()
    )
}

fun HemoglobinA1cInfoEntity.toHemoglobinA1cInfo(): HemoglobinA1cInfo {
    return HemoglobinA1cInfo(
        measureTime = this.insertDate.toLocalDate(),
        number = this.number,
        measureHospital = this.measureHospital,
        memo = this.memo
    )
}

fun HemoglobinA1cInfo.toHemoglobinA1cInfoEntity(): HemoglobinA1cInfoEntity {
    return HemoglobinA1cInfoEntity(
        insertDate = System.currentTimeMillis().toLocalDateToMillis(),
        number = this.number,
        measureHospital = this.measureHospital,
        memo = this.memo,
        lastModifyTime = System.currentTimeMillis()
    )
}

fun InsulinInfoEntity.toInsulinInfo(): InsulinInfo {
    return InsulinInfo(
        measureTime = this.insertDate.toLocalDate(),
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
        measureTime = this.insertDate.toLocalDate(),
        medicineType = MedicineType.valueOf(this.takeMedicineType),
        title = this.title,
        memo = this.memo
    )
}

fun MedicineInfo.toMedicineInfoEntity(): MedicineInfoEntity {
    return MedicineInfoEntity(
        takeMedicineType = this.medicineType.time.first,
        title = this.title,
        memo = this.memo,
        lastModifyTime = System.currentTimeMillis()
    )
}

fun WeightInfoEntity.toWeightInfo(): WeightInfo {
    return WeightInfo(
        measureTime = this.insertDate.toLocalDate(),
        weight = this.weight,
        memo = this.memo
    )
}

fun WeightInfo.toWeightInfoEntity(): WeightInfoEntity {
    return WeightInfoEntity(
        weight = this.weight,
        measureTime = this.measureTime.toMillis(),
        memo = this.memo,
        lastModifyTime = System.currentTimeMillis()
    )
}