package com.example.elsol

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView

class TablaAdapter(
    private val items: List<ItemData>,
    private val onMenuItemClick: (item: ItemData, actionId: Int) -> Unit
) : RecyclerView.Adapter<TablaAdapter.CardViewHolder>() {

    inner class CardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.item_image)
        val toolbar: Toolbar = view.findViewById(R.id.itemToolbar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_card, parent, false)

        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val solarItem = items[position]

        holder.image.setImageResource(solarItem.imagenRes)
        holder.toolbar.title = solarItem.texto

        holder.toolbar.menu.clear()
        holder.toolbar.inflateMenu(R.menu.menu_card)

        holder.toolbar.setOnMenuItemClickListener { menuItem ->
            onMenuItemClick(solarItem, menuItem.itemId)
            true
        }
    }

    override fun getItemCount() = items.size


}