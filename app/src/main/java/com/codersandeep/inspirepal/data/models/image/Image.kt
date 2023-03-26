package com.codersandeep.inspirepal.data.models.image

data class Image(
    val next_page: String,
    val page: Int,
    val per_page: Int,
    val photos: List<Photo>,
    val total_results: Int,
    var randomPos: Int
)