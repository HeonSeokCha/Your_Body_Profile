package com.chs.your_body_profile.presentation

sealed class Screens(
    val route: String
) {
    data object BodyDashScreen : Screens("body_dash_screen")
    data object InputBloodSugarScreen : Screens("input_blood_sugar_screen")
    data object InputBloodPressureScreen : Screens("input_blood_pressure_screen")
    data object InputInsulinScreen : Screens("input_insulin_screen")
    data object InputHemoglobinA1cScreen : Screens("input_hemoglobinA1c_screen")
    data object InputMedicineScreen : Screens("input_medicine_screen")
    data object InputFoodScreen : Screens("input_food_screen")
    data object SearchFoodScreen : Screens("search_food_screen")
}
