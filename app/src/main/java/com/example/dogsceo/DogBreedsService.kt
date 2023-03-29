package com.example.dogsceo

import retrofit2.http.GET

interface DogBreedsService {
    @GET("breed/hound/images")
    suspend fun fetchDogImagesByBreed() : DogImageResponse
}