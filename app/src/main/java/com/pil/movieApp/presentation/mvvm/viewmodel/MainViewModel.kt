package com.pil.movieApp.presentation.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private val mutableLiveData: MutableLiveData<MainData> = MutableLiveData()

    fun getValue():LiveData<MainData> = mutableLiveData

    fun onMoviesButtonPressed() {
        mutableLiveData.postValue(MainData(MainStatus.SHOW_MOVIES))
    }

    fun onShowErrorButtonPressed() {
        mutableLiveData.postValue(MainData(MainStatus.SHOW_ERROR_DIALOG))
    }

    data class MainData(
        val status : MainStatus,
            )

    enum class MainStatus{
        SHOW_MOVIES,
        SHOW_ERROR_DIALOG,
    }
}