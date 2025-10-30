package com.example.myrustore.domain

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
enum class AppCategory {

    @SerializedName("Приложения")
    APP,

    @SerializedName("Игры")
    GAME,

    @SerializedName("Производительность")
    PRODUCTIVITY,

    @SerializedName("Социальные сети")
    SOCIAL,

    @SerializedName("Общение")
    MESSAGING,

    @SerializedName("Образование")
    EDUCATION,

    @SerializedName("Развлечения")
    ENTERTAINMENT,

    @SerializedName("Музыка")
    MUSIC,

    @SerializedName("Фото и видео")
    PHOTO_AND_VIDEO,

    @SerializedName("Фотография")
    PHOTOGRAPHY,

    @SerializedName("Здоровье и фитнес")
    HEALTH,

    @SerializedName("Спорт")
    SPORTS,

    @SerializedName("Новости")
    NEWS,

    @SerializedName("Книги и справочники")
    BOOKS,

    @SerializedName("Бизнес")
    BUSINESS,

    @SerializedName("Финансы")
    FINANCE,

    @SerializedName("Путешествия")
    TRAVEL,

    @SerializedName("Карты")
    MAPS,

    @SerializedName("Еда и напитки")
    FOOD,

    @SerializedName("Шопинг")
    SHOPPING,

    @SerializedName("Утилиты")
    UTILITIES,

    @SerializedName("Навигация")
    NAVIGATION,

    @SerializedName("Погода")
    WEATHER,

    @SerializedName("Образ жизни")
    LIFESTYLE
}
