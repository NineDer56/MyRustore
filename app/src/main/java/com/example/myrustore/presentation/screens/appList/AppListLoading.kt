package com.example.myrustore.presentation.screens.appList

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myrustore.presentation.theme.MyRustoreTheme

@Composable
fun AppListLoading(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.windowInsetsPadding(WindowInsets.safeDrawing),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}


@Preview
@Composable
fun AppListLoadingPreview(){
    MyRustoreTheme(
        darkTheme = true
    ) {
        AppListLoading(
            modifier = Modifier.fillMaxSize()
        )
    }
}