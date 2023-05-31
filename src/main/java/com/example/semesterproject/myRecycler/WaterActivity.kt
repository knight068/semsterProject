package com.example.semesterproject.myRecycler

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.semesterproject.R
import kotlinx.android.synthetic.main.activity_water.ibWaterAdd
import kotlinx.android.synthetic.main.add_dialog.btnAdd
import kotlinx.android.synthetic.main.add_dialog.btnCancel
import kotlinx.android.synthetic.main.add_dialog.view.btnAdd
import kotlinx.android.synthetic.main.add_dialog.view.btnCancel
import kotlinx.android.synthetic.main.add_dialog.view.etAddDateWater
import kotlinx.android.synthetic.main.add_dialog.view.etAddWaterBill

class WaterActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var foodList:ArrayList<Foods>
    private lateinit var foodAdapter:FoodsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_water)
        ibWaterAdd.setOnClickListener {
            dialogFun()
        }


        foodList=ArrayList()
        recyclerView=findViewById(R.id.waterRecyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager= LinearLayoutManager(this)
        foodList.add(Foods("someThing",R.drawable.billview))
        foodAdapter= FoodsAdapter(foodList)
        recyclerView.adapter=foodAdapter
        foodAdapter.onItemClick= {
            val intent = Intent(this,DetailedRecycler::class.java)
            intent.putExtra("food",it)
            startActivity(intent)
        }
    }

    private fun dialogFun (){
        ibWaterAdd.setOnClickListener{
            val  view =View.inflate(this,R.layout.add_dialog,null)
            val builder= AlertDialog.Builder(this)
            builder.setView(view)
            val dialog =builder.create()
            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            view.btnCancel.setOnClickListener {
                dialog.dismiss()
            }
            view.btnAdd.setOnClickListener{
                var valu :String =(view.etAddWaterBill.text.toString()+"\n"+view.etAddDateWater.text.toString())
                foodList.add(Foods(valu,R.drawable.billview))
                view.etAddWaterBill.text.clear()
                view.etAddDateWater.text.clear()
                dialog.dismiss()
            }
        }

    }
}