package com.example.myrustore.domain

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
enum class AppCategory(val title : String) {

    @SerializedName("Приложения")
    APP("Приложения"),

    @SerializedName("Игры")
    GAME("Игры"),

    @SerializedName("Производительность")
    PRODUCTIVITY("Производительность"),

    @SerializedName("Социальные сети")
    SOCIAL("Социальные сети"),

    @SerializedName("Общение")
    MESSAGING("Общение"),

    @SerializedName("Образование")
    EDUCATION("Образование"),

    @SerializedName("Развлечения")
    ENTERTAINMENT("Развлечения"),

    @SerializedName("Музыка")
    MUSIC("Музыка"),

    @SerializedName("Фото и видео")
    PHOTO_AND_VIDEO("Фото и видео"),

    @SerializedName("Фотография")
    PHOTOGRAPHY("Фотография"),

    @SerializedName("Здоровье и фитнес")
    HEALTH("Здоровье и фитнес"),

    @SerializedName("Спорт")
    SPORTS("Спорт"),

    @SerializedName("Новости")
    NEWS("Новости"),

    @SerializedName("Книги и справочники")
    BOOKS("Книги и справочники"),

    @SerializedName("Бизнес")
    BUSINESS("Бизнес"),

    @SerializedName("Финансы")
    FINANCE("Финансы"),

    @SerializedName("Путешествия")
    TRAVEL("Путешествия"),

    @SerializedName("Карты")
    MAPS("Карты"),

    @SerializedName("Еда и напитки")
    FOOD("Еда и напитки"),

    @SerializedName("Шопинг")
    SHOPPING("Шопинг"),

    @SerializedName("Утилиты")
    UTILITIES("Утилиты"),

    @SerializedName("Навигация")
    NAVIGATION("Навигация"),

    @SerializedName("Погода")
    WEATHER("Погода"),

    @SerializedName("Образ жизни")
    LIFESTYLE("Образ жизни")
}
