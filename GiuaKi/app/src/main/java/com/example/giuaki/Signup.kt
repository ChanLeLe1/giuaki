package com.example.giuaki

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class Signup : Fragment() {
    private lateinit var userEmail: TextInputEditText
    private lateinit var userName: TextInputEditText
    private lateinit var userPass1: TextInputEditText
    private lateinit var userPass2: TextInputEditText

    private lateinit var btnSignup: MaterialButton

    private lateinit var dbHelper: UserDBHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dbHelper = UserDBHelper(requireContext(), null)
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_signup, container, false)
        anhXa(view)

        btnSignup.setOnClickListener {
            dangKy()
        }

        return view
    }

    private fun dangKy() {
        val email = userEmail.text.toString()
        val name = userName.text.toString()
        val pass1 = userPass1.text.toString()
        val pass2 = userPass2.text.toString()

        if(email!!.isEmpty() || name!!.isEmpty() || pass1!!.isEmpty()){
            Toast.makeText(context, "Vui lòng nhập đầy đủ các trường!", Toast.LENGTH_LONG).show()
        }else if(!pass1.equals(pass2)){
            Toast.makeText(context, "Mật khẩu xác nhận không giống với mật khẩu!", Toast.LENGTH_LONG).show()
        }else{
            dbHelper.addNewUser(name, email, pass1)
            Toast.makeText(context, "Đã đăng kí thành công!", Toast.LENGTH_LONG).show()
            userName.setText("")
            userEmail.setText("")
            userPass2.setText("")
            userPass1.setText("")
        }
    }

    private fun anhXa(view: View) {
        userEmail = view.findViewById(R.id.userEmail)
        userName = view.findViewById(R.id.userName)
        userPass1 = view.findViewById(R.id.userPass1)
        userPass2 = view.findViewById(R.id.userPass2)
        btnSignup = view.findViewById(R.id.btnSignup)
    }


}