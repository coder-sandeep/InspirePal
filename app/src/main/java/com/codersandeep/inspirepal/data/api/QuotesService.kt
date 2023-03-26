package com.codersandeep.inspirepal.data.api

import com.codersandeep.inspirepal.data.models.quote.Quote
import retrofit2.Response
import retrofit2.http.GET

interface QuotesService {
    @GET("/random")
    suspend fun getQuotes(
    ):Response<Quote>
}