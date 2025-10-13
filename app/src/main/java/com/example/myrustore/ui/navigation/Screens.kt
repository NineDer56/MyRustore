package com.example.myrustore.ui.navigation

sealed class Screens(
    val route : String
) {
    data object AppsFeed : Screens(ROUTE_APPS_FEED)
    data object AppCard : Screens(ROUTE_APP_CARD)

    companion object {
        const val ROUTE_APPS_FEED = "route_apps_feed"
        const val ROUTE_APP_CARD = "route_app_card"
    }
}