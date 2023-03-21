package com.example.countryregion

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.countryregion.models.CountryResponse
import com.example.countryregion.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = Repository()
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage
    private val _countries = MutableLiveData<List<CountryResponse>>()
    val countries: LiveData<List<CountryResponse>> get() = _countries

    fun getCountries() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val countryResponse = repository.getAllCountries()
                if (countryResponse.isSuccessful) {
                    countryResponse.body()?.let {
                        _countries.postValue(it)
                    }
                } else {
                    _errorMessage.postValue("Error whilst fetching countries")
                    Log.e("**logged at MainViewModel", "Error whilst fetching countries")
                }
            } catch (e: Exception) {
                _errorMessage.postValue(e.getErrorMessage())
                Log.e("**logged at MainViewModel", e.getErrorMessage())
            }
        }
    }
}