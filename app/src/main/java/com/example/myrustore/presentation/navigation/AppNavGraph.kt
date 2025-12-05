package com.example.myrustore.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun AppNavGraph(
    modifier: Modifier,
    navController: NavHostController,
    appsFeed: @Composable () -> Unit,
    appCard: @Composable (id : String) -> Unit
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screens.AppsFeed.route
    ) {

        composable(
            route = Screens.AppsFeed.route
        ) {
            appsFeed()
        }

        composable(
            route = Screens.AppCard.route,
            arguments = listOf(
                navArgument(Screens.KEY_APP_CARD_ID) {
                    type = NavType.StringType
                }
            )
        ) {
            val appId = it.arguments?.getString(Screens.KEY_APP_CARD_ID)
                ?: throw RuntimeException("No id with key Screens.KEY_APP_CARD_ID")
            appCard(appId)
        }

    }
}