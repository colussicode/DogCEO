package com.example.dogsceo

import retrofit2.Response
import retrofit2.http.GET

interface DogBreedsService {
    @GET("breeds/list/all")
    suspend fun fetchDogBreeds() : Response<DogBreedsResponse>

    @GET("breed/hound/images")
    suspend fun fetchDogImagesByBreed() : Response<DogImageResponse>
}