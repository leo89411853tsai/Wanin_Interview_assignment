package com.example.wanininterviewassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class HelloXXX extends AppCompatActivity {

    TextView hello_user;
    Button change_password,logout;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_xxx);

        hello_user = findViewById(R.id.hello_tv);
        change_password = findViewById(R.id.changepassword);
        logout = findViewById(R.id.logout);

        mAuth = FirebaseAuth.getInstance();

        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("username");
        String email = bundle.getString("email");
        String password = bundle.getString("password");

        hello_user.setText("Hello," + username);

        change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(HelloXXX.this, ChangePassword.class);
                intent.putExtra("email", email);
                intent.putExtra("password", password);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Toast.makeText(HelloXXX.this, "LOG OUT ...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(HelloXXX.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}