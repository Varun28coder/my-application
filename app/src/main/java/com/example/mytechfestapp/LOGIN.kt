package com.example.mytechfestapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mytechfestapp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LOGIN : AppCompatActivity() {
    private lateinit var binding: com.example.mytechfestapp.databinding.ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.login.setOnClickListener {
            val Intent = Intent(this, REGISTERUSER::class.java)
            startActivity(Intent)
        }
        binding.login.setOnClickListener {
            val email = binding.username.text.toString()
            val password = binding.password.text.toString()

            if ( email.isNotEmpty() && password.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val Intent = Intent(this, MainActivity::class.java)
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
    override fun onStart() {
        super.onStart()

        if(firebaseAuth.currentUser != null){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

}