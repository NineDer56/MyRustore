package com.example.myrustore.presentation.screens.appList

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myrustore.domain.AppDetails
import com.example.myrustore.domain.AppItem
import com.example.myrustore.presentation.theme.MyRustoreTheme

@Composable
fun AppListContent(
    apps : List<AppItem>,
    onAppClick : (appId : Int) -> Unit,
    modifier: Modifier = Modifier
) {
   LazyColumn(
       verticalArrangement = Arrangement.spacedBy(8.dp),
       modifier = modifier
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
    appDetails : AppItem,
    onAppClick : (appId : Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = {onAppClick(appDetails.appId)})
        ,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = appDetails.appImage,
            contentDescription = null,
            modifier = Modifier
                .clip(RoundedCornerShape(18.dp))
                .background(MaterialTheme.colorScheme.primary)
                .size(90.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column{
            Text(
                text = appDetails.appName,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 16.sp
            )
            Text(
                text = appDetails.appShortDescription,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 12.sp
            )
            Text(
                text = appDetails.appCategory + " " + appDetails.appId,
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
        AppListContent(
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