package com.my.submission.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.my.submission.core.R
import com.my.submission.core.databinding.ItemListRecipeBinding
import com.my.submission.core.domain.model.Recipe

class RecipeAdapter : RecyclerView.Adapter<RecipeAdapter.ListViewHolder>() {

    private var listData = ArrayList<Recipe>()
    var onItemClick: ((Recipe) -> Unit)? = null

    fun setData(newListData: List<Recipe>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_recipe, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListRecipeBinding.bind(itemView)
        fun bind(data: Recipe) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.thumb)
                    .into(ivItemImage)
                tvItemTitle.text = data.title
                tvItemSubtitle.text = "Waktu memasak: ${data.times}"
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}