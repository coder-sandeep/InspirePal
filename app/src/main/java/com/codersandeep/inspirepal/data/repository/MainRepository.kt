package com.codersandeep.inspirepal.data.repository

import com.codersandeep.inspirepal.data.api.ImageService
import com.codersandeep.inspirepal.data.api.QuotesService
import com.codersandeep.inspirepal.data.db.QuoteDatabase
import com.codersandeep.inspirepal.data.db.entity.QuoteEntity

class MainRepository(private val imageService: ImageService, private val quoteService: QuotesService,private val quoteDatabase: QuoteDatabase) {

    suspend fun getQuote() = quoteService.getQuotes()
    suspend fun getImage(queryString:String) = imageService.getImage(queryString)

    suspend fun saveQuote(quoteEntity: QuoteEntity) = quoteDatabase.quoteDao().insertQuote(quoteEntity)

}