package com.example.wanininterviewassignment

import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import android.os.Bundle
import com.example.wanininterviewassignment.R
import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.wanininterviewassignment.ChangePassword
import android.widget.Toast
import com.example.wanininterviewassignment.MainActivity

class HelloXXX : AppCompatActivity() {
    var hello_user: TextView? = null
    var change_password: Button? = null
    var logout: Button? = null
    var mAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hello_xxx)

        hello_user = findViewById(R.id.hello_tv)
        change_password = findViewById(R.id.changepassword)
        logout = findViewById(R.id.logout)

        val bundle = intent.extras
        val username = bundle!!.getString("username")
        val email = bundle.getString("email")
        val password = bundle.getString("password")
        hello_user?.setText("Hello,$username")
        change_password?.setOnClickListener(View.OnClickListener {
            val intent = Intent()
            intent.setClass(this@HelloXXX, ChangePassword::class.java)
            intent.putExtra("email", email)
            intent.putExtra("password", password)
            startActivity(intent)
        })
        logout?.setOnClickListener(View.OnClickListener {
            mAuth.signOut()
            Toast.makeText(this@HelloXXX, "LOG OUT ...", Toast.LENGTH_SHORT).show()
            val intent = Intent()
            intent.setClass(this@HelloXXX, MainActivity::class.java)
            startActivity(intent)
        })
    }
}