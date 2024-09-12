package com.example.hopistalfirebase.Adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hopistalfirebase.Domain.CategoryModel
import com.example.hopistalfirebase.databinding.ViewhodelCategoryBinding

class CategoryAdapter(val items:MutableList<CategoryModel>): RecyclerView.Adapter<CategoryAdapter.ViewHodel>() {
    private lateinit var context : Context
    class ViewHodel (val binding: ViewhodelCategoryBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.ViewHodel {
       context = parent.context
        val binding = ViewhodelCategoryBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHodel(binding)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.ViewHodel, position: Int) {
        val item =items[position]
        holder.binding.degreeTxt.text= item.Name

        Glide.with(context)
            .load(item.Picture)
            .into(holder.binding.img)
    }

    override fun getItemCount(): Int  =items.size
}