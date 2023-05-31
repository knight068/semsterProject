package com.example.semesterproject.myRecycler

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.semesterproject.R
import kotlinx.android.synthetic.main.activity_other_recycler.ibOtherAdd
import kotlinx.android.synthetic.main.activity_other_recycler.tvAverageOther
import kotlinx.android.synthetic.main.add_dialog.view.btnAdd
import kotlinx.android.synthetic.main.add_dialog.view.btnCancel
import kotlinx.android.synthetic.main.add_dialog.view.etAddDateWater
import kotlinx.android.synthetic.main.add_dialog.view.etAddWaterBill

class OtherRecycler : AppCompatActivity() {
    private var usersAverage :Double =1.0
    private lateinit var recyclerView: RecyclerView
    private lateinit var foodList:ArrayList<Foods>
    private lateinit var foodAdapter:FoodsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other_recycler)
        foodList=ArrayList()
        recyclerView=findViewById(R.id.otherRecyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager= LinearLayoutManager(this)
        foodAdapter= FoodsAdapter(foodList)
        recyclerView.adapter=foodAdapter
        foodAdapter.onItemClick= {
            val intent = Intent(this,DetailedRecycler::class.java)
            intent.putExtra("food",it)
            startActivity(intent)
        }
        ibOtherAdd.setOnClickListener{
            dialogFun()
        }
    }
    private fun dialogFun (){

        val  view = View.inflate(this,R.layout.add_dialog,null)
        val builder= AlertDialog.Builder(this)
        builder.setView(view)
        val dialog =builder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        view.btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        view.btnAdd.setOnClickListener{
            var valu1 :String =(view.etAddWaterBill.text.toString()+"\n"+view.etAddDateWater.text.toString())
            // pickImageGallery()
            var edittoString =view.etAddWaterBill.text.toString()
            var editToInt =edittoString.toInt()
            usersAverage=(usersAverage*foodList.size.toInt()+editToInt)/(foodList.size.toInt()+1)
            foodList.add(Foods(valu1,R.drawable.billview))
            tvAverageOther.text=usersAverage.toString()
            view.etAddWaterBill.text.clear()
            view.etAddDateWater.text.clear()

            dialog.dismiss()
        }


    }
}