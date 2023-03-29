package com.example.dogsceo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dogsceo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var retrofitService: DogBreedsService
    private lateinit var binding: ActivityMainBinding
    private lateinit var dogAdapter: DogAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofitService = RetrofitClient.getInstance().create(DogBreedsService::class.java)
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(retrofitService))[MainViewModel::class.java]
        dogAdapter = DogAdapter()
        initRv()
        fetchDogs()
        loadDogBreeds()
    }

    private fun initRv() {
        binding.rvDogs.layoutManager = LinearLayoutManager(this)
    }

    private fun fetchDogs() {
        mainViewModel.fetchDogs()
    }
    private fun loadDogBreeds() {
        mainViewModel.dogs.observe(this){ dogs ->
            binding.rvDogs.adapter = dogAdapter
            dogAdapter.updateList(dogs)
        }
    }
}