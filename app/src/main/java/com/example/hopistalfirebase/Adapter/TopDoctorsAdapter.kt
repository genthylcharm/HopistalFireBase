package com.example.hopistalfirebase.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.hopistalfirebase.Activity.DetailActivity
import com.example.hopistalfirebase.Domain.DoctorModel
import com.example.hopistalfirebase.databinding.ViewhodelTopDoctorsBinding

class TopDoctorsAdapter(val items: MutableList<DoctorModel>) :
    RecyclerView.Adapter<TopDoctorsAdapter.Viewhodel>() {

    private var context: Context? = null


    class Viewhodel(val binding: ViewhodelTopDoctorsBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopDoctorsAdapter.Viewhodel {
        context = parent.context
        val binding =
            ViewhodelTopDoctorsBinding.inflate(LayoutInflater.from(context), parent, false)
        return Viewhodel(binding)
    }

    override fun onBindViewHolder(holder: TopDoctorsAdapter.Viewhodel, position: Int) {
        holder.binding.nameTxt.text = items[position].Name
        holder.binding.specialTxt.text = items[position].Special
        holder.binding.scoreTxt.text = items[position].Rating.toString()
        holder.binding.ratingBar.rating=items[position].Rating.toFloat()
        holder.binding.degreeTxt.text="Professional Doctor"

        Glide.with(holder.itemView.context)
            .load(items[position].Picture)
            .apply { RequestOptions().transform(CenterCrop()) }
            .into(holder.binding.pic)

        holder.binding.makePointmentBtn.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("object", items[position])
            context?.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = items.size
}