package com.example.semesterproject

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.register.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnTakeToRegister.setOnClickListener {
           val intent =Intent(this,registerActivity::class.java)
            startActivity(intent)
        }
        btn_logIn.setOnClickListener {
            checkLogIN()
        }
    }

    private fun checkLogIN() {
        if (etLogInEmail.text.isEmpty()||etLogInPassowrd.text.isEmpty()){
            alertFail("Email and Password are Required.")
        }else{
            sendLogIn()
            val intent =Intent(this,MainScreen::class.java)
            startActivity(intent)
        }

    }

    private fun sendLogIn() {
        Toast.makeText(this,"send",Toast.LENGTH_SHORT).show()
    }

    private fun alertFail(s: String) {
        AlertDialog.Builder(this)
            .setTitle("Failed")
            .setIcon(R.drawable.ic_warning)
            .setMessage(s)
            .setPositiveButton("ok",DialogInterface.OnClickListener(){
                dialog, which ->  dialog.dismiss()
            }).show()

    }
}

