package com.example.dogsceo

data class DogBreedsResponse(
    val message: Map<String, List<String>>
)

data class DogImageResponse(
    val message: List<String>,
    val status: String
)
