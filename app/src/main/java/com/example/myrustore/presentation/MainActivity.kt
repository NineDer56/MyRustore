package com.example.myrustore.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myrustore.presentation.screens.MainScreen
import com.example.myrustore.presentation.screens.appDetails.AppDetailsLoading
import com.example.myrustore.presentation.screens.appDetails.AppDetailsScreen
import com.example.myrustore.presentation.screens.appList.AppListScreen
import com.example.myrustore.presentation.theme.MyRustoreTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
                MyRustoreTheme {
                    MainScreen()
                }
        }
    }
}