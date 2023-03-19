package com.codersandeep.inspirepal.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codersandeep.inspirepal.data.models.Quote
import com.codersandeep.inspirepal.data.models.unsplash.Image
import com.codersandeep.inspirepal.data.repository.ImageRepository
import com.codersandeep.inspirepal.data.repository.QuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val quoteRepository: QuoteRepository, private val imageRepository: ImageRepository): ViewModel() {
    init {
       getNextQuote()
    }
    val quotes:LiveData<Quote>
    get() = quoteRepository.quotes

    val images:LiveData<Image>
    get() = imageRepository.images

    fun getNextQuote(){
        viewModelScope.launch(Dispatchers.IO) {
            quoteRepository.getQuote()
            imageRepository.getImage()
        }
    }
}