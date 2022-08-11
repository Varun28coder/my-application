package com.example.mytechfestapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.example.mytechfestapp.databinding.ActivityLoginBinding.inflate
import com.example.mytechfestapp.databinding.ActivityRegisteruserBinding
import com.google.firebase.auth.FirebaseAuth

class REGISTERUSER : AppCompatActivity() {

    private lateinit var binding: ActivityRegisteruserBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registeruser)


        binding = ActivityRegisteruserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.SIGNIN.setOnClickListener {
            Toast.makeText(this, "PARTICIPANT REGISTERED", Toast.LENGTH_SHORT).show()
            val Intent = Intent(this, LOGIN::class.java)
            startActivity(Intent)
        }

        binding.registeruser.setOnClickListener {
            val name = binding.NAME.text.toString()
            val age = binding.AGE.text.toString()
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()

            if (name.isNotEmpty() && age.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val Intent = Intent(this, LOGIN::class.java)
                        startActivity(Intent)
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }


            } else {
                Toast.makeText(this, "EMPTY FIELDS ON ALLOWED!!!", Toast.LENGTH_SHORT).show()
            }


        }


    }