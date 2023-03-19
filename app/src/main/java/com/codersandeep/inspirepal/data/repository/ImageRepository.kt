package com.codersandeep.inspirepal.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.codersandeep.inspirepal.data.api.ImageService
import com.codersandeep.inspirepal.data.models.unsplash.Image

class ImageRepository(private val imageService: ImageService) {

    private val imageMutableLiveData = MutableLiveData<Image>()
    val images:LiveData<Image>
        get() = imageMutableLiveData

    suspend fun getImage(){
        val result = imageService.getImage();
        if (result.body()!=null)
            imageMutableLiveData.postValue(result.body())
    }
}