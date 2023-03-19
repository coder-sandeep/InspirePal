package com.codersandeep.inspirepal.data.api

import com.codersandeep.inspirepal.data.models.unsplash.Image
import com.codersandeep.inspirepal.utils.APIKeys
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import kotlin.coroutines.coroutineContext

interface ImageService {
    @GET("/photos/random/")
     suspend fun getImage(
        @Query("query")
        queryString:String = "nature",
        @Query("orientation")
        orientation:String = "portrait",
        @Query("count")
        count:Int = 1,
        @Query("client_id")
        authorizationKey:String = APIKeys.UNSPLASH_API_KEY
    ):Response<Image>
}