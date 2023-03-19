package com.codersandeep.inspirepal.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.codersandeep.inspirepal.data.repository.ImageRepository
import com.codersandeep.inspirepal.data.repository.QuoteRepository

class HomeViewModelFactory(private val quoteRepository: QuoteRepository, private val imageRepository: ImageRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(quoteRepository,imageRepository) as T
    }
}