package com.example.myrustore.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myrustore.ui.AppItem
import com.example.myrustore.ui.theme.MyRustoreTheme

@Composable
fun AppsList(
    apps : List<AppItem>,
    contentPadding : PaddingValues = PaddingValues(),
    onAppClick : () -> Unit
) {
   LazyColumn(
       verticalArrangement = Arrangement.spacedBy(8.dp),
       contentPadding = contentPadding,
       modifier = Modifier.padding(horizontal = 8.dp)
   ) {
       items(
           items =  apps,
           key = {it.appId}
       ) {
            ApplicationItem(it, onAppClick)

       }
   }
}


@Composable
fun ApplicationItem(
    appItem : AppItem,
    onAppClick : () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onAppClick)
        ,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = appItem.appImage,
            contentDescription = null,
            modifier = Modifier
                .clip(RoundedCornerShape(18.dp))
                .background(MaterialTheme.colorScheme.primary)
                .size(90.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column{
            Text(
                text = appItem.appName,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 16.sp
            )
            Text(
                text = appItem.appShortDescription,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 12.sp
            )
            Text(
                text = appItem.appCategory,
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
        AppsList(
            listOf(
                AppItem(0),
                AppItem(1),
                AppItem(2),
                AppItem(3),
            ),
            onAppClick = {}
        )
    }
}