package com.example.dogsceo

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class MainViewModel(
    private val dogBreedsService: DogBreedsService
) : ViewModel() {

    private val _dogs = MutableLiveData<List<String>>()
    val dogs: LiveData<List<String>> = _dogs

    fun fetchDogs() {
        viewModelScope.launch {
            val dogs = dogBreedsService.fetchDogImagesByBreed()
            _dogs.postValue(dogs.message)
        }
    }
}

class MainViewModelFactory(private val dogBreedsService: DogBreedsService) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>) = MainViewModel(dogBreedsService) as T
}