package com.marwa.moviecomposeproject.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.marwa.moviecomposeproject.domain.usescase.GetNowShowingUseCase

class MovieViewModelFactory(private val useCase: GetNowShowingUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(GetNowShowingUseCase::class.java).newInstance(useCase)
    }
}