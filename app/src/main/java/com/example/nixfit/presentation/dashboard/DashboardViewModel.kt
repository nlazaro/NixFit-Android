package com.example.nixfit.presentation.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nixfit.data.remote.FoodsResponse
import com.example.nixfit.domain.usecases.FoodsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor (
    private val foodUseCases : FoodsUseCases
): ViewModel() {
    private val _foodResponse = MutableLiveData<Result<FoodsResponse>>()
    val foodResponse: LiveData<Result<FoodsResponse>> = _foodResponse

    suspend fun getFoods(barcode: Int, fields: String){
        foodUseCases.getFoods(barcode, fields)
            .collect{
                _foodResponse.value = it
        }
    }
}