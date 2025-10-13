package com.example.myrustore.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.myrustore.ui.AppItem
import com.example.myrustore.ui.navigation.AppNavGraph
import com.example.myrustore.ui.navigation.Screens
import com.example.myrustore.ui.navigation.rememberNavigationState
import com.example.myrustore.ui.theme.MyRustoreTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    val navigationState = rememberNavigationState()

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
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
    ) { paddingValues ->

        val listOfItems: List<AppItem> = mutableListOf<AppItem>().apply {
            repeat(15) {
                this.add(it, AppItem(it))
            }
        }

        AppNavGraph(
            modifier = Modifier
                .padding(paddingValues)
                .consumeWindowInsets(paddingValues),
            navController = navigationState.navHostController,
            appsFeed = {
                AppsList(
                    apps = listOfItems,
                    onAppClick = {
                        navigationState.navigateTo(Screens.ROUTE_APP_CARD)
                    })
            },
            appCard = {
                AppCard(listOfItems[0])
            }
        )
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