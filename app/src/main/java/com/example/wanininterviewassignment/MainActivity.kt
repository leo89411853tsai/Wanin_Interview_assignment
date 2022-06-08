package com.example.wanininterviewassignment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    var context = this@MainActivity
    var login: EditText? = null
    var password: EditText? = null
    var login_b: Button? = null
    var signup_b: Button? = null
    var mAuth = FirebaseAuth.getInstance()
    var email: String = ""
    var user_name = arrayOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        login = findViewById(R.id.Login)
        password = findViewById(R.id.Password)
        login_b = findViewById(R.id.LOGIN_B)
        signup_b = findViewById(R.id.SIGNUP_B)
        mAuth = FirebaseAuth.getInstance()

        signup_b?.setOnClickListener(View.OnClickListener {
            if(login?.text.toString() != "" && password?.text.toString() != ""){
                mAuth.createUserWithEmailAndPassword(login?.text.toString(), password?.text.toString())
            }
            else{
                Toast.makeText(this@MainActivity, "Please enter your account and password...", Toast.LENGTH_SHORT).show()
            }
        })

        login_b?.setOnClickListener(View.OnClickListener {
            if(login?.text.toString() != "" && password?.text.toString() != ""){
                mAuth.signInWithEmailAndPassword(login?.text.toString(), password?.text.toString()).addOnCompleteListener(context) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this@MainActivity, "Successful...", Toast.LENGTH_SHORT).show()
                        val user = mAuth.currentUser
                        email = user?.email.toString()
                        user_name = email.split("@").toTypedArray()
                        val intent = Intent()
                        intent.setClass(this@MainActivity, HelloXXX::class.java)
                        intent.putExtra("username", user_name[0])
                        intent.putExtra("email", email)
                        intent.putExtra("password", password?.getText().toString())
                        startActivity(intent)
                    }
                }.addOnFailureListener { e -> Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_SHORT).show() }
            }
            else{
                Toast.makeText(this@MainActivity, "Please enter your account and password...", Toast.LENGTH_SHORT).show()
            }

        })
    }
}