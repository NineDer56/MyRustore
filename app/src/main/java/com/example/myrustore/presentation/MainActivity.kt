package com.example.myrustore.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myrustore.presentation.screens.MainScreen
import com.example.myrustore.presentation.theme.MyRustoreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyRustoreTheme {
                MainScreen()
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    AppCard(
//                        AppItem(0),
//                        paddingValues = PaddingValues(
//                            top = innerPadding.calculateTopPadding() + 16.dp,
//                            bottom = innerPadding.calculateBottomPadding() + 16.dp,
//                            start = innerPadding.calculateStartPadding(LayoutDirection.Ltr) + 12.dp,
//                            end = innerPadding.calculateEndPadding(LayoutDirection.Ltr) + 12.dp
//                        )
//                    )
//                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyRustoreTheme {
        Greeting("Android")
    }
}