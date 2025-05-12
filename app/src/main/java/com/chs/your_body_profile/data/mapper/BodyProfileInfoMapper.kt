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
import com.chs.your_body_profile.data.source.db.entity.MedicineInfoEntity
import com.chs.your_body_profile.data.source.db.entity.WeightInfoEntity
import com.chs.your_body_profile.domain.model.BloodPressureInfo
import com.chs.your_body_profile.domain.model.BloodSugarInfo
import com.chs.your_body_profile.domain.model.DrinkCoffeeInfo
import com.chs.your_body_profile.domain.model.DrinkInfo
import com.chs.your_body_profile.domain.model.DrinkWaterInfo
import com.chs.your_body_profile.domain.model.HemoglobinA1cInfo
import com.chs.your_body_profile.domain.model.InsulinInfo
import com.chs.your_body_profile.domain.model.MeasureType
import com.chs.your_body_profile.domain.model.MedicineInfo
import com.chs.your_body_profile.domain.model.MedicineType
import com.chs.your_body_profile.domain.model.WeightInfo

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

fun BloodSugarInfoEntity.toBloodSugarInfo(): BloodSugarInfo {
    return BloodSugarInfo(
        measureDateTime = this.measureDateTime.toLocalDateTime(),
        measureTypeIdx = this.measureType,
        number = this.number,
        memo = this.memo
    )
}

fun BloodSugarInfo.toBloodSugarInfoEntity(): BloodSugarInfoEntity {
    return BloodSugarInfoEntity(
        measureDateTime = this.measureDateTime.toMillis(),
        measureType = this.measureTypeIdx,
        number = this.number,
        memo = this.memo
    )
}

fun DrinkInfoEntity.toDrinkWaterInfo(): DrinkWaterInfo {
    return DrinkWaterInfo(
        takenDateTime = takenDate.toLocalDate(),
        totalCups = this.totalCups
    )
}

fun DrinkInfoEntity.toDrinkCoffeeInfo(): DrinkCoffeeInfo {
    return DrinkCoffeeInfo(
        takenDateTime = takenDate.toLocalDate(),
        totalCups = this.totalCups
    )
}

fun DrinkInfo.toDrinkInfoEntity(type: String): DrinkInfoEntity {
    return DrinkInfoEntity(
        takenDate = this.takenDateTime.toMillis(),
        drinkType = type,
        totalCups = this.totalCups
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

fun MedicineInfoEntity.toMedicineInfo(): MedicineInfo {
    return MedicineInfo(
        takenDateTime = this.takenDateTime.toLocalDateTime(),
        medicineType = MedicineType.entries.find { it.time.first == this.takeMedicineType } ?: MedicineType.UNKNOWN,
        title = this.title,
        memo = this.memo
    )
}

fun MedicineInfo.toMedicineInfoEntity(): MedicineInfoEntity {
    return MedicineInfoEntity(
        takenDateTime = this.takenDateTime.toMillis(),
        takeMedicineType = this.medicineType.time.first,
        title = this.title,
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
