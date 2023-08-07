package com.chs.your_body_profile.data.mapper

import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.common.toLocalDate
import com.chs.your_body_profile.common.toLocalDateTime
import com.chs.your_body_profile.common.toLocalDateToMillis
import com.chs.your_body_profile.common.toMillis
import com.chs.your_body_profile.data.model.entity.BloodPressureInfoEntity
import com.chs.your_body_profile.data.model.entity.BloodSugarInfoEntity
import com.chs.your_body_profile.data.model.entity.BodySummaryInfoEntity
import com.chs.your_body_profile.data.model.entity.DrinkInfoEntity
import com.chs.your_body_profile.data.model.entity.HemoglobinA1cInfoEntity
import com.chs.your_body_profile.data.model.entity.InsulinInfoEntity
import com.chs.your_body_profile.data.model.entity.MedicineInfoEntity
import com.chs.your_body_profile.data.model.entity.WeightInfoEntity
import com.chs.your_body_profile.domain.model.BloodPressureInfo
import com.chs.your_body_profile.domain.model.BloodSugarInfo
import com.chs.your_body_profile.domain.model.BodySummaryInfo
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
        measureTime = this.measureTime.toLocalDateTime(),
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

fun BloodPressureInfoEntity?.toBodySummaryInfoEntity(): BodySummaryInfoEntity {
    return BodySummaryInfoEntity(
        insertDate = this?.insertDate ?: System.currentTimeMillis().toLocalDateToMillis(),
        type = Constants.BODY_SUMMARY_TYPE_BLOOD_PRESSURE,
        todayLastInfo = "${this?.systolic ?: "--"}/${this?.diastolic ?: "--"}",
        measureUnit = "",
        lastModifyTime = this?.measureTime ?: System.currentTimeMillis()
    )
}

fun BloodSugarInfoEntity.toBloodSugarInfo(): BloodSugarInfo {
    return BloodSugarInfo(
        measureTime = this.measureTime.toLocalDateTime(),
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


fun BloodSugarInfoEntity?.toBodySummaryInfoEntity(): BodySummaryInfoEntity {
    return BodySummaryInfoEntity(
        insertDate = this?.insertDate ?: System.currentTimeMillis().toLocalDateToMillis(),
        type = Constants.BODY_SUMMARY_TYPE_BLOOD_PRESSURE,
        todayLastInfo = "${this?.number ?: 0}",
        measureUnit = "",
        lastModifyTime = this?.measureTime ?: System.currentTimeMillis()
    )
}

fun DrinkInfoEntity.toDrinkWaterInfo(): DrinkWaterInfo {
    return DrinkWaterInfo(totalCups = this.totalCups)
}

fun DrinkInfoEntity.toDrinkCoffeeInfo(): DrinkCoffeeInfo {
    return DrinkCoffeeInfo(totalCups = this.totalCups)
}

fun DrinkInfoEntity?.toBodySummaryInfoEntity(drinkType: DrinkType): BodySummaryInfoEntity {
    return BodySummaryInfoEntity(
        insertDate = this?.insertDate ?: System.currentTimeMillis().toLocalDateToMillis(),
        type = if (this?.drinkType != null) {
            this.drinkType
        } else {
            when (drinkType) {
                is DrinkWaterInfo -> Constants.BODY_SUMMARY_TYPE_WATER
                is DrinkCoffeeInfo -> Constants.BODY_SUMMARY_TYPE_COFFEE
            }
        },
        todayLastInfo = "${this?.totalCups ?: 0}",
        measureUnit = "",
        lastModifyTime = this?.lastModified ?: System.currentTimeMillis().toLocalDateToMillis()
    )
}

fun DrinkType.toDrinkInfoEntity(drinkType: DrinkType): DrinkInfoEntity {
    return DrinkInfoEntity(
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
        number = this.number,
        measureHospital = this.measureHospital,
        memo = this.memo
    )
}

fun HemoglobinA1cInfoEntity?.toBodySummaryInfoEntity(): BodySummaryInfoEntity {
    return BodySummaryInfoEntity(
        insertDate = this?.insertDate ?: System.currentTimeMillis().toLocalDateToMillis(),
        type = Constants.BODY_SUMMARY_TYPE_HEMOGLOBIN_A1C,
        todayLastInfo = "${this?.number ?: 0.0}",
        measureUnit = "",
        lastModifyTime = this?.lastModifyTime ?: System.currentTimeMillis()
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
        level = this.level,
        memo = this.memo
    )
}

fun InsulinInfoEntity?.toBodySummaryInfoEntity(): BodySummaryInfoEntity {
    return BodySummaryInfoEntity(
        insertDate = this?.insertDate ?: System.currentTimeMillis().toLocalDateToMillis(),
        type = Constants.BODY_SUMMARY_TYPE_INSULIN,
        todayLastInfo = "${this?.level ?: 0}",
        measureUnit = "",
        lastModifyTime = this?.lastModifyTime ?: System.currentTimeMillis()
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
        medicineType = MedicineType.valueOf(this.takeMedicineType),
        title = this.title,
        memo = this.memo
    )
}

fun MedicineInfoEntity?.toBodySummaryInfoEntity(): BodySummaryInfoEntity {
    return BodySummaryInfoEntity(
        insertDate = this?.insertDate ?: System.currentTimeMillis().toLocalDateToMillis(),
        type = Constants.BODY_SUMMARY_TYPE_MEDICINE,
        todayLastInfo = this?.takeMedicineType ?: MedicineType.UNKNOWN.time.first,
        measureUnit = "",
        lastModifyTime = this?.lastModifyTime ?: System.currentTimeMillis()
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

fun BodySummaryInfoEntity.toBodySummaryInfo(): BodySummaryInfo {
    return BodySummaryInfo(
        insertDate = this.insertDate.toLocalDate(),
        type = this.type,
        todayLastInfo = this.todayLastInfo,
        measureUnit = this.measureUnit
    )
}

fun BodySummaryInfo.toBodySummaryInfoEntity(): BodySummaryInfoEntity {
    return BodySummaryInfoEntity(
        insertDate = this.insertDate.toMillis(),
        type = this.type,
        todayLastInfo = this.todayLastInfo,
        measureUnit = this.measureUnit,
        lastModifyTime = System.currentTimeMillis()
    )
}

fun WeightInfoEntity.toWeightInfo(): WeightInfo {
    return WeightInfo(
        weight = this.weight,
        measureTime = this.measureTime.toLocalDateTime(),
        memo = this.memo
    )
}

fun WeightInfoEntity?.toBodySummaryInfoEntity(): BodySummaryInfoEntity {
    return BodySummaryInfoEntity(
        insertDate = this?.insertDate ?: System.currentTimeMillis().toLocalDateToMillis(),
        type = Constants.BODY_SUMMARY_TYPE_WEIGHT,
        todayLastInfo = "${this?.weight ?: 0.0}",
        measureUnit = "",
        lastModifyTime = this?.lastModifyTime ?: System.currentTimeMillis()
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