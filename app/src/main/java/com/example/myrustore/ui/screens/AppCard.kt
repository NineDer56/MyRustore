package com.example.myrustore.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myrustore.ui.AppItem
import com.example.myrustore.ui.theme.MyRustoreTheme

@Composable
fun AppCard(
    appItem: AppItem,
    paddingValues: PaddingValues = PaddingValues(
        vertical = 16.dp,
        horizontal = 12.dp
    )
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(paddingValues)

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                imageVector = appItem.appImage,
                contentDescription = null,
                modifier = Modifier
                    .size(172.dp)
                    .clip(RoundedCornerShape(40.dp))
                    .background(Color.Red)
            )
            Spacer(modifier = Modifier.width(16.dp))

            AboutApp(
                appCategory = appItem.appCategory,
                appName = appItem.appName,
                developerName = appItem.developerName,
                ageRestrictions = appItem.ageRestrictions,
                applicationSize = appItem.applicationSize,
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

        ButtonDownload()

        Spacer(modifier = Modifier.height(20.dp))

        ScreenShots(appItem.screenshots)

        Spacer(modifier = Modifier.height(20.dp))

        AboutApp(appItem.appDescription)

        Spacer(modifier = Modifier.height(16.dp))
        Spacer(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.secondary)
        )
        Spacer(modifier = Modifier.height(16.dp))

        AboutDeveloper(appItem.developerName)

    }
}

@Composable
private fun AboutDeveloper(developerName: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = developerName,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "Разработчик",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
private fun AboutApp(
    appDescription: String
) {
    Text(
        text = "Описание приложения",
        fontSize = 16.sp,
        color = MaterialTheme.colorScheme.onBackground
    )

    Spacer(modifier = Modifier.height(4.dp))

    Text(
        text = appDescription,
        fontSize = 14.sp,
        color = MaterialTheme.colorScheme.onBackground
    )

    Spacer(modifier = Modifier.height(8.dp))

    Text(
        text = "Читать подробнее",
        fontSize = 14.sp,
        color = MaterialTheme.colorScheme.primary
    )
}


@Composable
fun AboutApp(
    appCategory: String,
    appName: String,
    developerName: String,
    ageRestrictions: String,
    applicationSize: String
) {
    Column {
        Text(
            text = appCategory,
            color = MaterialTheme.colorScheme.secondary,
            fontSize = 14.sp
        )
        Text(
            text = appName,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Text(
            text = developerName,
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 14.sp
        )

        Row {
            TwoTextsInColumn(
                upperText = ageRestrictions,
                lowerText = "Возраст"
            )
            Spacer(Modifier.width(16.dp))
            TwoTextsInColumn(
                upperText = applicationSize,
                lowerText = "Размер"
            )
        }
    }
}

@Composable
private fun TwoTextsInColumn(
    upperText: String,
    lowerText: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = upperText,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = lowerText,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}


@Composable
fun ButtonDownload() {
    Button(
        onClick = {},
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Установить",
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}


@Composable
fun ScreenShots(
    screenshots: List<ImageVector>
) {
    Text(
        text = "Скриншоты",
        fontSize = 16.sp,
        color = MaterialTheme.colorScheme.onBackground
    )
    Spacer(modifier = Modifier.height(8.dp))
    LazyRow(
        modifier = Modifier
            .height(120.dp)
            .fillMaxWidth()
    ) {
        items(screenshots) { imageVector ->
            Image(
                imageVector = imageVector,
                contentDescription = null,
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.Red)
                    .fillParentMaxSize()
            )
        }
    }
}


@Preview
@Composable
private fun PreviewAppCard() {
    MyRustoreTheme(
        darkTheme = true
    ) {
        AppCard(AppItem(0))
    }
}