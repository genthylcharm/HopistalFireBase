package com.example.hopistalfirebase.Activity

import MainViewModel
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hopistalfirebase.Adapter.TopDoctorsAdapter
import com.example.hopistalfirebase.databinding.ActivityTopDoctorBinding

class TopDoctorActivity : BaseAct() {
    private lateinit var binding: ActivityTopDoctorBinding
    private val viewModel = MainViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTopDoctorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initTopDoctors()
    }

    private fun initTopDoctors() {
        binding.apply {
            progressBar.visibility = View.VISIBLE
            viewModel.doctors.observe(this@TopDoctorActivity, Observer {
                viewDoctors.layoutManager =
                    LinearLayoutManager(this@TopDoctorActivity, LinearLayoutManager.VERTICAL, false)
                viewDoctors.adapter = TopDoctorsAdapter(it)
                progressBar.visibility = View.GONE
            })
            viewModel.loadDoctors()
            backBtn.setOnClickListener { finish() }
        }
    }
}
