package com.example.semesterproject.myRecycler

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.semesterproject.R
import kotlinx.android.synthetic.main.activity_my_recycler.ibElectricityAdd
import kotlinx.android.synthetic.main.activity_my_recycler.tvAverageElectricity
import kotlinx.android.synthetic.main.add_dialog.view.btnAdd
import kotlinx.android.synthetic.main.add_dialog.view.btnCancel
import kotlinx.android.synthetic.main.add_dialog.view.etAddDateWater
import kotlinx.android.synthetic.main.add_dialog.view.etAddWaterBill

class myRecyclerActivity : AppCompatActivity() {
    var pickedPhoto :Uri?=null
    var pickedBitMap: Bitmap?= null
    private var usersAverage :Double =1.0
    private lateinit var recyclerView: RecyclerView
    private lateinit var foodList:ArrayList<Foods>
    private lateinit var foodAdapter:FoodsAdapter
    private lateinit var imageView :ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_recycler)

        foodList=ArrayList()
        recyclerView=findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager= LinearLayoutManager(this)
        foodAdapter= FoodsAdapter(foodList)
        recyclerView.adapter=foodAdapter
        foodAdapter.onItemClick= {
            val intent =Intent(this,DetailedRecycler::class.java)
            intent.putExtra("food",it)
            startActivity(intent)
            }
        ibElectricityAdd.setOnClickListener{
            dialogFun()
        }
    }
    private fun dialogFun () {

        val view = View.inflate(this, R.layout.add_dialog, null)
        val builder = AlertDialog.Builder(this)
        builder.setView(view)
        val dialog = builder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        view.btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        view.btnAdd.setOnClickListener {
            pickedPhoto(view)
            var valu1: String =
                (view.etAddWaterBill.text.toString() + "\n" + view.etAddDateWater.text.toString())
            var edittoString = view.etAddWaterBill.text.toString()
            var editToInt = edittoString.toInt()
            usersAverage =
                (usersAverage * foodList.size.toInt() + editToInt) / (foodList.size.toInt() + 1)
            foodList.add(Foods(valu1, R.drawable.invoicei))


            tvAverageElectricity.text = usersAverage.toString()
            view.etAddWaterBill.text.clear()
            view.etAddDateWater.text.clear()

            dialog.dismiss()
        }




}
    fun pickedPhoto(view: View) {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf
                    (android.Manifest.permission.READ_EXTERNAL_STORAGE), 1
            )
        } else {
            val galleryIntex =
                Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galleryIntex, 2)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (grantResults.size>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            val galleryIntex =
                Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galleryIntex, 2)
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode==2 && resultCode==Activity.RESULT_OK && data!=null){
            pickedPhoto = data.data
            if (Build.VERSION.SDK_INT>=28){
            val source =ImageDecoder.createSource(this.contentResolver,pickedPhoto!!)
                pickedBitMap=ImageDecoder.decodeBitmap(source)
        }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}