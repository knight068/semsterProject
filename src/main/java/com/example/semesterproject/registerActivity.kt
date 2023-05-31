package com.example.semesterproject

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*

class registerActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        btnTakeToLogIn.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            }
        btnRegister.setOnClickListener {
            checkRegister()

        }
    }

    private fun checkRegister() {
        if (etRegisterId.text.isEmpty()||etRegisterEmail.text.isEmpty()||etRegisterPassword.text.isEmpty()
            ||etRegisterUserName.text.isEmpty()){
            alertFail("Username ,National Id, Email, Password  are required")
        }else if ( !etRegisterPassword.text.toString().equals(etReInterPassword.text.toString())){
            alertFail("Password and Confirm Password doesn't match")
        }else{
            sendRegister()
        }
    }

    private fun sendRegister() {
        alertSuccess("Register is successful ")
    }

    private fun alertSuccess(s: String) {
        AlertDialog.Builder(this)
            .setTitle("Success")
            .setIcon(R.drawable.ic_check)
            .setMessage(s)
            .setPositiveButton("LogIn", DialogInterface.OnClickListener(){
                    dialog, which ->  onBackPressed()
            }).show()
    }

    private fun alertFail(s: String) {
        AlertDialog.Builder(this)
            .setTitle("Failed")
            .setIcon(R.drawable.ic_warning)
            .setMessage(s)
            .setPositiveButton("ok", DialogInterface.OnClickListener(){
                    dialog, which ->  dialog.dismiss()
            }).show()

    }
}