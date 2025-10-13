package com.example.myrustore.ui.navigation

sealed class Screens(
    val route : String
) {
    data object AppsFeed : Screens(ROUTE_APPS_FEED)

    data object AppCard : Screens(ROUTE_APP_CARD) {
        private const val ROUTE_FOR_ARGS = "route_app_card"

        fun getRoute(appId : Int) : String{
            return "${ROUTE_FOR_ARGS}/$appId"
        }
    }


    companion object {
        const val KEY_APP_CARD_ID = "key_app_card_id"

        const val ROUTE_APPS_FEED = "route_apps_feed"
        const val ROUTE_APP_CARD = "route_app_card/{$KEY_APP_CARD_ID}"
    }
}