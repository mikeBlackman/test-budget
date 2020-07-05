package com.dynamiccontrols.myob.ui.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dynamiccontrols.myob.databinding.CategoryCellBinding
import com.dynamiccontrols.myob.model.Category


class CategoriesAdapter(
    private var categories: List<Category>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val itemBinding =
            CategoryCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(itemBinding)
    }

    fun update(categories: Set<Category>){
        this.categories = categories.toList()
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val category = categories[position]
        if (holder is CategoryViewHolder) {
            holder.bind(category)
        }
    }

    override fun getItemCount(): Int = categories.size

    inner class CategoryViewHolder(private val binding: CategoryCellBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Category) {
            binding.categoryName.text = "Category: " + category.name
            binding.categoryTotal.text = "Not implemented"
        }
    }
}

