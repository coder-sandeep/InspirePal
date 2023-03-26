package com.codersandeep.inspirepal.viewmodels

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codersandeep.inspirepal.data.db.entity.QuoteEntity
import com.codersandeep.inspirepal.data.models.image.Image
import com.codersandeep.inspirepal.data.models.quote.Quote
import com.codersandeep.inspirepal.data.repository.MainRepository
import kotlinx.coroutines.launch

class MainViewModel(private val mainRepository: MainRepository): ViewModel() {
    init {
        getNextQuote()
    }
    val quotes: MutableLiveData<Quote> = MutableLiveData()
    val image: MutableLiveData<Image> = MutableLiveData()


    fun getNextQuote() {
        viewModelScope.launch {
            val response = mainRepository.getQuote()
            quotes.postValue(response.body())
        }
        viewModelScope.launch {
            val response = mainRepository.getImage("nature")
            image.postValue(response.body())
        }
    }
     fun saveQuote(quoteEntity: QuoteEntity){
         viewModelScope.launch {
             mainRepository.saveQuote(quoteEntity)
         }
    }
}