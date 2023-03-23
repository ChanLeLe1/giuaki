package com.example.giuaki

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText


class Login : Fragment() {
    private lateinit var userName: TextInputEditText
    private lateinit var userPass: TextInputEditText

    private lateinit var btnSignIn: MaterialButton

    private lateinit var dbHelper: UserDBHelper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        dbHelper = UserDBHelper(requireContext(), null)
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        anhXa(view)

        btnSignIn.setOnClickListener {
            danhNhap()
        }

        return view
    }

    private fun danhNhap() {
        val name = userName.text.toString()
        val pass = userPass.text.toString()

        if(name.isEmpty() || pass.isEmpty()){
            Toast.makeText(context, "Vui lòng nhập đủ các trường!", Toast.LENGTH_LONG).show()
        }else{
            val dataList: ArrayList<User> = dbHelper.readCourse()!!
            for(dataCurrent in dataList){
                if (name.equals(dataCurrent.userName)){
                    if(pass.equals(dataCurrent.userPass)){
                        Utils.userCurrent = dataCurrent
                        Toast.makeText(context, "Đăng nhập thành công", Toast.LENGTH_LONG).show()
                        userName.setText("")
                        userPass.setText("")
                        val intent = Intent(context, UserList::class.java)
                        startActivity(intent)
                        }else{
                        Toast.makeText(context, "Mật khẩu sai!", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun anhXa(view: View) {
        userName = view.findViewById(R.id.userName)
        userPass = view.findViewById(R.id.userPass)
        btnSignIn = view.findViewById(R.id.btnSignIn)
    }

}