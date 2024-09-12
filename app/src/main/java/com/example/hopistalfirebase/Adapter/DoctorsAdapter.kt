package com.example.hopistalfirebase.Adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.hopistalfirebase.Activity.DetailActivity
import com.example.hopistalfirebase.Domain.CategoryModel
import com.example.hopistalfirebase.Domain.DoctorModel
import com.example.hopistalfirebase.databinding.ViewhodelDoctorBinding

class DoctorsAdapter(val items: MutableList<DoctorModel>) :
    RecyclerView.Adapter<DoctorsAdapter.Viewhodel>() {

    private var context: Context? = null


    class Viewhodel(val binding: ViewhodelDoctorBinding) : RecyclerView.ViewHolder(binding.root) {

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorsAdapter.Viewhodel {
        context = parent.context
        val binding = ViewhodelDoctorBinding.inflate(LayoutInflater.from(context), parent, false)
        return Viewhodel(binding)
    }

    override fun onBindViewHolder(holder: DoctorsAdapter.Viewhodel, position: Int) {
        holder.binding.nameTxt.text = items[position].Name
        holder.binding.spacialTxt.text = items[position].Special
        holder.binding.ratingTxt.text = items[position].Rating.toString()
        holder.binding.yearTxt.text = items[position].Expriense.toString() + "year"

        Glide.with(holder.itemView.context)
            .load(items[position].Picture)
            .apply{RequestOptions().transform(CenterCrop())}
            .into(holder.binding.imgavt)
        Log.d("DoctorsAdapter", "https://firebasestorage.googleapis.com/v0/b/project198-ee047.appspot.com/o/Dr%20Jessica%20Wyne.png?alt=media&token=9ad675d6-45e9-4bc5-b61b-fdecbcc6c79b: ${items[position].Picture}")

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("object", items[position])
            context?.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = items.size
}