package com.example.hopistalfirebase.Activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.hopistalfirebase.Domain.DoctorModel
import com.example.hopistalfirebase.databinding.ActivityDetailBinding

class DetailActivity : BaseAct() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var item: DoctorModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getBunder()
    }

    // lấy dữ liệu từ Intent
    private fun getBunder() {
        item = intent.getParcelableExtra("object")!!

        binding.apply {
            degreeTxt.text = item.Name
            speacialTxt.text = item.Special
            patientsTxt.text = item.Patiens
            bioTxt.text = item.Biography
            addressTxt.text = item.Address
            experienceTxt.text = item.Expriense.toString() + "years"
            raringTxt.text = "${item.Rating}"
            backBtn.setOnClickListener { finish() }


            websiteBtn.setOnClickListener {
                val i = Intent(Intent.ACTION_VIEW)
                i.setData(Uri.parse(item.Site))
                startActivity(i)
            }
            messageBtn.setOnClickListener {
                val uri = Uri.parse("smsto: ${item.Mobile}")
                val inten = Intent(Intent.ACTION_SENDTO, uri)
                inten.putExtra("sms_body", "the SMS text")
                startActivity(inten)
            }
            directionBtn.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                Uri.parse(item.Location)
                startActivity(intent)
            }
            callBtn.setOnClickListener {
                val uri = "tel:" + item.Mobile.trim()
                val intent = Intent(
                    Intent.ACTION_DIAL,
                    Uri.parse(uri)
                )
                startActivity(intent)
            }
            shareBtn.setOnClickListener {
                val intent = Intent(Intent.ACTION_SEND)
                intent.setType("text/plain")
                intent.putExtra(Intent.EXTRA_SUBJECT, item.Name)
                intent.putExtra(
                    Intent.EXTRA_TEXT,
                    item.Name + " " + item.Address + " " + item.Mobile
                )
                startActivity(Intent.createChooser(intent, "choose one "))
            }
            Glide.with(this@DetailActivity)
                .load(item.Picture)
                .into(img)
        }
    }
}