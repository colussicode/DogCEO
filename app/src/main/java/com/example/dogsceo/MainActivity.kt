package com.example.dogsceo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dogsceo.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var retrofitClient: DogBreedsService
    private lateinit var binding: ActivityMainBinding
    private lateinit var dogAdapter: DogAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofitClient = RetrofitClient.getInstance().create(DogBreedsService::class.java)

        initRv()
        loadDogBreeds()
    }

    private fun initRv() {
        binding.rvDogs.layoutManager = LinearLayoutManager(this)
        dogAdapter = DogAdapter()
    }

    private fun loadDogBreeds() {
        lifecycleScope.launch(Dispatchers.IO) {
            val imagesByBreed = retrofitClient.fetchDogImagesByBreed()
            withContext(Dispatchers.Main) {
                binding.rvDogs.adapter = dogAdapter
                dogAdapter.updateList(imagesByBreed.body()!!.message)
            }
        }
    }
}