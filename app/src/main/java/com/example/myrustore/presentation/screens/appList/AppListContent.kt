package com.example.myrustore.presentation.screens.appList

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.myrustore.domain.AppItem
import com.example.myrustore.presentation.theme.MyRustoreTheme

@Composable
fun AppListContent(
    apps : List<AppItem>,
    onAppClick : (id : String) -> Unit,
    modifier: Modifier = Modifier
) {
   LazyColumn(
       verticalArrangement = Arrangement.spacedBy(8.dp),
       modifier = modifier
   ) {
       items(
           items =  apps,
           key = {it.id}
       ) {
            ApplicationItem(it, onAppClick)

       }
   }
}


@Composable
fun ApplicationItem(
    appItem : AppItem,
    onAppClick : (id : String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                onClick = {
                    onAppClick(appItem.id)
                }
            )
        ,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(appItem.iconUrl)
                .crossfade(true)
                .listener(
                    onError = { req, result ->
                        Log.e(
                            "AsyncImage",
                            "Load error for: ${req.data} -> ${result.throwable}"
                        )
                    }
                )
                .build(),
            contentDescription = null,
            modifier = Modifier
                .clip(RoundedCornerShape(18.dp))
                .size(90.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column{
            Text(
                text = appItem.name,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 16.sp
            )
            Text(
                text = appItem.description,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 12.sp,
                lineHeight = 14.sp
            )
            Text(
                text = appItem.category.name,
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 12.sp
            )
        }
    }
}


@Preview
@Composable
fun PreviewAppsList() {
    MyRustoreTheme(
        darkTheme = true
    ) {

    }
}