package com.example.coroutinessample.presentation.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutinessample.R
import com.example.coroutinessample.domain.PokemonType

class PokemonTypeAdapter(
    private val mList: List<PokemonType>,
    private val onTypeClicked: (PokemonType) -> Unit
) : RecyclerView.Adapter<PokemonTypeAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pokemon_type, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemonType = mList[position]
        holder.text.text = pokemonType.name
        holder.text.setOnClickListener {
            onTypeClicked.invoke(pokemonType)
        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text: TextView = itemView.findViewById(R.id.text)
    }
}