package com.example.listaperros

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listaperros.databinding.ItemDogBinding
import com.squareup.picasso.Picasso

class DogAdapter(val image: List<String>) : RecyclerView.Adapter<DogViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DogViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return DogViewHolder(layoutInflater.inflate(R.layout.item_dog,parent,false))

    }

    override fun onBindViewHolder(
        holder: DogViewHolder,
        position: Int
    ) {
        val item = image[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return image.size
    }
}


class DogViewHolder(view: View): RecyclerView.ViewHolder(view){

    val binding = ItemDogBinding.bind(view)
    fun bind(image: String){
        Picasso.get().load(image).into(binding.ivDog)
    }
}