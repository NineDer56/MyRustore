package com.example.myrustore.presentation.screens.appDetails

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.myrustore.domain.AppCategory
import com.example.myrustore.domain.AppDetails
import com.example.myrustore.presentation.theme.MyRustoreTheme

@Composable
fun AppDetailsContent(
    appDetails: AppDetails,
    descriptionExpanded: Boolean,
    onReadMoreClick: () -> Unit,
    onButtonDownloadClick: () -> Unit,
    downloadState: DownloadState,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .verticalScroll(scrollState)

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            AsyncImage(
                model = appDetails.iconUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(172.dp)
                    .clip(RoundedCornerShape(40.dp))
                    .background(Color.Red)
            )
            Spacer(modifier = Modifier.width(16.dp))

            AboutApp(
                appCategory = appDetails.category.title,
                appName = appDetails.name,
                developerName = appDetails.developer,
                ageRestrictions = appDetails.ageRating.toString(),
                applicationSize = appDetails.size.toString(),
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

        ButtonDownload(
            onButtonDownloadClick = onButtonDownloadClick,
            downloadState = downloadState
        )

        Spacer(modifier = Modifier.height(20.dp))

        ScreenShots(appDetails.screenshotUrlList)

        Spacer(modifier = Modifier.height(20.dp))

        AppDescription(
            appDescription = appDetails.description,
            isExpanded = descriptionExpanded,
            onReadMoreClick = onReadMoreClick
        )

        Spacer(modifier = Modifier.height(4.dp))
        Spacer(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.secondary)
        )
        Spacer(modifier = Modifier.height(16.dp))

        AboutDeveloper(appDetails.developer)

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
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
private fun AppDescription(
    appDescription: String,
    isExpanded: Boolean,
    onReadMoreClick: () -> Unit
) {
    Text(
        text = "Описание приложения",
        fontSize = 16.sp,
        color = MaterialTheme.colorScheme.onBackground
    )

    Spacer(modifier = Modifier.height(4.dp))

    AnimatedContent(
        targetState = isExpanded,
        transitionSpec = {
            fadeIn() togetherWith fadeOut()
        },
        label = "descriptionExpansion"
    ) { expanded ->
        Column {
            Text(
                text = appDescription,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onBackground,
                maxLines = if (expanded) Int.MAX_VALUE else 3,
                overflow = TextOverflow.Ellipsis
            )

            TextButton(
                onClick = onReadMoreClick,
                contentPadding = PaddingValues(0.dp)
            ) {

                Text(
                    text = if (expanded) "Свернуть" else "Читать подробнее",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}


@Composable
fun AboutApp(
    appCategory: String,
    appName: String,
    developerName: String,
    ageRestrictions: String,
    applicationSize: String
) {
    Column(
        modifier = Modifier
            .height(172.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = appCategory,
            color = MaterialTheme.colorScheme.secondary,
            fontSize = 16.sp
        )
        Text(
            text = appName,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Text(
            text = developerName,
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 16.sp
        )

        Row(
            modifier = Modifier.fillMaxWidth(),

            ) {
            TwoTextsInColumn(
                upperText = "$ageRestrictions+",
                lowerText = "Возраст"
            )
            Spacer(Modifier.width(16.dp))
            TwoTextsInColumn(
                upperText = "$applicationSize MB",
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
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = lowerText,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}


@Composable
fun ButtonDownload(
    onButtonDownloadClick: () -> Unit,
    downloadState: DownloadState
) {
    when (downloadState) {
        is DownloadState.Initial -> {
            ButtonDownloadStandard(
                onButtonDownloadClick = onButtonDownloadClick,
                text = "Скачать"
            )
        }

        is DownloadState.Prepare -> {
            ButtonDownloadStandard(
                onButtonDownloadClick = onButtonDownloadClick,
                text = "Подготовка"
            )
        }

        is DownloadState.Loading -> {
            ButtonDownloadStandard(
                onButtonDownloadClick = onButtonDownloadClick,
                text = "${downloadState.percent}%"
            )
        }

        is DownloadState.Finish -> {
            ButtonDownloadStandard(
                onButtonDownloadClick = onButtonDownloadClick,
                text = "Установить"
            )
        }
    }


}

@Composable
private fun ButtonDownloadStandard(
    onButtonDownloadClick: () -> Unit,
    text: String
) {
    Button(
        onClick = onButtonDownloadClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }

}


@Composable
fun ScreenShots(
    screenshots: List<String>
) {
    Text(
        text = "Скриншоты",
        fontSize = 16.sp,
        color = MaterialTheme.colorScheme.onBackground
    )
    Spacer(modifier = Modifier.height(8.dp))
    LazyRow(
        modifier = Modifier
            .height(280.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)

    ) {
        items(screenshots) { imageVector ->
            AsyncImage(
                model = imageVector,
                contentDescription = null,
                modifier = Modifier
                    .width(240.dp)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.FillHeight
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
        AppDetailsContent(
            appDetails = AppDetails(
                id = "",
                name = "Гильдия Героев: Экшен ММО РПГ",
                developer = "developer",
                category = AppCategory.HEALTH,
                ageRating = 3,
                size = 24.5f,
                iconUrl = "",
                screenshotUrlList = emptyList(),
                description = "Calmalist — это менеджер задач и привычек с простым интерфейсом и поддержкой напоминаний. Отслеживайте выполнение дел, создавайте цветовые рубрики, стройте диаграммы прогресса. Поддержка виджетов и тем оформления делает работу приятной в любое время суток. Доступна сортировка и архив старых задач, календарь и отчёты об эффективности. Уведомления не мешают работе, а плавная анимация расслабляет пользователя."
            ),
            onReadMoreClick = {},
            onButtonDownloadClick = {},
            downloadState = DownloadState.Initial,
            modifier = Modifier.padding(8.dp),
            descriptionExpanded = false
        )
    }
}