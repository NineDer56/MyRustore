package com.example.myrustore.presentation.screens

import android.util.Log
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myrustore.presentation.AppState
import com.example.myrustore.presentation.AppViewModel
import com.example.myrustore.presentation.navigation.AppNavGraph
import com.example.myrustore.presentation.navigation.Screens
import com.example.myrustore.presentation.navigation.rememberNavigationState
import com.example.myrustore.presentation.screens.appDetails.AppDetailsScreen
import com.example.myrustore.presentation.screens.appList.AppListContent
import com.example.myrustore.presentation.screens.appList.AppListScreen
import com.example.myrustore.presentation.theme.MyRustoreTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    val navigationState = rememberNavigationState()
    val navBackStackEntry = navigationState.navHostController.currentBackStackEntryAsState()
    val destination = navBackStackEntry.value?.destination?.route
    Log.d("MainScreen", "destination: " + destination.toString())

    val appViewModel: AppViewModel = hiltViewModel()
    val appState = appViewModel.appState.collectAsStateWithLifecycle()

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier.then(
            when (destination) {
                Screens.AppsFeed.route -> {
                    Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
                }

                Screens.AppCard.route -> {
                    Modifier
                }

                else -> {
                    Modifier
                }
            }
        ),

        topBar = {
            when (destination) {
                Screens.AppsFeed.route -> {
                    TopAppBar(
                        title = {
                            Text(
                                text = "MyRuStore",
                            )
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            titleContentColor = MaterialTheme.colorScheme.onPrimary,
                            scrolledContainerColor = MaterialTheme.colorScheme.primary
                        ),
                        modifier = Modifier
                            .clip(
                                RoundedCornerShape(
                                    bottomStart = 14.dp, bottomEnd = 14.dp
                                )
                            ),
                        scrollBehavior = scrollBehavior
                    )
                }

                Screens.AppCard.route -> {
                    TopAppBar(
                        navigationIcon = {
                            IconButton(
                                onClick = {
                                    navigationState.navHostController.popBackStack()
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        },
                        title = {},
                        actions = {
                            IconButton(
                                onClick = {}
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Share,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    )
                }

                else -> {

                }
            }
        }
    ) { paddingValues ->

        when (val currentState = appState.value) {
            is AppState.Apps -> {
                AppNavGraph(
                    modifier = Modifier
                        .padding(paddingValues)
                        .consumeWindowInsets(paddingValues),
                    navController = navigationState.navHostController,

                    appsFeed = {
                        AppListScreen(paddingValues)
//                        AppListContent(
//                            apps = apps,
//                            onAppClick = {
//                                navigationState.navigateToAppCardById(it)
//                            })
                    },
                    appCard = { appId ->
                        AppDetailsScreen(paddingValues)
                    }
                )
            }

            AppState.Initial -> {

            }

            AppState.Error -> {

            }

            AppState.Loading -> {

            }
        }

    }
}


@Preview
@Composable
fun PreviewMainScreen() {
    MyRustoreTheme(
        darkTheme = false
    ) {
        MainScreen()
    }
}