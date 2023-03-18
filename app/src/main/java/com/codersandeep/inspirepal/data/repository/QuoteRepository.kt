package com.codersandeep.inspirepal.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.codersandeep.inspirepal.data.api.QuotesService
import com.codersandeep.inspirepal.data.models.Quote

class QuoteRepository(private val quotesService: QuotesService) {
    private val quotesLiveData= MutableLiveData<Quote>()
    val quotes:LiveData<Quote>
    get() = quotesLiveData

    suspend fun getQuote(){
        val result = quotesService.getQuotes()
        if (result?.body()!=null)
            quotesLiveData.postValue(result.body())
    }
}