package com.example.dogsceo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dogsceo.databinding.DogItemBinding

class DogAdapter() : RecyclerView.Adapter<DogAdapter.DogViewHolder>(){

    private var dogList: List<String> = emptyList()

    fun updateList(dogList: List<String>) {
        this.dogList = dogList
    }

    class DogViewHolder(binding: DogItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val dogImage = binding.imageviewDog
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val adapterLayout = DogItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return DogViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        val item = dogList[position]

        Glide.with(holder.itemView.context)
            .load(item)
            .into(holder.dogImage)
    }

   override fun getItemCount() = dogList.size
}