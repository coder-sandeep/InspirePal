package com.codersandeep.inspirepal.data.api

import com.codersandeep.inspirepal.data.models.image.Image
import com.codersandeep.inspirepal.utils.APIKeys
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ImageService {
    @GET("/v1/search/")
     suspend fun getImage(
        @Query("query")
        queryString:String,
        @Query("orientation")
        orientation:String = "portrait",
        @Query("per_page")
        count:Int = 80,
        @Header("Authorization")
        authorizationKey:String = APIKeys.IMAGE_API_KEY
    ):Response<Image>
}