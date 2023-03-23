package com.example.giuaki

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class UserList : AppCompatActivity() {
    private lateinit var userName: TextInputEditText
    private lateinit var userEmail: TextInputEditText
    private lateinit var userPass: TextInputEditText

    private lateinit var btnUpdate: MaterialButton
    private lateinit var btnDelete: MaterialButton

    private lateinit var dbHelper: UserDBHelper

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)
        dbHelper = UserDBHelper(this, null)
        var data = Utils.userCurrent
        val passName = data.userName
        anhXa()
        setData(data)

        btnUpdate.setOnClickListener {
            val name = userName.text.toString()
            val email = userEmail.text.toString()
            val pass = userPass.text.toString()
            dbHelper.updateCourse(passName, name, email, pass)!!
            Toast.makeText(this, "Cập nhật thành công, vui lòng đăng nhập lại!", Toast.LENGTH_LONG).show()
            finish()
        }

        btnDelete.setOnClickListener {
            dbHelper.deleteCourse(passName)!!
            finish()
            Toast.makeText(this, "Đã xóa tài khoản, vui lòng đăng nhập lại", Toast.LENGTH_LONG).show()
        }
        
    }

    private fun setData(data: User) {
        userName.setText(data.userName.toString())
        userEmail.setText(data.userEmail.toString())
        userPass.setText(data.userPass.toString())
    }

    private fun anhXa() {
        userName = findViewById(R.id.userName)
        userPass = findViewById(R.id.userPass)
        userEmail = findViewById(R.id.userEmail)

        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }
}