package com.example.hopistalfirebase.Activity

import MainViewModel
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hopistalfirebase.Adapter.CategoryAdapter
import com.example.hopistalfirebase.Adapter.DoctorsAdapter
import com.example.hopistalfirebase.databinding.ActivityHomeBinding

class HomeActivity: BaseAct() {
    private lateinit var binding: ActivityHomeBinding
    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initCategory()
        initDoctors()
    }

    // Phương thức để khởi tạo phần danh mục
    private fun initCategory() {
        binding.progresView1.visibility = View.VISIBLE
        viewModel.category.observe(this, Observer {
            binding.view1.layoutManager=LinearLayoutManager(this@HomeActivity,
                LinearLayoutManager.HORIZONTAL,false)
            binding.view1.adapter=CategoryAdapter(it)
            binding.progresView1.visibility=View.GONE
        })
        viewModel.loadCategory()
    }
    // Phương thức Ds bác sĩ
    private fun initDoctors() {
binding.apply {
    progresView2.visibility=View.VISIBLE
    viewModel.doctors.observe(this@HomeActivity, Observer {
        view2.layoutManager=LinearLayoutManager(this@HomeActivity,LinearLayoutManager.HORIZONTAL,false)
        view2.adapter=DoctorsAdapter(it)
        progresView2.visibility=View.GONE
    })
    viewModel.loadDoctors()
    SeeAllTxt.setOnClickListener {
        startActivity(Intent(this@HomeActivity,TopDoctorActivity::class.java))
    }
}
    }

}
