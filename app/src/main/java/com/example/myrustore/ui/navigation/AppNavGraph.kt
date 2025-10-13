package com.example.myrustore.ui.navigation

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
    appCard: @Composable (appId : Int) -> Unit
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
                    type = NavType.IntType
                }
            )
        ) {
            val appId = it.arguments?.getInt(Screens.KEY_APP_CARD_ID)
                ?: throw RuntimeException("No id with key Screens.KEY_APP_CARD_ID")
            appCard(appId)
        }

    }
}