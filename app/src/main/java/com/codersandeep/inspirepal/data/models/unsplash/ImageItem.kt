package com.codersandeep.inspirepal.data.models.unsplash

data class ImageItem(
    val links: Links, //links>html
    val urls: Urls,//Urls > regular
    val user: User, // User>name | user>links>html
)