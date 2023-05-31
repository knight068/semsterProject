package com.example.semesterproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.semesterproject.myRecycler.Foods
import com.example.semesterproject.myRecycler.InternetRecycler
import com.example.semesterproject.myRecycler.OtherRecycler
import com.example.semesterproject.myRecycler.PhoneRecycler
import com.example.semesterproject.myRecycler.WaterActivity
import com.example.semesterproject.myRecycler.myRecyclerActivity
import kotlinx.android.synthetic.main.activity_main_screen.*
import kotlinx.android.synthetic.main.activity_other_recycler.otherRecyclerView
import org.w3c.dom.Text


class MainScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)
        bindRecyclers()
       // tvElectricity.text=
    }


    private fun bindRecyclers(){
        llElectricity.setOnClickListener{
            val intent= Intent(this,myRecyclerActivity::class.java)
            startActivity(intent)
        }
        llWater.setOnClickListener{
            val intent= Intent(this,WaterActivity::class.java)
            startActivity(intent)
        }
        llPhone.setOnClickListener{
            val intent= Intent(this,PhoneRecycler::class.java)
            startActivity(intent)
        }
        llInternet.setOnClickListener{
            val intent= Intent(this,InternetRecycler::class.java)
            startActivity(intent)
        }
        llOther.setOnClickListener{
            val intent= Intent(this,OtherRecycler::class.java)
            startActivity(intent)
        }
    }
}