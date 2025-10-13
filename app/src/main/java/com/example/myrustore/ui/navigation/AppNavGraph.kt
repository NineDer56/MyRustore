package com.example.myrustore.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph(
    modifier : Modifier,
    navController : NavHostController,
    appsFeed : @Composable () -> Unit,
    appCard : @Composable () -> Unit
){

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screens.ROUTE_APPS_FEED
    ) {

        composable(Screens.ROUTE_APPS_FEED){
            appsFeed()
        }

        composable(Screens.ROUTE_APP_CARD){
            appCard()
        }

    }
}