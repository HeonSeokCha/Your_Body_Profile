package com.chs.your_body_profile.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.chs.your_body_profile.presentation.screen.bills.input.PayInputScreenRoot
import com.chs.your_body_profile.presentation.screen.bills.input.PayInputViewModel
import com.chs.your_body_profile.presentation.screen.bills.list.PayInfoListScreenRoot
import com.chs.your_body_profile.presentation.screen.bills.list.PayListViewModel
import com.chs.your_body_profile.presentation.screen.blood_pressure.input.BloodPressureInputScreenRoot
import com.chs.your_body_profile.presentation.screen.blood_pressure.input.BloodPressureInputViewModel
import com.chs.your_body_profile.presentation.screen.blood_pressure.list.BloodPressureListScreenRoot
import com.chs.your_body_profile.presentation.screen.blood_pressure.list.BloodPressureListViewModel
import com.chs.your_body_profile.presentation.screen.blood_sugar.input.BloodSugarInputScreenRoot
import com.chs.your_body_profile.presentation.screen.blood_sugar.input.BloodSugarInputViewModel
import com.chs.your_body_profile.presentation.screen.blood_sugar.list.BloodSugarListScreenRoot
import com.chs.your_body_profile.presentation.screen.blood_sugar.list.BloodSugarListViewModel
import com.chs.your_body_profile.presentation.screen.drink.DrinkListScreenRoot
import com.chs.your_body_profile.presentation.screen.drink.DrinkListViewModel
import com.chs.your_body_profile.presentation.screen.hemoglobinA1c.input.HemoglobinA1cInputScreenRoot
import com.chs.your_body_profile.presentation.screen.hemoglobinA1c.input.HemoglobinA1cInputViewModel
import com.chs.your_body_profile.presentation.screen.hemoglobinA1c.list.HemoglobinA1cListScreenRoot
import com.chs.your_body_profile.presentation.screen.hemoglobinA1c.list.HemoglobinA1cListViewModel
import com.chs.your_body_profile.presentation.screen.home.HomeScreenRoot
import com.chs.your_body_profile.presentation.screen.home.HomeViewModel
import com.chs.your_body_profile.presentation.screen.insulin.input.InsulinInputScreenRoot
import com.chs.your_body_profile.presentation.screen.insulin.input.InsulinInputViewModel
import com.chs.your_body_profile.presentation.screen.insulin.list.InsulinListScreenRoot
import com.chs.your_body_profile.presentation.screen.insulin.list.InsulinListViewModel
import com.chs.your_body_profile.presentation.screen.weight.input.WeightInputScreenRoot
import com.chs.your_body_profile.presentation.screen.weight.input.WeightInputViewModel
import com.chs.your_body_profile.presentation.screen.weight.list.WeightListScreenRoot
import com.chs.your_body_profile.presentation.screen.weight.list.WeightListViewModel

@Composable
fun MainNavHost(
    navController: NavHostController,
    paddingValue: PaddingValues,
) {
    NavHost(
        modifier = Modifier
            .padding(paddingValue),
        navController = navController,
        startDestination = Screens.Home,
    ) {
        composable<Screens.Home> {
            val viewModel: HomeViewModel = hiltViewModel()
            HomeScreenRoot(viewModel) {
                navController.navigate(it)
            }
        }

        composable<Screens.DrinkList> {
            val viewModel: DrinkListViewModel = hiltViewModel()
            DrinkListScreenRoot(viewModel) {
                navController.popBackStack()
            }
        }

        composable<Screens.BloodSugarInput> {
            val parentEntry = remember(it) {
                navController.getBackStackEntry(it.toRoute<Screens.BloodSugarInput>())
            }
            val viewModel: BloodSugarInputViewModel = hiltViewModel(parentEntry)
            BloodSugarInputScreenRoot(
                viewModel = viewModel,
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable<Screens.BloodPressureInput> {
            val parentEntry = remember(it) {
                navController.getBackStackEntry(it.toRoute<Screens.BloodPressureInput>())
            }
            val viewModel: BloodPressureInputViewModel = hiltViewModel(parentEntry)
            BloodPressureInputScreenRoot(
                viewModel = viewModel,
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable<Screens.HemoglobinA1cInput> {
            val parentEntry = remember(it) {
                navController.getBackStackEntry(it.toRoute<Screens.HemoglobinA1cInput>())
            }
            val viewModel: HemoglobinA1cInputViewModel = hiltViewModel(parentEntry)
            HemoglobinA1cInputScreenRoot(
                viewModel = viewModel,
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable<Screens.InsulinInput> {
            val parentEntry = remember(it) {
                navController.getBackStackEntry(it.toRoute<Screens.InsulinInput>())
            }
            val viewModel: InsulinInputViewModel = hiltViewModel(parentEntry)
            InsulinInputScreenRoot(
                viewModel = viewModel,
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable<Screens.WeightInput> {
            val parentEntry = remember(it) {
                navController.getBackStackEntry(it.toRoute<Screens.WeightInput>())
            }
            val viewModel: WeightInputViewModel = hiltViewModel(parentEntry)
            WeightInputScreenRoot(
                viewModel = viewModel,
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable<Screens.PayInput> {
            val parentEntry = remember(it) {
                navController.getBackStackEntry(it.toRoute<Screens.PayInput>())
            }
            val viewModel: PayInputViewModel = hiltViewModel(parentEntry)
            PayInputScreenRoot(
                viewModel = viewModel,
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable<Screens.BloodPressureList> {
            val viewModel: BloodPressureListViewModel = hiltViewModel()
            BloodPressureListScreenRoot(
                viewModel = viewModel,
                onBack = {
                    navController.popBackStack()
                },
                onInput = {
                    navController.navigate(Screens.BloodPressureInput())
                }
            )
        }


        composable<Screens.BloodSugarList> {
            val viewModel: BloodSugarListViewModel = hiltViewModel()
            BloodSugarListScreenRoot(
                viewModel = viewModel,
                onBack = {
                    navController.popBackStack()
                },
                onInput = {
                    navController.navigate(Screens.BloodSugarInput())
                }
            )
        }

        composable<Screens.HemoglobinA1cList> {
            val viewModel: HemoglobinA1cListViewModel = hiltViewModel()
            HemoglobinA1cListScreenRoot(
                viewModel = viewModel,
                onBack = {
                    navController.popBackStack()
                },
                onInput = {
                    navController.navigate(Screens.HemoglobinA1cInput())
                }
            )
        }

        composable<Screens.InsulinList> {
            val viewModel: InsulinListViewModel = hiltViewModel()
            InsulinListScreenRoot(
                viewModel = viewModel,
                onBack = {
                    navController.popBackStack()
                },
                onInput = {
                    navController.navigate(Screens.InsulinInput())
                }
            )
        }

        composable<Screens.WeightList> {
            val viewModel: WeightListViewModel = hiltViewModel()
            WeightListScreenRoot(
                viewModel = viewModel,
                onBack = {
                    navController.popBackStack()
                },
                onInput = {
                    navController.navigate(Screens.WeightInput())
                }
            )
        }

        composable<Screens.PayList> {
            val viewModel: PayListViewModel = hiltViewModel()
            PayInfoListScreenRoot(
                viewModel = viewModel,
                onBack = {
                    navController.popBackStack()
                },
                onInput = {
                    navController.navigate(Screens.PayInput())
                }
            )
        }
    }
}