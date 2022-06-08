package com.example.wanininterviewassignment

import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import android.os.Bundle
import com.example.wanininterviewassignment.R
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.EmailAuthProvider
import com.google.android.gms.tasks.OnSuccessListener
import android.widget.Toast
import android.content.Intent
import android.view.View
import android.widget.Button
import com.example.wanininterviewassignment.MainActivity
import com.google.android.gms.tasks.OnFailureListener

class ChangePassword : AppCompatActivity() {
    var new_password: EditText? = null
    var type_again: EditText? = null
    var submit: Button? = null
    var mAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        new_password = findViewById(R.id.new_password)
        type_again = findViewById(R.id.type_again)
        submit = findViewById(R.id.submit)

        val bundle = intent.extras
        val email = bundle?.getString("email")
        val password = bundle?.getString("password")

        submit?.setOnClickListener(View.OnClickListener {
            if(new_password?.text.toString() != "" && type_again?.text.toString() != ""){
                if (new_password?.text.toString() == type_again?.text.toString()) {
                    val user = mAuth.currentUser
                    val authCredential = EmailAuthProvider.getCredential(user?.email.toString(), password.toString())
                    user?.reauthenticate(authCredential)?.addOnSuccessListener {
                        user?.updatePassword(new_password?.text.toString())?.addOnSuccessListener {
                            Toast.makeText(this@ChangePassword, "Password Updated...", Toast.LENGTH_SHORT).show()
                            val intent = Intent()
                            intent.setClass(this@ChangePassword, MainActivity::class.java)
                            startActivity(intent)
                        }?.addOnFailureListener { e -> Toast.makeText(this@ChangePassword, e.message, Toast.LENGTH_SHORT).show() }
                    }
                } else {
                    Toast.makeText(this@ChangePassword, "Input Error...", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(this@ChangePassword, "Please enter your password...", Toast.LENGTH_SHORT).show()
            }
        })
    }
}