package com.codersandeep.inspirepal.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codersandeep.inspirepal.data.models.Image
import com.codersandeep.inspirepal.data.models.Quote
import com.codersandeep.inspirepal.data.repository.ImageRepository
import com.codersandeep.inspirepal.data.repository.QuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val quoteRepository: QuoteRepository,private val imageRepository: ImageRepository): ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.IO) {
            quoteRepository.getQuote()
            imageRepository.getImage()
        }
    }
    val quotes:LiveData<Quote>
    get() = quoteRepository.quotes

    val images:LiveData<Image>
    get() = imageRepository.images
}