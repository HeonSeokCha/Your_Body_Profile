package com.chs.your_body_profile.presentation

sealed class Screens(
    val route: String
) {
    data object ScreenBodyDash : Screens("body_dash_screen")
    data object ScreenBloodSugarInput : Screens("input_blood_sugar_screen")
    data object ScreenBloodSugarList : Screens("blood_sugar_list_screen")
    data object ScreenBloodPressureInput : Screens("input_blood_pressure_screen")
    data object ScreenBloodPressureList : Screens("blood_sugar_list_screen")
    data object ScreenInsulinInput : Screens("input_insulin_screen")
    data object ScreenInsulinList : Screens("insulin_list_screen")
    data object ScreenHemoglobinA1cInput : Screens("input_hemoglobinA1c_screen")
    data object ScreenMedicineInput : Screens("input_medicine_screen")
    data object ScreenFoodSearch : Screens("search_food_screen")
    data object ScreenFoodDetail : Screens("food_detail_Screen")
    data object ScreenMealInput : Screens("meal_input_screen")
    data object ScreenMealList : Screens("meal_list_screen")
}
